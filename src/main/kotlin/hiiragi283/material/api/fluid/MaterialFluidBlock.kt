package hiiragi283.material.api.fluid

import hiiragi283.material.RMReference
import hiiragi283.material.api.item.HiiragiItemBlock
import hiiragi283.material.api.registry.HiiragiEntry
import hiiragi283.material.util.SimpleStateMapper
import net.minecraft.block.material.Material
import net.minecraftforge.client.model.ModelLoader
import net.minecraftforge.fluids.BlockFluidClassic
import net.minecraftforge.fluids.Fluid

class MaterialFluidBlock(fluid: Fluid) : BlockFluidClassic(fluid, Material.WATER), HiiragiEntry.BLOCK {

    init {
        setRegistryName(RMReference.MOD_ID, fluid.name)
        translationKey = fluid.unlocalizedName
    }

    override val itemBlock: HiiragiItemBlock = MaterialFluidBlockItem(this)

    //    HiiragiEntry    //

    override fun registerModel() {
        val mapper = SimpleStateMapper(getLocation(), "fluid")
        ModelLoader.setCustomStateMapper(this, mapper)
        ModelLoader.setCustomMeshDefinition(itemBlock, mapper)
    }

}