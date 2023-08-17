package hiiragi283.material.item

import hiiragi283.api.item.HiiragiItem
import hiiragi283.api.item.ICastItem
import hiiragi283.api.item.ItemMaterial
import hiiragi283.api.material.MaterialCommon
import hiiragi283.api.material.MaterialStack
import hiiragi283.api.part.HiiragiPart
import hiiragi283.material.RMReference
import net.minecraft.client.renderer.color.ItemColors
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

class ItemCast(val item: ItemMaterial) : HiiragiItem("cast_${item.shape.name}", 0), ICastItem {

    init {
        maxDamage = 63
        maxStackSize = 1
    }

    //    ICastItem    //

    override fun getCastItem(): Item = this

    override fun getMaterialAmount(): Int = item.shape.scale

    override fun getResult(materialStack: MaterialStack): ItemStack {
        return if (!materialStack.isEmpty()) {
            HiiragiPart(
                item.shape,
                materialStack.material
            ).findItemStack("minecraft", RMReference.MOD_ID)
        } else ItemStack.EMPTY
    }

    //    HiiragiEntry    //

    @SideOnly(Side.CLIENT)
    override fun registerColorItem(itemColors: ItemColors) {
        itemColors.registerItemColorHandler(
            { stack, tintIndex -> if (tintIndex == 1) MaterialCommon.WOOD.color else -1 },
            this
        )
    }

}