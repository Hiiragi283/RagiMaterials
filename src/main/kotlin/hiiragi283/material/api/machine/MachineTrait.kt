package hiiragi283.material.api.machine

enum class MachineTrait {
    CHEMICAL,
    CLEAN,
    LASER,
    SHIELDING,
    MELT;

    companion object {

        fun from(name: String): MachineTrait? = values().firstOrNull { it.name == name }

    }

}