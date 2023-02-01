package hiiragi283.ragi_materials.config

import hiiragi283.ragi_materials.util.RagiLogger
import net.minecraftforge.common.config.Configuration
import java.io.File

/*
  Thanks to defeatedcrow!
  Source: https://github.com/defeatedcrow/HeatAndClimateLib/blob/1.12.2_v3/main/java/defeatedcrow/hac/config/ClimateConfig.java
          https://github.com/defeatedcrow/HeatAndClimateLib/blob/1.12.2_v3/main/java/defeatedcrow/hac/config/CoreConfigDC.java
*/

object RagiConfig {

    //変数の宣言
    //Debug Setting
    var isDebug = false
    //Recipe
    private var listForgeBurning = arrayOf(
        "minecraft:cobblestone:0;minecraft:magma:0",
        "ragi_materials:ingot:3;ragi_materials:ingot_hot:3",
        "ragi_materials:ingot:4;ragi_materials:ingot_hot:4",
        "ragi_materials:ingot:12;ragi_materials:ingot_hot:12",
        "ragi_materials:ingot:13;ragi_materials:ingot_hot:13",
        "ragi_materials:ingot:25;ragi_materials:ingot_hot:25",
        "minecraft:iron_ingot:0;ragi_materials:ingot_hot:26",
        "ragi_materials:ingot:26;ragi_materials:ingot_hot:26",
        "ragi_materials:ingot:27;ragi_materials:ingot_hot:27",
        "ragi_materials:ingot:28;ragi_materials:ingot_hot:28",
        "ragi_materials:ingot:29;ragi_materials:ingot_hot:29",
        "ragi_materials:ingot:30;ragi_materials:ingot_hot:30",
        "ragi_materials:ingot:31;ragi_materials:ingot_hot:31",
        "ragi_materials:ingot:47;ragi_materials:ingot_hot:47",
        "ragi_materials:ingot:49;ragi_materials:ingot_hot:49",
        "ragi_materials:ingot:50;ragi_materials:ingot_hot:50",
        "ragi_materials:ingot:51;ragi_materials:ingot_hot:51",
        "ragi_materials:ingot:60;ragi_materials:ingot_hot:60",
        "ragi_materials:ingot:62;ragi_materials:ingot_hot:62",
        "minecraft:gold_ingot:0;ragi_materials:ingot_hot:79",
        "ragi_materials:ingot:79;ragi_materials:ingot_hot:79",
        "ragi_materials:ingot:82;ragi_materials:ingot_hot:82",
        "ragi_materials:ingot:83;ragi_materials:ingot_hot:83"
    )
    private var listForgeBlasting = arrayOf(
        "ragi_materials:ingot:14;ragi_materials:ingot_hot:14",
        "ragi_materials:ingot:22;ragi_materials:ingot_hot:22",
        "ragi_materials:ingot:24;ragi_materials:ingot_hot:24",
        "ragi_materials:ingot:40;ragi_materials:ingot_hot:40",
        "ragi_materials:ingot:41;ragi_materials:ingot_hot:41",
        "ragi_materials:ingot:42;ragi_materials:ingot_hot:42",
        "ragi_materials:ingot:44;ragi_materials:ingot_hot:44",
        "ragi_materials:ingot:45;ragi_materials:ingot_hot:45",
        "ragi_materials:ingot:46;ragi_materials:ingot_hot:46",
        "ragi_materials:ingot:72;ragi_materials:ingot_hot:72",
        "ragi_materials:ingot:78;ragi_materials:ingot_hot:78"
    )
    private var listForgeHellfire = arrayOf(
        "ragi_materials:ingot:73;ragi_materials:ingot_hot:73",
        "ragi_materials:ingot:74;ragi_materials:ingot_hot:74",
        "ragi_materials:ingot:76;ragi_materials:ingot_hot:76"
    )
    var mapForgeBurning: MutableMap<String, String> = mutableMapOf()
    var mapForgeBlasting: MutableMap<String, String> = mutableMapOf()
    var mapForgeHellfire: MutableMap<String, String> = mutableMapOf()
    //Utility
    var listMaxStack = arrayOf(
        "forge:bucketfilled",
        "minecraft:bed",
        "minecraft:beetroot_soup",
        "minecraft:birch_boat",
        "minecraft:boat",
        "minecraft:bucket",
        "minecraft:cake",
        "minecraft:chest_minecart",
        "minecraft:command_block_minecart",
        "minecraft:dark_oak_boat",
        "minecraft:diamond_horse_armor",
        "minecraft:egg",
        "minecraft:enchanted_book",
        "minecraft:ender_pearl",
        "minecraft:furnace_minecart",
        "minecraft:golden_horse_armor",
        "minecraft:hopper_minecart",
        "minecraft:iron_horse_armor",
        "minecraft:jungle_boat",
        "minecraft:lava_bucket",
        "minecraft:minecart",
        "minecraft:mushroom_stew",
        "minecraft:rabbit_stew",
        "minecraft:record_11",
        "minecraft:record_13",
        "minecraft:record_blocks",
        "minecraft:record_cat",
        "minecraft:record_chirp",
        "minecraft:record_far",
        "minecraft:record_mall",
        "minecraft:record_mellohi",
        "minecraft:record_stal",
        "minecraft:record_strad",
        "minecraft:record_wait",
        "minecraft:record_ward",
        "minecraft:saddle",
        "minecraft:sign",
        "minecraft:snowball",
        "minecraft:spruce_boat",
        "minecraft:tnt_minecart",
        "minecraft:water_bucket",
        "minecraft:written_book"
    )

    //configを読み込むメソッド
    fun load(file: File) {
        //configファイルを指定
        val config = Configuration(File(file, "ragi_materials.cfg"))
        try {
            //configの読み込み
            config.load()
            //各値の取得
            isDebug = config.get("debug setting", "Debug Log", isDebug, "If true, Ragi Library throws sooo many debug logs...").boolean
            listForgeBurning = config.get("Recipe Map", "Recipes for Forge Furnace - Burning tier", listForgeBurning).stringList
            listForgeBlasting = config.get("Recipe Map", "Recipes for Forge Furnace - Blasting tier", listForgeBlasting).stringList
            listForgeHellfire = config.get("Recipe Map", "Recipes for Forge Furnace - Hellfire tier", listForgeHellfire).stringList
            mapForgeBurning = convertListToMap(listForgeBurning, mapForgeBurning)
            mapForgeBlasting = convertListToMap(listForgeBlasting, mapForgeBlasting)
            mapForgeHellfire = convertListToMap(listForgeHellfire, mapForgeHellfire)
            listMaxStack = config.get("utility", "Override Max Stack Size", listMaxStack, "The maximum stack size of items added to this list will be changed").stringList
        } catch (e: Exception) {
            //エラーを出力
            e.printStackTrace()
        } finally {
            //configを保存
            config.save()
        }
    }

    //String型のlist/arrayをmapに変換するメソッド
    private fun convertListToMap(list: Array<String>, map: MutableMap<String, String>): MutableMap<String, String> {
        for (key in list) {
            map[key.split(";")[0]] = key.split(";")[1]
            RagiLogger.infoDebug("${key.split(";")[0]} to ${map[key.split(";")[0]]}")
        }
        return map
    }
}