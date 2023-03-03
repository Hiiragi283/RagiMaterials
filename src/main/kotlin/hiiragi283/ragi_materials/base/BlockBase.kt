package hiiragi283.ragi_materials.base

import net.minecraft.block.Block
import net.minecraft.block.material.Material
import net.minecraft.block.properties.PropertyInteger
import net.minecraft.block.state.BlockStateContainer
import net.minecraft.block.state.IBlockState
import java.util.*

open class BlockBase(Material: Material?, MOD: String, ID: String?, val maxMeta: Int) : Block(Material!!) {

    companion object {
        private val property16 = PropertyInteger.create("property", 0, 15)
    }

    init {
        defaultState = blockState.baseState.withProperty(property16, 0) //デフォルトのBlockstateをpropertyの0番に設定
        setRegistryName(MOD, ID)
        unlocalizedName = ID.toString() //翻訳キーをIDから取得
    }

    //    BlockState    //

    override fun createBlockState(): BlockStateContainer {
        //property16を使用すると、Blockstateは"type=0"から"type=15"までが登録される
        return BlockStateContainer(this, property16)
    }

    override fun getMetaFromState(state: IBlockState): Int {
        //stateから得られたmetadataが0以上maxMeta以下の場合，そのまま返す
        //そうでない場合はmaxMetaを返す
        return if (state.getValue(property16) in 0..maxMeta) state.getValue(property16) else maxMeta
    }

    @Deprecated("Deprecated in Java")
    override fun getStateFromMeta(meta: Int): IBlockState {
        //代入した値が0以上maxMeta以下の場合，そのまま返す
        //そうでない場合はmaxMetaを返す
        val metadata = if (meta in 0..maxMeta) meta else maxMeta
        //property16をもとに指定したメタデータからBlockstateを返す
        return defaultState.withProperty(property16, metadata)
    }
}