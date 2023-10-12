package hiiragi283.material

import hiiragi283.material.api.item.MaterialItemNew
import hiiragi283.material.api.registry.HiiragiEntry
import hiiragi283.material.api.registry.HiiragiRegistries
import hiiragi283.material.api.shape.HiiragiShapes
import hiiragi283.material.config.HiiragiConfigs
import hiiragi283.material.item.*
import hiiragi283.material.item.material.*
import hiiragi283.material.util.isDeobf

object HiiragiItemsNew : HiiragiEntry.ITEM {


    //    Material    //

    @JvmField
    val BOOK_RESPAWN = HiiragiRegistries.ITEM.register(ItemBookRespawn)

    @JvmField
    val MINECART_TANK = HiiragiRegistries.ITEM.registerOptional(ItemMinecartTank) { isDeobf() }

    @JvmField
    val MOTOR = HiiragiRegistries.ITEM.register(ItemMotor)

    @JvmField
    val SHAPE_PATTERN = HiiragiRegistries.ITEM.register(ItemShapePattern)

    @JvmField
    val SMITHING_HAMMER = HiiragiRegistries.ITEM.register(ItemSmithingHammer)

    init {
        HiiragiRegistries.ITEM.registerOptional(MaterialItemBlockStorage) { !HiiragiConfigs.EXPERIMENTAL.enableMetaTileBlock }
        HiiragiRegistries.ITEM.register(MaterialItemNew(HiiragiShapes.BOTTLE))
        HiiragiRegistries.ITEM.registerOptional(MaterialItemCasingNew) { !HiiragiConfigs.EXPERIMENTAL.enableMetaTileBlock }
        HiiragiRegistries.ITEM.register(MaterialItemDust)
        HiiragiRegistries.ITEM.register(MaterialItemGear)
        HiiragiRegistries.ITEM.register(MaterialItemGem)
        HiiragiRegistries.ITEM.register(MaterialItemIngot)
        HiiragiRegistries.ITEM.register(MaterialItemNugget)
        HiiragiRegistries.ITEM.register(MaterialItemPlate)
        HiiragiRegistries.ITEM.register(MaterialItemStick)
    }

}