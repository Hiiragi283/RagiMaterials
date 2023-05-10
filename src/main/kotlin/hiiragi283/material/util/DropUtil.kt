@file:JvmName("DropUtil")

package hiiragi283.material.util

import hiiragi283.material.RagiMaterials
import net.minecraft.entity.item.EntityItem
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.items.IItemHandler

fun dropItemAtPlayer(player: EntityPlayer, stack: ItemStack, x: Double = 0.0, y: Double = 0.0, z: Double = 0.0) {
    val world = player.world
    if (!world.isRemote) {
        val posPlayer = player.position
        dropItem(world, posPlayer, stack, x, y, z)
    }
}

fun dropInventoryItems(
    world: World,
    pos: BlockPos,
    inventory: IItemHandler,
    x: Double = 0.0,
    y: Double = 0.0,
    z: Double = 0.0
) {
    for (i in 0 until inventory.slots) {
        val stack = inventory.getStackInSlot(i)
        dropItem(world, pos, stack, x, y, z)
    }
}

fun dropItemFromTile(
    world: World,
    pos: BlockPos,
    stack: ItemStack,
    tile: TileEntity,
    x: Double = 0.0,
    y: Double = 0.0,
    z: Double = 0.0
) {
    RagiMaterials.LOGGER.debug("The stack is ${stack.toBracket()}")
    stack.getOrCreateSubCompound("BlockEntityTag").merge(tile.updateTag)
    dropItem(world, pos, stack, x, y, z)
}

fun dropItem(world: World, pos: BlockPos, stack: ItemStack, x: Double = 0.0, y: Double = 0.0, z: Double = 0.0) {
    if (!world.isRemote && !stack.isEmpty) {
        val drop = EntityItem(world, pos.x.toDouble() + 0.5f, pos.y.toDouble(), pos.z.toDouble() + 0.5f, stack)
        drop.setPickupDelay(0) //即座に回収できるようにする
        drop.motionX = x
        drop.motionY = y
        drop.motionZ = z
        world.spawnEntity(drop) //ドロップアイテムをスポーン
    }
}