package hiiragi283.material.api.item;

import hiiragi283.material.api.material.HiiragiMaterial;
import hiiragi283.material.api.shape.HiiragiShapes;
import hiiragi283.material.util.CraftingBuilder;
import hiiragi283.material.util.HiiragiUtil;

public class MaterialItemCasing extends MaterialItem {

    public MaterialItemCasing() {
        super(HiiragiShapes.CASING);
    }

    //    MaterialItem    //

    @Override
    protected void getRecipe(ITEM item, HiiragiMaterial material) {
        new CraftingBuilder(item.asItemStack(material))
                .setPattern("A A", "ABA", "A A")
                .setIngredient('A', HiiragiShapes.PLATE.getOreDict(material))
                .setIngredient('B', HiiragiShapes.FRAME.getOreDict(material))
                .build();
    }

    @Override
    protected void getModel(ITEM item) {
        HiiragiUtil.setModelSame(item.getObject());
    }

}