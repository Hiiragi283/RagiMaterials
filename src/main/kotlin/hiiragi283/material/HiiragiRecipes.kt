package hiiragi283.material

import hiiragi283.material.api.machine.IMachineProperty
import hiiragi283.material.api.machine.MachineTrait
import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.api.material.MaterialCommon
import hiiragi283.material.api.material.MaterialElements
import hiiragi283.material.api.part.HiiragiPart
import hiiragi283.material.api.part.getParts
import hiiragi283.material.api.recipe.IMachineRecipe
import hiiragi283.material.api.recipe.MachineRecipe
import hiiragi283.material.api.registry.HiiragiRegistries
import hiiragi283.material.api.shape.HiiragiShapes
import hiiragi283.material.tile.TileEntityModuleMachine
import hiiragi283.material.util.*
import net.minecraft.block.Block
import net.minecraft.init.Blocks
import net.minecraft.init.Items
import net.minecraft.item.ItemStack
import net.minecraft.item.crafting.FurnaceRecipes
import net.minecraftforge.fluids.FluidRegistry
import net.minecraftforge.fluids.FluidStack

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
            .setIngredient('B', HiiragiItems.RECIPE_SMELTER.getItemStack())
            .setIngredient('C', HiiragiShapes.SLAB.getOreDict(MaterialCommon.STONE))
            .build()
    }

    fun postInit() {
        compressor()
        extractor()
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

    private fun extractor() {
        fun addPrimitive(stone: Block, meta: Int = 0, materials: List<HiiragiMaterial>) {
            val stack = ItemStack(stone, 8, meta)
            val builder = MachineRecipe.Builder(
                IMachineRecipe.Type.EXTRACTOR,
                stack.toLocation().append("_primitive")
            )
            builder.addTrait(MachineTrait.PRIMITIVE)
            builder.addInput(stack, count = 8)
            materials.getOrNull(0)?.let { builder.addOutput(HiiragiShapes.DUST, it, 4) }
            materials.getOrNull(1)?.let { builder.addOutput(HiiragiShapes.DUST, it, 2) }
            materials.getOrNull(2)?.let { builder.addOutput(HiiragiShapes.DUST, it, 1) }
            builder.buildAndRegister()
        }
        //Cobblestone -> Iron, Nickel
        addPrimitive(
            Blocks.COBBLESTONE,
            0,
            listOf(
                MaterialElements.IRON,
                MaterialElements.NICKEL,

                )
        )
        //Granite -> Copper, Manganese, Gold
        addPrimitive(
            Blocks.STONE,
            1,
            listOf(
                MaterialElements.COPPER,
                MaterialElements.MANGANESE,
                MaterialElements.GOLD
            )
        )
        //Diorite -> Tin, Lead, Cobalt
        addPrimitive(
            Blocks.STONE,
            3,
            listOf(
                MaterialElements.TIN,
                MaterialElements.LEAD,
                MaterialElements.COBALT
            )
        )
        //Andesite -> Zinc, Silver, Chrome
        addPrimitive(
            Blocks.STONE,
            5,
            listOf(
                MaterialElements.ZINC,
                MaterialElements.SILVER,
                MaterialElements.CHROMIUM
            )
        )
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
        fun addCopy(stone: Block, meta: Int = 0, water: Int = 0, lava: Int = 0) {
            val stack = ItemStack(stone, 1, meta)
            MachineRecipe.Builder(IMachineRecipe.Type.ROCK_GENERATOR, stack.toLocation())
                .addInput(stack)
                .addInputFluid(FluidRegistry.WATER, water)
                .addInputFluid(FluidRegistry.LAVA, lava)
                .addOutput(stack)
                .buildAndRegister()
        }
        addCopy(Blocks.STONE)
        addCopy(Blocks.STONE, 1)
        addCopy(Blocks.STONE, 3)
        addCopy(Blocks.STONE, 5)
        addCopy(Blocks.COBBLESTONE)
        addCopy(Blocks.OBSIDIAN, 0, 1000, 1000)
        addCopy(Blocks.NETHERRACK, lava = 1)
        addCopy(Blocks.END_STONE, water = 1)
    }

    private fun smelter() {
        //かまどレシピの自動インポート
        //インプットがDUST, アウトプットがINGOTのものは除外される
        FurnaceRecipes.instance().smeltingList.toList()
            .filter { (input: ItemStack, output: ItemStack) ->
                HiiragiShapes.DUST !in input.getParts()
                    .map(HiiragiPart::shape) && HiiragiShapes.INGOT !in output.getParts().map(HiiragiPart::shape)
            }
            .forEach { (input: ItemStack, output: ItemStack) ->
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
        HiiragiRegistries.MACHINE_RECIPE.getValue(IMachineRecipe.Type.TEST)
            ?.register(hiiragiLocation("test_impl"), TestImpl)
    }

    object TestImpl : IMachineRecipe {

        override val type: IMachineRecipe.Type = IMachineRecipe.Type.TEST
        override val requiredTraits: Set<MachineTrait> = setOf()
        override val inputItems: List<List<ItemStack>> = listOf(
            listOf(ItemStack(Items.DIAMOND_PICKAXE)),
            listOf(ItemStack(Blocks.OBSIDIAN))
        )
        override val inputFluids: List<FluidStack> = listOf()
        override val outputItems: List<ItemStack> = listOf(ItemStack(Blocks.DIAMOND_BLOCK))
        override val outputFluids: List<FluidStack> = listOf()

        override fun matches(tile: TileEntityModuleMachine): Boolean {
            super.validate()
            return when {
                tile.inventoryInput.getStackInSlot(0).item != Items.DIAMOND_PICKAXE -> false
                tile.inventoryInput.getStackInSlot(1).item.getBlock() != Blocks.OBSIDIAN -> false
                !tile.inventoryOutput.insertItem(0, outputItems[0], true).isEmpty -> false
                else -> true
            }
        }

        override fun process(tile: TileEntityModuleMachine) {
            tile.inventoryInput.getStackInSlot(0).itemDamage += 1
            tile.inventoryInput.extractItem(1, 1, false)
            tile.inventoryOutput.insertItem(0, outputItems[0], false)
        }

    }

}