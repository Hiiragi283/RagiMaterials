package ragi_materials.core.item

import net.minecraft.client.renderer.block.model.ModelResourceLocation
import net.minecraftforge.client.model.ModelLoader
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import ragi_materials.core.RagiMaterials
import ragi_materials.core.material.IMaterialItem
import ragi_materials.core.material.part.PartRegistry
import ragi_materials.core.material.type.EnumCrystalType
import ragi_materials.core.material.type.EnumMaterialType

object ItemMaterialBlock : ItemMaterial(PartRegistry.BLOCK) {

    @SideOnly(Side.CLIENT)
    override fun registerCustomModel() {

        val blockCrystal = ModelResourceLocation("${RagiMaterials.MOD_ID}:part", "block_crystal")
        val blockMaterial = ModelResourceLocation("${RagiMaterials.MOD_ID}:part", "block_material")
        val blockMetal = ModelResourceLocation("${RagiMaterials.MOD_ID}:part", "block_metal")

        ModelLoader.registerItemVariants(this, blockCrystal, blockMaterial, blockMetal)

        ModelLoader.setCustomMeshDefinition(this) { stack ->
            var result = blockMaterial
            val item = stack.item
            if (item is IMaterialItem) {
                val material = item.getMaterial(stack)
                if (material.crystalType != EnumCrystalType.NONE) {
                    if (material.crystalType != EnumCrystalType.COAL) result = blockCrystal
                } else if (EnumMaterialType.INGOT in material.type.types) result = blockMetal
            }
            result
        }
    }
}