package hiiragi283.material.api.material;

import com.github.bsideup.jabel.Desugar;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;

@Desugar
public record MaterialStack(HiiragiMaterial material, int amount) {

    public MaterialStack(FluidStack fluidStack) {
        this(fluidStack.getFluid(), fluidStack.amount);
    }

    public MaterialStack(Fluid fluid, int amount) {
        this(fluid.getName(), amount);
    }

    public MaterialStack(NBTTagCompound nbt) {
        this(nbt.getString("material"), nbt.getInteger("amount"));
    }

    public MaterialStack(String name, int amount) {
        this(HiiragiMaterial.REGISTRY.getValue(name), amount);
    }

    public static MaterialStack EMPTY = new MaterialStack(HiiragiMaterial.EMPTY, 0);

    public void addTooltip(List<String> tooltip) {
        material().addTooltip(tooltip, material().getTranslatedName(), amount());
    }

    public boolean isEmpty() {
        return Objects.equals(this, EMPTY) || this.equalsMaterial(EMPTY) || this.amount <= 0;
    }

    //    Amount    //

    public MaterialStack addAmount(int add) {
        return new MaterialStack(this.material(), this.amount() + add);
    }

    public MaterialStack removeAmount(int remove) {
        return new MaterialStack(this.material(), this.amount() - remove);
    }

    public MaterialStack setAmount(int amount) {
        return new MaterialStack(this.material(), amount);
    }

    public boolean equalsMaterial(@NotNull MaterialStack other) {
        return this.material().equals(other.material());
    }

    //    Serialization    //

    public NBTTagCompound serializeNBT() {
        var tag = new NBTTagCompound();
        tag.setString("material", material().name());
        tag.setInteger("amount", amount());
        return tag;
    }

}