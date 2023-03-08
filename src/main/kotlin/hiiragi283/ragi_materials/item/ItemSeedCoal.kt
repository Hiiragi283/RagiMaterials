package hiiragi283.ragi_materials.item

import hiiragi283.ragi_materials.init.RagiInit
import hiiragi283.ragi_materials.material.MaterialBuilder
import hiiragi283.ragi_materials.material.MaterialRegistry
import net.minecraft.block.state.IBlockState
import net.minecraft.item.ItemStack
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockAccess

class ItemSeedCoal : ItemSeedPeat("seed_coal") {

    //    IPlantable    //

    override fun getPlant(world: IBlockAccess, pos: BlockPos): IBlockState = RagiInit.BlockCropCoal.defaultState

    //    IMaterialItem    //

    override fun getMaterial(stack: ItemStack): MaterialBuilder = MaterialRegistry.COAL
}