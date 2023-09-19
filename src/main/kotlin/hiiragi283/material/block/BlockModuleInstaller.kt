package hiiragi283.material.block

import hiiragi283.material.RagiMaterials
import hiiragi283.material.api.block.HiiragiBlock
import hiiragi283.material.api.item.HiiragiItemBlock
import net.minecraft.block.material.Material
import net.minecraft.block.state.IBlockState
import net.minecraft.client.renderer.color.BlockColors
import net.minecraft.client.renderer.color.ItemColors
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.EnumRarity
import net.minecraft.item.ItemStack
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumHand
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.common.IRarity
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

object BlockModuleInstaller : HiiragiBlock(Material.IRON, "module_installer") {

    override val itemBlock: HiiragiItemBlock = object : HiiragiItemBlock(this, 0) {

        override fun getForgeRarity(stack: ItemStack): IRarity = EnumRarity.EPIC

    }

    override fun onBlockActivated(
        world: World,
        pos: BlockPos,
        state: IBlockState,
        player: EntityPlayer,
        hand: EnumHand,
        facing: EnumFacing,
        hitX: Float,
        hitY: Float,
        hitZ: Float
    ): Boolean {
        if (!world.isRemote) openGui(player, world, pos)
        return true
    }

    //    HiiragiEntry    //

    @SideOnly(Side.CLIENT)
    override fun registerBlockColor(blockColors: BlockColors) {
        blockColors.registerBlockColorHandler({ _, _, _, _ -> RagiMaterials.COLOR.rgb }, this)
    }

    override fun registerItemColor(itemColors: ItemColors) {
        itemColors.registerItemColorHandler({ _, _ -> RagiMaterials.COLOR.rgb }, this)
    }

}