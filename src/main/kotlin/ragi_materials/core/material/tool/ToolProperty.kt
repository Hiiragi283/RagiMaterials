package ragi_materials.core.material.tool

import net.minecraft.nbt.NBTTagCompound

data class ToolProperty(
        val name: String,
        val maxDamage: Int,
        val harvestLevel: Int,
        val efficiency: Float,
        val attackDamage: Float,
        val attackSpeed: Float,
        val enchantability: Int
) {

    companion object {
        @JvmStatic
        val EMPTY = ToolProperty("empty", 0, 0, 0.0f, 0.0f, 0.0f, 0)
    }

    fun writeToNBT(tag: NBTTagCompound? = null): NBTTagCompound {
        val tag1 = tag ?: NBTTagCompound()
        tag1.setTag("toolProperty", NBTTagCompound().also {
            it.setString("name", name)
            it.setInteger("maxDamage", maxDamage)
            it.setInteger("level", harvestLevel)
            it.setFloat("efficiency", efficiency)
            it.setFloat("attackDamage", attackDamage)
            it.setFloat("attackSpeed", attackSpeed)
            it.setInteger("enchantability", enchantability)
        })
        return tag1
    }

    fun readFromNBT(tag: NBTTagCompound): ToolProperty {
        if (tag.hasKey("toolProperty")) {
            val tag1 = tag.getCompoundTag("toolProperty")
            return ToolProperty(
                    tag1.getString("name"),
                    tag1.getInteger("maxDamage"),
                    tag1.getInteger("level"),
                    tag1.getFloat("efficiency"),
                    tag1.getFloat("attackDamage"),
                    tag1.getFloat("attackSpeed"),
                    tag1.getInteger("enchantability")
            )
        }
        return EMPTY
    }

}