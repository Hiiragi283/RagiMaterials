package hiiragi283.ragi_materials.event

import hiiragi283.ragi_materials.init.RagiInit
import hiiragi283.ragi_materials.util.RagiModel
import net.minecraft.block.state.IBlockState
import net.minecraft.client.renderer.block.model.ModelResourceLocation
import net.minecraft.client.renderer.block.statemap.StateMapperBase
import net.minecraftforge.client.event.ModelRegistryEvent
import net.minecraftforge.client.model.ModelLoader
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

class ModelRegistry {

    @SubscribeEvent
    fun modelRegistry(event: ModelRegistryEvent) {
        RagiModel.setModel(
            RagiInit.ItemBlockBlazeHeater,
            RagiInit.ItemBlockForgeFurnace,
            RagiInit.ItemBlockOreDictConv,
            RagiInit.ItemBlockSaltPond,

            RagiInit.ItemBlazingCube,
            RagiInit.ItemBookDebug,
            RagiInit.ItemForgeHammer,
            RagiInit.ItemToolBellow
        )
        RagiModel.setModelSame(RagiInit.ItemIngotHot)
        RagiModel.setModelMaterial(
            RagiInit.ItemBlockMetal,
            RagiInit.ItemDust,
            RagiInit.ItemIngot,
            RagiInit.ItemNugget,
            RagiInit.ItemPlate
        )
    }
}