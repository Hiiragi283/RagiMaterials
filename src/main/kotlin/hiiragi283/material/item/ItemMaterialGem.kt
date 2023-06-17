package hiiragi283.material.item

import hiiragi283.material.material.CrystalType
import hiiragi283.material.material.HiiragiMaterial
import hiiragi283.material.material_part.MaterialPartRegistry
import hiiragi283.material.part.PartRegistry
import hiiragi283.material.util.CraftingBuilder
import hiiragi283.material.util.RagiIngredient
import hiiragi283.material.util.append
import net.minecraft.client.renderer.block.model.ModelResourceLocation
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraftforge.client.model.ModelLoader
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

object ItemMaterialGem : ItemMaterialBase(PartRegistry.GEM) {

    override fun isMatch(material: HiiragiMaterial): Boolean = super.isMatch(material) && material.isGem()

    override fun materialRecipe(material: HiiragiMaterial) {
        CraftingBuilder(ItemStack(this, 9, material.index))
            .addIngredient(RagiIngredient("block${material.getOreDictName()}"))
            .buildShapeless()
    }

    //    IRMEntry    //

    @SideOnly(Side.CLIENT)
    override fun registerModel() {

        fun getLocation(crystalType: CrystalType) = registryName!!.append("_" + crystalType.texture)

        fun getModelLocation(crystalType: CrystalType) = ModelResourceLocation(getLocation(crystalType), "inventory")

        val locations: MutableList<ResourceLocation> = mutableListOf()

        CrystalType.values()
            .filter { it.texture.isNotEmpty() }
            .map { getLocation(it) }
            .forEach { locations.add(it) }

        ModelLoader.registerItemVariants(this, *locations.toTypedArray())

        ModelLoader.setCustomMeshDefinition(this) {
            val material = MaterialPartRegistry.getMaterialPart(it).material
            if (isMatch(material)) getModelLocation(material.crystalType)
            else getModelLocation(CrystalType.CUBIC)
        }
    }

}