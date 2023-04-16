package hiiragi283.ragi_materials.api.material.materials

import hiiragi283.ragi_materials.api.material.ElementRegistry
import hiiragi283.ragi_materials.api.material.MaterialRegistry
import hiiragi283.ragi_materials.api.material.RagiMaterial
import hiiragi283.ragi_materials.api.material.type.EnumCrystalType
import hiiragi283.ragi_materials.api.material.type.TypeRegistry
import hiiragi283.ragi_materials.client.color.ColorManager
import hiiragi283.ragi_materials.client.color.RagiColor
import net.minecraft.item.EnumRarity

object PeriodSecond {

    fun load() {
        //30 ~ 39: Lithium
        MaterialRegistry.LITHIUM = RagiMaterial.Builder(30, "lithium", TypeRegistry.METAL).setSimple(ElementRegistry.LITHIUM to 1).apply {
            rarity = EnumRarity.RARE
        }.build()

        MaterialRegistry.SPODUMENE = RagiMaterial.Builder(31, "spodumene", TypeRegistry.CRYSTAL).setComponents(listOf(ElementRegistry.LITHIUM to 1, ElementRegistry.ALUMINIUM to 1, ElementRegistry.SILICON to 2, ElementRegistry.OXYGEN to 6)).apply {
            color = ColorManager.mixColor(RagiColor.LIGHT_PURPLE, RagiColor.WHITE)
            crystalType = EnumCrystalType.LAPIS
        }.build()

        //40 ~ 49: Beryllium
        MaterialRegistry.BERYLLIUM = RagiMaterial.Builder(40, "beryllium", TypeRegistry.METAL).setSimple(ElementRegistry.BERYLLIUM to 1).apply {
            rarity = EnumRarity.RARE
        }.build()

        MaterialRegistry.EMERALD = RagiMaterial.Builder(41, "emerald", TypeRegistry.CRYSTAL).setComponents(listOf(ElementRegistry.BERYLLIUM to 3, ElementRegistry.ALUMINIUM to 2, ElementRegistry.SILICON to 6, ElementRegistry.OXYGEN to 18)).apply {
            color = RagiColor.GREEN
            crystalType = EnumCrystalType.EMERALD
        }.build()

        MaterialRegistry.AQUAMARINE = RagiMaterial.Builder(42, "aquamarine", TypeRegistry.CRYSTAL).setComponents(listOf(ElementRegistry.BERYLLIUM to 3, ElementRegistry.ALUMINIUM to 2, ElementRegistry.SILICON to 6, ElementRegistry.OXYGEN to 18)).apply {
            color = RagiColor.AQUA
            crystalType = EnumCrystalType.EMERALD
        }.build()

        //50 ~ 59: Boron
        MaterialRegistry.BORON = RagiMaterial.Builder(50, "boron", TypeRegistry.METALLOID).setSimple(ElementRegistry.BORON to 1).build()

        MaterialRegistry.BORAX = RagiMaterial.Builder(51, "borax", TypeRegistry.DUST).setComponents(listOf(ElementRegistry.SODIUM to 2, ElementRegistry.HYDROGEN to 20, ElementRegistry.BORON to 4, ElementRegistry.OXYGEN to 17)).build()

        MaterialRegistry.BORON_OXIDE = RagiMaterial.Builder(52, "boron_oxide", TypeRegistry.DUST).setComponents(listOf(ElementRegistry.BORON to 2, ElementRegistry.OXYGEN to 3)).build()

        //60 ~ 69: Carbon
        MaterialRegistry.CARBON = RagiMaterial.Builder(60, "carbon", TypeRegistry.METALLOID).setSimple(ElementRegistry.CARBON to 1).build()

        MaterialRegistry.CARBON_MONOXIDE = RagiMaterial.Builder(61, "carbon_monoxide", TypeRegistry.GAS).setComponents(listOf(ElementRegistry.CARBON to 1, ElementRegistry.OXYGEN to 1)).build()

        MaterialRegistry.CARBON_DIOXIDE = RagiMaterial.Builder(62, "carbon_dioxide", TypeRegistry.GAS).setComponents(listOf(ElementRegistry.CARBON to 1, ElementRegistry.OXYGEN to 2)).build()

        MaterialRegistry.COAL = RagiMaterial.Builder(63, "coal", TypeRegistry.FUEL).setSimple(ElementRegistry.CARBON to 1).apply {
            burnTime = 200 * 8
            crystalType = EnumCrystalType.COAL
        }.build()

        MaterialRegistry.CHARCOAL = RagiMaterial.Builder(64, "charcoal", TypeRegistry.FUEL).setSimple(ElementRegistry.CARBON to 1).apply {
            burnTime = 200 * 8
            crystalType = EnumCrystalType.COAL
        }.build()

        MaterialRegistry.COKE = RagiMaterial.Builder(65, "coke", TypeRegistry.FUEL).setSimple(ElementRegistry.CARBON to 1).apply {
            burnTime = 200 * 16
            color = RagiColor.DARK_GRAY
            crystalType = EnumCrystalType.COAL
        }.build()

        MaterialRegistry.ANTHRACITE = RagiMaterial.Builder(66, "anthracite", TypeRegistry.FUEL).setSimple(ElementRegistry.CARBON to 1).apply {
            burnTime = 200 * 24
            crystalType = EnumCrystalType.COAL
            color = ColorManager.mixColor(MaterialRegistry.COAL.color to 5, RagiColor.DARK_BLUE to 1)
        }.build()

        MaterialRegistry.LIGNITE = RagiMaterial.Builder(67, "lignite", TypeRegistry.FUEL).setSimple(ElementRegistry.CARBON to 1).apply {
            burnTime = 200 * 4
            crystalType = EnumCrystalType.COAL
            color = ColorManager.mixColor(MaterialRegistry.COAL.color to 5, RagiColor.DARK_RED to 1)
        }.build()

        MaterialRegistry.PEAT = RagiMaterial.Builder(68, "peat", TypeRegistry.FUEL).setSimple(ElementRegistry.CARBON to 1).apply {
            burnTime = 200 * 2
            crystalType = EnumCrystalType.COAL
            color = ColorManager.mixColor(MaterialRegistry.COAL.color to 5, RagiColor.DARK_GREEN to 1)
        }.build()

        MaterialRegistry.DIAMOND = RagiMaterial.Builder(69, "diamond", TypeRegistry.CRYSTAL).setSimple(ElementRegistry.CARBON to 1).apply {
            crystalType = EnumCrystalType.DIAMOND
            color = RagiColor.AQUA
        }.build()

        //70 ~ 79: Nitrogen
        MaterialRegistry.NITROGEN = RagiMaterial.Builder(70, "nitrogen", TypeRegistry.GAS).setSimple(ElementRegistry.NITROGEN to 2).build()

        MaterialRegistry.NITRIC_ACID = RagiMaterial.Builder(71, "nitric_acid", TypeRegistry.LIQUID).setComponents(listOf(ElementRegistry.HYDROGEN to 1, MaterialRegistry.NITRATE to 1)).build()

        MaterialRegistry.NITER = RagiMaterial.Builder(72, "niter", TypeRegistry.DUST).setComponents(listOf(ElementRegistry.POTASSIUM to 1, MaterialRegistry.NITRATE to 1)).apply {
            color = RagiColor.WHITE
            oredictAlt = "Saltpeter"
        }.build()

        //80 ~ 89: Oxygen
        MaterialRegistry.OXYGEN = RagiMaterial.Builder(80, "oxygen", TypeRegistry.GAS).setSimple(ElementRegistry.OXYGEN to 2).build()

        //90 ~ 99: Fluorite
        MaterialRegistry.FLUORINE = RagiMaterial.Builder(90, "fluorine", TypeRegistry.GAS).setSimple(ElementRegistry.FLUORINE to 2).build()

        MaterialRegistry.FLUORITE = RagiMaterial.Builder(91, "fluorite", TypeRegistry.CRYSTAL).setComponents(listOf(ElementRegistry.CALCIUM to 1, ElementRegistry.FLUORINE to 2)).apply {
            color = ColorManager.mixColor(RagiColor.GREEN, RagiColor.AQUA)
            crystalType = EnumCrystalType.DIAMOND
        }.build()

        MaterialRegistry.HYDROGEN_FLUORIDE = RagiMaterial.Builder(92, "hydrogen_fluoride", TypeRegistry.GAS).setComponents(listOf(ElementRegistry.HYDROGEN to 1, ElementRegistry.FLUORINE to 1)).build()

        MaterialRegistry.CRYOLITE = RagiMaterial.Builder(93, "cryolite", TypeRegistry.CRYSTAL).setComponents(listOf(ElementRegistry.SODIUM to 3, ElementRegistry.ALUMINIUM to 1, ElementRegistry.FLUORINE to 6)).apply {
            color = RagiColor.WHITE
            crystalType = EnumCrystalType.DIAMOND
        }.build()

        //100 ~ 109: Misc (1)
        MaterialRegistry.WOOD = RagiMaterial.Builder(100, "wood", TypeRegistry.WOOD).setComponents(listOf(ElementRegistry.CARBON to 1, ElementRegistry.HYDROGEN to 1, ElementRegistry.OXYGEN to 1)).apply {
            color = ColorManager.mixColor(RagiColor.DARK_GRAY to 2, RagiColor.RED to 1, RagiColor.YELLOW to 1)
        }.setMixture().build()

        MaterialRegistry.LAPIS = RagiMaterial.Builder(101, "lapis", TypeRegistry.CRYSTAL).setComponents(listOf(RagiMaterial.Formula("?").build() to 1)).setMixture().apply {
            color = RagiColor.BLUE
            crystalType = EnumCrystalType.LAPIS
        }.build()

        MaterialRegistry.REDSTONE = RagiMaterial.Builder(102, "redstone", TypeRegistry.DUST).setSimple(ElementRegistry.REDSTONE to 1).build()

        MaterialRegistry.GLOWSTONE = RagiMaterial.Builder(103, "glowstone", TypeRegistry.DUST).setSimple(ElementRegistry.GLOWSTONE to 1).build()

        MaterialRegistry.ENDER_PEARL = RagiMaterial.Builder(104, "ender_pearl", TypeRegistry.DUST).setSimple(ElementRegistry.ENDER to 1).apply {
            oredictAlt = "Ender"
        }.build()
    }
}