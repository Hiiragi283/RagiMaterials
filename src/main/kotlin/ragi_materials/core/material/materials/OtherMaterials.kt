package ragi_materials.core.material.materials

import net.minecraftforge.fluids.FluidRegistry
import ragi_materials.core.material.ElementRegistry
import ragi_materials.core.material.MaterialRegistry
import ragi_materials.core.material.RagiMaterial
import ragi_materials.core.material.type.EnumCrystalType
import ragi_materials.core.material.type.TypeRegistry
import ragi_materials.core.util.ColorUtil
import ragi_materials.core.util.RagiColor

object OtherMaterials {

    fun load() {

        MaterialRegistry.STONE = RagiMaterial.Builder(1000, "stone", TypeRegistry.STONE).setSimple(MaterialRegistry.SILICON_DIOXIDE to 1).apply {
            color = RagiColor.GRAY
        }.buildAndRegister()

        MaterialRegistry.LAVA = RagiMaterial.Builder(1001, "lava", TypeRegistry.INTERNAL).setSimple(MaterialRegistry.SILICON_DIOXIDE to 1).apply {
            color = ColorUtil.mixColor(RagiColor.DARK_RED, RagiColor.GOLD)
            tempMelt = FluidRegistry.LAVA.temperature
        }.buildAndRegister()

        MaterialRegistry.WOOD = RagiMaterial.Builder(1002, "wood", TypeRegistry.WOOD).setComponents(listOf(ElementRegistry.CARBON to 1, ElementRegistry.HYDROGEN to 1, ElementRegistry.OXYGEN to 1)).apply {
            color = ColorUtil.mixColor(RagiColor.DARK_GRAY to 2, RagiColor.RED to 1, RagiColor.YELLOW to 1)
        }.setMixture().buildAndRegister()

        MaterialRegistry.LAPIS = RagiMaterial.Builder(1003, "lapis", TypeRegistry.CRYSTAL.enableOre()).setComponents(listOf(RagiMaterial.Formula("?").build() to 1)).setMixture().apply {
            color = RagiColor.BLUE
            crystalType = EnumCrystalType.LAPIS
        }.buildAndRegister()

        MaterialRegistry.OBSIDIAN = RagiMaterial.Builder(1004, "obsidian", TypeRegistry.DUST).setSimple(MaterialRegistry.SILICON_DIOXIDE to 1).apply {
            color = ColorUtil.mixColor(RagiColor.BLACK to 2, RagiColor.BLUE to 1, RagiColor.RED to 1)
        }.buildAndRegister()

        MaterialRegistry.REDSTONE = RagiMaterial.Builder(1005, "redstone", TypeRegistry.DUST.enableOre()).setSimple(ElementRegistry.REDSTONE to 1).buildAndRegister()

        MaterialRegistry.CLAY = RagiMaterial.Builder(1006, "clay", TypeRegistry.INGOT).setComponents(listOf(MaterialRegistry.ALUMINA to 1, MaterialRegistry.SILICON_DIOXIDE to 1)).apply {
            color = ColorUtil.mixColor(RagiColor.GRAY to 2, RagiColor.AQUA to 1)
        }.setMixture().buildAndRegister()

        MaterialRegistry.NETHERRACK = RagiMaterial.Builder(1007, "netherrack", TypeRegistry.DUST).setSimple(MaterialRegistry.SILICON_DIOXIDE to 1).apply {
            color = RagiColor.DARK_RED
        }.buildAndRegister()

        MaterialRegistry.SOUL_SAND = RagiMaterial.Builder(1008, "soul_sand", TypeRegistry.DUST).setSimple(MaterialRegistry.SILICON_DIOXIDE to 1).apply {
            color = ColorUtil.mixColor(RagiColor.BLACK to 5, RagiColor.GOLD to 1)
        }.buildAndRegister()

        MaterialRegistry.GLOWSTONE = RagiMaterial.Builder(1009, "glowstone", TypeRegistry.DUST.enableOre()).setSimple(ElementRegistry.GLOWSTONE to 1).buildAndRegister()

        MaterialRegistry.END_STONE = RagiMaterial.Builder(1010, "end_stone", TypeRegistry.DUST).setSimple(MaterialRegistry.SILICON_DIOXIDE to 1).apply {
            color = ColorUtil.mixColor(RagiColor.YELLOW to 1, RagiColor.WHITE to 3)
        }.buildAndRegister()

        MaterialRegistry.ENDER_PEARL = RagiMaterial.Builder(1011, "ender_pearl", TypeRegistry.DUST).setSimple(ElementRegistry.ENDER to 1).apply {
            oredictAlt = "Ender"
        }.buildAndRegister()

    }
}