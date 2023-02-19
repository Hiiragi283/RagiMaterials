package hiiragi283.ragi_materials.item

import hiiragi283.ragi_materials.init.RagiInit
import net.minecraft.block.state.IBlockState
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockAccess

class ItemSeedCoal : ItemSeedPeat("seed_coal") {

    override fun getPlant(world: IBlockAccess, pos: BlockPos): IBlockState {
        return RagiInit.BlockCropCoal.defaultState
    }
}