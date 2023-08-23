package hiiragi283.api.block

import hiiragi283.api.HiiragiEntry
import hiiragi283.api.HiiragiRegistry
import hiiragi283.api.item.ItemBlockMaterial
import hiiragi283.api.material.HiiragiMaterial
import hiiragi283.api.shape.HiiragiShape
import hiiragi283.api.tile.MaterialTileEntity
import hiiragi283.material.RMCreativeTabs
import hiiragi283.material.util.getTile
import hiiragi283.material.util.setModelSame
import net.minecraft.block.SoundType
import net.minecraft.block.material.Material
import net.minecraft.block.state.IBlockState
import net.minecraft.client.renderer.color.BlockColors
import net.minecraft.client.renderer.color.ItemColors
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.util.NonNullList
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.RayTraceResult
import net.minecraft.world.IBlockAccess
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import net.minecraftforge.oredict.OreDictionary
import java.util.*
import java.util.function.BiConsumer
import java.util.function.Consumer

fun createBlockMaterial(
    shape: HiiragiShape,
    model: Consumer<HiiragiEntry<*>> = Consumer { it.asItem().setModelSame() },
    recipe: BiConsumer<HiiragiEntry<*>, HiiragiMaterial> = BiConsumer { _, _ -> }
): BlockMaterial = object : BlockMaterial(shape) {

    init {
        setHarvestLevel("pickaxe", 0)
    }

    override fun getRecipe(entry: HiiragiEntry<*>, material: HiiragiMaterial) {
        recipe.accept(entry, material)
    }

    override fun getModel(entry: HiiragiEntry<*>) {
        model.accept(entry)
    }

}

abstract class BlockMaterial(val shape: HiiragiShape) : HiiragiBlockContainer.Holdable<MaterialTileEntity>(
    Material.IRON,
    shape.name,
    MaterialTileEntity::class.java
) {

    override val itemBlock = ItemBlockMaterial(this)

    init {
        blockHardness = 5.0f
        blockResistance = 5.0f
        creativeTab = RMCreativeTabs.MATERIAL_BLOCK
        soundType = SoundType.METAL
    }

    //    HiiragiBlock    //

    override fun getDrops(
        drops: NonNullList<ItemStack>,
        world: IBlockAccess,
        pos: BlockPos,
        state: IBlockState,
        fortune: Int
    ) {
        drops.add(getItemStack(getTile<MaterialTileEntity>(world, pos)?.material ?: HiiragiMaterial.EMPTY))
    }

    override fun getPickBlock(
        state: IBlockState,
        target: RayTraceResult,
        world: World,
        pos: BlockPos,
        player: EntityPlayer
    ): ItemStack {
        return getItemStack(getTile<MaterialTileEntity>(world, pos)?.material ?: HiiragiMaterial.EMPTY)
    }

    override fun quantityDropped(random: Random): Int = 0

    //    HiiragiEntry    //

    override fun registerOreDict() {
        HiiragiRegistry.getMaterials()
            .filter { it.isSolid() && shape.isValid(it) }
            .forEach { OreDictionary.registerOre(shape.getOreDict(it), getItemStack(it)) }
    }

    override fun registerRecipe() {
        HiiragiRegistry.getMaterials()
            .filter { it.isSolid() && shape.isValid(it) }
            .forEach { getRecipe(this, it) }
    }

    abstract fun getRecipe(entry: HiiragiEntry<*>, material: HiiragiMaterial)

    @SideOnly(Side.CLIENT)
    override fun registerColorBlock(blockColors: BlockColors) {
        blockColors.registerBlockColorHandler({ state, world, pos, tintIndex ->
            getTile<MaterialTileEntity>(world, pos)?.material?.color ?: -1
        }, this)
    }

    @SideOnly(Side.CLIENT)
    override fun registerColorItem(itemColors: ItemColors) {
        itemColors.registerItemColorHandler({ stack, tintIndex ->
            val material = HiiragiRegistry.getMaterial(stack.metadata)
            if (!material.isEmpty() && tintIndex == 0) material.color else -1
        }, this)
    }

    @SideOnly(Side.CLIENT)
    override fun registerModel() = getModel(this)

    abstract fun getModel(entry: HiiragiEntry<*>)

}