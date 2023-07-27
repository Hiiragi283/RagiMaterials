package hiiragi283.material.common.util

import net.devtech.arrp.json.tags.JTag
import net.minecraft.util.Identifier

object TagUtil {

    fun addTags(tags: Collection<Identifier>): JTag {
        val jTag = JTag.tag()
        tags.forEach { jTag.
        tag(it) }
        return jTag
    }

    fun addIds(tags: Collection<Identifier>): JTag {
        val jTag = JTag.tag()
        tags.forEach { jTag.add(it) }
        return jTag
    }

}