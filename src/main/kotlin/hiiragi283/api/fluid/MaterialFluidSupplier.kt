package hiiragi283.api.fluid

import net.minecraft.block.Block
import net.minecraft.util.ResourceLocation
import net.minecraftforge.fluids.Fluid
import net.minecraftforge.fluids.FluidRegistry
import net.minecraftforge.fml.common.registry.ForgeRegistries
import java.util.function.Supplier

class MaterialFluidSupplier @JvmOverloads constructor(
    private val fluidSupplier: Supplier<Fluid>,
    private val blockSupplier: Supplier<Block?> = Supplier<Block?> { null }
) : Supplier<Fluid> {

    private var value: Fluid? = null

    constructor(fluidSupplier: Supplier<Fluid>, fluidBlock: String) : this(
        fluidSupplier,
        Supplier<Block?> { ForgeRegistries.BLOCKS.getValue(ResourceLocation(fluidBlock)) }
    )

    constructor(fluid: String, fluidBlock: String) : this(
        Supplier<Fluid> { FluidRegistry.getFluid(fluid) },
        Supplier<Block?> { ForgeRegistries.BLOCKS.getValue(ResourceLocation(fluidBlock)) }
    )

    override fun get(): Fluid {
        if (value == null) {
            val fluid: Fluid = fluidSupplier.get()
            blockSupplier.get()?.let { fluid.setBlock(it) }
            value = fluid
        }
        return value!!
    }

}