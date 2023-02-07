package hiiragi283.ragi_materials

import hiiragi283.ragi_materials.init.RagiInit
import net.minecraftforge.fluids.FluidRegistry
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent

//Modの定義
@Mod(
    modid = Reference.MOD_ID,
    name = Reference.MOD_NAME,
    version = Reference.VERSION,
    dependencies = Reference.DEPENDENCIES,
    acceptedMinecraftVersions = Reference.MC_VERSIONS
)
class RagiMaterials {

    init {
        //Universal Bucketの使用
        FluidRegistry.enableUniversalBucket()
    }

    //Pre-Initializationの段階で呼ばれるevent
    @Mod.EventHandler
    fun preInit(event: FMLPreInitializationEvent?) {
        //configの読み込み
        RagiInit.loadPreInit()
    }

    //Initializationの段階で呼ばれるevent
    @Mod.EventHandler
    fun init(event: FMLInitializationEvent?) {
        RagiInit.loadInit()
    }

    //Post-Initializationの段階で呼ばれるevent
    @Mod.EventHandler
    fun postInit(event: FMLPostInitializationEvent?) {
        RagiInit.loadPostInit()
    }
}