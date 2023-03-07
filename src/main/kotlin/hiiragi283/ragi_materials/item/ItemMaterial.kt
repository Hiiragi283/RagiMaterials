package hiiragi283.ragi_materials.item

import hiiragi283.ragi_materials.base.ItemBase
import hiiragi283.ragi_materials.Reference
import hiiragi283.ragi_materials.config.RagiConfig
import hiiragi283.ragi_materials.init.RagiInit
import hiiragi283.ragi_materials.material.IMaterialItem
import hiiragi283.ragi_materials.material.MaterialBuilder
import hiiragi283.ragi_materials.material.MaterialUtil
import hiiragi283.ragi_materials.material.MaterialRegistry
import hiiragi283.ragi_materials.material.type.EnumMaterialType
import hiiragi283.ragi_materials.material.type.TypeRegistry
import hiiragi283.ragi_materials.util.RagiUtil
import net.minecraft.client.resources.I18n
import net.minecraft.client.util.ITooltipFlag
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.entity.Entity
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.util.NonNullList
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

open class ItemMaterial(private val ID: String, private val type: EnumMaterialType) : ItemBase(Reference.MOD_ID, ID, RagiConfig.material.maxMaterials), IMaterialItem {

    init {
        creativeTab = RagiInit.TabMaterials
    }

    //    General    //

    override fun getItemBurnTime(stack: ItemStack): Int {
        return run {
            //素材に紐づいた燃焼時間を取得
            var time = MaterialUtil.getMaterial(stack.metadata).burnTime
            //dust_tinyの場合は1/9
            if (stack.item.registryName!!.resourcePath == "dust_tiny") time /= 9
            time
        }
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
                    if (material.type.parts.contains(EnumMaterialType.RADIOACTIVE)) {
                        val stackRadio = stack.copy()
                        stackRadio.shrink(1) //1つ減らす
                        entity.inventory.setInventorySlotContents(slot, stackRadio)
                        //崩壊後の素材を取得
                        val materialDecayed = material.decayed
                        if (materialDecayed !== null) {
                            val stackDecayed = ItemStack(this, 1, materialDecayed.index)
                            RagiUtil.spawnItemAtPlayer(world, entity, stackDecayed) //プレイヤーの足元にドロップ
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
        super.addInformation(stack, world, tooltip, flag)
    }

    @SideOnly(Side.CLIENT)
    override fun getItemStackDisplayName(stack: ItemStack): String {
        val material = getMaterial(stack)
        return I18n.format("item.ragi_$ID.name", I18n.format("material.${material.name}"))
    }

    @SideOnly(Side.CLIENT)
    override fun getSubItems(tab: CreativeTabs, subItems: NonNullList<ItemStack>) {
        if (isInCreativeTab(tab)) {
            //list内の各materialに対して実行
            for (material in MaterialRegistry.mapIndex.values) {
                //typeがINTERNALでない，かつmaterialのtypeが一致する場合
                if (material.type != TypeRegistry.INTERNAL && material.type.parts.contains(type)) {
                    //ItemStackをlistに追加
                    val stack = ItemStack(this, 1, material.index)
                    subItems.add(stack)
                }
            }
        }
    }

    //    IMaterialItem    //

    override fun getMaterial(stack: ItemStack): MaterialBuilder {
        return MaterialUtil.getMaterial(stack.metadata)
    }

    override fun setMaterial(stack: ItemStack, material: MaterialBuilder): ItemStack {
        stack.itemDamage = material.index //メタデータを上書き
        return stack
    }
}