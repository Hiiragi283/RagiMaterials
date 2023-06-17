package hiiragi283.material.util

import net.minecraft.block.Block
import net.minecraft.block.state.IBlockState
import net.minecraft.client.renderer.block.model.ModelResourceLocation
import net.minecraft.client.renderer.block.statemap.StateMapperBase
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraftforge.client.model.ModelLoader
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

@SideOnly(Side.CLIENT)
object RMModelManager {

    //代入されたItemに応じてモデルファイルのパスを登録するメソッド
    @JvmStatic
    fun setModel(item: Item) {
        //itemが耐久値を使用しない，かつhasSubtypesがtrueの場合
        if (item.getMaxDamage(ItemStack(item)) == 0 && item.hasSubtypes) {
            //メタデータが最大値になるまで処理を繰り返す
            (0..item.getMetadata(32768)).forEach {
                val location = ModelResourceLocation(item.registryName.toString() + "_" + it, "inventory")
                ModelLoader.setCustomModelResourceLocation(item, it, location)
            }
        } else {
            //1つだけ登録する
            setModelSame(item)
        }
    }

    @JvmStatic
    fun setModel(block: Block) {
        val item = Item.getItemFromBlock(block)
        if (item.registryName?.path != "air") setModel(item)
    }

    @JvmStatic
    fun setModel(vararg items: Item): Unit = items.forEach { setModel(it) }

    //メタデータによらず特定のモデルファイルだけを利用させるメソッド
    @JvmStatic
    fun setModelSame(item: Item) {
        ModelLoader.setCustomMeshDefinition(item) { ModelResourceLocation(item.registryName!!, "inventory") }
    }

    @JvmStatic
    fun setModelSame(vararg items: Item): Unit = items.forEach { setModelSame(it) }

    //新規でモデルのパスを紐づけてモデルを登録するメソッド
    @JvmStatic
    fun setModelAlt(item: Item, location: ModelResourceLocation) {
        ModelLoader.registerItemVariants(item, location)
        ModelLoader.setCustomMeshDefinition(item) { location }
    }

    @JvmStatic
    fun setModelAlt(block: Block, location: ModelResourceLocation) {
        setModelAlt(Item.getItemFromBlock(block), location)
    }

    @JvmStatic
    fun setStateMapperAlt(block: Block, location: ModelResourceLocation) {
        ModelLoader.setCustomStateMapper(block, object : StateMapperBase() {
            override fun getModelResourceLocation(state: IBlockState): ModelResourceLocation = location
        })
    }

}