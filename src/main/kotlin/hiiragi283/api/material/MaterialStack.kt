package hiiragi283.api.material

import hiiragi283.api.HiiragiRegistry
import net.minecraft.client.resources.I18n
import net.minecraft.nbt.NBTTagCompound
import net.minecraftforge.fluids.Fluid
import net.minecraftforge.fluids.FluidStack

data class MaterialStack(val material: HiiragiMaterial, var amount: Int) {

    constructor(name: String, amount: Int) : this(HiiragiRegistry.getMaterial(name), amount)

    constructor(compound: NBTTagCompound) : this(compound.getString("material"), compound.getInteger("amount"))

    constructor(fluid: Fluid, amount: Int) : this(fluid.name, amount)

    constructor(fluidStack: FluidStack) : this(fluidStack.fluid, fluidStack.amount)

    companion object {

        @JvmField
        val EMPTY = MaterialStack(HiiragiMaterial.EMPTY, 0)

    }

    fun addTooltip(tooltip: MutableList<String>) {
        if (material.isEmpty()) return
        tooltip.add(material.getTranslatedName())
        tooltip.add("§e=== Property ===")
        if (material.hasFormula())
            tooltip.add(I18n.format("tips.ragi_materials.property.formula", material.formula))
        if (material.hasMolar())
            tooltip.add(I18n.format("tips.ragi_materials.property.mol", material.molar))
        if (amount > 0)
            tooltip.add(I18n.format("tips.ragi_materials.property.scale", amount))
        if (material.hasTempMelt())
            tooltip.add(I18n.format("tips.ragi_materials.property.melt", material.tempMelt))
        if (material.hasTempBoil())
            tooltip.add(I18n.format("tips.ragi_materials.property.boil", material.tempBoil))
        if (material.hasTempSubl())
            tooltip.add(I18n.format("tips.ragi_materials.property.subl", material.tempSubl))
    }

    fun isEmpty(): Boolean = this == EMPTY || this.material == HiiragiMaterial.EMPTY || this.amount <= 0

    //    Amount    //

    fun addAmount(add: Int): MaterialStack = also { it.amount += add }

    fun removeAmount(remove: Int): MaterialStack = also { it.amount -= remove }

    fun setAmount(set: Int): MaterialStack = also { it.amount = set }

    fun multiplyAmount(multi: Int): MaterialStack = also { it.amount *= multi }

    fun divideAmount(div: Int): MaterialStack = also { it.amount /= div }

    fun equalsMaterial(other: MaterialStack): Boolean = this.material == other.material

    //    Serialization    //

    fun serializeNBT(): NBTTagCompound = NBTTagCompound().also {
        it.setString("material", material.name)
        it.setInteger("amount", amount)
    }

    //    General    //

    override fun toString(): String = "$material:$amount"

}