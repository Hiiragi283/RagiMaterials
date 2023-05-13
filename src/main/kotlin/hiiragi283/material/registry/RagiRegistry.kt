package hiiragi283.material.registry

import hiiragi283.material.RagiMaterials
import hiiragi283.material.base.ItemBase
import hiiragi283.material.client.color.IColorHandler
import hiiragi283.material.item.ItemMaterial
import hiiragi283.material.item.ItemMaterialBlock
import hiiragi283.material.item.ItemMaterialCrystal
import hiiragi283.material.util.RagiColor
import net.minecraft.item.Item
import net.minecraftforge.client.event.ColorHandlerEvent
import net.minecraftforge.client.event.ModelRegistryEvent
import net.minecraftforge.event.RegistryEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

object RagiRegistry {

    //    Collection    //

    private val items: MutableSet<ItemBase> = mutableSetOf()
    private val mapItemMaterials: LinkedHashMap<String, ItemMaterial> = linkedMapOf()

    fun getItemMaterials(): Collection<ItemMaterial> = mapItemMaterials.values

    //    Item    //

    val ITEM_PART_BLOCK = ItemMaterialBlock
    val ITEM_PART_CRYSTAL = ItemMaterialCrystal
    val ITEM_PART_DUST = ItemMaterial("dust")
    val ITEM_PART_DUST_TINY = ItemMaterial("dust_tiny", 0.1f)
    val ITEM_PART_GEAR = ItemMaterial("gear", 4.0f)
    val ITEM_PART_INGOT = ItemMaterial("ingot")
    val ITEM_PART_NUGGET = ItemMaterial("nugget", 0.1f)
    val ITEM_PART_PLATE = ItemMaterial("plate")
    val ITEM_PART_STICK = ItemMaterial("stick", 0.5f)

    //    Registration    //

    fun load() {
        for (field in this::class.java.declaredFields) {
            field.isAccessible = true
            val obj = field.get(this)
            if (obj is ItemBase) {
                items.add(obj)
                RagiMaterials.LOGGER.debug("The item ${obj.registryName} was added to collection!")
                if (obj is ItemMaterial) mapItemMaterials[obj.getOrePrefix()] = obj
            }
        }
    }

    @SubscribeEvent
    fun registerItems(event: RegistryEvent.Register<Item>) {
        val registry = event.registry
        for (item in items) {
            item.register(registry)
        }
    }

    fun registerOreDict() {
        for (item in items) {
            item.registerOreDict()
        }
    }

    fun registerRecipe() {
        for (item in items) {
            item.registerRecipe()
        }
    }

    @SideOnly(Side.CLIENT)
    object Client {

        @SubscribeEvent
        fun registerModel(event: ModelRegistryEvent) {
            for (item in items) {
                item.registerModel()
                RagiMaterials.LOGGER.debug("The model for item ${item.registryName} is registered!")
            }
        }

        @SubscribeEvent
        fun registerColor(event: ColorHandlerEvent.Item) {
            event.itemColors.registerItemColorHandler({ stack, tintIndex ->
                val item = stack.item
                if (item is IColorHandler.Item) item.getColor(stack, tintIndex).rgb else RagiColor.WHITE.rgb
            }, *items.toTypedArray())
        }
    }
}