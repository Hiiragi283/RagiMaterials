package hiiragi283.ragi_materials.util

import hiiragi283.ragi_materials.Reference
import net.minecraft.block.Block
import net.minecraft.command.ICommandSender
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.math.BlockPos
import net.minecraft.util.text.TextComponentTranslation
import net.minecraft.world.IBlockAccess

object RagiResult {

    fun succeeded(block: Block, player: ICommandSender = Reference.PLAYER_CLIENT) {
        player.sendMessage(TextComponentTranslation("text.ragi_materials.succeeded", block.localizedName))
    }

    fun succeeded(world: IBlockAccess = Reference.WORLD_CLIENT, pos: BlockPos, player: ICommandSender = Reference.PLAYER_CLIENT) {
        succeeded(world.getBlockState(pos).block, player)
    }

    fun succeeded(tile: TileEntity, player: ICommandSender = Reference.PLAYER_CLIENT) {
        succeeded(tile.world, tile.pos, player)
    }

    fun failed(block: Block, player: ICommandSender = Reference.PLAYER_CLIENT) {
        player.sendMessage(TextComponentTranslation("text.ragi_materials.failed", block.localizedName))
    }

    fun failed(world: IBlockAccess = Reference.WORLD_CLIENT, pos: BlockPos, player: ICommandSender = Reference.PLAYER_CLIENT) {
        failed(world.getBlockState(pos).block, player)
    }
    fun failed(tile: TileEntity, player: ICommandSender = Reference.PLAYER_CLIENT) {
        failed(tile.world, tile.pos, player)
    }

}