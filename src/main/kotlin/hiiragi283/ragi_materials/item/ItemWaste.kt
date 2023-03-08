package hiiragi283.ragi_materials.item

import hiiragi283.ragi_materials.Reference
import hiiragi283.ragi_materials.base.ItemBase
import hiiragi283.ragi_materials.init.RagiInit
import hiiragi283.ragi_materials.material.IMaterialItem
import hiiragi283.ragi_materials.material.MaterialBuilder
import hiiragi283.ragi_materials.material.MaterialRegistry
import hiiragi283.ragi_materials.util.RagiUtil
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.entity.Entity
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.util.NonNullList
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

class ItemWaste : ItemBase(Reference.MOD_ID, "waste", 0), IMaterialItem {

    init {
        creativeTab = RagiInit.TabMaterials
    }

    //    General    //

    //    Event    //

    override fun onUpdate(stack: ItemStack, world: World, entity: Entity, slot: Int, isSelected: Boolean) {
        //サーバー側の場合，かつ5秒ごと
        if (!world.isRemote && world.worldInfo.worldTime % 100 == 0L) {
            //entityがplayerの場合
            if (entity is EntityPlayer) {
                when (stack.metadata) {
                    //化学廃棄物 -> 毒デバフ
                    0 -> entity.addPotionEffect(RagiUtil.getPotionEffect("minecraft:poison", 110, 0))
                    else -> {}
                }
            }
        }
    }

    //    Client    //

    @SideOnly(Side.CLIENT)
    override fun getSubItems(tab: CreativeTabs, subItems: NonNullList<ItemStack>) {
        if (isInCreativeTab(tab)) {
            for (i in 0..this.maxMeta) {
                subItems.add(ItemStack(this, 1, i))
            }
        }
    }

    //    IMaterialItem    //

    override fun getMaterial(stack: ItemStack): MaterialBuilder = MaterialRegistry.SOUL_SAND

    override fun setMaterial(stack: ItemStack, material: MaterialBuilder): ItemStack = stack

}