package ragi_materials.main.item

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
import net.minecraftforge.fluids.FluidStack
import net.minecraftforge.fluids.capability.CapabilityFluidHandler
import net.minecraftforge.fluids.capability.templates.FluidHandlerItemStackSimple
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import ragi_materials.core.RagiMaterials
import ragi_materials.core.item.ItemBase
import ragi_materials.core.material.IMaterialItem
import ragi_materials.core.material.MaterialRegistry
import ragi_materials.core.material.RagiMaterial
import ragi_materials.core.util.getBottle
import ragi_materials.core.util.getMaterialFromName

class ItemFullBottle : ItemBase(RagiMaterials.MOD_ID, "fullbottle", 0), IMaterialItem {

    //    General    //

    override fun getForgeRarity(stack: ItemStack): IRarity {
        return getMaterial(stack).rarity
    }

    //    Client    //

    @SideOnly(Side.CLIENT)
    override fun addInformation(stack: ItemStack, world: World?, tooltip: MutableList<String>, flag: ITooltipFlag) {
        //アイテムとしてのツールチップ
        val path = stack.item.registryName!!.path
        tooltip.add("§e=== Info ===")
        for (i in 0..1) {
            tooltip.add(I18n.format("tips.ragi_materials.${path}.$i"))
        }
        //素材のツールチップ
        getMaterial(stack).getTooltip(tooltip)
    }

    @SideOnly(Side.CLIENT)
    override fun getItemStackDisplayName(stack: ItemStack): String {
        val fluid = getMaterial(stack).getFluid()
        return fluid?.let { I18n.format("item.fullbottle_filled.name", I18n.format(it.getLocalizedName(FluidStack(fluid, 1000)))) }
                ?: super.getItemStackDisplayName(stack)
    }

    @SideOnly(Side.CLIENT)
    override fun getSubItems(tab: CreativeTabs, subItems: NonNullList<ItemStack>) {
        if (isInCreativeTab(tab)) {
            val stack = ItemStack(this)
            subItems.add(stack) //空のフルボトル
            //素材の一覧から液体が取得できるならクリエタブに登録する
            for (material in MaterialRegistry.list) {
                material.getFluid()?.let {
                    subItems.add(getBottle(FluidStack(it, 1000)))
                }
            }
        }
    }

    //    Capability    //

    override fun initCapabilities(stack: ItemStack, nbt: NBTTagCompound?): ICapabilityProvider {
        return object : ICapabilityProvider {

            override fun <T : Any?> getCapability(capability: Capability<T>, facing: EnumFacing?): T? {
                return if (capability == CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY) CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY.cast(FullBottleFluidHandler(stack, 1000)) else null
            }

            override fun hasCapability(capability: Capability<*>, facing: EnumFacing?): Boolean {
                return capability == CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY
            }
        }
    }

    class FullBottleFluidHandler(val stack: ItemStack, capacity: Int) : FluidHandlerItemStackSimple(stack, capacity) {

        override fun drain(maxDrain: Int, doDrain: Boolean): FluidStack? {
            //変数の宣言
            var result: FluidStack? = null
            //NBTタグからFluidStackを取得
            val fluidStack = FluidStack.loadFluidStackFromNBT(stack.getOrCreateSubCompound("Fluid"))
            //FluidStackがnullでない場合
            if (fluidStack !== null) {
                val amount = fluidStack.amount //液体量を取得
                //液体量が1000の場合
                if (amount == 1000) {
                    if (doDrain) stack.shrink(1) //stackを1つ減らす
                    result = fluidStack.copy() //FluidStackを代入する
                }
            }
            return result
        }

        override fun canFillFluidType(fluidStack: FluidStack): Boolean = !getMaterialFromName(fluidStack.fluid.name).isEmpty() //液体の名前から取得した素材が空でないならtrue
    }

    //    IMaterialItem    //

    override fun getMaterial(stack: ItemStack): RagiMaterial {
        FluidStack.loadFluidStackFromNBT(stack.getOrCreateSubCompound("Fluid"))?.let { return getMaterialFromName(it.fluid.name) }
                ?: return RagiMaterial.EMPTY
    }

    override fun setMaterial(stack: ItemStack, material: RagiMaterial) = getBottle(material, count = stack.count)
}