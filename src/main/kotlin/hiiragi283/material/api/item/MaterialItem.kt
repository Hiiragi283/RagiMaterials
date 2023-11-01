package hiiragi283.material.api.item

import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.api.part.PartConvertible
import hiiragi283.material.api.shape.HiiragiShape
import hiiragi283.material.init.HiiragiCreativeTabs
import hiiragi283.material.util.itemStack
import hiiragi283.material.util.toModelLocation
import net.minecraft.client.renderer.color.IItemColor
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.ItemStack
import net.minecraft.util.NonNullList
import net.minecraft.util.ResourceLocation
import net.minecraftforge.client.model.ModelLoader
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import net.minecraftforge.oredict.OreDictionary

open class MaterialItem(final override val shape: HiiragiShape) : HiiragiItem(
    shape.name,
    Short.MAX_VALUE.toInt()
), PartConvertible.ITEM {

    init {
        creativeTab = HiiragiCreativeTabs.MATERIAL_ITEM
    }

    //    Client    //

    @SideOnly(Side.CLIENT)
    override fun getItemStackDisplayName(stack: ItemStack): String =
        getPart(stack)?.getTranslatedName() ?: super.getItemStackDisplayName(stack)

    @SideOnly(Side.CLIENT)
    override fun getSubItems(tab: CreativeTabs, subItems: NonNullList<ItemStack>) {
        if (!isInCreativeTab(tab)) return
        HiiragiMaterial.REGISTRY.getValidIndexValues()
            .filter(HiiragiMaterial::isValidIndex)
            .filter(shape::canCreateMaterialItem)
            .map(::itemStack)
            .forEach(subItems::add)
    }

    //    HiiragiEntry    //

    override fun onRegister() {
        PartConvertible.ITEM.register(this)
    }

    override fun onInit() {
        HiiragiMaterial.REGISTRY.getValidIndexValues()
            .filter(shape::canCreateMaterialItem)
            .forEach {
                OreDictionary.registerOre(shape.getOreDict(it), itemStack(it))
                registerRecipe(it)
            }
    }

    open fun registerRecipe(material: HiiragiMaterial) {}

    @SideOnly(Side.CLIENT)
    override fun getItemColor(): IItemColor = HiiragiMaterial.ITEM_COLOR

    @SideOnly(Side.CLIENT)
    override fun registerModel() {
        val allIcons: Set<ResourceLocation> = HiiragiMaterial.REGISTRY.getValidIndexValues()
            .map(HiiragiMaterial::iconSet)
            .mapNotNull { it[shape] }
            .toSet()
        ModelLoader.registerItemVariants(this, *allIcons.toTypedArray())
        ModelLoader.setCustomMeshDefinition(this) { stack: ItemStack ->
            (getMaterial(stack)?.iconSet?.get(shape) ?: this.registryName!!).toModelLocation()
        }
    }

}