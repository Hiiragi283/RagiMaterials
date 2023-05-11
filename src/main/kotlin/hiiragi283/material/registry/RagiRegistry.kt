package hiiragi283.material.registry

import hiiragi283.material.RagiMaterials
import hiiragi283.material.base.BlockBase
import hiiragi283.material.base.ItemBase
import hiiragi283.material.base.ItemBlockBase
import hiiragi283.material.client.color.IRagiColored
import hiiragi283.material.client.model.ICustomModel
import hiiragi283.material.client.model.ModelManager
import hiiragi283.material.item.ItemMaterial
import hiiragi283.material.material.part.MaterialPart
import hiiragi283.material.material.part.PartRegistry
import hiiragi283.material.util.RagiColor
import net.minecraft.block.Block
import net.minecraft.item.Item
import net.minecraft.util.ResourceLocation
import net.minecraftforge.client.event.ColorHandlerEvent
import net.minecraftforge.client.event.ModelRegistryEvent
import net.minecraftforge.event.RegistryEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.common.registry.ForgeRegistries
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

object RagiRegistry {

    fun getPart(part: MaterialPart) = ForgeRegistries.ITEMS.getValue(ResourceLocation(RagiMaterials.MOD_ID, part.name)) ?: ITEM_INGOT

    //    Collection    //

    private lateinit var blocks: Set<BlockBase>
    private lateinit var items: Set<ItemBase>

    //    Item    //

    val ITEM_DUST = ItemMaterial(PartRegistry.DUST)
    val ITEM_INGOT = ItemMaterial(PartRegistry.INGOT)

    //    Registration    //

    fun load() {

        val blocks: MutableSet<BlockBase> = mutableSetOf()
        val items: MutableSet<ItemBase> = mutableSetOf()

        for (field in this::class.java.declaredFields) {
            field.isAccessible = true
            when (val obj = field.get(this)) {
                is BlockBase -> blocks.add(obj)
                is ItemBase -> items.add(obj)
                else -> {}
            }
        }

        this.blocks = blocks
        this.items = items

    }

    fun registerOreDict() {
        for (item in items) {
            item.registerOreDict()
        }
    }

    fun registerRecipe() {
        for (item in items) {
            item.registerRecipe()
        }
    }

    object CommonEvent {

        @SubscribeEvent
        fun registerBlocks(event: RegistryEvent.Register<Block>) {
            val registry = event.registry
            for (block in blocks) {
                block.register(registry)
            }
        }

        @SubscribeEvent
        fun registerItems(event: RegistryEvent.Register<Item>) {
            val registry = event.registry
            for (item in items) {
                item.register(registry)
            }
        }
    }

    @SideOnly(Side.CLIENT)
    object ClientEvent {

        @SubscribeEvent
        fun registerModel(event: ModelRegistryEvent) {
            //モデルの自動登録
            for (block in blocks) {
                if (block is ICustomModel) block.registerModel() else ModelManager.setModel(block)
                RagiMaterials.LOGGER.debug("The model for item block ${block.registryName} is registered!")
            }
            for (item in items) {
                if (item is ICustomModel) item.registerModel() else ModelManager.setModel(item)
                RagiMaterials.LOGGER.debug("The model for item ${item.registryName} is registered!")
            }
        }

        @SubscribeEvent
        fun registerColor(event: ColorHandlerEvent.Item) {

            val blockColors = event.blockColors
            val itemColors = event.itemColors

            //Block
            blockColors.registerBlockColorHandler({ state, world, pos, tintIndex ->
                val block = state.block
                if (world !== null && pos !== null && block is IRagiColored.Block) block.getColor(
                    state,
                    world,
                    pos,
                    tintIndex
                ).rgb else RagiColor.WHITE.rgb
            }, *blocks.toTypedArray())

            //ItemBlock
            itemColors.registerItemColorHandler({ stack, tintIndex ->
                val item = stack.item as ItemBlockBase
                val block = item.block
                if (block is IRagiColored.Block) block.getColor(stack, tintIndex).rgb else RagiColor.WHITE.rgb
            }, *blocks.toTypedArray())

            //Item
            itemColors.registerItemColorHandler({ stack, tintIndex ->
                val item = stack.item
                if (item is IRagiColored.Item) item.getColor(stack, tintIndex).rgb else RagiColor.WHITE.rgb
            }, *items.toTypedArray())
        }
    }
}