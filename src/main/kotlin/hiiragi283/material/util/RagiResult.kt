@file:JvmName("RagiResult")

package hiiragi283.material.util

import net.minecraft.block.Block
import net.minecraft.client.Minecraft
import net.minecraft.client.entity.EntityPlayerSP
import net.minecraft.client.multiplayer.WorldClient
import net.minecraft.command.ICommandSender
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.math.BlockPos
import net.minecraft.util.text.TextComponentTranslation
import net.minecraft.world.IBlockAccess

private val WORLD_CLIENT: WorldClient by lazy { Minecraft.getMinecraft().world }
private val PLAYER_CLIENT: EntityPlayerSP by lazy { Minecraft.getMinecraft().player }

//resultの結果に応じて成功/失敗をチャット欄に出力する
fun printResult(block: Block, player: ICommandSender = PLAYER_CLIENT, result: (Block, ICommandSender) -> Boolean) {
    if (result(block, player)) succeeded(block, player) else failed(block, player)
}

fun printResult(
    world: IBlockAccess = WORLD_CLIENT,
    pos: BlockPos,
    player: ICommandSender = PLAYER_CLIENT,
    result: (IBlockAccess, BlockPos, ICommandSender) -> Boolean
) {
    if (result(world, pos, player)) succeeded(world, pos, player) else failed(world, pos, player)
}

fun printResult(
    tile: TileEntity,
    player: ICommandSender = PLAYER_CLIENT,
    result: (TileEntity, ICommandSender) -> Boolean
) {
    if (result(tile, player)) succeeded(tile, player) else failed(tile, player)
}

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