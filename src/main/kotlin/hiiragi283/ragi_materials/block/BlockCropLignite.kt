package hiiragi283.ragi_materials.block

import hiiragi283.ragi_materials.init.RagiInit
import hiiragi283.ragi_materials.material.MaterialBuilder
import hiiragi283.ragi_materials.material.MaterialRegistry
import net.minecraft.block.state.IBlockState
import net.minecraft.item.Item
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

class BlockCropLignite : BlockCropPeat("crop_lignite") {

    //    General    //

    override fun getDropMain(): Int = MaterialRegistry.LIGNITE.index

    override fun getDropSeed(): Item = RagiInit.ItemSeedLignite

    override fun getDropSub(): Int = MaterialRegistry.COAL.index

    //    IMaterialBlock    //

    override fun getMaterialBlock(world: World, pos: BlockPos, state: IBlockState): MaterialBuilder = MaterialRegistry.LIGNITE

}