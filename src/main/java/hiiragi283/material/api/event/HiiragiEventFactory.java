package hiiragi283.material.api.event;

import hiiragi283.material.api.shape.HiiragiShape;
import hiiragi283.material.api.shape.ShapeType;
import net.minecraftforge.common.MinecraftForge;

import java.util.Set;

public abstract class HiiragiEventFactory {

    //    Shape    //

    public static void onShapeTypeCreate(String name, Set<HiiragiShape> shapes) {
        var event = new ShapeTypeEvent.Create(name, shapes);
        MinecraftForge.EVENT_BUS.post(event);
    }

    public static void onShapeTypeCopy(String name, Set<HiiragiShape> shapes, ShapeType parent) {
        var event = new ShapeTypeEvent.Copy(name, shapes, parent);
        MinecraftForge.EVENT_BUS.post(event);
    }

}