package hiiragi283.ragi_materials.util

import com.google.common.base.Predicate
import net.minecraft.block.state.IBlockState

class RagiStateMatcher(val state: IBlockState): Predicate<IBlockState> {

    //    Predicate    //

    override fun apply(input: IBlockState?): Boolean {
        var result = false
        input?.let {
            val block1 = it.block
            val block2 = state.block
            result = block1 == block2 && block1.getMetaFromState(it) == block2.getMetaFromState(state)
        }
        return result
    }
}