package hiiragi283.material.api.registry;

import java.lang.reflect.Field;
import java.util.function.Consumer;

public abstract class HiiragiRegistryEntries {

    //    Register    //

    public HiiragiRegistryEntries() {
    }

    @SuppressWarnings("unchecked")
    public static <T, U, V> void register(T instance, Class<V> clazz, HiiragiRegistry<U, V> registry, Consumer<V> consumer) throws IllegalAccessException {
        Field[] fields = instance.getClass().getFields();
        for (Field field : fields) {
            field.setAccessible(true);
            Object obj = field.get(instance);
            if (clazz.isInstance(obj)) {
                V objCasted = (V) obj;
                consumer.accept(objCasted);
            }
        }
    }

}