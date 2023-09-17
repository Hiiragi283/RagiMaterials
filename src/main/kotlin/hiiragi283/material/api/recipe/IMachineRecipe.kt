package hiiragi283.material.api.recipe

import hiiragi283.material.api.machine.ModuleTrait
import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.api.tile.TileEntityModuleMachine
import net.minecraft.client.resources.I18n
import net.minecraft.item.ItemStack
import net.minecraftforge.fluids.FluidStack

interface IMachineRecipe {

    val type: Type

    val requiredTraits: Set<ModuleTrait>

    //    Process    //

    fun matches(tile: TileEntityModuleMachine): Boolean

    fun process(tile: TileEntityModuleMachine)

    //    Inputs    //

    val inputItems: List<List<ItemStack>>

    val inputFluids: List<FluidStack>

    //    Outputs    //

    val outputItems: List<ItemStack>

    val outputFluids: List<FluidStack>

    enum class Type {
        BEND,
        CRUSH,
        CUT,
        PULVERISE,
        SMELT,
        TEST,
        WIRE,
        NONE;

        private val translationKey: String = "hiiragi_machine.${this.name.lowercase()}"

        fun getTranslatedName(material: HiiragiMaterial): String =
            I18n.format(translationKey, material.getTranslatedName())

    }

}