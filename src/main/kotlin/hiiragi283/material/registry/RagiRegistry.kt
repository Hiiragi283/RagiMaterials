package hiiragi283.material.registry

import hiiragi283.material.RagiMaterials
import hiiragi283.material.base.ItemBase
import hiiragi283.material.client.color.IColorHandler
import hiiragi283.material.item.*
import hiiragi283.material.util.RagiColor
import net.minecraft.init.Blocks
import net.minecraft.init.Items
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraftforge.client.event.ColorHandlerEvent
import net.minecraftforge.client.event.ModelRegistryEvent
import net.minecraftforge.event.RegistryEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import net.minecraftforge.oredict.OreDictionary

object RagiRegistry {

    //    Collection    //

    private val items: MutableSet<ItemBase> = mutableSetOf()
    private val mapItemMaterials: LinkedHashMap<String, ItemMaterial> = linkedMapOf()

    fun getItemMaterials(): Collection<ItemMaterial> = mapItemMaterials.values

    //    Item    //

    val ITEM_PART_BLOCK = ItemMaterialBlock
    val ITEM_PART_CRYSTAL = ItemMaterialCrystal
    val ITEM_PART_DUST = ItemMaterialDust
    val ITEM_PART_DUST_TINY = ItemMaterialDustTiny
    val ITEM_PART_GEAR = ItemMaterialGear
    val ITEM_PART_INGOT = ItemMaterialIngot
    val ITEM_PART_NUGGET = ItemMaterialNugget
    val ITEM_PART_PLATE = ItemMaterialPlate
    val ITEM_PART_STICK = ItemMaterialStick

    val ITEM_BOOK_RESPAWN = ItemBookRespawn
    val ITEM_FORGE_HAMMER = ItemForgeHammer

    //    Registration    //

    fun load() {
        for (field in this::class.java.declaredFields) {
            field.isAccessible = true
            val obj = field.get(this)
            if (obj is ItemBase) {
                items.add(obj)
                RagiMaterials.LOGGER.debug("The item ${obj.registryName} was added to collection!")
                if (obj is ItemMaterial) mapItemMaterials[obj.getOrePrefix()] = obj
            }
        }
    }

    @SubscribeEvent
    fun registerItems(event: RegistryEvent.Register<Item>) {
        val registry = event.registry
        for (item in items) {
            item.register(registry)
        }
    }

    fun registerOreDict() {
        for (item in items) {
            item.registerOreDict()
        }
        //for Vanilla
        OreDictionary.registerOre("blockStone", ItemStack(Blocks.STONE, 1, 0))
        OreDictionary.registerOre("blockGranite", ItemStack(Blocks.STONE, 1, 1))
        OreDictionary.registerOre("blockGranite", ItemStack(Blocks.STONE, 1, 2))
        OreDictionary.registerOre("blockDiorite", ItemStack(Blocks.STONE, 1, 3))
        OreDictionary.registerOre("blockDiorite", ItemStack(Blocks.STONE, 1, 4))
        OreDictionary.registerOre("blockAndesite", ItemStack(Blocks.STONE, 1, 5))
        OreDictionary.registerOre("blockAndesite", ItemStack(Blocks.STONE, 1, 6))
        OreDictionary.registerOre("blockBedrock", Blocks.BEDROCK)
        OreDictionary.registerOre("blockSand", Blocks.SAND)
        OreDictionary.registerOre("blockObsidian", Blocks.OBSIDIAN)
        OreDictionary.registerOre("blockClay", Blocks.CLAY)
        OreDictionary.registerOre("blockNetherrack", Blocks.NETHERRACK)
        OreDictionary.registerOre("blockSoulSand", Blocks.SOUL_SAND)
        OreDictionary.registerOre("blockEndStone", Blocks.END_STONE)
        OreDictionary.registerOre("blockBone", Blocks.BONE_BLOCK)

        OreDictionary.registerOre("gemCoal", ItemStack(Items.COAL, 1, 0))
        OreDictionary.registerOre("gemCharcoal", ItemStack(Items.COAL, 1, 1))
        OreDictionary.registerOre("dustGunpowder", Items.GUNPOWDER)
        OreDictionary.registerOre("dustClay", Items.CLAY_BALL)
        OreDictionary.registerOre("dustBone", ItemStack(Items.DYE, 1, 15))
        OreDictionary.registerOre("stickBone", Items.BONE)
        OreDictionary.registerOre("dustSugar", Items.SUGAR)
        OreDictionary.registerOre("gemEnderPearl", Items.ENDER_PEARL)
        OreDictionary.registerOre("stickBlaze", Items.BLAZE_ROD)
        OreDictionary.registerOre("dustBlaze", Items.BLAZE_POWDER)
    }

    fun registerRecipe() {
        for (item in items) {
            item.registerRecipe()
        }
    }

    @SideOnly(Side.CLIENT)
    object Client {

        @SideOnly(Side.CLIENT)
        @SubscribeEvent
        fun registerModel(event: ModelRegistryEvent) {
            for (item in items) {
                item.registerModel()
                RagiMaterials.LOGGER.debug("The model for item ${item.registryName} is registered!")
            }
        }

        @SideOnly(Side.CLIENT)
        @SubscribeEvent
        fun registerColor(event: ColorHandlerEvent.Item) {
            event.itemColors.registerItemColorHandler({ stack, tintIndex ->
                val item = stack.item
                if (item is IColorHandler.Item) item.getColor(stack, tintIndex).rgb else RagiColor.WHITE.rgb
            }, *items.toTypedArray())
        }
    }
}