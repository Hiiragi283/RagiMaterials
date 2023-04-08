package hiiragi283.ragi_materials.proxy

import hiiragi283.ragi_materials.RagiMaterialsCore
import hiiragi283.ragi_materials.RagiRegistry
import hiiragi283.ragi_materials.Reference
import hiiragi283.ragi_materials.base.BlockContainerBase
import hiiragi283.ragi_materials.base.FluidBase
import hiiragi283.ragi_materials.client.gui.GuiLaboTable
import hiiragi283.ragi_materials.client.gui.GuiOreDictConv
import hiiragi283.ragi_materials.client.gui.GuiStoneMill
import hiiragi283.ragi_materials.config.JsonConfig
import hiiragi283.ragi_materials.config.RagiConfig
import hiiragi283.ragi_materials.crafting.CraftingRegistry
import hiiragi283.ragi_materials.crafting.SmeltingRegistry
import hiiragi283.ragi_materials.integration.IntegrationCore
import hiiragi283.ragi_materials.inventory.container.ContainerLaboTable
import hiiragi283.ragi_materials.inventory.container.ContainerOreDictConv
import hiiragi283.ragi_materials.inventory.container.ContainerStoneMill
import hiiragi283.ragi_materials.material.MaterialRegistry
import hiiragi283.ragi_materials.material.MaterialUtil
import hiiragi283.ragi_materials.material.OreProperty
import hiiragi283.ragi_materials.material.RagiMaterial
import hiiragi283.ragi_materials.material.part.PartRegistry
import hiiragi283.ragi_materials.material.type.EnumMaterialType
import hiiragi283.ragi_materials.material.type.TypeRegistry
import hiiragi283.ragi_materials.packet.MessageHandlerLabo
import hiiragi283.ragi_materials.packet.MessageTile
import hiiragi283.ragi_materials.recipe.FFRecipe
import hiiragi283.ragi_materials.recipe.LaboRecipe
import hiiragi283.ragi_materials.recipe.MillRecipe
import hiiragi283.ragi_materials.tile.*
import hiiragi283.ragi_materials.util.RagiLogger
import hiiragi283.ragi_materials.util.RagiUtil
import net.minecraft.client.gui.inventory.GuiContainer
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.inventory.Container
import net.minecraft.item.ItemStack
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.ResourceLocation
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
import net.minecraftforge.fml.common.registry.GameRegistry
import net.minecraftforge.fml.relauncher.Side
import java.io.File

abstract class CommonProxy : IGuiHandler {

    open fun onConstruct(event: FMLConstructionEvent) {
        MinecraftForge.EVENT_BUS.register(RagiRegistry)
        FluidRegistry.enableUniversalBucket()
    }

    //Pre-Initializationで読み込むメソッド
    open fun loadPreInit(event: FMLPreInitializationEvent) {
        /*
          Thanks to defeatedcrow!
          Source: https://github.com/defeatedcrow/JsonSampleMod/blob/main/src/main/java/com/defeatedcrow/jsonsample/JsonSampleCore.java
        */
        //configフォルダーの取得
        RagiMaterialsCore.config = File(event.modConfigurationDirectory, "${Reference.MOD_ID}/")
        RagiLogger.infoDebug(("Config path: ${RagiMaterialsCore.config?.absolutePath}"))
        //鉱石生成の登録
        //MinecraftForge.ORE_GEN_BUS.register(OreGenRegistry())
        //GUI描画の登録
        NetworkRegistry.INSTANCE.registerGuiHandler(RagiMaterialsCore.INSTANCE, this)
        //連携要素の登録
        IntegrationCore.loadPreInit()
    }

    //Initializationで読み込むメソッド
    open fun loadInit(event: FMLInitializationEvent) {
        //液体の登録
        registerFluid()
        //TileEntityの登録
        registerTiles()
        //鉱石辞書の登録
        registerOreDict()
        //レシピの登録
        CraftingRegistry.load()
        SmeltingRegistry.load()
        //LootTableの登録
        LootTableList.register(RagiRegistry.OreRainbow)
        //パケットの登録
        RagiRegistry.RagiNetworkWrapper.registerMessage(MessageHandlerLabo::class.java, MessageTile::class.java, 0, Side.CLIENT)
        //連携要素の登録
        IntegrationCore.loadInit()
    }

    //Post-Initializationで読み込むメソッド
    open fun loadPostInit(event: FMLPostInitializationEvent) {
        //Jsonの読み取り
        loadJson()
        //設備レシピの登録
        registerRecipes()
        //連携要素の登録
        IntegrationCore.loadPostInit()
    }

    private fun registerFluid() {
        //Fluidの登録
        for (material in RagiMaterial.list) {
            //typeがINTERNALでない，かつmaterialのtypeがfluidの場合
            if (material.type != TypeRegistry.INTERNAL && EnumMaterialType.LIQUID in material.type.list) {
                //Fluidの登録
                val fluid = FluidBase(material.name)
                fluid.setColor(material.color)
                //MaterialTypesがGASの場合
                if (material.type.match(TypeRegistry.GAS)) {
                    fluid.isGaseous = true
                    fluid.density = fluid.density * -1
                }
                FluidRegistry.registerFluid(fluid)
                //fluid入りバケツを登録
                FluidRegistry.addBucketForFluid(fluid)
            }
        }
    }

    private fun registerTiles() {
        registerTile(TileLaboTable::class.java, RagiRegistry.BlockLaboratoryTable) //100
        registerTile(TileFullBottleStation::class.java, RagiRegistry.BlockFullBottleStation) //101
        registerTile(TileForgeFurnace::class.java, RagiRegistry.BlockForgeFurnace) //102
        registerTile(TileBlazingForge::class.java, RagiRegistry.BlockBlazingForge) //103
        registerTile(TileIndustrialLabo::class.java, RagiRegistry.BlockIndustrialLabo) //104
        registerTile(TileStoneMill::class.java, RagiRegistry.BlockStoneMill) //105
        registerTile(TileQuartzAntenna::class.java, RagiRegistry.BlockQuartzAntenna) //106
        registerTile(TileOreDictConv::class.java, RagiRegistry.BlockOreDictConvNew) //107
    }

    private fun <T : TileEntity> registerTile(tile: Class<T>, block: BlockContainerBase) {
        GameRegistry.registerTileEntity(tile, ResourceLocation(Reference.MOD_ID, "te_${block.registryName!!.path}"))
    }

    private fun registerOreDict() {

        for (pair in RagiMaterial.validPair) {
            val part = pair.first
            val material = pair.second
            val stack = MaterialUtil.getPart(part, material)
            RagiUtil.setOreDict(part.prefixOre + material.getOreDict(), stack)
            material.oredictAlt?.let { RagiUtil.setOreDict(part.prefixOre + it, stack) }
        }

        //Ore
        for (i in OreProperty.listOre1.indices) {
            RagiUtil.setOreDict("ore${OreProperty.listOre1[i].first}", ItemStack(RagiRegistry.BlockOre1, 1, i))
            RagiUtil.setOreDict("crushed${OreProperty.listOre1[i].first}", ItemStack(RagiRegistry.ItemOreCrushed, 1, i))
        }
        RagiUtil.setOreDict("oreSaltpeter", ItemStack(RagiRegistry.BlockOre1, 1, 6))
        RagiUtil.setOreDict("oreSaltpeterCrushed", ItemStack(RagiRegistry.ItemOreCrushed, 1, 6))

        for (i in OreProperty.listVanilla.indices) {
            RagiUtil.setOreDict("crushed${OreProperty.listVanilla[i].first}", ItemStack(RagiRegistry.ItemOreCrushedVanilla, 1, i))
        }

        //Others
        RagiUtil.setOreDict("charcoal", MaterialUtil.getPart(PartRegistry.CRYSTAL, MaterialRegistry.CHARCOAL))
        RagiUtil.setOreDict("dustGunpowder", RagiUtil.getStack("minecraft:gunpowder", 1, 0))
        RagiUtil.setOreDict("dustSugar", RagiUtil.getStack("minecraft:sugar", 1, 0))
        RagiUtil.setOreDict("fuelCoke", MaterialUtil.getPart(PartRegistry.CRYSTAL, MaterialRegistry.COKE))
        RagiUtil.setOreDict("gearStone", MaterialUtil.getPart(PartRegistry.GEAR, MaterialRegistry.STONE))
        RagiUtil.setOreDict("gearWood", MaterialUtil.getPart(PartRegistry.GEAR, MaterialRegistry.WOOD))
        RagiUtil.setOreDict("gemCharcoal", RagiUtil.getStack("minecraft:coal", 1, 1))
        RagiUtil.setOreDict("stickStone", MaterialUtil.getPart(PartRegistry.STICK, MaterialRegistry.STONE))
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