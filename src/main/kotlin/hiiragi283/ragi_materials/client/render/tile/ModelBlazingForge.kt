package hiiragi283.ragi_materials.client.render.tile

import net.minecraft.client.model.ModelBase
import net.minecraft.client.model.ModelBox
import net.minecraft.client.model.ModelRenderer

// Made with Blockbench 4.6.5
// Exported for Minecraft version 1.7 - 1.12
// Paste this class into your mod and generate all required imports

class ModelBlazingForge : ModelBase() {

    private val head: ModelRenderer
    private val pillarW: ModelRenderer
    private val pillarS: ModelRenderer
    private val pillarE: ModelRenderer
    private val pillarN: ModelRenderer

    init {
        textureWidth = 64
        textureHeight = 32

        head = ModelRenderer(this)
        head.setRotationPoint(0.0f, 0.0f, 0.0f)
        head.cubeList.add(ModelBox(head, 0, 0, -4.0f, -12.0f, -4.0f, 8, 8, 8, 0.0f, false))

        pillarW = ModelRenderer(this)
        pillarW.setRotationPoint(0.0f, 0.0f, 0.0f)
        head.addChild(pillarW)
        setRotationAngle(pillarW, 0.0f, 1.5708f, 1.5708f)
        pillarW.cubeList.add(ModelBox(pillarW, 0, 16, 5.0f, -5.0f, -15.0f, 2, 12, 2, 0.0f, false))

        pillarS = ModelRenderer(this)
        pillarS.setRotationPoint(0.0f, 0.0f, 0.0f)
        head.addChild(pillarS)
        setRotationAngle(pillarS, 1.5708f, 0.0f, 3.1416f)
        pillarS.cubeList.add(ModelBox(pillarS, 0, 16, 5.0f, -5.0f, -15.0f, 2, 12, 2, 0.0f, false))

        pillarE = ModelRenderer(this)
        pillarE.setRotationPoint(0.0f, 0.0f, 0.0f)
        head.addChild(pillarE)
        setRotationAngle(pillarE, 0.0f, -1.5708f, -1.5708f)
        pillarE.cubeList.add(ModelBox(pillarE, 0, 16, 5.0f, -5.0f, -15.0f, 2, 12, 2, 0.0f, false))

        pillarN = ModelRenderer(this)
        pillarN.setRotationPoint(0.0f, 0.0f, 0.0f)
        head.addChild(pillarN)
        setRotationAngle(pillarN, -1.5708f, 0.0f, 0.0f)
        pillarN.cubeList.add(ModelBox(pillarN, 0, 16, 5.0f, -5.0f, -15.0f, 2, 12, 2, 0.0f, false))
    }

    fun render() {
        head.render(0.0625f)
    }

    fun setRotationAngle(modelRenderer: ModelRenderer, x: Float, y: Float, z: Float) {
        modelRenderer.rotateAngleX = x
        modelRenderer.rotateAngleY = y
        modelRenderer.rotateAngleZ = z
    }
}