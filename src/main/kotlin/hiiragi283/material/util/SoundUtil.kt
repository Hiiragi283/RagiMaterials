@file:JvmName("SoundUtil")

package hiiragi283.material.util

import net.minecraft.block.entity.BlockEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.sound.SoundCategory
import net.minecraft.sound.SoundEvent
import net.minecraft.sound.SoundEvents
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

fun playSound(world: World?, pos: BlockPos, soundEvent: SoundEvent, volume: Float = 1.0f, pitch: Float = 1.0f, soundCategory: SoundCategory = SoundCategory.MASTER, player: PlayerEntity? = null) {
    world?.playSound(player, pos, soundEvent, soundCategory, volume, pitch)
}

fun playSound(tile: BlockEntity, soundEvent: SoundEvent, volume: Float = 1.0f, pitch: Float = 1.0f, soundCategory: SoundCategory = SoundCategory.MASTER) {
    playSound(tile.world, tile.pos, soundEvent, volume, pitch, soundCategory)
}

fun playSoundHypixel(world: World?, pos: BlockPos) {
    world?.playSound(null, pos, SoundEvents.ENTITY_PLAYER_LEVELUP, SoundCategory.BLOCKS, 1.0f, 0.5f)
}

fun playSoundHypixel(tile: BlockEntity) {
    playSoundHypixel(tile.world, tile.pos)
}