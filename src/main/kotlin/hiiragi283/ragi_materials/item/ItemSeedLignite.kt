package hiiragi283.ragi_materials.item

import hiiragi283.ragi_materials.init.RagiInit
import hiiragi283.ragi_materials.material.MaterialBuilder
import hiiragi283.ragi_materials.material.MaterialRegistry
import net.minecraft.block.state.IBlockState
import net.minecraft.item.ItemStack
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockAccess

class ItemSeedLignite : ItemSeedPeat("seed_lignite") {

    //    IPlantable    //

    override fun getPlant(world: IBlockAccess, pos: BlockPos): IBlockState {
        return RagiInit.BlockCropLignite.defaultState
    }

    //    IMaterialItem    //

    override fun getMaterial(stack: ItemStack): MaterialBuilder {
        return MaterialRegistry.LIGNITE
    }
}