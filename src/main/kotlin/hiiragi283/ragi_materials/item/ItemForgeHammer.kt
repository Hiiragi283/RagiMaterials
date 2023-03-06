package hiiragi283.ragi_materials.item

import hiiragi283.ragi_materials.Reference
import hiiragi283.ragi_materials.base.ItemBase
import hiiragi283.ragi_materials.material.IMaterialItem
import hiiragi283.ragi_materials.material.MaterialBuilder
import hiiragi283.ragi_materials.material.MaterialUtil
import net.minecraft.item.ItemStack

class ItemForgeHammer : ItemBase(Reference.MOD_ID, "forge_hammer", 0), IMaterialItem {

    init {
        maxDamage = 63
        setMaxStackSize(1)
    }

    //    Crafting    //

    override fun getContainerItem(stack: ItemStack): ItemStack {
        val stack1 = stack.copy()
        stack1.itemDamage += 1
        return stack1
    }

    override fun hasContainerItem(stack: ItemStack): Boolean {
        return true
    }

    //    IMaterialItem    //
    
    override fun getMaterial(stack: ItemStack): MaterialBuilder {
        return if (stack.tagCompound !== null) {
            //NBTタグからmaterialを取得
            val tag = stack.tagCompound!!
            MaterialUtil.getMaterial(tag.getInteger("material"))
        } else MaterialBuilder.EMPTY
    }
}