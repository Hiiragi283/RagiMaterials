package hiiragi283.ragi_materials.client.model.tile

import net.minecraft.client.model.ModelBase
import net.minecraft.client.model.ModelBox
import net.minecraft.client.model.ModelRenderer

// Made with Blockbench 4.6.5
// Exported for Minecraft version 1.7 - 1.12
// Paste this class into your mod and generate all required imports

object ModelEnergyCrystal : ModelBase() {

    private val core: ModelRenderer
    private val halo: ModelRenderer

    init {
        textureWidth = 64
        textureHeight = 64
        core = ModelRenderer(this)
        core.setRotationPoint(0.0f, 8.0f, 0.0f)
        core.cubeList.add(ModelBox(core, 0, 0, -8.0f, -16.0f, -8.0f, 16, 16, 0, 0.0f, false))
        halo = ModelRenderer(this)
        halo.setRotationPoint(0.0f, -8.0f, 0.0f)
        core.addChild(halo)
        halo.rotateAngleX = -0.7854f
        halo.rotateAngleY = -0.7854f
        halo.rotateAngleZ = 0.7854f
        halo.cubeList.add(ModelBox(halo, 0, 24, -3.0f, -3.0f, -3.0f, 6, 6, 6, 0.0f, false))
    }

    fun render() {
        core.render(0.0625f)
    }
}