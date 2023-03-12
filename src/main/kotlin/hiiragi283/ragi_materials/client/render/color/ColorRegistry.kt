package hiiragi283.ragi_materials.client.render.color

import hiiragi283.ragi_materials.init.RagiInit
import hiiragi283.ragi_materials.material.IMaterialBlock
import hiiragi283.ragi_materials.material.IMaterialItem
import net.minecraft.client.Minecraft
import net.minecraft.client.renderer.color.BlockColors
import net.minecraft.client.renderer.color.IBlockColor
import net.minecraft.client.renderer.color.IItemColor
import net.minecraft.client.renderer.color.ItemColors
import net.minecraft.world.World

object ColorRegistry {

    private val blockColors: BlockColors = Minecraft.getMinecraft().blockColors
    private val itemColors: ItemColors = Minecraft.getMinecraft().itemColors

    init {

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
                RagiInit.ItemBlockMaterial,
                RagiInit.ItemCrystal,
                RagiInit.ItemDust,
                RagiInit.ItemDustTiny,
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

        itemColors.registerItemColorHandler(IItemColor { stack, tintIndex ->
            val item = stack.item
            if (item is IMaterialItem && tintIndex == 1) item.getMaterial(stack).color.rgb else 0xFFFFFF
        },
                *RagiInit.ItemsAxe,
                *RagiInit.ItemsHammer,
                *RagiInit.ItemsPickaxe,
                *RagiInit.ItemsSpade,
                *RagiInit.ItemsSword
        )

    }
}