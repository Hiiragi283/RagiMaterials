package hiiragi283.ragi_materials.item

import hiiragi283.ragi_materials.Reference
import hiiragi283.ragi_materials.base.ItemBase
import hiiragi283.ragi_materials.material.MaterialManager
import net.minecraft.client.resources.I18n
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.util.EnumFacing
import net.minecraft.util.NonNullList
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.common.capabilities.ICapabilityProvider
import net.minecraftforge.fluids.FluidRegistry
import net.minecraftforge.fluids.FluidStack
import net.minecraftforge.fluids.capability.CapabilityFluidHandler
import net.minecraftforge.fluids.capability.templates.FluidHandlerItemStackSimple

import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

class ItemFullBottle : ItemBase(Reference.MOD_ID, "fullbottle", 0) {

    //stackの表示名を上書きするメソッド
    @SideOnly(Side.CLIENT)
    override fun getItemStackDisplayName(stack: ItemStack): String {
        val fluidItem = stack.getCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY, null)
        return if ((fluidItem !== null) && (fluidItem.tankProperties[0].contents?.fluid?.unlocalizedName !== null)) {
            val name = fluidItem.tankProperties[0].contents?.fluid?.unlocalizedName!!
            I18n.format("item.fullbottle_filled.name", I18n.format(name))
        } else I18n.format("item.fullbottle.name")
    }

    //メタデータ付きアイテムをクリエイティブタブに登録するメソッド
    @SideOnly(Side.CLIENT) //Client側のみ
    override fun getSubItems(tab: CreativeTabs, subItems: NonNullList<ItemStack>) {
        if (isInCreativeTab(tab)) {
            //空のフルボトル
            subItems.add(ItemStack(this))
            //液体の名前から素材が取得できる場合のみ登録
            for (fluid in FluidRegistry.getRegisteredFluids().values) {
                if (MaterialManager.getMaterial(fluid.name) !== null) {
                    val stack = ItemStack(this)
                    val fluidItem = stack.getCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY, null)
                    fluidItem!!.fill(FluidStack(fluid, 1000), true)
                    val stackFilled = fluidItem.container
                    subItems.add(stackFilled)
                }
            }
        }
    }

    /*
      Thanks to defeatedcrow!
      Source: https://github.com/Hiiragi283/HeatAndClimateMod/blob/1.12.2_v3/main/java/defeatedcrow/hac/food/item/ItemSilverCup.java#L331
    */

    override fun initCapabilities(stack: ItemStack, nbt: NBTTagCompound?): ICapabilityProvider {
        return CellProvider(stack)
    }

    private class CellProvider(val stack: ItemStack) : ICapabilityProvider {

        //capabilityを持っているか判別するメソッド
        override fun hasCapability(capability: Capability<*>, facing: EnumFacing?): Boolean {
            return capability == CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY
        }

        //capabilityを取得するメソッド
        override fun <T : Any?> getCapability(capability: Capability<T>, facing: EnumFacing?): T? {
            return if (capability == CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY) FullBottleFluidHandler(
                stack, 1000
            ) as T else null
        }
    }

    class FullBottleFluidHandler(val stack: ItemStack, capacity:Int): FluidHandlerItemStackSimple(stack, capacity) {

        //液体を組みだすメソッド
        override fun drain(maxDrain: Int, doDrain: Boolean): FluidStack? {
            //stackにNBTタグがない場合, NBTタグを付与
            if (stack.tagCompound == null) stack.tagCompound = NBTTagCompound()
            //NBTタグからFluidStackを取得
            val fluidstack = FluidStack.loadFluidStackFromNBT(stack.tagCompound)
            //FluidStackがnullでない場合
            return if (fluidstack !== null) {
                val amount = fluidstack.amount //液体量を取得
                //液体量が1000の場合
                if (amount == 1000) {
                    if (doDrain) stack.shrink(1) //doDrain==trueの場合，空のボトルを返す
                    fluidstack.copy() //FluidStackを返す
                } else null
            } else null
        }

        //液体を組めるか判定するメソッド
        override fun canFillFluidType(fluidStack: FluidStack): Boolean {
            val fluid = fluidStack.fluid
            //液体の名前から素材が取得できるならtrue
            return MaterialManager.getMaterial(fluid.name) !== null
        }
    }
}