package hiiragi283.material.api.material

import net.minecraft.block.BlockState
import net.minecraft.entity.Entity
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import net.minecraft.item.ItemUsageContext
import net.minecraft.util.ActionResult
import net.minecraft.util.Hand
import net.minecraft.util.TypedActionResult
import net.minecraft.util.UseAction
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import java.util.*

class MaterialWrapper {

    //    block    //

    var randomDisplayTick: (BlockState, World, BlockPos, Random) -> Unit =
        { state, world, pos, random -> }

    //    Item    //

    var useOnBlock: (ItemUsageContext) -> ActionResult = { context -> ActionResult.PASS }

    var use: (World, PlayerEntity, Hand) -> TypedActionResult<ItemStack> =
        { world, user, hand -> TypedActionResult.pass(user.getStackInHand(hand)) }

    var useOnEntity: (ItemStack, PlayerEntity, LivingEntity, Hand) -> ActionResult =
        { stack, user, entity, hand -> ActionResult.PASS }

    var inventoryTick: (ItemStack, World, Entity, Int, Boolean) -> Unit =
        { stack, world, entity, slot, selected -> }

    var useAction: (ItemStack) -> UseAction = { UseAction.NONE }

}