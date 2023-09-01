package hiiragi283.material.api.event;

import hiiragi283.material.api.shape.HiiragiShape;
import hiiragi283.material.api.shape.ShapeType;
import net.minecraftforge.fml.common.eventhandler.Event;

import java.util.Set;

public abstract class ShapeTypeEvent extends Event {

    private final String name;
    private final Set<HiiragiShape> shapes;

    public ShapeTypeEvent(String name, Set<HiiragiShape> shapes) {
        this.name = name;
        this.shapes = shapes;
    }

    public String getName() {
        return name;
    }

    public Set<HiiragiShape> getShapes() {
        return shapes;
    }

    public static class Create extends ShapeTypeEvent {

        public Create(String name, Set<HiiragiShape> shapes) {
            super(name, shapes);
        }

    }

    public static class Copy extends ShapeTypeEvent {

        private final ShapeType parent;

        public Copy(String name, Set<HiiragiShape> shapes, ShapeType parent) {
            super(name, shapes);
            this.parent = parent;
        }

        public ShapeType getParent() {
            return parent;
        }

    }

}