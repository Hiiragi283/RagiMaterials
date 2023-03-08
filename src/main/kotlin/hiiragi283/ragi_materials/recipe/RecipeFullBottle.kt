package hiiragi283.ragi_materials.recipe

import hiiragi283.ragi_materials.Reference
import hiiragi283.ragi_materials.init.RagiInit
import hiiragi283.ragi_materials.util.RagiUtil
import net.minecraft.init.Items
import net.minecraft.inventory.InventoryCrafting
import net.minecraft.item.ItemStack
import net.minecraft.item.crafting.IRecipe
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.util.NonNullList
import net.minecraft.world.World
import net.minecraftforge.fluids.FluidStack
import net.minecraftforge.fluids.capability.CapabilityFluidHandler

class RecipeFullBottle : net.minecraftforge.registries.IForgeRegistryEntry.Impl<IRecipe>(), IRecipe {

    init {
        setRegistryName("${Reference.MOD_ID}:fullbottle") //タイプミス防止のため予めレシピ名を書いておく
    }

    //レシピが一致するかどうかを判定するメソッド
    override fun matches(inv: InventoryCrafting, world: World): Boolean {
        //変数の宣言
        var hasBucketFilled = false
        //クラフトグリッド内の各スロットに対して実行
        for (i in 0..inv.sizeInventory) {
            //スロットからstackを取得
            val stack = inv.getStackInSlot(i)
            //stackからIFluidHandlerItemを取得
            val fluidItem = stack.getCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY, null)
            //IFluidHandlerItemから取得できる液体がnullでない場合
            if (fluidItem?.tankProperties?.get(0)?.contents?.fluid !== null && !hasBucketFilled) hasBucketFilled = true
        }
        return hasBucketFilled
    }

    //クラフト結果を返すメソッド
    override fun getCraftingResult(inv: InventoryCrafting): ItemStack {
        //変数の宣言
        var fluidStack: FluidStack? = null
        //クラフトグリッド内の各スロットに対して実行
        for (i in 0..inv.sizeInventory) {
            //スロットからstackを取得
            val stack = inv.getStackInSlot(i)
            //stackからIFluidHandlerItemを取得
            val fluidItem = stack.getCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY, null)
            //IFluidHandlerItemから取得できるFluidStackがnullでない場合
            if (fluidItem?.tankProperties?.get(0)?.contents !== null) fluidStack = fluidItem.tankProperties[0].contents
        }
        //fluidStackがnullでない，かつfluidStackの量が1000 mb以上の場合
        return if (fluidStack !== null && fluidStack.amount >= 1000) {
            val count = fluidStack.amount / 1000
            fluidStack.amount = 1000
            val result = ItemStack(RagiInit.ItemFullBottle, count, 0)
            result.tagCompound = RagiUtil.setFluidToNBT(NBTTagCompound(), fluidStack)
            result
        } else ItemStack.EMPTY
    }

    //クラフトグリッドに残るアイテムを取得するメソッド
    override fun getRemainingItems(inv: InventoryCrafting): NonNullList<ItemStack> {
        //変数の宣言
        var fluidStack: FluidStack? = null
        val list = NonNullList.withSize(inv.sizeInventory, ItemStack.EMPTY)
        //クラフトグリッド内の各スロットに対して実行
        for (i in 0..inv.sizeInventory) {
            //スロットからstackを取得
            val stack = inv.getStackInSlot(i)
            //stackからIFluidHandlerItemを取得
            val fluidItem = stack.getCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY, null)
            //IFluidHandlerItemから取得できるFluidStackがnullでない場合
            if (fluidItem?.tankProperties?.get(0)?.contents !== null) {
                fluidStack = fluidItem.tankProperties[0].contents!!
                //液体の量が1000 mbより大きい場合
                if (fluidStack.amount > 1000) {
                    val drain = fluidStack.amount - (fluidStack.amount % 1000)
                    fluidItem.drain(drain, true) //1000 mb汲み取る
                    list[i] = stack.copy() //listに追加
                    break
                }
                //アイテムのIDが"bucket"の文字列を含んでいる場合
                if ("bucket" in stack.item.registryName!!.resourcePath) {
                    list[i] = ItemStack(Items.BUCKET) //空のバケツを追加
                }
            }
        }
        return list
    }

    //レシピのサイズが一致するかどうかを返すメソッド
    //よくわかってない
    override fun canFit(width: Int, height: Int): Boolean {
        return true
    }

    //デフォルトのクラフト結果を返すメソッド
    override fun getRecipeOutput(): ItemStack {
        return ItemStack.EMPTY //何もない
    }

    //不定形かどうかを判定するメソッド（多分）
    override fun isDynamic(): Boolean {
        return true
    }

}