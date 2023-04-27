package ragi_materials.core.block.property

import net.minecraft.block.properties.PropertyDirection
import net.minecraft.block.properties.PropertyEnum
import net.minecraft.block.properties.PropertyInteger
import net.minecraft.util.EnumFacing

object RagiProperty {

    val AGE4: PropertyInteger = PropertyInteger.create("age", 0, 3)
    val AGE6: PropertyInteger = PropertyInteger.create("age", 0, 5)
    val AGE8: PropertyInteger = PropertyInteger.create("age", 0, 7)

    val BLOOMERY: PropertyEnum<EnumBloomeryType> = PropertyEnum.create("type", EnumBloomeryType::class.java)

    val COUNT8: PropertyInteger = PropertyInteger.create("count", 0, 7)

    //0 -> NORTH, 1 -> EAST, 2 -> SOUTH, 3 -> WEST
    val HORIZONTAL: PropertyDirection = PropertyDirection.create("facing", listOf(EnumFacing.NORTH, EnumFacing.EAST, EnumFacing.SOUTH, EnumFacing.WEST))

    val MODE2: PropertyEnum<EnumTransferMode> = PropertyEnum.create("mode", EnumTransferMode::class.java)

    val TYPE16: PropertyInteger = PropertyInteger.create("type", 0, 15)

}