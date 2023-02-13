package hiiragi283.ragi_materials

import hiiragi283.ragi_materials.config.RagiConfig
import hiiragi283.ragi_materials.event.RightClickBlock
import hiiragi283.ragi_materials.init.RagiInit
import hiiragi283.ragi_materials.init.RagiInitOreDict
import hiiragi283.ragi_materials.init.RagiInitRecipe
import hiiragi283.ragi_materials.proxy.CommonProxy
import hiiragi283.ragi_materials.util.RagiUtils
import net.minecraft.init.Blocks
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fluids.FluidRegistry
import net.minecraftforge.fml.common.Loader
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.SidedProxy
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent

//Modの定義
@Mod(
    modid = Reference.MOD_ID,
    name = Reference.MOD_NAME,
    version = Reference.VERSION,
    dependencies = Reference.DEPENDENCIES,
    acceptedMinecraftVersions = Reference.MC_VERSIONS
)
class RagiMaterials {

    init {
        //Universal Bucketの使用
        FluidRegistry.enableUniversalBucket()
    }

    companion object {
        val isLoadedGT = Loader.isModLoaded("gregtech")

        //Proxyの定義
        @SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
        var proxy: CommonProxy? = null
    }

    //Pre-Initializationの段階で呼ばれるevent
    @Mod.EventHandler
    fun preInit(event: FMLPreInitializationEvent?) {
        if (!isLoadedGT) {
            RagiInit.register()
            //Eventの登録
            MinecraftForge.EVENT_BUS.register(RightClickBlock())
            //proxyの読み込み
            proxy!!.loadPreInit()
        }
    }

    //Initializationの段階で呼ばれるevent
    @Mod.EventHandler
    fun init(event: FMLInitializationEvent?) {
        if (!isLoadedGT) {
            //鉱石辞書の登録
            RagiInitOreDict.registerOreDict()
            //レシピの登録
            RagiInitRecipe.registerRecipes()
            //proxyの読み込み
            proxy!!.loadInit()
        }
    }

    //Post-Initializationの段階で呼ばれるevent
    @Mod.EventHandler
    fun postInit(event: FMLPostInitializationEvent?) {
        if (!isLoadedGT) {
            //ディスペンサーの機能の登録
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
            //アイテムの最大スタック数の上書き
            //configのlistMaxStackを参照する
            for (name in RagiConfig.utility.listMaxStack) {
                val item = RagiUtils.getItem(name)
                //itemの耐久値が0の場合、最大スタック数を64に上書きする
                if (item.maxDamage == 0) item.setMaxStackSize(64)
            }
            //proxyの読み込み
            proxy!!.loadPostInit()
        }
    }
}