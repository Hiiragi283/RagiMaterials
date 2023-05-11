package hiiragi283.material.base

import hiiragi283.material.RagiMaterials
import net.minecraft.block.Block
import net.minecraft.block.material.Material
import net.minecraft.block.properties.PropertyBool
import net.minecraft.block.properties.PropertyDirection
import net.minecraft.block.properties.PropertyEnum
import net.minecraft.block.properties.PropertyInteger
import net.minecraft.block.state.IBlockState
import net.minecraft.client.resources.I18n
import net.minecraft.client.util.ITooltipFlag
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.ItemStack
import net.minecraft.util.EnumFacing
import net.minecraft.util.IStringSerializable
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import net.minecraftforge.registries.IForgeRegistry

abstract class BlockBase(ID: String, Material: Material, private val maxTips: Int) : Block(Material) {

    abstract val itemBlock: ItemBlockBase?

    init {
        setRegistryName(RagiMaterials.MOD_ID, ID)
        creativeTab = CreativeTabs.BUILDING_BLOCKS
        translationKey = ID
    }

    //    BlockState    //

    fun getBoolean(state: IBlockState, property: PropertyBool = RagiProperty.ACTIVE): Boolean? {
        return if (property in state.propertyKeys) state.getValue(property) else null
    }

    fun <T> getEnum(state: IBlockState, property: PropertyEnum<T>): T?
            where T : Enum<T>, T : IStringSerializable {
        return if (property in state.propertyKeys) state.getValue(property) else null
    }

    fun getFacing(state: IBlockState, property: PropertyDirection = RagiProperty.HORIZONTAL): EnumFacing? {
        return if (property in state.propertyKeys) state.getValue(property) else null
    }

    fun getInteger(state: IBlockState, property: PropertyInteger): Int? {
        return if (property in state.propertyKeys) state.getValue(property) else null
    }

    //    ClientEvent    //

    @SideOnly(Side.CLIENT)
    override fun addInformation(stack: ItemStack, world: World?, tooltip: MutableList<String>, flag: ITooltipFlag) {
        val path = stack.item.registryName!!.path
        if (maxTips != -1) {
            tooltip.add("§e=== Info ===")
            for (i in 0..maxTips) {
                tooltip.add(I18n.format("tips.${RagiMaterials.MOD_ID}.${path}.$i"))
            }
        }
    }

    //    BlockBase    //

    fun register(registry: IForgeRegistry<Block>) {
        registry.register(this)
    }

}