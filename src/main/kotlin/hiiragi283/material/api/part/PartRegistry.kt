package hiiragi283.material.api.part

import hiiragi283.material.RMItems
import hiiragi283.material.RagiMaterials
import hiiragi283.material.api.material.CrystalType
import hiiragi283.material.api.material.MaterialRegistry
import hiiragi283.material.api.material_part.MaterialPartRegistry
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
        REGISTRY[name]?.let { RagiMaterials.LOGGER.warn("The part: $name will be overrided!") }
        REGISTRY[name] = part
    }

    //    Parts    //

    @JvmField
    val COMMON: (Double) -> HiiragiPart = { partOf("common", it) }

    @JvmField
    val BLOCK = partOf("block", 9.0) {
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
    val BOTTLE = partOf("bottle", 1.0)

    @JvmField
    val CLUMP = partOf("clump", 1.0)

    @JvmField
    val CRYSTAL = partOf("crystal", 1.0)

    @JvmField
    val DUST = partOf("dust", 1.0) {
        isMatch = { it.isSolid() }
        recipe = { item, material ->
            CraftingBuilder(ItemStack(item, 1, material.index))
                .setPattern("AAA", "AAA", "AAA")
                .setIngredient('A', "dustTiny${material.getOreDictName()}")
                .buildShaped()
        }
    }

    @JvmField
    val DUST_DIRTY = partOf("dust_dirty", 1.0)

    @JvmField
    val DUST_TINY = partOf("dust_tiny", 0.1) {
        isMatch = { it.isSolid() }
        recipe = { item, material ->
            CraftingBuilder(ItemStack(item, 9, material.index))
                .addIngredient(RagiIngredient("dust${material.getOreDictName()}"))
                .buildShapeless()
        }
    }

    @JvmField
    val GEAR = partOf("gear", 4.0) {
        isMatch = { it.isSolid() && it.isMetal() }
        recipe = { item, material ->
            CraftingBuilder(ItemStack(item, 1, material.index))
                .setPattern(" A ", "A A", " A ")
                .setIngredient('A', "ingot${material.getOreDictName()}")
                .buildShaped()
        }
    }

    @JvmField
    val GEM = partOf("gem", 1.0) {
        isMatch = { it.isSolid() && it.isGem() }
        model = { item ->

            val locations: MutableList<ResourceLocation> = mutableListOf()

            CrystalType.values()
                .filter { it.texture.isNotEmpty() }
                .map { item.registryName!!.append("_" + it.texture) }
                .forEach { locations.add(it) }

            ModelLoader.registerItemVariants(item, *locations.toTypedArray())

            ModelLoader.setCustomMeshDefinition(item) { stack ->
                val material = MaterialRegistry.getMaterial(stack.metadata)
                val type = if (item.part.isMatch(material)) material.crystalType else CrystalType.CUBIC
                type.getLocation(item)
            }
        }
        recipe = { item, material ->
            CraftingBuilder(ItemStack(item, 9, material.index))
                .addIngredient(RagiIngredient("block${material.getOreDictName()}"))
                .buildShapeless()
        }
    }


    @JvmField
    val INGOT = partOf("ingot", 1.0) {
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
    val LOG = partOf("log", 4.0)

    @JvmField
    val NUGGET = partOf("nugget", 0.1) {
        isMatch = { it.isSolid() && it.isMetal() }
        recipe = { item, material ->
            CraftingBuilder(ItemStack(item, 9, material.index))
                .addIngredient(RagiIngredient("ingot${material.getOreDictName()}"))
                .buildShapeless()
        }
    }

    @JvmField
    val ORE = partOf("ore", -1.0)

    @JvmField
    val PLANK = partOf("plank", 1.0)

    @JvmField
    val PLATE = partOf("plate", 1.0) {
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
    val SHARD = partOf("shard", 1.0)

    @JvmField
    val STICK = partOf("stick", 0.5) {
        isMatch = { it.isSolid() && it.isMetal() }
        recipe = { item, material ->
            if (material.isMetal()) {
                CraftingBuilder(ItemStack(item, 4, material.index))
                    .setPattern("AB", "A ")
                    .setIngredient('A', "ingot${material.getOreDictName()}")
                    .setIngredient('B', ItemStack(RMItems.FORGE_HAMMER, 1, OreDictionary.WILDCARD_VALUE))
                    .buildShaped()
            }
        }
    }

    @JvmField
    val STONE = partOf("stone", 1.0)

    fun init() {
        val fields = this::class.java.declaredFields
        fields.forEach { it.isAccessible = true }
        fields.map { it.get(this) }
            .filterIsInstance<HiiragiPart>()
            .forEach { registerPart(it) }
    }

}