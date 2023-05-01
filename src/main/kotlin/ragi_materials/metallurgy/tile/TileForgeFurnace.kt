package ragi_materials.metallurgy.tile

import net.minecraft.entity.player.EntityPlayer
import net.minecraft.init.SoundEvents
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.tileentity.TileEntityFurnace
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumHand
import net.minecraft.util.math.BlockPos
import net.minecraft.util.text.TextComponentTranslation
import net.minecraft.world.World
import net.minecraftforge.items.CapabilityItemHandler
import net.minecraftforge.items.IItemHandler
import ragi_materials.core.RagiMaterials
import ragi_materials.core.RagiRegistry
import ragi_materials.core.capability.EnumIOType
import ragi_materials.core.capability.RagiCapabilityProvider
import ragi_materials.core.capability.item.RagiItemHandler
import ragi_materials.core.capability.item.RagiItemHandlerWrapper
import ragi_materials.core.tile.ITileProvider
import ragi_materials.core.tile.TileBase
import ragi_materials.core.util.dropItemAtPlayer
import ragi_materials.core.util.failed
import ragi_materials.core.util.playSound
import ragi_materials.core.util.succeeded

class TileForgeFurnace : TileBase(), ITileProvider.Inventory {

    lateinit var input: RagiItemHandler
    var fuel = 0
    var fuelMax = 200 * 80 * 64

    //    NBT tag    //

    override fun writeToNBT(tag: NBTTagCompound) = tag.also {
        super.writeToNBT(it)
        it.setInteger("fuel", fuel) //燃料をtagに書き込む
    }

    override fun readFromNBT(tag: NBTTagCompound) {
        super.readFromNBT(tag)
        fuel = tag.getInteger("fuel") //tagから燃料を読み込む
    }

    //    Capability    //

    override fun createInventory(): RagiCapabilityProvider<IItemHandler> {
        input = object : RagiItemHandler(1) {
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
        }.setIOType(EnumIOType.INPUT)
        inventory = RagiItemHandlerWrapper(input)
        return RagiCapabilityProvider(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, inventory, inventory)
    }

    //    TileBase    //

    override fun onTileActivated(world: World, pos: BlockPos, player: EntityPlayer, hand: EnumHand, facing: EnumFacing): Boolean {
        var result = false
        if (!world.isRemote) {
            val stack = player.getHeldItem(hand)
            result = if (isFuel(stack)) {
                player.setHeldItem(hand, input.insertItem(0, stack, false)) //燃料を搬入
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

                playSound(this, SoundEvents.BLOCK_FIRE_EXTINGUISH)
                result = true
                break
            }
        }
        return result
    }

    //かまどのタイルエンティティから燃焼時間を取得
    private fun isFuel(stack: ItemStack): Boolean = TileEntityFurnace.getItemBurnTime(stack) > 0

    fun playSoundFuel() {
        playSound(this, SoundEvents.BLOCK_GRAVEL_PLACE, 1.0f, 0.5f)
    }

}