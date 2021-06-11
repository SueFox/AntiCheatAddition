package de.photon.aacadditionpro.user.data.batch;

import de.photon.aacadditionpro.user.User;
import de.photon.aacadditionpro.util.datastructure.batch.Batch;
import de.photon.aacadditionpro.util.datastructure.broadcast.Broadcaster;
import de.photon.aacadditionpro.util.world.InternalPotion;
import lombok.Value;
import lombok.val;
import org.bukkit.block.Block;
import org.jetbrains.annotations.NotNull;

public class TowerBatch extends Batch<TowerBatch.TowerBlockPlace>
{
    public static final Broadcaster<Snapshot<TowerBlockPlace>> TOWER_BATCH_BROADCASTER = new Broadcaster<>();
    private static final int TOWER_BATCH_SIZE = 6;

    public TowerBatch(@NotNull User user)
    {
        super(TOWER_BATCH_BROADCASTER, user, TOWER_BATCH_SIZE, new TowerBlockPlace(null, null, null));
    }

    @Value
    public static class TowerBlockPlace
    {
        long time = System.currentTimeMillis();
        Block block;
        InternalPotion.PotentialPotionEffect jumpBoost;
        InternalPotion.PotentialPotionEffect levitation;

        public long timeOffset(@NotNull TowerBlockPlace other)
        {
            val otime = other.getTime();
            return time < otime ? otime - time : time - otime;
        }
    }
}
