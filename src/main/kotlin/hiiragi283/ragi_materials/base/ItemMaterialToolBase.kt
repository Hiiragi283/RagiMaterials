package hiiragi283.ragi_materials.base

import hiiragi283.ragi_materials.Reference
import hiiragi283.ragi_materials.material.IMaterialItem
import hiiragi283.ragi_materials.material.MaterialBuilder
import hiiragi283.ragi_materials.material.MaterialRegistry
import hiiragi283.ragi_materials.material.MaterialUtil
import net.minecraft.client.resources.I18n
import net.minecraft.client.util.ITooltipFlag
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.util.NonNullList
import net.minecraft.world.World
import net.minecraftforge.common.IRarity
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

open class ItemMaterialToolBase(private val ID: String) : ItemBase(Reference.MOD_ID, ID, 0), IMaterialItem {

    private val keyDurability = "durability"
    private val keyMaterial = "material"

    init {
        creativeTab = CreativeTabs.TOOLS
        maxStackSize = 1
    }

    //    General    //

    override fun getForgeRarity(stack: ItemStack): IRarity {
        return getMaterial(stack).rarity
    }

    //    Crafting    //

    override fun getContainerItem(stack: ItemStack): ItemStack {
        val stack1 = stack.copy()
        setDamage(stack1, getDamage(stack1) - 1) //耐久地を1つ減らす
        return if (getDamage(stack1) < 0) ItemStack.EMPTY else stack1 //耐久地が0未満になったら壊れる
    }

    override fun hasContainerItem(stack: ItemStack): Boolean {
        return true
    }

    //    Durability    //

    fun decrDamage(stack: ItemStack, damage: Int) = setDamage(stack, getDamage(stack) - damage) //damageの分だけ耐久地を減らす

    override fun getDamage(stack: ItemStack): Int = if (hasDurability(stack)) stack.tagCompound!!.getInteger(keyDurability) else 0

    override fun getMaxDamage(stack: ItemStack): Int = getMaterial(stack).durability ?: 1 //ゼロ除算回避

    override fun setDamage(stack: ItemStack, damage: Int) {
        if (hasDurability(stack)) stack.tagCompound!!.setInteger(keyDurability, damage.coerceAtLeast(0)) //耐久値が0未満の場合は0になる
        else addDurability(stack) //新規で耐久値を設定
    }

    private fun addDurability(stack: ItemStack) {
        if (!hasDurability(stack)) {
            val durability = getMaterial(stack).durability
            durability?.run { stack.tagCompound!!.setInteger(keyDurability, durability) }//耐久値を設定
        }
    }

    private fun hasDurability(stack: ItemStack): Boolean {
        val tag = stack.tagCompound
        return tag !== null && tag.hasKey(keyDurability) //NBTタグがnullでない，かつdurabilityを持つ場合
    }

    //    Client    //

    @SideOnly(Side.CLIENT)
    override fun addInformation(stack: ItemStack, world: World?, tooltip: MutableList<String>, flag: ITooltipFlag) {
        MaterialUtil.materialInfo(getMaterial(stack), tooltip)
        tooltip.add(I18n.format("tips.ragi_materials.property.durability", getDamage(stack))) //耐久値
        super.addInformation(stack, world, tooltip, flag)
    }

    @SideOnly(Side.CLIENT)
    override fun showDurabilityBar(stack: ItemStack): Boolean {
        return if (hasDurability(stack)) true else super.showDurabilityBar(stack)
    }

    @SideOnly(Side.CLIENT)
    override fun getDurabilityForDisplay(stack: ItemStack): Double {
        //耐久値を表示する場合
        return if (showDurabilityBar(stack)) {
            //現在の耐久値を最大耐久値で割った値を返す
            //1.0から引いているのはマイクラの仕様上
            1.0 - (getDamage(stack).toDouble() / getMaxDamage(stack).toDouble())
        } else super.getDurabilityForDisplay(stack)
    }

    @SideOnly(Side.CLIENT)
    override fun getItemStackDisplayName(stack: ItemStack): String {
        val material = getMaterial(stack)
        return I18n.format("item.ragi_$ID.name", I18n.format("material.${material.name}"))
    }

    @SideOnly(Side.CLIENT)
    override fun getSubItems(tab: CreativeTabs, subItems: NonNullList<ItemStack>) {
        val stack = ItemStack(this)
        if (isInCreativeTab(tab)) {
            for (material in MaterialRegistry.mapIndex.values) {
                if (material.durability !== null) {
                    subItems.add(setMaterial(stack.copy(), material))
                }
            }
        }
    }

    //    IMaterialItem    //

    override fun getMaterial(stack: ItemStack): MaterialBuilder {
        stack.tagCompound ?: run { stack.tagCompound = NBTTagCompound() } //NBTタグがない場合は新規で代入
        return MaterialUtil.getMaterial(stack.tagCompound!!.getString(keyMaterial))
    }

    override fun setMaterial(stack: ItemStack, material: MaterialBuilder): ItemStack {
        stack.tagCompound ?: run { stack.tagCompound = NBTTagCompound() } //NBTタグがない場合は新規で代入
        stack.tagCompound!!.setString(keyMaterial, material.name) //materialを代入
        addDurability(stack) //最大耐久値を設定
        return stack
    }

}