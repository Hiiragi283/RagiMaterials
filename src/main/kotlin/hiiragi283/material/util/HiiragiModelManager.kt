package hiiragi283.material.util

import net.minecraft.block.Block
import net.minecraft.client.renderer.block.model.ModelResourceLocation
import net.minecraft.init.Items
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraftforge.client.model.ModelLoader
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

@SideOnly(Side.CLIENT)
object HiiragiModelManager {

    //代入されたItemに応じてモデルファイルのパスを登録するメソッド
    @JvmStatic
    fun setModel(item: Item) {
        //itemが耐久値を使用しない，かつhasSubtypesがtrueの場合
        if (item.getMaxDamage(ItemStack(item)) == 0 && item.hasSubtypes) {
            //メタデータが最大値になるまで処理を繰り返す
            (0..item.getMetadata(32768)).forEach {
                ModelLoader.setCustomModelResourceLocation(
                    item,
                    it,
                    ModelResourceLocation(item.registryName!!.append("_$it"), "inventory")
                )
            }
        } else {
            //1つだけ登録する
            setModelSame(item)
        }
    }

    @JvmStatic
    fun setModel(block: Block) {
        val item = Item.getItemFromBlock(block)
        if (item != Items.AIR) setModel(item)
    }

    //メタデータによらず特定のモデルファイルだけを利用させるメソッド
    @JvmStatic
    fun setModelSame(item: Item) {
        ModelLoader.setCustomMeshDefinition(item) { ModelResourceLocation(item.registryName!!, "inventory") }
    }

}