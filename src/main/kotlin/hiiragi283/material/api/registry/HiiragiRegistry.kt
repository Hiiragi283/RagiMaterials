package hiiragi283.material.api.registry

import hiiragi283.material.RagiMaterials
import hiiragi283.material.api.event.MaterialRegistryEvent
import hiiragi283.material.api.event.ShapeRegistryEvent
import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.api.material.MaterialStack
import hiiragi283.material.api.part.HiiragiPart
import hiiragi283.material.api.recipe.CrushingRecipe
import hiiragi283.material.api.recipe.RockGenerationRecipe
import hiiragi283.material.api.shape.HiiragiShape
import hiiragi283.material.util.MetaResourceLocation
import hiiragi283.material.util.hiiragiLocation
import hiiragi283.material.util.toItemStack
import hiiragi283.material.util.toMetaLocation
import net.minecraft.block.Block
import net.minecraft.block.state.IBlockState
import net.minecraft.init.Blocks
import net.minecraft.item.ItemStack
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fluids.Fluid
import net.minecraftforge.fluids.FluidRegistry
import net.minecraftforge.fluids.FluidStack
import net.minecraftforge.fluids.capability.CapabilityFluidHandler
import net.minecraftforge.oredict.OreDictionary
import net.minecraftforge.registries.IForgeRegistry
import net.minecraftforge.registries.RegistryBuilder

object HiiragiRegistry {

    @JvmField
    val CRUSHING: IForgeRegistry<CrushingRecipe> = RegistryBuilder<CrushingRecipe>()
        .allowModification()
        .disableSaving()
        .setName(hiiragiLocation("crush"))
        .setType(CrushingRecipe::class.java)
        .create()

    @JvmField
    val ROCK_GENERATION: IForgeRegistry<RockGenerationRecipe> = RegistryBuilder<RockGenerationRecipe>()
        .allowModification()
        .disableSaving()
        .setName(hiiragiLocation("rock_generation"))
        .setType(RockGenerationRecipe::class.java)
        .create()

    fun initFluid() {
        getMaterials()
            .mapNotNull { it.fluidSupplier?.get() }
            .filterNot { FluidRegistry.isFluidRegistered(it) }
            .forEach {
                FluidRegistry.registerFluid(it)
                FluidRegistry.addBucketForFluid(it)
            }
    }

    //    HiiragiShape    //

    val SHAPE: Map<String, HiiragiShape>
        get() = shapeInternal
    private val shapeInternal: HashMap<String, HiiragiShape> = hashMapOf()

    fun initShape() {
        MinecraftForge.EVENT_BUS.post(ShapeRegistryEvent())
    }

    @JvmStatic
    fun getShapes(): Collection<HiiragiShape> = shapeInternal.values

    @JvmStatic
    fun getShape(name: String): HiiragiShape = shapeInternal.getOrDefault(name, HiiragiShape.EMPTY)

    @JvmStatic
    fun registerShape(shape: HiiragiShape) {

        val name = shape.name
        shapeInternal[name]?.let {
            RagiMaterials.LOGGER.warn("$shape is already registered!")
            return
        }
        shapeInternal[name] = shape

    }

    //    HiiragiMaterial    //

    private val MATERIAL: Map<String, HiiragiMaterial>
        get() = materialInternal
    private val materialInternal: LinkedHashMap<String, HiiragiMaterial> = linkedMapOf()
    private val CACHE: HashMap<Int, HiiragiMaterial> = hashMapOf()
    private val INDEX_MAP: HashMap<Int, HiiragiMaterial> = hashMapOf()

    fun initMaterial() {
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
    }

    @JvmStatic
    fun getMaterials(): Collection<HiiragiMaterial> = materialInternal.values

    @JvmStatic
    fun getMaterial(name: String): HiiragiMaterial =
        materialInternal.getOrDefault(name, HiiragiMaterial.EMPTY)

    @JvmStatic
    fun getMaterial(fluid: Fluid) = getMaterial(fluid.name)

    @JvmStatic
    fun getMaterial(stack: FluidStack) = getMaterial(stack.fluid)


    @Deprecated("")
    @JvmStatic
    fun getMaterial(index: Int): HiiragiMaterial =
        INDEX_MAP.getOrDefault(index, HiiragiMaterial.EMPTY)

    @JvmStatic
    fun registerMaterial(material: HiiragiMaterial) {

        //EMPTYを渡された場合はパス
        if (material == HiiragiMaterial.EMPTY) return

        //名前が空の場合はパス
        val name = material.name
        if (name.isEmpty()) {
            RagiMaterials.LOGGER.warn("The name of $material is empty!")
            return
        }

        //同じ名前で登録されていた場合はパス
        if (materialInternal[name] !== null) {
            RagiMaterials.LOGGER.warn("$material is already registered!")
            return
        }

        //登録を行う
        materialInternal[name] = material

        //番号が0 <= index <= 32767でない場合はパス
        val index = material.index
        if (index !in 0..32767) {
            RagiMaterials.LOGGER.warn("The index of $material is not in 0 to 32767!")
            return
        }

        //同じ番号で登録されていた場合はパス
        if (CACHE[index] !== null) {
            RagiMaterials.LOGGER.warn("The index: $index is already registered by $material")
            return
        }

        //CACHEに保存する
        CACHE[index] = material

    }

    //    HiiragiPart    //

    val PART: Map<String, HiiragiPart>
        get() = partInternal
    private val partInternal: HashMap<String, HiiragiPart> = hashMapOf()

    fun initPart() {
        createAllParts()
            .forEach { registerTag(it.getOreDicts(), it) }
    }

    @JvmStatic
    fun createAllParts(): List<HiiragiPart> =
        getShapes()
            .flatMap { shape -> getMaterials().map { HiiragiPart(shape, it) } }

    @JvmStatic
    fun getPart(oredict: String): HiiragiPart =
        partInternal.getOrDefault(oredict, HiiragiPart.EMPTY)

    @JvmStatic
    fun getAllParts(): Collection<HiiragiPart> = partInternal.values

    @JvmStatic
    fun getParts(oredict: String): List<HiiragiPart> =
        listOf(getPart(oredict)).filterNot(HiiragiPart::isEmpty)

    @JvmStatic
    fun getParts(oredicts: Collection<String>): List<HiiragiPart> =
        oredicts.map(HiiragiRegistry::getPart).filterNot(HiiragiPart::isEmpty)

    @JvmStatic
    fun getParts(state: IBlockState): List<HiiragiPart> {
        return state
            .let(IBlockState::toItemStack)
            .let(HiiragiRegistry::getParts)
    }

    @JvmStatic
    fun getParts(stack: ItemStack): List<HiiragiPart> {
        if (stack.isEmpty) return listOf()
        return stack.let(OreDictionary::getOreIDs)
            .map(OreDictionary::getOreName)
            .map(HiiragiRegistry::getPart)
            .filterNot(HiiragiPart::isEmpty)
    }

    @JvmStatic
    fun getStacks(stack: ItemStack): List<MaterialStack> {
        if (stack.isEmpty) return listOf()
        //液体から素材のデータを取得しようと試みる
        return stack.getCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY, null)
            ?.tankProperties
            ?.mapNotNull { it.contents }
            ?.map { MaterialStack(it) } ?: listOf()
    }

    @JvmStatic
    fun registerTag(oredict: String, part: HiiragiPart) {

        if (part.isEmpty()) {
            RagiMaterials.LOGGER.warn("The part: $part is EMPTY!")
            return
        }

        partInternal[oredict]?.let {
            RagiMaterials.LOGGER.warn("The part: $it will be overrided by $oredict!")
            return
        }

        partInternal[oredict] = part
    }

    @JvmStatic
    fun registerTag(oredicts: Collection<String>, part: HiiragiPart) {
        oredicts.forEach { registerTag(it, part) }
    }

    //    Heat Source    //

    val HEAT_SOURCE: Map<MetaResourceLocation, Int>
        get() = heatInternal
    private val heatInternal: HashMap<MetaResourceLocation, Int> = hashMapOf()

    fun initHeatSource() {
        registerHeatSource(Blocks.FIRE, 1200 + 273)
        registerHeatSource(Blocks.LAVA, FluidRegistry.LAVA.temperature)
        registerHeatSource(Blocks.LIT_FURNACE, 800 + 273)
        registerHeatSource(Blocks.MAGMA, 800 + 273)
        registerHeatSource(Blocks.TORCH, 400 + 273)
    }

    @JvmStatic
    fun getHeat(metaLocation: MetaResourceLocation): Int =
        heatInternal.getOrDefault(metaLocation, 273)

    @JvmStatic
    fun getHeat(state: IBlockState): Int = getHeat(state.toMetaLocation())

    @JvmStatic
    fun getHeat(block: Block): Int = getHeat(block.defaultState)

    @JvmStatic
    fun registerHeatSource(metaLocation: MetaResourceLocation, temp: Int) {
        if (heatInternal.containsKey(metaLocation)) {
            RagiMaterials.LOGGER.error("The heat source: $metaLocation was already registered!")
            return
        }
        heatInternal[metaLocation] = temp
    }

    @JvmStatic
    fun registerHeatSource(state: IBlockState, temp: Int) =
        registerHeatSource(state.toMetaLocation(), temp)

    @JvmStatic
    fun registerHeatSource(block: Block, temp: Int) =
        registerHeatSource(block.defaultState, temp)

}