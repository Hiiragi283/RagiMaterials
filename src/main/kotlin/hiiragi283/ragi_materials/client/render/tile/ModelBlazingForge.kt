package hiiragi283.ragi_materials.client.render.tile

import net.minecraft.client.model.ModelBase
import net.minecraft.client.model.ModelBox
import net.minecraft.client.model.ModelRenderer

// Made with Blockbench 4.6.5
// Exported for Minecraft version 1.7 - 1.12
// Paste this class into your mod and generate all required imports

class ModelBlazingForge : ModelBase() {
    private val cube: ModelRenderer
    private val head: ModelRenderer

    init {
        textureWidth = 64
        textureHeight = 32

        cube = ModelRenderer(this)
        cube.setRotationPoint(0.0f, 0.0f, 0.0f)
        head = ModelRenderer(this)
        head.setRotationPoint(0.0f, -8.0f, 0.0f)
        cube.addChild(head)
        setRotationAngle(head, -0.1745f, 0.0f, 0.1745f)
        head.cubeList.add(ModelBox(head, 0, 0, -4.0f, -4.0f, -4.0f, 8, 8, 8, 0.0f, false))
    }

    fun render() {
        cube.render(0.0625f)
    }

    private fun setRotationAngle(modelRenderer: ModelRenderer, x: Float, y: Float, z: Float) {
        modelRenderer.rotateAngleX = x
        modelRenderer.rotateAngleY = y
        modelRenderer.rotateAngleZ = z
    }
}