package hiiragi283.material;

import hiiragi283.material.api.item.HiiragiItem;
import hiiragi283.material.api.item.MaterialItem;
import hiiragi283.material.api.material.HiiragiMaterial;
import hiiragi283.material.api.registry.HiiragiEntry;
import hiiragi283.material.api.shape.HiiragiShapes;
import hiiragi283.material.api.shape.ShapeType;
import hiiragi283.material.config.RMConfig;
import hiiragi283.material.item.ItemBookRespawn;
import hiiragi283.material.util.CraftingBuilder;
import hiiragi283.material.util.HiiragiIngredient;
import hiiragi283.material.util.HiiragiUtil;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.ArrayList;
import java.util.List;

public class HiiragiItems implements HiiragiEntry.ITEM {

    public static HiiragiItems INSTANCE = new HiiragiItems();

    private HiiragiItems() {
    }

    private static final List<ITEM> ENTRIES = new ArrayList<>();

    //    Items    //

    public static final HiiragiItem BOOK_RESPAWN = new ItemBookRespawn();

    public static final MaterialItem MATERIAL_BLOCK = MaterialItem.create(
            HiiragiShapes.BLOCK,
            (item, material) -> {
                if (material.isMetal()) {
                    new CraftingBuilder(item.asItemStack(material))
                            .setPattern("AAA", "AAA", "AAA")
                            .setIngredient('A', HiiragiShapes.INGOT.getOreDict(material))
                            .build();
                } else if (material.isGem()) {
                    new CraftingBuilder(item.asItemStack(material))
                            .setPattern("AAA", "AAA", "AAA")
                            .setIngredient('A', HiiragiShapes.GEM.getOreDict(material))
                            .build();
                }
            }
    );

    public static final MaterialItem MATERIAL_BOTTLE = MaterialItem.create(HiiragiShapes.BOTTLE);

    public static final MaterialItem MATERIAL_DUST = MaterialItem.create(
            HiiragiShapes.DUST,
            (item, material) -> new CraftingBuilder(item.asItemStack(material))
                    .setPattern("AAA", "AAA", "AAA")
                    .setIngredient('A', HiiragiShapes.DUST_TINY.getOreDict(material))
                    .build()
    );

    public static final MaterialItem MATERIAL_DUST_TINY = MaterialItem.create(
            HiiragiShapes.DUST_TINY,
            (item, material) -> new CraftingBuilder(item.asItemStack(material, 9))
                    .addIngredient(new HiiragiIngredient(HiiragiShapes.DUST.getOreDict(material)))
                    .build()
    );

    public static final MaterialItem MATERIAL_GEAR = MaterialItem.create(HiiragiShapes.GEAR);

    public static final MaterialItem MATERIAL_GEM = MaterialItem.create(
            HiiragiShapes.GEM,
            item -> {
                ModelLoader.registerItemVariants(item.asItem(),
                        HiiragiUtil.appendPath(item.getLocation(), "_" + ShapeType.GEM_AMORPHOUS.name()),
                        HiiragiUtil.appendPath(item.getLocation(), "_" + ShapeType.GEM_COAL.name()),
                        HiiragiUtil.appendPath(item.getLocation(), "_" + ShapeType.GEM_CUBIC.name()),
                        HiiragiUtil.appendPath(item.getLocation(), "_" + ShapeType.GEM_DIAMOND.name()),
                        HiiragiUtil.appendPath(item.getLocation(), "_" + ShapeType.GEM_EMERALD.name()),
                        HiiragiUtil.appendPath(item.getLocation(), "_" + ShapeType.GEM_LAPIS.name()),
                        HiiragiUtil.appendPath(item.getLocation(), "_" + ShapeType.GEM_QUARTZ.name()),
                        HiiragiUtil.appendPath(item.getLocation(), "_" + ShapeType.GEM_RUBY.name())
                );
                ModelLoader.setCustomMeshDefinition(item.asItem(), stack -> {
                    var shapeType = HiiragiMaterial.REGISTRY_INDEX.getValue(stack.getMetadata()).shapeType();
                    return new ModelResourceLocation(HiiragiUtil.appendPath(item.getLocation(), "_" + shapeType.name()), "inventory");
                });
            },
            (item, material) -> new CraftingBuilder(item.asItemStack(material, 9))
                    .addIngredient(new HiiragiIngredient(HiiragiShapes.BLOCK.getOreDict(material)))
                    .build()
    );

    public static final MaterialItem MATERIAL_INGOT = MaterialItem.create(
            HiiragiShapes.INGOT,
            (item, material) -> {
                new CraftingBuilder(item.asItemStack(material))
                        .setPattern("AAA", "AAA", "AAA")
                        .setIngredient('A', HiiragiShapes.NUGGET.getOreDict(material))
                        .build();
                ItemStack ingot9 = item.asItemStack(material, 9);
                new CraftingBuilder(HiiragiUtil.appendPath(HiiragiUtil.toLocation(ingot9, '_'), "_alt"), ingot9)
                        .addIngredient(new HiiragiIngredient(HiiragiShapes.BLOCK.getOreDict(material)))
                        .build();
            }
    );

    public static final MaterialItem MATERIAL_NUGGET = MaterialItem.create(
            HiiragiShapes.NUGGET,
            (item, material) -> new CraftingBuilder(item.asItemStack(material, 9))
                    .addIngredient(new HiiragiIngredient(HiiragiShapes.INGOT.getOreDict(material)))
                    .build()
    );

    public static final MaterialItem MATERIAL_PLATE = MaterialItem.create(HiiragiShapes.PLATE);

    public static final MaterialItem MATERIAL_STICK = MaterialItem.create(HiiragiShapes.STICK);

    public static final MaterialItem MATERIAL_WIRE = MaterialItem.create(HiiragiShapes.WIRE);

    //    Registry    //

    public static void init() {
        RagiMaterials.LOGGER.info("HiiragiItems has been initialized!");
        ENTRIES.addAll(HiiragiBlocks.getItemBlocks());
        ENTRIES.add(BOOK_RESPAWN);
        if (!RMConfig.EXPERIMENTAL.enableMetaTileBlock) ENTRIES.add(MATERIAL_BLOCK);
        ENTRIES.add(MATERIAL_BOTTLE);
        ENTRIES.add(MATERIAL_DUST);
        ENTRIES.add(MATERIAL_DUST_TINY);
        ENTRIES.add(MATERIAL_GEAR);
        ENTRIES.add(MATERIAL_GEM);
        ENTRIES.add(MATERIAL_INGOT);
        ENTRIES.add(MATERIAL_NUGGET);
        ENTRIES.add(MATERIAL_PLATE);
        ENTRIES.add(MATERIAL_STICK);
    }

    @Override
    public void register(IForgeRegistry<Item> registry) {
        ENTRIES.forEach(item -> registry.register(item.getObject()));
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
    public void registerItemColor(ItemColors itemColors) {
        ENTRIES.forEach(item -> item.registerItemColor(itemColors));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerModel() {
        ENTRIES.forEach(HiiragiEntry::registerModel);
    }

}