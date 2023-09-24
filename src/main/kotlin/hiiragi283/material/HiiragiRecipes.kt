package hiiragi283.material

import hiiragi283.material.api.machine.IMachineProperty
import hiiragi283.material.api.machine.MachineTrait
import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.api.material.MaterialCommon
import hiiragi283.material.api.material.MaterialElements
import hiiragi283.material.api.part.HiiragiPart
import hiiragi283.material.api.part.getParts
import hiiragi283.material.api.recipe.IMachineRecipe
import hiiragi283.material.api.registry.HiiragiRegistries
import hiiragi283.material.api.shape.HiiragiShape
import hiiragi283.material.api.shape.HiiragiShapes
import hiiragi283.material.recipe.MachineRecipe
import hiiragi283.material.recipe.MaterialMeltingRecipe
import hiiragi283.material.util.*
import net.minecraft.block.Block
import net.minecraft.init.Blocks
import net.minecraft.init.Items
import net.minecraft.item.ItemStack
import net.minecraft.item.crafting.FurnaceRecipes
import net.minecraftforge.common.crafting.CraftingHelper
import net.minecraftforge.fluids.FluidRegistry
import net.minecraftforge.fluids.FluidStack

object HiiragiRecipes {

    fun init() {
        crafting()
        smelting()
    }

    private fun crafting() {
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
        //Raw Steel Dust
        CraftingBuilder(HiiragiItems.MATERIAL_DUST.getItemStack(MaterialCommon.RAW_STEEL))
            .setPattern("ABB", "BB ")
            .setIngredient('A', HiiragiShapes.DUST.getOreDict(MaterialElements.IRON))
            .setIngredient('B', HiiragiShapes.DUST.getOreDict(MaterialCommon.CHARCOAL))
            .build()
        CraftingBuilder(HiiragiItems.MATERIAL_DUST.getItemStack(MaterialCommon.RAW_STEEL), "_alt")
            .addIngredient(CraftingHelper.getIngredient(HiiragiShapes.DUST.getOreDict(MaterialElements.IRON)))
            .addIngredient(CraftingHelper.getIngredient(HiiragiShapes.DUST.getOreDict(MaterialCommon.COKE)))
            .build()
    }

    private fun smelting() {
        SmeltingBuilder.addSmelting(
            HiiragiItems.MATERIAL_DUST.getItemStack(MaterialCommon.RAW_STEEL),
            HiiragiItems.MATERIAL_INGOT.getItemStack(MaterialCommon.STEEL)
        )
    }

    fun postInit() {
        compressor()
        crusher()
        extractor()
        freezer()
        melter()
        smelter()
        rockGenerator()
        test()
    }

    private fun compressor() {
        MachineRecipe.buildAndRegister(IMachineRecipe.Type.COMPRESSOR, Blocks.PACKED_ICE.registryName!!) {
            inputItems.add(HiiragiIngredient.Blocks(Blocks.ICE, count = 3))
            outputItems.add(ItemStack(Blocks.PACKED_ICE))
        }
    }

    private fun crusher() {
        //素材アイテム -> Dust
        HiiragiRegistries.SHAPE.getValues()
            .filter { shape: HiiragiShape -> shape.scale >= 144 }
            .forEach { shape: HiiragiShape ->
                HiiragiRegistries.MATERIAL_INDEX.getValues()
                    .map(shape::getPart)
                    .filter(HiiragiPart::isInOreDictionary)
                    .forEach { part ->
                        MachineRecipe.buildAndRegister(IMachineRecipe.Type.CRUSHER, hiiragiLocation(part.toString())) {
                            inputItems.add(HiiragiIngredient.Parts(part))
                            outputItems.add(HiiragiItems.MATERIAL_DUST.getItemStack(part))
                        }
                    }
            }
    }

    private fun extractor() {
        fun addPrimitive(stone: Block, meta: Int = 0, materials: List<HiiragiMaterial>) {
            val stack = ItemStack(stone, 8, meta)
            MachineRecipe.buildAndRegister(
                IMachineRecipe.Type.EXTRACTOR,
                stack.toLocation().append("_primitive")
            ) {
                traits.add(MachineTrait.PRIMITIVE)
                inputItems.add(HiiragiIngredient.Stacks(stack, count = 8))
                materials.getOrNull(0)?.let { outputItems.add(HiiragiItems.MATERIAL_DUST.getItemStack(it, 4)) }
                materials.getOrNull(1)?.let { outputItems.add(HiiragiItems.MATERIAL_DUST.getItemStack(it, 2)) }
                materials.getOrNull(2)?.let { outputItems.add(HiiragiItems.MATERIAL_DUST.getItemStack(it, 1)) }
            }

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
        MachineRecipe.buildAndRegister(IMachineRecipe.Type.FREEZER, Blocks.ICE.registryName!!) {
            inputFluids.add(FluidIngredient.Fluids(FluidRegistry.WATER, amount = 1000))
            outputItems.add(ItemStack(Blocks.ICE))
        }
        MachineRecipe.buildAndRegister(IMachineRecipe.Type.FREEZER, Blocks.MAGMA.registryName!!) {
            inputFluids.add(FluidIngredient.Fluids(FluidRegistry.LAVA, amount = 1000))
            outputItems.add(ItemStack(Blocks.MAGMA))
        }
    }

    private fun melter() {
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

    private fun rockGenerator() {
        fun addCopy(stone: Block, meta: Int = 0, water: Int = 0, lava: Int = 0) {
            val stack = ItemStack(stone, 1, meta)
            MachineRecipe.buildAndRegister(IMachineRecipe.Type.ROCK_GENERATOR, stack.toLocation()) {
                inputItems.add(
                    HiiragiIngredient.Custom(
                        stacks = { listOf(stack) },
                        predicate = { stackIn: ItemStack -> stackIn.getBlock() == stone },
                        process = HiiragiIngredient.CATALYST_PROCESS
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
                HiiragiShapes.DUST !in input.getParts()
                    .map(HiiragiPart::shape) && HiiragiShapes.INGOT !in output.getParts().map(HiiragiPart::shape)
            }
            .forEach { (input: ItemStack, output: ItemStack) ->
                MachineRecipe.buildAndRegister(
                    IMachineRecipe.Type.SMELTER,
                    input.toLocation().append("_furnace")
                ) {
                    inputItems.add(HiiragiIngredient.Stacks(input, count = input.count))
                    outputItems.add(output)
                }
        }
        //金属の精錬レシピ
        HiiragiRegistries.MATERIAL_INDEX.getValues()
            .filter(HiiragiShapes.DUST::isValid)
            .filter(HiiragiShapes.INGOT::isValid)
            .forEach { material: HiiragiMaterial ->
                MachineRecipe.buildAndRegister(
                    IMachineRecipe.Type.SMELTER,
                    hiiragiLocation("${material.name}_ingot")
                ) {
                    inputItems.add(HiiragiIngredient.Parts(HiiragiShapes.DUST, material))
                    outputItems.add(HiiragiItems.MATERIAL_INGOT.getItemStack(material))
                }
            }
        //合金レシピ
        //Stainless Steel
        MachineRecipe.buildAndRegister(IMachineRecipe.Type.SMELTER, hiiragiLocation("stainless_steel_alloy")) {
            inputItems.add(HiiragiIngredient.Parts(HiiragiShapes.DUST, MaterialElements.IRON, 6))
            inputItems.add(HiiragiIngredient.Parts(HiiragiShapes.DUST, MaterialElements.CHROMIUM))
            inputItems.add(HiiragiIngredient.Parts(HiiragiShapes.DUST, MaterialElements.MANGANESE))
            inputItems.add(HiiragiIngredient.Parts(HiiragiShapes.DUST, MaterialElements.NICKEL))
            outputItems.add(HiiragiItems.MATERIAL_INGOT.getItemStack(MaterialCommon.STAINLESS_STEEL, 9))
        }
        MachineRecipe.buildAndRegister(IMachineRecipe.Type.SMELTER, hiiragiLocation("stainless_steel_alloy1")) {
            inputItems.add(HiiragiIngredient.Parts(HiiragiShapes.INGOT, MaterialElements.IRON, 6))
            inputItems.add(HiiragiIngredient.Parts(HiiragiShapes.INGOT, MaterialElements.CHROMIUM))
            inputItems.add(HiiragiIngredient.Parts(HiiragiShapes.INGOT, MaterialElements.MANGANESE))
            inputItems.add(HiiragiIngredient.Parts(HiiragiShapes.INGOT, MaterialElements.NICKEL))
            outputItems.add(HiiragiItems.MATERIAL_INGOT.getItemStack(MaterialCommon.STAINLESS_STEEL, 9))
        }
        //Steel
        MachineRecipe.buildAndRegister(IMachineRecipe.Type.SMELTER, hiiragiLocation("steel_alloy")) {
            inputItems.add(HiiragiIngredient.Parts(HiiragiShapes.DUST, MaterialElements.IRON))
            inputItems.add(HiiragiIngredient.Parts(HiiragiShapes.DUST, MaterialCommon.CHARCOAL, 4))
            outputItems.add(HiiragiItems.MATERIAL_INGOT.getItemStack(MaterialCommon.STEEL))
        }
        MachineRecipe.buildAndRegister(IMachineRecipe.Type.SMELTER, hiiragiLocation("steel_alloy1")) {
            inputItems.add(HiiragiIngredient.Parts(HiiragiShapes.INGOT, MaterialElements.IRON))
            inputItems.add(HiiragiIngredient.Parts(HiiragiShapes.DUST, MaterialCommon.CHARCOAL, 4))
            outputItems.add(HiiragiItems.MATERIAL_INGOT.getItemStack(MaterialCommon.STEEL))
        }
        MachineRecipe.buildAndRegister(IMachineRecipe.Type.SMELTER, hiiragiLocation("steel_alloy_coke")) {
            inputItems.add(HiiragiIngredient.Parts(HiiragiShapes.DUST, MaterialElements.IRON))
            inputItems.add(HiiragiIngredient.Parts(HiiragiShapes.DUST, MaterialCommon.COKE))
            outputItems.add(HiiragiItems.MATERIAL_INGOT.getItemStack(MaterialCommon.STEEL))
        }
        MachineRecipe.buildAndRegister(IMachineRecipe.Type.SMELTER, hiiragiLocation("steel_alloy1_coke")) {
            inputItems.add(HiiragiIngredient.Parts(HiiragiShapes.INGOT, MaterialElements.IRON))
            inputItems.add(HiiragiIngredient.Parts(HiiragiShapes.DUST, MaterialCommon.COKE))
            outputItems.add(HiiragiItems.MATERIAL_INGOT.getItemStack(MaterialCommon.STEEL))
        }
        //Constantan
        MachineRecipe.buildAndRegister(IMachineRecipe.Type.SMELTER, hiiragiLocation("constantan_alloy")) {
            inputItems.add(HiiragiIngredient.Parts(HiiragiShapes.DUST, MaterialElements.NICKEL))
            inputItems.add(HiiragiIngredient.Parts(HiiragiShapes.DUST, MaterialElements.COPPER))
            outputItems.add(HiiragiItems.MATERIAL_INGOT.getItemStack(MaterialCommon.CONSTANTAN, 2))
        }
        MachineRecipe.buildAndRegister(IMachineRecipe.Type.SMELTER, hiiragiLocation("constantan_alloy1")) {
            inputItems.add(HiiragiIngredient.Parts(HiiragiShapes.INGOT, MaterialElements.NICKEL))
            inputItems.add(HiiragiIngredient.Parts(HiiragiShapes.INGOT, MaterialElements.COPPER))
            outputItems.add(HiiragiItems.MATERIAL_INGOT.getItemStack(MaterialCommon.CONSTANTAN, 2))
        }
        //Invar
        MachineRecipe.buildAndRegister(IMachineRecipe.Type.SMELTER, hiiragiLocation("invar_alloy")) {
            inputItems.add(HiiragiIngredient.Parts(HiiragiShapes.DUST, MaterialElements.NICKEL, 2))
            inputItems.add(HiiragiIngredient.Parts(HiiragiShapes.DUST, MaterialElements.IRON))
            outputItems.add(HiiragiItems.MATERIAL_INGOT.getItemStack(MaterialCommon.INVAR, 2))
        }
        MachineRecipe.buildAndRegister(IMachineRecipe.Type.SMELTER, hiiragiLocation("invar_alloy1")) {
            inputItems.add(HiiragiIngredient.Parts(HiiragiShapes.INGOT, MaterialElements.NICKEL, 2))
            inputItems.add(HiiragiIngredient.Parts(HiiragiShapes.INGOT, MaterialElements.IRON))
            outputItems.add(HiiragiItems.MATERIAL_INGOT.getItemStack(MaterialCommon.INVAR, 2))
        }
        //Brass
        MachineRecipe.buildAndRegister(IMachineRecipe.Type.SMELTER, hiiragiLocation("brass_alloy")) {
            inputItems.add(HiiragiIngredient.Parts(HiiragiShapes.DUST, MaterialElements.COPPER, 3))
            inputItems.add(HiiragiIngredient.Parts(HiiragiShapes.DUST, MaterialElements.ZINC))
            outputItems.add(HiiragiItems.MATERIAL_INGOT.getItemStack(MaterialCommon.BRASS, 4))
        }
        MachineRecipe.buildAndRegister(IMachineRecipe.Type.SMELTER, hiiragiLocation("brass_alloy1")) {
            inputItems.add(HiiragiIngredient.Parts(HiiragiShapes.INGOT, MaterialElements.COPPER, 3))
            inputItems.add(HiiragiIngredient.Parts(HiiragiShapes.INGOT, MaterialElements.ZINC))
            outputItems.add(HiiragiItems.MATERIAL_INGOT.getItemStack(MaterialCommon.BRASS, 4))
        }
        //Bronze
        MachineRecipe.buildAndRegister(IMachineRecipe.Type.SMELTER, hiiragiLocation("bronze_alloy")) {
            inputItems.add(HiiragiIngredient.Parts(HiiragiShapes.DUST, MaterialElements.COPPER, 3))
            inputItems.add(HiiragiIngredient.Parts(HiiragiShapes.DUST, MaterialElements.TIN))
            outputItems.add(HiiragiItems.MATERIAL_INGOT.getItemStack(MaterialCommon.BRONZE, 4))
        }
        MachineRecipe.buildAndRegister(IMachineRecipe.Type.SMELTER, hiiragiLocation("bronze_alloy1")) {
            inputItems.add(HiiragiIngredient.Parts(HiiragiShapes.INGOT, MaterialElements.COPPER, 3))
            inputItems.add(HiiragiIngredient.Parts(HiiragiShapes.INGOT, MaterialElements.TIN))
            outputItems.add(HiiragiItems.MATERIAL_INGOT.getItemStack(MaterialCommon.BRONZE, 4))
        }
        //Electrum
        MachineRecipe.buildAndRegister(IMachineRecipe.Type.SMELTER, hiiragiLocation("electrum_alloy")) {
            inputItems.add(HiiragiIngredient.Parts(HiiragiShapes.DUST, MaterialElements.SILVER))
            inputItems.add(HiiragiIngredient.Parts(HiiragiShapes.DUST, MaterialElements.GOLD))
            outputItems.add(HiiragiItems.MATERIAL_INGOT.getItemStack(MaterialCommon.ELECTRUM, 2))
        }
        MachineRecipe.buildAndRegister(IMachineRecipe.Type.SMELTER, hiiragiLocation("electrum_alloy1")) {
            inputItems.add(HiiragiIngredient.Parts(HiiragiShapes.INGOT, MaterialElements.SILVER))
            inputItems.add(HiiragiIngredient.Parts(HiiragiShapes.INGOT, MaterialElements.GOLD))
            outputItems.add(HiiragiItems.MATERIAL_INGOT.getItemStack(MaterialCommon.ELECTRUM, 2))
        }
        //Tungsten Steel
        MachineRecipe.buildAndRegister(IMachineRecipe.Type.SMELTER, hiiragiLocation("tungsten_steel_alloy")) {
            inputItems.add(HiiragiIngredient.Parts(HiiragiShapes.DUST, MaterialElements.TUNGSTEN))
            inputItems.add(HiiragiIngredient.Parts(HiiragiShapes.DUST, MaterialCommon.STEEL))
            outputItems.add(HiiragiItems.MATERIAL_INGOT.getItemStack(MaterialCommon.TUNGSTEN_STEEL, 2))
        }
        MachineRecipe.buildAndRegister(IMachineRecipe.Type.SMELTER, hiiragiLocation("tungsten_steel_alloy1")) {
            inputItems.add(HiiragiIngredient.Parts(HiiragiShapes.INGOT, MaterialElements.TUNGSTEN))
            inputItems.add(HiiragiIngredient.Parts(HiiragiShapes.INGOT, MaterialCommon.STEEL))
            outputItems.add(HiiragiItems.MATERIAL_INGOT.getItemStack(MaterialCommon.TUNGSTEN_STEEL, 2))
        }
    }

    private fun test() {
        MachineRecipe.buildAndRegister(IMachineRecipe.Type.TEST, hiiragiLocation("smelt_test")) {
            inputItems.add(HiiragiIngredient.Blocks(Blocks.COBBLESTONE))
            outputItems.add(ItemStack(Blocks.STONE))
        }
        MachineRecipe.buildAndRegister(IMachineRecipe.Type.TEST, hiiragiLocation("almighty")) {
            inputItems.addAll(
                listOf(
                    HiiragiIngredient.Items(Items.COAL),
                    HiiragiIngredient.Stacks(ItemStack(Items.COAL, 1, 1)),
                    HiiragiIngredient.Blocks(Blocks.BEACON),
                    HiiragiIngredient.Blocks(Blocks.DIAMOND_BLOCK),
                    HiiragiIngredient.OreDicts("cobblestone"),
                    HiiragiIngredient.Parts(HiiragiShapes.INGOT, MaterialCommon.STEEL)
                )
            )
            inputFluids.addAll(
                listOf(
                    FluidIngredient.Fluids(FluidRegistry.WATER, amount = 1000),
                    FluidIngredient.Fluids(FluidRegistry.LAVA, amount = 500),
                    FluidIngredient.Materials(MaterialCommon.BRONZE, amount = 144 * 4)
                )
            )
            outputItems.add(ItemStack(Blocks.DIRT))
            outputFluids.add(MaterialElements.HYDROGEN.getFluidStack(4000)!!)
        }
        IMachineRecipe.register(hiiragiLocation("test_impl"), TestImpl)
    }


    object TestImpl : IMachineRecipe {

        //    Input    //

        override fun getInputItems(): List<HiiragiIngredient> = listOf(
            HiiragiIngredient.Custom(
                stacks = { listOf(ItemStack(Items.DIAMOND_PICKAXE)) },
                predicate = { stack: ItemStack -> stack.item == Items.DIAMOND_PICKAXE },
                process = HiiragiIngredient.TOOL_PROCESS
            ),
            HiiragiIngredient.Blocks(Blocks.OBSIDIAN)
        )

        override fun getInputFluids(): List<FluidIngredient> = listOf()

        override fun getRequiredTraits(): Set<MachineTrait> = setOf()

        override fun getRequiredType(): IMachineRecipe.Type = IMachineRecipe.Type.TEST

        override fun getOutputItems(): List<ItemStack> = listOf(ItemStack(Blocks.DIAMOND_BLOCK))

        override fun getOutputFluids(): List<FluidStack> = listOf()

    }

}