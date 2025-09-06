package net.ejibarra.pathtoimmortals.enchantment;

import net.ejibarra.pathtoimmortals.PathToImmortals;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHealEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = PathToImmortals.MOD_ID)
public class GrievousWoundsEvents {
    private static final String GW_TICKS = "GW_Ticks";
    private static final String GW_LVL   = "GW_Level";

    // Base duration (level-scaled below)
    private static final int BASE_DURATION = 80; // 4s at 20tps

    /** % reduction per enchantment level (e.g., 50%, 65%, 80%) */
    private static float reductionForLevel(int lvl) {
        // Example curve: 50% at I, +15% each level, capped at 90%
        return Math.min(0.50f + 0.15f * (lvl - 1), 0.90f);
    }

    /** Duration per level (optional). Return BASE_DURATION if you don't want scaling. */
    private static int durationForLevel(int lvl) {
        // Example: +1s per level
        return BASE_DURATION + (lvl - 1) * 20; // +20 ticks = +1s each level
    }

    @SubscribeEvent
    public static void onLivingHurt(LivingHurtEvent event) {
        Entity src = event.getSource().getEntity();
        if (!(src instanceof LivingEntity attacker)) return;

        ItemStack weapon = attacker.getMainHandItem();
        int lvl = EnchantmentHelper.getItemEnchantmentLevel(ModEnchantments.GRIEVOUS_WOUND.get(), weapon);
        if (lvl <= 0) return;

        LivingEntity victim = event.getEntity();
        CompoundTag tag = victim.getPersistentData();

        tag.putInt(GW_TICKS, durationForLevel(lvl)); // refresh timer
        tag.putInt(GW_LVL,   Math.max(tag.getInt(GW_LVL), lvl)); // keep highest level if multiple hits overlap
    }

    @SubscribeEvent
    public static void onLivingTick(LivingEvent.LivingTickEvent event) {
        LivingEntity e = event.getEntity();
        CompoundTag tag = e.getPersistentData();
        int ticks = tag.getInt(GW_TICKS);
        if (ticks > 0) {
            tag.putInt(GW_TICKS, ticks - 1);
            if (ticks - 1 <= 0) {
                // timer ended: clear stored level
                tag.remove(GW_LVL);
            }
        }
    }

    @SubscribeEvent
    public static void onLivingHeal(LivingHealEvent event) {
        LivingEntity target = event.getEntity();
        CompoundTag tag = target.getPersistentData();
        int ticks = tag.getInt(GW_TICKS);
        if (ticks <= 0) return;

        int lvl = Math.max(1, tag.getInt(GW_LVL));
        float reduce = reductionForLevel(lvl); // 0..0.9
        event.setAmount(event.getAmount() * (1.0f - reduce));
    }
}

