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

    override fun createBlockState(): BlockStateContainer = BlockStateContainer(this, property16)

    override fun getMetaFromState(state: IBlockState): Int = if (state.getValue(property16) in 0..maxMeta) state.getValue(property16) else maxMeta

    @Deprecated("Deprecated in Java")
    override fun getStateFromMeta(meta: Int): IBlockState = defaultState.withProperty(property16, if (meta in 0..maxMeta) meta else maxMeta)

}