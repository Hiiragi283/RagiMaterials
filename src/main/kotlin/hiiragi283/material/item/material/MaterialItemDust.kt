package hiiragi283.material.item.material

import hiiragi283.material.api.item.MaterialItem
import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.init.HiiragiItems
import hiiragi283.material.init.HiiragiShapes
import hiiragi283.material.init.materials.MaterialCommons
import hiiragi283.material.util.CraftingBuilder
import hiiragi283.material.util.itemStack
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemDye
import net.minecraft.item.ItemStack
import net.minecraft.util.EnumActionResult
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumHand
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

object MaterialItemDust : MaterialItem(HiiragiShapes.DUST) {

    override fun onItemUse(
        player: EntityPlayer,
        worldIn: World,
        pos: BlockPos,
        hand: EnumHand,
        facing: EnumFacing,
        hitX: Float,
        hitY: Float,
        hitZ: Float
    ): EnumActionResult {
        val stack: ItemStack = player.getHeldItem(hand)
        if (!player.canPlayerEdit(pos.offset(facing), facing, stack)) {
            return EnumActionResult.FAIL
        } else {
            val material: HiiragiMaterial = getMaterial(stack) ?: return EnumActionResult.FAIL
            if (material == MaterialCommons.APATITE) {
                if (ItemDye.applyBonemeal(stack, worldIn, pos, player, hand)) {
                    if (!worldIn.isRemote) {
                        worldIn.playEvent(2005, pos, 0)
                        return EnumActionResult.SUCCESS
                    }
                }
            }
        }
        return EnumActionResult.PASS
    }

    //    MaterialItem    //

    override fun registerRecipe(material: HiiragiMaterial) {
        // 1x Ingot/Gem + 1x Smithing Hammer -> 1x Dust
        val builder: CraftingBuilder = CraftingBuilder(itemStack(material))
            .setPattern("A", "B")
            .setIngredient('A', HiiragiItems.SMITHING_HAMMER, true)
        if (material.isMetal()) {
            builder.setIngredient('B', HiiragiShapes.INGOT.getOreDict(material)).build()
        } else if (material.isGem()) {
            builder.setIngredient('B', HiiragiShapes.GEM.getOreDict(material)).build()
        }
    }

}