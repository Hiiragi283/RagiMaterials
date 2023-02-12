package hiiragi283.ragi_materials.block

import hiiragi283.ragi_materials.init.RagiInit
import hiiragi283.ragi_materials.util.RagiLogger
import hiiragi283.ragi_materials.util.RagiUtils
import net.minecraft.block.BlockHorizontal
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
        "ragi_materials:ingot:83" to "ragi_materials:ingot_hot:83",
        "ragi_materials:ingot:201" to "ragi_materials:ingot_hot:201",
        "ragi_materials:ingot:211" to "ragi_materials:ingot_hot:211",
        "ragi_materials:ingot:212" to "ragi_materials:ingot_hot:212"
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
        "ragi_materials:ingot:45" to "ragi_materials:ingot_hot:45",
        "ragi_materials:ingot:46" to "ragi_materials:ingot_hot:46",
        "ragi_materials:ingot:78" to "ragi_materials:ingot_hot:78",
        "ragi_materials:ingot:200" to "ragi_materials:ingot_hot:200",
        "ragi_materials:ingot:202" to "ragi_materials:ingot_hot:202",
        "ragi_materials:ingot:203" to "ragi_materials:ingot_hot:203",
        "ragi_materials:ingot:204" to "ragi_materials:ingot_hot:204",
        "ragi_materials:ingot:205" to "ragi_materials:ingot_hot:205",
        "ragi_materials:ingot:206" to "ragi_materials:ingot_hot:206",
        "ragi_materials:ingot:207" to "ragi_materials:ingot_hot:207",
        "ragi_materials:ingot:208" to "ragi_materials:ingot_hot:208",
        "ragi_materials:ingot:209" to "ragi_materials:ingot_hot:209",
        "ragi_materials:ingot:210" to "ragi_materials:ingot_hot:210",
        "ragi_materials:ingot:213" to "ragi_materials:ingot_hot:213",
        "ragi_materials:ingot:214" to "ragi_materials:ingot_hot:214",
        "ragi_materials:ingot:216" to "ragi_materials:ingot_hot:216"
    )

    var mapForgeHellrise = mutableMapOf(
        "ragi_materials:ingot:41" to "ragi_materials:ingot_hot:41",
        "ragi_materials:ingot:42" to "ragi_materials:ingot_hot:42",
        "ragi_materials:ingot:44" to "ragi_materials:ingot_hot:44",
        "ragi_materials:ingot:72" to "ragi_materials:ingot_hot:72",
        "ragi_materials:ingot:73" to "ragi_materials:ingot_hot:73",
        "ragi_materials:ingot:74" to "ragi_materials:ingot_hot:74",
        "ragi_materials:ingot:76" to "ragi_materials:ingot_hot:76",
        "ragi_materials:ingot:77" to "ragi_materials:ingot_hot:77",
        "ragi_materials:ingot:215" to "ragi_materials:ingot_hot:215"
    )

    //燃料を投入するメソッド
    fun setFuel(world: World, pos: BlockPos, state: IBlockState, stack: ItemStack): ItemStack {
        //stateから状態を取得
        val fuel = state.getValue(BlockForgeFurnace.FUEL)
        //サーバー側，かつブロックに蓄えられた燃料が3未満の場合
        if (!world.isRemote && fuel < 3) {
            val result = state.withProperty(BlockForgeFurnace.FUEL, fuel + 1)
            world.setBlockState(pos, result, 2) //燃料を投入する
            world.playSound(
                null, pos, RagiUtils.getSound("minecraft:block.gravel.place"), SoundCategory.BLOCKS, 1.0f, 0.5f
            ) //SEを再生
            stack.shrink(1) //手持ちの燃料を1つ減らす
            RagiLogger.infoDebug("Fuel was added!")
        }
        return stack
    }

    //ふいごで火力を上げるメソッド
    fun setBoosted(world: World, pos: BlockPos, state: IBlockState, stack: ItemStack): ItemStack {
        //サーバー側，かつ燃料が満タンの場合
        if (!world.isRemote && state.getValue(BlockForgeFurnace.FUEL) == 3) {
            val litForgeFurnace = RagiInit.BlockLitForgeFurnace.defaultState
            val facing = state.getValue(BlockHorizontal.FACING)
            world.setBlockState(pos, litForgeFurnace.withProperty(BlockHorizontal.FACING, facing), 2) //火力UP
            world.playSound(
                null, pos, RagiUtils.getSound("minecraft:entity.blaze.shoot"), SoundCategory.BLOCKS, 1.0f, 0.5f
            ) //SEを再生
            stack.itemDamage += 1//耐久値を1つ減らす
            RagiLogger.infoDebug("Forge Furnace was boosted!")
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
            }
            RagiLogger.infoDebug("Heating was succeeded!")
        }
        return stack
    }

    //レシピが実行できるかどうか
    private fun canProcess(state: IBlockState): Boolean {
        return when (state.block) {
            is BlockForgeFurnace -> {
                val fuel = state.getValue(BlockForgeFurnace.FUEL)
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
                val fuel = state.getValue(BlockForgeFurnace.FUEL)
                val result = state.withProperty(BlockForgeFurnace.FUEL, fuel - 1)
                world.setBlockState(pos, result, 2)
                world.playSound(
                    null, pos, RagiUtils.getSound("minecraft:block.fire.extinguish"), SoundCategory.BLOCKS, 1.0f, 1.0f
                ) //SEを再生
                RagiLogger.infoDebug("The state of Forge Furnace was updated!")
            }

            is BlockLitForgeFurnace -> {
                val forgeFurnace = RagiInit.BlockForgeFurnace.defaultState
                val result = forgeFurnace.withProperty(BlockForgeFurnace.FUEL, 2)
                world.setBlockState(pos, result, 2)
                world.playSound(
                    null, pos, RagiUtils.getSound("minecraft:block.fire.extinguish"), SoundCategory.BLOCKS, 1.0f, 1.0f
                ) //SEを再生
                RagiLogger.infoDebug("The state of Boosted Forge Furnace was updated!")
            }

            is BlockBlazeHeater -> {
                if (state.getValue(BlockBlazeHeater.HELL)) {
                    world.playSound(
                        null, pos, RagiUtils.getSound("minecraft:entity.endermen.hurt"), SoundCategory.BLOCKS, 1.0f, 0.5f
                    ) //SEを再生
                    RagiLogger.infoDebug("The state of Hellrise Heater was updated!")
                } else {
                    world.playSound(
                        null, pos, RagiUtils.getSound("minecraft:block.fire.extinguish"), SoundCategory.BLOCKS, 1.0f, 1.0f
                    ) //SEを再生
                    RagiLogger.infoDebug("The state of Blaze Heater was updated!")
                }
            }
        }
    }
}