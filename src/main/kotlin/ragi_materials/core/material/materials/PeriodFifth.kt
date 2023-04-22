package ragi_materials.core.material.materials

import ragi_materials.core.material.ElementRegistry
import ragi_materials.core.material.MaterialRegistry
import ragi_materials.core.material.RagiMaterial
import ragi_materials.core.material.type.TypeRegistry

object PeriodFifth {

    fun load() {
        //380 ~ 389: Strontium
        MaterialRegistry.STRONTIUM = RagiMaterial.Builder(380, "strontium", TypeRegistry.INTERNAL).setSimple(ElementRegistry.STRONTIUM to 1).build()

        //400 ~ 409: Zirconium
        MaterialRegistry.ZIRCONIUM = RagiMaterial.Builder(400, "zirconium", TypeRegistry.METAL).setSimple(ElementRegistry.ZIRCONIUM to 1).build()

        //410 ~ 419: Niobium
        MaterialRegistry.NIOBIUM = RagiMaterial.Builder(410, "niobium", TypeRegistry.METAL).setSimple(ElementRegistry.NIOBIUM to 1).build()

        //420 ~ 429: Molybdenum
        MaterialRegistry.MOLYBDENUM = RagiMaterial.Builder(420, "molybdenum", TypeRegistry.METAL).setSimple(ElementRegistry.MOLYBDENUM to 1).build()

        //440 ~ 449: Platinum Group Metal
        MaterialRegistry.RUTHENIUM = RagiMaterial.Builder(440, "ruthenium", TypeRegistry.METAL).setSimple(ElementRegistry.RUTHENIUM to 1).build()

        MaterialRegistry.RHODIUM = RagiMaterial.Builder(441, "rhodium", TypeRegistry.METAL).setSimple(ElementRegistry.RHODIUM to 1).build()

        MaterialRegistry.PALLADIUM = RagiMaterial.Builder(442, "palladium", TypeRegistry.METAL).setSimple(ElementRegistry.PALLADIUM to 1).build()

        MaterialRegistry.OSMIUM = RagiMaterial.Builder(443, "osmium", TypeRegistry.METAL).setSimple(ElementRegistry.OSMIUM to 1).build()

        MaterialRegistry.IRIDIUM = RagiMaterial.Builder(444, "iridium", TypeRegistry.METAL).setSimple(ElementRegistry.IRIDIUM to 1).build()

        MaterialRegistry.PLATINUM = RagiMaterial.Builder(445, "platinum", TypeRegistry.METAL).setSimple(ElementRegistry.PLATINUM to 1).build()

        //470 ~ 479: Silver
        MaterialRegistry.SILVER = RagiMaterial.Builder(470, "silver", TypeRegistry.METAL).setSimple(ElementRegistry.SILVER to 1).build()

        //490 ~ 499: Indium
        MaterialRegistry.INDIUM = RagiMaterial.Builder(490, "indium", TypeRegistry.METAL).setSimple(ElementRegistry.INDIUM to 1).build()

        //500 ~ 509: Tin
        MaterialRegistry.TIN = RagiMaterial.Builder(500, "tin", TypeRegistry.METAL).setSimple(ElementRegistry.TIN to 1).build()

        //510 ~ 519: Antimony
        MaterialRegistry.ANTIMONY = RagiMaterial.Builder(510, "antimony", TypeRegistry.METAL).setSimple(ElementRegistry.ANTIMONY to 1).build()

        //530 ~ 539: Iodine
        MaterialRegistry.IODINE = RagiMaterial.Builder(530, "iodine", TypeRegistry.METALLOID).setSimple(ElementRegistry.IODINE to 2).build()
    }
}