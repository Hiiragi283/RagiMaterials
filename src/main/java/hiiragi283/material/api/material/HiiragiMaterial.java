package hiiragi283.material.api.material;


import com.github.bsideup.jabel.Desugar;
import com.google.common.base.CaseFormat;
import hiiragi283.material.api.event.HiiragiEventFactory;
import hiiragi283.material.api.event.HiiragiRegistryEvent;
import hiiragi283.material.api.registry.HiiragiRegistry;
import hiiragi283.material.api.shape.HiiragiShape;
import hiiragi283.material.api.shape.HiiragiShapes;
import hiiragi283.material.api.shape.ShapeType;
import hiiragi283.material.util.HiiragiCollectors;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.common.MinecraftForge;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * An object which contains several properties of material
 * <p>
 * Should be registered in
 * <p>
 * === Index Range ===
 * <p>
 * <= 0 ... Not registered
 * <p>
 * 1 ~ 118 ... Periodic Table
 * <p>
 * 120 ~ 199 ... Isotopes
 * <p>
 * 1000 ~ 1900 ... Integration for other mods
 * <p>
 * 10010 ~ 11899 ... Compounds: 1 + Atomic Number + Index
 * <p>
 * >= 32768 ... Not registered
 *
 * @param name           should be unique
 * @param index          follows above format
 * @param color          see also [hiiragi283.material.util.HiiragiColor]
 * @param formula        empty value will be ignored
 * @param molar          0 or less value will be ignored
 * @param tempBoil       boiling point with kelvin Temperature, 0 or less will be ignored
 * @param tempMelt       melting point with kelvin Temperature, 0 or less will be ignored
 * @param translationKey can be overridden
 */
@Desugar
public record HiiragiMaterial(
        String name,
        int index,
        int color,
        String formula,
        double molar,
        List<String> oreDictAlt,
        ShapeType shapeType,
        int tempBoil,
        int tempMelt,
        String translationKey
) {

    public static final HiiragiMaterial EMPTY = create("empty", 0, builder -> {
    });

    public HiiragiMaterial addBracket() {
        return copyAndEdit(builder -> builder.formula = "(" + this.formula + ")");
    }

    public void addTooltip(List<String> tooltip, HiiragiShape shape) {
        addTooltip(tooltip, shape.getTranslatedName(this), shape.scale());
    }

    public void addTooltip(List<String> tooltip, String name, int scale) {
        if (isEmpty()) return;
        tooltip.add(I18n.format("tips.ragi_materials.property.name", name));
        tooltip.add("§e=== Property ===");
        if (hasFormula())
            tooltip.add(I18n.format("tips.ragi_materials.property.formula", formula));
        if (hasMolar())
            tooltip.add(I18n.format("tips.ragi_materials.property.mol", molar));
        if (scale > 0)
            tooltip.add(I18n.format("tips.ragi_materials.property.scale", scale));
        if (hasTempMelt())
            tooltip.add(I18n.format("tips.ragi_materials.property.melt", tempMelt));
        if (hasTempBoil())
            tooltip.add(I18n.format("tips.ragi_materials.property.boil", tempBoil));
    }

    public String getOreDictName() {
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, name);
    }

    public String getTranslatedName() {
        return I18n.format(translationKey);
    }

    public boolean hasFormula() {
        return formula.isEmpty();
    }

    public boolean hasMolar() {
        return molar > 0.0;
    }

    public boolean hasTempBoil() {
        return tempBoil > 0;
    }

    public boolean hasTempMelt() {
        return tempMelt > 0;
    }

    public boolean isIndexValid() {
        return index > 0 && index <= 32767;
    }

    public boolean isEmpty() {
        return Objects.equals(this, EMPTY);
    }

    public boolean isLiquid() {
        return HiiragiShapes.LIQUID.isValid(this);
    }

    public boolean isFluid() {
        return isGas() || isLiquid();
    }

    public boolean isGas() {
        return HiiragiShapes.GAS.isValid(this);
    }

    public boolean isGem() {
        return HiiragiShapes.GEM.isValid(this);
    }

    public boolean isMetal() {
        return HiiragiShapes.METAL.isValid(this);
    }

    public boolean isSolid() {
        return HiiragiShapes.SOLID.isValid(this);
    }


    //    General    //

    public HiiragiMaterial copy() {
        return new HiiragiMaterial(name, index, color, formula, molar, oreDictAlt, shapeType, tempBoil, tempMelt, translationKey);
    }

    public HiiragiMaterial copyAndEdit(Consumer<Builder> edit) {
        var builder = new Builder(name, index);
        builder.color = this.color;
        builder.formula = this.formula;
        builder.molar = this.molar;
        builder.oreDictAlt = this.oreDictAlt;
        builder.shapeType = this.shapeType;
        builder.tempBoil = this.tempBoil;
        builder.tempMelt = this.tempMelt;
        builder.translationKey = this.translationKey;
        edit.accept(builder);
        return builder.build();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof HiiragiMaterial)) return false;
        return Objects.equals(((HiiragiMaterial) obj).name, this.name);
    }

    @Override
    public String toString() {
        return "Material:" + name;
    }

    //    Registry    //

    public static HiiragiRegistry<String, HiiragiMaterial> REGISTRY = new HiiragiRegistry<>("Material", EMPTY);

    public static HiiragiRegistry<Integer, HiiragiMaterial> REGISTRY_INDEX = new HiiragiRegistry<>("Material Index", EMPTY);

    public static void registerMaterials() {

        var event = new HiiragiRegistryEvent.Material(HiiragiMaterial.REGISTRY);
        MinecraftForge.EVENT_BUS.post(event);
        HiiragiMaterial.REGISTRY.setUnmodifiable();

        HiiragiMaterial.REGISTRY.getValues().forEach(material -> HiiragiMaterial.REGISTRY_INDEX.register(material.index(), material));
        HiiragiMaterial.REGISTRY_INDEX.setUnmodifiable();

    }

    //    Material    //

    public static HiiragiMaterial create(String name, int index, Consumer<Builder> consumer) {
        var builder = new Builder(name, index);
        consumer.accept(builder);
        HiiragiEventFactory.onMaterialCreate(builder);
        return builder.build();
    }

    //    Isotope    //

    public static HiiragiMaterial createIsotope(String name, int index, HiiragiMaterial parent, Consumer<Builder> consumer) {
        var builder = new Builder(name, index, parent);
        consumer.accept(builder);
        HiiragiEventFactory.onMaterialCreate(builder);
        return builder.build();
    }

    //    Compound    //

    public static HiiragiMaterial createCompound(String name, int index, Map<HiiragiMaterial, Integer> components, Consumer<Builder> consumer) {
        var builder = new Builder(name, index);
        initCompound(builder, components);
        consumer.accept(builder);
        HiiragiEventFactory.onMaterialCreate(builder);
        return builder.build();
    }

    private static void initCompound(Builder builder, Map<HiiragiMaterial, Integer> components) {
        initColor(builder, components);
        initFormula(builder, components);
        initMolar(builder, components);
    }

    private static void initColor(Builder builder, Map<HiiragiMaterial, Integer> components) {
        builder.color = components.entrySet().stream().collect(HiiragiCollectors.MATERIAL_COLOR_COLLECTOR).getRGB();
    }

    private static void initFormula(Builder builder, Map<HiiragiMaterial, Integer> components) {
        builder.formula = components.entrySet().stream()
                .filter(entry -> entry.getKey().hasFormula())
                .collect(HiiragiCollectors.FORMULA_COLLECTOR);
    }

    private static void initMolar(Builder builder, Map<HiiragiMaterial, Integer> components) {
        builder.molar = components.entrySet().stream()
                .filter(entry -> entry.getKey().hasMolar())
                .collect(HiiragiCollectors.MOLAR_COLLECTOR);
    }

    //    Alloy    //

    private static void initTempBoil(Builder builder, Map<HiiragiMaterial, Integer> components) {
        builder.tempMelt = components.entrySet().stream()
                .filter(entry -> entry.getKey().hasTempBoil())
                .collect(HiiragiCollectors.TEMP_BOIL_COLLECTOR);
    }

    private static void initTempMelt(Builder builder, Map<HiiragiMaterial, Integer> components) {
        builder.tempMelt = components.entrySet().stream()
                .filter(entry -> entry.getKey().hasTempMelt())
                .collect(HiiragiCollectors.TEMP_MELT_COLLECTOR);
    }

    //    Builder    //

    public static class Builder {

        public final String name;
        public final int index;
        public int color = 0xFFFFFF;
        public String formula = "";
        public double molar = 0.0;
        public List<String> oreDictAlt = new ArrayList<>();
        public ShapeType shapeType = ShapeType.INTERNAL;
        public int tempBoil = 0;
        public int tempMelt = 0;
        public String translationKey;

        private Builder(String name, int index, HiiragiMaterial parent) {
            this(name, index);
            this.color = parent.color();
            this.shapeType = parent.shapeType();
            this.tempBoil = parent.tempBoil();
            this.tempMelt = parent.tempMelt();
        }

        private Builder(String name, int index) {
            this.name = name;
            this.index = index;
            translationKey = "hiiragi_material." + name;
        }

        public HiiragiMaterial build() {
            return new HiiragiMaterial(name, index, color, formula, molar, oreDictAlt, shapeType, tempBoil, tempMelt, translationKey);
        }

    }

}