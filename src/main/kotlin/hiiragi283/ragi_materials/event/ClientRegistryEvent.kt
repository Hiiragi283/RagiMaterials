package hiiragi283.ragi_materials.event

import hiiragi283.ragi_materials.RagiMaterials
import hiiragi283.ragi_materials.RagiRegistry
import hiiragi283.ragi_materials.block.IMaterialBlock
import hiiragi283.ragi_materials.client.model.ICustomModel
import hiiragi283.ragi_materials.client.model.ModelManager
import hiiragi283.ragi_materials.item.IMaterialItem
import hiiragi283.ragi_materials.item.ItemBlockBase
import hiiragi283.ragi_materials.material.RagiMaterial
import hiiragi283.ragi_materials.util.RagiLogger
import net.minecraft.client.renderer.block.model.ModelResourceLocation
import net.minecraft.item.Item
import net.minecraft.item.ItemBlock
import net.minecraftforge.client.event.ColorHandlerEvent
import net.minecraftforge.client.event.ModelRegistryEvent
import net.minecraftforge.client.model.ModelLoader
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

@SideOnly(Side.CLIENT)
object ClientRegistryEvent {

    @SubscribeEvent
    fun registerModel(event: ModelRegistryEvent) {
        //モデルの自動登録
        RagiRegistry.setItems.forEach {
            if (it is ItemBlockBase) {
                val block = it.block
                if (block is ICustomModel) block.registerCustomModel() else ModelManager.setModel(it)
                RagiLogger.infoDebug("The model for item block ${it.registryName} is registered!")
            } else {
                if (it is ICustomModel) it.registerCustomModel() else ModelManager.setModel(it)
                RagiLogger.infoDebug("The model for item ${it.registryName} is registered!")
            }
        }

        for (material in RagiMaterial.list) {
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
        }, *RagiRegistry.setIMaterialBlock.toTypedArray())

        //Item Block
        itemColors.registerItemColorHandler({ stack, tintIndex ->
            val item = stack.item as ItemBlock
            val block = item.block
            if (block is IMaterialBlock) block.getColor(stack, tintIndex).rgb else 0xFFFFFF
        }, *RagiRegistry.setIMaterialItemBlock.toTypedArray())

        //Item
        itemColors.registerItemColorHandler({ stack, tintIndex ->
            val item = stack.item
            if (item is IMaterialItem) item.getColor(stack, tintIndex).rgb else 0xFFFFFF
        }, *RagiRegistry.setIMaterialItem.toTypedArray())

    }

}