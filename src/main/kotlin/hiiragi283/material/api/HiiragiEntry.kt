package hiiragi283.material.api

import hiiragi283.material.api.block.MinableType
import hiiragi283.material.api.fluid.HiiragiFluid
import net.fabricmc.fabric.api.item.v1.FabricItem
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.Block
import net.minecraft.block.Blocks
import net.minecraft.block.FluidBlock
import net.minecraft.fluid.Fluid
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.item.ToolItem
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry

interface HiiragiEntry<T : Any> {

    fun getIdentifier(): Identifier

    fun getObject(): T

    fun register(): T

    interface BLOCK : HiiragiEntry<Block> {

        fun getMinableType(): MinableType

        override fun getObject(): Block = this as Block

        override fun register(): Block = Registry.register(Registry.BLOCK, getIdentifier(), getObject())

    }

    interface FLUID : HiiragiEntry<Fluid> {

        override fun getObject(): Fluid = this as Fluid

        override fun register(): Fluid = Registry.register(Registry.FLUID, getIdentifier(), getObject())

    }

    interface ITEM : HiiragiEntry<Item> {

        override fun getObject(): Item = this as Item

        override fun register(): Item = Registry.register(Registry.ITEM, getIdentifier(), getObject())

        interface TOOL : ITEM, FabricItem {

            override fun getRecipeRemainder(stack: ItemStack): ItemStack =
                if (this is ToolItem && stack.damage < this.material.durability) stack.copy()
                    .also { it.damage += 1 } else ItemStack.EMPTY

        }

    }

}

//    Block    //

abstract class HiiragiBlock(settings: Settings) : Block(settings), HiiragiEntry.BLOCK

abstract class HiiragiFluidBlock(
    fluid: HiiragiFluid,
    settings: FabricBlockSettings = FabricBlockSettings.copyOf(Blocks.WATER)
) : FluidBlock(fluid, settings), HiiragiEntry.BLOCK

//    Item    //

abstract class HiiragiBlockItem(block: Block, settings: FabricItemSettings = FabricItemSettings()) :
    BlockItem(block, settings), HiiragiEntry.ITEM

abstract class HiiragiItem(settings: FabricItemSettings = FabricItemSettings()) : Item(settings), HiiragiEntry.ITEM