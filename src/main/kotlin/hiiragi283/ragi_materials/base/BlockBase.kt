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
        //stateから得られたmetadataが0以上maxMeta以下の場合，そのまま返す
        //そうでない場合はmaxMetaを返す
        return if (state.getValue(property16) in 0..maxMeta) state.getValue(property16) else maxMeta
    }

    //メタデータからBlockstateを得るメソッド
    override fun getStateFromMeta(meta: Int): IBlockState {
        //代入した値が0以上maxMeta以下の場合，そのまま返す
        //そうでない場合はmaxMetaを返す
        val metadata = if (meta in 0..maxMeta) meta else maxMeta
        //property16をもとに指定したメタデータからBlockstateを返す
        return defaultState.withProperty(property16, metadata)
    }

    //Blockstateをもとにドロップするアイテムのメタデータを指定するメソッド
    override fun damageDropped(state: IBlockState): Int {
        //stateから得られたmetadataが0以上maxMeta以下の場合，そのまま返す
        //そうでない場合はmaxMetaを返す
        return if (state.getValue(property16) in 0..maxMeta) state.getValue(property16) else maxMeta
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