package hiiragi283.ragi_materials.proxy

import hiiragi283.ragi_materials.RagiRegistry
import hiiragi283.ragi_materials.Reference
import hiiragi283.ragi_materials.base.BlockContainerBase
import hiiragi283.ragi_materials.tile.*
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.ResourceLocation
import net.minecraftforge.fml.common.registry.GameRegistry

open class CommonProxy {

    //Pre-Initializationで読み込むメソッド
    open fun loadPreInit() {}

    //Initializationで読み込むメソッド
    open fun loadInit() {
        registerTiles()
    }

    //Post-Initializationで読み込むメソッド
    open fun loadPostInit() {}

    //TileEntityを登録するメソッド
    open fun registerTiles() {
        registerTile(TileLaboTable::class.java, RagiRegistry.BLOCK.BlockLaboratoryTable) //100
        registerTile(TileFullBottleStation::class.java, RagiRegistry.BLOCK.BlockFullBottleStation) //101
        registerTile(TileForgeFurnace::class.java, RagiRegistry.BLOCK.BlockForgeFurnace) //102
        registerTile(TileBlazingForge::class.java, RagiRegistry.BLOCK.BlockBlazingForge) //103
        registerTile(TileIndustrialLabo::class.java, RagiRegistry.BLOCK.BlockIndustrialLabo) //104
        registerTile(TileStoneMill::class.java, RagiRegistry.BLOCK.BlockStoneMill) //105
    }

    private fun <T : TileEntity> registerTile(tile: Class<T>, block: BlockContainerBase) {
        GameRegistry.registerTileEntity(tile, ResourceLocation(Reference.MOD_ID, "te_${block.registryName!!.path}"))
    }
}