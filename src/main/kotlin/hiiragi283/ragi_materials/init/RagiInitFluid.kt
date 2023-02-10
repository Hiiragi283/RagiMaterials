package hiiragi283.ragi_materials.init

import hiiragi283.ragi_materials.base.FluidBase
import hiiragi283.ragi_materials.material.MaterialBuilder
import hiiragi283.ragi_materials.material.MaterialRegistry
import net.minecraft.block.material.Material
import net.minecraftforge.fluids.BlockFluidClassic
import net.minecraftforge.fluids.FluidRegistry
import net.minecraftforge.fml.common.registry.ForgeRegistries

object RagiInitFluid {

    //Fluidを登録するメソッド
    fun registerFluids() {
        //EnumMaterialsの各enumに対して実行
        for (material in MaterialRegistry.list) {
            //materialがWILDCARDでない，かつmaterialのtypeのhasFluidがtrueの場合
            if (material != MaterialRegistry.WILDCARD && material.type.hasFluid) {
                //Fluidの登録
                val fluid = FluidBase(material.name)
                fluid.setColor(material.getColor())
                //MaterialTypesがGASの場合
                if (material.type == MaterialBuilder.MaterialType.GAS) {
                    fluid.isGaseous = true
                    fluid.density = fluid.density * -1
                }
                FluidRegistry.registerFluid(fluid)
                //fluid入りバケツを登録
                FluidRegistry.addBucketForFluid(fluid)
                //materialのtypeのhasFluidBlockがtrueの場合
                if (material.type.hasFluidBlock) {
                    //液体ブロックを生成・登録・割り当て
                    val fluidBlock = BlockFluidClassic(fluid, Material.WATER).setRegistryName(fluid.name)
                    ForgeRegistries.BLOCKS.register(fluidBlock)
                    fluid.block = fluidBlock
                }
            }
        }
    }
}