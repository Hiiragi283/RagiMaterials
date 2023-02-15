package hiiragi283.ragi_materials.recipe.forge_furnace

import hiiragi283.ragi_materials.util.RagiUtils
import net.minecraft.item.ItemStack

open class ForgeFurnaceRecipe(val input: ItemStack, val output: ItemStack, val type: EnumFire) {

    constructor(input: String, output: String, type: EnumFire): this(RagiUtils.getStack(input), RagiUtils.getStack(output), type)

    constructor(recipe: String, type: EnumFire): this(RagiUtils.getStack(recipe.split(";")[0]), RagiUtils.getStack(recipe.split(";")[1]), type)

    //コンストラクタの初期化
    init {
        register()
    }

    fun register() {
        ForgeFurnaceRegistry.list.add(this)
    }

    enum class EnumFire(val display: String) {
        BURNING("§6§lBurning"), BOOSTED("§c§lBoosted"), HELLRISE("§4§lHellrise")
    }
}