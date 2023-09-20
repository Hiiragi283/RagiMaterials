package hiiragi283.material.network

import hiiragi283.material.RMReference
import hiiragi283.material.tile.TileEntityModuleMachine
import hiiragi283.material.util.getTile
import net.minecraft.client.Minecraft
import net.minecraft.tileentity.TileEntity
import net.minecraft.world.World
import net.minecraftforge.fml.common.network.NetworkRegistry
import net.minecraftforge.fml.common.network.simpleimpl.IMessage
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper
import net.minecraftforge.fml.relauncher.Side

val HiiragiNetworkWrapper: SimpleNetworkWrapper = NetworkRegistry.INSTANCE.newSimpleChannel(RMReference.MOD_ID)

object HiiragiNetworkManager {

    fun register() {
        HiiragiNetworkWrapper.registerMessage(
            { message: HiiragiMessage.Client, _: MessageContext ->
                syncMessage(Minecraft.getMinecraft().world, message)
            },
            HiiragiMessage.Client::class.java,
            0,
            Side.CLIENT
        )
        HiiragiNetworkWrapper.registerMessage(
            { message: HiiragiMessage.Server, ctx: MessageContext ->
                syncMessage(ctx.serverHandler.player.world, message)
            },
            HiiragiMessage.Server::class.java,
            1,
            Side.SERVER
        )
        HiiragiNetworkWrapper.registerMessage(
            { message: HiiragiMessage.ModuleMachine, ctx: MessageContext ->
                syncMessage(
                    ctx.serverHandler.player.world,
                    message
                ) { tile: TileEntity -> (tile as? TileEntityModuleMachine)?.processRecipe() }
            },
            HiiragiMessage.ModuleMachine::class.java,
            2,
            Side.SERVER
        )
        HiiragiNetworkWrapper.registerMessage(
            { _: HiiragiMessage.Player, ctx: MessageContext ->
                val player = ctx.serverHandler.player
                player.foodStats.foodLevel -= 1
                return@registerMessage null
            },
            HiiragiMessage.Player::class.java,
            3,
            Side.SERVER
        )
    }

    private fun syncMessage(
        world: World,
        message: IHiiragiMessage,
        handler: (TileEntity) -> Unit = { tile -> tile.readFromNBT(message.tag) }
    ): IMessage? {
        getTile<TileEntity>(world, message.pos)?.let(handler)
        return null
    }

}