package hiiragi283.ragi_materials.block

import hiiragi283.ragi_materials.init.RagiInit
import hiiragi283.ragi_materials.util.RagiUtils
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.item.EntityItem
import net.minecraft.init.Items
import net.minecraft.item.ItemStack
import net.minecraft.util.SoundCategory
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

//Forge Furnaceの機能をオブジェクト化することで他クラスからも参照できるようにした
object ForgeFurnaceHelper {

    //レシピマップの登録
    var mapForgeBurning = mutableMapOf(
        "ragi_materials:ingot:3" to "ragi_materials:ingot_hot:3",
        "ragi_materials:ingot:12" to "ragi_materials:ingot_hot:12",
        "ragi_materials:ingot:13" to "ragi_materials:ingot_hot:13",
        "minecraft:iron_ingot:0" to "ragi_materials:ingot_hot:26",
        "ragi_materials:ingot:26" to "ragi_materials:ingot_hot:26",
        "ragi_materials:ingot:29" to "ragi_materials:ingot_hot:29",
        "ragi_materials:ingot:30" to "ragi_materials:ingot_hot:30",
        "ragi_materials:ingot:31" to "ragi_materials:ingot_hot:31",
        "ragi_materials:ingot:47" to "ragi_materials:ingot_hot:47",
        "ragi_materials:ingot:49" to "ragi_materials:ingot_hot:49",
        "ragi_materials:ingot:50" to "ragi_materials:ingot_hot:50",
        "ragi_materials:ingot:51" to "ragi_materials:ingot_hot:51",
        "ragi_materials:ingot:60" to "ragi_materials:ingot_hot:60",
        "ragi_materials:ingot:62" to "ragi_materials:ingot_hot:62",
        "minecraft:gold_ingot:0" to "ragi_materials:ingot_hot:79",
        "ragi_materials:ingot:79" to "ragi_materials:ingot_hot:79",
        "ragi_materials:ingot:82" to "ragi_materials:ingot_hot:82",
        "ragi_materials:ingot:83" to "ragi_materials:ingot_hot:83"
    )

    var mapForgeBoosted = mutableMapOf(
        "ragi_materials:ingot:4" to "ragi_materials:ingot_hot:4",
        "ragi_materials:ingot:14" to "ragi_materials:ingot_hot:14",
        "ragi_materials:ingot:22" to "ragi_materials:ingot_hot:22",
        "ragi_materials:ingot:24" to "ragi_materials:ingot_hot:24",
        "ragi_materials:ingot:25" to "ragi_materials:ingot_hot:25",
        "ragi_materials:ingot:27" to "ragi_materials:ingot_hot:27",
        "ragi_materials:ingot:28" to "ragi_materials:ingot_hot:28",
        "ragi_materials:ingot:40" to "ragi_materials:ingot_hot:40",
        "ragi_materials:ingot:42" to "ragi_materials:ingot_hot:42",
        "ragi_materials:ingot:45" to "ragi_materials:ingot_hot:45",
        "ragi_materials:ingot:46" to "ragi_materials:ingot_hot:46",
        "ragi_materials:ingot:78" to "ragi_materials:ingot_hot:78"
    )

    var mapForgeHellrise = mutableMapOf(
        "ragi_materials:ingot:41" to "ragi_materials:ingot_hot:41",
        "ragi_materials:ingot:44" to "ragi_materials:ingot_hot:44",
        "ragi_materials:ingot:72" to "ragi_materials:ingot_hot:72",
        "ragi_materials:ingot:73" to "ragi_materials:ingot_hot:73",
        "ragi_materials:ingot:74" to "ragi_materials:ingot_hot:74",
        "ragi_materials:ingot:76" to "ragi_materials:ingot_hot:76"
    )

    //燃料を投入するメソッド
    fun setFuel(world: World, pos: BlockPos, state: IBlockState, stack: ItemStack): ItemStack {
        //stateから状態を取得
        val facing = state.getValue(BlockForgeFurnace.propertyFacing)
        val fuel = state.getValue(BlockForgeFurnace.propertyFuel)
        //ブロックに蓄えられた燃料が3未満の場合
        if (fuel < 3) {
            val result = state.withProperty(BlockForgeFurnace.propertyFacing, facing)
                .withProperty(BlockForgeFurnace.propertyFuel, fuel + 1)
            world.setBlockState(pos, result, 2) //燃料を投入する
            world.playSound(
                null, pos, RagiUtils.getSound("minecraft:block.gravel.place"), SoundCategory.BLOCKS, 1.0f, 0.5f
            ) //SEを再生
            stack.shrink(1) //手持ちの燃料を1つ減らす
        }
        return stack
    }

    //ふいごで火力を上げるメソッド
    fun setBoosted(world: World, pos: BlockPos, state: IBlockState, stack: ItemStack): ItemStack {
        //燃料が満タンの場合
        if (state.getValue(BlockForgeFurnace.propertyFuel) == 3) {
            val litForgeFurnace = RagiInit.BlockLitForgeFurnace.defaultState
            val facing = litForgeFurnace.getValue(BlockLitForgeFurnace.propertyFacing)
            world.setBlockState(pos, litForgeFurnace.withProperty(BlockLitForgeFurnace.propertyFacing, facing), 2) //火力UP
            world.playSound(
                null, pos, RagiUtils.getSound("minecraft:entity.blaze.shoot"), SoundCategory.BLOCKS, 1.0f, 0.5f
            ) //SEを再生
            stack.itemDamage += 1//耐久値を1つ減らす
        }
        return stack
    }

    //右クリックレシピを司るメソッド
    fun getResult(
        world: World,
        pos: BlockPos,
        state: IBlockState,
        stack: ItemStack,
        map: MutableMap<String, String>
    ): ItemStack {
        var result = ItemStack.EMPTY
        if (canProcess(state)) {
            //mapRecipe内の各keyに対して実行
            for (key in map.keys) {
                //stackがkeyと等しい場合は対応する完成品を，そうでない場合は空のItemStackを返す
                if (RagiUtils.isSameStack(stack, RagiUtils.getStack(key))) {
                    result = RagiUtils.getStack(map.getValue(key))
                    break
                } else ItemStack.EMPTY
            }
            //resultが空気でない場合
            if (result.item !== Items.AIR) {
                stack.shrink(1) //stackを1つ減らす
                val drop = EntityItem(
                    world, pos.x.toDouble(), pos.y.toDouble() + 1.0, pos.z.toDouble(), result
                ) //ドロップアイテムを生成
                drop.setPickupDelay(0) //ドロップしたら即座に回収できるようにする
                if (!world.isRemote) world.spawnEntity(drop) //ドロップアイテムをスポーン
                setState(world, pos, state) //Forge Furnaceの状態を上書き
                world.playSound(
                    null, pos, RagiUtils.getSound("minecraft:block.fire.extinguish"), SoundCategory.BLOCKS, 1.0f, 1.0f
                ) //SEを再生
            }
        }
        return stack
    }

    //レシピが実行できるかどうか
    private fun canProcess(state: IBlockState): Boolean {
        return when (state.block) {
            is BlockForgeFurnace -> {
                val fuel = state.getValue(BlockForgeFurnace.propertyFuel)
                //燃料が入っているならtrue
                fuel > 0
            }
            is BlockLitForgeFurnace -> true
            is BlockBlazeHeater -> true
            else -> false
        }
    }

    //レシピ実行後にblockstateを上書きするメソッド
    private fun setState(world: World, pos: BlockPos, state: IBlockState) {
        when (state.block) {
            is BlockForgeFurnace -> {
                //stateから状態を取得
                val facing = state.getValue(BlockForgeFurnace.propertyFacing)
                val fuel = state.getValue(BlockForgeFurnace.propertyFuel)
                val result = state.withProperty(BlockForgeFurnace.propertyFacing, facing)
                    .withProperty(BlockForgeFurnace.propertyFuel, fuel - 1)
                world.setBlockState(pos, result, 2)
            }

            is BlockLitForgeFurnace -> {
                val forgeFurnace = RagiInit.BlockForgeFurnace.defaultState
                val facing = state.getValue(BlockForgeFurnace.propertyFacing)
                val result = forgeFurnace.withProperty(BlockForgeFurnace.propertyFacing, facing)
                    .withProperty(BlockForgeFurnace.propertyFuel, 2)
                world.setBlockState(pos, result, 2)
            }

            is BlockBlazeHeater -> {}
        }
    }
}