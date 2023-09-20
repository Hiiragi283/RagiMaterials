package hiiragi283.material.network

import net.minecraft.nbt.NBTTagCompound
import net.minecraft.util.math.BlockPos
import net.minecraftforge.fml.common.network.simpleimpl.IMessage

interface IHiiragiMessage : IMessage{

    var pos: BlockPos
    var tag: NBTTagCompound

}