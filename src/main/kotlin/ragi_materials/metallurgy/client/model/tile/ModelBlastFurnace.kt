package ragi_materials.metallurgy.client.model.tile

import net.minecraft.client.model.ModelBase
import net.minecraft.client.model.ModelBox
import net.minecraft.client.model.ModelRenderer

// Made with Blockbench 4.7.1
// Exported for Minecraft version 1.7 - 1.12
// Paste this class into your mod and generate all required imports

object ModelBlastFurnace : ModelBase() {

    private val cube: ModelRenderer

    init {
        textureWidth = 32
        textureHeight = 32
        cube = ModelRenderer(this)
        cube.setRotationPoint(0.0f, 0.0f, 0.0f)
        cube.cubeList.add(ModelBox(cube, -16, 0, -8.0f, 0.0f, -8.0f, 16, 0, 16, 0.0f, false))
    }

    fun render() {
        cube.render(0.0625f)
    }
}