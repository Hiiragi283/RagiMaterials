package hiiragi283.ragi_materials.tile

import hiiragi283.ragi_materials.RagiMaterials
import hiiragi283.ragi_materials.api.capability.EnumIOType
import hiiragi283.ragi_materials.api.capability.item.RagiItemHandler
import hiiragi283.ragi_materials.api.capability.item.RagiItemHandlerWrapper
import hiiragi283.ragi_materials.api.recipe.MillRecipe
import hiiragi283.ragi_materials.api.registry.RagiRegistries
import hiiragi283.ragi_materials.block.BlockStoneMill
import hiiragi283.ragi_materials.container.ContainerStoneMill
import hiiragi283.ragi_materials.proxy.CommonProxy
import hiiragi283.ragi_materials.util.RagiResult
import hiiragi283.ragi_materials.util.RagiUtil
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.entity.player.InventoryPlayer
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumHand
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.items.CapabilityItemHandler

class TileStoneMill : TileItemHandlerBase() {

    val input = RagiItemHandler(1).setIOType(EnumIOType.INPUT)
    val output = RagiItemHandler(1).setIOType(EnumIOType.OUTPUT)
    val inventory = RagiItemHandlerWrapper(input, output)

    private var cache: MillRecipe? = null

    //    NBT tag    //

    override fun writeToNBT(tag: NBTTagCompound): NBTTagCompound {
        super.writeToNBT(tag)
        tag.setTag(keyInventory, inventory.serializeNBT()) //インベントリをtagに書き込む
        return tag
    }

    override fun readFromNBT(tag: NBTTagCompound) {
        super.readFromNBT(tag)
        inventory.deserializeNBT(tag.getCompoundTag(keyInventory)) //tagからインベントリを読み込む
    }

    //    Capability    //

    override fun <T : Any?> getCapability(capability: Capability<T>, facing: EnumFacing?): T? {
        return if (hasCapability(capability, facing)) CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(inventory) else null
    }

    override fun hasCapability(capability: Capability<*>, facing: EnumFacing?): Boolean = capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY

    //    Recipe    //

    private fun doProcess() {
        var result = false
        if (cacheRecipe()) {
            RagiMaterials.LOGGER.debug("The recipe cached is ${cache!!.registryName}")
            input.extractItem(0, cache!!.getInput().count, false)
            val stackExtra = output.insertItem(0, cache!!.getOutput(), false)
            if (!stackExtra.isEmpty) RagiUtil.dropItem(world, pos.add(0, 1, 0), stackExtra, 0.0, 0.25, 0.0)
            result = true
        }
        if (result) RagiResult.succeeded(this) else RagiResult.failed(this)
    }

    private fun cacheRecipe(): Boolean {
        val stack = input.getStackInSlot(0)
        var result = false
        //cacheが空の場合，新規で検索する
        if (cache == null) {
            for (recipe in RagiRegistries.MILL_RECIPE.valuesCollection) {
                if (recipe.match(stack)) {
                    cache = recipe
                    result = true
                    break
                }
            }
        }
        //cacheがある場合，それが現在のレシピに適応できないなら空にする
        else if (cache!!.match(stack)) result = true else cache = null
        return result
    }

    //    TileBase    //

    override fun onTileActivated(world: World, pos: BlockPos, player: EntityPlayer, hand: EnumHand, facing: EnumFacing): Boolean {
        if (!world.isRemote) {
            if (player.isSneaking) {
                player.openGui(RagiMaterials.INSTANCE, CommonProxy.TileID, world, pos.x, pos.y, pos.z)
            } else {
                val state = world.getBlockState(pos)
                val count = state.getValue(BlockStoneMill.COUNT)
                if (count == 7) doProcess()
                world.setBlockState(pos, state.withProperty(BlockStoneMill.COUNT, (count + 1) % 8), 2) //8で割った余りにすることでオーバーフローを防止
            }
        }
        return true
    }

    //    TileItemHandlerBase    //

    override fun createContainer(playerInventory: InventoryPlayer, player: EntityPlayer) = ContainerStoneMill(player, this)

    override fun getGuiID() = "${RagiMaterials.MOD_ID}:stone_mill"

    override fun getName() = "gui.${RagiMaterials.MOD_ID}.stone_mill"

}