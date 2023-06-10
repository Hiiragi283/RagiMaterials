package hiiragi283.material.item

import hiiragi283.material.RagiMaterials
import hiiragi283.material.client.RMModelManager
import hiiragi283.material.init.IRMEntry
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.util.NonNullList
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import net.minecraftforge.registries.IForgeRegistry

abstract class RMItemBase(ID: String, private var maxMeta: Int) : Item(), IRMEntry<Item> {

    init {
        setRegistryName(RagiMaterials.MODID, ID)
        creativeTab = CreativeTabs.MISC
        hasSubtypes = maxMeta > 0
        maxMeta = 0.coerceAtLeast(maxMeta)
        translationKey = ID
    }

    //    General    //

    override fun getCreatorModId(stack: ItemStack): String = RagiMaterials.MODID

    override fun getMetadata(damage: Int): Int = if (damage in 0..maxMeta) damage else maxMeta

    override fun getTranslationKey(stack: ItemStack): String = StringBuilder().also {
        it.append(super.getTranslationKey())
        it.append("_")
        it.append(stack.metadata)
    }.toString()

    //    Client    //

    @SideOnly(Side.CLIENT)
    override fun getSubItems(tab: CreativeTabs, subItems: NonNullList<ItemStack>) {
        if (isInCreativeTab(tab)) {
            (0..maxMeta).forEach {
                subItems.add(ItemStack(this, 1, it))
            }
        }
    }

    //    IRMEntry    //

    override fun register(registry: IForgeRegistry<Item>) {
        registry.register(this)
        RagiMaterials.LOGGER.debug("The item $registryName was registered!")
    }

    @SideOnly(Side.CLIENT)
    override fun registerModel(): Unit = RMModelManager.setModel(this)

}