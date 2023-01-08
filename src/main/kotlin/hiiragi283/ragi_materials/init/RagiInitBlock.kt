package hiiragi283.ragi_materials.init

import hiiragi283.ragi_materials.Reference
import hiiragi283.ragi_materials.base.BlockMaterialBase
import net.minecraft.block.material.Material
import net.minecraftforge.fml.common.registry.ForgeRegistries

object RagiInitBlock {

    //Blockの定義
    val BlockMetal = BlockMaterialBase(Material.IRON, Reference.MOD_ID, "block_metal")

    //Blockを登録するメソッド
    fun registerBlocks() {
        ForgeRegistries.BLOCKS.register(BlockMetal)
    }
}