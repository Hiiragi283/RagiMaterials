package hiiragi283.ragi_materials.recipe.forge_furnace

import hiiragi283.ragi_materials.util.RagiUtil
import net.minecraft.item.ItemStack

open class FFRecipe(val input: ItemStack, val output: ItemStack, val type: EnumFire) {

    constructor(input: String, output: String, type: EnumFire) : this(RagiUtil.getStack(input), RagiUtil.getStack(output), type)

    constructor(recipe: String, type: EnumFire) : this(RagiUtil.getStack(recipe.split(";")[0]), RagiUtil.getStack(recipe.split(";")[1]), type)

    //コンストラクタの初期化
    init {
        register()
    }

    fun register() {
        when (this.type) {
            EnumFire.BURNING -> FFRegistry.mapBurning[this.input] = this.output
            EnumFire.BOOSTED -> FFRegistry.mapBoosted[this.input] = this.output
            EnumFire.HELLRISE -> FFRegistry.mapHellrise[this.input] = this.output
        }
    }

    enum class EnumFire(val display: String) {
        BURNING("§6§lBurning"), BOOSTED("§c§lBoosted"), HELLRISE("§4§lHellrise")
    }
}