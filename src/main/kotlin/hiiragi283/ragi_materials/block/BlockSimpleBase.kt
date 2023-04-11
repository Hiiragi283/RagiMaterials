package hiiragi283.ragi_materials.block

import net.minecraft.block.material.Material
import net.minecraft.block.properties.PropertyInteger
import net.minecraft.block.state.BlockStateContainer
import net.minecraft.block.state.IBlockState
import java.util.*

open class BlockSimpleBase(ID: String, Material: Material, private val maxMeta: Int, maxTips: Int) : BlockBase(ID, Material, maxTips) {

    companion object {
        private val property16 = PropertyInteger.create("property", 0, 15)
    }

    init {
        defaultState = blockState.baseState.withProperty(property16, 0) //デフォルトのBlockstateをpropertyの0番に設定
    }

    //    General    //

    override fun damageDropped(state: IBlockState): Int = state.getValue(property16)

    //    BlockState    //

    override fun createBlockState(): BlockStateContainer = BlockStateContainer(this, property16)

    override fun getMetaFromState(state: IBlockState): Int = if (state.getValue(property16) in 0..maxMeta) state.getValue(property16) else maxMeta

    @Deprecated("Deprecated in Java")
    override fun getStateFromMeta(meta: Int): IBlockState = defaultState.withProperty(property16, if (meta in 0..maxMeta) meta else maxMeta)

}