package ragi_materials.core.item

import com.google.common.collect.Multimap
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.EntityLivingBase
import net.minecraft.entity.SharedMonsterAttributes
import net.minecraft.entity.ai.attributes.AttributeModifier
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.inventory.EntityEquipmentSlot
import net.minecraft.item.ItemStack
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import ragi_materials.core.RagiMaterials
import ragi_materials.core.material.tool.ToolProperty

open class ItemToolBase(ID: String, val toolClass: Collection<String>, val property: ToolProperty) : ItemBase(RagiMaterials.MOD_ID, ID, 0) {

    init {
        maxDamage = property.maxDamage
        maxStackSize = 1
    }

    //    General    //

    //採掘速度を取得するメソッド
    override fun getDestroySpeed(stack: ItemStack, state: IBlockState): Float {
        for (type in getToolClasses(stack)) {
            if (state.block.isToolEffective(type, state)) return property.efficiency
        }
        return 1.0f
    }

    //採掘レベルを取得するメソッド
    override fun getHarvestLevel(stack: ItemStack, toolClass: String, player: EntityPlayer?, blockState: IBlockState?): Int {
        val level = super.getHarvestLevel(stack, toolClass, player, blockState)
        return if (level == -1 && toolClass in this.toolClass) property.harvestLevel else level
    }

    //Attributeを取得するメソッド
    @Deprecated("Deprecated in Java")
    override fun getItemAttributeModifiers(equipmentSlot: EntityEquipmentSlot): Multimap<String, AttributeModifier> {
        val multimap = super.getItemAttributeModifiers(equipmentSlot)
        if (equipmentSlot == EntityEquipmentSlot.MAINHAND) {
            multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.name, AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Tool modifier", property.attackDamage.toDouble(), 0))
            multimap.put(SharedMonsterAttributes.ATTACK_SPEED.name, AttributeModifier(ATTACK_SPEED_MODIFIER, "Tool modifier", property.attackSpeed.toDouble(), 0))
        }
        return multimap
    }

    //エンチャント適正を取得するメソッド
    override fun getItemEnchantability() = property.enchantability

    //道具の種類を取得するメソッド
    override fun getToolClasses(stack: ItemStack) = toolClass.toSet()

    //    Event    //

    //ブロック破壊時に呼び出されるメソッド
    override fun onBlockDestroyed(stack: ItemStack, worldIn: World, state: IBlockState, pos: BlockPos, entityLiving: EntityLivingBase): Boolean {
        if (!worldIn.isRemote && state.getBlockHardness(worldIn, pos) != 0.0f) stack.damageItem(1, entityLiving)
        return true
    }

    //    Client    //

    @SideOnly(Side.CLIENT)
    override fun isFull3D() = true

}