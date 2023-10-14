package hiiragi283.material.init

import hiiragi283.material.api.item.MaterialItem
import hiiragi283.material.api.registry.HiiragiEntry
import hiiragi283.material.config.HiiragiConfigs
import hiiragi283.material.item.*
import hiiragi283.material.item.material.*
import hiiragi283.material.util.isDeobf

object HiiragiItems : HiiragiEntry.ITEM {


    //    Material    //

    @JvmField
    val BOOK_RESPAWN = ItemBookRespawn.register()

    @JvmField
    val MINECART_TANK = ItemMinecartTank.registerOptional { isDeobf() }

    @JvmField
    val MOTOR = ItemMotor.register()

    @JvmField
    val SHAPE_PATTERN = ItemShapePattern.register()

    @JvmField
    val SMITHING_HAMMER = ItemSmithingHammer.register()

    init {
        MaterialItemBlockStorage.registerOptional { !HiiragiConfigs.EXPERIMENTAL.enableMetaTileBlock }
        MaterialItem(HiiragiShapes.BOTTLE).register()
        MaterialItemCasing.registerOptional { !HiiragiConfigs.EXPERIMENTAL.enableMetaTileBlock }
        MaterialItemDust.register()
        MaterialItemGear.register()
        MaterialItemGem.register()
        MaterialItemIngot.register()
        MaterialItemNugget.register()
        MaterialItemPlate.register()
        MaterialItemStick.register()
    }

}