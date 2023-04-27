package ragi_materials.core.material.materials

import ragi_materials.core.material.ElementRegistry
import ragi_materials.core.material.EnumSubMaterial
import ragi_materials.core.material.MaterialRegistry
import ragi_materials.core.material.RagiMaterial
import ragi_materials.core.material.type.TypeRegistry

object PeriodSeventh {

    fun load() {
        //900 ~ 909: Thorium
        MaterialRegistry.THORIUM = RagiMaterial.Builder(900, "thorium", TypeRegistry.METAL.enableRadio().enableOre()).setSimple(ElementRegistry.THORIUM to 1).build()

        //920 ~ 929: Uranium
        MaterialRegistry.URANIUM_238 = RagiMaterial.Builder(920, "uranium", TypeRegistry.METAL.enableRadio().enableOre()).setSimple(ElementRegistry.URANIUM_238 to 1).build()
        MaterialRegistry.URANIUM_238.setSubMaterial(EnumSubMaterial.DECAYED, MaterialRegistry.LEAD)

        MaterialRegistry.URANIUM_235 = RagiMaterial.Builder(921, "uranium235", TypeRegistry.METAL.enableRadio()).setSimple(ElementRegistry.URANIUM_235 to 1).build()
        MaterialRegistry.URANIUM_235.setSubMaterial(EnumSubMaterial.DECAYED, MaterialRegistry.LEAD)

        //940 ~ 949: Plutonium
        MaterialRegistry.PLUTONIUM_244 = RagiMaterial.Builder(940, "plutonium", TypeRegistry.METAL.enableRadio()).setSimple(ElementRegistry.PLUTONIUM_244 to 1).build()
        MaterialRegistry.PLUTONIUM_244.setSubMaterial(EnumSubMaterial.DECAYED, MaterialRegistry.LEAD)

        MaterialRegistry.PLUTONIUM_239 = RagiMaterial.Builder(941, "plutonium239", TypeRegistry.METAL.enableRadio()).setSimple(ElementRegistry.PLUTONIUM_239 to 1).build()
        MaterialRegistry.PLUTONIUM_239.setSubMaterial(EnumSubMaterial.DECAYED, MaterialRegistry.LEAD)
    }
}