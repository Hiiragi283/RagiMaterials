package ragi_materials.core

import net.minecraft.block.Block
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.Item
import net.minecraft.item.ItemBlock
import net.minecraft.util.ResourceLocation
import net.minecraftforge.registries.IForgeRegistry
import net.minecraftforge.registries.IForgeRegistryModifiable
import ragi_materials.core.block.BlockBase
import ragi_materials.core.item.ItemBase
import ragi_materials.core.item.ItemMaterial
import ragi_materials.core.material.IMaterialBlock
import ragi_materials.core.material.IMaterialItem
import ragi_materials.core.material.part.MaterialPart
import ragi_materials.core.recipe.BlastFurnaceRecipe
import ragi_materials.core.recipe.FFRecipe
import ragi_materials.core.recipe.LaboRecipe
import ragi_materials.core.recipe.MillRecipe

object RagiRegistry {

    //    Collection    //

    val setBlocks: MutableSet<Block> = mutableSetOf()
    val setIMaterialBlocks: MutableSet<Block> = mutableSetOf()

    val setIMaterialItemBlocks: MutableSet<ItemBlock> = mutableSetOf()
    val setItemBlocks: MutableSet<ItemBlock> = mutableSetOf()

    val setIMaterialItems: MutableSet<Item> = mutableSetOf()
    val setItems: MutableSet<Item> = mutableSetOf()
    val mapMaterialParts: HashMap<MaterialPart, ItemMaterial> = hashMapOf()

    //    Block    //

    lateinit var BlockBlastFurnaceCore: BlockBase
    lateinit var BlockBlastFurnaceInterface: BlockBase
    lateinit var BlockBlazingForge: BlockBase
    lateinit var BlockBloom: BlockBase
    lateinit var BlockBloomery: BlockBase
    lateinit var BlockForgeFurnace: BlockBase
    lateinit var BlockFullBottleStation: BlockBase
    lateinit var BlockIndustrialLabo: BlockBase
    lateinit var BlockLaboratoryTable: BlockBase
    lateinit var BlockOreDictConv: BlockBase
    lateinit var BlockOreRainbow: BlockBase
    lateinit var BlockSoilAir: BlockBase
    lateinit var BlockSoilCoal: BlockBase
    lateinit var BlockSoilLignite: BlockBase
    lateinit var BlockSoilPeat: BlockBase
    lateinit var BlockStoneMill: BlockBase
    lateinit var BlockTransferEnergy: BlockBase
    lateinit var BlockTransferFluid: BlockBase
    lateinit var BlockTransferGas: BlockBase

    //    CreativeTab    //

    lateinit var TabBlock: CreativeTabs
    lateinit var TabFullBottle: CreativeTabs
    lateinit var TabMaterial: CreativeTabs

    fun availableTabBlock() = ::TabBlock.isInitialized
    fun availableTabFullBottle() = ::TabFullBottle.isInitialized
    fun availableTabMaterial() = ::TabMaterial.isInitialized

    //    Item    //

    lateinit var ItemBlazingCube: ItemBase
    lateinit var ItemBookDebug: ItemBase
    lateinit var ItemEnderTable: ItemBase
    lateinit var ItemForgeHammer: ItemBase
    lateinit var ItemFullBottle: ItemBase
    lateinit var ItemWaste: ItemBase

    lateinit var ItemBlockMaterial: ItemMaterial
    lateinit var ItemCrystal: ItemMaterial
    lateinit var ItemDust: ItemMaterial
    lateinit var ItemDustTiny: ItemMaterial
    lateinit var ItemGear: ItemMaterial
    lateinit var ItemIngot: ItemMaterial

    //lateinit var ItemIngotHot: ItemMaterial
    lateinit var ItemNugget: ItemMaterial
    lateinit var ItemOre: ItemMaterial
    lateinit var ItemOreCrushed: ItemMaterial
    lateinit var ItemPlate: ItemMaterial
    lateinit var ItemStick: ItemMaterial

    //    LootTable    //

    val OreRainbow = ResourceLocation(RagiMaterials.MOD_ID, "gameplay/ore_rainbow")

    //    IForgeRegistry    //

    lateinit var BF_RECIPE: IForgeRegistry<BlastFurnaceRecipe>
    lateinit var FF_RECIPE: IForgeRegistry<FFRecipe>
    lateinit var LABO_RECIPE: IForgeRegistry<LaboRecipe>
    lateinit var MILL_RECIPE: IForgeRegistry<MillRecipe>

    //エントリーを削除するメソッド
    fun removeRegistryEntry(registry: IForgeRegistry<*>, registryName: ResourceLocation) {
        if (registry is IForgeRegistryModifiable<*>) {
            registry.remove(registryName)
            RagiMaterials.LOGGER.warn("The entry $registryName was removed from ${registry::class.java.name}!")
        } else RagiMaterials.LOGGER.warn("The registry ${registry::class.java.name} is not implementing IForgeRegistryModifiable!")
    }

    fun remove(registry: IForgeRegistry<*>, registryName: String) {
        removeRegistryEntry(registry, ResourceLocation(registryName))
    }

    //このクラス内のフィールドを一覧に代入するメソッド
    fun addCollection() {
        this::class.java.declaredFields.forEach {
            it.isAccessible = true
            RagiMaterials.LOGGER.debug(it.name)
            try {
                //Block
                val obj = it.get(this)
                if (obj is BlockBase && obj.registryName !== null) {
                    setBlocks.add(obj)
                    RagiMaterials.LOGGER.debug("The block ${obj.registryName} is added to list!")
                    if (obj.itemBlock !== null) {
                        setItemBlocks.add(obj.itemBlock!!)
                        RagiMaterials.LOGGER.debug("The item block ${obj.registryName} is added to list!")
                    }
                }
                //Item
                if (obj is ItemBase && obj.registryName !== null) {
                    setItems.add(obj)
                    RagiMaterials.LOGGER.debug("The item ${obj.registryName} is added to list!")
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        //Block
        setBlocks.forEach {
            if (it is IMaterialBlock) setIMaterialBlocks.add(it)
        }
        setItemBlocks.forEach {
            val block = it.block
            if (block is IMaterialBlock) setIMaterialItemBlocks.add(it)
        }

        //Item
        setItems.forEach {
            if (it is IMaterialItem) setIMaterialItems.add(it)
            if (it is ItemMaterial) {
                mapMaterialParts[it.part] = it
                RagiMaterials.LOGGER.debug("The part-item pair ${it.part.name} -> ${it.registryName} is added to map!")
            }
        }
    }
}