package hiiragi283.material.api.block

import hiiragi283.material.HiiragiCreativeTabs
import hiiragi283.material.api.item.MaterialItemBlock
import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.api.registry.HiiragiEntry
import hiiragi283.material.api.registry.HiiragiRegistries
import hiiragi283.material.api.shape.HiiragiShape
import hiiragi283.material.api.tile.MaterialTileEntity
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

open class MaterialBlock(
    val shape: HiiragiShape,
    val model: (HiiragiEntry<*>) -> Unit = { entry -> entry.asItem().setModelSame() },
    val recipe: (HiiragiEntry<*>, HiiragiMaterial) -> Unit = { _, _ -> }
) : HiiragiBlockContainer.Holdable<MaterialTileEntity>(
    Material.IRON,
    shape.name,
    { MaterialTileEntity() }
) {

    override val itemBlock = MaterialItemBlock(this)

    init {
        creativeTab = HiiragiCreativeTabs.MATERIAL_BLOCK
        soundType = SoundType.METAL
        HiiragiRegistries.MATERIAL_BLOCK.register(shape, this)
    }

    //    General    //

    override fun getDrops(
        drops: NonNullList<ItemStack>,
        world: IBlockAccess,
        pos: BlockPos,
        state: IBlockState,
        fortune: Int
    ) {
        drops.add(getItemStack(getTile<MaterialTileEntity>(world, pos)?.material))
    }

    override fun getPickBlock(
        state: IBlockState,
        target: RayTraceResult,
        world: World,
        pos: BlockPos,
        player: EntityPlayer
    ): ItemStack = getItemStack(getTile<MaterialTileEntity>(world, pos)?.material)

    override fun quantityDropped(random: Random): Int = 0

    //    HiiragiEntry    //

    override fun registerOreDict() {
        HiiragiRegistries.MATERIAL.getValues()
            .filter(shape::isValid)
            .forEach { material -> OreDictionary.registerOre(shape.getOreDict(material), getItemStack(material)) }
    }

    override fun registerRecipe() {
        HiiragiRegistries.MATERIAL.getValues()
            .filter(shape::isValid)
            .forEach { material -> recipe(this, material) }
    }

    @SideOnly(Side.CLIENT)
    override fun registerBlockColor(blockColors: BlockColors) {
        blockColors.registerBlockColorHandler({ _: IBlockState, world: IBlockAccess?, pos: BlockPos?, _: Int ->
            getTile<MaterialTileEntity>(world, pos)?.material?.color ?: -1
        }, this)
    }

    @SideOnly(Side.CLIENT)
    override fun registerItemColor(itemColors: ItemColors) {
        itemColors.registerItemColorHandler({ stack, tintIndex ->
            if (tintIndex == 0) HiiragiRegistries.MATERIAL_INDEX.getValue(stack.metadata)?.color ?: -1 else -1
        }, this)
    }

    @SideOnly(Side.CLIENT)
    override fun registerModel() = model(this)

}