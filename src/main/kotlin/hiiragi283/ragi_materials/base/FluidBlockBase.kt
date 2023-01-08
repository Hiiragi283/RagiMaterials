package hiiragi283.ragi_materials.base

import net.minecraft.block.material.Material
import net.minecraftforge.fluids.BlockFluidClassic
import net.minecraftforge.fluids.Fluid

class FluidBlockBase(fluid: Fluid) : BlockFluidClassic(fluid, Material.WATER) {

    //コンストラクタの初期化
    init {
        setRegistryName(fluid.name) //IDの設定
    }
}