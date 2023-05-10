package ragi_materials.main.client.model

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
object ModelManager {

    //代入されたItemに応じてモデルファイルのパスを登録するメソッド

    fun setModel(item: Item) {
        //itemが耐久値を使用しない，かつhasSubtypesがtrueの場合
        if (item.getMaxDamage(ItemStack(item)) == 0 && item.hasSubtypes) {
            //メタデータが最大値になるまで処理を繰り返す
            for (i in 0..item.getMetadata(32768)) {
                val location = ModelResourceLocation(item.registryName.toString() + "_" + i, "inventory")
                ModelLoader.setCustomModelResourceLocation(item, i, location)
            }
        } else {
            //1つだけ登録する
            ModelLoader.setCustomModelResourceLocation(item, 0, ModelResourceLocation(item.registryName!!, "inventory"))
        }
    }

    fun setModel(block: Block) {
        val item = Item.getItemFromBlock(block)
        if (item.registryName?.path != "air") setModel(item)
    }

    fun setModel(vararg items: Item) {
        for (item in items) {
            setModel(item)
        }
    }

    //メタデータによらず特定のモデルファイルだけを利用させるメソッド
    fun setModelSame(item: Item) {
        ModelLoader.setCustomMeshDefinition(item) { ModelResourceLocation(item.registryName!!, "inventory") }
    }

    fun setModelSame(vararg items: Item) {
        for (item in items) {
            setModelSame(item)
        }
    }

    //新規でモデルのパスを紐づけてモデルを登録するメソッド
    fun setModelAlt(item: Item, location: ModelResourceLocation) {
        ModelLoader.registerItemVariants(item, location)
        ModelLoader.setCustomMeshDefinition(item) { location }
    }

    fun setModelAlt(block: Block, location: ModelResourceLocation) {
        setModelAlt(Item.getItemFromBlock(block), location)
    }

    fun setStateMapperAlt(block: Block, location: ModelResourceLocation) {
        ModelLoader.setCustomStateMapper(block, object : StateMapperBase() {
            override fun getModelResourceLocation(state: IBlockState): ModelResourceLocation = location
        })
    }

}