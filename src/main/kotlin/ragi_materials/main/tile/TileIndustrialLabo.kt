package ragi_materials.main.tile

import net.minecraft.block.state.IBlockState
import net.minecraft.entity.EntityLivingBase
import net.minecraft.init.SoundEvents
import net.minecraft.item.ItemStack
import net.minecraft.util.EnumFacing
import net.minecraft.util.ITickable
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.energy.CapabilityEnergy
import net.minecraftforge.energy.IEnergyStorage
import ragi_materials.core.RagiMaterials
import ragi_materials.core.RagiRegistry
import ragi_materials.core.block.property.RagiProperty
import ragi_materials.core.capability.RagiCapabilityProvider
import ragi_materials.core.capability.energy.RagiEnergyStorage
import ragi_materials.core.recipe.LaboRecipe
import ragi_materials.core.tile.ITileProvider
import ragi_materials.core.util.dropItem
import ragi_materials.core.util.playSound
import ragi_materials.core.util.toBracket

class TileIndustrialLabo : TileLaboBase(), ITickable, ITileProvider.Energy {

    private var count = 0
    var front = EnumFacing.NORTH

    private var cache: LaboRecipe? = null

    //    Capability    //

    override fun createBattery(): RagiCapabilityProvider<IEnergyStorage> {
        energy = RagiEnergyStorage(64000)
        return RagiCapabilityProvider(CapabilityEnergy.ENERGY, energy, energy)
    }

    //    TileBase    //

    override fun onTilePlaced(world: World, pos: BlockPos, state: IBlockState, placer: EntityLivingBase, stack: ItemStack) {
        front = state.getValue(RagiProperty.HORIZONTAL) //タイルエンティティに向きを保存させる
    }

    //    TileItemHandlerBase    //

    override fun getGuiID() = "${RagiMaterials.MOD_ID}:industrial_labo"

    //    ITickable    //

    override fun update() {
        if (!world.isRemote) {
            //countが20以上の場合
            if (count >= 20) {
                //インベントリが空でない場合
                if (!inventory.isEmpty() && energy.energyStored >= 1000) {
                    //レシピチェック
                    if (cacheRecipe()) {
                        RagiMaterials.LOGGER.debug("The recipe cached is ${cache!!.registryName}")
                        for (i in 0..4) {
                            val input = cache!!.getInput(i)
                            val output = cache!!.getOutput(i)
                            if (!this.input.getStackInSlot(i).isEmpty && !input.isEmpty) {
                                this.input.extractItem(i, input.count, false) //入力スロットからアイテムを減らす
                                RagiMaterials.LOGGER.debug("The slot$i is decreased!")
                            }
                            dropItem(world, pos.offset(front), output)
                            RagiMaterials.LOGGER.debug("The output is ${output.toBracket()}")
                        }
                        energy.extractEnergy(1000, false)
                        playSound(this, SoundEvents.BLOCK_PISTON_EXTEND)
                    }
                }
                count = 0 //countをリセット
            } else count++ //countを追加
        }
    }

    private fun cacheRecipe(): Boolean {
        var result = false
        //cacheが空の場合，新規で検索する
        if (cache == null) {
            for (recipe in RagiRegistry.LABO_RECIPE.valuesCollection) {
                if (recipe.match(inventory)) {
                    cache = recipe
                    result = true
                    break
                }
            }
        }
        //cacheがある場合，それが現在のレシピに適応できないなら空にする
        else if (cache!!.match(inventory)) result = true else cache = null
        return result
    }
}