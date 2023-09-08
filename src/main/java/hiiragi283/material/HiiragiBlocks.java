package hiiragi283.material;

import hiiragi283.material.api.block.MaterialBlock;
import hiiragi283.material.api.block.MaterialBlockCasing;
import hiiragi283.material.api.item.HiiragiItemBlock;
import hiiragi283.material.api.registry.HiiragiEntry;
import hiiragi283.material.api.shape.HiiragiShapes;
import hiiragi283.material.block.BlockModuleInstaller;
import hiiragi283.material.block.BlockTestMachine;
import hiiragi283.material.config.RMConfig;
import hiiragi283.material.util.CraftingBuilder;
import hiiragi283.material.util.HiiragiUtil;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class HiiragiBlocks implements HiiragiEntry.BLOCK {

    public static HiiragiBlocks INSTANCE = new HiiragiBlocks();

    private HiiragiBlocks() {
    }

    private static final List<BLOCK> ENTRIES = new ArrayList<>();

    public static List<HiiragiEntry.ITEM> getItemBlocks() {
        return ENTRIES.stream()
                .map(HiiragiEntry.BLOCK::getItemBlock)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    //    Blocks    //

    public static final MaterialBlock MATERIAL_BLOCK = MaterialBlock.create(
            HiiragiShapes.BLOCK,
            (block, material) -> {
                if (material.isMetal()) {
                    new CraftingBuilder(block.asItemStack(material))
                            .setPattern("AAA", "AAA", "AAA")
                            .setIngredient('A', HiiragiShapes.INGOT.getOreDict(material))
                            .build();
                } else if (material.isGem()) {
                    new CraftingBuilder(block.asItemStack(material))
                            .setPattern("AAA", "AAA", "AAA")
                            .setIngredient('A', HiiragiShapes.GEM.getOreDict(material))
                            .build();
                }
            }
    );

    public static final MaterialBlock MATERIAL_CASING = new MaterialBlockCasing();

    public static final MaterialBlock MATERIAL_FRAME = MaterialBlock.create(
            HiiragiShapes.FRAME,
            (block, material) -> new CraftingBuilder(block.asItemStack(material, 2))
                    .setPattern("AAA", "A A", "AAA")
                    .setIngredient('A', HiiragiShapes.STICK.getOreDict(material))
                    .build()
    );

    public static final BlockTestMachine TEST_MACHINE = new BlockTestMachine();

    public static final BlockModuleInstaller MODULE_INSTALLER = new BlockModuleInstaller();

    //    Registry    //

    public static void init() {
        RagiMaterials.LOGGER.info("HiiragiBlocks has been initialized!");
        if (RMConfig.EXPERIMENTAL.enableMetaTileBlock) {
            ENTRIES.add(MATERIAL_BLOCK);
            ENTRIES.add(MATERIAL_CASING);
            ENTRIES.add(MATERIAL_FRAME);
            MATERIAL_BLOCK.registerTileEntity();
        }
        if (HiiragiUtil.isDeobf()) {
            ENTRIES.add(TEST_MACHINE);
            ENTRIES.add(MODULE_INSTALLER);
            TEST_MACHINE.registerTileEntity();
            MODULE_INSTALLER.registerTileEntity();
        }
    }

    @Override
    public @Nullable HiiragiItemBlock getItemBlock() {
        return null;
    }

    @Override
    public void register(IForgeRegistry<Block> registry) {
        ENTRIES.forEach(block -> registry.register(block.getObject()));
    }

    @Override
    public void registerOreDict() {
        ENTRIES.forEach(HiiragiEntry::registerOreDict);
    }

    @Override
    public void registerRecipe() {
        ENTRIES.forEach(HiiragiEntry::registerRecipe);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockColor(BlockColors blockColors) {
        ENTRIES.forEach(block -> block.registerBlockColor(blockColors));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerItemColor(ItemColors itemColors) {
        ENTRIES.forEach(block -> block.registerItemColor(itemColors));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerModel() {
        ENTRIES.forEach(HiiragiEntry::registerModel);
    }

}