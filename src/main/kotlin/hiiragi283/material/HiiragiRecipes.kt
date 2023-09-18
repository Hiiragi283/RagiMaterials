package hiiragi283.material

import hiiragi283.material.api.material.MaterialCommon
import hiiragi283.material.api.material.MaterialElements
import hiiragi283.material.api.recipe.IMachineRecipe
import hiiragi283.material.api.recipe.MachineRecipe
import hiiragi283.material.api.shape.HiiragiShapes
import hiiragi283.material.util.hiiragiLocation
import net.minecraft.init.Blocks
import net.minecraft.init.Items
import net.minecraft.item.ItemStack
import net.minecraftforge.fluids.FluidRegistry

object HiiragiRecipes {

    fun init() {
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
    }

}