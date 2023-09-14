package hiiragi283.material.api.registry

object HiiragiRegistryOld {

    //    HiiragiMaterial    //

    /*fun initMaterial() {
        MinecraftForge.EVENT_BUS.post(MaterialRegistryEvent())
        //REGISTRYをindex順に並び変える
        val sorted = materialInternal.toList().sortedBy { it.second.index }
        materialInternal.clear()
        materialInternal.putAll(sorted)
        //CACHEを消す
        CACHE.clear()
        //indexを昇順に並べて代入する
        INDEX_MAP.putAll(materialInternal.map { it.value.index to it.value }
            .toMap())
    }*/

}