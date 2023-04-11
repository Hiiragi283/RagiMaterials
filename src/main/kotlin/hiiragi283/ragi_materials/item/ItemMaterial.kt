package hiiragi283.ragi_materials.item

import hiiragi283.ragi_materials.RagiMaterials
import hiiragi283.ragi_materials.RagiRegistry
import hiiragi283.ragi_materials.client.model.ICustomModel
import hiiragi283.ragi_materials.client.model.ModelManager
import hiiragi283.ragi_materials.config.RagiConfig
import hiiragi283.ragi_materials.material.MaterialUtil
import hiiragi283.ragi_materials.material.RagiMaterial
import hiiragi283.ragi_materials.material.part.MaterialPart
import hiiragi283.ragi_materials.material.part.PartRegistry
import hiiragi283.ragi_materials.material.type.EnumCrystalType
import hiiragi283.ragi_materials.material.type.EnumMaterialType
import hiiragi283.ragi_materials.util.RagiUtil
import net.minecraft.client.renderer.block.model.ModelResourceLocation
import net.minecraft.client.resources.I18n
import net.minecraft.client.util.ITooltipFlag
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.entity.Entity
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.util.NonNullList
import net.minecraft.world.World
import net.minecraftforge.client.model.ModelLoader
import net.minecraftforge.common.IRarity
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import net.minecraftforge.oredict.OreDictionary

open class ItemMaterial(val part: MaterialPart) : ItemBase(RagiMaterials.MOD_ID, part.name, OreDictionary.WILDCARD_VALUE), ICustomModel, IMaterialItem {

    //    General    //

    override fun getForgeRarity(stack: ItemStack): IRarity {
        return getMaterial(stack).rarity
    }

    override fun getItemBurnTime(stack: ItemStack): Int {
        //素材に紐づいた燃焼時間*部品ごとの倍率を返す
        return (getMaterial(stack).burnTime * part.scale).toInt()
    }

    //    Event    //

    override fun onUpdate(stack: ItemStack, world: World, entity: Entity, slot: Int, isSelected: Boolean) {
        //enableDecay==trueの場合
        if (RagiConfig.material.enableDecay) {
            //サーバー側の場合，かつ5秒ごと
            if (!world.isRemote && world.worldInfo.worldTime % 100 == 0L) {
                //entityがplayerの場合
                if (entity is EntityPlayer) {
                    val material = getMaterial(stack)
                    //取得した素材が放射性の場合
                    if (EnumMaterialType.RADIOACTIVE in material.type.list) {
                        val stackRadio = stack.copy()
                        stackRadio.shrink(1) //1つ減らす
                        entity.inventory.setInventorySlotContents(slot, stackRadio)
                        //崩壊後の素材を取得
                        val decayed = material.decayed
                        if (decayed !== null && !decayed.isEmpty()) {
                            RagiUtil.dropItemAtPlayer(entity, ItemStack(this, 1, decayed.index))
                        }
                    }
                }
            }
        }
    }

    //    Client    //

    @SideOnly(Side.CLIENT)
    override fun addInformation(stack: ItemStack, world: World?, tooltip: MutableList<String>, flag: ITooltipFlag) {
        MaterialUtil.materialInfo(getMaterial(stack), tooltip)
    }

    @SideOnly(Side.CLIENT)
    override fun getItemStackDisplayName(stack: ItemStack): String = I18n.format("item.ragi_${part.name}.name", I18n.format("material.${getMaterial(stack).name}"))

    @SideOnly(Side.CLIENT)
    override fun getSubItems(tab: CreativeTabs, subItems: NonNullList<ItemStack>) {
        if (this.isInCreativeTab(tab)) {
            for (material in RagiMaterial.list) {
                if (MaterialUtil.isValidPart(part, material)) {
                    subItems.add(MaterialUtil.getPart(part, material))
                }
            }
        }
    }

    override fun registerCustomModel() {
        when (this.part) {
            //Block of **
            PartRegistry.BLOCK -> {

                val blockCrystal = ModelResourceLocation("${RagiMaterials.MOD_ID}:part", "block_crystal")
                val blockMaterial = ModelResourceLocation("${RagiMaterials.MOD_ID}:part", "block_material")
                val blockMetal = ModelResourceLocation("${RagiMaterials.MOD_ID}:part", "block_metal")

                ModelLoader.registerItemVariants(this, blockCrystal, blockMaterial, blockMetal)

                ModelLoader.setCustomMeshDefinition(RagiRegistry.ItemBlockMaterial) { stack ->
                    var result = blockMaterial
                    val item = stack.item
                    if (item is IMaterialItem) {
                        val material = item.getMaterial(stack)
                        if (material.crystalType != EnumCrystalType.NONE) {
                            if (material.crystalType != EnumCrystalType.COAL) result = blockCrystal
                        } else if (EnumMaterialType.INGOT in material.type.list) result = blockMetal
                    }
                    result
                }
            }
            //Crystal
            PartRegistry.CRYSTAL -> {

                ModelLoader.registerItemVariants(
                        RagiRegistry.ItemCrystal,
                        ModelResourceLocation("${RagiMaterials.MOD_ID}:crystal", "coal"),
                        ModelResourceLocation("${RagiMaterials.MOD_ID}:crystal", "cubic"),
                        ModelResourceLocation("${RagiMaterials.MOD_ID}:crystal", "diamond"),
                        ModelResourceLocation("${RagiMaterials.MOD_ID}:crystal", "emerald"),
                        ModelResourceLocation("${RagiMaterials.MOD_ID}:crystal", "lapis"),
                        ModelResourceLocation("${RagiMaterials.MOD_ID}:crystal", "quartz"),
                        ModelResourceLocation("${RagiMaterials.MOD_ID}:crystal", "ruby")
                )
                ModelLoader.setCustomMeshDefinition(RagiRegistry.ItemCrystal) { stack ->
                    var result = ModelResourceLocation("${RagiMaterials.MOD_ID}:crystal", EnumCrystalType.CUBIC.texture)
                    val item = stack.item
                    if (item is IMaterialItem) {
                        val material = item.getMaterial(stack)
                        if (material.crystalType != EnumCrystalType.NONE) result = ModelResourceLocation("${RagiMaterials.MOD_ID}:crystal", material.crystalType.texture)
                    }
                    result
                }
            }
            //Others
            else -> {
                ModelManager.setModelAlt(this, ModelResourceLocation("${RagiMaterials.MOD_ID}:part", part.name))
            }
        }
    }

    //    IMaterialItem    //

    override fun getMaterial(stack: ItemStack): RagiMaterial = MaterialUtil.getMaterial(stack.metadata)

    override fun setMaterial(stack: ItemStack, material: RagiMaterial): ItemStack = stack.also { it.itemDamage = material.index } //メタデータを上書き

}