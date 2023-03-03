package hiiragi283.ragi_materials.item

import hiiragi283.ragi_materials.Reference
import hiiragi283.ragi_materials.base.ItemBase
import hiiragi283.ragi_materials.material.MaterialManager
import net.minecraft.client.resources.I18n
import net.minecraft.client.util.ITooltipFlag
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.util.EnumFacing
import net.minecraft.util.NonNullList
import net.minecraft.world.World
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.common.capabilities.ICapabilityProvider
import net.minecraftforge.fluids.FluidRegistry
import net.minecraftforge.fluids.FluidStack
import net.minecraftforge.fluids.capability.CapabilityFluidHandler
import net.minecraftforge.fluids.capability.templates.FluidHandlerItemStackSimple

import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

class ItemFullBottle : ItemBase(Reference.MOD_ID, "fullbottle", 0) {


    //    Client    //

    @SideOnly(Side.CLIENT)
    override fun addInformation(stack: ItemStack, world: World?, tooltip: MutableList<String>, flag: ITooltipFlag) {
        val path = stack.item.registryName!!.resourcePath
        tooltip.add("§e=== Info ===")
        for (i in 0..1) {
            tooltip.add(I18n.format("text.ragi_materials.${path}.$i"))
        }
        super.addInformation(stack, world, tooltip, ITooltipFlag.TooltipFlags.NORMAL)
    }

    @SideOnly(Side.CLIENT)
    override fun getItemStackDisplayName(stack: ItemStack): String {
        val fluidItem = stack.getCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY, null)
        return if ((fluidItem !== null) && (fluidItem.tankProperties[0].contents?.fluid?.unlocalizedName !== null)) {
            val name = fluidItem.tankProperties[0].contents?.fluid?.unlocalizedName!!
            I18n.format("item.fullbottle_filled.name", I18n.format(name))
        } else I18n.format("item.fullbottle.name")
    }

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

    //    Capability    //

    override fun initCapabilities(stack: ItemStack, nbt: NBTTagCompound?): ICapabilityProvider {
        return CellProvider(stack)
    }

    private class CellProvider(val stack: ItemStack) : ICapabilityProvider {

        override fun <T : Any?> getCapability(capability: Capability<T>, facing: EnumFacing?): T? {
            return if (capability == CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY) FullBottleFluidHandler(
                stack, 1000
            ) as T else null
        }

        override fun hasCapability(capability: Capability<*>, facing: EnumFacing?): Boolean {
            return capability == CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY
        }
    }

    class FullBottleFluidHandler(val stack: ItemStack, capacity:Int): FluidHandlerItemStackSimple(stack, capacity) {

        override fun drain(maxDrain: Int, doDrain: Boolean): FluidStack? {
            //変数の宣言
            var result: FluidStack? = null
            //stackにNBTタグがない場合, NBTタグを付与
            if (stack.tagCompound == null) stack.tagCompound = NBTTagCompound()
            //NBTタグを取得
            val tag = stack.tagCompound!!
            //NBTタグがFluidというkeyを持っている場合
            if (tag.hasKey("Fluid")) {
                //Fluid配下のNBTタグからFluidStackを取得
                val fluidStack = FluidStack.loadFluidStackFromNBT(tag.getCompoundTag("Fluid"))
                //FluidStackがnullでない場合
                if (fluidStack !== null) {
                    val amount = fluidStack.amount //液体量を取得
                    //液体量が1000の場合
                    if (amount == 1000) {
                        //doDrain==true，かつstackの個数が1よりも大きい場合
                        if (doDrain && stack.count > 1) stack.shrink(1) //stackを1つ減らす
                        result = fluidStack.copy() //FluidStackを代入する
                    }
                }
            }
            return result
        }

        override fun canFillFluidType(fluidStack: FluidStack): Boolean {
            val fluid = fluidStack.fluid
            //液体の名前から素材が取得できるならtrue
            return MaterialManager.getMaterial(fluid.name) !== null
        }
    }
}