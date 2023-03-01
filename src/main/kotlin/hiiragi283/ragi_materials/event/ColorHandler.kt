package hiiragi283.ragi_materials.event

import hiiragi283.ragi_materials.block.BlockCropCoal
import hiiragi283.ragi_materials.block.BlockCropLignite
import hiiragi283.ragi_materials.init.RagiInit
import hiiragi283.ragi_materials.item.ItemSeedCoal
import hiiragi283.ragi_materials.item.ItemSeedLignite
import hiiragi283.ragi_materials.item.ItemSeedPeat
import hiiragi283.ragi_materials.material.MaterialManager
import hiiragi283.ragi_materials.material.MaterialRegistry
import net.minecraft.client.renderer.color.IBlockColor
import net.minecraft.client.renderer.color.IItemColor
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
        blockColors.registerBlockColorHandler(
            IBlockColor { state, world, pos, tintIndex ->
                when (state.block) {
                    is BlockCropCoal -> MaterialRegistry.COAL.color!!.rgb
                    is BlockCropLignite -> MaterialRegistry.LIGNITE.color!!.rgb
                    else -> MaterialRegistry.PEAT.color!!.rgb
                }
            }, RagiInit.BlockCropCoal, RagiInit.BlockCropLignite, RagiInit.BlockCropPeat
        )

        itemColors.registerItemColorHandler(
            IItemColor { stack, tintIndex ->
                //メタデータからmaterialを取得
                val material = MaterialManager.getMaterial(stack.metadata)
                //tintIndexが0ならばmaterial.color，そうでないなら白を返す
                if (tintIndex == 0) {
                    if (material !== null && material.color !== null) material.color!!.rgb else 0xFFFFFF
                } else 0xFFFFFF
            },
            RagiInit.ItemBlockCrystal,
            RagiInit.ItemBlockMetal,
            RagiInit.ItemDust,
            RagiInit.ItemDustTiny,
            RagiInit.ItemCrystal,
            RagiInit.ItemGear,
            RagiInit.ItemIngot,
            RagiInit.ItemIngotHot,
            RagiInit.ItemNugget,
            RagiInit.ItemOre,
            RagiInit.ItemOreNether,
            RagiInit.ItemOreEnd,
            RagiInit.ItemPlate,
            RagiInit.ItemStick
        )

        itemColors.registerItemColorHandler(
            IItemColor { stack, tintIndex ->
                //NBTタグがnullでない場合
                if (stack.tagCompound !== null) {
                    //NBTタグからmaterialを取得
                    val tag = stack.tagCompound!!
                    val material = MaterialManager.getMaterial(tag.getInteger("material"))
                    //tintIndexが1ならばmaterial.color，そうでないなら白を返す
                    if (tintIndex == 1) {
                        if (material !== null && material.color !== null) material.color!!.rgb else 0xFFFFFF
                    } else 0xFFFFFF
                }
                //NBTタグがnullの場合
                else 0xFFFFFF //白色を返す
            }, RagiInit.ItemForgeHammer
        )

        itemColors.registerItemColorHandler(
            IItemColor { stack, tintIndex ->
                val fluidItem = stack.getCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY, null)
                if ((fluidItem !== null) && (fluidItem.tankProperties[0].contents?.fluid?.name !== null) && tintIndex == 0) {
                    val name = fluidItem.tankProperties[0].contents!!.fluid!!.name
                    val material = MaterialManager.getMaterial(name)
                    if (material !== null && material.color !== null) material.color!!.rgb else 0xFFFFFF
                } else 0xFFFFFF
            }, RagiInit.ItemFullBottle
        )

        itemColors.registerItemColorHandler(
            IItemColor { stack, tintIndex ->
                when (stack.item) {
                    is ItemSeedCoal -> MaterialRegistry.COAL.color!!.rgb
                    is ItemSeedLignite -> MaterialRegistry.LIGNITE.color!!.rgb
                    is ItemSeedPeat -> MaterialRegistry.PEAT.color!!.rgb
                    else -> 0xFFFFFF
                }
            }, RagiInit.ItemSeedCoal, RagiInit.ItemSeedLignite, RagiInit.ItemSeedPeat
        )
    }
}