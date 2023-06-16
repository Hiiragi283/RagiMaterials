package hiiragi283.material.item

import hiiragi283.material.material.HiiragiMaterial
import hiiragi283.material.material_part.MaterialPartRegistry
import hiiragi283.material.part.PartRegistry
import hiiragi283.material.util.CraftingBuilder
import hiiragi283.material.util.append
import net.minecraft.client.renderer.block.model.ModelResourceLocation
import net.minecraft.item.ItemStack
import net.minecraftforge.client.model.ModelLoader
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

object ItemMaterialBlock : ItemMaterialBase(PartRegistry.BLOCK) {

    override fun isMatch(material: HiiragiMaterial): Boolean =
        super.isMatch(material) && (material.isMetal() || material.isGem())

    override fun materialRecipe(material: HiiragiMaterial) {
        if (!material.isSolid()) return
        if (material.isMetal()) {
            CraftingBuilder(ItemStack(this, 1, material.index))
                .setPattern("AAA", "AAA", "AAA")
                .setIngredient('A', "ingot${material.getOreDictName()}")
                .buildShaped()
        } else if (material.isGem()) {
            CraftingBuilder(ItemStack(this, 1, material.index))
                .setPattern("AAA", "AAA", "AAA")
                .setIngredient('A', "gem${material.getOreDictName()}")
                .buildShaped()
        }
    }

    @SideOnly(Side.CLIENT)
    override fun registerModel() {

        val common = ModelResourceLocation(registryName!!.append("_material"), "inventory")
        val gem = ModelResourceLocation(registryName!!.append("_gem"), "inventory")
        val metal = ModelResourceLocation(registryName!!.append("_metal"), "inventory")

        ModelLoader.registerItemVariants(this, common, gem, metal)

        ModelLoader.setCustomMeshDefinition(this) {
            val material = MaterialPartRegistry.getMaterialPart(it).material
            if (material.isMetal()) metal
            else if (material.isGem()) gem
            else common
        }

    }

}