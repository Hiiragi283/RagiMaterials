package hiiragi283.material.api.part

import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.api.shape.HiiragiShape
import hiiragi283.material.init.HiiragiRegistries
import hiiragi283.material.util.getTileImplemented
import hiiragi283.material.util.item
import net.minecraft.block.Block
import net.minecraft.block.state.IBlockState
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockAccess

object PartConvertible {

    interface BLOCK : ITEM {

        fun getMaterial(state: IBlockState, world: IBlockAccess?, pos: BlockPos?): HiiragiMaterial? =
            getTileImplemented<TILE>(world, pos)?.material

        fun block() = this as Block

        override fun item(): Item = block().item()

    }

    interface ITEM {

        val shape: HiiragiShape

        fun getMaterial(stack: ItemStack): HiiragiMaterial? = HiiragiRegistries.MATERIAL_INDEX.getValue(stack.metadata)

        fun getPart(stack: ItemStack): HiiragiPart? = getMaterial(stack)?.getPart(shape)

        fun item(): Item = this as Item

    }

    interface TILE {

        var material: HiiragiMaterial?

    }

}