package hiiragi283.material.item_old

import hiiragi283.material.RagiMaterials
import hiiragi283.material.material_old.MaterialRegistryOld
import hiiragi283.material.material_old.RagiMaterialOld
import hiiragi283.material.material_old.type.EnumCrystalType
import hiiragi283.material.material_old.type.TypeRegistry
import hiiragi283.material.registry.RagiRegistry
import hiiragi283.material.util.RagiIngredient
import hiiragi283.material.util.addCraftingShaped
import net.minecraft.client.renderer.block.model.ModelResourceLocation
import net.minecraft.util.NonNullList
import net.minecraftforge.client.model.ModelLoader
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

object ItemMaterialBlock : ItemMaterial("block", 9.0f) {

    @SideOnly(Side.CLIENT)
    override fun registerModel() {

        val blockCrystal = ModelResourceLocation("${RagiMaterials.MODID}:block_crystal", "inventory")
        val blockMaterial = ModelResourceLocation("${RagiMaterials.MODID}:block_material", "inventory")
        val blockMetal = ModelResourceLocation("${RagiMaterials.MODID}:block_metal", "inventory")

        ModelLoader.registerItemVariants(this, blockCrystal, blockMaterial, blockMetal)

        ModelLoader.setCustomMeshDefinition(this) { stack ->
            var result = blockMaterial
            val item = stack.item
            if (item is ItemMaterial) {
                val material = MaterialRegistryOld.getMaterial(stack)
                if (material.crystalType != EnumCrystalType.NONE) {
                    if (material.crystalType != EnumCrystalType.COAL) result = blockCrystal
                } else if (material.type == TypeRegistry.METAL) result = blockMetal
            }
            result
        }
    }

    override fun registerRecipeMaterial(material: RagiMaterialOld) {
        //INGOTをもつ素材の場合
        if (RagiRegistry.ITEM_PART_INGOT in material.type.getParts()) {
            //ingot -> blockのレシピを登録

            addCraftingShaped(
                getStack(material),
                NonNullList.withSize(9, RagiIngredient(RagiRegistry.ITEM_PART_INGOT.getOreDict(material))),
                3,
                3,
                "${RagiMaterials.MODID}:ingot_to_block_${material.index}"
            )
        }
        //CRYSTALをもつ素材の場合
        else if (RagiRegistry.ITEM_PART_CRYSTAL in material.type.getParts()) {
            //gem -> blockのレシピを登録
            addCraftingShaped(
                getStack(material),
                NonNullList.withSize(9, RagiIngredient(RagiRegistry.ITEM_PART_CRYSTAL.getOreDict(material))),
                3,
                3,
                "${RagiMaterials.MODID}:crystal_to_block_${material.index}"
            )
        }
    }
}