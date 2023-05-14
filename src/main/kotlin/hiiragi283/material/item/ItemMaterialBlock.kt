package hiiragi283.material.item

import hiiragi283.material.RagiMaterials
import hiiragi283.material.material.MaterialRegistry
import hiiragi283.material.material.RagiMaterial
import hiiragi283.material.material.type.EnumCrystalType
import hiiragi283.material.material.type.TypeRegistry
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

    override fun registerRecipeMaterial(material: RagiMaterial) {
        //INGOTをもつ素材の場合
        if (RagiRegistry.ITEM_PART_INGOT in material.type.getParts()) {
            //ingot -> blockのレシピを登録
            addCraftingShaped(
                getStack(material),
                NonNullList.withSize(9, RagiIngredient(RagiRegistry.ITEM_PART_INGOT.getOreDict(material))),
                3,
                3,
                "${RagiMaterials.MOD_ID}:ingot_to_block_${material.index}"
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
                "${RagiMaterials.MOD_ID}:crystal_to_block_${material.index}"
            )
        }
    }
}