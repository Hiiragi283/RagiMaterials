package hiiragi283.material

import hiiragi283.material.api.HiiragiEntry
import hiiragi283.material.api.item.ItemMaterial
import hiiragi283.material.api.shape.HiiragiShapes
import hiiragi283.material.item.ItemBookRespawn
import hiiragi283.material.item.ItemForgeHammer
import net.minecraft.client.renderer.color.ItemColors
import net.minecraft.item.Item
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import net.minecraftforge.registries.IForgeRegistry

object RMItems : HiiragiEntry.ITEM {

    @JvmField
    val BOOK_RESPAWN = ItemBookRespawn

    @JvmField
    val FORGE_HAMMER = ItemForgeHammer

    @JvmField
    val MATERIAL_BLOCK = ItemMaterial(HiiragiShapes.BLOCK)

    @JvmField
    val MATERIAL_BOTTLE = ItemMaterial(HiiragiShapes.BOTTLE)

    @JvmField
    val MATERIAL_DUST = ItemMaterial(HiiragiShapes.DUST)

    @JvmField
    val MATERIAL_DUST_TINY = ItemMaterial(HiiragiShapes.DUST_TINY)

    @JvmField
    val MATERIAL_GEAR = ItemMaterial(HiiragiShapes.GEAR)

    @JvmField
    val MATERIAL_GEM = ItemMaterial(HiiragiShapes.GEM)

    @JvmField
    val MATERIAL_INGOT = ItemMaterial(HiiragiShapes.INGOT)

    @JvmField
    val MATERIAL_NUGGET = ItemMaterial(HiiragiShapes.NUGGET)

    @JvmField
    val MATERIAL_PLATE = ItemMaterial(HiiragiShapes.PLATE)

    @JvmField
    val MATERIAL_STICK = ItemMaterial(HiiragiShapes.STICK)

    override fun register(registry: IForgeRegistry<Item>) {

        MATERIAL_BOTTLE.setCreativeTab(RMCreativeTabs.BOTTLE)

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
        MATERIAL_STICK.register(registry)
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
        MATERIAL_STICK.registerOreDict()
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
        MATERIAL_STICK.registerRecipe()
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
        MATERIAL_STICK.registerColorItem(itemColors)
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
        MATERIAL_STICK.registerModel()
    }

}