package ragi_materials.core.block

import net.minecraft.block.Block
import net.minecraft.block.material.Material
import net.minecraft.block.state.IBlockState
import net.minecraft.client.resources.I18n
import net.minecraft.client.util.ITooltipFlag
import net.minecraft.item.ItemStack
import net.minecraft.util.EnumFacing
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import ragi_materials.core.RagiMaterials
import ragi_materials.core.block.property.RagiProperty
import ragi_materials.core.item.ItemBlockBase

abstract class BlockBase(ID: String, Material: Material, private val maxTips: Int) : Block(Material) {

    abstract val itemBlock: ItemBlockBase?

    init {
        setRegistryName(RagiMaterials.MOD_ID, ID)
        translationKey = ID
    }

    //    BlockState    //

    fun getFacing(state: IBlockState): EnumFacing = state.getValue(RagiProperty.HORIZONTAL)

    //    Client    //

    @SideOnly(Side.CLIENT)
    override fun addInformation(stack: ItemStack, world: World?, tooltip: MutableList<String>, flag: ITooltipFlag) {
        val path = stack.item.registryName!!.path
        if (maxTips != -1) {
            tooltip.add("§e=== Info ===")
            for (i in 0..maxTips) {
                tooltip.add(I18n.format("tips.ragi_materials.${path}.$i"))
            }
        }
    }
}