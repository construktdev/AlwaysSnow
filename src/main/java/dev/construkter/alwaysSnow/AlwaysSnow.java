package dev.construkter.alwaysSnow;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

public class AlwaysSnow extends JavaPlugin {

    List<Material> blacklist = List.of(
            Material.WATER,
            Material.LAVA,
            Material.SNOW,
            Material.ICE,
            Material.PACKED_ICE,
            Material.BLUE_ICE,
            Material.CAVE_AIR,
            Material.VOID_AIR,
            Material.OAK_FENCE,
            Material.SPRUCE_FENCE,
            Material.BIRCH_FENCE,
            Material.JUNGLE_FENCE,
            Material.ACACIA_FENCE,
            Material.DARK_OAK_FENCE,
            Material.NETHER_BRICK_FENCE,
            Material.CRIMSON_FENCE,
            Material.WARPED_FENCE,
            Material.OAK_FENCE_GATE,
            Material.SPRUCE_FENCE_GATE,
            Material.BIRCH_FENCE_GATE,
            Material.JUNGLE_FENCE_GATE,
            Material.ACACIA_FENCE_GATE,
            Material.DARK_OAK_FENCE_GATE,
            Material.CRIMSON_FENCE_GATE,
            Material.WARPED_FENCE_GATE,
            Material.STONE_SLAB,
            Material.COBBLESTONE_SLAB,
            Material.BRICK_SLAB,
            Material.STONE_BRICK_SLAB,
            Material.NETHER_BRICK_SLAB,
            Material.QUARTZ_SLAB,
            Material.RED_SANDSTONE_SLAB,
            Material.PURPUR_SLAB,
            Material.PRISMARINE_SLAB,
            Material.SMOOTH_STONE_SLAB,
            Material.SMOOTH_SANDSTONE_SLAB,
            Material.SMOOTH_QUARTZ_SLAB,
            Material.SMOOTH_RED_SANDSTONE_SLAB,
            Material.CUT_SANDSTONE_SLAB,
            Material.CUT_RED_SANDSTONE_SLAB,
            Material.OAK_STAIRS,
            Material.SPRUCE_STAIRS,
            Material.BIRCH_STAIRS,
            Material.JUNGLE_STAIRS,
            Material.ACACIA_STAIRS,
            Material.DARK_OAK_STAIRS,
            Material.LANTERN,
            Material.SOUL_LANTERN,
            Material.LIGHTNING_ROD,
            Material.STONE_BRICK_WALL,
            Material.COBBLESTONE_WALL,
            Material.MOSSY_COBBLESTONE_WALL,
            Material.BRICK_WALL,
            Material.NETHER_BRICK_WALL,
            Material.RED_SANDSTONE_WALL,
            Material.SANDSTONE_WALL,
            Material.PRISMARINE_WALL,
            Material.DIORITE_WALL,
            Material.ANDESITE_WALL,
            Material.GRANITE_WALL,
            Material.SCAFFOLDING
    );

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

                if (!highest.getType().isSolid() || blacklist.contains(highest.getType())) continue;

                Block above = highest.getRelative(0, 1, 0);

                if (above.getType() == Material.AIR) {
                    above.setType(Material.SNOW);
                }
            }
        }
    }
}
