package hiiragi283.material.item

import com.cleanroommc.modularui.api.IGuiHolder
import com.cleanroommc.modularui.manager.GuiCreationContext
import com.cleanroommc.modularui.screen.ModularPanel
import com.cleanroommc.modularui.value.sync.GuiSyncManager
import com.cleanroommc.modularui.widgets.ItemSlot
import com.cleanroommc.modularui.widgets.SlotGroupWidget
import com.cleanroommc.modularui.widgets.layout.Column
import hiiragi283.api.capability.HiiragiCapabilityProvider
import hiiragi283.api.capability.IOType
import hiiragi283.api.capability.item.HiiragiItemHandlerWrapper
import hiiragi283.api.capability.item.HiiragiItemStackItemHandler
import hiiragi283.api.item.HiiragiItem
import hiiragi283.material.util.HiiragiModularUtil
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.init.Items
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.util.ActionResult
import net.minecraft.util.EnumActionResult
import net.minecraft.util.EnumHand
import net.minecraft.world.World
import net.minecraftforge.common.capabilities.ICapabilityProvider
import net.minecraftforge.items.CapabilityItemHandler
import net.minecraftforge.items.IItemHandler

object ItemClayBall : HiiragiItem("clay_ball", 0), IGuiHolder {

    override fun onItemRightClick(world: World, player: EntityPlayer, hand: EnumHand): ActionResult<ItemStack> {
        val stack: ItemStack = player.getHeldItem(hand)
        if (!world.isRemote && stack.count >= 2) {
            HiiragiModularUtil.openSyncedGUI(hand, player)
            stack.shrink(2)
        }
        return ActionResult<ItemStack>(EnumActionResult.SUCCESS, player.getHeldItem(hand))
    }

    override fun initCapabilities(stack: ItemStack, nbt: NBTTagCompound?): ICapabilityProvider =
        HiiragiCapabilityProvider(
            CapabilityItemHandler.ITEM_HANDLER_CAPABILITY,
            HiiragiItemHandlerWrapper(HiiragiItemStackItemHandler(stack, 25, ItemStack(Items.CLAY_BALL)).setIOType(IOType.OUTPUT))
        )

    //    IGuiHolder    //

    override fun buildUI(
        creationContext: GuiCreationContext,
        syncManager: GuiSyncManager,
        isClient: Boolean
    ): ModularPanel {

        val itemHandler: IItemHandler =
            HiiragiModularUtil.getUsedItemStack(creationContext).getCapability(
                CapabilityItemHandler.ITEM_HANDLER_CAPABILITY,
                null
            )!!
        syncManager.registerSlotGroup("clay_slot", 5)

        return ModularPanel.defaultPanel("clay_ball")
            .size(176, 176 + 18)
            .child(
                Column()
                    .padding(16)
                    .child(SlotGroupWidget.playerInventory())
                    .child(
                        SlotGroupWidget.builder()
                            .row("IIIII")
                            .row("IIIII")
                            .row("IIIII")
                            .row("IIIII")
                            .row("IIIII")
                            .key('I') {
                                ItemSlot().slot(
                                    HiiragiModularUtil.phantomItemSlotOut(itemHandler, it)
                                        .slotGroup("clay_slot")
                                )
                            }
                            .build()
                    )
            )

    }

}