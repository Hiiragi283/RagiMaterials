package hiiragi283.material.item

import hiiragi283.material.api.item.HiiragiItem
import hiiragi283.material.api.item.ICrusherItem
import hiiragi283.material.util.CraftingBuilder
import net.minecraft.block.state.IBlockState
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.entity.EntityLivingBase
import net.minecraft.init.Items
import net.minecraft.item.ItemStack
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import net.minecraftforge.oredict.OreDictionary

object ItemCrushingHammer : HiiragiItem("crushing_hammer", 0), ICrusherItem {

    init {
        creativeTab = CreativeTabs.TOOLS
        maxDamage = 63
        setMaxStackSize(1)
    }

    //どんなブロックでも採掘可能
    override fun canHarvestBlock(state: IBlockState, stack: ItemStack): Boolean = true

    //クラフト時に返却されるstackを取得するメソッド
    override fun getContainerItem(stack: ItemStack): ItemStack = stack.copy().also {
        it.itemDamage += 1
    }

    //採掘速度は12.0fで固定
    override fun getDestroySpeed(stack: ItemStack, state: IBlockState): Float = 12.0f

    //"ingotIron"を含むアイテムなら受け付ける
    override fun getIsRepairable(toRepair: ItemStack, repair: ItemStack): Boolean =
        "ingotIron" in OreDictionary.getOreIDs(repair).map { OreDictionary.getOreName(it) }

    override fun getItemEnchantability(stack: ItemStack): Int = 15

    //斧，ツルハシ，ショベルに適正あり
    override fun getToolClasses(stack: ItemStack): MutableSet<String> =
        mutableSetOf("axe", "pickaxe", "shovel")

    //クラフト時にstackを返却するかどうかを確認するメソッド
    override fun hasContainerItem(stack: ItemStack): Boolean = true

    @SideOnly(Side.CLIENT)
    override fun isFull3D(): Boolean = true

    //採掘時に耐久地が1減る
    override fun onBlockDestroyed(
        stack: ItemStack,
        worldIn: World,
        state: IBlockState,
        pos: BlockPos,
        entityLiving: EntityLivingBase
    ): Boolean {
        if (!worldIn.isRemote && state.getBlockHardness(worldIn, pos).toDouble() != 0.0) {
            stack.damageItem(1, entityLiving)
        }
        return true
    }

    //    HiiragiEntry    //

    override fun registerRecipe() {
        CraftingBuilder(ItemStack(this))
            .setPattern("AAA", "AAA", " B ")
            .setIngredient('A', "ingotIron")
            .setIngredient('B', ItemStack(Items.SIGN))
            .build()
    }
}