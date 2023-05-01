package ragi_materials.core.proxy

import net.minecraft.client.gui.inventory.GuiContainer
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.inventory.Container
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.common.capabilities.CapabilityManager
import net.minecraftforge.fluids.FluidRegistry
import net.minecraftforge.fml.common.event.FMLConstructionEvent
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent
import net.minecraftforge.fml.common.network.IGuiHandler
import net.minecraftforge.fml.common.network.NetworkRegistry
import ragi_materials.core.RagiInit
import ragi_materials.core.RagiMaterials
import ragi_materials.core.RagiRegistry
import ragi_materials.core.capability.RagiStorage
import ragi_materials.core.capability.heat.HeatStorage
import ragi_materials.core.capability.heat.IHeatStorage
import ragi_materials.core.event.CapabilityEvent
import ragi_materials.core.event.CommonRegistryEvent
import ragi_materials.core.event.CreateRegistryEvent
import ragi_materials.core.event.RecipeRegistryEvent
import ragi_materials.core.integration.IntegrationCore
import ragi_materials.core.network.RagiNetworkManager
import ragi_materials.main.client.gui.GuiFullBottle
import ragi_materials.main.client.gui.GuiLaboTable
import ragi_materials.main.client.gui.GuiOreDictConv
import ragi_materials.main.client.gui.GuiStoneMill
import ragi_materials.main.container.ContainerFullBottle
import ragi_materials.main.container.ContainerLaboTable
import ragi_materials.main.container.ContainerOreDictConv
import ragi_materials.main.container.ContainerStoneMill
import ragi_materials.main.tile.*
import ragi_materials.metallurgy.client.gui.GuiBlastFurnace
import ragi_materials.metallurgy.container.ContainerBlastFurnace
import ragi_materials.metallurgy.tile.TileBlastFurnaceInterface
import java.io.File

abstract class CommonProxy : IGuiHandler, IProxy {

    override fun onConstruct(event: FMLConstructionEvent) {
        //イベントの登録
        MinecraftForge.EVENT_BUS.register(CapabilityEvent)
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
        CapabilityManager.INSTANCE.register(IHeatStorage::class.java, RagiStorage<IHeatStorage>()) { HeatStorage(1000) }
        //GUI描画の登録
        NetworkRegistry.INSTANCE.registerGuiHandler(RagiMaterials.INSTANCE, this)
        //連携要素の登録
        IntegrationCore.onPreInit(event)
        //BlockやItemの一覧の登録
        RagiInit.onPreInit(event)
        //onPreInitの最後にRagiRegistry内のフィールドを一覧に代入していく
        RagiRegistry.addCollection()
    }

    //Initializationで読み込むメソッド
    override fun onInit(event: FMLInitializationEvent) {
        //LootTableの登録
        //LootTableList.register(RagiRegistry.OreRainbow)
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
                    is TileBlastFurnaceInterface -> container = ContainerBlastFurnace(player, tile)
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
                    is TileBlastFurnaceInterface -> gui = GuiBlastFurnace(player, tile)
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