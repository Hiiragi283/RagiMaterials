package hiiragi283.material;

import hiiragi283.material.api.capability.HiiragiCapability;
import hiiragi283.material.api.fluid.MaterialFluid;
import hiiragi283.material.api.material.HiiragiMaterial;
import hiiragi283.material.api.part.HiiragiPart;
import hiiragi283.material.api.shape.HiiragiShape;
import hiiragi283.material.network.HiiragiNetworkManager;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(
        modid = RMReference.MOD_ID,
        name = RMReference.MOD_NAME,
        version = RMReference.VERSION,
        acceptedMinecraftVersions = "[1.12, 1.12.2]"
)
public class RagiMaterials implements HiiragiProxy {

    public static final Logger LOGGER = LogManager.getLogger(RMReference.MOD_NAME);

    @Mod.Instance
    public static RagiMaterials INSTANCE;

    public RagiMaterials() {
        if (Loader.isModLoaded("gregtech")) {
            throw new RuntimeException("""
                    =====================================================
                    RagiMaterials detected GregTech in this environment!!
                    Remove RagiMaterials or GregTech from mods folder
                    =====================================================""");
        }
    }

    @Mod.EventHandler
    public void onConstruct(FMLConstructionEvent event) {
        //Universal Bucketを有効化
        FluidRegistry.enableUniversalBucket();
    }

    @Mod.EventHandler
    public void onPreInit(FMLPreInitializationEvent event) {
        //形状の登録
        HiiragiShape.registerShapes();
        //素材の登録
        HiiragiMaterial.registerMaterials();
        //Block, Itemの初期化
        HiiragiBlocks.init();
        HiiragiItems.init();
        //Fluidの登録
        MaterialFluid.register();
        //Capabilityの登録
        HiiragiCapability.register();
    }

    @Mod.EventHandler
    public void onInit(FMLInitializationEvent event) {
        //部品の登録
        HiiragiPart.getAllParts().forEach(part -> part.getOreDicts().forEach(oreDict -> HiiragiPart.REGISTRY.register(oreDict, part)));
        //鉱石辞書の登録
        HiiragiBlocks.INSTANCE.registerOreDict();
        HiiragiItems.INSTANCE.registerOreDict();
        //レシピの登録
        HiiragiBlocks.INSTANCE.registerRecipe();
        HiiragiItems.INSTANCE.registerRecipe();
    }

    @Mod.EventHandler
    public void onPostInit(FMLPostInitializationEvent event) {
    }

    @Mod.EventHandler
    public void onComplete(FMLLoadCompleteEvent event) {
        //GUi操作を登録
        NetworkRegistry.INSTANCE.registerGuiHandler(INSTANCE, new HiiragiGuiHandler());
        //パケット送信を登録
        HiiragiNetworkManager.register();
    }

}