package hiiragi283.material.util.wrapper

import net.minecraft.block.Block
import net.minecraft.block.state.IBlockState

data class BlockStateWrapper(val block: Block, val meta: Int) {

    constructor(state: IBlockState) : this(state.block, state.block.getMetaFromState(state))

    fun toBlockState(): IBlockState = block.getStateFromMeta(meta)

    override fun toString() = StringBuilder().also {
        it.append(block.registryName!!.namespace)
        it.append(":")
        it.append(block.registryName!!.path)
        it.append(":")
        it.append(meta)
    }.toString()

}