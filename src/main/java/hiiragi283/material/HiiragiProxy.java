package hiiragi283.material;

import net.minecraftforge.fml.common.event.*;

public interface HiiragiProxy {

    void onConstruct(FMLConstructionEvent event);

    void onPreInit(FMLPreInitializationEvent event);

    void onInit(FMLInitializationEvent event);

    void onPostInit(FMLPostInitializationEvent event);

    void onComplete(FMLLoadCompleteEvent event);

}