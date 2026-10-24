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

    private static final int BASE_DURATION = 80; // 4s

    private static float reductionForLevel(int lvl) {
        return Math.min(0.50f + 0.15f * (lvl - 1), 0.90f);
    }

    private static int durationForLevel(int lvl) {
        return BASE_DURATION + (lvl - 1) * 20;
    }

    // 👉 NEW: reusable helper so anything (sword hit, projectile, ability) can apply GW
    public static void applyGrievousWound(LivingEntity victim, int lvl) {
        if (lvl <= 0) return;

        CompoundTag tag = victim.getPersistentData();
        int newDuration = durationForLevel(lvl);

        tag.putInt(GW_TICKS, newDuration); // refresh timer
        tag.putInt(GW_LVL, Math.max(tag.getInt(GW_LVL), lvl)); // keep highest level
    }

    @SubscribeEvent
    public static void onLivingHurt(LivingHurtEvent event) {
        Entity src = event.getSource().getEntity();
        if (!(src instanceof LivingEntity attacker)) return;

        ItemStack weapon = attacker.getMainHandItem();
        int lvl = EnchantmentHelper.getItemEnchantmentLevel(ModEnchantments.GRIEVOUS_WOUND.get(), weapon);
        if (lvl <= 0) return;

        LivingEntity victim = event.getEntity();

        // 🔁 use the helper here
        applyGrievousWound(victim, lvl);
    }

    @SubscribeEvent
    public static void onLivingTick(LivingEvent.LivingTickEvent event) {
        LivingEntity e = event.getEntity();
        CompoundTag tag = e.getPersistentData();
        int ticks = tag.getInt(GW_TICKS);
        if (ticks > 0) {
            int next = ticks - 1;
            tag.putInt(GW_TICKS, next);
            if (next <= 0) {
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
        float reduce = reductionForLevel(lvl);
        event.setAmount(event.getAmount() * (1.0f - reduce));
    }
}

