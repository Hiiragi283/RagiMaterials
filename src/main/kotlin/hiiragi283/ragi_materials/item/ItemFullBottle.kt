package hiiragi283.ragi_materials.item

import hiiragi283.ragi_materials.RagiRegistry
import hiiragi283.ragi_materials.Reference
import hiiragi283.ragi_materials.base.ItemBase
import hiiragi283.ragi_materials.material.MaterialUtil
import hiiragi283.ragi_materials.material.builder.MaterialBuilder
import hiiragi283.ragi_materials.util.RagiUtil
import net.minecraft.client.resources.I18n
import net.minecraft.client.util.ITooltipFlag
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.util.EnumFacing
import net.minecraft.util.NonNullList
import net.minecraft.world.World
import net.minecraftforge.common.IRarity
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.common.capabilities.ICapabilityProvider
import net.minecraftforge.fluids.FluidRegistry
import net.minecraftforge.fluids.FluidStack
import net.minecraftforge.fluids.FluidUtil
import net.minecraftforge.fluids.capability.CapabilityFluidHandler
import net.minecraftforge.fluids.capability.templates.FluidHandlerItemStackSimple
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

class ItemFullBottle : ItemBase(Reference.MOD_ID, "fullbottle", 0), IMaterialItem {

    init {
        creativeTab = RagiRegistry.TabFullBottle
    }

    //    General    //

    override fun getForgeRarity(stack: ItemStack): IRarity {
        return getMaterial(stack).rarity
    }

    //    Client    //

    @SideOnly(Side.CLIENT)
    override fun addInformation(stack: ItemStack, world: World?, tooltip: MutableList<String>, flag: ITooltipFlag) {
        //アイテムとしてのツールチップ
        val path = stack.item.registryName!!.resourcePath
        tooltip.add("§e=== Info ===")
        for (i in 0..1) {
            tooltip.add(I18n.format("tips.ragi_materials.${path}.$i"))
        }
        //素材のツールチップ
        MaterialUtil.materialInfo(getMaterial(stack), tooltip)
        super.addInformation(stack, world, tooltip, ITooltipFlag.TooltipFlags.NORMAL)
    }

    @SideOnly(Side.CLIENT)
    override fun getItemStackDisplayName(stack: ItemStack): String {
        val material = getMaterial(stack)
        val fluid = material.getFluid()
        return if (material.isNotEmpty() && fluid !== null) I18n.format("item.fullbottle_filled.name", I18n.format(fluid.getLocalizedName(FluidStack(fluid, 1000)))) else super.getItemStackDisplayName(stack)
    }

    @SideOnly(Side.CLIENT)
    override fun getSubItems(tab: CreativeTabs, subItems: NonNullList<ItemStack>) {
        if (isInCreativeTab(tab)) {
            val stack = ItemStack(this)
            subItems.add(stack) //空のフルボトル
            for (fluid in FluidRegistry.getRegisteredFluids().values) {
                //液体の名前から素材が取得できる場合のみ登録
                if (!MaterialUtil.getMaterial(fluid.name).isEmpty()) {
                    val stackFilled = setFluid(stack.copy(), fluid.name)
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
            return if (capability == CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY) FullBottleFluidHandler(stack, 1000) as T else null
        }

        override fun hasCapability(capability: Capability<*>, facing: EnumFacing?): Boolean {
            return capability == CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY
        }
    }

    class FullBottleFluidHandler(val stack: ItemStack, capacity: Int) : FluidHandlerItemStackSimple(stack, capacity) {

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
                        if (doDrain) stack.shrink(1) //stackを1つ減らす
                        result = fluidStack.copy() //FluidStackを代入する
                    }
                }
            }
            return result
        }

        override fun canFillFluidType(fluidStack: FluidStack): Boolean = !MaterialUtil.getMaterial(fluidStack.fluid.name).isEmpty() //液体の名前から取得した素材が空でないならtrue
    }

    //    IMaterialItem    //

    override fun getMaterial(stack: ItemStack): MaterialBuilder {
        val fluidStack = FluidUtil.getFluidContained(stack)
        return if (fluidStack !== null) MaterialUtil.getMaterial(fluidStack.fluid.name) else MaterialBuilder.EMPTY
    }

    override fun setMaterial(stack: ItemStack, material: MaterialBuilder): ItemStack {
        return setFluid(stack, material.name)
    }

    private fun setFluid(stack: ItemStack, fluid: String, amount: Int = 1000): ItemStack {
        stack.tagCompound = RagiUtil.setFluidToNBT(NBTTagCompound(), RagiUtil.getFluidStack(fluid, amount))
        return stack
    }

}