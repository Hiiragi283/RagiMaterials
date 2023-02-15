package hiiragi283.ragi_materials.event

import hiiragi283.ragi_materials.init.RagiInit
import hiiragi283.ragi_materials.material.MaterialManager
import net.minecraft.block.state.IBlockState
import net.minecraft.client.renderer.color.IBlockColor
import net.minecraft.client.renderer.color.IItemColor
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockAccess
import net.minecraftforge.client.event.ColorHandlerEvent
import net.minecraftforge.fluids.capability.CapabilityFluidHandler
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent

class ColorHandler {
    @SubscribeEvent
    fun colorHandler(event: ColorHandlerEvent.Item) {
        //変数の宣言
        val blockColors = event.blockColors
        val itemColors = event.itemColors

        //BlockとItemの着色
        //blockColors.registerBlockColorHandler()

        itemColors.registerItemColorHandler(
            IItemColor { stack, tintIndex ->
                //メタデータからmaterialを取得
                val material = MaterialManager.getMaterial(stack.metadata)
                //tintIndexが0ならばmaterial.getColor()，そうでないなら白を返す
                if (tintIndex == 0) material.getColor().rgb else 0xFFFFFF
            },
            RagiInit.ItemBlockMetal,
            RagiInit.ItemDust,
            RagiInit.ItemDustTiny,
            RagiInit.ItemIngot,
            RagiInit.ItemIngotHot,
            RagiInit.ItemNugget,
            RagiInit.ItemPlate
        )

        itemColors.registerItemColorHandler(
            IItemColor { stack, tintIndex ->
                //NBTタグがnullでない場合
                if (stack.tagCompound !== null) {
                    //NBTタグからmaterialを取得
                    val tag = stack.tagCompound!!
                    val material = MaterialManager.getMaterial(tag.getString("material"))
                    //tintIndexが1ならばmaterial.getColor()，そうでないなら白を返す
                    if (tintIndex == 1) material.getColor().rgb else 0xFFFFFF
                }
                //NBTタグがnullの場合
                else 0xFFFFFF //白色を返す
            }, RagiInit.ItemForgeHammer
        )

        itemColors.registerItemColorHandler(
            IItemColor { stack, tintIndex ->
                val fluidItem = stack.getCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY, null)
                if ((fluidItem !== null) && (fluidItem.tankProperties[0].contents?.fluid !== null)) {
                    if (tintIndex == 0) fluidItem.tankProperties[0].contents?.fluid?.color!! else 0xFFFFFF
                } else 0xFFFFFF
            }, RagiInit.ItemFullBottle
        )
    }

    class ColorMaterial : IBlockColor {

        override fun colorMultiplier(state: IBlockState, worldIn: IBlockAccess?, pos: BlockPos?, tintIndex: Int): Int {
            val material = MaterialManager.getMaterial(state.block.getMetaFromState(state))
            return if (tintIndex == 0) material.getColor().rgb else 0xFFFFFF
        }
    }
}