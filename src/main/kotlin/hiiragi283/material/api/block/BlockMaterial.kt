package hiiragi283.material.api.block

import hiiragi283.material.RMCreativeTabs
import hiiragi283.material.RMReference
import hiiragi283.material.api.item.ItemBlockMaterial
import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.api.material.MaterialRegistry
import hiiragi283.material.api.shape.HiiragiShape
import hiiragi283.material.api.tileentity.HiiragiTileEntity
import hiiragi283.material.api.tileentity.MaterialTileEntity
import hiiragi283.material.util.getTile
import net.minecraft.block.material.Material
import net.minecraft.block.state.IBlockState
import net.minecraft.client.renderer.color.BlockColors
import net.minecraft.client.renderer.color.ItemColors
import net.minecraft.item.ItemStack
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import net.minecraftforge.oredict.OreDictionary

open class BlockMaterial<T : HiiragiTileEntity>(val shape: HiiragiShape, material: Material, tile: Class<T>) :
    HiiragiBlockContainer.Holdable<T>(material, RMReference.MOD_ID, shape.name, tile) {

    override val itemBlock = ItemBlockMaterial(this)

    init {
        creativeTab = RMCreativeTabs.MATERIAL_BLOCK
    }

    //    HiiragiBlock    //

    @Deprecated(
        "Deprecated in Java", ReplaceWith(
            "getItemStack(getTile<MaterialTileEntity>(worldIn, pos)?.material ?: HiiragiMaterial.EMPTY)",
            "hiiragi283.material.util.getTile",
            "hiiragi283.material.api.tile.MaterialTileEntity",
            "hiiragi283.material.api.material.HiiragiMaterial"
        )
    )
    override fun getItem(worldIn: World, pos: BlockPos, state: IBlockState): ItemStack =
        getItemStack(getTile<MaterialTileEntity>(worldIn, pos)?.material ?: HiiragiMaterial.EMPTY)

    //    HiiragiEntry    //

    override fun registerOreDict() {
        MaterialRegistry.getMaterials()
            .filter { it.isSolid() && shape.isValid(it) }
            .forEach { OreDictionary.registerOre(shape.getOreDict(it), getItemStack(it)) }
    }

    override fun registerRecipe() {
        MaterialRegistry.getMaterials()
            .filter { it.isSolid() && shape.isValid(it) }
            .forEach { shape.registerRecipe(this, it) }
    }

    @SideOnly(Side.CLIENT)
    override fun registerColorBlock(blockColors: BlockColors) {
        blockColors.registerBlockColorHandler({ state, world, pos, tintIndex ->
            getTile<MaterialTileEntity>(world, pos)?.material?.color ?: -1
        }, this)
    }

    @SideOnly(Side.CLIENT)
    override fun registerColorItem(itemColors: ItemColors) {
        itemColors.registerItemColorHandler({ stack, tintIndex ->
            val material = MaterialRegistry.getMaterial(stack.metadata)
            if (!material.isEmpty() && tintIndex == 0) material.color else -1
        }, this)
    }

    @SideOnly(Side.CLIENT)
    override fun registerModel() = shape.registerModel(this)

}