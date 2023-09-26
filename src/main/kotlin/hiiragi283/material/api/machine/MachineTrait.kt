package hiiragi283.material.api.machine

enum class MachineTrait {
    PRIMITIVE,
    LASER,
    MELT;

    companion object {

        fun from(name: String): MachineTrait? = values().firstOrNull { it.name == name }

    }

}