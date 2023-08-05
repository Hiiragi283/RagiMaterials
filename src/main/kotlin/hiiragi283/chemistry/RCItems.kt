package hiiragi283.chemistry

import hiiragi283.api.HiiragiEntry
import hiiragi283.api.item.HiiragiItem
import hiiragi283.chemistry.item.ItemCast
import net.minecraft.client.renderer.color.BlockColors
import net.minecraft.client.renderer.color.ItemColors
import net.minecraft.item.Item
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import net.minecraftforge.registries.IForgeRegistry

object RCItems : HiiragiEntry.ITEM {

    @JvmField
    val CAST = ItemCast

    var list: List<HiiragiItem> = this::class.java.declaredFields
        .map { it.also { it.isAccessible = true } }
        .map { it.get(this) }
        .filterIsInstance<HiiragiItem>()

    override fun register(registry: IForgeRegistry<Item>) {
        RCBlocks.list.map { it.itemBlock }.forEach { registry.register(it) }
        list.forEach { registry.register(it) }
    }

    override fun registerOreDict() {
        list.forEach { it.registerOreDict() }
    }

    override fun registerRecipe() {
        list.forEach { it.registerRecipe() }
    }

    @SideOnly(Side.CLIENT)
    override fun registerColorBlock(blockColors: BlockColors) {
        list.forEach { it.registerColorBlock(blockColors) }
    }

    @SideOnly(Side.CLIENT)
    override fun registerColorItem(itemColors: ItemColors) {
        list.forEach { it.registerColorItem(itemColors) }
    }

    @SideOnly(Side.CLIENT)
    override fun registerModel() {
        list.forEach { it.registerModel() }
    }

}