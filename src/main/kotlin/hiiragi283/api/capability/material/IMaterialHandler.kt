package hiiragi283.api.capability.material

import hiiragi283.api.material.MaterialStack

interface IMaterialHandler {

    fun getMaterialStack(): MaterialStack

    fun getMaterialAmount(): Int

    fun getCapacity(): Int

    fun isEmpty(): Boolean = getMaterialStack().isEmpty()

    /**
     *
     * @param resource
     *            MaterialStack attempting to be inserted to the handler.
     * @param simulate
     *            If true, the fill will only be simulated.
     * @return MaterialStack that was accepted by the handler.
     */
    fun insertMaterial(resource: MaterialStack, simulate: Boolean): MaterialStack

    /**
     *
     * @param resource
     *            MaterialStack to be extracted from the handler.
     * @param simulate
     *            If true, the drain will only be simulated.
     * @return MaterialStack that was removed from the handler.
     */
    fun extractMaterial(resource: MaterialStack, simulate: Boolean): MaterialStack

    fun canInsert(resource: MaterialStack): Boolean = when {
        getMaterialStack().isEmpty() -> true
        !getMaterialStack().equalsMaterial(resource) -> false
        else -> getMaterialAmount() + resource.amount <= getCapacity()
    }

    fun canExtract(resource: MaterialStack): Boolean = when {
        getMaterialStack().isEmpty() -> false
        !getMaterialStack().equalsMaterial(resource) -> false
        else -> true
    }

}