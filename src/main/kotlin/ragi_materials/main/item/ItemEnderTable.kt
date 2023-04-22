package ragi_materials.main.item

import ragi_materials.core.RagiMaterials
import ragi_materials.core.util.playSound
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.init.SoundEvents
import net.minecraft.item.ItemStack
import net.minecraft.stats.StatList
import net.minecraft.util.ActionResult
import net.minecraft.util.EnumActionResult
import net.minecraft.util.EnumHand
import net.minecraft.world.World
import ragi_materials.core.item.ItemBase

class ItemEnderTable : ItemBase(RagiMaterials.MOD_ID, "ender_tablet", 0) {

    init {
        setMaxStackSize(1)
    }

    //    Event    //

    override fun onItemRightClick(world: World, player: EntityPlayer, hand: EnumHand): ActionResult<ItemStack> {
        val enderChest = player.inventoryEnderChest
        player.displayGUIChest(enderChest)
        player.addStat(StatList.ENDERCHEST_OPENED)
        playSound(world, player.position, SoundEvents.BLOCK_ENDERCHEST_OPEN)
        return ActionResult(EnumActionResult.SUCCESS, player.getHeldItem(hand))
    }

}