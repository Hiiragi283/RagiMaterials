package hiiragi283.material.item

import hiiragi283.material.base.ItemBase
import hiiragi283.material.client.color.IRagiColored
import hiiragi283.material.client.model.ICustomModel
import hiiragi283.material.client.model.ModelManager
import hiiragi283.material.material.MaterialRegistry
import hiiragi283.material.material.RagiMaterial
import hiiragi283.material.util.RagiColor
import net.minecraft.client.resources.I18n
import net.minecraft.client.util.ITooltipFlag
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.ItemStack
import net.minecraft.util.NonNullList
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import net.minecraftforge.oredict.OreDictionary

open class ItemMaterial(private val ID: String, val prefixOre: String, val scale: Float = 1.0f) :
    ItemBase(ID, OreDictionary.WILDCARD_VALUE), ICustomModel, IRagiColored.Item {

    //    General    //

    override fun getItemBurnTime(stack: ItemStack): Int = getMaterial(stack).burnTime

    //    ClientEvent    //

    @SideOnly(Side.CLIENT)
    override fun addInformation(stack: ItemStack, world: World?, tooltip: MutableList<String>, flagIn: ITooltipFlag) {
        getMaterial(stack).getTooltip(tooltip)
    }

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

    override fun registerOreDict() {
        for (material in MaterialRegistry.getMaterialAll()) {
            val stack = getStack(material)
            if (!stack.isEmpty) OreDictionary.registerOre(prefixOre + material.getOreDict(), stack)
        }
    }

    //    ItemMaterial    //

    private fun getMaterial(stack: ItemStack): RagiMaterial = MaterialRegistry.getMaterial(stack.metadata)

    private fun getStack(material: RagiMaterial, amount: Int = 1) =
        if (material.isValidPart(this)) ItemStack(this, amount, material.index) else ItemStack.EMPTY

    //    ICustomModel    //

    override fun registerModel(): Unit = ModelManager.setModelSame(this)

    //    IRagiColored    //

    override fun getColor(stack: ItemStack, tintIndex: Int) =
        if (tintIndex == 0) getMaterial(stack).color else RagiColor.WHITE

}