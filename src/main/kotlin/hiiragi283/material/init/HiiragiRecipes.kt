package hiiragi283.material.init

import hiiragi283.material.api.ingredient.FluidIngredient
import hiiragi283.material.api.ingredient.ItemIngredient
import hiiragi283.material.api.machine.IMachineRecipe
import hiiragi283.material.api.machine.MachineTrait
import hiiragi283.material.api.machine.MachineType
import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.api.part.HiiragiPart
import hiiragi283.material.api.part.getParts
import hiiragi283.material.init.materials.MaterialCommons
import hiiragi283.material.init.materials.MaterialElements
import hiiragi283.material.recipe.MachineRecipe
import hiiragi283.material.recipe.MaterialMeltingRecipe
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
        crafting()
        smelting()
    }

    private fun crafting() {
        //Recipe Modules
        CraftingBuilder(HiiragiRegistries.RECIPE_MODULE.getValue(MachineType.SMELTER)!!.itemStack())
            .setPattern("ABA", "BCB", "ABA")
            .setIngredient('A', "stone")
            .setIngredient('B', HiiragiShapes.INGOT.getOreDict(MaterialElements.IRON))
            .setIngredient('C', Blocks.FURNACE)
            .build()
        //Raw Steel Dust
        CraftingBuilder(HiiragiShapes.DUST.getItemStack(MaterialCommons.RAW_STEEL))
            .addIngredient(Items.IRON_INGOT)
            .addIngredient(Items.COAL.itemStack(meta = 1))
            .addIngredient(Items.COAL.itemStack(meta = 1))
            .addIngredient(Items.COAL.itemStack(meta = 1))
            .addIngredient(Items.COAL.itemStack(meta = 1))
            .addIngredient(Items.FLINT)
            .addIngredient(Items.BOWL)
            .build()
        CraftingBuilder(HiiragiShapes.DUST.getItemStack(MaterialCommons.RAW_STEEL), "_alt")
            .addIngredient(HiiragiShapes.DUST.getOreDict(MaterialElements.IRON))
            .addIngredient(HiiragiShapes.DUST.getOreDict(MaterialCommons.CHARCOAL))
            .addIngredient(HiiragiShapes.DUST.getOreDict(MaterialCommons.CHARCOAL))
            .addIngredient(HiiragiShapes.DUST.getOreDict(MaterialCommons.CHARCOAL))
            .addIngredient(HiiragiShapes.DUST.getOreDict(MaterialCommons.CHARCOAL))
            .build()
        CraftingBuilder(HiiragiShapes.DUST.getItemStack(MaterialCommons.RAW_STEEL), "_alt2")
            .addIngredient(HiiragiShapes.DUST.getOreDict(MaterialElements.IRON))
            .addIngredient(HiiragiShapes.DUST.getOreDict(MaterialCommons.COKE))
            .build()
        //Wooden Gear from Planks
        CraftingBuilder(HiiragiShapes.GEAR.getPart(MaterialCommons.WOOD).getItemStack(), "_alt")
            .setPattern(" A ", "A A", " A ")
            .setIngredient('A', HiiragiShapes.PLANK.getOreDict(MaterialCommons.WOOD))
            .build()
        //Stone Gear from Wooden Gear
        CraftingBuilder(HiiragiShapes.GEAR.getPart(MaterialCommons.STONE).getItemStack(), "_alt")
            .setPattern(" A ", "A A", " A ")
            .setIngredient('A', "cobblestone")
            .build()
        //1x Ore -> 2x Dust
        HiiragiRegistries.MATERIAL_INDEX.getValues()
            .filter(HiiragiShapes.DUST::isValid)
            .filter(HiiragiShapes.ORE::hasValidItem)
            .forEach { material ->
                CraftingBuilder(HiiragiShapes.DUST.getItemStack(material, 2), "_ore")
                    .addIngredient(HiiragiShapes.ORE.getOreDict(material))
                    .addIngredient(HiiragiItems.SMITHING_HAMMER)
                    .build()
            }
        //Stainless Steel Dust
        CraftingBuilder(HiiragiShapes.DUST.getItemStack(MaterialCommons.STAINLESS_STEEL, 9), "_alt")
            .addIngredient(HiiragiShapes.DUST.getOreDict(MaterialElements.IRON))
            .addIngredient(HiiragiShapes.DUST.getOreDict(MaterialElements.IRON))
            .addIngredient(HiiragiShapes.DUST.getOreDict(MaterialElements.IRON))
            .addIngredient(HiiragiShapes.DUST.getOreDict(MaterialElements.IRON))
            .addIngredient(HiiragiShapes.DUST.getOreDict(MaterialElements.IRON))
            .addIngredient(HiiragiShapes.DUST.getOreDict(MaterialElements.IRON))
            .addIngredient(HiiragiShapes.DUST.getOreDict(MaterialElements.CHROMIUM))
            .addIngredient(HiiragiShapes.DUST.getOreDict(MaterialElements.MANGANESE))
            .addIngredient(HiiragiShapes.DUST.getOreDict(MaterialElements.NICKEL))
            .build()
        //Constantan
        CraftingBuilder(HiiragiShapes.DUST.getItemStack(MaterialCommons.CONSTANTAN, 2), "_alt")
            .addIngredient(HiiragiShapes.DUST.getOreDict(MaterialElements.NICKEL))
            .addIngredient(HiiragiShapes.DUST.getOreDict(MaterialElements.COPPER))
            .build()
        //Invar
        CraftingBuilder(HiiragiShapes.DUST.getItemStack(MaterialCommons.INVAR, 2), "_alt")
            .addIngredient(HiiragiShapes.DUST.getOreDict(MaterialElements.IRON))
            .addIngredient(HiiragiShapes.DUST.getOreDict(MaterialElements.NICKEL))
            .build()
        //Brass
        CraftingBuilder(HiiragiShapes.DUST.getItemStack(MaterialCommons.BRASS, 4), "_alt")
            .addIngredient(HiiragiShapes.DUST.getOreDict(MaterialElements.COPPER))
            .addIngredient(HiiragiShapes.DUST.getOreDict(MaterialElements.COPPER))
            .addIngredient(HiiragiShapes.DUST.getOreDict(MaterialElements.COPPER))
            .addIngredient(HiiragiShapes.DUST.getOreDict(MaterialElements.ZINC))
            .build()
        //Bronze
        CraftingBuilder(HiiragiShapes.DUST.getItemStack(MaterialCommons.BRONZE, 4), "_alt")
            .addIngredient(HiiragiShapes.DUST.getOreDict(MaterialElements.COPPER))
            .addIngredient(HiiragiShapes.DUST.getOreDict(MaterialElements.COPPER))
            .addIngredient(HiiragiShapes.DUST.getOreDict(MaterialElements.COPPER))
            .addIngredient(HiiragiShapes.DUST.getOreDict(MaterialElements.TIN))
            .build()
        //Electrum
        CraftingBuilder(HiiragiShapes.DUST.getItemStack(MaterialCommons.ELECTRUM, 2), "_alt")
            .addIngredient(HiiragiShapes.DUST.getOreDict(MaterialElements.SILVER))
            .addIngredient(HiiragiShapes.DUST.getOreDict(MaterialElements.GOLD))
            .build()
    }

    private fun smelting() {
        //レジストリからかまど精錬レシピを生成
        HiiragiRegistries.MATERIAL_SMELTED.getEntries()
            .filter { (input: HiiragiMaterial, pair: Pair<HiiragiMaterial, Int>) -> input.isRegistered() && pair.first.isRegistered() }
            .filter { (input: HiiragiMaterial, pair: Pair<HiiragiMaterial, Int>) -> input.isSolid() && pair.first.isSolid() }
            .filter { HiiragiShapes.DUST.isValid(it.first) }
            .forEach { (input: HiiragiMaterial, pair: Pair<HiiragiMaterial, Int>) ->
                val (output: HiiragiMaterial, count: Int) = pair
                SmeltingBuilder.addSmelting(
                    HiiragiShapes.DUST.getItemStack(input),
                    when {
                        output.isMetal() -> HiiragiShapes.INGOT
                        output.isGem() -> HiiragiShapes.GEM
                        else -> HiiragiShapes.DUST
                    }.getItemStack(output, count)
                )
            }
    }

    fun postInit() {
        compressor()
        freezer()
        grinder()
        metalFormer()
        rockGenerator()
        smelter()
        test()
    }

    private fun compressor() {
        MachineRecipe.buildAndRegister(MachineType.COMPRESSOR, Blocks.PACKED_ICE.registryName!!) {
            inputItems.add(ItemIngredient.Blocks(Blocks.ICE, count = 3))
            outputItems.add(Blocks.PACKED_ICE.itemStack())
        }
    }

    private fun freezer() {
        MachineRecipe.buildAndRegister(MachineType.FREEZER, Blocks.ICE.registryName!!) {
            inputFluids.add(FluidIngredient.Fluids(FluidRegistry.WATER, amount = 1000))
            outputItems.add(Blocks.ICE.itemStack())
        }
        MachineRecipe.buildAndRegister(MachineType.FREEZER, Blocks.MAGMA.registryName!!) {
            inputFluids.add(FluidIngredient.Fluids(FluidRegistry.LAVA, amount = 1000))
            outputItems.add(Blocks.MAGMA.itemStack())
        }
    }

    private fun grinder() {

    }

    private fun metalFormer() {

    }

    private fun rockGenerator() {
        fun addCopy(stone: Block, meta: Int = 0, water: Int = 0, lava: Int = 0) {
            val stack = stone.itemStack(meta = meta)
            MachineRecipe.buildAndRegister(MachineType.ROCK_GENERATOR, stack.toLocation()) {
                inputItems.add(
                    ItemIngredient.Custom(
                        stacks = { listOf(stack) },
                        predicate = { stackIn: ItemStack -> stackIn.block() == stone && stack.metadata == meta },
                        process = ItemIngredient.CATALYST_PROCESS
                    )
                )
                inputFluids.add(FluidIngredient.Fluids(FluidRegistry.WATER, amount = water))
                inputFluids.add(FluidIngredient.Fluids(FluidRegistry.LAVA, amount = lava))
                outputItems.add(stack)
            }
        }
        addCopy(Blocks.STONE)
        addCopy(Blocks.STONE, 1)
        addCopy(Blocks.STONE, 3)
        addCopy(Blocks.STONE, 5)
        addCopy(Blocks.COBBLESTONE)
        addCopy(Blocks.OBSIDIAN, water = 1000, lava = 1000)
        addCopy(Blocks.NETHERRACK, lava = 1)
        addCopy(Blocks.END_STONE, water = 1)
    }

    private fun smelter() {
        //かまどレシピの自動インポート
        //インプットがDUST, アウトプットがINGOTのものは除外される
        FurnaceRecipes.instance().smeltingList.toList()
            .filter { (input: ItemStack, output: ItemStack) ->
                HiiragiShapes.DUST !in input.getParts().map(HiiragiPart::shape)
                        && HiiragiShapes.INGOT !in output.getParts().map(HiiragiPart::shape)
            }
            .forEach { (input: ItemStack, output: ItemStack) ->
                MachineRecipe.buildAndRegister(
                    MachineType.SMELTER,
                    input.toLocation().append("_furnace")
                ) {
                    inputItems.add(ItemIngredient.Stacks(input, count = input.count))
                    outputItems.add(output)
                }
        }
        //合金レシピ
        //Stainless Steel
        MachineRecipe.buildAndRegister(MachineType.SMELTER, hiiragiLocation("stainless_steel_alloy")) {
            inputItems.add(ItemIngredient.Parts(HiiragiShapes.DUST, MaterialElements.IRON, 6))
            inputItems.add(ItemIngredient.Parts(HiiragiShapes.DUST, MaterialElements.CHROMIUM))
            inputItems.add(ItemIngredient.Parts(HiiragiShapes.DUST, MaterialElements.MANGANESE))
            inputItems.add(ItemIngredient.Parts(HiiragiShapes.DUST, MaterialElements.NICKEL))
            outputItems.add(HiiragiShapes.INGOT.getPart(MaterialCommons.STAINLESS_STEEL).getItemStack(9))
        }
        MachineRecipe.buildAndRegister(MachineType.SMELTER, hiiragiLocation("stainless_steel_alloy1")) {
            inputItems.add(ItemIngredient.Parts(HiiragiShapes.INGOT, MaterialElements.IRON, 6))
            inputItems.add(ItemIngredient.Parts(HiiragiShapes.INGOT, MaterialElements.CHROMIUM))
            inputItems.add(ItemIngredient.Parts(HiiragiShapes.INGOT, MaterialElements.MANGANESE))
            inputItems.add(ItemIngredient.Parts(HiiragiShapes.INGOT, MaterialElements.NICKEL))
            outputItems.add(HiiragiShapes.INGOT.getItemStack(MaterialCommons.STAINLESS_STEEL, 9))
        }
        //Steel
        MachineRecipe.buildAndRegister(MachineType.SMELTER, hiiragiLocation("steel_alloy")) {
            inputItems.add(ItemIngredient.Parts(HiiragiShapes.DUST, MaterialElements.IRON))
            inputItems.add(ItemIngredient.Parts(HiiragiShapes.DUST, MaterialCommons.CHARCOAL, 4))
            outputItems.add(HiiragiShapes.INGOT.getItemStack(MaterialCommons.STEEL))
        }
        MachineRecipe.buildAndRegister(MachineType.SMELTER, hiiragiLocation("steel_alloy1")) {
            inputItems.add(ItemIngredient.Parts(HiiragiShapes.INGOT, MaterialElements.IRON))
            inputItems.add(ItemIngredient.Parts(HiiragiShapes.DUST, MaterialCommons.CHARCOAL, 4))
            outputItems.add(HiiragiShapes.INGOT.getItemStack(MaterialCommons.STEEL))
        }
        MachineRecipe.buildAndRegister(MachineType.SMELTER, hiiragiLocation("steel_alloy_coke")) {
            inputItems.add(ItemIngredient.Parts(HiiragiShapes.DUST, MaterialElements.IRON))
            inputItems.add(ItemIngredient.Parts(HiiragiShapes.DUST, MaterialCommons.COKE))
            outputItems.add(HiiragiShapes.INGOT.getItemStack(MaterialCommons.STEEL))
        }
        MachineRecipe.buildAndRegister(MachineType.SMELTER, hiiragiLocation("steel_alloy1_coke")) {
            inputItems.add(ItemIngredient.Parts(HiiragiShapes.INGOT, MaterialElements.IRON))
            inputItems.add(ItemIngredient.Parts(HiiragiShapes.DUST, MaterialCommons.COKE))
            outputItems.add(HiiragiShapes.INGOT.getItemStack(MaterialCommons.STEEL))
        }
        //Constantan
        MachineRecipe.buildAndRegister(MachineType.SMELTER, hiiragiLocation("constantan_alloy")) {
            inputItems.add(ItemIngredient.Parts(HiiragiShapes.DUST, MaterialElements.NICKEL))
            inputItems.add(ItemIngredient.Parts(HiiragiShapes.DUST, MaterialElements.COPPER))
            outputItems.add(HiiragiShapes.INGOT.getItemStack(MaterialCommons.CONSTANTAN, 2))
        }
        MachineRecipe.buildAndRegister(MachineType.SMELTER, hiiragiLocation("constantan_alloy1")) {
            inputItems.add(ItemIngredient.Parts(HiiragiShapes.INGOT, MaterialElements.NICKEL))
            inputItems.add(ItemIngredient.Parts(HiiragiShapes.INGOT, MaterialElements.COPPER))
            outputItems.add(HiiragiShapes.INGOT.getItemStack(MaterialCommons.CONSTANTAN, 2))
        }
        //Invar
        MachineRecipe.buildAndRegister(MachineType.SMELTER, hiiragiLocation("invar_alloy")) {
            inputItems.add(ItemIngredient.Parts(HiiragiShapes.DUST, MaterialElements.NICKEL, 2))
            inputItems.add(ItemIngredient.Parts(HiiragiShapes.DUST, MaterialElements.IRON))
            outputItems.add(HiiragiShapes.INGOT.getItemStack(MaterialCommons.INVAR, 2))
        }
        MachineRecipe.buildAndRegister(MachineType.SMELTER, hiiragiLocation("invar_alloy1")) {
            inputItems.add(ItemIngredient.Parts(HiiragiShapes.INGOT, MaterialElements.NICKEL, 2))
            inputItems.add(ItemIngredient.Parts(HiiragiShapes.INGOT, MaterialElements.IRON))
            outputItems.add(HiiragiShapes.INGOT.getItemStack(MaterialCommons.INVAR, 2))
        }
        //Brass
        MachineRecipe.buildAndRegister(MachineType.SMELTER, hiiragiLocation("brass_alloy")) {
            inputItems.add(ItemIngredient.Parts(HiiragiShapes.DUST, MaterialElements.COPPER, 3))
            inputItems.add(ItemIngredient.Parts(HiiragiShapes.DUST, MaterialElements.ZINC))
            outputItems.add(HiiragiShapes.INGOT.getItemStack(MaterialCommons.BRASS, 4))
        }
        MachineRecipe.buildAndRegister(MachineType.SMELTER, hiiragiLocation("brass_alloy1")) {
            inputItems.add(ItemIngredient.Parts(HiiragiShapes.INGOT, MaterialElements.COPPER, 3))
            inputItems.add(ItemIngredient.Parts(HiiragiShapes.INGOT, MaterialElements.ZINC))
            outputItems.add(HiiragiShapes.INGOT.getItemStack(MaterialCommons.BRASS, 4))
        }
        //Bronze
        MachineRecipe.buildAndRegister(MachineType.SMELTER, hiiragiLocation("bronze_alloy")) {
            inputItems.add(ItemIngredient.Parts(HiiragiShapes.DUST, MaterialElements.COPPER, 3))
            inputItems.add(ItemIngredient.Parts(HiiragiShapes.DUST, MaterialElements.TIN))
            outputItems.add(HiiragiShapes.INGOT.getItemStack(MaterialCommons.BRONZE, 4))
        }
        MachineRecipe.buildAndRegister(MachineType.SMELTER, hiiragiLocation("bronze_alloy1")) {
            inputItems.add(ItemIngredient.Parts(HiiragiShapes.INGOT, MaterialElements.COPPER, 3))
            inputItems.add(ItemIngredient.Parts(HiiragiShapes.INGOT, MaterialElements.TIN))
            outputItems.add(HiiragiShapes.INGOT.getItemStack(MaterialCommons.BRONZE, 4))
        }
        //Electrum
        MachineRecipe.buildAndRegister(MachineType.SMELTER, hiiragiLocation("electrum_alloy")) {
            inputItems.add(ItemIngredient.Parts(HiiragiShapes.DUST, MaterialElements.SILVER))
            inputItems.add(ItemIngredient.Parts(HiiragiShapes.DUST, MaterialElements.GOLD))
            outputItems.add(HiiragiShapes.INGOT.getItemStack(MaterialCommons.ELECTRUM, 2))
        }
        MachineRecipe.buildAndRegister(MachineType.SMELTER, hiiragiLocation("electrum_alloy1")) {
            inputItems.add(ItemIngredient.Parts(HiiragiShapes.INGOT, MaterialElements.SILVER))
            inputItems.add(ItemIngredient.Parts(HiiragiShapes.INGOT, MaterialElements.GOLD))
            outputItems.add(HiiragiShapes.INGOT.getItemStack(MaterialCommons.ELECTRUM, 2))
        }
        //Tungsten Steel
        MachineRecipe.buildAndRegister(MachineType.SMELTER, hiiragiLocation("tungsten_steel_alloy")) {
            inputItems.add(ItemIngredient.Parts(HiiragiShapes.DUST, MaterialElements.TUNGSTEN))
            inputItems.add(ItemIngredient.Parts(HiiragiShapes.DUST, MaterialCommons.STEEL))
            outputItems.add(HiiragiShapes.INGOT.getItemStack(MaterialCommons.TUNGSTEN_STEEL, 2))
        }
        MachineRecipe.buildAndRegister(MachineType.SMELTER, hiiragiLocation("tungsten_steel_alloy1")) {
            inputItems.add(ItemIngredient.Parts(HiiragiShapes.INGOT, MaterialElements.TUNGSTEN))
            inputItems.add(ItemIngredient.Parts(HiiragiShapes.INGOT, MaterialCommons.STEEL))
            outputItems.add(HiiragiShapes.INGOT.getItemStack(MaterialCommons.TUNGSTEN_STEEL, 2))
        }
        //金属の溶融レシピ
        HiiragiRegistries.MATERIAL_INDEX.getValues()
            .filter(HiiragiMaterial::isSolid)
            .filter(HiiragiMaterial::hasFluid)
            .forEach { material: HiiragiMaterial ->
                IMachineRecipe.register(
                    hiiragiLocation(material.name),
                    MaterialMeltingRecipe(material)
                )
            }
    }

    private fun test() {
        MachineRecipe.buildAndRegister(MachineType.TEST, hiiragiLocation("smelt_test")) {
            inputItems.add(ItemIngredient.Blocks(Blocks.COBBLESTONE))
            outputItems.add(Blocks.STONE.itemStack())
        }
        MachineRecipe.buildAndRegister(MachineType.TEST, hiiragiLocation("almighty")) {
            inputItems.addAll(
                listOf(
                    ItemIngredient.Items(Items.COAL),
                    ItemIngredient.Stacks(Items.COAL.itemStack(meta = 1)),
                    ItemIngredient.Blocks(Blocks.BEACON),
                    ItemIngredient.Blocks(Blocks.DIAMOND_BLOCK),
                    ItemIngredient.OreDicts("cobblestone"),
                    ItemIngredient.Parts(HiiragiShapes.INGOT, MaterialCommons.STEEL)
                )
            )
            inputFluids.addAll(
                listOf(
                    FluidIngredient.Fluids(FluidRegistry.WATER, amount = 1000),
                    FluidIngredient.Fluids(FluidRegistry.LAVA, amount = 500),
                    FluidIngredient.Materials(MaterialCommons.BRONZE, amount = 144 * 4)
                )
            )
            outputItems.add(Blocks.DIRT.itemStack())
            outputFluids.add(MaterialElements.HYDROGEN.getFluidStack(4000)!!)
        }
        IMachineRecipe.register(hiiragiLocation("test_impl"), TestImpl)
    }


    object TestImpl : IMachineRecipe {

        //    Input    //

        override fun getInputItems(): List<ItemIngredient> = listOf(
            ItemIngredient.Custom(
                stacks = { listOf(ItemStack(Items.DIAMOND_PICKAXE)) },
                predicate = { stack: ItemStack -> stack.item == Items.DIAMOND_PICKAXE },
                process = ItemIngredient.TOOL_PROCESS
            ),
            ItemIngredient.Blocks(Blocks.OBSIDIAN)
        )

        override fun getInputFluids(): List<FluidIngredient> = listOf()

        override fun getRequiredTraits(): Set<MachineTrait> = setOf()

        override fun getRequiredType(): MachineType = MachineType.TEST

        override fun getOutputItems(): List<ItemStack> = listOf(Blocks.DIAMOND_BLOCK.itemStack())

        override fun getOutputFluids(): List<FluidStack> = listOf()

    }

}