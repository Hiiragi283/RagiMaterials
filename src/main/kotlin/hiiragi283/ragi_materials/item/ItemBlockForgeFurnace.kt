package hiiragi283.ragi_materials.item

import hiiragi283.ragi_materials.base.ItemBlockBase
import hiiragi283.ragi_materials.init.RagiInit
import net.minecraft.client.resources.I18n
import net.minecraft.client.util.ITooltipFlag
import net.minecraft.item.ItemStack
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

class ItemBlockForgeFurnace: ItemBlockBase(RagiInit.BlockForgeFurnace, 0) {

    init {
        creativeTab = RagiInit.TabBlocks
    }

    @SideOnly(Side.CLIENT)
    override fun addInformation(stack: ItemStack, world: World?, tooltip: MutableList<String>, flag: ITooltipFlag) {
        val path = stack.item.registryName.toString().split(":")[1]
        tooltip.add("§e=== Info ===")
        tooltip.add(I18n.format("text.ragi_materials.${path}.0"))
        tooltip.add(I18n.format("text.ragi_materials.${path}.1"))
        tooltip.add(I18n.format("text.ragi_materials.${path}.2"))
        tooltip.add(I18n.format("text.ragi_materials.${path}.3"))
        super.addInformation(stack, world, tooltip, ITooltipFlag.TooltipFlags.NORMAL)
    }
}