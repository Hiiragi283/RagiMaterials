package hiiragi283.ragi_materials.client.render.tile

import net.minecraft.client.model.ModelBase
import net.minecraft.client.model.ModelBox
import net.minecraft.client.model.ModelRenderer
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

// Made with Blockbench 4.6.4
// Exported for Minecraft version 1.7 - 1.12
// Paste this class into your mod and generate all required imports

/*
  Thanks to defeatedcrow!
  Source: https://github.com/defeatedcrow/FluidTankTutorialMod/blob/master/src/main/java/defeatedcrow/tutorial/ibc/base/ModelIBC.java
*/

@SideOnly(Side.CLIENT)
class ModelLaboratoryTable : ModelBase() {

    private val cube: ModelRenderer

    init {
        textureWidth = 64
        textureHeight = 64

        cube = ModelRenderer(this)
        cube.setRotationPoint(0.0f, 0.0f, 0.0f)
        cube.cubeList.add(ModelBox(cube, 0, 0, 6.0f, -14.0f, -8.0f, 2, 14, 2, 0.0f, true))
        cube.cubeList.add(ModelBox(cube, 0, 0, -8.0f, -14.0f, -8.0f, 2, 14, 2, 0.0f, true))
        cube.cubeList.add(ModelBox(cube, 0, 0, -8.0f, -14.0f, 6.0f, 2, 14, 2, 0.0f, true))
        cube.cubeList.add(ModelBox(cube, 0, 0, 6.0f, -14.0f, 6.0f, 2, 14, 2, 0.0f, true))
        cube.cubeList.add(ModelBox(cube, 0, 0, -8.0f, -16.0f, -8.0f, 16, 2, 16, 0.0f, true))
        cube.cubeList.add(ModelBox(cube, 0, 18, -7.0f, -10.0f, -7.0f, 14, 2, 14, 0.0f, true))

    }

    fun render() {
        cube.render(0.0625f)
    }

    fun setRotationAngle(modelRenderer: ModelRenderer, x: Float, y: Float, z: Float) {
        modelRenderer.rotateAngleX = x
        modelRenderer.rotateAngleY = y
        modelRenderer.rotateAngleZ = z
    }
}