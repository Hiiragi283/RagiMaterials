package hiiragi283.material

import hiiragi283.api.HiiragiEntry
import hiiragi283.api.block.createBlockMaterial
import hiiragi283.api.item.HiiragiItemBlock
import hiiragi283.api.shape.HiiragiShapes
import hiiragi283.material.block.*
import hiiragi283.material.config.RMConfig
import hiiragi283.material.util.hiiragiLocation
import hiiragi283.material.util.isDeobfEnv
import net.minecraft.block.Block
import net.minecraft.client.renderer.color.BlockColors
import net.minecraft.client.renderer.color.ItemColors
import net.minecraftforge.fml.common.registry.GameRegistry
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

    @JvmField
    val CRUCIBLE = BlockCrucible

    @JvmField
    val ROCK_GENERATOR = BlockRockGenerator

    @JvmField
    val SCAFFOLDING = BlockMaterialScaffolding

    @JvmField
    val STONE_COMMON = BlockStoneCommon

    fun init() {
        RagiMaterials.LOGGER.info("RMBlocks has been initialized!")
        if (RMConfig.EXPERIMENTAL.enableMetaTileBlock) entries.add(MATERIAL_BLOCK)
        if (isDeobfEnv()) {
            entries.add(BlockInventoryTest)
            entries.add(SCAFFOLDING)
            entries.add(ROCK_GENERATOR)
        }
        entries.add(CRUCIBLE)
    }

    override fun register(registry: IForgeRegistry<Block>) {

        entries.forEach { registry.register(it.getObject()) }

        listOf(
            BlockInventoryTest,
            CRUCIBLE,
            MATERIAL_BLOCK,
            ROCK_GENERATOR
        )
            .map { it.tile to hiiragiLocation("te_${it.registryName!!.path}") }
            .forEach { GameRegistry.registerTileEntity(it.first, it.second) }

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