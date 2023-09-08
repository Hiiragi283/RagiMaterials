package hiiragi283.material.api.part;

import hiiragi283.material.api.material.HiiragiMaterial;
import hiiragi283.material.api.registry.HiiragiRegistry;
import hiiragi283.material.api.shape.HiiragiShape;
import hiiragi283.material.util.HiiragiCollectors;
import hiiragi283.material.util.HiiragiUtil;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.stream.Collectors;

public class HiiragiPart {

    @Nullable
    private final HiiragiShape shape;
    @Nullable
    private final HiiragiMaterial material;

    public HiiragiPart(@Nullable HiiragiShape shape, @Nullable HiiragiMaterial material) {
        this.shape = shape;
        this.material = material;
    }

    public Optional<HiiragiShape> getShape() {
        return Optional.ofNullable(shape);
    }

    public Optional<HiiragiMaterial> getMaterial() {
        return Optional.ofNullable(material);
    }

    public static final HiiragiPart EMPTY = new HiiragiPart(null, null);

    public void addTooltip(List<String> tooltip) {
        getShape().ifPresent(shape -> getMaterial().ifPresent(material -> material.addTooltip(tooltip, shape)));
    }

    public List<ItemStack> getItemStacks() {
        return getOreDicts().stream().map(OreDictionary::getOres).collect(HiiragiCollectors.NON_NULL_LIST_COLLECTOR());
    }

    public String getOreDict() {
        return getShape().map(shape -> getMaterial()
                .map(shape::getOreDict)
                .orElse(""))
                .orElse("");
    }

    public List<String> getOreDicts() {
        return getShape().map(shape -> getMaterial()
                .map(shape::getOreDicts)
                .orElse(Collections.emptyList()))
                .orElse(Collections.emptyList());
    }

    public boolean isEmpty() {
        return this.equals(EMPTY) || this.shape == null || this.material == null;
    }

    @Override
    public String toString() {
        return getShape().map(HiiragiShape::name).orElse("EMPTY_SHAPE") + ":" + getMaterial().map(material -> material.name).orElse("EMPTY_MATERIAL");
    }

    //    Registry    //

    public static HiiragiRegistry<String, HiiragiPart> REGISTRY = new HiiragiRegistry<>("Part");

    public static List<HiiragiPart> getAllParts() {
        return HiiragiShape.REGISTRY.getValues()
                .stream()
                .flatMap(shape -> HiiragiMaterial.REGISTRY.getValues().stream()
                        .map(material -> new HiiragiPart(shape, material)))
                .collect(Collectors.toList());
    }

    public static List<HiiragiPart> getParts(Collection<String> oreDicts) {
        return oreDicts.stream()
                .map(oreDict -> HiiragiPart.REGISTRY.getValue(oreDict))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    public static List<HiiragiPart> getParts(String oreDict) {
        List<HiiragiPart> list = new ArrayList<>(Collections.singletonList(HiiragiPart.REGISTRY.getValue(oreDict).orElse(null)));
        list.remove(HiiragiPart.EMPTY);
        return new ArrayList<>(list);
    }

    public static List<HiiragiPart> getParts(IBlockState state) {
        return getParts(HiiragiUtil.toItemStack(state, 1));
    }

    public static List<HiiragiPart> getParts(ItemStack stack) {
        if (stack.isEmpty()) return new ArrayList<>();
        return Arrays.stream(OreDictionary.getOreIDs(stack))
                .mapToObj(OreDictionary::getOreName)
                .map(oreDict -> HiiragiPart.REGISTRY.getValue(oreDict).orElse(null))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

}