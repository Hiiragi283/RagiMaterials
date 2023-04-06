package hiiragi283.ragi_materials.proxy

import hiiragi283.ragi_materials.RagiMaterialsCore
import hiiragi283.ragi_materials.RagiRegistry
import hiiragi283.ragi_materials.Reference
import hiiragi283.ragi_materials.base.BlockContainerBase
import hiiragi283.ragi_materials.base.FluidBase
import hiiragi283.ragi_materials.config.JsonConfig
import hiiragi283.ragi_materials.config.RagiConfig
import hiiragi283.ragi_materials.crafting.CraftingRegistry
import hiiragi283.ragi_materials.crafting.SmeltingRegistry
import hiiragi283.ragi_materials.init.LootTableRegistry
import hiiragi283.ragi_materials.init.OreDictRegistry
import hiiragi283.ragi_materials.init.RagiGuiHandler
import hiiragi283.ragi_materials.integration.IntegrationCore
import hiiragi283.ragi_materials.material.MaterialUtil
import hiiragi283.ragi_materials.material.RagiMaterial
import hiiragi283.ragi_materials.material.type.EnumMaterialType
import hiiragi283.ragi_materials.material.type.TypeRegistry
import hiiragi283.ragi_materials.packet.PacketManager
import hiiragi283.ragi_materials.recipe.FFRecipe
import hiiragi283.ragi_materials.recipe.LaboRecipe
import hiiragi283.ragi_materials.recipe.MillRecipe
import hiiragi283.ragi_materials.tile.*
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.ResourceLocation
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fluids.FluidRegistry
import net.minecraftforge.fml.common.network.NetworkRegistry
import net.minecraftforge.fml.common.registry.GameRegistry

abstract class CommonProxy {

    open fun onConstruct() {
        MinecraftForge.EVENT_BUS.register(RagiRegistry)
        FluidRegistry.enableUniversalBucket()
    }

    //Pre-Initializationで読み込むメソッド
    open fun loadPreInit() {
        //鉱石生成の登録
        //MinecraftForge.ORE_GEN_BUS.register(OreGenRegistry())
        //GUI描画の登録
        NetworkRegistry.INSTANCE.registerGuiHandler(RagiMaterialsCore.INSTANCE, RagiGuiHandler)
        //連携要素の登録
        IntegrationCore.loadPreInit()
    }

    //Initializationで読み込むメソッド
    open fun loadInit() {
        //TileEntityの登録
        registerTiles()
        //液体の登録
        registerFluid()
        //鉱石辞書の登録
        OreDictRegistry.load()
        //レシピの登録
        CraftingRegistry.load()
        SmeltingRegistry.load()
        //LootTableの登録
        LootTableRegistry.load()
        //パケットの登録
        PacketManager.load()
        //連携要素の登録
        IntegrationCore.loadInit()
    }

    //Post-Initializationで読み込むメソッド
    open fun loadPostInit() {
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
        registerTile(TileLaboTable::class.java, RagiRegistry.BLOCK.BlockLaboratoryTable) //100
        registerTile(TileFullBottleStation::class.java, RagiRegistry.BLOCK.BlockFullBottleStation) //101
        registerTile(TileForgeFurnace::class.java, RagiRegistry.BLOCK.BlockForgeFurnace) //102
        registerTile(TileBlazingForge::class.java, RagiRegistry.BLOCK.BlockBlazingForge) //103
        registerTile(TileIndustrialLabo::class.java, RagiRegistry.BLOCK.BlockIndustrialLabo) //104
        registerTile(TileStoneMill::class.java, RagiRegistry.BLOCK.BlockStoneMill) //105
        registerTile(TileQuartzAntenna::class.java, RagiRegistry.BLOCK.BlockQuartzAntenna) //106
        registerTile(TileOreDictConv::class.java, RagiRegistry.BLOCK.BlockOreDictConvNew) //107
    }

    private fun <T : TileEntity> registerTile(tile: Class<T>, block: BlockContainerBase) {
        GameRegistry.registerTileEntity(tile, ResourceLocation(Reference.MOD_ID, "te_${block.registryName!!.path}"))
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

}