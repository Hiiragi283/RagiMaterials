package hiiragi283.chemistry

import hiiragi283.api.HiiragiEntry
import hiiragi283.api.block.HiiragiBlock
import hiiragi283.api.item.HiiragiItemBlock
import hiiragi283.chemistry.block.BlockCrucible
import net.minecraft.block.Block
import net.minecraft.client.renderer.color.BlockColors
import net.minecraft.client.renderer.color.ItemColors
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import net.minecraftforge.registries.IForgeRegistry

object RCBlocks : HiiragiEntry.BLOCK {

    override val itemBlock: HiiragiItemBlock? = null

    //val ORE1 = BlockCluster(Material.ROCK)
    @JvmField
    val CRUCIBLE = BlockCrucible

    var list: List<HiiragiBlock> = this::class.java.declaredFields
        .map { it.also { it.isAccessible = true } }
        .map { it.get(this) }
        .filterIsInstance<HiiragiBlock>()

    override fun register(registry: IForgeRegistry<Block>) {
        list.forEach { registry.register(it) }
    }

    override fun registerOreDict() {
        list.forEach { it.registerOreDict() }
    }

    override fun registerRecipe() {
        list.forEach { it.registerRecipe() }
    }

    @SideOnly(Side.CLIENT)
    override fun registerColorBlock(blockColors: BlockColors) {
        list.forEach { it.registerColorBlock(blockColors) }
    }

    @SideOnly(Side.CLIENT)
    override fun registerColorItem(itemColors: ItemColors) {
        list.forEach { it.registerColorItem(itemColors) }
    }

    @SideOnly(Side.CLIENT)
    override fun registerModel() {
        list.forEach { it.registerModel() }
    }

}