package hiiragi283.ragi_materials.api.material.materials

import hiiragi283.ragi_materials.api.material.ElementRegistry
import hiiragi283.ragi_materials.api.material.MaterialRegistry
import hiiragi283.ragi_materials.api.material.RagiMaterial
import hiiragi283.ragi_materials.api.material.type.TypeRegistry

object PeriodSeventh {

    fun load() {
        //900 ~ 909: Thorium
        MaterialRegistry.THORIUM = RagiMaterial.Builder(900, "thorium", TypeRegistry.METAL_RADIO).setSimple(ElementRegistry.THORIUM to 1).build()

        //920 ~ 929: Uranium
        MaterialRegistry.URANIUM_238 = RagiMaterial.Builder(920, "uranium", TypeRegistry.METAL_RADIO).setSimple(ElementRegistry.URANIUM_238 to 1).build()

        MaterialRegistry.URANIUM_235 = RagiMaterial.Builder(921, "uranium235", TypeRegistry.METAL_RADIO).setSimple(ElementRegistry.URANIUM_235 to 1).build()

        //940 ~ 949: Plutonium
        MaterialRegistry.PLUTONIUM_244 = RagiMaterial.Builder(940, "plutonium", TypeRegistry.METAL_RADIO).setSimple(ElementRegistry.PLUTONIUM_244 to 1).build()

        MaterialRegistry.PLUTONIUM_239 = RagiMaterial.Builder(941, "plutonium239", TypeRegistry.METAL_RADIO).setSimple(ElementRegistry.PLUTONIUM_239 to 1).build()
    }
}