package hiiragi283.material.item

import hiiragi283.material.RagiMaterials
import hiiragi283.material.material.MaterialRegistry
import hiiragi283.material.material.type.EnumCrystalType
import hiiragi283.material.material.type.TypeRegistry
import net.minecraft.client.renderer.block.model.ModelResourceLocation
import net.minecraftforge.client.model.ModelLoader
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

object ItemMaterialBlock : ItemMaterial("block", 9.0f) {

    @SideOnly(Side.CLIENT)
    override fun registerModel() {

        val blockCrystal = ModelResourceLocation("${RagiMaterials.MOD_ID}:block_crystal", "inventory")
        val blockMaterial = ModelResourceLocation("${RagiMaterials.MOD_ID}:block_material", "inventory")
        val blockMetal = ModelResourceLocation("${RagiMaterials.MOD_ID}:block_metal", "inventory")

        ModelLoader.registerItemVariants(this, blockCrystal, blockMaterial, blockMetal)

        ModelLoader.setCustomMeshDefinition(this) { stack ->
            var result = blockMaterial
            val item = stack.item
            if (item is ItemMaterial) {
                val material = MaterialRegistry.getMaterial(stack)
                if (material.crystalType != EnumCrystalType.NONE) {
                    if (material.crystalType != EnumCrystalType.COAL) result = blockCrystal
                } else if (material.type == TypeRegistry.METAL) result = blockMetal
            }
            result
        }
    }

}