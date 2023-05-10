package ragi_materials.core.event

import net.minecraft.client.renderer.block.model.ModelResourceLocation
import net.minecraft.item.Item
import net.minecraft.item.ItemBlock
import net.minecraftforge.client.event.ColorHandlerEvent
import net.minecraftforge.client.event.ModelRegistryEvent
import net.minecraftforge.client.model.ModelLoader
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import ragi_materials.core.RagiMaterials
import ragi_materials.core.RagiRegistry
import ragi_materials.core.material.IMaterialBlock
import ragi_materials.core.material.IMaterialItem
import ragi_materials.core.material.MaterialRegistry
import ragi_materials.main.client.model.ICustomModel
import ragi_materials.main.client.model.ModelManager

@SideOnly(Side.CLIENT)
object ClientRegistryEvent {

    @SubscribeEvent
    fun registerModel(event: ModelRegistryEvent) {
        //モデルの自動登録
        for (block in RagiRegistry.setBlocks) {
            if (block is ICustomModel) block.registerCustomModel() else ModelManager.setModel(block)
            RagiMaterials.LOGGER.debug("The model for item block ${block.registryName} is registered!")
        }
        for (item in RagiRegistry.setItems) {
            if (item is ICustomModel) item.registerCustomModel() else ModelManager.setModel(item)
            RagiMaterials.LOGGER.debug("The model for item ${item.registryName} is registered!")
        }

        for (material in MaterialRegistry.getMaterials()) {
            material.getFluid()?.let {
                val model = ModelResourceLocation(("${RagiMaterials.MOD_ID}:${it.name}"), "fluid")
                //アイテムとしての描画処理
                ModelLoader.setCustomMeshDefinition(Item.getItemFromBlock(it.block)) { model }
                //液体ブロックがnullでない場合, ブロックとしての描画処理を実装する
                if (it.block !== null) ModelManager.setStateMapperAlt(it.block, model)
            }
        }
    }

    @SubscribeEvent
    fun registerColor(event: ColorHandlerEvent.Item) {

        val blockColors = event.blockColors
        val itemColors = event.itemColors

        //Block
        blockColors.registerBlockColorHandler({ state, world, pos, tintIndex ->
            val block = state.block
            if (world !== null && pos !== null && block is IMaterialBlock) block.getColor(world, pos, state, tintIndex).rgb else 0xFFFFFF
        }, *RagiRegistry.setIMaterialBlocks.toTypedArray())

        //Item Block
        itemColors.registerItemColorHandler({ stack, tintIndex ->
            val item = stack.item as ItemBlock
            val block = item.block
            if (block is IMaterialBlock) block.getColor(stack, tintIndex).rgb else 0xFFFFFF
        }, *RagiRegistry.setIMaterialItemBlocks.toTypedArray())

        //Item
        itemColors.registerItemColorHandler({ stack, tintIndex ->
            val item = stack.item
            if (item is IMaterialItem) item.getColor(stack, tintIndex).rgb else 0xFFFFFF
        }, *RagiRegistry.setIMaterialItems.toTypedArray())

    }
}