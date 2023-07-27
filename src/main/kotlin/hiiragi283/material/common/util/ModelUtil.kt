package hiiragi283.material.common.util

import net.devtech.arrp.json.models.*
import net.minecraft.util.math.Direction

object ModelUtil {

    fun getBlockModel(texture: String): JModel = JModel.model("block/block")
        .textures(
            JTextures()
                .particle("#all")
                .`var`("all", texture)
        )
        .element(
            JElement()
                .from(0.0f, 0.0f, 0.0f)
                .to(16.0f, 16.0f, 16.0f)
                .faces(
                    JFaces()
                        .down(JFace("all").cullface(Direction.DOWN).tintIndex(0))
                        .up(JFace("all").cullface(Direction.UP).tintIndex(0))
                        .north(JFace("all").cullface(Direction.NORTH).tintIndex(0))
                        .south(JFace("all").cullface(Direction.SOUTH).tintIndex(0))
                        .west(JFace("all").cullface(Direction.WEST).tintIndex(0))
                        .east(JFace("all").cullface(Direction.EAST).tintIndex(0))
                )
        )

    fun getOreBlockModel(stone: String): JModel = JModel.model("block/block")
        .textures(
            JTextures()
                .particle(stone)
                .`var`("stone", stone)
                .`var`("ore", "ragi_materials:block/ore")
        )
        .element(
            JElement()
                .from(0.0f, 0.0f, 0.0f)
                .to(16.0f, 16.0f, 16.0f)
                .faces(
                    JFaces()
                        .down(JFace("stone").cullface(Direction.DOWN))
                        .up(JFace("stone").cullface(Direction.UP))
                        .north(JFace("stone").cullface(Direction.NORTH))
                        .south(JFace("stone").cullface(Direction.SOUTH))
                        .west(JFace("stone").cullface(Direction.WEST))
                        .east(JFace("stone").cullface(Direction.EAST)),
                ),
            JElement()
                .from(0.0f, 0.0f, 0.0f)
                .to(16.0f, 16.0f, 16.0f)
                .faces(
                    JFaces()
                        .down(JFace("ore").cullface(Direction.DOWN).tintIndex(0))
                        .up(JFace("ore").cullface(Direction.UP).tintIndex(0))
                        .north(JFace("ore").cullface(Direction.NORTH).tintIndex(0))
                        .south(JFace("ore").cullface(Direction.SOUTH).tintIndex(0))
                        .west(JFace("ore").cullface(Direction.WEST).tintIndex(0))
                        .east(JFace("ore").cullface(Direction.EAST).tintIndex(0)),
                )
        )


    fun getItemModel(layer: JTextures.() -> JTextures): JModel =
        JModel.model().parent("item/generated").textures(JTextures().layer())

}