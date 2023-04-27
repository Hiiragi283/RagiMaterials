package ragi_materials.metallurgy.tile

import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.tileentity.TileEntityFurnace
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumHand
import net.minecraft.util.math.BlockPos
import net.minecraft.util.text.TextComponentTranslation
import net.minecraft.world.World
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.items.CapabilityItemHandler
import net.minecraftforge.items.ItemStackHandler
import ragi_materials.core.RagiMaterials
import ragi_materials.core.RagiRegistry
import ragi_materials.core.tile.TileBase
import ragi_materials.core.util.*

class TileForgeFurnace : TileBase() {

    var fuel = 0
    var fuelMax = 200 * 80 * 64
    val inventory = object : ItemStackHandler(1) {

        override fun insertItem(slot: Int, stack: ItemStack, simulate: Boolean): ItemStack {
            return if (isFuel(stack)) {
                val burnTime = TileEntityFurnace.getItemBurnTime(stack) * stack.count
                if (fuel + burnTime <= fuelMax) {
                    fuel += burnTime //燃料を投入
                    playSoundFuel()
                    RagiMaterials.LOGGER.debug("Fuel: $fuel")
                    ItemStack.EMPTY //燃料は消失する
                } else stack //上限に達していたら搬入不可能
            } else stack //燃焼不可能な素材なら搬入不可能
        }
    }

    //    NBT tag    //

    override fun writeToNBT(tag: NBTTagCompound): NBTTagCompound {
        super.writeToNBT(tag)
        tag.setInteger("fuel", fuel) //燃料をtagに書き込む
        return tag
    }

    override fun readFromNBT(tag: NBTTagCompound) {
        super.readFromNBT(tag)
        fuel = tag.getInteger("fuel") //tagから燃料を読み込む
    }

    //    Capability    //

    override fun <T : Any?> getCapability(capability: Capability<T>, facing: EnumFacing?): T? {
        return if (hasCapability(capability, null)) CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(inventory) else null
    }

    override fun hasCapability(capability: Capability<*>, facing: EnumFacing?): Boolean {
        return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY
    }

    //    TileBase    //

    override fun onTileActivated(world: World, pos: BlockPos, player: EntityPlayer, hand: EnumHand, facing: EnumFacing): Boolean {
        var result = false
        if (!world.isRemote) {
            val stack = player.getHeldItem(hand)
            result = if (isFuel(stack)) {
                player.setHeldItem(hand, inventory.insertItem(0, stack, false)) //燃料を搬入
                true
            } else doProcess(player, hand) //レシピを実行
            if (result) succeeded(this) else {
                player.sendMessage(TextComponentTranslation("text.ragi_materials.fuel_point", fuel)) //燃料を表示
                failed(this)
            }
        }
        return result
    }

    //    Recipe    //

    private fun doProcess(player: EntityPlayer, hand: EnumHand): Boolean {
        var result = false
        val stack = player.getHeldItem(hand)
        for (recipe in RagiRegistry.FF_RECIPE.valuesCollection) {
            if (recipe.match(stack, fuel)) {
                fuel -= recipe.getFuel() //燃料を減らす
                RagiMaterials.LOGGER.debug("Fuel: $fuel")

                stack.shrink(1) //手持ちのアイテムを1つ減らす
                dropItemAtPlayer(player, recipe.getOutput()) //完成品をプレイヤーに渡す

                playSound(this, getSound("minecraft:block.fire.extinguish"))
                result = true
                break
            }
        }
        return result
    }

    //かまどのタイルエンティティから燃焼時間を取得
    private fun isFuel(stack: ItemStack): Boolean = TileEntityFurnace.getItemBurnTime(stack) > 0

    fun playSoundFuel() {
        playSound(this, getSound("minecraft:block.gravel.place"), 1.0f, 0.5f)
    }

}