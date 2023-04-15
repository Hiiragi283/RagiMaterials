package hiiragi283.ragi_materials.api.init

import hiiragi283.ragi_materials.RagiInit
import hiiragi283.ragi_materials.RagiMaterials
import hiiragi283.ragi_materials.api.material.IMaterialItem
import hiiragi283.ragi_materials.api.material.part.PartRegistry
import hiiragi283.ragi_materials.item.*

object RagiItems {

    val ItemBlazingCube = ItemBase(RagiMaterials.MOD_ID, "blazing_cube", 0)
    val ItemBookDebug = ItemBookDebug()
    val ItemEnderTable = ItemEnderTable()
    val ItemForgeHammer = ItemForgeHammer()
    val ItemFullBottle = ItemFullBottle()
    val ItemOreCrushed = ItemOreCrushed()
    val ItemOreCrushedVanilla = ItemOreCrushedVanilla()
    val ItemWaste = ItemWaste()

    val ItemBlockMaterial = ItemMaterial(PartRegistry.BLOCK)
    val ItemCrystal = ItemMaterial(PartRegistry.CRYSTAL)
    val ItemDust = ItemMaterial(PartRegistry.DUST)
    val ItemDustTiny = ItemMaterial(PartRegistry.DUST_TINY)
    val ItemGear = ItemMaterial(PartRegistry.GEAR)
    val ItemIngot = ItemMaterial(PartRegistry.INGOT)
    val ItemIngotHot = ItemMaterial(PartRegistry.INGOT_HOT)
    val ItemNugget = ItemMaterial(PartRegistry.NUGGET)
    val ItemPlate = ItemMaterial(PartRegistry.PLATE)
    val ItemStick = ItemMaterial(PartRegistry.STICK)

    fun load() {
        this::class.java.declaredFields.forEach {
            it.isAccessible = true
            RagiMaterials.LOGGER.debug(it.name)
            try {
                val obj = it.get(this)
                if (obj is ItemBase) {
                    RagiInit.setItems.add(obj)
                    RagiMaterials.LOGGER.debug("The item ${obj.registryName} is added to list!")
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        RagiInit.setItems.forEach {
            if (it is IMaterialItem) RagiInit.setIMaterialItems.add(it)
            if (it is ItemMaterial) {
                RagiInit.mapMaterialParts[it.part] = it
                RagiMaterials.LOGGER.debug("The part-item pair ${it.part.name} -> ${it.registryName} is added to map!")
            }
        }
    }
}