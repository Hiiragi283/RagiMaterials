package hiiragi283.api.tileentity

import hiiragi283.api.container.HiiragiContainer
import hiiragi283.material.RMReference
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.entity.player.InventoryPlayer
import net.minecraft.inventory.Container
import net.minecraft.util.text.TextComponentTranslation
import net.minecraft.world.IInteractionObject

interface HiiragiInteractionObject<T : HiiragiContainer<*>> : IInteractionObject {

    val classContainer: Class<T>

    val guiName: String

    override fun createContainer(playerInventory: InventoryPlayer, playerIn: EntityPlayer): Container =
        classContainer.getConstructor(EntityPlayer::class.java, this::class.java).newInstance(playerIn, this)

    override fun getDisplayName() = TextComponentTranslation(this.name)

    override fun getGuiID(): String = "${RMReference.MOD_ID}.$guiName"

    override fun getName() = "gui.${getGuiID()}"

    override fun hasCustomName() = false

}