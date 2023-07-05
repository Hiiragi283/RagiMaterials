package hiiragi283.material.item

import hiiragi283.material.RagiMaterials
import hiiragi283.material.material.MaterialRegistry
import hiiragi283.material.material_part.IMaterialPart
import hiiragi283.material.material_part.MaterialPart
import hiiragi283.material.material_part.MaterialPartRegistry
import hiiragi283.material.part.HiiragiPart
import net.minecraft.client.renderer.color.ItemColors
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.init.Items
import net.minecraft.item.ItemStack
import net.minecraft.util.NonNullList
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import net.minecraftforge.oredict.OreDictionary

private val CREATIVE_TAB = object : CreativeTabs("${RagiMaterials.MODID}.material") {
    override fun createIcon(): ItemStack = ItemStack(Items.IRON_INGOT)
}

open class ItemMaterialBase(val part: HiiragiPart) : RMItemBase(part.name, 32767), IMaterialPart<ItemStack> {

    init {
        creativeTab = CREATIVE_TAB
    }

    //    Client    //

    @SideOnly(Side.CLIENT)
    override fun getItemStackDisplayName(stack: ItemStack): String =
        getPart(stack).translatedName(getMaterial(stack))

    @SideOnly(Side.CLIENT)
    override fun getSubItems(tab: CreativeTabs, subItems: NonNullList<ItemStack>) {
        if (!isInCreativeTab(tab)) return
        MaterialRegistry.getMaterials()
            .filter { part.isMatch(it) || it.isAdditionalPart(part.name) }
            .map { ItemStack(this, 1, it.index) }
            .forEach { subItems.add(it) }
    }

    //    RMEntry    //

    override fun registerMaterialPart() {
        MaterialRegistry.getMaterials()
            .filter { part.isMatch(it) || it.isAdditionalPart(part.name) }
            .map { MaterialPart(part, it) }
            .forEach {
                MaterialPartRegistry.registerTag(ItemStack(this, 1, it.material.index), it)
            }
    }

    override fun registerOreDict() {
        MaterialRegistry.getMaterials()
            .filter { part.isMatch(it) || it.isAdditionalPart(part.name) }
            .map { MaterialPart(part, it) }
            .forEach {
                OreDictionary.registerOre(it.getOreDict(), ItemStack(this, 1, it.material.index))
                MaterialPartRegistry.registerTag(it.getOreDict(), it)
            }
    }

    override fun registerRecipe() {
        MaterialRegistry.getMaterials()
            .filter { part.isMatch(it) || it.isAdditionalPart(part.name) }
            .forEach { part.recipe(this, it) }
    }

    @SideOnly(Side.CLIENT)
    override fun registerColorItem(itemColors: ItemColors) {
        itemColors.registerItemColorHandler({ stack, tintIndex ->
            val material = getMaterial(stack)
            if (!material.isEmpty() && tintIndex == 0) material.color else -1
        }, this)
    }

    @SideOnly(Side.CLIENT)
    override fun registerModel() = part.model(this)

    //    IMaterialPart    //

    override fun getMaterialPart(obj: ItemStack): MaterialPart = MaterialPartRegistry.getMaterialPart(obj)

}