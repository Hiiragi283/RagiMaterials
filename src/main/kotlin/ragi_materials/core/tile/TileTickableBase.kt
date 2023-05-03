package ragi_materials.core.tile

import net.minecraft.util.ITickable

abstract class TileTickableBase(private val maxCount: Int) : TileBase(), ITickable {

    var countdown = 0

    //    ITickable    //

    override fun update() {
        onUpdateTick()
        if (countdown >= maxCount) {
            onUpdate()
            if (!world.isRemote) {
                onUpdateServer()
            } else {
                onUpdateClient()
            }
            countdown = 0
        } else countdown++
    }

    //1tickごとにサーバー側とクライアント側で同時に実行するメソッド
    open fun onUpdateTick() {}

    //20tickごとにサーバー側とクライアント側で同時に実行するメソッド
    open fun onUpdate() {}

    //20tickごとにサーバー側で実行するメソッド
    open fun onUpdateServer() {}

    //20tickごとにクライアント側で実行するメソッド
    open fun onUpdateClient() {}

}