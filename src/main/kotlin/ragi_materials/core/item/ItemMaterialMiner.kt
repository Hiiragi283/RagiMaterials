package ragi_materials.core.item

import net.minecraft.block.state.IBlockState
import net.minecraft.client.renderer.block.model.ModelResourceLocation
import net.minecraft.client.resources.I18n
import net.minecraft.client.util.ITooltipFlag
import net.minecraft.item.ItemStack
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import ragi_materials.core.material.tool.ToolProperty
import ragi_materials.main.client.model.ICustomModel
import ragi_materials.main.client.model.ModelManager

object ItemMaterialMiner : ItemToolBase("pickaxe_material", setOf("axe", "pickaxe", "shovel"), ToolProperty("dummy", 255, 2, 8.0f, 0.0f, 0.0f, 1)), ICustomModel {

    //    General    //

    //適正によらず速度は一定
    override fun getDestroySpeed(stack: ItemStack, state: IBlockState) = property.efficiency


    //    Client    //

    @SideOnly(Side.CLIENT)
    override fun addInformation(stack: ItemStack, world: World?, tooltip: MutableList<String>, flag: ITooltipFlag) {
        val path = stack.item.registryName!!.path
        tooltip.add("§e=== Info ===")
        for (i in 0..1) {
            tooltip.add(I18n.format("tips.ragi_materials.${path}.$i"))
        }
    }

    //    ICustomModel    //

    override fun registerCustomModel() {
        ModelManager.setModelAlt(this, ModelResourceLocation("minecraft:iron_pickaxe", "inventory"))
    }

}