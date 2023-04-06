package hiiragi283.ragi_materials.client.model.tile

import net.minecraft.client.model.ModelBase
import net.minecraft.client.model.ModelBox
import net.minecraft.client.model.ModelRenderer
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

// Made with Blockbench 4.6.5
// Exported for Minecraft version 1.7 - 1.12
// Paste this class into your mod and generate all required imports

@SideOnly(Side.CLIENT)
object ModelQuartzAntenna : ModelBase() {

    private val cube: ModelRenderer

    init {
        textureWidth = 32
        textureHeight = 32

        cube = ModelRenderer(this)
        cube.setRotationPoint(0.0f, 10.0f, 1.0f)
        cube.cubeList.add(ModelBox(cube, 0, 13, -1.0f, -1.0f, -6.0f, 2, 2, 5, 0.0f, false))
        cube.cubeList.add(ModelBox(cube, 0, 0, -6.0f, -6.0f, -1.0f, 12, 12, 1, 0.0f, false))
    }

    fun render(rotateX: Float, rotateY: Float) {
        cube.rotateAngleX = rotateX
        cube.rotateAngleY = rotateY
        cube.render(0.0625f)
    }
}