package hiiragi283.material.api.material;

import crafttweaker.annotations.ZenRegister;
import stanhebben.zenscript.annotations.ZenClass;

@FunctionalInterface
@ZenClass("${RMReference.MOD_ID}.material.MaterialPredicate")
@ZenRegister
public interface MaterialPredicate {

    boolean test(HiiragiMaterial material);

}