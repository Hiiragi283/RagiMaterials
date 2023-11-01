package hiiragi283.material.api.part

import hiiragi283.material.api.ingredient.ItemIngredient
import hiiragi283.material.api.machine.MachineType
import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.api.shape.HiiragiShape
import hiiragi283.material.init.HiiragiShapes
import hiiragi283.material.item.ItemShapePattern
import hiiragi283.material.recipe.MachineRecipe
import hiiragi283.material.util.getTileImplemented
import hiiragi283.material.util.hiiragiLocation
import hiiragi283.material.util.isSameWithoutCount
import hiiragi283.material.util.item
import net.minecraft.block.Block
import net.minecraft.block.state.IBlockState
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockAccess
import java.util.function.Supplier

object PartConvertible {

    interface BLOCK : ITEM {

        companion object {

            private val registry: MutableMap<HiiragiShape, BLOCK> = mutableMapOf()

            fun register(block: BLOCK) {
                registry.putIfAbsent(block.shape, block)
            }

            operator fun get(shape: HiiragiShape): BLOCK? = registry[shape]

        }

        fun getMaterial(state: IBlockState, world: IBlockAccess?, pos: BlockPos?): HiiragiMaterial? =
            getTileImplemented<TILE>(world, pos)?.material

        fun getPart(state: IBlockState, world: IBlockAccess?, pos: BlockPos?): HiiragiPart? =
            getMaterial(state, world, pos)?.getPart(shape)

        fun block() = this as Block

        override fun item(): Item = block().item()

    }

    interface ITEM {

        companion object {

            private val registry: MutableMap<HiiragiShape, ITEM> = mutableMapOf()

            fun register(item: ITEM) {
                registry.putIfAbsent(item.shape, item)
            }

            operator fun get(shape: HiiragiShape): ITEM? = registry[shape]

        }

        val shape: HiiragiShape

        fun getMaterial(stack: ItemStack): HiiragiMaterial? = HiiragiMaterial.REGISTRY[stack.metadata]

        fun getPart(stack: ItemStack): HiiragiPart? = getMaterial(stack)?.getPart(shape)

        fun item(): Item = this as Item

        fun addMetalFormerRecipe(material: HiiragiMaterial, inputCount: Int = 1, outputCount: Int = 1) {
            if (!material.isMetal()) return
            val part: HiiragiPart = shape.getPart(material)
            MachineRecipe.buildAndRegister(
                MachineType.METAL_FORMER,
                hiiragiLocation(part.toString())
            ) {
                inputItems.add(ItemIngredient.Parts(HiiragiShapes.INGOT, part.material, inputCount))
                inputItems.add(
                    ItemIngredient.Custom(
                        stacks = { listOf(ItemShapePattern.getItemStack(part.shape)) },
                        predicate = { stack -> stack.isSameWithoutCount(ItemShapePattern.getItemStack(part.shape)) },
                        process = ItemIngredient.CATALYST_PROCESS
                    )
                )
                outputItems.add(Supplier { part.getItemStack(outputCount) })
            }
        }

    }

    interface TILE {

        var material: HiiragiMaterial?

    }

}