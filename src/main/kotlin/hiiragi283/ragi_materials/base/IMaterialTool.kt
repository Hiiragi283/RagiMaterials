package hiiragi283.ragi_materials.base

import hiiragi283.ragi_materials.material.builder.MaterialBuilder

interface IMaterialTool {

    fun getToolID(): String

    fun getToolMaterial(): MaterialBuilder

}