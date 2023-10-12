package hiiragi283.material.item.material

import hiiragi283.material.api.item.MaterialItemNew
import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.api.registry.HiiragiRegistries
import hiiragi283.material.api.shape.HiiragiShapeType
import hiiragi283.material.api.shape.HiiragiShapeTypes
import hiiragi283.material.api.shape.HiiragiShapes
import hiiragi283.material.util.CraftingBuilder
import hiiragi283.material.util.append
import hiiragi283.material.util.itemStack
import hiiragi283.material.util.toModelLocation
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraftforge.client.model.ModelLoader
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

object MaterialItemGem : MaterialItemNew(HiiragiShapes.GEM) {

    override fun addRecipes(material: HiiragiMaterial) {
        // 1x Block -> 9x Gem
        if (HiiragiShapes.BLOCK.isValid(material)) {
            CraftingBuilder(itemStack(material, 9))
                .addIngredient(HiiragiShapes.BLOCK.getOreDict(material))
                .build()
        }
        //Grinder Recipe
        addGrinderRecipe(material)
    }

    @SideOnly(Side.CLIENT)
    override fun registerModel() {

        fun gemLocation(shapeType: HiiragiShapeType) = registryName!!.append("_${shapeType.name}")

        ModelLoader.registerItemVariants(
            this,
            registryName!!,
            gemLocation(HiiragiShapeTypes.GEM_AMORPHOUS),
            gemLocation(HiiragiShapeTypes.GEM_COAL),
            gemLocation(HiiragiShapeTypes.GEM_CUBIC),
            gemLocation(HiiragiShapeTypes.GEM_DIAMOND),
            gemLocation(HiiragiShapeTypes.GEM_EMERALD),
            gemLocation(HiiragiShapeTypes.GEM_LAPIS),
            gemLocation(HiiragiShapeTypes.GEM_QUARTZ),
            gemLocation(HiiragiShapeTypes.GEM_RUBY)
        )

        ModelLoader.setCustomMeshDefinition(this) { stack: ItemStack ->
            HiiragiRegistries.MATERIAL_INDEX.getValue(stack.metadata)?.shapeType
                ?.let(::gemLocation)
                ?.let(ResourceLocation::toModelLocation)
                ?: registryName!!.toModelLocation()
        }

    }

}