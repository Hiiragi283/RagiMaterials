package hiiragi283.ragi_materials

import hiiragi283.ragi_materials.api.init.RagiBlocks
import hiiragi283.ragi_materials.api.init.RagiItems
import hiiragi283.ragi_materials.api.material.MaterialRegistry
import hiiragi283.ragi_materials.api.material.RagiMaterial
import hiiragi283.ragi_materials.api.material.part.MaterialPart
import hiiragi283.ragi_materials.api.material.part.PartRegistry
import hiiragi283.ragi_materials.api.material.type.EnumMaterialType
import hiiragi283.ragi_materials.api.material.type.TypeRegistry
import hiiragi283.ragi_materials.item.ItemMaterial
import hiiragi283.ragi_materials.material.OreProperties
import hiiragi283.ragi_materials.proxy.IProxy
import hiiragi283.ragi_materials.recipe.furnace.SmeltingRegistry
import hiiragi283.ragi_materials.recipe.workbench.CraftingRegistry
import hiiragi283.ragi_materials.util.getPart
import net.minecraft.block.Block
import net.minecraft.client.resources.I18n
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.init.Items
import net.minecraft.item.Item
import net.minecraft.item.ItemBlock
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraftforge.fluids.Fluid
import net.minecraftforge.fluids.FluidRegistry
import net.minecraftforge.fluids.FluidStack
import net.minecraftforge.fml.common.event.FMLConstructionEvent
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent
import net.minecraftforge.oredict.OreDictionary

object RagiInit : IProxy {

    //    Creative Tab    //

    val TabBlock = object : CreativeTabs("${RagiMaterials.MOD_ID}.blocks") {
        override fun createIcon() = ItemStack(RagiBlocks.BlockForgeFurnace)
    }
    val TabFullBottle = object : CreativeTabs("${RagiMaterials.MOD_ID}.fullbottles") {
        override fun createIcon() = ItemStack(RagiItems.ItemFullBottle)
    }
    val TabMaterial = object : CreativeTabs("${RagiMaterials.MOD_ID}.materials") {
        override fun createIcon() = ItemStack(RagiItems.ItemIngot, 1, 26)
    }

    //    LootTable    //

    val OreRainbow = ResourceLocation(RagiMaterials.MOD_ID, "gameplay/ore_rainbow")

    //    Collection    //

    val setBlocks: MutableSet<Block> = mutableSetOf()
    val setIMaterialBlocks: MutableSet<Block> = mutableSetOf()

    val setIMaterialItemBlocks: MutableSet<ItemBlock> = mutableSetOf()
    val setItemBlocks: MutableSet<ItemBlock> = mutableSetOf()

    val setIMaterialItems: MutableSet<Item> = mutableSetOf()
    val setItems: MutableSet<Item> = mutableSetOf()
    val mapMaterialParts: HashMap<MaterialPart, ItemMaterial> = hashMapOf()

    override fun onConstruct(event: FMLConstructionEvent) {
        MaterialRegistry.registerMaterials()
    }

    override fun onPreInit(event: FMLPreInitializationEvent) {
        RagiBlocks.load()
        RagiItems.load()
    }

    override fun onInit(event: FMLInitializationEvent) {
        registerFluid()
        registerOreDict()
        CraftingRegistry.addRecipes()
        SmeltingRegistry.load()
    }

    private fun registerFluid() {
        //Fluidの登録
        for (material in MaterialRegistry.list) {
            //typeがINTERNALでない，かつmaterialのtypeがfluidの場合
            if (material.type != TypeRegistry.INTERNAL && EnumMaterialType.LIQUID in material.type.list) FluidBase(material)
        }
    }

    private fun registerOreDict() {
        //鉱石辞書の登録
        for (pair in MaterialRegistry.validPair) {
            val part = pair.first
            val material = pair.second
            val stack = getPart(part, material)
            OreDictionary.registerOre(part.prefixOre + material.getOreDict(), stack)
            material.oredictAlt?.let { OreDictionary.registerOre(part.prefixOre + it, stack) }
        }

        //Ore
        for (i in OreProperties.listOre1.indices) {
            OreDictionary.registerOre("ore${OreProperties.listOre1[i].first}", ItemStack(RagiBlocks.BlockOre1, 1, i))
            OreDictionary.registerOre("crushed${OreProperties.listOre1[i].first}", ItemStack(RagiItems.ItemOreCrushed, 1, i))
        }
        OreDictionary.registerOre("oreSaltpeter", ItemStack(RagiBlocks.BlockOre1, 1, 6))
        OreDictionary.registerOre("oreSaltpeterCrushed", ItemStack(RagiItems.ItemOreCrushed, 1, 6))

        for (i in OreProperties.listVanilla.indices) {
            OreDictionary.registerOre("crushed${OreProperties.listVanilla[i].first}", ItemStack(RagiItems.ItemOreCrushedVanilla, 1, i))
        }

        //Others
        OreDictionary.registerOre("charcoal", getPart(PartRegistry.CRYSTAL, MaterialRegistry.CHARCOAL))
        OreDictionary.registerOre("dustGunpowder", ItemStack(Items.GUNPOWDER))
        OreDictionary.registerOre("dustSugar", ItemStack(Items.SUGAR))
        OreDictionary.registerOre("fuelCoke", getPart(PartRegistry.CRYSTAL, MaterialRegistry.COKE))
        OreDictionary.registerOre("gearStone", getPart(PartRegistry.GEAR, MaterialRegistry.STONE))
        OreDictionary.registerOre("gearWood", getPart(PartRegistry.GEAR, MaterialRegistry.WOOD))
        OreDictionary.registerOre("gemCharcoal", ItemStack(Items.COAL))
        OreDictionary.registerOre("stickStone", getPart(PartRegistry.STICK, MaterialRegistry.STONE))
    }

    override fun onPostInit(event: FMLPostInitializationEvent) {
        MaterialRegistry.mapElement.values.forEach { RagiMaterials.LOGGER.debug("<element:${it.name}>") }
        MaterialRegistry.list.forEach { RagiMaterials.LOGGER.debug("Index: ${it.index}, <material:${it.name}>") }
        CraftingRegistry.removeRecipes()
    }

    private val location = ResourceLocation("minecraft:blocks/concrete_powder_white")

    class FluidBase(val material: RagiMaterial) : Fluid(material.name, location, location) {

        init {
            setColor(material.color)
            //MaterialTypesがGASの場合
            if (material.type.match(TypeRegistry.GAS)) {
                isGaseous = true
                density *= -1
            }
            FluidRegistry.registerFluid(this)
            FluidRegistry.addBucketForFluid(this)
        }

        override fun getUnlocalizedName(): String = "material.${this.unlocalizedName}"

        override fun getLocalizedName(stack: FluidStack?): String {
            var localized = ""
            if (stack !== null) {
                if (material.type != TypeRegistry.LIQUID && material.type != TypeRegistry.GAS) {
                    localized += "${I18n.format("fluid.molten")} "
                }
            }
            localized += I18n.format(getUnlocalizedName())
            return localized
        }
    }
}