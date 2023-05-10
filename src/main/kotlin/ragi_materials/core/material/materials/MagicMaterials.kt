package ragi_materials.core.material.materials

import ragi_materials.core.material.ElementRegistry
import ragi_materials.core.material.MaterialRegistry
import ragi_materials.core.material.RagiMaterial
import ragi_materials.core.material.type.TypeRegistry

object MagicMaterials {

    fun load() {

        MaterialRegistry.MITHRIL = RagiMaterial.Builder(1100, "mithril", TypeRegistry.METAL).setSimple(ElementRegistry.MITHRIL to 1).buildAndRegister()

    }

}