package hiiragi283.material.item_old

import hiiragi283.material.RagiMaterials
import hiiragi283.material.material_old.MaterialRegistryOld
import hiiragi283.material.material_old.RagiMaterialOld
import hiiragi283.material.material_old.type.EnumCrystalType
import hiiragi283.material.registry.RagiRegistry
import hiiragi283.material.util.RagiIngredient
import hiiragi283.material.util.addCraftingShapeless
import net.minecraft.client.renderer.block.model.ModelResourceLocation
import net.minecraft.util.NonNullList
import net.minecraftforge.client.model.ModelLoader
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

object ItemMaterialCrystal : ItemMaterial("gem") {

    @SideOnly(Side.CLIENT)
    override fun registerModel() {

        ModelLoader.registerItemVariants(
            this,
            ModelResourceLocation("${RagiMaterials.MODID}:crystal_coal", "inventory"),
            ModelResourceLocation("${RagiMaterials.MODID}:crystal_cubic", "inventory"),
            ModelResourceLocation("${RagiMaterials.MODID}:crystal_diamond", "inventory"),
            ModelResourceLocation("${RagiMaterials.MODID}:crystal_emerald", "inventory"),
            ModelResourceLocation("${RagiMaterials.MODID}:crystal_lapis", "inventory"),
            ModelResourceLocation("${RagiMaterials.MODID}:crystal_quartz", "inventory"),
            ModelResourceLocation("${RagiMaterials.MODID}:crystal_ruby", "inventory")
        )
        ModelLoader.setCustomMeshDefinition(this) { stack ->
            var result = ModelResourceLocation("${RagiMaterials.MODID}:crystal_cubic", "inventory")
            val item = stack.item
            if (item is ItemMaterial) {
                val material = MaterialRegistryOld.getMaterial(stack)
                if (material.crystalType != EnumCrystalType.NONE) result =
                    ModelResourceLocation(
                        "${RagiMaterials.MODID}:crystal_${material.crystalType.texture}",
                        "inventory"
                    )
            }
            result
        }
    }

    override fun registerRecipeMaterial(material: RagiMaterialOld) {
        //block -> gemのレシピを登録
        addCraftingShapeless(
            getStack(material, 9),
            NonNullList.withSize(1, RagiIngredient(RagiRegistry.ITEM_PART_BLOCK.getOreDict(material))),
            "${RagiMaterials.MODID}:block_to_crystal_${material.index}"
        )
    }
}