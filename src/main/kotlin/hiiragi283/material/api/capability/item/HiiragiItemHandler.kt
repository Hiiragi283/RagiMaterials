package hiiragi283.material.api.capability.item

import hiiragi283.material.api.capability.IOControllable
import hiiragi283.material.util.HiiragiNBTUtil
import hiiragi283.material.util.getStringOrNull
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.tileentity.TileEntity
import net.minecraftforge.items.ItemStackHandler

open class HiiragiItemHandler(
    size: Int = 1,
    override var ioType: IOControllable.Type = IOControllable.Type.GENERAL,
    val tile: TileEntity? = null
) : ItemStackHandler(size), IOControllable {

    override fun onContentsChanged(slot: Int) {
        tile?.markDirty();
    }

    //    INBTSerializable    //

    override fun serializeNBT(): NBTTagCompound =
        super.serializeNBT().also { tag: NBTTagCompound -> tag.setString(HiiragiNBTUtil.IO_TYPE, ioType.name) }

    override fun deserializeNBT(nbt: NBTTagCompound) {
        super.deserializeNBT(nbt)
        nbt.getStringOrNull(HiiragiNBTUtil.IO_TYPE)?.let { name: String -> ioType = IOControllable.Type.valueOf(name) }
    }

}