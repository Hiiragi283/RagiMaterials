package ragi_materials.metallurgy.tile

import net.minecraft.entity.player.EntityPlayer
import net.minecraft.init.SoundEvents
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumHand
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import ragi_materials.core.RagiRegistry
import ragi_materials.core.material.RagiMaterial
import ragi_materials.core.material.part.PartRegistry
import ragi_materials.core.tile.TileBase
import ragi_materials.core.util.*

class TileBloom : TileBase() {

    private var chance = 8

    //    NBT tag    //

    override fun writeToNBT(tag: NBTTagCompound) = tag.also {
        super.writeToNBT(it)
        material.writeToNBT(it)
    }

    override fun readFromNBT(tag: NBTTagCompound) {
        super.readFromNBT(tag)
        material = RagiMaterial.readFromNBT(tag)
    }

    override fun onTileActivated(world: World, pos: BlockPos, player: EntityPlayer, hand: EnumHand, facing: EnumFacing): Boolean {
        return if (!world.isRemote) {
            val stack = player.getHeldItem(hand)
            val item = stack.item
            if (item == RagiRegistry.ItemForgeHammer) {
                val rand = world.rand
                if (rand.nextInt(chance) != 0) {
                    dropItem(world, pos.add(0.0, 0.6, 0.0), getPart(PartRegistry.INGOT, material), rand.nextDouble() * 0.25, rand.nextDouble() * 0.25, rand.nextDouble() * 0.25)
                    playSound(this, SoundEvents.BLOCK_ANVIL_LAND, 0.5f)
                    chance-- //確率を下げる
                } else {
                    dropItem(world, pos, getPart(PartRegistry.INGOT, material), rand.nextDouble() * 0.25, rand.nextDouble() * 0.25, rand.nextDouble() * 0.25)
                    world.destroyBlock(pos, false)
                }
                item.setDamage(stack, item.getDamage(stack) + 1)
                succeeded(this, player)
                true
            } else {
                failed(this, player)
                false
            }
        } else false
    }
}