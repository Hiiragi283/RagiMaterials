package hiiragi283.material.api.fluid

import hiiragi283.material.RMReference
import hiiragi283.material.api.item.HiiragiItemBlock
import hiiragi283.material.api.registry.HiiragiEntry
import hiiragi283.material.util.simpleStateMapper
import net.minecraft.block.material.Material
import net.minecraftforge.client.model.ModelLoader
import net.minecraftforge.fluids.BlockFluidClassic
import net.minecraftforge.fluids.Fluid

class MaterialFluidBlock(fluid: Fluid) : BlockFluidClassic(fluid, Material.WATER), HiiragiEntry.BLOCK {

    init {
        setRegistryName(RMReference.MOD_ID, fluid.name)
        translationKey = fluid.unlocalizedName
    }

    override val itemBlock: HiiragiItemBlock? = null

    //    HiiragiEntry    //

    override fun registerModel() {
        ModelLoader.setCustomStateMapper(this, simpleStateMapper(getLocation(), "fluid"))
    }

}