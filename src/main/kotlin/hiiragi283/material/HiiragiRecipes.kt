package hiiragi283.material

import hiiragi283.material.api.recipe.IMachineRecipe
import hiiragi283.material.api.recipe.MachineRecipe
import hiiragi283.material.util.hiiragiLocation
import net.minecraft.init.Blocks

object HiiragiRecipes {

    fun init() {
        MachineRecipe.Builder(IMachineRecipe.Type.TEST, hiiragiLocation("smelt_test"))
            .addInputBlock(Blocks.COBBLESTONE)
            .addOutputBlock(Blocks.STONE)
            .buildAndRegister()
    }

}