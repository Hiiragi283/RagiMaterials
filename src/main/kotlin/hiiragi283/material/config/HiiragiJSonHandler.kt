package hiiragi283.material.config

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import hiiragi283.material.RMReference
import hiiragi283.material.RagiMaterials
import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.api.material.materialOf
import hiiragi283.material.api.shape.HiiragiShapeTypes
import hiiragi283.material.util.deserializeMaterial
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent
import java.io.File

object HiiragiJSonHandler {

    lateinit var event: FMLPreInitializationEvent

    //"instance/config/ragi_materials"のディレクトリを取得
    private val configs: File by lazy { File(event.modConfigurationDirectory, RMReference.MOD_ID) }

    private val gson: Gson = GsonBuilder().setPrettyPrinting().create()

    fun init() {
        RagiMaterials.LOGGER.info("Config Folder: $configs")
        //フォルダがない場合は生成する
        if (!configs.exists()) configs.mkdirs()
    }

    //"instance/config/ragi_materials"フォルダ内にサンプルファイルを生成する
    fun writeJson() {
        //サンプルを生成しない場合はパス
        if (!HiiragiConfigs.MATERIAL.sampleJson) return
        val sample = File(configs, "/example.json")
        //ファイルを新規作成
        try {
            //サンプルファイルがない場合は新規作成
            if (!sample.exists()) sample.createNewFile()
            //書き込み可能な場合
            if (sample.canWrite()) {
                val material: JsonElement = materialOf("example", -1) {
                    color = RagiMaterials.COLOR.rgb
                    fluidSupplier = { null }
                    formula = "HIIRAGI"
                    molar = 110.9
                    tempBoil = 2830
                    tempMelt = 1109
                    shapeType = HiiragiShapeTypes.WILDCARD
                }.serializableElement
                sample.writeText(gson.toJson(material), Charsets.UTF_8)
            }
        } catch (e: Exception) {
            RagiMaterials.LOGGER.error(e)
        }
    }

    //"instance/config/ragi_materials"フォルダ内のファイルを読み取る
    fun readJson() {
        try {
            val files: Array<File> = configs.listFiles() ?: return
            files.filter(File::exists) //Fileが存在しているか
                .filter(File::isFile) //Fileオブジェクトがファイルか = フォルダではないか
                .filter(File::canRead) //Fileが読み取り可能か
                .map(File::readText) //Stringを読み取る
                .map { gson.fromJson(it, JsonObject::class.java) } //String -> JsonObject
                .mapNotNull(::deserializeMaterial) //JsonObject -> HiiragiMaterial
                .forEach(CACHE::add) //CACHEに一時保存
        } catch (e: Exception) {
            RagiMaterials.LOGGER.error(e) //念のため例外処理
        }
    }

    private val CACHE: MutableList<HiiragiMaterial> = mutableListOf()

    fun register() {
        CACHE.forEach(HiiragiMaterial::register)
    }

}