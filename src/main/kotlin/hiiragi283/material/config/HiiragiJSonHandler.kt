package hiiragi283.material.config

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import hiiragi283.material.RMReference
import hiiragi283.material.RagiMaterials
import hiiragi283.material.api.ingredient.FluidIngredient
import hiiragi283.material.api.ingredient.ItemIngredient
import hiiragi283.material.api.machine.MachineTrait
import hiiragi283.material.api.machine.MachineType
import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.api.material.materialOf
import hiiragi283.material.api.shape.HiiragiShape
import hiiragi283.material.api.shape.HiiragiShapeType
import hiiragi283.material.init.HiiragiRegistries
import hiiragi283.material.init.HiiragiShapes
import hiiragi283.material.init.materials.MaterialCommons
import hiiragi283.material.init.materials.MaterialElements
import hiiragi283.material.recipe.MachineRecipe
import hiiragi283.material.util.HiiragiJsonUtil
import hiiragi283.material.util.HiiragiLogger
import hiiragi283.material.util.itemStack
import net.minecraft.init.Blocks
import net.minecraft.init.Items
import net.minecraft.item.ItemStack
import net.minecraftforge.fluids.FluidRegistry
import net.minecraftforge.fluids.FluidStack
import java.io.File

object HiiragiJSonHandler {

    lateinit var configFile: File

    //instance/config/ragi_materials
    private val parentFile: File by lazy { File(configFile, RMReference.MOD_ID) }

    //instance/config/ragi_materials/shapes
    private val shapeFile: File by lazy { File(parentFile, "shapes") }

    //instance/config/ragi_materials/materials
    private val materialFile: File by lazy { File(parentFile, "materials") }

    //instance/config/ragi_materials/recipes
    private val recipeFile: File by lazy { File(parentFile, "recipes") }

    private val gson: Gson = GsonBuilder().setPrettyPrinting().create()

    fun init() {
        //フォルダがない場合は生成する
        if (!parentFile.exists()) {
            HiiragiLogger.info("Created Config Folder: $parentFile")
            parentFile.mkdirs()
        }
        if (!shapeFile.exists()) {
            HiiragiLogger.info("Created Config Folder: $shapeFile")
            shapeFile.mkdirs()
        }
        if (!materialFile.exists()) {
            HiiragiLogger.info("Created Config Folder: $materialFile")
            materialFile.mkdirs()
        }
        if (!recipeFile.exists()) {
            HiiragiLogger.info("Created Config Folder: $recipeFile")
            recipeFile.mkdirs()
        }

    }

    private fun getJsonObjects(file: File): List<JsonObject> {
        val files: Array<File> = file.listFiles() ?: return listOf()
        return files.filter(File::exists) //Fileが存在しているか
            .filter(File::isFile) //Fileオブジェクトがファイルか = フォルダではないか
            .filter(File::canRead) //Fileが読み取り可能か
            .map(File::readText) //Stringを読み取る
            .map { gson.fromJson(it, JsonObject::class.java) } //String -> JsonObject
    }

    //    Shape    //

    fun writeShape() {
        //サンプルを生成しない場合はパス
        if (!HiiragiConfigs.COMMON.generateSample) return
        val sample = File(shapeFile, "/sample.json")
        //ファイルを新規作成
        try {
            //サンプルファイルがない場合は新規作成
            if (!sample.exists()) sample.createNewFile()
            //書き込み可能な場合
            if (sample.canWrite()) {
                val shape: JsonElement = HiiragiShape("sample", 144).getJsonElement()
                sample.writeText(gson.toJson(shape), Charsets.UTF_8)
            }
        } catch (e: Exception) {
            HiiragiLogger.error(e)
        }
    }

    fun readShape() {
        try {
            getJsonObjects(shapeFile)
                .mapNotNull(HiiragiJsonUtil::hiiragiShape) //JsonObject -> HiiragiShape
                .forEach(shapes::add) //shapesに一時保存
        } catch (e: Exception) {
            HiiragiLogger.error(e)
        }
    }

    private val shapes: MutableList<HiiragiShape> = mutableListOf()

    fun registerShape() {
        shapes.forEach(HiiragiShape::register)
    }

    //    Material    //

    fun writeMaterial() {
        //サンプルを生成しない場合はパス
        if (!HiiragiConfigs.COMMON.generateSample) return
        val sample = File(materialFile, "/sample.json")
        //ファイルを新規作成
        try {
            //サンプルファイルがない場合は新規作成
            if (!sample.exists()) sample.createNewFile()
            //書き込み可能な場合
            if (sample.canWrite()) {
                val material: JsonElement = materialOf("sample", -1) {
                    color = RagiMaterials.COLOR.rgb
                    formula = "HIIRAGI"
                    hasFluid = false
                    molar = 110.9
                    shapeType = HiiragiShapeType.build { shapes.addAll(HiiragiRegistries.SHAPE.getValues()) }
                    tempBoil = 2830
                    tempMelt = 1109
                }.getJsonElement()
                sample.writeText(gson.toJson(material), Charsets.UTF_8)
            }
        } catch (e: Exception) {
            HiiragiLogger.error(e)
        }
    }

    fun readMaterial() {
        try {
            getJsonObjects(materialFile)
                .mapNotNull(HiiragiJsonUtil::hiiragiMaterial) //JsonObject -> HiiragiMaterial
                .forEach(materials::add) //materialsに一時保存
        } catch (e: Exception) {
            HiiragiLogger.error(e) //念のため例外処理
        }
    }

    private val materials: MutableList<HiiragiMaterial> = mutableListOf()

    fun registerMaterial() {
        materials.forEach(HiiragiMaterial::register)
    }

    //    MachineRecipe    //

    fun writeRecipe() {
        //サンプルを生成しない場合はパス
        if (!HiiragiConfigs.COMMON.generateSample) return
        val sample = File(recipeFile, "/sample.json")
        //ファイルを新規作成
        try {
            //サンプルファイルがない場合は新規作成
            if (!sample.exists()) sample.createNewFile()
            //書き込み可能な場合
            if (sample.canWrite()) {
                val recipe: JsonElement = MachineRecipe.build(
                    MachineType.TEST
                ) {
                    inputItems.add(ItemIngredient.Stacks(ItemStack(Items.DYE, 1, 15), count = 6))
                    inputItems.add(ItemIngredient.Blocks(Blocks.COBBLESTONE, count = 5))
                    inputItems.add(ItemIngredient.Items(Items.DIAMOND, count = 4))
                    inputItems.add(ItemIngredient.OreDicts("ingotIron", count = 3))
                    inputItems.add(ItemIngredient.Materials(MaterialCommons.STEEL, count = 2))
                    inputItems.add(ItemIngredient.Shapes(HiiragiShapes.BLOCK))
                    inputFluids.add(FluidIngredient(FluidRegistry.WATER, amount = 250))
                    inputFluids.add(FluidIngredient(MaterialElements.HELIUM))
                    outputItems.add(Blocks.DIAMOND_BLOCK::itemStack)
                    outputFluids.add { FluidStack(FluidRegistry.LAVA, 4000) }
                    traits.addAll(MachineTrait.values())
                }.getJsonElement()
                sample.writeText(gson.toJson(recipe), Charsets.UTF_8)
            }
        } catch (e: Exception) {
            HiiragiLogger.error(e)
        }
    }

    fun registerRecipe() {
        try {
            val files: Array<File> = recipeFile.listFiles() ?: return
            files.filter(File::exists) //Fileが存在しているか
                .filter(File::isFile) //Fileオブジェクトがファイルか = フォルダではないか
                .filter(File::canRead) //Fileが読み取り可能か
                .map { it.readText() to it.nameWithoutExtension } //テキストとファイル名を読み取る
                .map { gson.fromJson(it.first, JsonObject::class.java) to it.second } //JsonObjectに変換
                .forEach(HiiragiJsonUtil::machineRecipe) //IMachineRecipeの登録
        } catch (e: Exception) {
            HiiragiLogger.error(e)
        }
    }

}