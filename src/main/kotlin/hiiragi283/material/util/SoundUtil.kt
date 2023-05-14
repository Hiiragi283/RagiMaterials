@file:JvmName("SoundUtil")

package hiiragi283.material.util

import net.minecraft.core.BlockPos
import net.minecraft.sounds.SoundEvent
import net.minecraft.sounds.SoundEvents
import net.minecraft.sounds.SoundSource
import net.minecraft.world.entity.player.Player
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.entity.BlockEntity

fun playSound(
    world: Level?,
    pos: BlockPos,
    soundEvent: SoundEvent,
    volume: Float = 1.0f,
    pitch: Float = 1.0f,
    soundCategory: SoundSource = SoundSource.MASTER,
    player: Player? = null
) {
    world?.playSound(player, pos, soundEvent, soundCategory, volume, pitch)
}

fun playSound(
    tile: BlockEntity,
    soundEvent: SoundEvent,
    volume: Float = 1.0f,
    pitch: Float = 1.0f,
    soundCategory: SoundSource = SoundSource.MASTER
) {
    playSound(tile.level, tile.blockPos, soundEvent, volume, pitch, soundCategory)
}

fun playSoundHypixel(world: Level?, pos: BlockPos) {
    world?.playSound(null, pos, SoundEvents.PLAYER_LEVELUP, SoundSource.MASTER, 1.0f, 0.5f)
}

fun playSoundHypixel(tile: BlockEntity) {
    playSoundHypixel(tile.level, tile.blockPos)
}