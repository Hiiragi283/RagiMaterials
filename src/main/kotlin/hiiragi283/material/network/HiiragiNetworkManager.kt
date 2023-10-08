package hiiragi283.material.network

import hiiragi283.material.RMReference
import hiiragi283.material.entity.EntityMinecartTank
import hiiragi283.material.tile.TileEntityModuleMachine
import hiiragi283.material.util.getTile
import net.minecraft.client.Minecraft
import net.minecraft.entity.Entity
import net.minecraft.tileentity.TileEntity
import net.minecraft.world.World
import net.minecraftforge.fluids.FluidStack
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
                syncTile(Minecraft.getMinecraft().world, message)
            },
            HiiragiMessage.Client::class.java,
            0,
            Side.CLIENT
        )
        HiiragiNetworkWrapper.registerMessage(
            { message: HiiragiMessage.Server, ctx: MessageContext ->
                syncTile(ctx.serverHandler.player.world, message)
            },
            HiiragiMessage.Server::class.java,
            1,
            Side.SERVER
        )
        HiiragiNetworkWrapper.registerMessage(
            { message: HiiragiMessage.Primitive, ctx: MessageContext ->
                syncTile(
                    ctx.serverHandler.player.world,
                    message
                ) { tile: TileEntity ->
                    (tile as? TileEntityModuleMachine)?.processRecipe()
                    ctx.serverHandler.player.foodStats.foodLevel -= 1
                }
            },
            HiiragiMessage.Primitive::class.java,
            2,
            Side.SERVER
        )
        HiiragiNetworkWrapper.registerMessage(
            { message: HiiragiMessage.SyncCount, ctx: MessageContext ->
                syncTile(
                    Minecraft.getMinecraft().world,
                    message
                ) { tile: TileEntity -> (tile as? TileEntityModuleMachine)?.currentCount = message.count }
            },
            HiiragiMessage.SyncCount::class.java,
            3,
            Side.CLIENT
        )
        HiiragiNetworkWrapper.registerMessage(
            { message: HiiragiMessage.MinecartTank, _: MessageContext ->
                syncEntity<EntityMinecartTank>(Minecraft.getMinecraft().world, message) { minecart ->
                    minecart.tank.fluid = FluidStack.loadFluidStackFromNBT(message.tag)
                }
                return@registerMessage null
            },
            HiiragiMessage.MinecartTank::class.java,
            4,
            Side.CLIENT
        )
    }

    @Suppress("UNCHECKED_CAST")
    private fun <T : Entity> syncEntity(
        world: World,
        message: IHiiragiMessage.Entity,
        handler: (T) -> Unit
    ): IMessage? {
        world.getEntityByID(message.entityId)?.let { it as? T }?.let(handler)
        return null
    }

    private fun syncTile(
        world: World,
        message: IHiiragiMessage,
        handler: (TileEntity) -> Unit = { tile -> tile.readFromNBT(message.tag) }
    ): IMessage? {
        getTile<TileEntity>(world, message.pos)?.let(handler)
        return null
    }

}