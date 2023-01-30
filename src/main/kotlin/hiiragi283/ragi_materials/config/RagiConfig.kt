package hiiragi283.ragi_materials.config

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
            listMaxStack = config.get("utility", "Override Max Stack Size", listMaxStack, "The maximum stack size of items added to this list will be changed").stringList
        } catch (e: Exception) {
            //エラーを出力
            e.printStackTrace()
        } finally {
            //configを保存
            config.save()
        }
    }
}