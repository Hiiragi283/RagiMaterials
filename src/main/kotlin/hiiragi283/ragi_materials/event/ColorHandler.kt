package hiiragi283.ragi_materials.event

import hiiragi283.ragi_materials.init.RagiInit
import hiiragi283.ragi_materials.material.IMaterialBlock
import hiiragi283.ragi_materials.material.IMaterialItem
import net.minecraft.client.renderer.color.IBlockColor
import net.minecraft.client.renderer.color.IItemColor
import net.minecraft.world.World
import net.minecraftforge.client.event.ColorHandlerEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent

class ColorHandler {

    @SubscribeEvent
    fun colorHandler(event: ColorHandlerEvent.Item) {
        //変数の宣言
        val blockColors = event.blockColors
        val itemColors = event.itemColors

        //BlockとItemの着色
        blockColors.registerBlockColorHandler(IBlockColor { state, world, pos, tintIndex ->
            val block = state.block
            if (block is IMaterialBlock) block.getMaterialBlock(world as World, pos!!, state).color.rgb else 0xFFFFFF
        },
                RagiInit.BlockCropCoal,
                RagiInit.BlockCropLignite,
                RagiInit.BlockCropPeat
        )

        itemColors.registerItemColorHandler(IItemColor { stack, tintIndex ->
            val item = stack.item
            if (item is IMaterialItem && tintIndex == 0) item.getMaterial(stack).color.rgb else 0xFFFFFF
        },
                RagiInit.ItemBlockCrystal,
                RagiInit.ItemBlockMetal,
                RagiInit.ItemCrystal,
                RagiInit.ItemDust,
                RagiInit.ItemDustTiny,
                RagiInit.ItemForgeHammer,
                RagiInit.ItemFullBottle,
                RagiInit.ItemGear,
                RagiInit.ItemIngot,
                RagiInit.ItemIngotHot,
                RagiInit.ItemNugget,
                RagiInit.ItemOre,
                RagiInit.ItemOreEnd,
                RagiInit.ItemOreNether,
                RagiInit.ItemPlate,
                RagiInit.ItemSeedCoal,
                RagiInit.ItemSeedLignite,
                RagiInit.ItemSeedPeat,
                RagiInit.ItemStick,
                RagiInit.ItemWaste
        )

    }
}