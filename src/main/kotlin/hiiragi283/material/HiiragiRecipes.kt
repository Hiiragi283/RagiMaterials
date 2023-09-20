package hiiragi283.material

import hiiragi283.material.api.machine.IMachineProperty
import hiiragi283.material.api.machine.MachineTrait
import hiiragi283.material.api.material.MaterialCommon
import hiiragi283.material.api.material.MaterialElements
import hiiragi283.material.api.recipe.IMachineRecipe
import hiiragi283.material.api.recipe.MachineRecipe
import hiiragi283.material.api.shape.HiiragiShapes
import hiiragi283.material.util.CraftingBuilder
import hiiragi283.material.util.append
import hiiragi283.material.util.hiiragiLocation
import hiiragi283.material.util.toLocation
import net.minecraft.block.Block
import net.minecraft.init.Blocks
import net.minecraft.init.Items
import net.minecraft.item.ItemStack
import net.minecraft.item.crafting.FurnaceRecipes
import net.minecraftforge.fluids.FluidRegistry

object HiiragiRecipes {

    fun init() {
        //Primitive Machine
        CraftingBuilder(
            hiiragiLocation("primitive_former"),
            HiiragiBlocks.MACHINE_SMELTER.createMachineStack(
                MaterialCommon.STONE.index, IMachineProperty.of(
                    processTime = Int.MAX_VALUE,
                    energyRate = 0,
                    machineTraits = setOf(MachineTrait.PRIMITIVE)
                )
            )
        )
            .setPattern("AAA", "ABA", "CCC")
            .setIngredient('A', "stone")
            .setIngredient('B', "workbench")
            .setIngredient('C', HiiragiShapes.SLAB.getOreDict(MaterialCommon.STONE))
            .build()
    }

    fun postInit() {
        compressor()
        freezer()
        smelter()
        rockGenerator()
        test()
    }

    private fun compressor() {
        MachineRecipe.Builder(IMachineRecipe.Type.COMPRESSOR, Blocks.PACKED_ICE.registryName!!)
            .addInput(Blocks.ICE, count = 3)
            .addOutput(Blocks.PACKED_ICE)
            .buildAndRegister()
    }

    private fun freezer() {
        MachineRecipe.Builder(IMachineRecipe.Type.FREEZER, Blocks.ICE.registryName!!)
            .addInputFluid(FluidRegistry.WATER, 1000)
            .addOutput(Blocks.ICE)
            .buildAndRegister()
        MachineRecipe.Builder(IMachineRecipe.Type.FREEZER, Blocks.MAGMA.registryName!!)
            .addInputFluid(FluidRegistry.LAVA, 1000)
            .addOutput(Blocks.MAGMA)
            .buildAndRegister()
    }

    private fun rockGenerator() {
        fun addCopyRecipe(stone: Block, meta: Int = 0, water: Int = 0, lava: Int = 0) {
            val stack = ItemStack(stone, 1, meta)
            MachineRecipe.Builder(IMachineRecipe.Type.ROCK_GENERATOR, stack.toLocation())
                .addInput(stack)
                .addInputFluid(FluidRegistry.WATER, water)
                .addInputFluid(FluidRegistry.LAVA, lava)
                .addOutput(stack)
                .buildAndRegister()
        }
        addCopyRecipe(Blocks.STONE)
        addCopyRecipe(Blocks.STONE, 1)
        addCopyRecipe(Blocks.STONE, 3)
        addCopyRecipe(Blocks.STONE, 5)
        addCopyRecipe(Blocks.COBBLESTONE)
        addCopyRecipe(Blocks.OBSIDIAN, 0, 1000, 1000)
        addCopyRecipe(Blocks.NETHERRACK, lava = 1)
        addCopyRecipe(Blocks.END_STONE, water = 1)
    }

    private fun smelter() {
        //かまどレシピの自動インポート
        FurnaceRecipes.instance().smeltingList.forEach { (input: ItemStack, output: ItemStack) ->
            MachineRecipe.Builder(IMachineRecipe.Type.SMELTER, input.toLocation().append("_furnace"))
                .addInput(input)
                .addOutput(output)
                .buildAndRegister()
        }
        //合金レシピ
        MachineRecipe.Builder(IMachineRecipe.Type.SMELTER, hiiragiLocation("stainless_steel_dust"))
            .addInput(HiiragiShapes.DUST, MaterialElements.IRON, 6)
            .addInput(HiiragiShapes.DUST, MaterialElements.CHROMIUM)
            .addInput(HiiragiShapes.DUST, MaterialElements.MANGANESE)
            .addInput(HiiragiShapes.DUST, MaterialElements.NICKEL)
            .addOutput(HiiragiShapes.INGOT, MaterialCommon.STAINLESS_STEEL, 9)
            .buildAndRegister()
        MachineRecipe.Builder(IMachineRecipe.Type.SMELTER, hiiragiLocation("stainless_steel_ingot"))
            .addInput(HiiragiShapes.INGOT, MaterialElements.IRON, 6)
            .addInput(HiiragiShapes.INGOT, MaterialElements.CHROMIUM)
            .addInput(HiiragiShapes.INGOT, MaterialElements.MANGANESE)
            .addInput(HiiragiShapes.INGOT, MaterialElements.NICKEL)
            .addOutput(HiiragiShapes.INGOT, MaterialCommon.STAINLESS_STEEL, 9)
            .buildAndRegister()

        MachineRecipe.Builder(IMachineRecipe.Type.SMELTER, hiiragiLocation("steel_dust"))
            .addInput(HiiragiShapes.DUST, MaterialElements.IRON)
            .addInput(HiiragiShapes.DUST, MaterialCommon.CHARCOAL, 4)
            .addOutput(HiiragiShapes.INGOT, MaterialCommon.STEEL)
            .buildAndRegister()
        MachineRecipe.Builder(IMachineRecipe.Type.SMELTER, hiiragiLocation("steel_ingot"))
            .addInput(HiiragiShapes.INGOT, MaterialElements.IRON)
            .addInput(HiiragiShapes.DUST, MaterialCommon.CHARCOAL, 4)
            .addOutput(HiiragiShapes.INGOT, MaterialCommon.STEEL)
            .buildAndRegister()
        MachineRecipe.Builder(IMachineRecipe.Type.SMELTER, hiiragiLocation("steel_dust_coke"))
            .addInput(HiiragiShapes.DUST, MaterialElements.IRON)
            .addInput(HiiragiShapes.DUST, MaterialCommon.COKE)
            .addOutput(HiiragiShapes.INGOT, MaterialCommon.STEEL)
            .buildAndRegister()
        MachineRecipe.Builder(IMachineRecipe.Type.SMELTER, hiiragiLocation("steel_ingot_coke"))
            .addInput(HiiragiShapes.INGOT, MaterialElements.IRON)
            .addInput(HiiragiShapes.DUST, MaterialCommon.COKE)
            .addOutput(HiiragiShapes.INGOT, MaterialCommon.STEEL)
            .buildAndRegister()

        MachineRecipe.Builder(IMachineRecipe.Type.SMELTER, hiiragiLocation("constantan_dust"))
            .addInput(HiiragiShapes.DUST, MaterialElements.NICKEL)
            .addInput(HiiragiShapes.DUST, MaterialElements.COPPER)
            .addOutput(HiiragiShapes.INGOT, MaterialCommon.CONSTANTAN, 2)
            .buildAndRegister()
        MachineRecipe.Builder(IMachineRecipe.Type.SMELTER, hiiragiLocation("constantan_ingot"))
            .addInput(HiiragiShapes.INGOT, MaterialElements.NICKEL)
            .addInput(HiiragiShapes.INGOT, MaterialElements.COPPER)
            .addOutput(HiiragiShapes.INGOT, MaterialCommon.CONSTANTAN, 2)
            .buildAndRegister()

        MachineRecipe.Builder(IMachineRecipe.Type.SMELTER, hiiragiLocation("invar_dust"))
            .addInput(HiiragiShapes.DUST, MaterialElements.NICKEL, 2)
            .addInput(HiiragiShapes.DUST, MaterialElements.IRON)
            .addOutput(HiiragiShapes.INGOT, MaterialCommon.INVAR, 2)
            .buildAndRegister()
        MachineRecipe.Builder(IMachineRecipe.Type.SMELTER, hiiragiLocation("invar_ingot"))
            .addInput(HiiragiShapes.INGOT, MaterialElements.NICKEL, 2)
            .addInput(HiiragiShapes.INGOT, MaterialElements.IRON)
            .addOutput(HiiragiShapes.INGOT, MaterialCommon.INVAR, 2)
            .buildAndRegister()

        MachineRecipe.Builder(IMachineRecipe.Type.SMELTER, hiiragiLocation("brass_dust"))
            .addInput(HiiragiShapes.DUST, MaterialElements.COPPER, 3)
            .addInput(HiiragiShapes.DUST, MaterialElements.ZINC)
            .addOutput(HiiragiShapes.INGOT, MaterialCommon.BRASS, 4)
            .buildAndRegister()
        MachineRecipe.Builder(IMachineRecipe.Type.SMELTER, hiiragiLocation("brass_ingot"))
            .addInput(HiiragiShapes.INGOT, MaterialElements.COPPER, 3)
            .addInput(HiiragiShapes.INGOT, MaterialElements.ZINC)
            .addOutput(HiiragiShapes.INGOT, MaterialCommon.BRASS, 4)
            .buildAndRegister()

        MachineRecipe.Builder(IMachineRecipe.Type.SMELTER, hiiragiLocation("bronze_dust"))
            .addInput(HiiragiShapes.DUST, MaterialElements.COPPER, 3)
            .addInput(HiiragiShapes.DUST, MaterialElements.TIN)
            .addOutput(HiiragiShapes.INGOT, MaterialCommon.BRONZE, 4)
            .buildAndRegister()
        MachineRecipe.Builder(IMachineRecipe.Type.SMELTER, hiiragiLocation("bronze_ingot"))
            .addInput(HiiragiShapes.INGOT, MaterialElements.COPPER, 3)
            .addInput(HiiragiShapes.INGOT, MaterialElements.TIN)
            .addOutput(HiiragiShapes.INGOT, MaterialCommon.BRONZE, 4)
            .buildAndRegister()

        MachineRecipe.Builder(IMachineRecipe.Type.SMELTER, hiiragiLocation("electrum_dust"))
            .addInput(HiiragiShapes.DUST, MaterialElements.SILVER)
            .addInput(HiiragiShapes.DUST, MaterialElements.GOLD)
            .addOutput(HiiragiShapes.INGOT, MaterialCommon.ELECTRUM, 2)
            .buildAndRegister()
        MachineRecipe.Builder(IMachineRecipe.Type.SMELTER, hiiragiLocation("electrum_ingot"))
            .addInput(HiiragiShapes.INGOT, MaterialElements.SILVER)
            .addInput(HiiragiShapes.INGOT, MaterialElements.GOLD)
            .addOutput(HiiragiShapes.INGOT, MaterialCommon.ELECTRUM, 2)
            .buildAndRegister()

        MachineRecipe.Builder(IMachineRecipe.Type.SMELTER, hiiragiLocation("tungsten_steel_dust"))
            .addInput(HiiragiShapes.DUST, MaterialElements.TUNGSTEN)
            .addInput(HiiragiShapes.DUST, MaterialCommon.STEEL)
            .addOutput(HiiragiShapes.INGOT, MaterialCommon.TUNGSTEN_STEEL, 2)
            .buildAndRegister()
        MachineRecipe.Builder(IMachineRecipe.Type.SMELTER, hiiragiLocation("tungsten_steel_ingot"))
            .addInput(HiiragiShapes.INGOT, MaterialElements.TUNGSTEN)
            .addInput(HiiragiShapes.INGOT, MaterialCommon.STEEL)
            .addOutput(HiiragiShapes.INGOT, MaterialCommon.TUNGSTEN_STEEL, 2)
            .buildAndRegister()

    }

    private fun test() {
        MachineRecipe.Builder(IMachineRecipe.Type.TEST, hiiragiLocation("smelt_test"))
            .addInput(Blocks.COBBLESTONE)
            .addOutput(Blocks.STONE)
            .buildAndRegister()
        MachineRecipe.Builder(IMachineRecipe.Type.TEST, hiiragiLocation("almighty"))
            .addInput(ItemStack(Items.COAL))
            .addInput(ItemStack(Items.COAL, 1, 1))
            .addInput(ItemStack(Blocks.BEACON))
            .addInput(Blocks.DIAMOND_BLOCK)
            .addInput("cobblestone")
            .addInput(HiiragiShapes.INGOT, MaterialCommon.STEEL)
            .addInputFluid(FluidRegistry.WATER, 1000)
            .addInputFluid(FluidRegistry.LAVA, 500)
            .addInputFluid(MaterialCommon.BRONZE, 144 * 4)
            .addOutput(Blocks.DIRT)
            .addOutputFluid(MaterialElements.HYDROGEN, 4000)
            .buildAndRegister()
        /*MachineRecipe.Builder(IMachineRecipe.Type.TEST, hiiragiLocation("water_test"))
            .addInputFluid(FluidRegistry.WATER, 1000)
            .addOutputBlock(Blocks.ICE)
            .buildAndRegister()*/
    }

}