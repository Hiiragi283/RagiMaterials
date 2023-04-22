package ragi_materials.core.block

import ragi_materials.core.RagiMaterials
import net.minecraft.block.Block
import net.minecraft.block.material.Material
import net.minecraft.client.resources.I18n
import net.minecraft.client.util.ITooltipFlag
import net.minecraft.item.ItemBlock
import net.minecraft.item.ItemStack
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

abstract class BlockBase(ID: String, Material: Material, private val maxTips: Int) : Block(Material) {

    abstract val itemBlock: ItemBlock?

    init {
        setRegistryName(RagiMaterials.MOD_ID, ID)
        translationKey = ID
    }

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