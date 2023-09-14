package hiiragi283.material.config

import hiiragi283.material.RMReference
import hiiragi283.material.RagiMaterials
import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.api.material.jsonMaterialOf
import hiiragi283.material.api.material.materialOf
import hiiragi283.material.api.shape.HiiragiShapeTypes
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent
import java.io.File

class RMJSonHandler(event: FMLPreInitializationEvent) {

    //"instance/config/ragi_materials"のディレクトリを取得
    private val configs = File(event.modConfigurationDirectory, RMReference.MOD_ID)

    init {
        //フォルダがない場合は生成する
        if (!configs.exists()) configs.mkdirs()
    }

    companion object {

        private val CACHE: MutableList<HiiragiMaterial> = mutableListOf()

        fun register() {
            CACHE.forEach(HiiragiMaterial::register)
        }

    }

    //"instance/config/ragi_materials"フォルダ内にサンプルファイルを生成する
    fun writeJson() {
        //サンプルを生成しない場合はパス
        if (!RMConfig.MATERIAL.sampleJson) return
        val sample = File(configs, "/example.json")
        //ファイルを新規作成
        try {
            //サンプルファイルがない場合は新規作成
            if (!sample.exists()) sample.createNewFile()
            //書き込み可能な場合
            if (sample.canWrite()) {
                val material = materialOf("hiiragi", -1) {
                    color = RagiMaterials.COLOR.rgb
                    fluid = null
                    formula = "HIIRAGI"
                    molar = 110.9
                    tempBoil = 2830
                    tempMelt = 1109
                    shapeType = HiiragiShapeTypes.WILDCARD
                }
                //sample.writeText(material.toJson(true), Charsets.UTF_8)
            }
        } catch (e: Exception) {
            RagiMaterials.LOGGER.error(e)
        }
    }

    //"instance/config/ragi_materials"フォルダ内のファイルを読み取る
    fun readJson() {
        try {
            configs.listFiles()
                ?.filter { it.exists() && it.canRead() } //存在している && 読み取り可能
                ?.map { it.readText() } //Stringを読み取る
                ?.mapNotNull { jsonMaterialOf(it) } //Json String -> HiiragiMaterial
                ?.forEach { CACHE.add(it) } //CACHEに一時保存
        } catch (e: Exception) {
            RagiMaterials.LOGGER.error(e) //念のため例外処理
        }
    }

}