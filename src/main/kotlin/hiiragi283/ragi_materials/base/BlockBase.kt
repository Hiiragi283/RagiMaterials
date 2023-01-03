package hiiragi283.ragi_materials.base

import net.minecraft.block.Block
import net.minecraft.block.material.Material
import net.minecraft.block.properties.PropertyInteger
import net.minecraft.block.state.BlockStateContainer
import net.minecraft.block.state.IBlockState
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.Item
import java.util.*

class BlockBase(Material: Material?, MOD: String, ID: String?, maxMeta: Int) : Block(Material!!) {

    //private変数の宣言
    private val maxMeta: Int
    private val type16 = PropertyInteger.create("type", 0, 15)

    //コンストラクタの宣言
    init {
        defaultState = blockState.baseState.withProperty(type16, 0) //デフォルトのBlockstateをtype16の0番に設定
        setCreativeTab(CreativeTabs.DECORATIONS) //表示するクリエイティブタブの設定
        setRegistryName(MOD, ID) //IDの設定
        unlocalizedName = ID.toString() //翻訳キーをIDから取得
        this.maxMeta = maxMeta //メタデータの最大値を代入
    }

    //Blockstateの登録をするメソッド
    override fun createBlockState(): BlockStateContainer {
        //type16を使用すると、Blockstateは"type=0"から"type=15"までが登録される
        return BlockStateContainer(this, type16)
    }

    //Blockstateからメタデータを得るメソッド
    override fun getMetaFromState(state: IBlockState): Int {
        var i = 0
        //type16をもとにBlockstateからメタデータを返す
        i = state.getValue(type16)
        //メタデータがmaxMetaよりも大きい場合、最大値を返す
        if (i > maxMeta) i = maxMeta
        return i
    }

    //メタデータからBlockstateを得るメソッド
    override fun getStateFromMeta(meta: Int): IBlockState {
        //metaをmaxMete+1で割った剰余を求める
        //これmaxMeta % maxMeta = 0になるからわざと+1してます
        val i = meta % (maxMeta + 1)
        //type16をもとに指定したメタデータからBlockstateを返す
        return defaultState.withProperty(type16, i)
    }

    //Blockstateをもとにドロップするアイテムのメタデータを指定するメソッド
    override fun damageDropped(state: IBlockState): Int {
        //type16をもとにBlockstateからメタデータを返す
        var i = state.getValue(type16)
        //メタデータがmaxMetaよりも大きい場合、最大値を返す
        if (i > maxMeta) i = maxMeta
        return i
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