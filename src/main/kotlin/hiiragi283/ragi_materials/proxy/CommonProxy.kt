package hiiragi283.ragi_materials.proxy

import hiiragi283.ragi_materials.Reference
import hiiragi283.ragi_materials.base.BlockContainerBase
import hiiragi283.ragi_materials.init.RagiBlock
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
        registerTile(TileLaboTable::class.java, RagiBlock.BlockLaboratoryTable) //100
        registerTile(TileFullBottleStation::class.java, RagiBlock.BlockFullBottleStation) //101
        registerTile(TileForgeFurnace::class.java, RagiBlock.BlockForgeFurnace) //102
        registerTile(TileBlazingForge::class.java, RagiBlock.BlockBlazingForge) //103
        registerTile(TileIndustrialLabo::class.java, RagiBlock.BlockIndustrialLabo) //104
        registerTile(TileStoneMill::class.java, RagiBlock.BlockStoneMill) //105
    }

    private fun <T : TileEntity> registerTile(tile: Class<T>, block: BlockContainerBase) {
        GameRegistry.registerTileEntity(tile, ResourceLocation(Reference.MOD_ID, "te_${block.registryName!!.resourcePath}"))
    }
}