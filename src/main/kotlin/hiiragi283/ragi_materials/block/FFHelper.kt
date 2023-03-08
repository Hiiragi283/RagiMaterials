package hiiragi283.ragi_materials.block

import hiiragi283.ragi_materials.init.RagiInit
import hiiragi283.ragi_materials.recipe.forge_furnace.FFRecipe
import hiiragi283.ragi_materials.recipe.forge_furnace.FFRegistry
import hiiragi283.ragi_materials.util.RagiLogger
import hiiragi283.ragi_materials.util.RagiUtil
import net.minecraft.block.BlockHorizontal
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.init.Items
import net.minecraft.item.ItemStack
import net.minecraft.util.SoundCategory
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

//Forge Furnaceの機能をオブジェクト化することで他クラスからも参照できるようにした
object FFHelper {

    //燃料を投入するメソッド
    fun setFuel(world: World, pos: BlockPos, state: IBlockState, stack: ItemStack): ItemStack {
        //stateから状態を取得
        val fuel = state.getValue(BlockForgeFurnace.FUEL)
        //サーバー側，かつブロックに蓄えられた燃料が3未満の場合
        if (!world.isRemote && fuel < 3) {
            val result = state.withProperty(BlockForgeFurnace.FUEL, fuel + 1)
            world.setBlockState(pos, result, 2) //燃料を投入する
            world.updateComparatorOutputLevel(pos, state.block) //コンパレータ出力を更新
            world.playSound(null, pos, RagiUtil.getSound("minecraft:block.gravel.place"), SoundCategory.BLOCKS, 1.0f, 0.5f) //SEを再生
            stack.shrink(1) //手持ちの燃料を1つ減らす
            RagiLogger.infoDebug("Fuel was added!")
        }
        return stack
    }

    //右クリックレシピを司るメソッド
    fun getResult(world: World, pos: BlockPos, state: IBlockState, player: EntityPlayer, stack: ItemStack): Boolean {
        var result = ItemStack.EMPTY
        if (!world.isRemote) {
            //mapRecipe内の各recipeに対して実行
            for (recipe in FFRegistry.list) {
                //stackがinputと等しい，かつブロックとレシピタイプが一致する場合は対応する完成品を，そうでない場合は空のItemStackを返す
                if (RagiUtil.isSameStack(stack, recipe.input, false) && canProcess(state, recipe.type)) {
                    result = recipe.output
                    break
                } else ItemStack.EMPTY
            }
            //resultが空気でない場合
            return if (result.item !== Items.AIR) {
                stack.shrink(1) //stackを1つ減らす
                RagiUtil.spawnItemAtPlayer(world, player, result)
                setState(world, pos, state) //Forge Furnaceの状態を上書き
                RagiLogger.infoDebug("Heating was succeeded!")
                true
            } else false
        } else return false
    }

    //レシピが実行できるかどうか
    private fun canProcess(state: IBlockState, type: FFRecipe.EnumFire): Boolean {
        return when (state.block) {
            is BlockForgeFurnace -> {
                val fuel = state.getValue(BlockForgeFurnace.FUEL)
                //燃料が入っているならtrue
                type == FFRecipe.EnumFire.BURNING && fuel > 0
            }
            is BlockLitForgeFurnace -> type == FFRecipe.EnumFire.BOOSTED
            is BlockBlazeHeater -> {
                if (state.getValue(BlockBlazeHeater.HELL)) type == FFRecipe.EnumFire.HELLRISE
                else type == FFRecipe.EnumFire.BOOSTED
            }
            else -> false
        }
    }

    //レシピ実行後にblockstateを上書きするメソッド
    private fun setState(world: World, pos: BlockPos, state: IBlockState) {
        val facing = state.getValue(BlockHorizontal.FACING)
        when (state.block) {
            is BlockForgeFurnace -> {
                //stateから状態を取得
                val fuel = state.getValue(BlockForgeFurnace.FUEL)
                val result = state
                    .withProperty(BlockHorizontal.FACING, facing)
                    .withProperty(BlockForgeFurnace.FUEL, fuel - 1)
                world.setBlockState(pos, result, 2)
                world.updateComparatorOutputLevel(pos, state.block) //コンパレータ出力を更新
                world.playSound(null, pos, RagiUtil.getSound("minecraft:block.fire.extinguish"), SoundCategory.BLOCKS, 1.0f, 1.0f) //SEを再生
                RagiLogger.infoDebug("The state of Forge Furnace was updated!")
            }

            is BlockLitForgeFurnace -> {
                val forgeFurnace = RagiInit.BlockForgeFurnace.defaultState
                val result = forgeFurnace
                    .withProperty(BlockHorizontal.FACING, facing)
                    .withProperty(BlockForgeFurnace.FUEL, 2)
                world.setBlockState(pos, result, 2)
                world.updateComparatorOutputLevel(pos, result.block) //コンパレータ出力を更新
                world.playSound(null, pos, RagiUtil.getSound("minecraft:block.fire.extinguish"), SoundCategory.BLOCKS, 1.0f, 1.0f) //SEを再生
                RagiLogger.infoDebug("The state of Boosted Forge Furnace was updated!")
            }

            is BlockBlazeHeater -> {
                if (state.getValue(BlockBlazeHeater.HELL)) {
                    world.playSound(null, pos, RagiUtil.getSound("minecraft:entity.endermen.hurt"), SoundCategory.BLOCKS, 1.0f, 0.5f) //SEを再生
                    RagiLogger.infoDebug("The state of Hellrise Heater was updated!")
                } else {
                    world.playSound(null, pos, RagiUtil.getSound("minecraft:block.fire.extinguish"), SoundCategory.BLOCKS, 1.0f, 1.0f) //SEを再生
                    RagiLogger.infoDebug("The state of Blaze Heater was updated!")
                }
            }
        }
    }
}