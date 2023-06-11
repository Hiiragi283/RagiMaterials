package hiiragi283.material.init

import hiiragi283.material.item.*
import net.minecraft.client.renderer.color.ItemColors
import net.minecraft.item.Item
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import net.minecraftforge.registries.IForgeRegistry

object RMItems : IRMEntry<Item> {

    val BOOK_RESPAWN = ItemBookRespawn
    val FORGE_HAMMER = ItemForgeHammer

    val MATERIAL_DUST = ItemMaterialDust
    val MATERIAL_DUST_TINY = ItemMaterialDustTiny
    val MATERIAL_INGOT = ItemMaterialIngot
    val MATERIAL_PLATE = ItemMaterialPlate

    override fun register(registry: IForgeRegistry<Item>) {
        BOOK_RESPAWN.register(registry)
        FORGE_HAMMER.register(registry)

        MATERIAL_DUST.register(registry)
        MATERIAL_DUST_TINY.register(registry)
        MATERIAL_INGOT.register(registry)
        MATERIAL_PLATE.register(registry)
    }

    override fun registerMaterialPart() {
        MATERIAL_DUST.registerMaterialPart()
        MATERIAL_DUST_TINY.registerMaterialPart()
        MATERIAL_INGOT.registerMaterialPart()
        MATERIAL_PLATE.registerMaterialPart()
    }

    override fun registerOreDict() {
        MATERIAL_DUST.registerOreDict()
        MATERIAL_DUST_TINY.registerOreDict()
        MATERIAL_INGOT.registerOreDict()
        MATERIAL_PLATE.registerOreDict()
    }

    override fun registerRecipe() {
        FORGE_HAMMER.registerRecipe()

        MATERIAL_DUST.registerRecipe()
        MATERIAL_DUST_TINY.registerRecipe()
        MATERIAL_INGOT.registerRecipe()
        MATERIAL_PLATE.registerRecipe()
    }

    @SideOnly(Side.CLIENT)
    override fun registerColorItem(itemColors: ItemColors) {
        MATERIAL_DUST.registerColorItem(itemColors)
        MATERIAL_DUST_TINY.registerColorItem(itemColors)
        MATERIAL_INGOT.registerColorItem(itemColors)
        MATERIAL_PLATE.registerColorItem(itemColors)
    }

    @SideOnly(Side.CLIENT)
    override fun registerModel() {
        BOOK_RESPAWN.registerModel()
        FORGE_HAMMER.registerModel()

        MATERIAL_DUST.registerModel()
        MATERIAL_DUST_TINY.registerModel()
        MATERIAL_INGOT.registerModel()
        MATERIAL_PLATE.registerModel()
    }


}