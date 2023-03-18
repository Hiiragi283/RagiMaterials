package hiiragi283.ragi_materials.client.render.tile

import net.minecraft.client.model.ModelBase
import net.minecraft.client.model.ModelBox
import net.minecraft.client.model.ModelRenderer

// Made with Blockbench 4.6.5
// Exported for Minecraft version 1.7 - 1.12
// Paste this class into your mod and generate all required imports

class ModelIndustrialLabo : ModelBase() {

    private val cube: ModelRenderer

    init {
        textureWidth = 64
        textureHeight = 64
        cube = ModelRenderer(this)
        cube.setRotationPoint(0.0f, 0.0f, 0.0f)
        cube.cubeList.add(ModelBox(cube, 0, 0, -8.0f, -16.0f, -4.0f, 16, 16, 12, 0.0f, false))
        cube.cubeList.add(ModelBox(cube, 0, 28, -8.0f, -14.0f, -8.0f, 16, 2, 4, 0.0f, false))
        cube.cubeList.add(ModelBox(cube, 0, 34, -8.0f, -12.0f, -8.0f, 2, 12, 4, 0.0f, false))
        cube.cubeList.add(ModelBox(cube, 0, 34, 6.0f, -12.0f, -8.0f, 2, 12, 4, 0.0f, true))
        cube.cubeList.add(ModelBox(cube, 12, 34, -6.0f, -12.0f, -6.0f, 12, 12, 0, 0.0f, false))
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