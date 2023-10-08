package hiiragi283.material.api.block.property

import net.minecraft.block.BlockRailBase.EnumRailDirection
import net.minecraft.block.properties.PropertyEnum
import net.minecraft.block.properties.PropertyInteger

object HiiragiProperty {

    @JvmField
    val TYPE16: PropertyInteger = PropertyInteger.create("type", 0, 15)

    @JvmField
    val RAIL_SHAPE: PropertyEnum<EnumRailDirection> = PropertyEnum.create(
        "shape",
        EnumRailDirection::class.java
    ) { value -> value != EnumRailDirection.NORTH_EAST && value != EnumRailDirection.NORTH_WEST && value != EnumRailDirection.SOUTH_EAST && value != EnumRailDirection.SOUTH_WEST }

}