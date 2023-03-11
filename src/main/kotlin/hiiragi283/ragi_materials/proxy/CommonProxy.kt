package hiiragi283.ragi_materials.proxy

import hiiragi283.ragi_materials.Reference
import hiiragi283.ragi_materials.tile.TileBlazingMelter
import hiiragi283.ragi_materials.tile.TileForgeFurnace
import hiiragi283.ragi_materials.tile.TileFullBottleStation
import hiiragi283.ragi_materials.tile.TileLaboTable
import net.minecraft.util.ResourceLocation
import net.minecraftforge.fml.common.registry.GameRegistry

open class CommonProxy {

    //Pre-Initializationで読み込むメソッド
    open fun loadPreInit() {}

    //Initializationで読み込むメソッド
    open fun loadInit() {
        registerTile()
    }

    //Post-Initializationで読み込むメソッド
    open fun loadPostInit() {}

    //TileEntityを登録するメソッド
    open fun registerTile() {
        GameRegistry.registerTileEntity(TileForgeFurnace::class.java, ResourceLocation(Reference.MOD_ID, "te_forge_furnace"))
        GameRegistry.registerTileEntity(TileFullBottleStation::class.java, ResourceLocation(Reference.MOD_ID, "te_fullbottle_station"))
        GameRegistry.registerTileEntity(TileLaboTable::class.java, ResourceLocation(Reference.MOD_ID, "te_laboratory_table"))
        GameRegistry.registerTileEntity(TileBlazingMelter::class.java, ResourceLocation(Reference.MOD_ID, "te_blazing_melter"))
    }
}