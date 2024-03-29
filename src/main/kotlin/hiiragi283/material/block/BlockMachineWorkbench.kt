package hiiragi283.material.block

import hiiragi283.material.RagiMaterials
import hiiragi283.material.api.block.HiiragiBlock
import hiiragi283.material.api.item.HiiragiItemBlock
import hiiragi283.material.init.HiiragiCreativeTabs
import hiiragi283.material.init.HiiragiShapes
import hiiragi283.material.init.materials.MaterialCommons
import hiiragi283.material.util.CraftingBuilder
import hiiragi283.material.util.SimpleColorProvider
import hiiragi283.material.util.itemStack
import net.minecraft.block.material.Material
import net.minecraft.block.state.IBlockState
import net.minecraft.client.renderer.color.IBlockColor
import net.minecraft.client.renderer.color.IItemColor
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.init.Blocks
import net.minecraft.item.EnumDyeColor
import net.minecraft.item.EnumRarity
import net.minecraft.item.ItemStack
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumHand
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.common.IRarity
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

object BlockMachineWorkbench : HiiragiBlock(Material.IRON, "machine_workbench") {

    override val itemBlock: HiiragiItemBlock = object : HiiragiItemBlock(this) {

        override fun getForgeRarity(stack: ItemStack): IRarity = EnumRarity.EPIC

    }

    init {
        creativeTab = HiiragiCreativeTabs.MACHINE
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

    override fun registerRecipe() {
        CraftingBuilder(itemStack())
            .setPattern("AAA", "BCB", "DDD")
            .setIngredient('A', Blocks.CONCRETE.itemStack(meta = EnumDyeColor.BLACK.metadata))
            .setIngredient('B', "dyeRed")
            .setIngredient('C', "workbench")
            .setIngredient('D', HiiragiShapes.PLATE.getOreDict(MaterialCommons.STEEL))
            .build()
    }

    @SideOnly(Side.CLIENT)
    override fun getBlockColor(): IBlockColor = SimpleColorProvider(RagiMaterials.COLOR)

    @SideOnly(Side.CLIENT)
    override fun getItemColor(): IItemColor = SimpleColorProvider(RagiMaterials.COLOR)

}