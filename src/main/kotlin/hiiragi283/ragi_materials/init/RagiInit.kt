package hiiragi283.ragi_materials.init

import hiiragi283.ragi_materials.event.RightClickBlock
import hiiragi283.ragi_materials.util.RagiUtils
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fml.common.registry.ForgeRegistries


object RagiInit {

    fun loadPreInit() {
        //Block, Event, Itemの登録
        RagiInitBlock.registerBlocks()
        registerEvents()
        RagiInitFluid.registerFluids()
        RagiInitItem.registerItems()
    }

    fun loadInit() {
        //鉱石辞書の登録
        RagiInitOreDict.registerOreDict()
        //レシピの登録
        RagiInitRecipe.registerRecipes()
    }

    fun loadPostInit() {
        //Itemの最大スタック数を上書きする
        for (name in ForgeRegistries.ITEMS.keys) {
            val item = RagiUtils.getItem(name.toString())
            //itemの耐久値が0の場合、最大スタック数を64に上書きする
            if (item.maxDamage == 0) item.setMaxStackSize(64)
        }
    }

    //Eventを登録するメソッド
    fun registerEvents() {
        MinecraftForge.EVENT_BUS.register(RightClickBlock())
    }
}