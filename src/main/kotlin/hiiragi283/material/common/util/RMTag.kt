package hiiragi283.material.common.util

import net.devtech.arrp.json.tags.JTag

class RMTag : JTag() {

    private val list: MutableList<String> = mutableListOf()

    fun add(identifier: String): RMTag = also { list.add(identifier) }

    fun tag(identifier: String): RMTag = also { list.add("#$identifier") }

}