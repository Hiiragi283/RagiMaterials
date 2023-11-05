package hiiragi283.material.api.material;

import crafttweaker.annotations.ZenRegister;
import stanhebben.zenscript.annotations.ZenClass;

@FunctionalInterface
@ZenClass("${RMReference.MOD_ID}.material.MaterialScaleFunction")
@ZenRegister
public interface MaterialScaleFunction {

    int apply(HiiragiMaterial material);

}