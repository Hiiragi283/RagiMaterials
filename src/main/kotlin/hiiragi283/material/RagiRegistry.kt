package hiiragi283.material

import hiiragi283.material.block.BlockBase
import hiiragi283.material.client.color.IColorHandler
import hiiragi283.material.item.ItemBase
import hiiragi283.material.item.ItemMaterial
import hiiragi283.material.material.MaterialRegistry
import net.minecraft.core.Registry

object RagiRegistry {

    //    Collection    //

    private val setBlocks: MutableSet<BlockBase> = mutableSetOf()
    private val setBlocksColored: MutableSet<BlockBase> = mutableSetOf()
    private val setItems: MutableSet<ItemBase> = mutableSetOf()
    private val setItemsColored: MutableSet<ItemBase> = mutableSetOf()

    fun getBlocks() = setBlocks.toSet()
    fun getBlocksColored() = setBlocksColored.toSet()
    fun getItems() = setItems.toSet()
    fun getItemsColored() = setItemsColored.toSet()

    //    Item    //

    val INGOT_COPPER = ItemMaterial.Ingot(MaterialRegistry.COPPER)
    val INGOT_GOLD = ItemMaterial.Ingot(MaterialRegistry.GOLD)
    val INGOT_IRON = ItemMaterial.Ingot(MaterialRegistry.IRON)

    //    Registration    //

    fun load() {
        for (field in this::class.java.declaredFields) {
            field.isAccessible = true
            val obj = field.get(this)
            //Block
            if (obj is BlockBase) {
                setBlocks.add(obj)
                RagiMaterials.LOGGER.info("The block ${obj.getRegistryName()} is added to collection!")
                if (obj is IColorHandler.BLOCK) setBlocksColored.add(obj)
            }
            //Item
            if (obj is ItemBase) {
                setItems.add(obj)
                RagiMaterials.LOGGER.info("The item ${obj.getRegistryName()} is added to collection!")
                if (obj is IColorHandler.ITEM) setItemsColored.add(obj)
            }
        }
    }

    fun register() {
        //Block
        for (block in setBlocks) {
            registerBlock(block)
            RagiMaterials.LOGGER.info("The block ${block.getRegistryName()} is registered!")
        }
        //Item
        for (item in setItems) {
            registerItem(item)
            RagiMaterials.LOGGER.info("The item ${item.getRegistryName()} is registered!")
        }
    }

    private fun registerBlock(block: BlockBase) {
        Registry.register(Registry.BLOCK, block.getRegistryName(), block)
    }

    private fun registerItem(item: ItemBase) {
        Registry.register(Registry.ITEM, item.getRegistryName(), item)
    }

}