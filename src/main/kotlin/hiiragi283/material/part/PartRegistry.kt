package hiiragi283.material.part

import hiiragi283.material.RagiMaterials
import hiiragi283.material.init.RMItems
import hiiragi283.material.material.CrystalType
import hiiragi283.material.material_part.MaterialPartRegistry
import hiiragi283.material.util.CraftingBuilder
import hiiragi283.material.util.RagiIngredient
import hiiragi283.material.util.append
import hiiragi283.material.util.toLocation
import net.minecraft.client.renderer.block.model.ModelResourceLocation
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraftforge.client.model.ModelLoader
import net.minecraftforge.oredict.OreDictionary

object PartRegistry {

    private val REGISTRY: HashMap<String, HiiragiPart> = hashMapOf()

    @JvmStatic
    fun getParts(): Collection<HiiragiPart> = REGISTRY.values

    @JvmStatic
    fun getPart(name: String) = REGISTRY.getOrDefault(name, HiiragiPart.EMPTY)

    @JvmStatic
    fun registerPart(part: HiiragiPart) {
        val name = part.name
        //同じ名前で登録されていた場合，登録せずに警告を表示する
        REGISTRY.putIfAbsent(name, part)
            ?.let { RagiMaterials.LOGGER.warn("The part: $name has already registered!") }
    }

    //    Parts    //

    @JvmField
    val BLOCK = HiiragiPart.Builder("block", 9.0).build {
        isMatch = { it.isSolid() && (it.isMetal() || it.isGem()) }
        model = {
            val common = ModelResourceLocation(it.registryName!!.append("_material"), "inventory")
            val gem = ModelResourceLocation(it.registryName!!.append("_gem"), "inventory")
            val metal = ModelResourceLocation(it.registryName!!.append("_metal"), "inventory")

            ModelLoader.registerItemVariants(it, common, gem, metal)

            ModelLoader.setCustomMeshDefinition(it) { stack ->
                val material = MaterialPartRegistry.getMaterialPart(stack).material
                if (material.isMetal()) metal
                else if (material.isGem()) gem
                else common
            }
        }
        recipe = { item, material ->
            if (material.isSolid()) {
                if (material.isMetal()) {
                    CraftingBuilder(ItemStack(item, 1, material.index))
                        .setPattern("AAA", "AAA", "AAA")
                        .setIngredient('A', "ingot${material.getOreDictName()}")
                        .buildShaped()
                } else if (material.isGem()) {
                    CraftingBuilder(ItemStack(item, 1, material.index))
                        .setPattern("AAA", "AAA", "AAA")
                        .setIngredient('A', "gem${material.getOreDictName()}")
                        .buildShaped()
                }
            }
        }
    }

    @JvmField
    val BOTTLE = HiiragiPart.Builder("bottle", 1.0).build()

    @JvmField
    val DUST = HiiragiPart.Builder("dust", 1.0).build {
        isMatch = { it.isSolid() }
        recipe = { item, material ->
            CraftingBuilder(ItemStack(item, 1, material.index))
                .setPattern("AAA", "AAA", "AAA")
                .setIngredient('A', "dustTiny${material.getOreDictName()}")
                .buildShaped()
        }
    }

    @JvmField
    val DUST_TINY = HiiragiPart.Builder("dust_tiny", 0.1).build {
        isMatch = { it.isSolid() }
        recipe = { item, material ->
            CraftingBuilder(ItemStack(item, 9, material.index))
                .addIngredient(RagiIngredient("dust${material.getOreDictName()}"))
                .buildShapeless()
        }
    }

    @JvmField
    val GEAR = HiiragiPart.Builder("gear", 4.0).build {
        isMatch = { it.isSolid() && it.isMetal() }
        recipe = { item, material ->
            CraftingBuilder(ItemStack(item, 1, material.index))
                .setPattern(" A ", "A A", " A ")
                .setIngredient('A', "ingot${material.getOreDictName()}")
                .buildShaped()
        }
    }

    @JvmField
    val GEM = HiiragiPart.Builder("gem", 1.0).build {
        isMatch = { it.isSolid() && it.isGem() }
        model = { item ->
            fun getLocation(crystalType: CrystalType) = item.registryName!!.append("_" + crystalType.texture)

            fun getModelLocation(crystalType: CrystalType) =
                ModelResourceLocation(getLocation(crystalType), "inventory")

            val locations: MutableList<ResourceLocation> = mutableListOf()

            CrystalType.values()
                .filter { it.texture.isNotEmpty() }
                .map { getLocation(it) }
                .forEach { locations.add(it) }

            ModelLoader.registerItemVariants(item, *locations.toTypedArray())

            ModelLoader.setCustomMeshDefinition(item) { stack ->
                val material = MaterialPartRegistry.getMaterialPart(stack).material
                if (item.part.isMatch(material)) getModelLocation(material.crystalType)
                else getModelLocation(CrystalType.CUBIC)
            }
        }
        recipe = { item, material ->
            CraftingBuilder(ItemStack(item, 9, material.index))
                .addIngredient(RagiIngredient("block${material.getOreDictName()}"))
                .buildShapeless()
        }
    }


    @JvmField
    val INGOT = HiiragiPart.Builder("ingot", 1.0).build {
        isMatch = { it.isSolid() && it.isMetal() }
        recipe = { item, material ->
            //nugget -> ingot
            CraftingBuilder(ItemStack(item, 1, material.index))
                .setPattern("AAA", "AAA", "AAA")
                .setIngredient('A', "nugget${material.getOreDictName()}")
                .buildShaped()
            //block -> ingot
            val ingot9 = ItemStack(item, 9, material.index)
            CraftingBuilder(ingot9.toLocation().append("_alt"), ingot9)
                .addIngredient(RagiIngredient("block${material.getOreDictName()}"))
                .buildShapeless()
        }
    }

    @JvmField
    val NUGGET = HiiragiPart.Builder("nugget", 0.1).build {
        isMatch = { it.isSolid() && it.isMetal() }
        recipe = { item, material ->
            CraftingBuilder(ItemStack(item, 9, material.index))
                .addIngredient(RagiIngredient("ingot${material.getOreDictName()}"))
                .buildShapeless()
        }
    }

    @JvmField
    val PLATE = HiiragiPart.Builder("plate", 1.0).build {
        isMatch = { it.isSolid() && (it.isMetal() || it.isGem()) }
        recipe = { item, material ->
            if (material.isMetal()) {
                CraftingBuilder(ItemStack(item, 1, material.index))
                    .addIngredient(RagiIngredient("ingot${material.getOreDictName()}"))
                    .addIngredient(RagiIngredient(ItemStack(RMItems.FORGE_HAMMER, 1, OreDictionary.WILDCARD_VALUE)))
                    .buildShapeless()
            }
        }
    }

    @JvmField
    val STICK = HiiragiPart.Builder("stick", 0.5).build {
        isMatch = { it.isSolid() && it.isMetal() }
    }

    fun init() {
        registerPart(BLOCK)
        registerPart(GEM)
        registerPart(DUST)
        registerPart(DUST_TINY)
        registerPart(GEAR)
        registerPart(INGOT)
        registerPart(NUGGET)
        registerPart(PLATE)
        registerPart(STICK)
    }

}