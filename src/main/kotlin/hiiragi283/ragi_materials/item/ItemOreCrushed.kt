package hiiragi283.ragi_materials.item

import hiiragi283.ragi_materials.RagiMaterials
import hiiragi283.ragi_materials.RagiRegistry
import hiiragi283.ragi_materials.block.IItemBlock
import hiiragi283.ragi_materials.client.model.ICustomModel
import hiiragi283.ragi_materials.client.model.ModelManager
import hiiragi283.ragi_materials.material.OreProperty
import net.minecraft.client.renderer.block.model.ModelResourceLocation
import net.minecraft.client.resources.I18n
import net.minecraft.item.ItemStack
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import java.awt.Color

class ItemOreCrushed : ItemBase(RagiMaterials.MOD_ID, "ore_crushed", OreProperty.mapOre1.size - 1), ICustomModel, IMaterialItem {

    val ore = RagiRegistry.BlockOre1 as IItemBlock

    //    Client    //

    @SideOnly(Side.CLIENT)
    override fun getItemStackDisplayName(stack: ItemStack): String = I18n.format("item.ore_crushed.name", I18n.format("${ore.getItemBlock()?.getTranslationKey(stack)}.name"))

    @SideOnly(Side.CLIENT)
    override fun registerCustomModel() {
        ModelManager.setModelAlt(this, ModelResourceLocation("${RagiMaterials.MOD_ID}:ore", "gravel"))
    }

    //    IMaterialItem    //

    override fun getColor(stack: ItemStack, tintIndex: Int): Color {
        val list = OreProperty.listOre1
        val index = stack.metadata % list.size
        return list[index].second.getColor()
    }
}