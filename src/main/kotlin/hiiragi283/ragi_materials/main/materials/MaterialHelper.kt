package hiiragi283.ragi_materials.main.materials

object MaterialHelper {

    //代入したindexと一致するEnumMaterialsを返すメソッド
    fun getMaterial(index: Int): EnumMaterials {
        //デフォルト値はDEBUG
        var materialMatches: EnumMaterials = EnumMaterials.DEBUG
        for (material in EnumMaterials.values()) {
            //indexが一致する場合
            if (material.index == index) {
                //materialMatchesにmaterialを代入
                materialMatches = material
            }
        }
        //materialMatchesを返す
        return materialMatches
    }
}