package hiiragi283.material.api

import hiiragi283.material.api.fluid.HiiragiFluid
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.Block
import net.minecraft.block.Blocks
import net.minecraft.block.FluidBlock
import net.minecraft.fluid.Fluid
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry

interface HiiragiEntry<T : Any> {

    fun getIdentifier(): Identifier

    fun register(): T

    interface BLOCK : HiiragiEntry<Block> {

        override fun register(): Block = Registry.register(Registry.BLOCK, getIdentifier(), this as Block)

    }

    interface FLUID : HiiragiEntry<Fluid> {

        override fun register(): Fluid = Registry.register(Registry.FLUID, getIdentifier(), this as Fluid)

    }

    interface ITEM : HiiragiEntry<Item> {

        override fun register(): Item = Registry.register(Registry.ITEM, getIdentifier(), this as Item)

    }

}

abstract class HiiragiBlock(settings: FabricBlockSettings) : Block(settings), HiiragiEntry.BLOCK

abstract class HiiragiFluidBlock(
    fluid: HiiragiFluid,
    settings: FabricBlockSettings = FabricBlockSettings.copyOf(Blocks.WATER)
) : FluidBlock(fluid, settings), HiiragiEntry.BLOCK

abstract class HiiragiBlockItem(block: Block, settings: FabricItemSettings = FabricItemSettings()) :
    BlockItem(block, settings), HiiragiEntry.ITEM

abstract class HiiragiItem(settings: FabricItemSettings = FabricItemSettings()) : Item(settings), HiiragiEntry.ITEM