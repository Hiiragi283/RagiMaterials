package hiiragi283.ragi_materials.config

import hiiragi283.ragi_materials.Reference
import net.minecraftforge.common.config.Config

@Config(modid = Reference.MOD_ID, category = "")
object RagiConfig {

    @Config.Name("Debug Mode")
    @Config.LangKey(DebugMode.prefix)
    @JvmField
    var debugMode = DebugMode()

    @Config.Name("Integration")
    @Config.LangKey(Integration.prefix)
    @JvmField
    var integration = Integration()

    @Config.Name("Custom Material Registry")
    @Config.Comment(
            "Add your custom materials in this format: index:name:type:color:formula:molar_mass:melting:boiling" +
                    "\nindex: Int ... used for metadata, limited in 1023 <= index <= maxMaterials" +
                    "\nname: String ... used for translation key and ore dictionary" +
                    "\ntype: Enum ... only available: dust, fluid, liquid, metal, gas, metal_radio, semimetal" +
                    "\ncolor: Int ... use color code" +
                    "\nformula: String ... show its chemical formula" +
                    "\nmolar_mass: Float ... show its molar mass [g/mol]" +
                    "\nmelting: Int ... show its melting point [°C]" +
                    "\nboiling: Int ... show its boiling point [°C]"
    )
    @Config.LangKey(Material.prefix)
    @JvmField
    var material = Material()

    @Config.Name("Recipe Map")
    @Config.Comment(
            "Add your custom recipes in this format: input;output\nThe stack format: mod:id:meta"
    )
    @Config.LangKey(RecipeMap.prefix)
    @JvmField
    var recipeMap = RecipeMap()

    @Config.Name("Utility")
    @Config.LangKey(Utility.prefix)
    @JvmField
    var utility = Utility()

    class DebugMode {

        companion object {
            const val prefix = "config.${Reference.MOD_ID}.debug"
        }

        @Config.LangKey("${prefix}.debug")
        @Config.Comment("If true, you can use some debug items but Ragi Library throws sooo many debug logs...")
        @JvmField
        var isDebug = false

    }

    class Integration {

        companion object {
            const val prefix = "config.${Reference.MOD_ID}.integration"
        }

        @Config.LangKey("${prefix}.ender_io")
        @Config.Comment("Enable integration for Ender IO")
        @JvmField
        var enableEIO = true

        @Config.LangKey("${prefix}.mekanism")
        @Config.Comment("Enable integration for Mekanism")
        @JvmField
        var enableMek = true

        @Config.LangKey("${prefix}.expansion")
        @Config.Comment("Enable integration for Thermal Expansion")
        @JvmField
        var enableTE = true

        @Config.LangKey("${prefix}.foundation")
        @Config.Comment("Enable integration for Thermal Foundation")
        @JvmField
        var enableTF = true
    }

    class Material {

        companion object {
            const val prefix = "config.${Reference.MOD_ID}.material"
        }

        @Config.Name("Enable radioactive decay")
        @Config.LangKey("$prefix.enable_decay")
        @JvmField
        var enableDecay = false

    }

    class RecipeMap {

        companion object {
            const val prefix = "config.${Reference.MOD_ID}.recipe_map"
        }

        @Config.Name("Blazing Forge - Fuel Map")
        @Config.LangKey("${prefix}.blazing_forge")
        @Config.RequiresMcRestart
        @JvmField
        var fuelBlazingForge = arrayOf(
                "lava;100",
                "pyrotheum;10"
        )

    }

    class Utility {

        companion object {
            const val prefix = "config.${Reference.MOD_ID}.utility"
        }

    }

    //configからmaterialを登録するメソッド
    /*fun registerMaterial() {
        for (value in material.listMaterials) {
            //valueをばらしてプロパティを得る
            val listProperty = value.split(":")
            val index = listProperty[0].toInt()
            val name = listProperty[1]
            val typeRaw = listProperty[2]
            var type = TypeRegistry.WILDCARD
            val color = Color(listProperty[3].toIntOrNull(16)!!)
            val formula = listProperty[4]
            val molar = listProperty[5].toFloat()
            val tempMelt = listProperty[6].toInt()
            val tempBoil = listProperty[7].toInt()
            //MaterialTypeの確認
            if (TypeRegistry.map[typeRaw] !== null) {
                type = TypeRegistry.map[typeRaw]!!
            }
            //indexが1023以上maxMaterials以下，かつtypeがWILDCARDでない場合，materialを登録する
            if (index in 1023..material.maxMaterials && type != TypeRegistry.WILDCARD) {
                val material = MaterialBuilder(index, name, type).apply {
                    this.color = color
                    this.formula = formula
                    this.molar = molar
                    this.tempMelt = tempMelt
                    this.tempBoil = tempBoil
                }.register()
            }
        }
    }*/

    //configからレシピを登録するメソッド
    fun registerRecipe() {}
}