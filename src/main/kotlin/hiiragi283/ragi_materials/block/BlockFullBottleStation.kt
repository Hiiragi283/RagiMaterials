package hiiragi283.ragi_materials.block

import hiiragi283.ragi_materials.init.RagiInit
import hiiragi283.ragi_materials.base.RagiBlockContainer
import hiiragi283.ragi_materials.tile.TileFullBottleStation
import net.minecraft.block.SoundType
import net.minecraft.block.material.Material
import net.minecraft.block.state.IBlockState
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.*
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import java.util.*

class BlockFullBottleStation : RagiBlockContainer("fullbottle_station", Material.IRON, 2) {

    init {
        blockHardness = 5.0F
        blockResistance = 5.0F
        setCreativeTab(RagiInit.TabBlocks)
        setHarvestLevel("pickaxe", 2)
        soundType = SoundType.METAL
    }

    //    General    //

    @Deprecated("Deprecated in Java", ReplaceWith("false"))
    override fun isFullCube(state: IBlockState): Boolean = false

    @Deprecated("Deprecated in Java", ReplaceWith("false"))
    override fun isOpaqueCube(state: IBlockState): Boolean = false

    //    Client    //

    @SideOnly(Side.CLIENT)
    override fun getBlockLayer(): BlockRenderLayer = BlockRenderLayer.CUTOUT

    //    Tile Entity    //

    override fun createNewTileEntity(worldIn: World, meta: Int): TileEntity = TileFullBottleStation()

}