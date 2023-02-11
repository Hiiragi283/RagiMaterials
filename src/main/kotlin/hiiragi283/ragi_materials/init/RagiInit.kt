package hiiragi283.ragi_materials.init

import hiiragi283.ragi_materials.RagiMaterials
import hiiragi283.ragi_materials.Reference
import hiiragi283.ragi_materials.base.ItemBase
import hiiragi283.ragi_materials.base.ItemBlockBase
import hiiragi283.ragi_materials.block.*
import hiiragi283.ragi_materials.config.RagiConfig
import hiiragi283.ragi_materials.event.ItemTooltip
import hiiragi283.ragi_materials.event.ModelRegistry
import hiiragi283.ragi_materials.event.RightClickBlock
import hiiragi283.ragi_materials.item.*
import hiiragi283.ragi_materials.material.MaterialBuilder
import hiiragi283.ragi_materials.material.MaterialRegistry
import hiiragi283.ragi_materials.material.MaterialType
import hiiragi283.ragi_materials.util.RagiColor
import hiiragi283.ragi_materials.util.RagiModel
import hiiragi283.ragi_materials.util.RagiUtils
import net.minecraft.block.state.IBlockState
import net.minecraft.client.renderer.block.model.ModelResourceLocation
import net.minecraft.client.renderer.block.statemap.StateMapperBase
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.init.Blocks
import net.minecraft.item.Item
import net.minecraftforge.client.model.ModelLoader
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fml.common.registry.ForgeRegistries
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import java.awt.Color

object RagiInit {

    //Creative Tabの定義
    val TabBlocks: CreativeTabs = if (!RagiMaterials.isLoadedGT) RagiCreativeTabs.Blocks() else CreativeTabs.MISC
    val TabMaterials: CreativeTabs = if (!RagiMaterials.isLoadedGT) RagiCreativeTabs.Materials() else CreativeTabs.MISC

    //Blockの定義
    val BlockBlazeHeater = BlockBlazeHeater()
    val BlockForgeFurnace = BlockForgeFurnace()
    val BlockLitForgeFurnace = BlockLitForgeFurnace()
    val BlockOreDictConv = BlockOreDictConv()
    val BlockSaltPond = BlockSaltPond()

    //Itemの定義
    val ItemBlockBlazeHeater = ItemBlockBase(BlockBlazeHeater, 1, 2)
    val ItemBlockForgeFurnace = ItemBlockBase(BlockForgeFurnace, 0, 3)
    val ItemBlockOreDictConv = ItemBlockBase(BlockOreDictConv, 0, 1)
    val ItemBlockSaltPond = ItemBlockBase(BlockSaltPond, 0, 2)

    val ItemBlazingCube: Item = ItemBase(Reference.MOD_ID, "blazing_cube", 0).setCreativeTab(CreativeTabs.MISC)
    val ItemBlockMetal = ItemMaterial("block_metal", MaterialType.METAL)

    val ItemBookDebug: Item = ItemBookDebug().setCreativeTab(CreativeTabs.MISC)
    val ItemDust = ItemMaterial("dust", MaterialType.DUST)
    val ItemForgeHammer: Item = ItemForgeHammer().setCreativeTab(CreativeTabs.TOOLS)
    val ItemIngot = ItemMaterial("ingot", MaterialType.METAL)
    val ItemIngotHot = ItemMaterial("ingot_hot", MaterialType.METAL)
    val ItemNugget = ItemMaterial("nugget", MaterialType.METAL)
    val ItemPlate = ItemMaterial("plate", MaterialType.METAL)
    val ItemToolBellow: Item = ItemBase(Reference.MOD_ID, "bellow", 0)
        .setCreativeTab(CreativeTabs.TOOLS)
        .setMaxDamage(63).setMaxStackSize(1)

    fun loadPreInit() {
        //configから素材を追加
        registerMaterial()
        //Blockの登録
        ForgeRegistries.BLOCKS.registerAll(
            BlockBlazeHeater,
            BlockForgeFurnace,
            BlockLitForgeFurnace,
            BlockOreDictConv,
            BlockSaltPond
        )
        //Itemの登録
        ForgeRegistries.ITEMS.registerAll(
            ItemBlockBlazeHeater,
            ItemBlockForgeFurnace,
            ItemBlockOreDictConv,
            ItemBlockSaltPond,

            ItemBlazingCube,
            ItemBlockMetal,
            ItemBookDebug,
            ItemDust,
            ItemForgeHammer,
            ItemIngot,
            ItemIngotHot,
            ItemNugget,
            ItemPlate,
            ItemToolBellow
        )
        //Eventの登録
        registerEvents()
        //Fluidの登録
        RagiInitFluid.registerFluids()
        RagiModel.setModelFluids()
        //Modelの登録
        registerModels()
    }

    fun loadInit() {
        //BlockとItemの着色
        RagiColor.setColor(
            RagiColor.ColorMaterial(), ItemBlockMetal, ItemDust, ItemIngot, ItemIngotHot, ItemNugget, ItemPlate
        )
        RagiColor.setColor(RagiColor.ColorNBT(), ItemForgeHammer)
        //鉱石辞書の登録
        RagiInitOreDict.registerOreDict()
        //レシピの登録
        RagiInitRecipe.registerRecipes()
    }

    fun loadPostInit() {
        //ディスペンサーの機能の登録
        RagiInitDispenser.registerDispense()
        overrideProperty()
        overrideStack()
    }

    //configから素材を登録するメソッド
    private fun registerMaterial() {
        for (value in RagiConfig.material.listMaterials) {
            //valueをばらしてプロパティを得る
            val listProperty = value.split(":")
            val index = listProperty[0].toInt()
            val name = listProperty[1]
            var type = MaterialType.WILDCARD
            val color = Color(listProperty[3].toIntOrNull(16)!!)
            val formula = listProperty[4]
            val molar = listProperty[5].toFloat()
            val melt = listProperty[6].toInt()
            val boil = listProperty[7].toInt()
            //MaterialTypeの確認
            for (i in MaterialType.list) {
                if (i.name == listProperty[2]) {
                    type = i
                    break
                }
            }
            //indexが1023以上maxMaterials以下，かつtypeがWILDCARDでない場合，素材を登録する
            if (index in 1023..RagiConfig.material.maxMaterials && type != MaterialType.WILDCARD) {
                val material = MaterialBuilder(index, name, type)
                material.setColor(color)
                material.setFormula(formula)
                material.setMolarMass(molar)
                material.setTempMelt(melt)
                material.setTempBoil(boil)
                MaterialRegistry.list.add(material)
            }
        }
    }

    //Eventを登録するメソッド
    private fun registerEvents() {
        MinecraftForge.EVENT_BUS.register(ItemTooltip())
        MinecraftForge.EVENT_BUS.register(ModelRegistry())
        MinecraftForge.EVENT_BUS.register(RightClickBlock())
    }

    //特殊なModelを登録するメソッド
    @SideOnly(Side.CLIENT)
    private fun registerModels() {
        ModelLoader.setCustomStateMapper(BlockSaltPond, object : StateMapperBase() {
            override fun getModelResourceLocation(state: IBlockState): ModelResourceLocation {
                return ModelResourceLocation((state.block.registryName!!), "multipart")
            }
        })
    }

    //Vanillaのブロックのプロパティを上書きするメソッド
    private fun overrideProperty() {
        //光源レベルの上書き
        //イワライナー氏から着想を得ました
        Blocks.BROWN_MUSHROOM.setLightLevel(0.0F)
        Blocks.LIT_REDSTONE_ORE.setLightLevel(0.0F)
        Blocks.POWERED_COMPARATOR.setLightLevel(0.0F)
        Blocks.POWERED_REPEATER.setLightLevel(0.0F)
        Blocks.REDSTONE_TORCH.setLightLevel(0.0F)
        Blocks.REDSTONE_WIRE.setLightLevel(0.0F)
        //光の透過率を上書きする
        Blocks.FLOWING_LAVA.setLightOpacity(0)
        Blocks.FLOWING_WATER.setLightOpacity(0)
        Blocks.ICE.setLightOpacity(0)
        Blocks.LAVA.setLightOpacity(0)
        Blocks.WATER.setLightOpacity(0)
        //採掘レベルの新規追加
        Blocks.CACTUS.setHarvestLevel("axe", 0)
        Blocks.CARPET.setHarvestLevel("axe", 0)
        Blocks.GLASS.setHarvestLevel("pickaxe", 0)
        Blocks.GLASS_PANE.setHarvestLevel("pickaxe", 0)
        Blocks.HAY_BLOCK.setHarvestLevel("axe", 0)
        Blocks.LEAVES2.setHarvestLevel("axe", 0)
        Blocks.LEAVES.setHarvestLevel("axe", 0)
        Blocks.LEVER.setHarvestLevel("pickaxe", 0)
        Blocks.NETHER_WART_BLOCK.setHarvestLevel("axe", 0)
        Blocks.PISTON.setHarvestLevel("pickaxe", 0)
        Blocks.SEA_LANTERN.setHarvestLevel("pickaxe", 0)
        Blocks.SKULL.setHarvestLevel("axe", 0)
        Blocks.SPONGE.setHarvestLevel("shovel", 0)
        Blocks.STAINED_GLASS.setHarvestLevel("pickaxe", 0)
        Blocks.STAINED_GLASS_PANE.setHarvestLevel("pickaxe", 0)
        Blocks.STICKY_PISTON.setHarvestLevel("pickaxe", 0)
    }

    //Itemの最大スタック数を上書きする
    private fun overrideStack() {
        //configのlistMaxStackを参照する
        for (name in RagiConfig.utility.listMaxStack) {
            val item = RagiUtils.getItem(name)
            //itemの耐久値が0の場合、最大スタック数を64に上書きする
            if (item.maxDamage == 0) item.setMaxStackSize(64)
        }
    }
}