package hiiragi283.ragi_materials.material

import hiiragi283.ragi_materials.client.render.color.RagiColorManager

class OreProperty(val first: RagiMaterial, val second: RagiMaterial = RagiMaterial.EMPTY, val third: RagiMaterial = RagiMaterial.EMPTY) {

    companion object {
        val mapVanilla = mapOf(
                "Gold" to OreProperty(RagiMaterial.EMPTY, RagiMaterial.EMPTY, RagiMaterial.EMPTY),
                "Iron" to OreProperty(MaterialRegistry.IRON, MaterialRegistry.IRON, MaterialRegistry.IRON),
                "Coal" to OreProperty(MaterialRegistry.COAL, MaterialRegistry.COAL, MaterialRegistry.COAL),
                "Lapis" to OreProperty(MaterialRegistry.LAPIS, MaterialRegistry.LAPIS, MaterialRegistry.LAPIS),
                "Diamond" to OreProperty(MaterialRegistry.DIAMOND, MaterialRegistry.DIAMOND, MaterialRegistry.DIAMOND),
                "Redstone" to OreProperty(MaterialRegistry.REDSTONE, MaterialRegistry.REDSTONE, MaterialRegistry.REDSTONE),
                "Emerald" to OreProperty(MaterialRegistry.EMERALD, MaterialRegistry.EMERALD, MaterialRegistry.EMERALD),
                "Quartz" to OreProperty(MaterialRegistry.QUARTZ, MaterialRegistry.QUARTZ, MaterialRegistry.QUARTZ)
        )
        val listVanilla = mapVanilla.toList()

        val mapOre1 = mapOf(
                "Salt" to OreProperty(MaterialRegistry.SALT, MaterialRegistry.BORAX, MaterialRegistry.SPODUMENE),
                "Beryl" to OreProperty(MaterialRegistry.EMERALD, MaterialRegistry.AQUAMARINE),
                "Fluorite" to OreProperty(MaterialRegistry.FLUORITE, MaterialRegistry.CRYOLITE),
                "Laterite" to OreProperty(MaterialRegistry.HEMATITE, MaterialRegistry.BAUXITE, MaterialRegistry.RUTILE),
                "Corundum" to OreProperty(MaterialRegistry.RUBY, MaterialRegistry.SAPPHIRE),
                "Sulfur" to OreProperty(MaterialRegistry.SULFUR, MaterialRegistry.PHOSPHORUS),
                "Niter" to OreProperty(MaterialRegistry.NITER, MaterialRegistry.MAGNESITE),
                "Lime" to OreProperty(MaterialRegistry.LIME, MaterialRegistry.GYPSUM),
                "Nodule" to OreProperty(MaterialRegistry.IRON, MaterialRegistry.MANGANESE, MaterialRegistry.COBALT),
                "Magnetite" to OreProperty(MaterialRegistry.MAGNETITE, MaterialRegistry.CHROMIUM),
                "Copper" to OreProperty(MaterialRegistry.COPPER, MaterialRegistry.NICKEL, MaterialRegistry.COBALT),
                "Sphalerite" to OreProperty(MaterialRegistry.ZINC)
        )
        val listOre1 = mapOre1.toList()
    }

    fun getColor() = RagiColorManager.mixColor(*arrayOf(first.color, second.color, third.color))

}