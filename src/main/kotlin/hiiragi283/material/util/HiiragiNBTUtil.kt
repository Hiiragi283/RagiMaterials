package hiiragi283.material.util

import net.minecraft.nbt.NBTTagCompound
import net.minecraft.nbt.NBTTagList

//    NBT    //

fun NBTTagCompound.getByteOrNull(key: String): Byte? = if (this.hasKey(key)) this.getByte(key) else null

fun NBTTagCompound.getShortOrNull(key: String): Short? = if (this.hasKey(key)) this.getShort(key) else null

fun NBTTagCompound.getIntegerOrNull(key: String): Int? = if (this.hasKey(key)) this.getInteger(key) else null

fun NBTTagCompound.getLongOrNull(key: String): Long? = if (this.hasKey(key)) this.getLong(key) else null

fun NBTTagCompound.getFloatOrNull(key: String): Float? = if (this.hasKey(key)) this.getFloat(key) else null

fun NBTTagCompound.getDoubleOrNull(key: String): Double? = if (this.hasKey(key)) this.getDouble(key) else null

fun NBTTagCompound.getStringOrNull(key: String): String? = if (this.hasKey(key)) this.getString(key) else null

fun NBTTagCompound.getByteArrayOrNull(key: String): ByteArray? = if (this.hasKey(key)) this.getByteArray(key) else null

fun NBTTagCompound.getIntArrayOrNull(key: String): IntArray? = if (this.hasKey(key)) this.getIntArray(key) else null

fun NBTTagCompound.getCompoundTagOrNull(key: String): NBTTagCompound? =
    if (this.hasKey(key)) this.getCompoundTag(key) else null

fun NBTTagCompound.getTagListOrNull(key: String, type: Int): NBTTagList? =
    if (this.hasKey(key)) this.getTagList(key, type) else null

fun NBTTagCompound.getOrCreateCompoundTag(key: String): NBTTagCompound = if (this.hasKey(key)) {
    this.getCompoundTag(key)
} else {
    val tag = NBTTagCompound()
    this.setTag(key, tag)
    tag
}

object HiiragiNBTUtil {

    const val AMOUNT = "amount"
    const val BATTERY = "Battery"
    const val BLOCK_ENTITY_TAG = "BlockEntityTag"
    const val CAPACITY = "Capacity"
    const val CURRENT_COUNT = "CurrentCount"
    const val DIM_ID = "DimID"
    const val ENTITY_ID = "EntityId"
    const val FLUID = "Fluid"
    const val FORGE_CAPS = "ForgeCaps"
    const val INVENTORY = "Inventory"
    const val IO_TYPE = "IOType"
    const val MACHINE_PROPERTY = "MachineProperty"
    const val MASS = "Mass"
    const val MATERIAL = "Material"
    const val MATERIALS = "Materials"
    const val MAX_COUNT = "MaxCount"
    const val OWNER = "Owner"
    const val TANK = "Tank"
    const val TYPE = "Type"

}