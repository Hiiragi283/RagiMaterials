package hiiragi283.material.item

import hiiragi283.material.api.item.HiiragiItem
import hiiragi283.material.init.HiiragiShapes
import hiiragi283.material.init.materials.MaterialCommons
import hiiragi283.material.util.CraftingBuilder
import hiiragi283.material.util.itemStack
import net.minecraft.block.state.IBlockState
import net.minecraft.client.resources.I18n
import net.minecraft.client.util.ITooltipFlag
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.util.EnumActionResult
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumHand
import net.minecraft.util.Rotation
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

object ItemSmithingHammer : HiiragiItem("smithing_hammer") {

    init {
        maxStackSize = 1
        maxDamage = 1024
    }

    //    Crafting    //

    override fun getContainerItem(stack: ItemStack): ItemStack = stack.copy().also { it.itemDamage += 1 }

    override fun hasContainerItem(stack: ItemStack): Boolean = true

    //    Event    //

    override fun onItemUseFirst(
        player: EntityPlayer,
        world: World,
        pos: BlockPos,
        side: EnumFacing,
        hitX: Float,
        hitY: Float,
        hitZ: Float,
        hand: EnumHand
    ): EnumActionResult {
        val state: IBlockState = world.getBlockState(pos)
        return if (!world.isRemote) {
            val stack: ItemStack = player.getHeldItem(hand)
            if (player.isSneaking) {
                world.setBlockState(pos, state.withRotation(Rotation.COUNTERCLOCKWISE_90))
                stack.damageItem(1, player)
                EnumActionResult.SUCCESS
            } else {
                world.setBlockState(pos, state.withRotation(Rotation.CLOCKWISE_90))
                stack.damageItem(1, player)
                EnumActionResult.SUCCESS
            }
        } else super.onItemUseFirst(player, world, pos, side, hitX, hitY, hitZ, hand)
    }

    //    Client    //

    @SideOnly(Side.CLIENT)
    override fun addInformation(stack: ItemStack, world: World?, tooltip: MutableList<String>, flag: ITooltipFlag) {
        tooltip.add(I18n.format("tips.ragi_materials.item.smithing_hammer"))
    }

    @SideOnly(Side.CLIENT)
    override fun isFull3D(): Boolean = true

    //    HiiragiEntry    //

    override fun onInit() {
        CraftingBuilder(this.itemStack())
            .setPattern(" A ", " BA", "B  ")
            .setIngredient('A', HiiragiShapes.INGOT.getOreDict(MaterialCommons.STEEL))
            .setIngredient('B', HiiragiShapes.STICK.getOreDict(MaterialCommons.WOOD))
            .build()
    }

}