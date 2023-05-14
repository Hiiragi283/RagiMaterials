package hiiragi283.material.item

import hiiragi283.material.util.IIdentifiable
import net.minecraft.item.Item
import net.minecraft.util.Identifier

abstract class ItemBase(settings: Settings) : Item(settings), IIdentifiable {

    abstract override fun getIdentifier(): Identifier

}