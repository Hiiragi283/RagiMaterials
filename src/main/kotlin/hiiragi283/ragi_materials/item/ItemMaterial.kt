package hiiragi283.ragi_materials.item

import hiiragi283.ragi_materials.base.ItemBase
import hiiragi283.ragi_materials.Reference
import hiiragi283.ragi_materials.config.RagiConfig
import hiiragi283.ragi_materials.material.MaterialUtil
import hiiragi283.ragi_materials.material.MaterialRegistry
import hiiragi283.ragi_materials.material.builder.MaterialBuilder
import hiiragi283.ragi_materials.material.part.MaterialPart
import hiiragi283.ragi_materials.material.type.EnumMaterialType
import hiiragi283.ragi_materials.util.RagiUtil
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

open class ItemMaterial(val part: MaterialPart) : ItemBase(Reference.MOD_ID, part.name, 32767), IMaterialItem {

    //    General    //

    override fun getForgeRarity(stack: ItemStack): IRarity {
        return getMaterial(stack).rarity
    }

    override fun getItemBurnTime(stack: ItemStack): Int {
        //素材に紐づいた燃焼時間*部品ごとの倍率を返す
        return (MaterialUtil.getMaterial(stack.metadata).burnTime * part.scale).toInt()
    }

    //    Event    //

    override fun onUpdate(stack: ItemStack, world: World, entity: Entity, slot: Int, isSelected: Boolean) {
        //enableDecay==trueの場合
        if (RagiConfig.material.enableDecay) {
            //サーバー側の場合，かつ5秒ごと
            if (!world.isRemote && world.worldInfo.worldTime % 100 == 0L) {
                //entityがplayerの場合
                if (entity is EntityPlayer) {
                    val material = MaterialUtil.getMaterial(stack.metadata)
                    //取得した素材が放射性の場合
                    if (EnumMaterialType.RADIOACTIVE in material.type.list) {
                        val stackRadio = stack.copy()
                        stackRadio.shrink(1) //1つ減らす
                        entity.inventory.setInventorySlotContents(slot, stackRadio)
                        //崩壊後の素材を取得
                        val materialDecayed = material.decayed
                        materialDecayed?.let { RagiUtil.dropItemAtPlayer(entity, ItemStack(this, 1, it.index)) }
                    }
                }
            }
        }
    }

    //    Client    //

    @SideOnly(Side.CLIENT)
    override fun addInformation(stack: ItemStack, world: World?, tooltip: MutableList<String>, flag: ITooltipFlag) {
        MaterialUtil.materialInfo(getMaterial(stack), tooltip)
        super.addInformation(stack, world, tooltip, flag)
    }

    @SideOnly(Side.CLIENT)
    override fun getItemStackDisplayName(stack: ItemStack): String = I18n.format("item.ragi_${part.name}.name", I18n.format("material.${getMaterial(stack).name}"))

    @SideOnly(Side.CLIENT)
    override fun getSubItems(tab: CreativeTabs, subItems: NonNullList<ItemStack>) {
        if (this.isInCreativeTab(tab)) {
            for (material in MaterialRegistry.list) {
                if (MaterialUtil.isValidPart(part, material)) {
                    subItems.add(MaterialUtil.getPart(part, material))
                }
            }
        }
    }

    //    IMaterialItem    //

    override fun getMaterial(stack: ItemStack): MaterialBuilder = MaterialUtil.getMaterial(stack.metadata)

    override fun setMaterial(stack: ItemStack, material: MaterialBuilder): ItemStack = stack.also { it.itemDamage = material.index } //メタデータを上書き

}