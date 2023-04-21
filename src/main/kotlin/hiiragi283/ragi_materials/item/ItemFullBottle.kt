package hiiragi283.ragi_materials.item

import hiiragi283.ragi_materials.RagiMaterials
import hiiragi283.ragi_materials.api.material.IMaterialItem
import hiiragi283.ragi_materials.api.material.MaterialRegistry
import hiiragi283.ragi_materials.api.material.RagiMaterial
import hiiragi283.ragi_materials.util.getBottle
import hiiragi283.ragi_materials.util.getMaterialFromName
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
import net.minecraftforge.fluids.FluidUtil
import net.minecraftforge.fluids.capability.CapabilityFluidHandler
import net.minecraftforge.fluids.capability.templates.FluidHandlerItemStackSimple
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

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
        if (this.isInCreativeTab(tab)) {
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
                return if (capability == CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY) FullBottleFluidHandler(stack, 1000) as T else null
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

        override fun canFillFluidType(fluidStack: FluidStack): Boolean = !getMaterialFromName(fluidStack.fluid.name).isEmpty() //液体の名前から取得した素材が空でないならtrue
    }

    //    IMaterialItem    //

    override fun getMaterial(stack: ItemStack): RagiMaterial {
        val fluidStack = FluidUtil.getFluidContained(stack)
        return if (fluidStack !== null) getMaterialFromName(fluidStack.fluid.name) else RagiMaterial.EMPTY
    }

    override fun setMaterial(stack: ItemStack, material: RagiMaterial): ItemStack {
        return getBottle(material, count = stack.count)
    }
}