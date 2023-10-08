package hiiragi283.material.api.machine

enum class MachineTrait {
    CHEMICAL,
    CLEAN,
    LASER,
    MELT,
    SHIELD;

    companion object {

        fun from(name: String): MachineTrait? = values().firstOrNull { it.name == name }

    }

}