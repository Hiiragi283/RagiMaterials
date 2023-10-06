package hiiragi283.material.api.item

import hiiragi283.material.HiiragiCreativeTabs
import hiiragi283.material.api.machine.MachineType
import hiiragi283.material.api.material.formulaOf
import hiiragi283.material.util.appendBefore
import hiiragi283.material.util.setModelAlt
import net.minecraft.item.ItemStack
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

class RecipeModuleItem(val recipeType: MachineType) : HiiragiItem("recipe_module_${recipeType.lowercase()}") {

    init {
        creativeTab = HiiragiCreativeTabs.MACHINE
    }

    //    Client    //

    @SideOnly(Side.CLIENT)
    override fun getItemStackDisplayName(stack: ItemStack): String = recipeType.getTranslatedName(RECIPE_MODULE)

    companion object {
        private val RECIPE_MODULE = formulaOf("recipe_module")
    }

    //    HiiragiEntry    //

    @SideOnly(Side.CLIENT)
    override fun registerModel() {
        this.setModelAlt(registryName!!.appendBefore("recipe_module/"))
    }

}