package hiiragi283.material

import hiiragi283.material.api.material.MaterialCommon
import hiiragi283.material.api.material.MaterialElements
import hiiragi283.material.api.recipe.IMachineRecipe
import hiiragi283.material.api.recipe.MachineRecipe
import hiiragi283.material.api.shape.HiiragiShapes
import hiiragi283.material.util.hiiragiLocation
import hiiragi283.material.util.toLocation
import net.minecraft.init.Blocks
import net.minecraft.init.Items
import net.minecraft.item.ItemStack
import net.minecraft.item.crafting.FurnaceRecipes
import net.minecraftforge.fluids.FluidRegistry

object HiiragiRecipes {

    fun init() {
        smelter()
        test()
    }

    private fun smelter() {
        //かまどレシピの自動インポート
        FurnaceRecipes.instance().smeltingList.forEach { (input: ItemStack, output: ItemStack) ->
            MachineRecipe.Builder(IMachineRecipe.Type.SMELTER, input.toLocation())
                .addInput(input)
                .addOutput(output)
                .buildAndRegister()
        }
        //合金レシピ
        MachineRecipe.Builder(IMachineRecipe.Type.SMELTER, hiiragiLocation("stainless_steel_dust"))
            .addInputPart(HiiragiShapes.DUST, MaterialElements.IRON, 6)
            .addInputPart(HiiragiShapes.DUST, MaterialElements.CHROMIUM)
            .addInputPart(HiiragiShapes.DUST, MaterialElements.MANGANESE)
            .addInputPart(HiiragiShapes.DUST, MaterialElements.NICKEL)
            .addOutputPart(HiiragiShapes.INGOT, MaterialCommon.STAINLESS_STEEL, 9)
            .buildAndRegister()
        MachineRecipe.Builder(IMachineRecipe.Type.SMELTER, hiiragiLocation("stainless_steel_ingot"))
            .addInputPart(HiiragiShapes.INGOT, MaterialElements.IRON, 6)
            .addInputPart(HiiragiShapes.INGOT, MaterialElements.CHROMIUM)
            .addInputPart(HiiragiShapes.INGOT, MaterialElements.MANGANESE)
            .addInputPart(HiiragiShapes.INGOT, MaterialElements.NICKEL)
            .addOutputPart(HiiragiShapes.INGOT, MaterialCommon.STAINLESS_STEEL, 9)
            .buildAndRegister()

        MachineRecipe.Builder(IMachineRecipe.Type.SMELTER, hiiragiLocation("steel_dust"))
            .addInputPart(HiiragiShapes.DUST, MaterialElements.IRON)
            .addInputPart(HiiragiShapes.DUST, MaterialCommon.CHARCOAL, 4)
            .addOutputPart(HiiragiShapes.INGOT, MaterialCommon.STEEL)
            .buildAndRegister()
        MachineRecipe.Builder(IMachineRecipe.Type.SMELTER, hiiragiLocation("steel_ingot"))
            .addInputPart(HiiragiShapes.INGOT, MaterialElements.IRON)
            .addInputPart(HiiragiShapes.DUST, MaterialCommon.CHARCOAL, 4)
            .addOutputPart(HiiragiShapes.INGOT, MaterialCommon.STEEL)
            .buildAndRegister()
        MachineRecipe.Builder(IMachineRecipe.Type.SMELTER, hiiragiLocation("steel_dust_coke"))
            .addInputPart(HiiragiShapes.DUST, MaterialElements.IRON)
            .addInputPart(HiiragiShapes.DUST, MaterialCommon.COKE)
            .addOutputPart(HiiragiShapes.INGOT, MaterialCommon.STEEL)
            .buildAndRegister()
        MachineRecipe.Builder(IMachineRecipe.Type.SMELTER, hiiragiLocation("steel_ingot_coke"))
            .addInputPart(HiiragiShapes.INGOT, MaterialElements.IRON)
            .addInputPart(HiiragiShapes.DUST, MaterialCommon.COKE)
            .addOutputPart(HiiragiShapes.INGOT, MaterialCommon.STEEL)
            .buildAndRegister()

        MachineRecipe.Builder(IMachineRecipe.Type.SMELTER, hiiragiLocation("constantan_dust"))
            .addInputPart(HiiragiShapes.DUST, MaterialElements.NICKEL)
            .addInputPart(HiiragiShapes.DUST, MaterialElements.COPPER)
            .addOutputPart(HiiragiShapes.INGOT, MaterialCommon.CONSTANTAN, 2)
            .buildAndRegister()
        MachineRecipe.Builder(IMachineRecipe.Type.SMELTER, hiiragiLocation("constantan_ingot"))
            .addInputPart(HiiragiShapes.INGOT, MaterialElements.NICKEL)
            .addInputPart(HiiragiShapes.INGOT, MaterialElements.COPPER)
            .addOutputPart(HiiragiShapes.INGOT, MaterialCommon.CONSTANTAN, 2)
            .buildAndRegister()

        MachineRecipe.Builder(IMachineRecipe.Type.SMELTER, hiiragiLocation("invar_dust"))
            .addInputPart(HiiragiShapes.DUST, MaterialElements.NICKEL, 2)
            .addInputPart(HiiragiShapes.DUST, MaterialElements.IRON)
            .addOutputPart(HiiragiShapes.INGOT, MaterialCommon.INVAR, 2)
            .buildAndRegister()
        MachineRecipe.Builder(IMachineRecipe.Type.SMELTER, hiiragiLocation("invar_ingot"))
            .addInputPart(HiiragiShapes.INGOT, MaterialElements.NICKEL, 2)
            .addInputPart(HiiragiShapes.INGOT, MaterialElements.IRON)
            .addOutputPart(HiiragiShapes.INGOT, MaterialCommon.INVAR, 2)
            .buildAndRegister()

        MachineRecipe.Builder(IMachineRecipe.Type.SMELTER, hiiragiLocation("brass_dust"))
            .addInputPart(HiiragiShapes.DUST, MaterialElements.COPPER, 3)
            .addInputPart(HiiragiShapes.DUST, MaterialElements.ZINC)
            .addOutputPart(HiiragiShapes.INGOT, MaterialCommon.BRASS, 4)
            .buildAndRegister()
        MachineRecipe.Builder(IMachineRecipe.Type.SMELTER, hiiragiLocation("brass_ingot"))
            .addInputPart(HiiragiShapes.INGOT, MaterialElements.COPPER, 3)
            .addInputPart(HiiragiShapes.INGOT, MaterialElements.ZINC)
            .addOutputPart(HiiragiShapes.INGOT, MaterialCommon.BRASS, 4)
            .buildAndRegister()

        MachineRecipe.Builder(IMachineRecipe.Type.SMELTER, hiiragiLocation("bronze_dust"))
            .addInputPart(HiiragiShapes.DUST, MaterialElements.COPPER, 3)
            .addInputPart(HiiragiShapes.DUST, MaterialElements.TIN)
            .addOutputPart(HiiragiShapes.INGOT, MaterialCommon.BRONZE, 4)
            .buildAndRegister()
        MachineRecipe.Builder(IMachineRecipe.Type.SMELTER, hiiragiLocation("bronze_ingot"))
            .addInputPart(HiiragiShapes.INGOT, MaterialElements.COPPER, 3)
            .addInputPart(HiiragiShapes.INGOT, MaterialElements.TIN)
            .addOutputPart(HiiragiShapes.INGOT, MaterialCommon.BRONZE, 4)
            .buildAndRegister()

        MachineRecipe.Builder(IMachineRecipe.Type.SMELTER, hiiragiLocation("electrum_dust"))
            .addInputPart(HiiragiShapes.DUST, MaterialElements.SILVER)
            .addInputPart(HiiragiShapes.DUST, MaterialElements.GOLD)
            .addOutputPart(HiiragiShapes.INGOT, MaterialCommon.ELECTRUM, 2)
            .buildAndRegister()
        MachineRecipe.Builder(IMachineRecipe.Type.SMELTER, hiiragiLocation("electrum_ingot"))
            .addInputPart(HiiragiShapes.INGOT, MaterialElements.SILVER)
            .addInputPart(HiiragiShapes.INGOT, MaterialElements.GOLD)
            .addOutputPart(HiiragiShapes.INGOT, MaterialCommon.ELECTRUM, 2)
            .buildAndRegister()

        MachineRecipe.Builder(IMachineRecipe.Type.SMELTER, hiiragiLocation("tungsten_steel_dust"))
            .addInputPart(HiiragiShapes.DUST, MaterialElements.TUNGSTEN)
            .addInputPart(HiiragiShapes.DUST, MaterialCommon.STEEL)
            .addOutputPart(HiiragiShapes.INGOT, MaterialCommon.TUNGSTEN_STEEL, 2)
            .buildAndRegister()
        MachineRecipe.Builder(IMachineRecipe.Type.SMELTER, hiiragiLocation("tungsten_steel_ingot"))
            .addInputPart(HiiragiShapes.INGOT, MaterialElements.TUNGSTEN)
            .addInputPart(HiiragiShapes.INGOT, MaterialCommon.STEEL)
            .addOutputPart(HiiragiShapes.INGOT, MaterialCommon.TUNGSTEN_STEEL, 2)
            .buildAndRegister()

    }

    private fun test() {
        MachineRecipe.Builder(IMachineRecipe.Type.TEST, hiiragiLocation("smelt_test"))
            .addInputBlock(Blocks.COBBLESTONE)
            .addOutputBlock(Blocks.STONE)
            .buildAndRegister()
        MachineRecipe.Builder(IMachineRecipe.Type.TEST, hiiragiLocation("almighty"))
            .addInput(ItemStack(Items.COAL))
            .addInput(ItemStack(Items.COAL, 1, 1))
            .addInput(ItemStack(Blocks.BEACON))
            .addInputBlock(Blocks.DIAMOND_BLOCK)
            .addInputOreDict("cobblestone")
            .addInputPart(HiiragiShapes.INGOT, MaterialCommon.STEEL)
            .addInputFluid(FluidRegistry.WATER, 1000)
            .addInputFluid(FluidRegistry.LAVA, 500)
            .addInputFluid(MaterialCommon.BRONZE, 144 * 4)
            .addOutputBlock(Blocks.DIRT)
            .addOutputFluid(MaterialElements.HYDROGEN, 4000)
            .buildAndRegister()
        /*MachineRecipe.Builder(IMachineRecipe.Type.TEST, hiiragiLocation("water_test"))
            .addInputFluid(FluidRegistry.WATER, 1000)
            .addOutputBlock(Blocks.ICE)
            .buildAndRegister()*/
    }

}