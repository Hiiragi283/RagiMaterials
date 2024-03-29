package hiiragi283.material.api.item

import hiiragi283.material.api.ingredient.ItemIngredient
import hiiragi283.material.api.machine.MachineType
import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.api.part.HiiragiPart
import hiiragi283.material.api.part.PartConvertible
import hiiragi283.material.api.shape.HiiragiShape
import hiiragi283.material.init.HiiragiCreativeTabs
import hiiragi283.material.init.HiiragiRegistries
import hiiragi283.material.init.HiiragiShapes
import hiiragi283.material.item.ItemShapePattern
import hiiragi283.material.recipe.MachineRecipe
import hiiragi283.material.util.hiiragiLocation
import hiiragi283.material.util.isSameWithoutCount
import hiiragi283.material.util.itemStack
import hiiragi283.material.util.setModelSame
import net.minecraft.client.renderer.color.IItemColor
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.ItemStack
import net.minecraft.util.NonNullList
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
        HiiragiRegistries.MATERIAL_INDEX.getValue(stack.metadata)
            ?.let(shape::getTranslatedName)
            ?: super.getItemStackDisplayName(stack)

    @SideOnly(Side.CLIENT)
    override fun getSubItems(tab: CreativeTabs, subItems: NonNullList<ItemStack>) {
        if (!isInCreativeTab(tab)) return
        HiiragiRegistries.MATERIAL_INDEX.getValues()
            .filter(HiiragiMaterial::isValidIndex)
            .filter(shape::isValid)
            .map(::itemStack)
            .forEach(subItems::add)
    }

    //    HiiragiEntry    //

    override fun onRegister() {
        HiiragiRegistries.MATERIAL_ITEM.register(shape, this)
    }

    override fun registerOreDict() {
        HiiragiRegistries.MATERIAL_INDEX.getValues()
            .filter(shape::isValid)
            .forEach { material: HiiragiMaterial ->
                shape.getOreDicts(material).forEach {
                    OreDictionary.registerOre(it, itemStack(material))
                }
            }
    }

    override fun registerRecipe() {
        HiiragiRegistries.MATERIAL_INDEX.getValues()
            .filter { material: HiiragiMaterial -> material.isSolid() && shape.isValid(material) }
            .forEach { addRecipes(it) }
    }

    open fun addRecipes(material: HiiragiMaterial) {}

    fun addGrinderRecipe(material: HiiragiMaterial) {
        if (shape.scale < 144) return
        val part: HiiragiPart = shape.getPart(material)
        MachineRecipe.buildAndRegister(MachineType.GRINDER, hiiragiLocation(part.toString())) {
            inputItems.add(ItemIngredient.Parts(part))
            outputItems.add(
                HiiragiRegistries.MATERIAL_ITEM
                    .getValue(HiiragiShapes.DUST)
                    ?.item()
                    ?.itemStack(part)
                    ?: ItemStack.EMPTY
            )
        }
    }

    fun addMetalFormerRecipe(material: HiiragiMaterial, inputCount: Int = 1, outputCount: Int = 1) {
        if (!HiiragiShapes.INGOT.isValid(material) || !shape.isValid(material)) return
        val part: HiiragiPart = shape.getPart(material)
        MachineRecipe.buildAndRegister(
            MachineType.METAL_FORMER,
            hiiragiLocation(part.toString())
        ) {
            inputItems.add(ItemIngredient.Parts(HiiragiShapes.INGOT, part.material, inputCount))
            inputItems.add(
                ItemIngredient.Custom(
                    stacks = { listOf(ItemShapePattern.getItemStack(part.shape)) },
                    predicate = { stack -> stack.isSameWithoutCount(ItemShapePattern.getItemStack(part.shape)) },
                    process = ItemIngredient.CATALYST_PROCESS
                )
            )
            outputItems.add(part.getItemStack(outputCount))
        }
    }

    @SideOnly(Side.CLIENT)
    override fun getItemColor(): IItemColor = HiiragiMaterial.ITEM_COLOR

    @SideOnly(Side.CLIENT)
    override fun registerModel() = this.setModelSame()

}