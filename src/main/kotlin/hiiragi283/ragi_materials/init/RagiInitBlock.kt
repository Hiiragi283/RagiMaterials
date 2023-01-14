package hiiragi283.ragi_materials.init

import hiiragi283.ragi_materials.blocks.BlockForgeFurnace
import net.minecraftforge.fml.common.registry.ForgeRegistries

object RagiInitBlock {

    //Blockの定義
    val BlockForgeFurnace = BlockForgeFurnace()

    //Blockを登録するメソッド
    fun registerBlocks() {
        ForgeRegistries.BLOCKS.register(BlockForgeFurnace)
    }
}