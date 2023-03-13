package hiiragi283.ragi_materials.block

import hiiragi283.ragi_materials.init.RagiItem
import hiiragi283.ragi_materials.material.MaterialRegistry
import hiiragi283.ragi_materials.material.builder.MaterialBuilder
import net.minecraft.block.state.IBlockState
import net.minecraft.item.Item
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

class BlockCropCoal : BlockCropPeat("crop_coal") {

    //    General    //

    override fun getDropMain(): Int = MaterialRegistry.COAL.index

    override fun getDropSeed(): Item = RagiItem.ItemSeedCoal

    override fun getDropSub(): Int = MaterialRegistry.ANTHRACITE.index

    //    IMaterialBlock    //

    override fun getMaterialBlock(world: World, pos: BlockPos, state: IBlockState): MaterialBuilder = MaterialRegistry.COAL

}