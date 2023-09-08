package hiiragi283.material.util;

import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.function.Supplier;

public class OptionalSupplier<T> implements Supplier<Optional<T>> {

    @Nullable
    private final T value;

    public OptionalSupplier(@Nullable T value) {
        this.value = value;
    }

    @Override
    public Optional<T> get() {
        return Optional.ofNullable(value);
    }

}