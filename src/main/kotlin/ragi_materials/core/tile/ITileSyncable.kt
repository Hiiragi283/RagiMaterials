package ragi_materials.core.tile

import net.minecraftforge.fml.common.network.simpleimpl.IMessage

interface ITileSyncable {

    fun sync(message: IMessage)

}