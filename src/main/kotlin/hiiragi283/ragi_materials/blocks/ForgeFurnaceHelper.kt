package hiiragi283.ragi_materials.blocks

import hiiragi283.ragi_materials.util.RagiUtils
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.item.EntityItem
import net.minecraft.item.ItemStack
import net.minecraft.util.SoundCategory
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

//Forge Furnaceの機能をオブジェクト化することで他クラスからも参照できるようにした
object ForgeFurnaceHelper {

    val mapRecipe = mutableMapOf(
        RagiUtils.getStack("minecraft:cobblestone", 1, 0) to RagiUtils.getStack("minecraft:magma", 1, 0)
    )

    //燃料を投入するメソッド
    fun setFuel(world: World, pos: BlockPos, state: IBlockState, stack: ItemStack): ItemStack {
        //ブロックに蓄えられた燃料の量を取得
        val fuel = state.getValue(BlockForgeFurnace.propertyFuel)
        //ブロックに蓄えられた燃料が3未満の場合
        if (fuel < 3) {
            world.setBlockState(
                pos,
                state.withProperty(BlockForgeFurnace.propertyFire, state.getValue(BlockForgeFurnace.propertyFire))
                    .withProperty(BlockForgeFurnace.propertyFuel, fuel + 1)
            ) //燃料を投入する
            world.playSound(
                null, pos, RagiUtils.getSound("minecraft:block.gravel.place"), SoundCategory.BLOCKS, 1.0f, 0.5f
            ) //SEを再生
            stack.shrink(1) //手持ちの燃料を1つ減らす
        }
        return stack
    }

    //着火するメソッド
    fun setFireItem(world: World, pos: BlockPos, state: IBlockState, stack: ItemStack): ItemStack {
        //消火状態，かつ燃料が1つ以上の場合
        if (state.getValue(BlockForgeFurnace.propertyFire) == BlockForgeFurnace.PropertyState.EXTINGUISH && state.getValue(
                BlockForgeFurnace.propertyFuel
            ) > 0
        ) {
            world.setBlockState(
                pos,
                state.withProperty(BlockForgeFurnace.propertyFire, BlockForgeFurnace.PropertyState.BURNING)
                    .withProperty(BlockForgeFurnace.propertyFuel, state.getValue(BlockForgeFurnace.propertyFuel))
            ) //着火
            world.playSound(
                null, pos, RagiUtils.getSound("minecraft:item.firecharge.use"), SoundCategory.BLOCKS, 1.0f, 1.0f
            ) //SEを再生
            stack.shrink(1) //手持ちの火種を1つ減らす
        }
        return stack
    }

    fun setFireTool(world: World, pos: BlockPos, state: IBlockState, stack: ItemStack): ItemStack {
        //消火状態，かつ燃料が1つ以上の場合
        if (state.getValue(BlockForgeFurnace.propertyFire) == BlockForgeFurnace.PropertyState.EXTINGUISH && state.getValue(
                BlockForgeFurnace.propertyFuel
            ) > 0
        ) {
            world.setBlockState(
                pos,
                state.withProperty(BlockForgeFurnace.propertyFire, BlockForgeFurnace.PropertyState.BURNING)
                    .withProperty(BlockForgeFurnace.propertyFuel, state.getValue(BlockForgeFurnace.propertyFuel))
            ) //着火
            world.playSound(
                null, pos, RagiUtils.getSound("minecraft:item.flintandsteel.use"), SoundCategory.BLOCKS, 1.0f, 1.0f
            ) //SEを再生
            stack.itemDamage += 1//耐久値を1つ減らす
        }
        return stack
    }

    //ふいごで火力を上げるメソッド
    fun setBlasting(world: World, pos: BlockPos, state: IBlockState, stack: ItemStack): ItemStack {
        //着火済み，かつ燃料が満タンの場合
        if (state.getValue(BlockForgeFurnace.propertyFire) == BlockForgeFurnace.PropertyState.BURNING && state.getValue(
                BlockForgeFurnace.propertyFuel
            ) == 3
        ) {
            world.setBlockState(
                pos,
                state.withProperty(BlockForgeFurnace.propertyFire, BlockForgeFurnace.PropertyState.BLASTING)
                    .withProperty(BlockForgeFurnace.propertyFuel, 3)
            ) //火力UP
            world.playSound(
                null, pos, RagiUtils.getSound("minecraft:entity.blaze.shoot"), SoundCategory.BLOCKS, 1.0f, 0.5f
            ) //SEを再生
            stack.itemDamage += 1//耐久値を1つ減らす
        }
        return stack
    }

    //右クリックレシピを司るメソッド
    fun getResult(world: World, pos: BlockPos, state: IBlockState, stack: ItemStack): ItemStack {
        var result = ItemStack.EMPTY
        if (canProcess(state)) {
            //mapRecipe内の各keyに対して実行
            for (key in mapRecipe.keys) {
                //stackがkeyと等しい場合は対応する完成品を，そうでない場合は空のItemStackを返す
                result = if (RagiUtils.isSameStack(key, stack)) mapRecipe.getValue(key) else ItemStack.EMPTY
            }
            //resultが空のItemStackでない場合
            if (!RagiUtils.isSameStack(result, ItemStack.EMPTY)) {
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
        val fire = state.getValue(BlockForgeFurnace.propertyFire)
        val fuel = state.getValue(BlockForgeFurnace.propertyFuel)
        //消化状態ではない，かつ燃料が入っているならtrue
        return fire !== BlockForgeFurnace.PropertyState.EXTINGUISH && fuel > 0
    }

    //レシピ実行後にblockstateを上書きするメソッド
    private fun setState(world: World, pos: BlockPos, state: IBlockState) {
        val stateFire = state.getValue(BlockForgeFurnace.propertyFire)
        val stateFuel = state.getValue(BlockForgeFurnace.propertyFuel)
        val stateReturn = when (stateFire) {
            //EXTINGUISH -> デフォルトのblockstateを返す
            BlockForgeFurnace.PropertyState.EXTINGUISH -> state.block.defaultState
            //BURNING ->
            BlockForgeFurnace.PropertyState.BURNING ->
                //燃料が1つの場合，デフォルトのblockstateを返す
                //2つ以上なら燃料を1つ減らす
                if (stateFuel == 1) state.block.defaultState else {
                    state.withProperty(BlockForgeFurnace.propertyFuel, stateFuel - 1)
                }
            //BLASTING -> 燃料を1つ減らし，BURNINGにする
            BlockForgeFurnace.PropertyState.BLASTING -> state.withProperty(
                BlockForgeFurnace.propertyFire,
                BlockForgeFurnace.PropertyState.BURNING
            ).withProperty(BlockForgeFurnace.propertyFuel, stateFuel - 1)
        }
        world.setBlockState(pos, stateReturn)
    }
}