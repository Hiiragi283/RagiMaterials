package hiiragi283.material

import hiiragi283.material.block.BlockBase
import hiiragi283.material.client.color.IColorHandler
import hiiragi283.material.item.ItemBase
import hiiragi283.material.item.ItemMaterialIngot
import hiiragi283.material.material.MaterialRegistry
import net.minecraft.util.registry.Registry

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

    val INGOT_COPPER = ItemMaterialIngot(MaterialRegistry.COPPER)
    val INGOT_GOLD = ItemMaterialIngot(MaterialRegistry.GOLD)
    val INGOT_IRON = ItemMaterialIngot(MaterialRegistry.IRON)

    //    Registration    //

    fun load() {
        for (field in this::class.java.declaredFields) {
            field.isAccessible = true
            val obj = field.get(this)
            //Block
            if (obj is BlockBase) {
                setBlocks.add(obj)
                RagiMaterials.LOGGER.info("The block ${obj.getIdentifier()} is added to collection!")
                if (obj is IColorHandler.BLOCK) setBlocksColored.add(obj)
            }
            //Item
            if (obj is ItemBase) {
                setItems.add(obj)
                RagiMaterials.LOGGER.info("The item ${obj.getIdentifier()} is added to collection!")
                if (obj is IColorHandler.ITEM) setItemsColored.add(obj)
            }
        }
    }

    fun register() {
        //Block
        for (block in setBlocks) {
            registerBlock(block)
            RagiMaterials.LOGGER.info("The block ${block.getIdentifier()} is registered!")
        }
        //Item
        for (item in setItems) {
            registerItem(item)
            RagiMaterials.LOGGER.info("The item ${item.getIdentifier()} is registered!")
        }
    }

    private fun registerBlock(block: BlockBase) {
        Registry.register(Registry.BLOCK, block.getIdentifier(), block)
    }

    private fun registerItem(item: ItemBase) {
        Registry.register(Registry.ITEM, item.getIdentifier(), item)
    }

}