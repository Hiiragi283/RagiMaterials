package hiiragi283.ragi_materials.block

import hiiragi283.ragi_materials.init.RagiInit
import hiiragi283.ragi_materials.material.MaterialBuilder
import hiiragi283.ragi_materials.material.MaterialRegistry
import net.minecraft.block.state.IBlockState
import net.minecraft.item.Item
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

class BlockCropCoal : BlockCropPeat("crop_coal") {

    //    General    //

    override fun getDropMain(): Int {
        return MaterialRegistry.COAL.index
    }

    override fun getDropSeed(): Item {
        return RagiInit.ItemSeedCoal
    }

    override fun getDropSub(): Int {
        return MaterialRegistry.ANTHRACITE.index
    }

    //    IMaterialBlock    //

    override fun getMaterialBlock(world: World, pos: BlockPos, state: IBlockState): MaterialBuilder {
        return MaterialRegistry.COAL
    }

}