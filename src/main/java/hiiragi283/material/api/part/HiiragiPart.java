package hiiragi283.material.api.part;

import com.github.bsideup.jabel.Desugar;
import hiiragi283.material.api.material.HiiragiMaterial;
import hiiragi283.material.api.registry.HiiragiRegistry;
import hiiragi283.material.api.shape.HiiragiShape;
import hiiragi283.material.util.HiiragiUtil;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import java.util.*;
import java.util.stream.Collectors;

@Desugar
public record HiiragiPart(HiiragiShape shape, HiiragiMaterial material) {

    public static final HiiragiPart EMPTY = new HiiragiPart(HiiragiShape.EMPTY, HiiragiMaterial.EMPTY);

    public void addTooltip(List<String> tooltip) {
        material.addTooltip(tooltip, shape);
    }

    public String getOreDict() {
        return shape.getOreDict(material);
    }

    public List<String> getOreDicts() {
        return shape.getOreDicts(material);
    }

    public boolean isEmpty() {
        return this.equals(EMPTY) || this.shape.isEmpty() || this.material.isEmpty();
    }

    @Override
    public String toString() {
        return shape.name() + ":" + material.name();
    }

    //    Registry    //

    public static HiiragiRegistry<String, HiiragiPart> REGISTRY = new HiiragiRegistry<>("Part", EMPTY);

    public static List<HiiragiPart> getAllParts() {
        return HiiragiShape.REGISTRY.getValues()
                .stream()
                .flatMap(shape -> HiiragiMaterial.REGISTRY.getValues()
                        .stream()
                        .map(material -> new HiiragiPart(shape, material)))
                .collect(Collectors.toList());
    }

    public static List<HiiragiPart> getParts(Collection<String> oreDicts) {
        return oreDicts.stream()
                .map(oreDict -> HiiragiPart.REGISTRY.getValue(oreDict))
                .filter(part -> !part.isEmpty())
                .collect(Collectors.toList());
    }

    public static List<HiiragiPart> getParts(String oreDict) {
        List<HiiragiPart> list = new ArrayList<>(Collections.singletonList(HiiragiPart.REGISTRY.getValue(oreDict)));
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
                .map(oreDict -> HiiragiPart.REGISTRY.getValue(oreDict))
                .filter(part -> !part.isEmpty())
                .collect(Collectors.toList());
    }

}