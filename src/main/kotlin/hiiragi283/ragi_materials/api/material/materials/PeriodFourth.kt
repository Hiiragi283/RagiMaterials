package hiiragi283.ragi_materials.api.material.materials

import hiiragi283.ragi_materials.api.material.ElementRegistry
import hiiragi283.ragi_materials.api.material.MaterialRegistry
import hiiragi283.ragi_materials.api.material.RagiMaterial
import hiiragi283.ragi_materials.api.material.type.EnumCrystalType
import hiiragi283.ragi_materials.api.material.type.TypeRegistry
import hiiragi283.ragi_materials.util.ColorUtil
import hiiragi283.ragi_materials.util.RagiColor
import net.minecraft.item.EnumRarity

object PeriodFourth {

    fun load() {
        //190 ~ 199: Potassium
        MaterialRegistry.POTASSIUM = RagiMaterial.Builder(190, "potassium", TypeRegistry.INTERNAL).setSimple(ElementRegistry.POTASSIUM to 1).build()

        //200 ~ 209: Calcium
        MaterialRegistry.CALCIUM = RagiMaterial.Builder(200, "calcium", TypeRegistry.INTERNAL).setSimple(ElementRegistry.CALCIUM to 1).build()

        MaterialRegistry.CALCIUM_HYDROXIDE = RagiMaterial.Builder(201, "calcium_hydroxide", TypeRegistry.DUST).setComponents(listOf(ElementRegistry.CALCIUM to 1, MaterialRegistry.HYDROXIDE.setBracket() to 2)).build()

        MaterialRegistry.LIME = RagiMaterial.Builder(202, "lime", TypeRegistry.DUST).setComponents(listOf(ElementRegistry.CALCIUM to 1, MaterialRegistry.CARBONATE to 1)).build()

        MaterialRegistry.QUICK_LIME = RagiMaterial.Builder(203, "quick_lime", TypeRegistry.DUST).setComponents(listOf(ElementRegistry.CALCIUM to 1, ElementRegistry.OXYGEN to 1)).build()

        MaterialRegistry.APATITE = RagiMaterial.Builder(204, "apatite", TypeRegistry.CRYSTAL).setComponents(listOf(ElementRegistry.CALCIUM to 5, MaterialRegistry.PHOSPHATE.setBracket() to 3, MaterialRegistry.HYDROXIDE to 1)).apply {
            color = ColorUtil.mixColor(RagiColor.YELLOW, RagiColor.WHITE)
            crystalType = EnumCrystalType.EMERALD
        }.build()

        MaterialRegistry.GYPSUM = RagiMaterial.Builder(205, "gypsum", TypeRegistry.CRYSTAL).setComponents(listOf(ElementRegistry.CALCIUM to 1, MaterialRegistry.SULFATE to 1)).build()

        //220 ~ 229: Titanium
        MaterialRegistry.TITANIUM = RagiMaterial.Builder(220, "titanium", TypeRegistry.METAL).setSimple(ElementRegistry.TITANIUM to 1).apply {
            rarity = EnumRarity.RARE
        }.build()

        MaterialRegistry.RUTILE = RagiMaterial.Builder(221, "rutile", TypeRegistry.CRYSTAL).setComponents(listOf(ElementRegistry.TITANIUM to 1, ElementRegistry.OXYGEN to 2)).apply {
            color = RagiColor.YELLOW
            crystalType = EnumCrystalType.QUARTZ
            rarity = EnumRarity.RARE
        }.build()

        MaterialRegistry.TITANIUM_TETRACHLORIDE = RagiMaterial.Builder(222, "titanium_tetrachloride", TypeRegistry.LIQUID).setComponents(listOf(ElementRegistry.TITANIUM to 1, ElementRegistry.CHLORINE to 4)).apply {
            rarity = EnumRarity.RARE
        }.build()

        //240 ~ 249: Chromium
        MaterialRegistry.CHROMIUM = RagiMaterial.Builder(240, "chromium", TypeRegistry.METAL).setSimple(ElementRegistry.CHROMIUM to 1).apply {
            oredictAlt = "Chrome"
            rarity = EnumRarity.UNCOMMON
        }.build()

        MaterialRegistry.STAINLESS_STEEL = RagiMaterial.Builder(241, "stainless_steel", TypeRegistry.METAL).setComponents(listOf(ElementRegistry.IRON to 6, ElementRegistry.CHROMIUM to 1, ElementRegistry.MANGANESE to 1, ElementRegistry.NICKEL to 1)).apply {
            color = ColorUtil.mixColor(RagiColor.GRAY, RagiColor.WHITE)
            rarity = EnumRarity.RARE
        }.build()

        //250 ~ 259: Manganese
        MaterialRegistry.MANGANESE = RagiMaterial.Builder(250, "manganese", TypeRegistry.METAL).setSimple(ElementRegistry.MANGANESE to 1).apply {
            rarity = EnumRarity.UNCOMMON
        }.build()

        //260 ~ 269: Iron
        MaterialRegistry.IRON = RagiMaterial.Builder(260, "iron", TypeRegistry.METAL).setSimple(ElementRegistry.IRON to 1).build()

        MaterialRegistry.HEMATITE = RagiMaterial.Builder(261, "hematite", TypeRegistry.DUST).setComponents(listOf(ElementRegistry.IRON to 2, ElementRegistry.OXYGEN to 3)).apply {
            color = ColorUtil.mixColor(RagiColor.BLACK to 1, RagiColor.DARK_RED to 2, RagiColor.GOLD to 1)
        }.build()

        MaterialRegistry.MAGNETITE = RagiMaterial.Builder(262, "magnetite", TypeRegistry.DUST).setComponents(listOf(ElementRegistry.IRON to 3, ElementRegistry.OXYGEN to 4)).apply {
            color = ColorUtil.mixColor(RagiColor.BLACK, RagiColor.DARK_GRAY)
        }.build()

        MaterialRegistry.PYRITE = RagiMaterial.Builder(263, "pyrite", TypeRegistry.CRYSTAL).setComponents(listOf(ElementRegistry.IRON to 1, ElementRegistry.SULFUR to 2)).apply {
            color = RagiColor.YELLOW
            crystalType = EnumCrystalType.CUBIC
        }.build()

        MaterialRegistry.ARSENOPYRITE = RagiMaterial.Builder(264, "arsenopyrite", TypeRegistry.CRYSTAL).setComponents(listOf(ElementRegistry.IRON to 1, ElementRegistry.ARSENIC to 1, ElementRegistry.SULFUR to 1)).apply {
            crystalType = EnumCrystalType.CUBIC
        }.build()

        MaterialRegistry.STEEL = RagiMaterial.Builder(265, "steel", TypeRegistry.METAL).setComponents(listOf(ElementRegistry.IRON to 1, ElementRegistry.CARBON to 1)).apply {
            rarity = EnumRarity.UNCOMMON
        }.build()

        //270 ~ 279: Cobalt
        MaterialRegistry.COBALT = RagiMaterial.Builder(270, "cobalt", TypeRegistry.METAL).setSimple(ElementRegistry.COBALT to 1).apply {
            rarity = EnumRarity.UNCOMMON
        }.build()

        //280 ~ 289: Nickel
        MaterialRegistry.NICKEL = RagiMaterial.Builder(280, "nickel", TypeRegistry.METAL).setSimple(ElementRegistry.NICKEL to 1).apply {
            rarity = EnumRarity.UNCOMMON
        }.build()

        MaterialRegistry.INVAR = RagiMaterial.Builder(281, "invar", TypeRegistry.METAL).setComponents(listOf(ElementRegistry.IRON to 1, ElementRegistry.NICKEL to 2)).apply {
            rarity = EnumRarity.UNCOMMON
        }.build()

        MaterialRegistry.CONSTANTAN = RagiMaterial.Builder(282, "constantan", TypeRegistry.METAL).setComponents(listOf(ElementRegistry.NICKEL to 1, ElementRegistry.COPPER to 1)).apply {
            rarity = EnumRarity.UNCOMMON
        }.build()

        //290 ~ 299: Copper
        MaterialRegistry.COPPER = RagiMaterial.Builder(290, "copper", TypeRegistry.METAL).setSimple(ElementRegistry.COPPER to 1).build()

        //300 ~ 309: Zinc
        MaterialRegistry.ZINC = RagiMaterial.Builder(300, "zinc", TypeRegistry.METAL).setSimple(ElementRegistry.ZINC to 1).build()

        //MaterialRegistry.SPHALERITE = RagiMaterial.Builder(301, "sphalerite", TypeRegistry.DUST).setComponents(listOf(ElementRegistry.ZINC to 1, ElementRegistry.SULFUR to 1)).build()

        MaterialRegistry.BRASS = RagiMaterial.Builder(302, "brass", TypeRegistry.METAL).setComponents(listOf(ElementRegistry.COPPER to 3, ElementRegistry.ZINC to 1)).apply {
            color = RagiColor.GOLD
        }.build()

        //310 ~ 319: Gallium, Arsenic
        MaterialRegistry.GALLIUM = RagiMaterial.Builder(310, "gallium", TypeRegistry.METAL).setSimple(ElementRegistry.GALLIUM to 1).apply {
            rarity = EnumRarity.RARE
        }.build()

        MaterialRegistry.ARSENIC = RagiMaterial.Builder(311, "arsenic", TypeRegistry.METALLOID).setSimple(ElementRegistry.ARSENIC to 1).apply {
            rarity = EnumRarity.RARE
        }.build()
    }
}