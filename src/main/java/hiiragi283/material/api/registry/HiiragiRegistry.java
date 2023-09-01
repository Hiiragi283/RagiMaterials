package hiiragi283.material.api.registry;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class HiiragiRegistry<K, V> {
    private final String name;

    private final V defaultValue;
    protected final Logger LOGGER;

    public HiiragiRegistry(String name, V defaultValue) {
        this.name = name;
        this.defaultValue = defaultValue;
        this.LOGGER = LogManager.getLogger(name);
    }

    private final Map<K, V> REGISTRY = new HashMap<>();
    private boolean isUnmodifiable = false;

    public String getName() {
        return name;
    }

    public V getDefaultValue() {
        return defaultValue;
    }

    public Collection<V> getValues() {
        return REGISTRY.values();
    }

    public @NotNull V getValue(K key) {
        return REGISTRY.getOrDefault(key, defaultValue);
    }

    public void register(K key, V value) {
        if (isUnmodifiable) {
            LOGGER.error("Cannot register any values with this registry!");
        } else if (Objects.equals(value, defaultValue)) {
            LOGGER.error("Cannot register default value!");
        } else if (REGISTRY.containsKey(key)) {
            LOGGER.error("The key: " + key + "is already registered!");
        } else {
            REGISTRY.putIfAbsent(key, value);
        }
    }

    public void setUnmodifiable() {
        isUnmodifiable = true;
    }

}