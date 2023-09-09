package hiiragi283.material.api.registry;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Optional;

@ParametersAreNonnullByDefault
public class HiiragiRegistry<K, V> {

    private boolean isUnmodifiable = false;
    private final LinkedHashMap<K, V> REGISTRY = new LinkedHashMap<>();
    private final String name;
    protected final Logger LOGGER;

    public HiiragiRegistry(String name) {
        this.name = name;
        this.LOGGER = LogManager.getLogger(name);
    }

    @NotNull
    public String getName() {
        return name;
    }

    @NotNull
    public Collection<V> getValues() {
        return REGISTRY.values();
    }

    @NotNull
    public Optional<V> getValue(K key) {
        return Optional.ofNullable(REGISTRY.get(key));
    }

    public void register(K key, V value) {
        if (isUnmodifiable) {
            LOGGER.error("Cannot register any values with this registry!");
        } else if (REGISTRY.containsKey(key)) {
            LOGGER.error("The key: " + key + " is already registered!");
        } else {
            REGISTRY.putIfAbsent(key, value);
        }
    }

    public void setUnmodifiable() {
        isUnmodifiable = true;
    }

}