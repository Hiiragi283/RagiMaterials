package ragi_materials.core.material.materials

import ragi_materials.core.material.ElementRegistry
import ragi_materials.core.material.MaterialRegistry
import ragi_materials.core.material.RagiMaterial
import ragi_materials.core.material.type.EnumCrystalType
import ragi_materials.core.material.type.TypeRegistry
import ragi_materials.core.util.ColorUtil
import ragi_materials.core.util.RagiColor
import net.minecraftforge.fluids.FluidRegistry

object PeriodThird {

    fun load() {
        //110 ~ 119: Sodium
        MaterialRegistry.SODIUM = RagiMaterial.Builder(110, "sodium", TypeRegistry.INTERNAL).setSimple(ElementRegistry.SODIUM to 1).build()

        MaterialRegistry.SODIUM_HYDROXIDE = RagiMaterial.Builder(111, "sodium_hydroxide", TypeRegistry.DUST).setComponents(listOf(ElementRegistry.SODIUM to 1, MaterialRegistry.HYDROXIDE to 1)).build()

        MaterialRegistry.SALT = RagiMaterial.Builder(112, "salt", TypeRegistry.DUST).setComponents(listOf(ElementRegistry.SODIUM to 1, ElementRegistry.CHLORINE to 1)).apply {
            color = RagiColor.WHITE
        }.build()

        MaterialRegistry.SODIUM_SULFATE = RagiMaterial.Builder(113, "sodium_sulfate", TypeRegistry.DUST).setComponents(listOf(ElementRegistry.SODIUM to 2, MaterialRegistry.SULFATE to 1)).build()

        //120 ~ 129: Magnesium
        MaterialRegistry.MAGNESIUM = RagiMaterial.Builder(120, "magnesium", TypeRegistry.INTERNAL).setSimple(ElementRegistry.MAGNESIUM to 1).build()

        MaterialRegistry.MAGNESITE = RagiMaterial.Builder(121, "magnesite", TypeRegistry.DUST).setComponents(listOf(ElementRegistry.MAGNESIUM to 1, MaterialRegistry.CARBONATE to 1)).build()

        MaterialRegistry.MAGNESIUM_CHLORIDE = RagiMaterial.Builder(122, "magnesium_chloride", TypeRegistry.DUST).setComponents(listOf(ElementRegistry.MAGNESIUM to 1, ElementRegistry.CHLORINE to 2)).build()

        //130 ~ 139: Aluminium
        MaterialRegistry.ALUMINIUM = RagiMaterial.Builder(130, "aluminium", TypeRegistry.METAL).setSimple(ElementRegistry.ALUMINIUM to 1).apply {
            oredictAlt = "Aluminum"
        }.build()

        MaterialRegistry.ALUMINA = RagiMaterial.Builder(131, "alumina", TypeRegistry.DUST).setComponents(listOf(ElementRegistry.ALUMINIUM to 2, ElementRegistry.OXYGEN to 3)).build()

        MaterialRegistry.ALUMINA_SOLUTION = RagiMaterial.Builder(132, "alumina_solution", TypeRegistry.LIQUID).setComponents(listOf(ElementRegistry.SODIUM to 1, RagiMaterial.Formula("[").build() to 1, ElementRegistry.ALUMINIUM to 1, MaterialRegistry.HYDROXIDE.setBracket() to 4, RagiMaterial.Formula("]").build() to 1)).build()

        MaterialRegistry.BAUXITE = RagiMaterial.Builder(133, "bauxite", TypeRegistry.DUST).setComponents(listOf(ElementRegistry.ALUMINIUM to 2, ElementRegistry.OXYGEN to 3)).apply {
            color = ColorUtil.mixColor(RagiColor.BLACK to 1, RagiColor.DARK_RED to 2, RagiColor.GOLD to 1)
        }.build()

        MaterialRegistry.RUBY = RagiMaterial.Builder(134, "ruby", TypeRegistry.CRYSTAL).setComponents(listOf(ElementRegistry.CHROMIUM to 1, MaterialRegistry.ALUMINA to 1)).apply {
            color = RagiColor.RED
            crystalType = EnumCrystalType.RUBY
        }.build()

        MaterialRegistry.SAPPHIRE = RagiMaterial.Builder(135, "sapphire", TypeRegistry.CRYSTAL).setComponents(listOf(ElementRegistry.IRON to 1, MaterialRegistry.ALUMINA to 1)).apply {
            color = RagiColor.BLUE
            crystalType = EnumCrystalType.RUBY
        }.build()

        //140 ~ 149: Silicon
        MaterialRegistry.SILICON = RagiMaterial.Builder(140, "silicon", TypeRegistry.METALLOID).setSimple(ElementRegistry.SILICON to 1).build()

        MaterialRegistry.SILICON_DIOXIDE = RagiMaterial.Builder(141, "silicon_dioxide", TypeRegistry.DUST).setComponents(listOf(ElementRegistry.SILICON to 1, ElementRegistry.OXYGEN to 2)).build()

        MaterialRegistry.GLASS = RagiMaterial.Builder(142, "glass", TypeRegistry.CRYSTAL).setSimple(MaterialRegistry.SILICON_DIOXIDE to 1).apply {
            color = RagiColor.WHITE
            crystalType = EnumCrystalType.RUBY
        }.build()

        MaterialRegistry.QUARTZ = RagiMaterial.Builder(143, "quartz", TypeRegistry.CRYSTAL).setSimple(MaterialRegistry.SILICON_DIOXIDE to 1).apply {
            color = RagiColor.WHITE
            crystalType = EnumCrystalType.QUARTZ
        }.build()

        //150 ~ 159: Phosphorus
        MaterialRegistry.PHOSPHORUS = RagiMaterial.Builder(150, "phosphorus", TypeRegistry.DUST).setSimple(ElementRegistry.PHOSPHORUS to 1).build()

        //160 ~ 169: Sulfur
        MaterialRegistry.SULFUR = RagiMaterial.Builder(160, "sulfur", TypeRegistry.DUST).setSimple(ElementRegistry.SULFUR to 8).build()

        MaterialRegistry.SULFURIC_ACID = RagiMaterial.Builder(161, "sulfuric_acid", TypeRegistry.LIQUID).setComponents(listOf(ElementRegistry.HYDROGEN to 2, MaterialRegistry.SULFATE to 1)).apply {
            color = ColorUtil.mixColor(RagiColor.GOLD, RagiColor.YELLOW)
        }.build()

        //170 ~ 179: Chlorine
        MaterialRegistry.CHLORINE = RagiMaterial.Builder(170, "chlorine", TypeRegistry.GAS).setSimple(ElementRegistry.CHLORINE to 2).build()

        MaterialRegistry.HYDROGEN_CHLORIDE = RagiMaterial.Builder(171, "hydrogen_chloride", TypeRegistry.GAS).setComponents(listOf(ElementRegistry.HYDROGEN to 1, ElementRegistry.CHLORINE to 1)).build()

        //180 ~ 189: Stone
        MaterialRegistry.STONE = RagiMaterial.Builder(180, "stone", TypeRegistry.STONE).setSimple(MaterialRegistry.SILICON_DIOXIDE to 1).apply {
            color = RagiColor.GRAY
        }.build()

        MaterialRegistry.LAVA = RagiMaterial.Builder(181, "lava", TypeRegistry.INTERNAL).setSimple(MaterialRegistry.SILICON_DIOXIDE to 1).apply {
            color = ColorUtil.mixColor(RagiColor.DARK_RED, RagiColor.GOLD)
            tempMelt = FluidRegistry.LAVA.temperature
        }.build()

        MaterialRegistry.OBSIDIAN = RagiMaterial.Builder(182, "obsidian", TypeRegistry.DUST).setSimple(MaterialRegistry.SILICON_DIOXIDE to 1).apply {
            color = ColorUtil.mixColor(RagiColor.BLACK to 2, RagiColor.BLUE to 1, RagiColor.RED to 1)
        }.build()

        MaterialRegistry.NETHERRACK = RagiMaterial.Builder(183, "netherrack", TypeRegistry.DUST).setSimple(MaterialRegistry.SILICON_DIOXIDE to 1).apply {
            color = RagiColor.DARK_RED
        }.build()

        MaterialRegistry.SOUL_SAND = RagiMaterial.Builder(184, "soul_sand", TypeRegistry.DUST).setSimple(MaterialRegistry.SILICON_DIOXIDE to 1).apply {
            color = ColorUtil.mixColor(RagiColor.BLACK to 5, RagiColor.GOLD to 1)
        }.build()

        MaterialRegistry.END_STONE = RagiMaterial.Builder(185, "end_stone", TypeRegistry.DUST).setSimple(MaterialRegistry.SILICON_DIOXIDE to 1).apply {
            color = ColorUtil.mixColor(RagiColor.YELLOW to 1, RagiColor.WHITE to 3)
        }.build()

    }
}