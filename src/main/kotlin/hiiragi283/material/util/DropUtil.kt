@file:JvmName("DropUtil")

package hiiragi283.material.util

import net.minecraft.entity.ItemEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import net.minecraft.util.math.Position
import net.minecraft.world.World

fun dropItemAtPlayer(player: PlayerEntity, stack: ItemStack, x: Double = 0.0, y: Double = 0.0, z: Double = 0.0) {
    val world = player.world
    if (!world.isClient) {
        val posPlayer = player.pos
        dropItem(world, posPlayer, stack, x, y, z)
    }
}

fun dropItem(world: World, pos: Position, stack: ItemStack, x: Double = 0.0, y: Double = 0.0, z: Double = 0.0) {
    if (!world.isClient && !stack.isEmpty) {
        val drop = ItemEntity(world, pos.x + 0.5f, pos.y, pos.z + 0.5f, stack)
        drop.setPickupDelay(0) //即座に回収できるようにする
        world.spawnEntity(drop) //ドロップアイテムをスポーン
    }
}