package ragi_materials.core.material.materials

import ragi_materials.core.material.ElementRegistry
import ragi_materials.core.material.EnumSubMaterial
import ragi_materials.core.material.MaterialRegistry
import ragi_materials.core.material.RagiMaterial
import ragi_materials.core.material.type.TypeRegistry

object PeriodSixth {

    fun load() {

        //740 ~ 749: Tungsten
        MaterialRegistry.TUNGSTEN = RagiMaterial.Builder(740, "tungsten", TypeRegistry.METAL).setSimple(ElementRegistry.TUNGSTEN to 1).buildAndRegister()

        //790 ~ 799: Gold
        MaterialRegistry.GOLD = RagiMaterial.Builder(790, "gold", TypeRegistry.METAL.enableOre()).setSimple(ElementRegistry.GOLD to 1).buildAndRegister()
        MaterialRegistry.GOLD.setSubMaterial(EnumSubMaterial.NATIVE, MaterialRegistry.GOLD)

        //800 ~ 809: Mercury
        MaterialRegistry.MERCURY = RagiMaterial.Builder(800, "mercury", TypeRegistry.LIQUID).setSimple(ElementRegistry.MERCURY to 1).buildAndRegister()

        //820 ~ 829: Lead
        MaterialRegistry.LEAD = RagiMaterial.Builder(820, "lead", TypeRegistry.METAL.enableOre()).setSimple(ElementRegistry.LEAD to 1).buildAndRegister()
        MaterialRegistry.LEAD.setSubMaterial(EnumSubMaterial.NATIVE, MaterialRegistry.LEAD)

        //830 ~ 839: Bismuth
        MaterialRegistry.BISMUTH = RagiMaterial.Builder(830, "bismuth", TypeRegistry.METAL.enableOre()).setSimple(ElementRegistry.BISMUTH to 1).buildAndRegister()
        MaterialRegistry.BISMUTH.setSubMaterial(EnumSubMaterial.NATIVE, MaterialRegistry.BISMUTH)
    }
}