package dev.construkter.alwaysSnow;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class AlwaysSnow extends JavaPlugin {
    @Override
    public void onEnable() {

        new BukkitRunnable() {
            @Override
            public void run() {
                for (World world : Bukkit.getWorlds()) {
                    if (world.getEnvironment() != World.Environment.NORMAL) continue;

                    for (Chunk chunk : world.getLoadedChunks()) {
                        coverChunkWithSnow(world, chunk);
                    }
                }
            }
        }.runTaskTimer(this, 0L, 20L * 30);
    }

    private void coverChunkWithSnow(World world, Chunk chunk) {
        int baseX = chunk.getX() << 4;
        int baseZ = chunk.getZ() << 4;

        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {

                Block highest = world.getHighestBlockAt(baseX + x, baseZ + z);

                if (!highest.getType().isSolid()) continue;

                Block above = highest.getRelative(0, 1, 0);

                if (above.getType() == Material.AIR) {
                    above.setType(Material.SNOW);
                }
            }
        }
    }
}
