package hiiragi283.ragi_materials.main.proxy

class ClientProxy : CommonProxy() {
    //Pre-Initializationで読み込むメソッド
    override fun loadPreInit() {}

    //Initializationで読み込むメソッド
    override fun loadInit() {}

    //Post-Initializationで読み込むメソッド
    override fun loadPostInit() {}
}