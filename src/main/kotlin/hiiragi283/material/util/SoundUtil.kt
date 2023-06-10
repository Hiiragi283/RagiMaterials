@file:JvmName("SoundUtil")

package hiiragi283.material.util

import net.minecraft.entity.player.EntityPlayer
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.SoundCategory
import net.minecraft.util.SoundEvent
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

fun playSound(
    world: World,
    pos: BlockPos,
    soundEvent: SoundEvent,
    volume: Float = 1.0f,
    pitch: Float = 1.0f,
    soundCategory: SoundCategory = SoundCategory.MASTER,
    player: EntityPlayer? = null
) {
    world.playSound(player, pos, soundEvent, soundCategory, volume, pitch)
}

fun playSound(
    tile: TileEntity,
    soundEvent: SoundEvent,
    volume: Float = 1.0f,
    pitch: Float = 1.0f,
    soundCategory: SoundCategory = SoundCategory.MASTER
) {
    playSound(tile.world, tile.pos, soundEvent, volume, pitch, soundCategory)
}

fun playSoundHypixel(world: World, pos: BlockPos) {
    world.playSound(
        null,
        pos,
        getSound("minecraft:entity.player.levelup"),
        SoundCategory.BLOCKS,
        1.0f,
        0.5f
    )
}

fun playSoundHypixel(tile: TileEntity) {
    playSoundHypixel(tile.world, tile.pos)
}