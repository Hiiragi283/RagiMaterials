package hiiragi283.material

import hiiragi283.material.api.registry.HiiragiEntry.BLOCK
import hiiragi283.material.api.registry.HiiragiEntry.ITEM
import hiiragi283.material.api.block.createBlockMaterial
import hiiragi283.material.api.item.HiiragiItemBlock
import hiiragi283.material.api.shape.HiiragiShapes
import hiiragi283.material.block.BlockCasing
import hiiragi283.material.block.BlockScaffolding
import hiiragi283.material.config.RMConfig
import net.minecraft.block.Block
import net.minecraft.client.renderer.color.BlockColors
import net.minecraft.client.renderer.color.ItemColors
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import net.minecraftforge.registries.IForgeRegistry

object RMBlocks : BLOCK {

    override val itemBlock: HiiragiItemBlock? = null

    private val entries: MutableList<BLOCK> = mutableListOf()

    fun getItemBlockEntries(): List<ITEM> = entries.mapNotNull { it.itemBlock }

    //    Material    //

    @JvmField
    val MATERIAL_BLOCK = createBlockMaterial(
        HiiragiShapes.BLOCK,
        recipe = getRecipeBlock()
    )

    //    Common    //

    @JvmField
    val CASING = BlockCasing

    @JvmField
    val SCAFFOLDING = BlockScaffolding

    fun init() {
        RagiMaterials.LOGGER.info("RMBlocks has been initialized!")
        if (RMConfig.EXPERIMENTAL.enableMetaTileBlock) entries.add(MATERIAL_BLOCK)
        entries.add(CASING)
        entries.add(SCAFFOLDING)
    }

    override fun register(registry: IForgeRegistry<Block>) {

        entries.forEach { registry.register(it.getObject()) }
        MATERIAL_BLOCK.registerTileEntity()

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