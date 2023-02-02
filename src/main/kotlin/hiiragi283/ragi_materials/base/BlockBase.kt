package hiiragi283.ragi_materials.base

import net.minecraft.block.Block
import net.minecraft.block.material.Material
import net.minecraft.block.properties.PropertyInteger
import net.minecraft.block.state.BlockStateContainer
import net.minecraft.block.state.IBlockState
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.Item
import net.minecraftforge.fml.common.registry.ForgeRegistries
import java.util.*

/*
  Thanks to defeatedcrow!
  Source: https://github.com/defeatedcrow/HeatAndClimateLib/blob/1.12.2_v3/main/java/defeatedcrow/hac/core/base/BlockDC.java
          https://github.com/defeatedcrow/HeatAndClimateLib/blob/1.12.2_v3/main/java/defeatedcrow/hac/core/base/DCSimpleBlock.java
*/

open class BlockBase(Material: Material?, MOD: String, ID: String?, maxMeta: Int) : Block(Material!!) {

    //private変数の宣言
    companion object {
        private val property16 = PropertyInteger.create("property", 0, 15)
    }

    private val maxMeta: Int

    //コンストラクタの初期化
    init {
        this.maxMeta = maxMeta //メタデータの最大値の初期化
        defaultState = blockState.baseState.withProperty(property16, 0) //デフォルトのBlockstateをpropertyの0番に設定
        setCreativeTab(CreativeTabs.DECORATIONS) //表示するクリエイティブタブの設定
        unlocalizedName = ID.toString() //翻訳キーをIDから取得

        ForgeRegistries.BLOCKS.register(this.setRegistryName(MOD, ID))

    }

    //Blockstateの登録をするメソッド
    override fun createBlockState(): BlockStateContainer {
        //property16を使用すると、Blockstateは"type=0"から"type=15"までが登録される
        return BlockStateContainer(this, property16)
    }

    //Blockstateからメタデータを得るメソッド
    override fun getMetaFromState(state: IBlockState): Int {
        //property16をもとにBlockstateからメタデータを返す
        val i = state.getValue(property16)
        //メタデータがmaxMetaよりも大きい場合、最大値を返す
        return i.coerceAtMost(maxMeta)
    }

    //メタデータからBlockstateを得るメソッド
    override fun getStateFromMeta(meta: Int): IBlockState {
        //metaをmaxMete+1で割った剰余を求める
        //これmaxMeta % maxMeta = 0になるからわざと+1してます
        val i = meta % (maxMeta + 1)
        //property16をもとに指定したメタデータからBlockstateを返す
        return defaultState.withProperty(property16, i)
    }

    //Blockstateをもとにドロップするアイテムのメタデータを指定するメソッド
    override fun damageDropped(state: IBlockState): Int {
        //property16をもとにBlockstateからメタデータを返す
        val i = state.getValue(property16)
        //メタデータがmaxMetaよりも大きい場合、最大値を返す
        return i.coerceAtMost(maxMeta)
    }

    //ドロップするアイテムを得るメソッド
    override fun getItemDropped(state: IBlockState, rand: Random, fortune: Int): Item {
        //Blockstateからブロックを取得し、更にそこからアイテムを取得して返す
        return Item.getItemFromBlock(state.block)
    }

    //ドロップする確率を得るメソッド
    override fun quantityDropped(random: Random): Int {
        //常にドロップさせるので1を返す
        return 1
    }
}