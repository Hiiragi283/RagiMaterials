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
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

class ItemMaterialToolBase(val ID: String): ItemBase(Reference.MOD_ID, ID, 0), IMaterialItem {

    private val keyDurability = "durability"
    private val keyMaterial = "material"

    init {
        creativeTab = CreativeTabs.TOOLS
        setMaxStackSize(1)
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

    override fun getDamage(stack: ItemStack): Int {
        return if (hasDurability(stack)) stack.tagCompound!!.getInteger(keyDurability) else 0
    }

    override fun getMaxDamage(stack: ItemStack): Int {
        val material = getMaterial(stack)
        return if (material.durability !== null) material.durability!! else 1 //ゼロ除算回避
    }

    override fun setDamage(stack: ItemStack, damage: Int) {
        if (hasDurability(stack)) {
            stack.tagCompound!!.setInteger(keyDurability, damage.coerceAtLeast(0)) //耐久値が0未満の場合は0になる
        } else {
            addDurability(stack) //新規で耐久値を設定
        }
    }

    private fun addDurability(stack: ItemStack) {
        val tag = stack.tagCompound
        if (!hasDurability(stack)) {
            if (tag == null) {
                stack.tagCompound = NBTTagCompound() //NBTタグがない場合，新規で代入
            }
            val durability = getMaterial(stack).durability
            if (durability !== null) stack.tagCompound!!.setInteger(keyDurability, durability) //耐久値を設定
        }
    }

    private fun hasDurability(stack: ItemStack): Boolean {
        val tag = stack.tagCompound
        return tag !== null && tag.hasKey(keyDurability) //NBTタグがnullでない，かつdurabilityを持つ場合
    }

    //    Tool Type    //

    //    Client    //

    @SideOnly(Side.CLIENT)
    override fun addInformation(stack: ItemStack, world: World?, tooltip: MutableList<String>, flag: ITooltipFlag) {
        MaterialUtil.materialInfo(getMaterial(stack), tooltip)
        tooltip.add(I18n.format("text.ragi_materials.durability", getDamage(stack))) //耐久値
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
        return if (stack.tagCompound !== null) {
            //NBTタグからmaterialを取得
            val tag = stack.tagCompound!!
            MaterialUtil.getMaterial(tag.getString(keyMaterial))
        } else MaterialBuilder.EMPTY
    }

    override fun setMaterial(stack: ItemStack, material: MaterialBuilder): ItemStack {
        val tag = stack.tagCompound
        if (tag == null) {
            stack.tagCompound = NBTTagCompound() //NBTタグがない場合，新規で代入
        }
        stack.tagCompound!!.setString(keyMaterial, material.name) //materialを代入
        addDurability(stack) //最大耐久値を設定
        return stack
    }

}