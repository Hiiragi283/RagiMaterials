package hiiragi283.ragi_materials.proxy

import hiiragi283.ragi_materials.RagiMaterialsCore
import hiiragi283.ragi_materials.RagiRegistry
import hiiragi283.ragi_materials.Reference
import hiiragi283.ragi_materials.client.gui.*
import hiiragi283.ragi_materials.capability.heat.CapabilityHeat
import hiiragi283.ragi_materials.config.JsonConfig
import hiiragi283.ragi_materials.config.RagiConfig
import hiiragi283.ragi_materials.container.*
import hiiragi283.ragi_materials.crafting.CraftingRegistry
import hiiragi283.ragi_materials.crafting.SmeltingRegistry
import hiiragi283.ragi_materials.event.CommonRegistryEvent
import hiiragi283.ragi_materials.integration.IntegrationCore
import hiiragi283.ragi_materials.material.MaterialUtil
import hiiragi283.ragi_materials.packet.RagiNetworkManager
import hiiragi283.ragi_materials.recipe.FFRecipe
import hiiragi283.ragi_materials.recipe.LaboRecipe
import hiiragi283.ragi_materials.recipe.MillRecipe
import hiiragi283.ragi_materials.tile.*
import hiiragi283.ragi_materials.util.RagiLogger
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
import net.minecraftforge.fml.relauncher.Side
import java.io.File

abstract class CommonProxy : IGuiHandler {

    open fun onConstruct(event: FMLConstructionEvent) {
        MinecraftForge.EVENT_BUS.register(CommonRegistryEvent)
        FluidRegistry.enableUniversalBucket()
    }

    //Pre-Initializationで読み込むメソッド
    open fun onPreInit(event: FMLPreInitializationEvent) {
        /**
        Thanks to defeatedcrow!
        Source: https://github.com/defeatedcrow/JsonSampleMod/blob/main/src/main/java/com/defeatedcrow/jsonsample/JsonSampleCore.java
         */
        //configフォルダーの取得
        RagiMaterialsCore.config = File(event.modConfigurationDirectory, "${Reference.MOD_ID}/")
        RagiLogger.infoDebug(("Config path: ${RagiMaterialsCore.config?.absolutePath}"))
        //鉱石生成の登録
        //MinecraftForge.ORE_GEN_BUS.register(OreGenRegistry())
        //Capabilityの登録
        CapabilityHeat.register()
        //GUI描画の登録
        NetworkRegistry.INSTANCE.registerGuiHandler(RagiMaterialsCore.INSTANCE, this)
        //連携要素の登録
        IntegrationCore.loadPreInit()
    }

    //Initializationで読み込むメソッド
    open fun onInit(event: FMLInitializationEvent) {
        CommonRegistryEvent.register()
        CraftingRegistry.load()
        SmeltingRegistry.load()
        //LootTableの登録
        LootTableList.register(RagiRegistry.OreRainbow)
        //Packetの登録
        RagiNetworkManager.load()
        //連携要素の登録
        IntegrationCore.loadInit()
    }

    //Post-Initializationで読み込むメソッド
    open fun onPostInit(event: FMLPostInitializationEvent) {
        //Jsonの読み取り
        loadJson()
        //設備レシピの登録
        registerRecipes()
        //連携要素の登録
        IntegrationCore.loadPostInit()
    }

    private fun loadJson() {
        //Json configの読み取り
        JsonConfig.loadJson()
        //Json configの生成
        JsonConfig.generateJson()
    }

    private fun registerRecipes() {
        //レシピの登録
        FFRecipe.Registry.load()
        LaboRecipe.Registry.load()
        MillRecipe.Registry.load()
        //デバッグ用
        if (RagiConfig.debugMode.isDebug) {
            MaterialUtil.printMap()
            FFRecipe.Registry.printMap()
            LaboRecipe.Registry.printMap()
            MillRecipe.Registry.printMap()
        }
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