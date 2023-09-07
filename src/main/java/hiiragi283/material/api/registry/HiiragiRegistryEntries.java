package hiiragi283.material.api.registry;

import java.lang.reflect.Field;
import java.util.function.Consumer;

public abstract class HiiragiRegistryEntries {

    //    Register    //

    public HiiragiRegistryEntries() {
    }

    @SuppressWarnings("unchecked")
    public static <T> void register(Object instance, Class<T> clazz, Consumer<T> consumer) throws IllegalAccessException {
        Field[] fields = instance.getClass().getFields();
        for (Field field : fields) {
            field.setAccessible(true);
            Object obj = field.get(instance);
            if (clazz.isInstance(obj)) {
                T objCasted = (T) obj;
                consumer.accept(objCasted);
            }
        }
    }

}