@file:JvmName("RMCalendarHandler")

package hiiragi283.core.util

import hiiragi283.core.RagiMaterials
import java.util.*

//4月1日かを判定するメソッド
fun isAprilFools(): Boolean =
    RagiMaterials.CALENDAR.get(Calendar.MONTH) + 1 == 4 && RagiMaterials.CALENDAR.get(Calendar.DATE) == 1