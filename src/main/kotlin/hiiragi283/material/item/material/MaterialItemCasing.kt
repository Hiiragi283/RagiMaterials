package hiiragi283.material.item.material

import hiiragi283.material.api.item.MaterialItem
import hiiragi283.material.api.machine.MachinePropertyItem
import hiiragi283.material.api.machine.MachineTrait
import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.init.HiiragiItems
import hiiragi283.material.init.HiiragiShapes
import hiiragi283.material.util.CraftingBuilder
import hiiragi283.material.util.itemStack
import net.minecraft.client.util.ITooltipFlag
import net.minecraft.item.ItemStack
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

object MaterialItemCasing : MaterialItem(HiiragiShapes.CASING), MachinePropertyItem {

    override fun registerRecipe(material: HiiragiMaterial) {
        // 8x Plate + 1x Smithing Hammer -> 1x Casing
        if (HiiragiShapes.PLATE.canCreateMaterialItem(material)) {
            CraftingBuilder(itemStack(material))
                .setPattern("AAA", "ABA", "AAA")
                .setIngredient('A', HiiragiShapes.PLATE.getOreDict(material))
                .setIngredient('B', HiiragiItems.SMITHING_HAMMER, true)
                .build()
        }
        //Metal Former Recipe
        addMetalFormerRecipe(material, inputCount = 8)
        //Grinder Recipe
        addGrinderRecipe(material)
    }

    //    Client    //

    @SideOnly(Side.CLIENT)
    override fun addInformation(stack: ItemStack, worldIn: World?, tooltip: MutableList<String>, flagIn: ITooltipFlag) {
        this.addTooltip(stack, tooltip)
    }

    //    MachinePropertyItem    //

    override fun getProcessTime(stack: ItemStack): Int = getMaterial(stack)
        ?.machineProperty
        ?.processTime
        ?: super.getProcessTime(stack)

    override fun getEnergyRate(stack: ItemStack): Int = getMaterial(stack)
        ?.machineProperty
        ?.energyRate
        ?: super.getEnergyRate(stack)

    override fun getItemSlots(stack: ItemStack): Int = getMaterial(stack)
        ?.machineProperty
        ?.itemSlots
        ?: super.getItemSlots(stack)

    override fun getFluidSlots(stack: ItemStack): Int = getMaterial(stack)
        ?.machineProperty
        ?.fluidSlots
        ?: super.getFluidSlots(stack)

    override fun getMachineTraits(stack: ItemStack): Set<MachineTrait> = getMaterial(stack)
        ?.machineProperty
        ?.machineTraits
        ?: super.getMachineTraits(stack)

}