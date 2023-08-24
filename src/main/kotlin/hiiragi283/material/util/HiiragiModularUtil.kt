package hiiragi283.material.util

import com.cleanroommc.modularui.ModularUI
import com.cleanroommc.modularui.drawable.UITexture
import com.cleanroommc.modularui.manager.GuiCreationContext
import com.cleanroommc.modularui.manager.GuiInfos
import com.cleanroommc.modularui.manager.GuiManager
import com.cleanroommc.modularui.screen.ModularScreen
import com.cleanroommc.modularui.value.sync.FluidSlotSyncHandler
import com.cleanroommc.modularui.widgets.slot.ModularSlot
import hiiragi283.api.capability.fluid.HiiragiFluidTank
import hiiragi283.material.RMReference
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.util.EnumHand
import net.minecraftforge.fluids.IFluidTank
import net.minecraftforge.items.IItemHandler

object HiiragiModularUtil {

    fun getUsedItemStack(creationContext: GuiCreationContext): ItemStack = creationContext.usedItemStack

    //    UITexture    //

    @JvmField
    val LOGO: UITexture = UITexture.builder()
        .location(RMReference.MOD_ID, "logo")
        .imageSize(512, 512)
        .build()

    @JvmField
    var PROGRESS_ARROW_INVERTED: UITexture = UITexture.builder()
        .location(ModularUI.ID, "gui/widgets/progress_bar_arrow_inverted")
        .imageSize(20, 40)
        .build()

    //    Slot    //

    fun itemSlot(handler: IItemHandler, index: Int) = ModularSlot(handler, index)

    fun phantomItemSlot(handler: IItemHandler, index: Int) = ModularSlot(handler, index, true)

    fun itemSlotOut(handler: IItemHandler, index: Int) = ModularSlotOut(handler, index)

    fun phantomItemSlotOut(handler: IItemHandler, index: Int) = ModularSlotOut(handler, index, true)

    fun fluidSlot(tank: HiiragiFluidTank) = FluidSlotSyncHandler(tank as IFluidTank)

    //    GUI    //

    fun openClientGUI(player: EntityPlayer, screen: ModularScreen) {
        GuiManager.openClientUI(player, screen)
    }

    fun openSyncedGUI(hand: EnumHand, player: EntityPlayer) {
        GuiInfos.getForHand(hand).open(player)
    }

}