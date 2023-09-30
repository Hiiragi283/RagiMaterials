package hiiragi283.material.util

import net.minecraft.item.ItemStack
import net.minecraftforge.items.IItemHandler
import net.minecraftforge.items.IItemHandlerModifiable

//    IItemHandler    //

fun IItemHandler.isEmpty(): Boolean {
    return (0 until slots)
        .map { slot: Int -> getStackInSlot(slot) }
        .all { stack: ItemStack -> stack.isEmpty }
}

fun IItemHandlerModifiable.clear(): Unit = clear(0 until slots)

fun IItemHandlerModifiable.clear(range: IntRange) {
    range.forEach { slot: Int -> setStackInSlot(slot, ItemStack.EMPTY) }
}

//実行結果の合否が返される
fun IItemHandler.transferTo(slotFrom: Int, handlerTo: IItemHandler, simulate: Boolean): Boolean {
    //搬出対象がEMPTY -> false
    val stack: ItemStack = this.getStackInSlot(slotFrom).notEmpty() ?: return false
    //一時的に結果を保存する
    var cache: ItemStack = stack
    for (slotTo: Int in (0 until handlerTo.slots)) {
        //cacheがEMPTY -> true
        if (cache.isEmpty) return true
        //相手に搬入した際の戻り値を取得
        val stackInserted: ItemStack = handlerTo.insertItem(slotTo, cache, true)
        //搬入の前後で変化 -> 搬入する余地がある
        if (cache != stackInserted) {
            //実際に搬出，搬入を行う
            this.extractItem(slotFrom, cache.count - stackInserted.count, simulate)
            cache = handlerTo.insertItem(slotTo, cache, simulate)
        }
    }
    return false
}

fun IItemHandler.transferAllTo(handlerTo: IItemHandler, simulate: Boolean): Boolean {
    (0 until slots).forEach {
        return transferTo(it, handlerTo, simulate)
    }
    return false
}