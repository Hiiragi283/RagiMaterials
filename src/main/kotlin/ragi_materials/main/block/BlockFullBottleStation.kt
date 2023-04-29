package ragi_materials.main.block

import net.minecraft.block.SoundType
import net.minecraft.block.material.Material
import net.minecraft.block.state.IBlockState
import net.minecraft.util.BlockRenderLayer
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import ragi_materials.core.block.BlockContainerBase
import ragi_materials.main.tile.TileFullBottleStation
import java.util.*

class BlockFullBottleStation : BlockContainerBase<TileFullBottleStation>("fullbottle_station", Material.IRON, TileFullBottleStation::class.java, 2) {

    init {
        blockHardness = 5.0F
        blockResistance = 5.0F
        setHarvestLevel("pickaxe", 2)
        soundType = SoundType.METAL
    }

    //    General    //

    @Deprecated("Deprecated in Java", ReplaceWith("false"))
    override fun isOpaqueCube(state: IBlockState): Boolean = false

    override fun quantityDropped(random: Random) = 0

    //    Client    //

    @SideOnly(Side.CLIENT)
    override fun getRenderLayer(): BlockRenderLayer = BlockRenderLayer.CUTOUT

}