package hiiragi283.ragi_materials.util

import net.minecraft.entity.player.EntityPlayer
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.ResourceLocation
import net.minecraft.util.SoundCategory
import net.minecraft.util.SoundEvent
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.fml.common.registry.ForgeRegistries

object RagiSoundEvent {

    fun getSound(location: ResourceLocation): SoundEvent {
        return ForgeRegistries.SOUND_EVENTS.getValue(location)
                ?: ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation("ambient.cave"))!!
    }

    fun getSound(registryName: String): SoundEvent {
        return getSound(ResourceLocation(registryName))
    }

    fun playSound(world: World, pos: BlockPos, soundEvent: SoundEvent, volume: Float = 1.0f, pitch: Float = 1.0f, soundCategory: SoundCategory = SoundCategory.MASTER, player: EntityPlayer? = null) {
        world.playSound(player, pos, soundEvent, soundCategory, volume, pitch)
    }

    fun playSound(tile: TileEntity, soundEvent: SoundEvent, volume: Float = 1.0f, pitch: Float = 1.0f, soundCategory: SoundCategory = SoundCategory.MASTER) {
        playSound(tile.world, tile.pos, soundEvent, volume, pitch, soundCategory)
    }

    fun playSoundHypixel(world: World, pos: BlockPos) {
        world.playSound(null, pos, getSound("minecraft:entity.player.levelup"), SoundCategory.BLOCKS, 1.0f, 0.5f)
    }

    fun playSoundHypixel(tile: TileEntity) {
        playSoundHypixel(tile.world, tile.pos)
    }
}