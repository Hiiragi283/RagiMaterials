package hiiragi283.material

import hiiragi283.material.item.ItemBookRespawn
import hiiragi283.material.item.ItemForgeHammer
import hiiragi283.material.item.ItemMaterialBase
import hiiragi283.material.material.MaterialElements
import hiiragi283.material.part.PartRegistry
import net.minecraft.client.renderer.color.ItemColors
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.init.Items
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import net.minecraftforge.oredict.OreDictionary
import net.minecraftforge.registries.IForgeRegistry

object RMItems : RMEntry<Item> {

    val BOOK_RESPAWN = ItemBookRespawn
    val FORGE_HAMMER = ItemForgeHammer

    val MATERIAL_BLOCK = ItemMaterialBase(PartRegistry.BLOCK)
    val MATERIAL_BOTTLE = ItemMaterialBase(PartRegistry.BOTTLE)
    val MATERIAL_DUST = ItemMaterialBase(PartRegistry.DUST)
    val MATERIAL_DUST_TINY = ItemMaterialBase(PartRegistry.DUST_TINY)
    val MATERIAL_GEAR = ItemMaterialBase(PartRegistry.GEAR)
    val MATERIAL_GEM = ItemMaterialBase(PartRegistry.GEM)
    val MATERIAL_INGOT = ItemMaterialBase(PartRegistry.INGOT)
    val MATERIAL_NUGGET = ItemMaterialBase(PartRegistry.NUGGET)
    val MATERIAL_PLATE = ItemMaterialBase(PartRegistry.PLATE)


    override fun register(registry: IForgeRegistry<Item>) {

        MATERIAL_BOTTLE.setCreativeTab(object : CreativeTabs("${RagiMaterials.MODID}.bottle") {
            override fun createIcon(): ItemStack =
                ItemStack(MATERIAL_BOTTLE, 1, MaterialElements.HYDROGEN.index)
        })

        BOOK_RESPAWN.register(registry)
        FORGE_HAMMER.register(registry)

        MATERIAL_BLOCK.register(registry)
        MATERIAL_BOTTLE.register(registry)
        MATERIAL_DUST.register(registry)
        MATERIAL_DUST_TINY.register(registry)
        MATERIAL_GEAR.register(registry)
        MATERIAL_GEM.register(registry)
        MATERIAL_INGOT.register(registry)
        MATERIAL_NUGGET.register(registry)
        MATERIAL_PLATE.register(registry)
    }

    override fun registerMaterialPart() {
        MATERIAL_BLOCK.registerMaterialPart()
        MATERIAL_BOTTLE.registerMaterialPart()
        MATERIAL_DUST.registerMaterialPart()
        MATERIAL_DUST_TINY.registerMaterialPart()
        MATERIAL_GEAR.registerMaterialPart()
        MATERIAL_GEM.registerMaterialPart()
        MATERIAL_INGOT.registerMaterialPart()
        MATERIAL_NUGGET.registerMaterialPart()
        MATERIAL_PLATE.registerMaterialPart()
    }

    override fun registerOreDict() {
        MATERIAL_BLOCK.registerOreDict()
        MATERIAL_BOTTLE.registerOreDict()
        MATERIAL_DUST.registerOreDict()
        MATERIAL_DUST_TINY.registerOreDict()
        MATERIAL_GEAR.registerOreDict()
        MATERIAL_GEM.registerOreDict()
        MATERIAL_INGOT.registerOreDict()
        MATERIAL_NUGGET.registerOreDict()
        MATERIAL_PLATE.registerOreDict()

        fun shareOredict(oredict1: String, oredict2: String) {
            OreDictionary.getOres(oredict1).forEach { OreDictionary.registerOre(oredict2, it) }
            OreDictionary.getOres(oredict2).forEach { OreDictionary.registerOre(oredict1, it) }
        }

        fun registerOredict(oredict: String, item: Item, meta: Int = 0, share: String? = null) {
            OreDictionary.registerOre(oredict, ItemStack(item, 1, meta))
            share?.let { shareOredict(oredict, it) }
        }

        registerOredict("dustGunpowder", Items.GUNPOWDER, share = "gunpowder")
        registerOredict("dustSugar", Items.SUGAR, share = "sugar")
        registerOredict("gemCharcoal", Items.COAL, 1, share = "charcoal")
        registerOredict("gemCoal", Items.COAL, share = "coal")
        registerOredict("gemEnder", Items.ENDER_PEARL, share = "enderpearl")
        registerOredict("stickWood", Items.STICK, share = "stick")

        shareOredict("dustSaltpeter", "dustNiter")
        shareOredict("fuelCoke", "gemCoke")
    }

    override fun registerRecipe() {
        FORGE_HAMMER.registerRecipe()

        MATERIAL_BLOCK.registerRecipe()
        //MATERIAL_CELL.registerRecipe()
        MATERIAL_DUST.registerRecipe()
        MATERIAL_DUST_TINY.registerRecipe()
        MATERIAL_GEAR.registerRecipe()
        MATERIAL_GEM.registerRecipe()
        MATERIAL_INGOT.registerRecipe()
        MATERIAL_NUGGET.registerRecipe()
        MATERIAL_PLATE.registerRecipe()
    }

    @SideOnly(Side.CLIENT)
    override fun registerColorItem(itemColors: ItemColors) {
        MATERIAL_BLOCK.registerColorItem(itemColors)
        MATERIAL_BOTTLE.registerColorItem(itemColors)
        MATERIAL_DUST.registerColorItem(itemColors)
        MATERIAL_DUST_TINY.registerColorItem(itemColors)
        MATERIAL_GEAR.registerColorItem(itemColors)
        MATERIAL_GEM.registerColorItem(itemColors)
        MATERIAL_INGOT.registerColorItem(itemColors)
        MATERIAL_NUGGET.registerColorItem(itemColors)
        MATERIAL_PLATE.registerColorItem(itemColors)
    }

    @SideOnly(Side.CLIENT)
    override fun registerModel() {
        BOOK_RESPAWN.registerModel()
        FORGE_HAMMER.registerModel()

        MATERIAL_BLOCK.registerModel()
        MATERIAL_BOTTLE.registerModel()
        MATERIAL_DUST.registerModel()
        MATERIAL_DUST_TINY.registerModel()
        MATERIAL_GEAR.registerModel()
        MATERIAL_GEM.registerModel()
        MATERIAL_INGOT.registerModel()
        MATERIAL_NUGGET.registerModel()
        MATERIAL_PLATE.registerModel()
    }

}