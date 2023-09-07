package hiiragi283.material.api.material;

import hiiragi283.material.util.HiiragiUtil;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;
import java.util.Optional;


public class MaterialStack {

    @Nullable
    private final HiiragiMaterial material;
    public int amount;

    public MaterialStack(@Nullable HiiragiMaterial material, int amount) {
        this.material = material;
        this.amount = amount;
    }

    public MaterialStack(FluidStack fluidStack) {
        this(fluidStack.getFluid(), fluidStack.amount);
    }

    public MaterialStack(Fluid fluid, int amount) {
        this(fluid.getName(), amount);
    }

    public MaterialStack(NBTTagCompound nbt) {
        this(nbt.getString(HiiragiUtil.MATERIAL), nbt.getInteger(HiiragiUtil.AMOUNT));
    }

    public MaterialStack(String name, int amount) {
        this(HiiragiMaterial.REGISTRY.getValue(name).orElse(null), amount);
    }

    public MaterialStack(MaterialStack stack, int amount) {
        this(stack.material, amount);
    }

    public Optional<HiiragiMaterial> getMaterial() {
        return Optional.ofNullable(material);
    }

    public static MaterialStack EMPTY = new MaterialStack((HiiragiMaterial) null, 0);

    public void addTooltip(List<String> tooltip) {
        if (material == null) return;
        material.addTooltip(tooltip, material.getTranslatedName(), amount);
    }

    public String getTranslatedName() {
        return material != null ? material.getTranslatedName() : "";
    }

    public boolean isEmpty() {
        return Objects.equals(this, EMPTY) || this.material == null || this.amount <= 0;
    }

    //    Amount    //

    public MaterialStack addAmount(int add) {
        return new MaterialStack(this.material, this.amount + add);
    }

    public MaterialStack removeAmount(int remove) {
        return new MaterialStack(this.material, this.amount - remove);
    }

    public MaterialStack setAmount(int amount) {
        return new MaterialStack(this.material, amount);
    }

    public boolean equalsMaterial(@NotNull MaterialStack other) {
        return material != null && this.material.equals(other.material);
    }

    //    Serialization    //

    public NBTTagCompound serializeNBT() {
        var tag = new NBTTagCompound();
        tag.setString(HiiragiUtil.MATERIAL, material != null ? material.name() : "");
        tag.setInteger(HiiragiUtil.AMOUNT, amount);
        return tag;
    }

}