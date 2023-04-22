package ragi_materials.core.config

import com.google.gson.Gson
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import ragi_materials.core.RagiMaterials
import ragi_materials.core.material.RagiMaterial
import ragi_materials.core.material.type.EnumCrystalType
import ragi_materials.core.material.type.TypeRegistry
import ragi_materials.core.util.getEnumRarity
import ragi_materials.core.util.getMaterialFromName
import net.minecraft.item.EnumRarity
import java.awt.Color
import java.io.*

/**
 * Thanks to defeatedcrow!
 * Source: https://github.com/defeatedcrow/JsonSampleMod/blob/main/src/main/java/com/defeatedcrow/jsonsample/SampleConfigMaker.java
 */

object JsonConfig {

    var config: MaterialList? = null

    fun loadJson() {
        //configフォルダが取得済みの場合
        val file = File(RagiMaterials.CONFIG, "/material.json") //jsonファイルのパスを生成
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

    fun generateJson() {
        //既存のjsonファイルがない場合
        if (config == null) {
            config = MaterialList() //デフォルト値は[EMPTY]
            val file = File(RagiMaterials.CONFIG, "/material.json") //jsonファイルのパスを生成
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
            val index: Int,
            val name: String,
            val type: String,
            var burnTime: Int,
            var color: Int?,
            var components: Map<String, Int>,
            var crystalType: String,
            var decayed: String?,
            var formula: String?,
            var molar: Float?,
            var oredictAlt: String?,
            var rarity: String,
            var tempBoil: Int?,
            var tempMelt: Int?
    ) {

        companion object {
            val EMPTY = MaterialConfig(
                    -1,
                    "example",
                    TypeRegistry.INTERNAL.name,
                    -1,
                    0xFFFFFF,
                    mapOf("empty" to 1),
                    EnumCrystalType.NONE.texture,
                    "example",
                    "EXAMPLE",
                    0.0f,
                    "Sample",
                    EnumRarity.COMMON.rarityName,
                    0,
                    0
            )
        }

        fun toRagiMaterial() {
            RagiMaterial.Builder(this.index, this.name, TypeRegistry.getType(this.type)).setComponents(convertList()).also { builder ->
                builder.burnTime = burnTime
                color?.let { builder.color = Color(it) }
                builder.crystalType = EnumCrystalType.getType(crystalType)
                builder.decayed = decayed?.let { getMaterialFromName(it) }
                formula?.let { builder.formula = it }
                molar?.let { builder.molar = it }
                builder.oredictAlt = oredictAlt
                builder.rarity = getEnumRarity(rarity)
                tempBoil?.let { builder.tempBoil = it }
                tempMelt?.let { builder.tempMelt = it }
            }.build()
        }

        private fun convertList(): List<Pair<RagiMaterial, Int>> {
            val map: MutableMap<RagiMaterial, Int> = mutableMapOf()
            components.forEach {
                var material = getMaterialFromName(it.key)
                //material取得できなかった場合，化学式の文字列として認識される
                if (material.isEmpty()) material = RagiMaterial.Formula(it.key).build()
                map[material] = it.value
            }
            return map.toList()
        }
    }
}