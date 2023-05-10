package ragi_materials.core.material.materials

import ragi_materials.core.material.ElementRegistry
import ragi_materials.core.material.MaterialRegistry
import ragi_materials.core.material.RagiMaterial
import ragi_materials.core.material.type.TypeRegistry

object PeriodFifth {

    fun load() {

        //420 ~ 429: Molybdenum
        MaterialRegistry.MOLYBDENUM = RagiMaterial.Builder(420, "molybdenum", TypeRegistry.METAL).setSimple(ElementRegistry.MOLYBDENUM to 1).buildAndRegister()

        //440 ~ 449: Platinum Group Metal
        MaterialRegistry.OSMIUM = RagiMaterial.Builder(443, "osmium", TypeRegistry.METAL).setSimple(ElementRegistry.OSMIUM to 1).buildAndRegister()

        MaterialRegistry.IRIDIUM = RagiMaterial.Builder(444, "iridium", TypeRegistry.METAL).setSimple(ElementRegistry.IRIDIUM to 1).buildAndRegister()

        MaterialRegistry.PLATINUM = RagiMaterial.Builder(445, "platinum", TypeRegistry.METAL).setSimple(ElementRegistry.PLATINUM to 1).buildAndRegister()

        //470 ~ 479: Silver
        MaterialRegistry.SILVER = RagiMaterial.Builder(470, "silver", TypeRegistry.METAL).setSimple(ElementRegistry.SILVER to 1).buildAndRegister()

        //500 ~ 509: Tin
        MaterialRegistry.TIN = RagiMaterial.Builder(500, "tin", TypeRegistry.METAL.enableOre()).setSimple(ElementRegistry.TIN to 1).buildAndRegister()

        //510 ~ 519: Antimony
        MaterialRegistry.ANTIMONY = RagiMaterial.Builder(510, "antimony", TypeRegistry.METAL).setSimple(ElementRegistry.ANTIMONY to 1).buildAndRegister()

    }
}