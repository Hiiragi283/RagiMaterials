package hiiragi283.ragi_materials.util

import net.minecraft.block.Block
import net.minecraft.client.Minecraft
import net.minecraft.client.multiplayer.WorldClient
import net.minecraft.command.ICommandSender
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.math.BlockPos
import net.minecraft.util.text.TextComponentTranslation
import net.minecraft.world.IBlockAccess

object RagiResult {

    private val WORLD_CLIENT: WorldClient = Minecraft.getMinecraft().world
    private val PLAYER_CLIENT = Minecraft.getMinecraft().player

    fun succeeded(block: Block, player: ICommandSender = PLAYER_CLIENT) {
        player.sendMessage(TextComponentTranslation("text.ragi_materials.succeeded", block.localizedName))
    }

    fun succeeded(world: IBlockAccess = WORLD_CLIENT, pos: BlockPos, player: ICommandSender = PLAYER_CLIENT) {
        succeeded(world.getBlockState(pos).block, player)
    }

    fun succeeded(tile: TileEntity, player: ICommandSender = PLAYER_CLIENT) {
        succeeded(tile.world, tile.pos, player)
    }

    fun failed(block: Block, player: ICommandSender = PLAYER_CLIENT) {
        player.sendMessage(TextComponentTranslation("text.ragi_materials.failed", block.localizedName))
    }

    fun failed(world: IBlockAccess = WORLD_CLIENT, pos: BlockPos, player: ICommandSender = PLAYER_CLIENT) {
        failed(world.getBlockState(pos).block, player)
    }

    fun failed(tile: TileEntity, player: ICommandSender = PLAYER_CLIENT) {
        failed(tile.world, tile.pos, player)
    }

}