package hiiragi283.material.item

import hiiragi283.material.RagiMaterials
import hiiragi283.material.material.HiiragiMaterial
import hiiragi283.material.material.MaterialRegistry
import hiiragi283.material.material_part.IMaterialPart
import hiiragi283.material.material_part.MaterialPart
import hiiragi283.material.material_part.MaterialPartRegistry
import hiiragi283.material.part.HiiragiPart
import hiiragi283.material.util.RMModelManager
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

abstract class ItemMaterialBase(val part: HiiragiPart) : RMItemBase(part.name, 32767), IMaterialPart<ItemStack> {

    open fun isMatch(material: HiiragiMaterial): Boolean = material.isSolid()

    init {
        creativeTab = CREATIVE_TAB
    }

    //    Client    //

    /*@SideOnly(Side.CLIENT)
    override fun addInformation(stack: ItemStack, worldIn: World?, tooltip: MutableList<String>, flagIn: ITooltipFlag) {
        getMaterial(stack).run { if (!this.isEmpty()) this.getTooltip(tooltip, part) }
    }*/

    @SideOnly(Side.CLIENT)
    override fun getItemStackDisplayName(stack: ItemStack): String =
        getPart(stack).getTranslatedName(getMaterial(stack))

    @SideOnly(Side.CLIENT)
    override fun getSubItems(tab: CreativeTabs, subItems: NonNullList<ItemStack>) {
        if (!isInCreativeTab(tab)) return
        MaterialRegistry.getMaterials()
            .filter { isMatch(it) || it.isAdditionalPart(part.name) }
            .map { ItemStack(this, 1, it.index) }
            .forEach { subItems.add(it) }
    }

    //    IRMEntry    //

    override fun registerMaterialPart() {
        MaterialRegistry.getMaterials()
            .filter { isMatch(it) || it.isAdditionalPart(part.name) }
            .map { MaterialPart(part, it) }
            .forEach {
                MaterialPartRegistry.registerTag(ItemStack(this, 1, it.material.index), it)
            }
    }

    override fun registerOreDict() {
        MaterialRegistry.getMaterials()
            .filter { isMatch(it) || it.isAdditionalPart(part.name) }
            .map { MaterialPart(part, it) }
            .forEach {
                OreDictionary.registerOre(it.getOreDict(), ItemStack(this, 1, it.material.index))
                MaterialPartRegistry.registerTag(it.getOreDict(), it)
            }
    }

    override fun registerRecipe() {
        MaterialRegistry.getMaterials()
            .filter { isMatch(it) || it.isAdditionalPart(part.name) }
            .forEach { materialRecipe(it) }
    }

    abstract fun materialRecipe(material: HiiragiMaterial)

    @SideOnly(Side.CLIENT)
    override fun registerColorItem(itemColors: ItemColors) {
        itemColors.registerItemColorHandler({ stack, tintIndex ->
            val material = getMaterial(stack)
            if (!material.isEmpty() && tintIndex == 0) material.color else -1
        }, this)
    }

    @SideOnly(Side.CLIENT)
    override fun registerModel(): Unit = RMModelManager.setModelSame(this)

    //    IMaterialPart    //

    override fun getMaterialPart(obj: ItemStack): MaterialPart = MaterialPartRegistry.getMaterialPart(obj)

}