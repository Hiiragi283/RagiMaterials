package hiiragi283.material.item

import hiiragi283.material.base.ItemBase
import hiiragi283.material.client.color.IColorHandler
import hiiragi283.material.client.model.RagiModelManager
import hiiragi283.material.creativetab.CreativeTabMaterial
import hiiragi283.material.material.MaterialRegistry
import hiiragi283.material.material.RagiMaterial
import hiiragi283.material.util.RagiColor
import net.minecraft.client.resources.I18n
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.ItemStack
import net.minecraft.util.NonNullList
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import net.minecraftforge.oredict.OreDictionary
import rechellatek.snakeToLowerCamelCase
import kotlin.math.roundToInt

abstract class ItemMaterial(private val ID: String, private val scale: Float = 1.0f) :
    ItemBase(ID, OreDictionary.WILDCARD_VALUE), IColorHandler.Item {

    init {
        creativeTab = CreativeTabMaterial
    }

    //    General    //

    override fun getItemBurnTime(stack: ItemStack): Int = (getMaterial(stack).burnTime * scale).roundToInt()

    //    Client    //

    @SideOnly(Side.CLIENT)
    override fun getItemStackDisplayName(stack: ItemStack): String =
        I18n.format("item.ragi_$ID.name", getMaterial(stack).getTranslatedName())

    @SideOnly(Side.CLIENT)
    override fun getSubItems(tab: CreativeTabs, subItems: NonNullList<ItemStack>) {
        if (isInCreativeTab(tab)) {
            for (material in MaterialRegistry.getMaterialAll()) {
                val stack = getStack(material)
                if (!stack.isEmpty) subItems.add(getStack(material))
            }
        }
    }

    //    ItemBase    //

    @SideOnly(Side.CLIENT)
    override fun registerModel(): Unit = RagiModelManager.setModelSame(this)

    override fun registerOreDict() {
        for (material in MaterialRegistry.getMaterialAll()) {
            val oredict = getOreDict(material)
            val stack = getStack(material)
            if (!stack.isEmpty) OreDictionary.registerOre(oredict, stack)
            //有効な部品でなくとも鉱石辞書の組み合わせは登録する
            MaterialRegistry.setMaterialWithOre(oredict, material)
        }
    }

    override fun registerRecipe() {
        for (material in MaterialRegistry.getMaterialAll()) {
            if (this in material.type.getParts()) registerRecipeMaterial(material)
        }
    }

    abstract fun registerRecipeMaterial(material: RagiMaterial)

    //    ItemMaterial    //

    private fun getMaterial(stack: ItemStack): RagiMaterial = MaterialRegistry.getMaterial(stack)

    fun getOreDict(material: RagiMaterial) = getOrePrefix() + material.getOreDict()

    fun getOrePrefix() = ID.snakeToLowerCamelCase()

    fun getStack(material: RagiMaterial, amount: Int = 1): ItemStack =
        if (material.isValidPart(this)) ItemStack(this, amount, material.index) else ItemStack.EMPTY

    //    IColorHandler    //

    override fun getColor(stack: ItemStack, tintIndex: Int) =
        if (tintIndex == 0) getMaterial(stack).color else RagiColor.WHITE

}