package net.ejibarra.pathtoimmortals.client;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.player.Player;

public class OverchargedParticles {
    public static void spawn(ClientLevel level, Player player) {
        // Center slightly above feet (looks like a shockwave origin)
        double cx = player.getX();
        double cy = player.getY() + 0.8D;
        double cz = player.getZ();

        int count = 360;

        // Make it visible: spawn farther out
        double minSpawnR = 0.9D;
        double maxSpawnR = 2.8D;

        // Push outward harder so it doesn't look like it "falls in"
        double minSpeed = 0.25D;
        double maxSpeed = 0.90D;

        for (int i = 0; i < count; i++) {
            // Random direction on a sphere
            double dx = level.random.nextDouble() * 2.0 - 1.0;
            double dy = level.random.nextDouble() * 2.0 - 1.0;
            double dz = level.random.nextDouble() * 2.0 - 1.0;

            double len = Math.sqrt(dx * dx + dy * dy + dz * dz);
            if (len < 1.0E-6) { i--; continue; } // retry (rare)
            dx /= len; dy /= len; dz /= len;

            // Spawn on a spherical shell around the player (far enough to see)
            double r = minSpawnR + level.random.nextDouble() * (maxSpawnR - minSpawnR);
            double x = cx + dx * r;
            double y = cy + dy * (r * 0.6D); // slightly squashed vertically (looks nicer around a player)
            double z = cz + dz * r;

            // Outward velocity (same direction) + slight upward bias
            double speed = minSpeed + level.random.nextDouble() * (maxSpeed - minSpeed);
            double vx = dx * speed;
            double vy = dy * (speed * 0.35D) + 0.10D;  // upward lift so it "bursts" instead of sinking
            double vz = dz * speed;

            level.addParticle(ParticleTypes.ENCHANT, x, y, z, vx, vy, vz);
        }
    }
}
