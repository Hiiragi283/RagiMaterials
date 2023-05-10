package ragi_materials.core

import net.minecraft.creativetab.CreativeTabs
import net.minecraft.enchantment.Enchantment
import net.minecraft.util.ResourceLocation
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.common.capabilities.CapabilityInject
import net.minecraftforge.fluids.Fluid
import net.minecraftforge.registries.IForgeRegistry
import ragi_materials.core.block.BlockBase
import ragi_materials.core.capability.heat.IHeatStorage
import ragi_materials.core.item.ItemBase
import ragi_materials.core.item.ItemBlockBase
import ragi_materials.core.item.ItemMaterial
import ragi_materials.core.material.IMaterialBlock
import ragi_materials.core.material.IMaterialItem
import ragi_materials.core.material.part.MaterialPart
import ragi_materials.core.recipe.*

object RagiRegistry {

    //    Collection    //

    val setBlocks: MutableSet<BlockBase> = mutableSetOf()
    val setIMaterialBlocks: MutableSet<BlockBase> = mutableSetOf()

    val setIMaterialItemBlocks: MutableSet<ItemBlockBase> = mutableSetOf()
    val setItemBlocks: MutableSet<ItemBlockBase> = mutableSetOf()

    val setIMaterialItems: MutableSet<ItemBase> = mutableSetOf()
    val setItems: MutableSet<ItemBase> = mutableSetOf()
    val mapMaterialParts: HashMap<MaterialPart, ItemMaterial> = hashMapOf()

    //    Block    //

    lateinit var BlockBasin: BlockBase
    lateinit var BlockBlastFurnaceCore: BlockBase
    lateinit var BlockBlastFurnaceInterface: BlockBase
    lateinit var BlockBlazingForge: BlockBase
    lateinit var BlockBloom: BlockBase
    lateinit var BlockBloomery: BlockBase
    lateinit var BlockForgeFurnace: BlockBase
    lateinit var BlockFullBottleStation: BlockBase
    lateinit var BlockHopperPress: BlockBase
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

    //    Enchantment    //

    lateinit var EnchantmentMaterial: Enchantment

    //    Fluid    //

    lateinit var FluidSeedOil: Fluid

    //    Item    //

    lateinit var ItemBlazingCube: ItemBase
    lateinit var ItemBookDebug: ItemBase
    lateinit var ItemEnderTable: ItemBase
    lateinit var ItemForgeHammer: ItemBase
    lateinit var ItemFullBottle: ItemBase
    lateinit var ItemWaste: ItemBase
    lateinit var ItemMaterialMiner: ItemBase

    lateinit var ItemBlockMaterial: ItemMaterial
    lateinit var ItemCrushed: ItemMaterial
    lateinit var ItemCrystal: ItemMaterial
    lateinit var ItemDust: ItemMaterial
    lateinit var ItemDustTiny: ItemMaterial
    lateinit var ItemGear: ItemMaterial
    lateinit var ItemIngot: ItemMaterial
    lateinit var ItemNugget: ItemMaterial
    lateinit var ItemOre: ItemMaterial
    lateinit var ItemPlate: ItemMaterial
    lateinit var ItemPurified: ItemMaterial
    lateinit var ItemStick: ItemMaterial

    //    LootTable    //

    val OreRainbow = ResourceLocation(RagiMaterials.MOD_ID, "gameplay/ore_rainbow")

    //    IForgeRegistry    //

    lateinit var BASIN_RECIPE: IForgeRegistry<BasinRecipe>
    lateinit var BF_RECIPE: IForgeRegistry<BlastFurnaceRecipe>
    lateinit var FF_RECIPE: IForgeRegistry<ForgeFurnaceRecipe>
    lateinit var HP_RECIPE: IForgeRegistry<HopperPressRecipe>
    lateinit var LABO_RECIPE: IForgeRegistry<LaboRecipe>
    lateinit var MILL_RECIPE: IForgeRegistry<MillRecipe>

    //    Capability    //

    @CapabilityInject(IHeatStorage::class)
    lateinit var HEAT: Capability<IHeatStorage>

    //このクラス内のフィールドを一覧に代入するメソッド
    fun addCollection() {
        for (field in this::class.java.declaredFields) {
            field.isAccessible = true
            RagiMaterials.LOGGER.debug(field.name)
            try {
                //Block
                val obj = field.get(this)
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
        for (block in setBlocks) {
            if (block is IMaterialBlock) setIMaterialBlocks.add(block)
        }
        for (itemBlock in setItemBlocks) {
            val block = itemBlock.block
            if (block is IMaterialBlock) setIMaterialItemBlocks.add(itemBlock)
        }

        //Item
        for (item in setItems) {
            if (item is IMaterialItem) setIMaterialItems.add(item)
            if (item is ItemMaterial) {
                mapMaterialParts[item.part] = item
                RagiMaterials.LOGGER.debug("The part-item pair ${item.part.name} -> ${item.registryName} is added to map!")
            }

        }
    }
}