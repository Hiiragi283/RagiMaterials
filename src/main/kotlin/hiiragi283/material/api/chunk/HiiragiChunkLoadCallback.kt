package hiiragi283.material.api.chunk

import hiiragi283.material.RMReference
import hiiragi283.material.RagiMaterials
import hiiragi283.material.util.DimensionalBlockPos
import hiiragi283.material.util.HiiragiNBTUtil
import hiiragi283.material.util.getBlockImplemented
import net.minecraft.entity.Entity
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraft.world.chunk.Chunk
import net.minecraftforge.common.ForgeChunkManager

/**
 * Thanks to defeatedcrow!
 * [: Source](https://github.com/defeatedcrow/HeatAndClimateMod/blob/1.12.2_v3/main/java/defeatedcrow/hac/main/util/DCChunkloadContoroller.java)
 */
object HiiragiChunkLoadCallback : ForgeChunkManager.LoadingCallback {

    private val mapBlockTicket: HashMap<DimensionalBlockPos, ForgeChunkManager.Ticket> = hashMapOf()

    private val mapEntityTicket: HashMap<Int, ForgeChunkManager.Ticket> = hashMapOf()

    init {
        ForgeChunkManager.setForcedChunkLoadingCallback(RagiMaterials.Instance, this)
    }

    //    LoadingCallback    //

    override fun ticketsLoaded(tickets: MutableList<ForgeChunkManager.Ticket>, world: World) {
        //Block Ticketのみに実行
        tickets.filter(this::isBlockTicket).forEach { ticket: ForgeChunkManager.Ticket ->
            //ticketから読み取った座標のブロックがIBlockChunkLoaderを実装しているかで判断する
            val x: Int = ticket.modData.getInteger("x")
            val y: Int = ticket.modData.getInteger("y")
            val z: Int = ticket.modData.getInteger("z")
            val pos = BlockPos(x, y, z)
            val dimId: Int = ticket.modData.getInteger(HiiragiNBTUtil.DIM_ID)
            val dimPos = DimensionalBlockPos(dimId, pos)
            //dimensionのidが違ったらパス
            if (!dimPos.isSameDimId(world)) return
            world.getBlockState(pos).getBlockImplemented<IBlockChunkLoader>()?.let {
                //チャンク読み込みが可能 -> 読み込みを有効に
                if (it.canLoad(world, pos)) activateBlockTicket(world, dimPos)
                //そうでない -> チャンク読み込みを無効に
                else inactivateBlockTicket(world, dimPos)
            } ?: inactivateBlockTicket(world, dimPos)
        }
        //Entity Ticketのみに実行
        tickets.filter(this::isEntityTicket).forEach { ticket: ForgeChunkManager.Ticket ->
            //ticketから取得したEntityがIEntityChunkLoaderを実装しているかで判断する
            val entity: Entity = ticket.entity ?: return
            (entity as? IEntityChunkLoader)?.let {
                //チャンク読み込みが可能 -> 読み込みを有効に
                if (it.canLoad()) activateEntityTicket(world, entity)
                //そうでない -> チャンク読み込みを無効に
                else inactivateEntityTicket(world, entity)
            } ?: inactivateEntityTicket(world, entity)
        }
    }

    //    Block Ticket    //

    private fun isBlockTicket(ticket: ForgeChunkManager.Ticket): Boolean = ticket.modData.let { tag: NBTTagCompound ->
        tag.getString(HiiragiNBTUtil.OWNER) == RMReference.MOD_ID && tag.getString(HiiragiNBTUtil.TYPE) == "block"
    }

    fun activateBlockTicket(world: World, pos: DimensionalBlockPos) {
        //ForgeChunkManagerにticketを要求する
        val ticket: ForgeChunkManager.Ticket =
            ForgeChunkManager.requestTicket(RagiMaterials.Instance, world, ForgeChunkManager.Type.NORMAL) ?: return
        //ticketに情報を書き込む
        ticket.setBlockData(pos)
        //ticketをmapに入れる
        mapBlockTicket[pos] = ticket
        //ForgeChunkManagerに読み込みを行わせる
        ForgeChunkManager.forceChunk(ticket, world.getChunk(pos.pos).pos)
        //ログに出力する
        RagiMaterials.LOGGER.info("Chunk Loading on blockPos: $pos is activated!")
    }

    private fun ForgeChunkManager.Ticket.setBlockData(pos: DimensionalBlockPos): ForgeChunkManager.Ticket =
        also { ticket: ForgeChunkManager.Ticket ->
            val tag: NBTTagCompound = ticket.modData
            tag.setString(HiiragiNBTUtil.OWNER, RMReference.MOD_ID)
            tag.setString(HiiragiNBTUtil.TYPE, "block")
            tag.setInteger("x", pos.x)
            tag.setInteger("y", pos.y)
            tag.setInteger("z", pos.z)
            tag.setInteger(HiiragiNBTUtil.DIM_ID, pos.dimId)
        }

    fun inactivateBlockTicket(world: World, pos: DimensionalBlockPos) {
        //指定された座標に対してticketが発行されていて，チャンクが読み込まれている -> 読み込みを停止 & ticketを解放
        val ticket: ForgeChunkManager.Ticket = mapBlockTicket[pos] ?: return
        val worldTicket: World = ticket.world ?: return
        if (!ForgeChunkManager.getPersistentChunksFor(worldTicket).isEmpty) {
            ForgeChunkManager.unforceChunk(ticket, world.getChunk(pos.pos).pos)
            ForgeChunkManager.releaseTicket(ticket)
        }
        //mapから座標とticketの組を消す
        mapBlockTicket.remove(pos)
        //ログに出力する
        RagiMaterials.LOGGER.info("Chunk Loading on blockPos: $pos is inactivated...")
    }

    //    Entity Ticket    //

    private fun isEntityTicket(ticket: ForgeChunkManager.Ticket): Boolean = ticket.modData.let { tag: NBTTagCompound ->
        tag.getString(HiiragiNBTUtil.OWNER) == RMReference.MOD_ID && tag.getString(HiiragiNBTUtil.TYPE) == "entity"
    }

    fun activateEntityTicket(world: World, entity: Entity) {
        //ForgeChunkManagerにticketを要求する
        val ticket: ForgeChunkManager.Ticket =
            ForgeChunkManager.requestTicket(RagiMaterials.Instance, world, ForgeChunkManager.Type.ENTITY) ?: return
        //ticketとentityを結びつける
        ticket.bindEntity(entity)
        //ticketに情報を書き込む
        ticket.setEntityData()
        //ticketをmapに入れる
        mapEntityTicket[entity.entityId] = ticket
        //ForgeChunkManagerに読み込みを行わせる
        ForgeChunkManager.forceChunk(ticket, entity.getChunk(world).pos)
        //ログに出力する
        RagiMaterials.LOGGER.info("Chunk Loading on Entity: $entity is activated!")
    }

    private fun ForgeChunkManager.Ticket.setEntityData(): ForgeChunkManager.Ticket =
        also { ticket: ForgeChunkManager.Ticket ->
            val tag: NBTTagCompound = ticket.modData
            tag.setString(HiiragiNBTUtil.OWNER, RMReference.MOD_ID)
            tag.setString(HiiragiNBTUtil.TYPE, "entity")
            //tag.setInteger(HiiragiNBTUtil.ENTITY_ID, entity.entityId)
        }

    private fun Entity.getChunk(world: World): Chunk = world.getChunk(this.chunkCoordX, this.chunkCoordZ)

    fun inactivateEntityTicket(world: World, entity: Entity) {
        //指定されたentityのidに対してticketが発行されていて，チャンクが読み込まれている -> 読み込みを停止 & ticketを解放
        val ticket: ForgeChunkManager.Ticket = mapEntityTicket[entity.entityId] ?: return
        val worldTicket: World = ticket.world ?: return
        if (!ForgeChunkManager.getPersistentChunksFor(worldTicket).isEmpty) {
            ForgeChunkManager.unforceChunk(ticket, entity.getChunk(world).pos)
            ForgeChunkManager.releaseTicket(ticket)
            //ログに出力する
            RagiMaterials.LOGGER.info("Chunk Loading on Entity: $entity is inactivated...")
        }
    }

}