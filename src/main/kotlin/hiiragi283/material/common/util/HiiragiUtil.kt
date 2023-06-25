@file:JvmName("HiiragiUtil")

package hiiragi283.material.common.util

import hiiragi283.material.common.RagiMaterials
import net.minecraft.client.MinecraftClient
import net.minecraft.server.command.ServerCommandSource
import net.minecraft.sound.SoundCategory
import net.minecraft.sound.SoundEvent
import net.minecraft.sound.SoundEvents
import net.minecraft.util.Identifier
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Vec3d
import net.minecraft.world.World

//    Identifier    //

fun Identifier.append(path: String) = Identifier(this.namespace, this.path + path)

fun Identifier.appendBefore(path: String) = Identifier(this.namespace, path + this.path)

fun commonId(path: String): Identifier = Identifier("c", path)

fun hiiragiId(path: String): Identifier = Identifier(RagiMaterials.MODID, path)

//    Sound    //

fun playSound(world: World, pos: BlockPos, sound: SoundEvent, volume: Float = 1.0f, pitch: Float = 1.0f) {
    world.playSound(null, pos, sound, SoundCategory.MASTER, volume, pitch)
}

fun playSound(world: World, pos: Vec3d, sound: SoundEvent, volume: Float = 1.0f, pitch: Float = 1.0f) {
    world.playSound(null, pos.x, pos.y, pos.z, sound, SoundCategory.MASTER, volume, pitch)
}

fun playHypixel(world: World, pos: BlockPos) {
    world.playSound(null, pos, SoundEvents.ENTITY_PLAYER_LEVELUP, SoundCategory.MASTER, 1.0f, 0.5f)
}

fun playHypixel(world: World, pos: Vec3d) {
    playSound(world, pos, SoundEvents.ENTITY_PLAYER_LEVELUP, 1.0f, 0.5f)
}

//    Misc    //

fun executeCommand(source: ServerCommandSource, command: String) {
    MinecraftClient.getInstance().server?.commandManager?.execute(source, command)
}