package hiiragi283.ragi_materials.proxy

import hiiragi283.ragi_materials.RagiInit
import hiiragi283.ragi_materials.RagiMaterials
import hiiragi283.ragi_materials.api.capability.heat.CapabilityHeat
import hiiragi283.ragi_materials.client.gui.*
import hiiragi283.ragi_materials.container.*
import hiiragi283.ragi_materials.event.CommonRegistryEvent
import hiiragi283.ragi_materials.event.CreateRegistryEvent
import hiiragi283.ragi_materials.event.RecipeRegistryEvent
import hiiragi283.ragi_materials.integration.IntegrationCore
import hiiragi283.ragi_materials.network.RagiNetworkManager
import hiiragi283.ragi_materials.tile.*
import net.minecraft.client.gui.inventory.GuiContainer
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.inventory.Container
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraft.world.storage.loot.LootTableList
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fluids.FluidRegistry
import net.minecraftforge.fml.common.event.FMLConstructionEvent
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent
import net.minecraftforge.fml.common.network.IGuiHandler
import net.minecraftforge.fml.common.network.NetworkRegistry
import java.io.File

abstract class CommonProxy : IGuiHandler, IProxy {

    override fun onConstruct(event: FMLConstructionEvent) {
        //イベントの登録
        MinecraftForge.EVENT_BUS.register(CommonRegistryEvent)
        MinecraftForge.EVENT_BUS.register(CreateRegistryEvent)
        MinecraftForge.EVENT_BUS.register(RecipeRegistryEvent)
        //ForgeのUniversal Bucketを使えるようにする
        FluidRegistry.enableUniversalBucket()
        //連携要素の登録
        IntegrationCore.onConstruct(event)
        //素材の登録
        RagiInit.onConstruct(event)
    }

    //Pre-Initializationで読み込むメソッド
    override fun onPreInit(event: FMLPreInitializationEvent) {
        //lateinit変数の初期化
        /**
         * Thanks to defeatedcrow!
         * Source: https://github.com/defeatedcrow/JsonSampleMod/blob/main/src/main/java/com/defeatedcrow/jsonsample/JsonSampleCore.java
         */
        RagiMaterials.CONFIG = File(event.modConfigurationDirectory, "${RagiMaterials.MOD_ID}/")
        RagiMaterials.LOGGER.debug(("Config path: ${RagiMaterials.CONFIG.absolutePath}"))
        //Capabilityの登録
        CapabilityHeat.register()
        //GUI描画の登録
        NetworkRegistry.INSTANCE.registerGuiHandler(RagiMaterials.INSTANCE, this)
        //連携要素の登録
        IntegrationCore.onPreInit(event)
        //BlockやItemの一覧の登録
        RagiInit.onPreInit(event)
    }

    //Initializationで読み込むメソッド
    override fun onInit(event: FMLInitializationEvent) {
        //LootTableの登録
        LootTableList.register(RagiInit.OreRainbow)
        //Packetの登録
        RagiNetworkManager.load()
        //連携要素の登録
        IntegrationCore.onInit(event)
        //液体や鉱石辞書，バニラレシピの登録
        RagiInit.onInit(event)
    }

    //Post-Initializationで読み込むメソッド
    override fun onPostInit(event: FMLPostInitializationEvent) {
        //Jsonの読み取り
        //loadJson()
        //連携要素の登録
        IntegrationCore.onPostInit(event)
        //設備レシピの登録
        RagiInit.onPostInit(event)
    }

    companion object {
        const val TileID = 0
    }

    //    IGuiHandler    //

    //サーバー側にはContainerを返す
    override fun getServerGuiElement(ID: Int, player: EntityPlayer, world: World, x: Int, y: Int, z: Int): Any? {
        var container: Container? = null
        if (ID == TileID) {
            val tile = world.getTileEntity(BlockPos(x, y, z))
            if (tile !== null) {
                when (tile) {
                    is TileFireboxPrimitive -> container = ContainerFirebox(player, tile)
                    is TileFullBottleStation -> container = ContainerFullBottle(player, tile)
                    is TileIndustrialLabo -> container = ContainerLaboTable(player, tile)
                    is TileLaboTable -> container = ContainerLaboTable(player, tile)
                    is TileOreDictConv -> container = ContainerOreDictConv(player, tile)
                    is TileStoneMill -> container = ContainerStoneMill(player, tile)
                    else -> {}
                }
            }
        }
        return container
    }

    //クライアント側にはGUIを返す
    override fun getClientGuiElement(ID: Int, player: EntityPlayer, world: World, x: Int, y: Int, z: Int): Any? {
        var gui: GuiContainer? = null
        if (ID == TileID) {
            val tile = world.getTileEntity(BlockPos(x, y, z))
            if (tile !== null) {
                when (tile) {
                    is TileFireboxPrimitive -> gui = GuiFirebox(player, tile)
                    is TileFullBottleStation -> gui = GuiFullBottle(player, tile)
                    is TileIndustrialLabo -> gui = GuiLaboTable(player, tile)
                    is TileLaboTable -> gui = GuiLaboTable(player, tile)
                    is TileOreDictConv -> gui = GuiOreDictConv(player, tile)
                    is TileStoneMill -> gui = GuiStoneMill(player, tile)
                    else -> {}
                }
            }
        }
        return gui
    }
}