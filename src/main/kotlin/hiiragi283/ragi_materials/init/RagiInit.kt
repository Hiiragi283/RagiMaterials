package hiiragi283.ragi_materials.init

import hiiragi283.ragi_materials.Reference
import hiiragi283.ragi_materials.base.ItemBase
import hiiragi283.ragi_materials.block.*
import hiiragi283.ragi_materials.config.RagiConfig
import hiiragi283.ragi_materials.event.ItemTooltip
import hiiragi283.ragi_materials.event.ModelRegistry
import hiiragi283.ragi_materials.event.RightClickBlock
import hiiragi283.ragi_materials.item.*
import hiiragi283.ragi_materials.material.MaterialBuilder.MaterialType
import hiiragi283.ragi_materials.util.RagiColor
import hiiragi283.ragi_materials.util.RagiModel
import hiiragi283.ragi_materials.util.RagiUtils
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.init.Blocks
import net.minecraft.item.Item
import net.minecraftforge.common.MinecraftForge

object RagiInit {

    //Blockの定義
    val BlockForgeFurnace = BlockForgeFurnace()
    val BlockLitForgeFurnace = BlockLitForgeFurnace()
    val BlockOreDictConv = BlockOreDictConv()

    //Itemの定義
    val ItemBlockForgeFurnace = ItemBlockForgeFurnace()
    val ItemBlockMetal = ItemMaterial("block_metal", MaterialType.METAL)
    val ItemBlockOreDictConv = ItemBlockOreDictConv()

    val ItemBookDebug = ItemBookDebug()
    val ItemDust = ItemMaterial("dust", MaterialType.DUST, MaterialType.METAL)
    val ItemForgeHammer: Item = ItemForgeHammer().setCreativeTab(CreativeTabs.TOOLS)
    val ItemIngot = ItemMaterial("ingot", MaterialType.METAL)
    val ItemIngotHot = ItemMaterial("ingot_hot", MaterialType.METAL)
    val ItemNugget = ItemMaterial("nugget", MaterialType.METAL)
    val ItemPlate = ItemMaterial("plate", MaterialType.METAL)
    val ItemToolBellow: Item = ItemBase(Reference.MOD_ID, "bellow", 0)
        .setCreativeTab(CreativeTabs.TOOLS)
        .setMaxDamage(63).setMaxStackSize(1)

    fun loadPreInit() {
        //Event, Fluidの登録
        registerEvents()
        RagiInitFluid.registerFluids()
        RagiModel.setModelFluids()
    }

    fun loadInit() {
        //BlockとItemの着色
        RagiColor.setColor(
            RagiColor.ColorMaterial(), ItemBlockMetal, ItemDust, ItemIngot, ItemNugget, ItemPlate
        )
        RagiColor.setColor(RagiColor.ColorHot(), ItemIngotHot)
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

    //Eventを登録するメソッド
    private fun registerEvents() {
        MinecraftForge.EVENT_BUS.register(ItemTooltip())
        MinecraftForge.EVENT_BUS.register(ModelRegistry())
        MinecraftForge.EVENT_BUS.register(RightClickBlock())
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
        for (name in RagiConfig.listMaxStack) {
            val item = RagiUtils.getItem(name)
            //itemの耐久値が0の場合、最大スタック数を64に上書きする
            if (item.maxDamage == 0) item.setMaxStackSize(64)
        }
    }
}