package hiiragi283.ragi_materials.block

import net.minecraft.block.properties.PropertyEnum
import net.minecraft.block.properties.PropertyInteger

object RagiProperty {

    val AGE4: PropertyInteger = PropertyInteger.create("age", 0, 3)
    val AGE6: PropertyInteger = PropertyInteger.create("age", 0, 5)
    val AGE8: PropertyInteger = PropertyInteger.create("age", 0, 7)

    val COUNT8: PropertyInteger = PropertyInteger.create("count", 0, 7)

    val MODE2: PropertyEnum<EnumTransferMode> = PropertyEnum.create("mode", EnumTransferMode::class.java)

    val TYPE16: PropertyInteger = PropertyInteger.create("type", 0, 15)

}