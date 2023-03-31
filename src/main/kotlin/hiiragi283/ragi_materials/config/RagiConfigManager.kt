package hiiragi283.ragi_materials.config

import com.google.gson.Gson
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import hiiragi283.ragi_materials.material.MaterialUtil
import hiiragi283.ragi_materials.material.RagiMaterial
import hiiragi283.ragi_materials.material.type.EnumCrystalType
import hiiragi283.ragi_materials.material.type.TypeRegistry
import hiiragi283.ragi_materials.util.RagiUtil
import net.minecraft.item.EnumRarity
import java.awt.Color
import java.io.*

/*
  Thanks to defeatedcrow!
  Source: https://github.com/defeatedcrow/JsonSampleMod/blob/main/src/main/java/com/defeatedcrow/jsonsample/SampleConfigMaker.java
*/

object RagiConfigManager {

    var config: MaterialList? = null

    fun loadJson() {
        //configフォルダが取得済みの場合
        if (RagiDirectory.config !== null) {
            val file = File(RagiDirectory.config, "/material.json") //jsonファイルのパスを生成
            try {
                //Jsonファイルが存在し，かつ読み取り可能な場合
                if (file.exists() && file.canRead()) {
                    val streamFile = FileInputStream(file.path) //Fileのstreamを開ける
                    val streamInput = InputStreamReader(streamFile) //Inputのstreamを開ける
                    val reader = JsonReader(streamInput) //JsonReaderを開ける

                    val gson = Gson()
                    config = gson.fromJson(reader, MaterialList::class.java) //Jsonから値を読み取る
                    if (config !== null) {
                        for (material in config!!.list) {
                            if (material != MaterialConfig.EMPTY) material.toRagiMaterial() //読み取った素材が有効な場合，登録する
                        }
                    }

                    reader.close() //JsonReaderを閉じる
                    streamInput.close() //Inputのstreamを閉じる
                    streamFile.close() //Fileのstreamを閉じる
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    fun generateJson() {
        //configフォルダが取得済み，かつ既存のjsonファイルがない場合
        if (RagiDirectory.config !== null && config == null) {
            config = MaterialList() //デフォルト値は[EMPTY]
            val file = File(RagiDirectory.config, "/material.json") //jsonファイルのパスを生成
            //jsonファイルがない場合
            if (!file.exists()) {
                //Jsonファイルの親フォルダがない場合，新たに生成する
                if (!file.parentFile.exists()) file.parentFile.mkdirs()
                try {
                    file.createNewFile() //Jsonファイルを新規で生成
                } catch (e: IOException) {
                    e.printStackTrace()
                }
                try {
                    //Jsonファイルが書き込み可能な場合
                    if (file.canWrite()) {
                        val streamFile = FileOutputStream(file.path) //Fileのstreamを開ける
                        val streamOutput = OutputStreamWriter(streamFile) //Outputのstreamを開ける
                        val writer = JsonWriter(streamOutput) //JsonWriterを開ける

                        writer.setIndent("  ") //インデントを設定
                        val gson = Gson()
                        gson.toJson(config, MaterialList::class.java, writer) //Jsonにデフォルト値を書き込む

                        writer.close() //JsonWriterを閉じる
                        streamOutput.close() //Outputのstreamを閉じる
                        streamFile.close() //Fileのstreamを閉じる
                    }
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }
    }

    class MaterialList(val list: List<MaterialConfig> = listOf(MaterialConfig.EMPTY))

    data class MaterialConfig private constructor(
            val index: Int = -1,
            val name: String = "example",
            val type: String = TypeRegistry.INTERNAL.name,
            var burnTime: Int = -1,
            var color: Int = 0xFFFFFF,
            var components: Map<String, Int> = mapOf("empty" to 1),
            var crystalType: String = EnumCrystalType.NONE.texture,
            var decayed: String? = "example",
            var formula: String? = "EXAMPLE",
            var molar: Float? = 0.0f,
            var oredictAlt: String? = "sample",
            var rarity: String = EnumRarity.COMMON.rarityName,
            var tempBoil: Int? = 0,
            var tempMelt: Int? = 0
    ) {

        companion object {
            val EMPTY = MaterialConfig()
        }

        fun toRagiMaterial() {
            RagiMaterial.Builder(this.index, this.name, TypeRegistry.getType(this.type)).setComponents(convertList()).also {
                it.burnTime = burnTime
                it.color = Color(color)
                it.crystalType = EnumCrystalType.getType(crystalType)
                it.decayed = decayed?.let { it1 -> MaterialUtil.getMaterial(it1) }
                it.formula = formula
                it.molar = molar
                it.oredictAlt = oredictAlt
                it.rarity = RagiUtil.getEnum(rarity)
                it.tempBoil = tempBoil
                it.tempMelt = tempMelt
            }.build()
        }

        private fun convertList(): List<Pair<RagiMaterial, Int>> {
            val map: MutableMap<RagiMaterial, Int> = mutableMapOf()
            components.forEach { map[MaterialUtil.getMaterial(it.key)] = it.value }
            return map.toList()
        }
    }
}