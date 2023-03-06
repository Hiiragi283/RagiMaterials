package hiiragi283.ragi_materials.item

/*
class ItemCraftingTool(private val ID: String, private val maxMeta: Int) : ItemBase(Reference.MOD_ID, ID, maxMeta) {

    //コンストラクタの初期化
    init {
        creativeTab = CreativeTabs.TOOLS
        setMaxStackSize(1)
    }

    //メタデータ付きアイテムをクリエイティブタブに登録するメソッド
    @SideOnly(Side.CLIENT) //Client側のみ
    override fun getSubItems(tab: CreativeTabs, subItems: NonNullList<ItemStack>) {
        if (isInCreativeTab(tab)) {
            //メタデータの最大値まで処理を繰り返す
            for (i in 0 .. maxMeta) {
                //numMaterialの最大値まで処理を繰り替えす
                for (j in 0 .. Reference.numMaterial) {
                    //materialの取得
                    val material = MaterialUtil.getMaterial(j)
                    //materialがWILDCARDでない，かつmaterialがmapToolMaterialに含まれている場合
                    if (material !== MaterialRegistryOld.WILDCARD && MaterialRegistry.mapIndexToolMaterial.contains(material)) {
                        //NBTタグの生成
                        val tag = RagiNBT.getTagTool(material)
                        //ItemStackの生成
                        val stack = ItemStack(this, 1, i)
                        //NBTタグをItemStackに代入
                        stack.tagCompound = tag
                        //ItemStackをsubItemsに追加
                        subItems.add(stack)
                    }
                }
            }
        }
    }

    //stackの表示名を上書きするメソッド
    override fun getItemStackDisplayName(stack: ItemStack): String {
        //NBTタグが存在しない場合
        if (stack.tagCompound == null) {
            //NBTタグを生成・代入
            stack.tagCompound = RagiNBT.getTagTool(MaterialRegistryOld.WILDCARD)
        }
        //NBTタグを取得
        val tag = stack.tagCompound!!
        return I18n.format(
            "item.ragi_${ID}.${stack.metadata}.name",
            I18n.format("material.${tag.getString("material")}")
        )
    }

    //Itemにtooltipを付与するメソッド
    @SideOnly(Side.CLIENT)
    override fun addInformation(stack: ItemStack, world: World?, tooltip: MutableList<String>, flag: ITooltipFlag) {
        //materialの取得
        val material = MaterialUtil.getMaterial(stack.metadata)
        //NBTタグが存在しない場合
        if (stack.tagCompound == null) {
            //NBTタグを生成・代入
            stack.tagCompound = RagiNBT.getTagTool(material)
        }
        //NBTタグを取得
        val tag = stack.tagCompound!!
        //tooltipの追加
        tooltip.add("§e===Property===")
        tooltip.add(I18n.format("text.ragi_materials.durability.name", tag.getInteger("durability")))
        super.addInformation(stack, world, tooltip, ITooltipFlag.TooltipFlags.NORMAL)
    }

    //クラフト時に返却されるstackを取得するメソッド
    override fun getContainerItem(stack: ItemStack): ItemStack {
        //NBTタグが存在しない場合
        if (stack.tagCompound == null) {
            //NBTタグを生成・代入
            stack.tagCompound = RagiNBT.getTagTool(MaterialRegistryOld.WILDCARD)
        }
        //NBTタグを取得
        val tag = stack.tagCompound!!
        //NBTタグから耐久地を取得
        var durability = tag.getInteger("durability")
        //耐久地が0の場合
        return if (durability == 0) {
            //壊れる
            ItemStack.EMPTY
        }
        //耐久地が0以外の場合
        else {
            //durabilityを1つ小さくする
            durability -= 1
            tag.setInteger("durability", durability)
            stack.tagCompound = tag
            //stackを複製する
            val stackCopied = stack.copy()
            //複製したものを返す
            stackCopied
        }
    }

    //クラフト時にstackを返却するかどうかを確認するメソッド
    override fun hasContainerItem(stack: ItemStack): Boolean {
        return true
    }
}
*/