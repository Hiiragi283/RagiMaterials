package hiiragi283.material.api

import hiiragi283.material.api.fluid.HiiragiFluid
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.Block
import net.minecraft.block.Blocks
import net.minecraft.block.FluidBlock
import net.minecraft.fluid.Fluid
import net.minecraft.item.*
import net.minecraft.text.Text
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

//    Block    //

abstract class HiiragiBlock(settings: FabricBlockSettings) : Block(settings), HiiragiEntry.BLOCK

abstract class HiiragiFluidBlock(
    fluid: HiiragiFluid,
    settings: FabricBlockSettings = FabricBlockSettings.copyOf(Blocks.WATER)
) : FluidBlock(fluid, settings), HiiragiEntry.BLOCK

//    Item    //

abstract class HiiragiBlockItem(block: Block, settings: FabricItemSettings = FabricItemSettings()) :
    BlockItem(block, settings), HiiragiEntry.ITEM

abstract class HiiragiItem(settings: FabricItemSettings = FabricItemSettings()) : Item(settings), HiiragiEntry.ITEM

//    Item - Tool    //

abstract class HiiragiAxeItem(
    material: ToolMaterial,
    attackDamage: Float = 0.0f,
    attackSpeed: Float = -3.0f,
    settings: FabricItemSettings = FabricItemSettings().group(ItemGroup.TOOLS)
) : AxeItem(material, attackDamage, attackSpeed, settings), HiiragiEntry.ITEM {

    override fun getRecipeRemainder(stack: ItemStack): ItemStack {
        val damage = stack.damage
        return if (damage >= this.material.durability) ItemStack.EMPTY else stack.copy().also { it.damage += 1 }
    }

}

abstract class HiiragiCraftingToolItem(
    material: ToolMaterial,
    settings: FabricItemSettings = FabricItemSettings().group(ItemGroup.TOOLS)
) : ToolItem(material, settings), HiiragiEntry.ITEM {

    override fun getRecipeRemainder(stack: ItemStack): ItemStack {
        val damage = stack.damage
        return if (damage >= this.material.durability) ItemStack.EMPTY else stack.copy().also { it.damage += 1 }
    }

}

abstract class HiiragiHoeItem(
    material: ToolMaterial,
    attackDamage: Int = 0,
    attackSpeed: Float = -3.0f,
    settings: FabricItemSettings = FabricItemSettings().group(ItemGroup.TOOLS)
) : HoeItem(material, attackDamage, attackSpeed, settings), HiiragiEntry.ITEM {

    override fun getRecipeRemainder(stack: ItemStack): ItemStack {
        val damage = stack.damage
        return if (damage >= this.material.durability) ItemStack.EMPTY else stack.copy().also { it.damage += 1 }
    }

}

abstract class HiiragiPickaxeItem(
    material: ToolMaterial,
    attackDamage: Int = 0,
    attackSpeed: Float = -3.0f,
    settings: FabricItemSettings = FabricItemSettings().group(ItemGroup.TOOLS)
) : PickaxeItem(material, attackDamage, attackSpeed, settings), HiiragiEntry.ITEM {

    override fun getRecipeRemainder(stack: ItemStack): ItemStack {
        val damage = stack.damage
        return if (damage >= this.material.durability) ItemStack.EMPTY else stack.copy().also { it.damage += 1 }
    }

}

abstract class HiiragiShovelItem(
    material: ToolMaterial,
    attackDamage: Float = 0.0f,
    attackSpeed: Float = -3.0f,
    settings: FabricItemSettings = FabricItemSettings().group(ItemGroup.TOOLS)
) : ShovelItem(material, attackDamage, attackSpeed, settings), HiiragiEntry.ITEM {

    override fun getRecipeRemainder(stack: ItemStack): ItemStack {
        val damage = stack.damage
        return if (damage >= this.material.durability) ItemStack.EMPTY else stack.copy().also { it.damage += 1 }
    }

}