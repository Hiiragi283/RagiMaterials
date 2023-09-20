package hiiragi283.material.api.block

import hiiragi283.material.HiiragiCreativeTabs
import hiiragi283.material.HiiragiGuiHandler
import hiiragi283.material.RMReference
import hiiragi283.material.RagiMaterials
import hiiragi283.material.api.item.HiiragiItemBlock
import hiiragi283.material.api.registry.HiiragiEntry
import net.minecraft.block.Block
import net.minecraft.block.material.Material
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.RayTraceResult
import net.minecraft.world.World
import java.util.*

abstract class HiiragiBlock(material: Material, id: String) : Block(material), HiiragiEntry.BLOCK {

    init {
        blockHardness = 5.0f
        blockResistance = 5.0f
        creativeTab = HiiragiCreativeTabs.COMMON
        setRegistryName(RMReference.MOD_ID, id)
        translationKey = id
    }

    override val itemBlock: HiiragiItemBlock = HiiragiItemBlock(this, 0)

    fun openGui(player: EntityPlayer, world: World, pos: BlockPos) {
        if (!world.isRemote) player.openGui(
            RagiMaterials.Instance,
            HiiragiGuiHandler.BLOCK,
            world,
            pos.x,
            pos.y,
            pos.z
        )
    }

    //    Block    //

    override fun getItemDropped(state: IBlockState, rand: Random, fortune: Int): Item = asItem()

    override fun getPickBlock(
        state: IBlockState,
        target: RayTraceResult,
        world: World,
        pos: BlockPos,
        player: EntityPlayer
    ): ItemStack = ItemStack(itemBlock)

}