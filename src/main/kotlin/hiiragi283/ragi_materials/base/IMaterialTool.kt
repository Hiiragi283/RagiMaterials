package hiiragi283.ragi_materials.base

import hiiragi283.ragi_materials.material.RagiMaterial

interface IMaterialTool {

    fun getToolID(): String

    fun getToolMaterial(): RagiMaterial

}