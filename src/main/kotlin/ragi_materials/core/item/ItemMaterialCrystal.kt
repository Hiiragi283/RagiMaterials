package ragi_materials.core.item

import net.minecraft.client.renderer.block.model.ModelResourceLocation
import net.minecraftforge.client.model.ModelLoader
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import ragi_materials.core.RagiMaterials
import ragi_materials.core.material.IMaterialItem
import ragi_materials.core.material.part.PartRegistry
import ragi_materials.core.material.type.EnumCrystalType

object ItemMaterialCrystal : ItemMaterial(PartRegistry.CRYSTAL) {

    @SideOnly(Side.CLIENT)
    override fun registerCustomModel() {

        ModelLoader.registerItemVariants(
                this,
                ModelResourceLocation("${RagiMaterials.MOD_ID}:crystal", "coal"),
                ModelResourceLocation("${RagiMaterials.MOD_ID}:crystal", "cubic"),
                ModelResourceLocation("${RagiMaterials.MOD_ID}:crystal", "diamond"),
                ModelResourceLocation("${RagiMaterials.MOD_ID}:crystal", "emerald"),
                ModelResourceLocation("${RagiMaterials.MOD_ID}:crystal", "lapis"),
                ModelResourceLocation("${RagiMaterials.MOD_ID}:crystal", "quartz"),
                ModelResourceLocation("${RagiMaterials.MOD_ID}:crystal", "ruby")
        )
        ModelLoader.setCustomMeshDefinition(this) { stack ->
            var result = ModelResourceLocation("${RagiMaterials.MOD_ID}:crystal", EnumCrystalType.CUBIC.texture)
            val item = stack.item
            if (item is IMaterialItem) {
                val material = item.getMaterial(stack)
                if (material.crystalType != EnumCrystalType.NONE) result = ModelResourceLocation("${RagiMaterials.MOD_ID}:crystal", material.crystalType.texture)
            }
            result
        }
    }
}