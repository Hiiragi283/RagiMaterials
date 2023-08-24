package hiiragi283.material

import hiiragi283.api.HiiragiEntry
import hiiragi283.api.HiiragiRegistry
import hiiragi283.api.item.HiiragiItem
import hiiragi283.api.item.ItemMaterial
import hiiragi283.api.item.createItemMaterial
import hiiragi283.api.material.CrystalType
import hiiragi283.api.material.HiiragiMaterial
import hiiragi283.api.shape.HiiragiShapes
import hiiragi283.material.config.RMConfig
import hiiragi283.material.item.ItemCast
import hiiragi283.material.item.ItemClayBall
import hiiragi283.material.item.ItemCrushingHammer
import hiiragi283.material.item.ItemUnfiredCast
import hiiragi283.material.util.*
import net.minecraft.client.renderer.color.ItemColors
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.EnumRarity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.util.ActionResult
import net.minecraft.util.EnumHand
import net.minecraft.world.World
import net.minecraftforge.client.model.ModelLoader
import net.minecraftforge.common.IRarity
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import net.minecraftforge.registries.IForgeRegistry
import java.util.function.BiConsumer

fun getRecipeBlock(): BiConsumer<HiiragiEntry<*>, HiiragiMaterial> =
    BiConsumer { entry, material ->
        if (material.isSolid()) {
            if (material.isGem()) {
                CraftingBuilder(entry.getItemStack(material))
                    .setPattern("AAA", "AAA", "AAA")
                    .setIngredient('A', "gem${material.getOreDictName()}")
                    .build()
            } else {
                CraftingBuilder(entry.getItemStack(material))
                    .setPattern("AAA", "AAA", "AAA")
                    .setIngredient('A', "ingot${material.getOreDictName()}")
                    .build()
            }
        }
    }

object RMItems : HiiragiEntry.ITEM {

    private val entries: MutableList<HiiragiEntry.ITEM> = mutableListOf()

    @JvmField
    val BOOK_RESPAWN = object : HiiragiItem("book_respawn", 0) {

        //    General    //

        override fun getForgeRarity(stack: ItemStack): IRarity = EnumRarity.EPIC

        //    Event    //

        override fun onItemRightClick(world: World, player: EntityPlayer, hand: EnumHand): ActionResult<ItemStack> {
            //落下死防止やコマンド権限のためクリエモードに切り替え
            if (!world.isRemote) executeCommand(player, "gamemode 1")
            if (world.isRemote) {
                val spawnPoint = world.spawnPoint
                player.motionX = 0.0
                player.motionY = 0.0
                player.motionZ = 0.0 //運動ベクトルをリセット
                //プレイヤーを指定した座標にテレポート
                player.setLocationAndAngles(spawnPoint.x + 0.5, 128.0, spawnPoint.z + 0.5, 0.0f, 0.0f)
            }
            return super.onItemRightClick(world, player, hand)
        }

    }

    //    Material    //

    @JvmField
    val MATERIAL_BLOCK = createItemMaterial(
        HiiragiShapes.BLOCK,
        recipe = getRecipeBlock()
    )

    @JvmField
    val MATERIAL_BOTTLE: ItemMaterial =
        createItemMaterial(HiiragiShapes.BOTTLE).also { it.setCreativeTab(RMCreativeTabs.BOTTLE) }

    @JvmField
    val MATERIAL_DUST = createItemMaterial(
        HiiragiShapes.DUST,
        recipe = { entry, material ->
            CraftingBuilder(entry.getItemStack(material))
                .setPattern("AAA", "AAA", "AAA")
                .setIngredient('A', "dustTiny${material.getOreDictName()}")
                .build()
        }
    )

    @JvmField
    val MATERIAL_DUST_TINY = createItemMaterial(
        HiiragiShapes.DUST_TINY,
        recipe = { entry, material ->
            CraftingBuilder(entry.getItemStack(material, 9))
                .addIngredient(HiiragiIngredient("dust${material.getOreDictName()}"))
                .build()
        }
    )

    @JvmField
    val MATERIAL_GEAR = createItemMaterial(HiiragiShapes.GEAR)

    @JvmField
    val MATERIAL_GEM = createItemMaterial(
        HiiragiShapes.GEM,
        model = { entry ->
            ModelLoader.registerItemVariants(entry.asItem(), *CrystalType.values()
                .filter { it.texture.isNotEmpty() }
                .map { entry.getLocation()!!.append("_" + it.texture) }
                .toTypedArray())

            ModelLoader.setCustomMeshDefinition(entry.asItem()) { stack ->
                val material = HiiragiRegistry.getMaterial(stack.metadata)
                val type = if (material.isGem()) material.crystalType else CrystalType.CUBIC
                type.getLocation(entry.asItem())
            }
        },
        recipe = { entry, material ->
            CraftingBuilder(entry.getItemStack(material, 9))
                .addIngredient(HiiragiIngredient("block${material.getOreDictName()}"))
                .build()
        }
    )

    @JvmField
    val MATERIAL_INGOT = createItemMaterial(
        HiiragiShapes.INGOT,
        recipe = { entry, material ->
            //nugget -> ingot
            CraftingBuilder(entry.getItemStack(material))
                .setPattern("AAA", "AAA", "AAA")
                .setIngredient('A', "nugget${material.getOreDictName()}")
                .build()
            //block -> ingot
            val ingot9 = entry.getItemStack(material, 9)
            CraftingBuilder(ingot9.toLocation("_").append("_alt"), ingot9)
                .addIngredient(HiiragiIngredient("block${material.getOreDictName()}"))
                .build()
        }
    )

    @JvmField
    val MATERIAL_NUGGET = createItemMaterial(
        HiiragiShapes.NUGGET,
        recipe = { entry, material ->
            CraftingBuilder(entry.getItemStack(material, 9))
                .addIngredient(HiiragiIngredient("ingot${material.getOreDictName()}"))
                .build()
        }
    )

    @JvmField
    val MATERIAL_PLATE = createItemMaterial(HiiragiShapes.PLATE)

    @JvmField
    val MATERIAL_STICK = createItemMaterial(HiiragiShapes.STICK)

    //    Common    //

    @JvmField
    val CAST_GEAR = ItemCast(MATERIAL_GEAR)

    @JvmField
    val CAST_INGOT = ItemCast(MATERIAL_INGOT)

    @JvmField
    val CAST_NUGGET = ItemCast(MATERIAL_NUGGET)

    @JvmField
    val CAST_PLATE = ItemCast(MATERIAL_PLATE)

    @JvmField
    val CAST_STICK = ItemCast(MATERIAL_STICK)

    @JvmField
    val CRUSHING_HAMMER = ItemCrushingHammer

    @JvmField
    val UNFIRED_CAST = ItemUnfiredCast

    fun init() {
        RagiMaterials.LOGGER.info("RMItems has been initialized!")
        if (isDeobfEnv()) {
            entries.add(ItemClayBall)
        }
        entries.addAll(RMBlocks.getItemBlockEntries())
        entries.add(BOOK_RESPAWN)
        if (!RMConfig.EXPERIMENTAL.enableMetaTileBlock) entries.add(MATERIAL_BLOCK)
        entries.add(MATERIAL_BOTTLE)
        entries.add(MATERIAL_DUST)
        entries.add(MATERIAL_DUST_TINY)
        entries.add(MATERIAL_GEAR)
        entries.add(MATERIAL_GEM)
        entries.add(MATERIAL_INGOT)
        entries.add(MATERIAL_NUGGET)
        entries.add(MATERIAL_PLATE)
        entries.add(MATERIAL_STICK)
        entries.add(CAST_GEAR)
        entries.add(CAST_INGOT)
        entries.add(CAST_NUGGET)
        entries.add(CAST_PLATE)
        entries.add(CAST_STICK)
        entries.add(CRUSHING_HAMMER)
        entries.add(UNFIRED_CAST)
    }

    override fun register(registry: IForgeRegistry<Item>) {
        entries.forEach { registry.register(it.getObject()) }
    }

    override fun registerOreDict() {
        entries.forEach { it.registerOreDict() }
    }

    override fun registerRecipe() {
        entries.forEach { it.registerRecipe() }
    }

    @SideOnly(Side.CLIENT)
    override fun registerColorItem(itemColors: ItemColors) {
        entries.forEach { it.registerColorItem(itemColors) }
    }

    @SideOnly(Side.CLIENT)
    override fun registerModel() {
        entries.forEach { it.registerModel() }
    }

}