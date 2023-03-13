package hiiragi283.ragi_materials.item

import hiiragi283.ragi_materials.init.RagiBlock
import hiiragi283.ragi_materials.material.MaterialRegistry
import hiiragi283.ragi_materials.material.builder.MaterialBuilder
import net.minecraft.block.state.IBlockState
import net.minecraft.item.ItemStack
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockAccess

class ItemSeedLignite : ItemSeedPeat("seed_lignite") {

    //    IPlantable    //

    override fun getPlant(world: IBlockAccess, pos: BlockPos): IBlockState = RagiBlock.BlockCropLignite.defaultState

    //    IMaterialItem    //

    override fun getMaterial(stack: ItemStack): MaterialBuilder = MaterialRegistry.LIGNITE
}