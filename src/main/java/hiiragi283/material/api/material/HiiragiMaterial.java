package hiiragi283.material.api.material;

import com.google.common.base.CaseFormat;
import hiiragi283.material.api.event.HiiragiRegistryEvent;
import hiiragi283.material.api.fluid.MaterialFluidNew;
import hiiragi283.material.api.machine.IMachineProperty;
import hiiragi283.material.api.part.HiiragiPart;
import hiiragi283.material.api.registry.HiiragiRegistry;
import hiiragi283.material.api.shape.HiiragiShape;
import hiiragi283.material.api.shape.HiiragiShapes;
import hiiragi283.material.api.shape.ShapeType;
import hiiragi283.material.util.HiiragiColor;
import hiiragi283.material.util.HiiragiUtil;
import net.minecraft.block.Block;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fluids.Fluid;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * An object which contains several properties of material
 */
public class HiiragiMaterial {

    /**
     * should be unique
     */
    @NotNull
    public final String name;

    /**
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
     */
    public final int index;

    /**
     * see also {@link HiiragiColor}
     */
    public int color = 0xFFFFFF;

    /**
     * provides fluid block, set null if this material has no fluid block
     */
    @Nullable
    protected Block fluidBlock = null;

    public Optional<Block> getFluidBlock() {
        return Optional.ofNullable(fluidBlock);
    }

    /**
     * provides fluid, set null if this material has no fluid
     */
    @Nullable
    protected Fluid fluid;

    public Optional<Fluid> getFluid() {
        return Optional.ofNullable(fluid);
    }

    /**
     * empty value will be ignored
     */
    @NotNull
    public String formula = "";

    /**
     * provides machine property, set null if this material cannot build machine
     */
    @Nullable
    protected IMachineProperty machineProperty = null;

    public Optional<IMachineProperty> getMachineProperty() {
        return Optional.ofNullable(machineProperty);
    }

    /**
     * 0 or less value will be ignored
     */
    public double molar = 0.0;

    @NotNull
    public final List<String> oreDictAlt = new ArrayList<>();

    /**
     * define
     */
    @NotNull
    public ShapeType shapeType = ShapeType.INTERNAL;

    /**
     * boiling point with kelvin Temperature, 0 or less will be ignored
     */
    public int tempBoil = 0;

    /**
     * melting point with kelvin Temperature, 0 or less will be ignored
     */
    public int tempMelt = 0;

    /**
     * initialized in constructor but can be overridden
     */
    @NotNull
    public String translationKey;

    public HiiragiMaterial(@NotNull String name, int index) {
        this.name = name;
        this.index = index;
        this.translationKey = "hiiragi_material." + name;
        this.fluid = new MaterialFluidNew(this); //must be last!
    }

    public HiiragiMaterial copy() {
        return copy(HiiragiUtil.getEmptyConsumer());
    }

    public HiiragiMaterial copy(Consumer<HiiragiMaterial> consumer) {
        var material = new HiiragiMaterial(name, index);
        material.color = this.color;
        material.fluidBlock = this.fluidBlock;
        material.formula = this.formula;
        material.machineProperty = this.machineProperty;
        material.molar = this.molar;
        material.oreDictAlt.addAll(this.oreDictAlt);
        material.shapeType = this.shapeType;
        material.tempBoil = this.tempBoil;
        material.tempMelt = this.tempMelt;
        material.translationKey = this.translationKey;
        consumer.accept(material);
        return material;
    }

    //    Utils    //

    public HiiragiMaterial addBracket() {
        return copy(material -> material.formula = "(" + this.formula + ")");
    }

    public void addTooltip(List<String> tooltip, HiiragiShape shape) {
        addTooltip(tooltip, shape.getTranslatedName(this), shape.scale());
    }

    public void addTooltip(List<String> tooltip, String name, int scale) {
        tooltip.add(I18n.format("tips.ragi_materials.property.name", name));
        tooltip.add("§e=== Material Property ===");
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

    public List<ItemStack> getAllItemStack() {
        return HiiragiShape.REGISTRY.getValues().stream()
                .flatMap(shape -> new HiiragiPart(shape, this).getItemStacks().stream())
                .collect(Collectors.toList());
    }

    public String getOreDictName() {
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, name);
    }

    public String getTranslatedName() {
        return I18n.format(translationKey);
    }

    public boolean hasFormula() {
        return !formula.isEmpty();
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

    public MaterialStack toMaterialStack() {
        return toMaterialStack(144);
    }

    public MaterialStack toMaterialStack(int amount) {
        return new MaterialStack(this, amount);
    }

    //    Registry    //

    public static HiiragiRegistry<String, HiiragiMaterial> REGISTRY = new HiiragiRegistry<>("Material");

    public static HiiragiRegistry<Integer, HiiragiMaterial> REGISTRY_INDEX = new HiiragiRegistry<>("Material Index");

    public static void registerMaterials() {

        var event = new HiiragiRegistryEvent.Material(HiiragiMaterial.REGISTRY);
        MinecraftForge.EVENT_BUS.post(event);
        HiiragiMaterial.REGISTRY.setUnmodifiable();

        HiiragiMaterial.REGISTRY.getValues().forEach(material -> HiiragiMaterial.REGISTRY_INDEX.register(material.index, material));
        HiiragiMaterial.REGISTRY_INDEX.setUnmodifiable();

    }

}