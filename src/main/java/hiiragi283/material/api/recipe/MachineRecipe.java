package hiiragi283.material.api.recipe;

import com.github.bsideup.jabel.Desugar;
import hiiragi283.material.api.machine.MachineContents;
import hiiragi283.material.api.machine.ModuleTrait;
import hiiragi283.material.api.material.HiiragiMaterial;
import hiiragi283.material.api.part.HiiragiPart;
import hiiragi283.material.api.shape.HiiragiShape;
import hiiragi283.material.util.HiiragiIngredient;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraftforge.fluids.FluidStack;
import org.jetbrains.annotations.NotNull;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.*;
import java.util.stream.Collectors;

@Desugar
@ParametersAreNonnullByDefault
public record MachineRecipe(

        IMachineRecipe.Type type,
        Set<ModuleTrait> traits,

        List<Ingredient> inputsItem,

        List<FluidStack> inputsFluid,

        List<Ingredient> outputsItem,

        List<FluidStack> outputsFluid

) implements IMachineRecipe {

    //    IMachineRecipe    //

    @NotNull
    @Override
    public Type getType() {
        return type;
    }

    @NotNull
    @Override
    public Set<ModuleTrait> getRequiredTraits() {
        return new HashSet<>(traits);
    }

    @Override
    public boolean matches(MachineContents machineContents) {
        return machineContents.property().getModuleTraits().containsAll(getRequiredTraits());
    }

    @NotNull
    @Override
    public List<List<ItemStack>> getInputItems() {
        return inputsItem.stream()
                .map(Ingredient::getMatchingStacks)
                .map(Arrays::asList)
                .collect(Collectors.toList());
    }

    @NotNull
    @Override
    public List<FluidStack> getInputFluids() {
        return new ArrayList<>(inputsFluid);
    }

    @NotNull
    @Override
    public List<List<ItemStack>> getOutputItems() {
        return outputsItem.stream()
                .map(Ingredient::getMatchingStacks)
                .map(Arrays::asList)
                .collect(Collectors.toList());
    }

    @NotNull
    @Override
    public List<FluidStack> getOutputFluids() {
        return new ArrayList<>(outputsFluid);
    }

    //    Builder    //

    @ParametersAreNonnullByDefault
    public static class Builder {

        private final IMachineRecipe.Type type;

        private final Set<ModuleTrait> traits = new HashSet<>();

        private final List<Ingredient> inputsItem = new ArrayList<>();

        private final List<FluidStack> inputsFluid = new ArrayList<>();

        private final List<Ingredient> outputsItem = new ArrayList<>();

        private final List<FluidStack> outputsFluid = new ArrayList<>();

        public Builder(IMachineRecipe.Type type) {
            this.type = type;
        }

        public MachineRecipe build() {
            return new MachineRecipe(type, traits, inputsItem, inputsFluid, outputsItem, outputsFluid);
        }

        //    Traits    //

        public MachineRecipe.Builder addTrait(ModuleTrait trait) {
            traits.add(trait);
            return this;
        }

        public MachineRecipe.Builder addTraits(Collection<ModuleTrait> traits) {
            this.traits.addAll(traits);
            return this;
        }

        public MachineRecipe.Builder addTraits(ModuleTrait... traits) {
            this.traits.addAll(Arrays.asList(traits));
            return this;
        }

        //    Input    //

        public MachineRecipe.Builder addInput(Item item) {
            return addInput(new ItemStack(item));
        }

        public MachineRecipe.Builder addInput(ItemStack stack) {
            inputsItem.add(new HiiragiIngredient(stack));
            return this;
        }

        public MachineRecipe.Builder addInput(HiiragiPart part) {
            return part.getShape()
                    .map(shape -> part.getMaterial()
                            .map(material -> addInput(shape, material))
                            .orElse(this)).
                    orElse(this);
        }

        public MachineRecipe.Builder addInput(HiiragiShape shape, HiiragiMaterial material) {
            return addInput(shape.getOreDict(material));
        }

        public MachineRecipe.Builder addInput(Collection<String> oreDicts) {
            //TODO
            return this;
        }

        public MachineRecipe.Builder addInput(String oreDict) {
            inputsItem.add(new HiiragiIngredient(oreDict));
            return this;
        }

        //    Output    //

    }

}