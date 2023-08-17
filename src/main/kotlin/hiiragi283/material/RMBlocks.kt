package hiiragi283.material

import hiiragi283.api.HiiragiEntry
import hiiragi283.api.block.createBlockMaterial
import hiiragi283.api.item.HiiragiItemBlock
import hiiragi283.api.shape.HiiragiShapes
import hiiragi283.material.block.BlockCrucible
import hiiragi283.material.block.BlockInventoryTest
import hiiragi283.material.block.BlockOreCluster
import hiiragi283.material.config.RMConfig
import net.minecraft.block.Block
import net.minecraft.client.renderer.color.BlockColors
import net.minecraft.client.renderer.color.ItemColors
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import net.minecraftforge.registries.IForgeRegistry

object RMBlocks : HiiragiEntry.BLOCK {

    override val itemBlock: HiiragiItemBlock? = null

    private val entries: MutableList<HiiragiEntry.BLOCK> = mutableListOf()

    fun getItemBlockEntries(): List<HiiragiEntry.ITEM> = entries.mapNotNull { it.itemBlock }

    //    Material    //

    @JvmField
    val MATERIAL_BLOCK = createBlockMaterial(
        HiiragiShapes.BLOCK,
        recipe = getRecipeBlock()
    )

    //    Common    //

    val TEST = BlockInventoryTest

    @JvmField
    val CRUCIBLE = BlockCrucible

    @JvmField
    val ORE_CLUSTER = BlockOreCluster

    fun init() {
        RagiMaterials.LOGGER.info("RMBlocks has been initialized!")
        if (RMConfig.EXPERIMENTAL.enableMetaTileBlock) entries.add(MATERIAL_BLOCK)
        entries.add(TEST)
        entries.add(CRUCIBLE)
        entries.add(ORE_CLUSTER)
    }

    override fun register(registry: IForgeRegistry<Block>) {
        entries.forEach { registry.register(it.getObject()) }
    }

    override fun registerOreDict() {
        entries.forEach { it.registerOreDict() }
    }

    override fun registerRecipe() {
        entries.forEach { it.registerRecipe() }
    }

    @SideOnly(Side.CLIENT)
    override fun registerColorBlock(blockColors: BlockColors) {
        entries.forEach { it.registerColorBlock(blockColors) }
    }

    @SideOnly(Side.CLIENT)
    override fun registerColorItem(itemColors: ItemColors) {
        entries.forEach { it.registerColorItem(itemColors) }
    }

    @SideOnly(Side.CLIENT)
    override fun registerModel() {
        entries.forEach { it.registerModel() }
    }

}