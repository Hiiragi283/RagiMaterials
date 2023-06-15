package hiiragi283.material.item

import hiiragi283.material.client.RMModelManager
import hiiragi283.material.creativetab.CreativeTabMaterial
import hiiragi283.material.material.HiiragiMaterial
import hiiragi283.material.material.MaterialRegistry
import hiiragi283.material.material_part.IMaterialPart
import hiiragi283.material.material_part.MaterialPart
import hiiragi283.material.material_part.MaterialPartRegistry
import hiiragi283.material.part.HiiragiPart
import net.minecraft.client.renderer.color.ItemColors
import net.minecraft.client.resources.I18n
import net.minecraft.client.util.ITooltipFlag
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.ItemStack
import net.minecraft.util.NonNullList
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import net.minecraftforge.oredict.OreDictionary

abstract class ItemMaterialBase(val part: HiiragiPart) : RMItemBase(part.name, 32767), IMaterialPart<ItemStack> {

    open fun isMatch(material: HiiragiMaterial): Boolean = material.isSolid()

    init {
        creativeTab = CreativeTabMaterial
    }

    //    Client    //

    @SideOnly(Side.CLIENT)
    override fun addInformation(stack: ItemStack, worldIn: World?, tooltip: MutableList<String>, flagIn: ITooltipFlag) {
        val material = getMaterial(stack)
        if (!material.isEmpty()) {
            tooltip.add("§e=== Property ===")
            tooltip.add(I18n.format("tips.ragi_materials.property.name", material.name))
            if (material.hasFormula())
                tooltip.add(I18n.format("tips.ragi_materials.property.formula", material.formula))
            if (material.hasMolar())
                tooltip.add(I18n.format("tips.ragi_materials.property.mol", material.molar))
            if (material.hasTempMelt())
                tooltip.add(I18n.format("tips.ragi_materials.property.melt", material.tempMelt))
            if (material.hasTempBoil())
                tooltip.add(I18n.format("tips.ragi_materials.property.boil", material.tempBoil))
            if (material.hasTempSubl())
                tooltip.add(I18n.format("tips.ragi_materials.property.subl", material.tempSubl))
        }
    }

    @SideOnly(Side.CLIENT)
    override fun getItemStackDisplayName(stack: ItemStack): String =
        I18n.format(getPart(stack).translationKey, I18n.format(getMaterial(stack).translationKey))

    @SideOnly(Side.CLIENT)
    override fun getSubItems(tab: CreativeTabs, subItems: NonNullList<ItemStack>) {
        if (!isInCreativeTab(tab)) return
        MaterialRegistry.getMaterials()
            .filter { isMatch(it) }
            .map { ItemStack(this, 1, it.index) }
            .forEach { subItems.add(it) }
    }

    //    IRMEntry    //

    override fun registerMaterialPart() {
        MaterialRegistry.getMaterials()
            .filter { isMatch(it) }
            .map { MaterialPart(part, it) }
            .forEach {
                MaterialPartRegistry.registerTag(ItemStack(this, 1, it.material.index), it)
            }
    }

    override fun registerOreDict() {
        MaterialRegistry.getMaterials()
            .filter { isMatch(it) }
            .map { MaterialPart(part, it) }
            .forEach {
                OreDictionary.registerOre(it.getOreDictName(), ItemStack(this, 1, it.material.index))
            }
    }

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