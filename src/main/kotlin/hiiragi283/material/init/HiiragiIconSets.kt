package hiiragi283.material.init

import hiiragi283.material.api.material.MaterialIconSet
import hiiragi283.material.util.hiiragiLocation

object HiiragiIconSets {

    @JvmField
    val DEFAULT: MaterialIconSet = MaterialIconSet.build()

    //    GEM    //

    @JvmField
    val GEM = DEFAULT.copy {
        iconSet[HiiragiShapes.BLOCK] = hiiragiLocation("block_gem")
    }

    @JvmField
    val AMORPHOUS = GEM.copy {
        iconSet[HiiragiShapes.GEM] = hiiragiLocation("gem_amorphous")
    }

    @JvmField
    val COAL = GEM.copy {
        iconSet[HiiragiShapes.GEM] = hiiragiLocation("gem_coal")
    }

    @JvmField
    val CUBIC = GEM.copy {
        iconSet[HiiragiShapes.GEM] = hiiragiLocation("gem_cubic")
    }

    @JvmField
    val DIAMOND = GEM.copy {
        iconSet[HiiragiShapes.GEM] = hiiragiLocation("gem_diamond")
    }

    @JvmField
    val EMERALD = GEM.copy {
        iconSet[HiiragiShapes.GEM] = hiiragiLocation("gem_emerald")
    }

    @JvmField
    val LAPIS = GEM.copy {
        iconSet[HiiragiShapes.GEM] = hiiragiLocation("gem_lapis")
    }

    @JvmField
    val QUARTZ = GEM.copy {
        iconSet[HiiragiShapes.GEM] = hiiragiLocation("gem_quartz")
    }

    @JvmField
    val RUBY = GEM.copy {
        iconSet[HiiragiShapes.GEM] = hiiragiLocation("gem_ruby")
    }

    //    METAL    //

    @JvmField
    val METAL = DEFAULT.copy {
        iconSet[HiiragiShapes.BLOCK] = hiiragiLocation("block_metal")
    }

}