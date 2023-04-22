package ragi_materials.core.material.materials

import ragi_materials.core.material.ElementRegistry
import ragi_materials.core.material.MaterialRegistry
import ragi_materials.core.material.RagiMaterial
import ragi_materials.core.material.type.TypeRegistry

object PeriodSixth {

    fun load() {
        //560 ~ 569: Barium
        MaterialRegistry.BARIUM = RagiMaterial.Builder(560, "barium", TypeRegistry.INTERNAL).setSimple(ElementRegistry.BARIUM to 1).build()

        //570 ~ 579: Rare Earth
        MaterialRegistry.NEODYMIUM = RagiMaterial.Builder(570, "neodymium", TypeRegistry.METAL).setSimple(ElementRegistry.NEODYMIUM to 1).build()

        MaterialRegistry.SAMARIUM = RagiMaterial.Builder(571, "samarium", TypeRegistry.METAL).setSimple(ElementRegistry.SAMARIUM to 1).build()

        //720 ~ 729: Hafnium
        MaterialRegistry.HAFNIUM = RagiMaterial.Builder(720, "hafnium", TypeRegistry.METAL).setSimple(ElementRegistry.HAFNIUM to 1).build()

        //730 ~ 739: Tantalum
        MaterialRegistry.TANTALUM = RagiMaterial.Builder(730, "tantalum", TypeRegistry.METAL).setSimple(ElementRegistry.TANTALUM to 1).build()

        //740 ~ 749: Tungsten
        MaterialRegistry.TUNGSTEN = RagiMaterial.Builder(740, "tungsten", TypeRegistry.METAL).setSimple(ElementRegistry.TUNGSTEN to 1).build()

        //790 ~ 799: Gold
        MaterialRegistry.GOLD = RagiMaterial.Builder(790, "gold", TypeRegistry.METAL).setSimple(ElementRegistry.GOLD to 1).build()

        //800 ~ 809: Mercury
        MaterialRegistry.MERCURY = RagiMaterial.Builder(800, "mercury", TypeRegistry.LIQUID).setSimple(ElementRegistry.MERCURY to 1).build()

        //820 ~ 829: Lead
        MaterialRegistry.LEAD = RagiMaterial.Builder(820, "lead", TypeRegistry.METAL).setSimple(ElementRegistry.LEAD to 1).build()

        //830 ~ 839: Bismuth
        MaterialRegistry.BISMUTH = RagiMaterial.Builder(830, "bismuth", TypeRegistry.METAL).setSimple(ElementRegistry.BISMUTH to 1).build()
    }
}