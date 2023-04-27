package ragi_materials.metallurgy.tile

import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemFlintAndSteel
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumHand
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import ragi_materials.core.RagiRegistry
import ragi_materials.core.item.ItemMaterialOre
import ragi_materials.core.material.EnumSubMaterial
import ragi_materials.core.material.RagiMaterial
import ragi_materials.core.tile.TileBase
import ragi_materials.metallurgy.block.BlockBloomery

class TileBloomery : TileBase() {

    //    NBT tag    //

    override fun writeToNBT(tag: NBTTagCompound) = tag.also {
        super.writeToNBT(it)
        material.writeToNBT(it)
    }

    override fun readFromNBT(tag: NBTTagCompound) {
        super.readFromNBT(tag)
        material = RagiMaterial.readFromNBT(tag)
    }

    //    TileBase    //

    override fun onTileActivated(world: World, pos: BlockPos, player: EntityPlayer, hand: EnumHand, facing: EnumFacing): Boolean {
        val stack = player.getHeldItem(hand)
        return when (val item = stack.item) {
            //鉱石の場合
            RagiRegistry.ItemOre -> {
                var result = false
                val metal = (item as ItemMaterialOre).getMaterial(stack).mapSubMaterials[EnumSubMaterial.NATIVE]
                //製錬後の素材が取得できる場合，素材を代入する
                if (metal !== null && !metal.isEmpty()) {
                    material = metal
                    stack.shrink(1)
                    result = true
                }
                result
            }
            //火打石の場合
            is ItemFlintAndSteel -> {
                val state = getState()
                val block = state.block
                if (block is BlockBloomery && !material.isEmpty()) {
                    world.setBlockState(pos, block.setActive(state, true), 2)
                    world.scheduleUpdate(pos, block, 10 * 20) //10秒間精錬する
                    stack.itemDamage += 1 //耐久地を1つ減らす
                }
                true
            }

            else -> false
        }
    }
}