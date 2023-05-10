package ragi_materials.core.item

import net.minecraft.client.renderer.block.model.ModelResourceLocation
import net.minecraft.client.resources.I18n
import net.minecraft.client.util.ITooltipFlag
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.entity.Entity
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.util.NonNullList
import net.minecraft.world.World
import net.minecraftforge.common.IRarity
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import net.minecraftforge.oredict.OreDictionary
import ragi_materials.core.RagiMaterials
import ragi_materials.core.config.RagiConfig
import ragi_materials.core.material.EnumSubMaterial
import ragi_materials.core.material.IMaterialItem
import ragi_materials.core.material.MaterialRegistry
import ragi_materials.core.material.RagiMaterial
import ragi_materials.core.material.part.MaterialPart
import ragi_materials.core.material.type.EnumMaterialType
import ragi_materials.core.util.dropItemAtPlayer
import ragi_materials.main.client.model.ICustomModel
import ragi_materials.main.client.model.ModelManager

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
                    if (EnumMaterialType.RADIOACTIVE in material.type.types) {
                        val stackRadio = stack.copy()
                        stackRadio.shrink(1) //1つ減らす
                        entity.inventory.setInventorySlotContents(slot, stackRadio)
                        //崩壊後の素材を取得
                        val decayed = material.mapSubMaterials[EnumSubMaterial.DECAYED]
                        if (decayed !== null && !decayed.isEmpty()) {
                            dropItemAtPlayer(entity, ItemStack(this, 1, decayed.index))
                        }
                    }
                }
            }
        }
    }

    //    Client    //

    @SideOnly(Side.CLIENT)
    override fun addInformation(stack: ItemStack, world: World?, tooltip: MutableList<String>, flag: ITooltipFlag) {
        getMaterial(stack).getTooltip(tooltip)
    }

    @SideOnly(Side.CLIENT)
    override fun getItemStackDisplayName(stack: ItemStack): String = I18n.format("item.ragi_${part.name}.name", I18n.format("material.${getMaterial(stack).name}"))

    @SideOnly(Side.CLIENT)
    override fun getSubItems(tab: CreativeTabs, subItems: NonNullList<ItemStack>) {
        if (this.isInCreativeTab(tab)) {
            for (material in MaterialRegistry.getMaterials()) {
                if (material.isValidPart(part)) {
                    subItems.add(material.getPart(part))
                }
            }
        }
    }

    override fun registerCustomModel() {
        ModelManager.setModelAlt(this, ModelResourceLocation("${RagiMaterials.MOD_ID}:part", part.name))
    }

    //    IMaterialItem    //

    override fun getMaterial(stack: ItemStack): RagiMaterial = MaterialRegistry.getMaterial(stack.metadata)

    override fun setMaterial(stack: ItemStack, material: RagiMaterial): ItemStack = stack.also { it.itemDamage = material.index } //メタデータを上書き

}