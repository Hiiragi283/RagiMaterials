package hiiragi283.material.api.block;

import hiiragi283.material.api.item.MaterialItemBlockCasing;
import hiiragi283.material.api.material.HiiragiMaterial;
import hiiragi283.material.api.shape.HiiragiShapes;
import hiiragi283.material.util.CraftingBuilder;
import hiiragi283.material.util.HiiragiUtil;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class MaterialBlockCasing extends MaterialBlock {

    public MaterialBlockCasing() {
        super(HiiragiShapes.CASING);
        this.itemBlock = new MaterialItemBlockCasing(this);
    }

    //    MaterialBlock    //

    @Override
    void getRecipe(BLOCK block, HiiragiMaterial material) {
        new CraftingBuilder(block.asItemStack(material))
                .setPattern("A A", "ABA", "A A")
                .setIngredient('A', HiiragiShapes.PLATE.getOreDict(material))
                .setIngredient('B', HiiragiShapes.FRAME.getOreDict(material))
                .build();
    }

    @Override
    void getModel(BLOCK block) {
        HiiragiUtil.setModelSame(block.getObject());
    }
}