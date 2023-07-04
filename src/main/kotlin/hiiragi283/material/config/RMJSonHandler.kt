package hiiragi283.material.config

import hiiragi283.material.RagiMaterials
import hiiragi283.material.material.CrystalType
import hiiragi283.material.material.HiiragiMaterial
import hiiragi283.material.material.MaterialRegistry
import hiiragi283.material.material.StandardState
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent
import java.io.File

class RMJSonHandler(event: FMLPreInitializationEvent) {

    //"instance/config/ragi_materials"のディレクトリを取得
    private val configs = File(event.modConfigurationDirectory, RagiMaterials.MODID)

    init {
        //フォルダがない場合は生成する
        if (!configs.exists()) configs.mkdirs()
    }

    //"instance/config/ragi_materials"フォルダ内にサンプルファイルを生成する
    fun writeJson() {
        //サンプルを生成しない場合はパス
        if (!RMConfig.generateSampleJson) return
        val sample = File(configs, "/example.json")
        //ファイルを新規作成
        try {
            //サンプルファイルがない場合は新規作成
            if (!sample.exists()) sample.createNewFile()
            //書き込み可能な場合
            if (sample.canWrite()) {
                val material = HiiragiMaterial.Builder("hiiragi", -1).build {
                    color = RagiMaterials.COLOR.rgb
                    crystalType = CrystalType.METAL
                    formula = "Tsubasa"
                    molar = 110.9
                    partsAdditional = listOf()
                    standardState = StandardState.SOLID
                    tempBoil = 1109
                    tempMelt = 283
                }
                sample.writeText(material.toJson(true), Charsets.UTF_8)
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
                ?.map { HiiragiMaterial.fromJson(it) } //Json String -> HiiragiMaterial
                ?.forEach { MaterialRegistry.registerMaterial(it) } //MaterialRegistryに登録
        } catch (e: Exception) {
            RagiMaterials.LOGGER.error(e) //念のため例外処理
        }
    }
}