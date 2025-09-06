package net.ejibarra.pathtoimmortals.enchantment;

import net.ejibarra.pathtoimmortals.PathToImmortals;
import net.ejibarra.pathtoimmortals.effects.ModEffects;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;


import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = PathToImmortals.MOD_ID)
public class BleedSystem {

    // === PersistentData keys (unique-ish to your mod) ===
    private static final String TAG_BLEED_LEVEL = "yourmod_bleed_lvl";
    private static final String TAG_BLEED_UNTIL = "yourmod_bleed_until"; // absolute gameTime tick

    // === Config-like knobs ===
    private static final int BASE_DURATION_T = 30; // 1.5s
    private static final int DURATION_PER_LVL_T = 20; // +1s/level
    // If you REALLY want 20% *per tick* (super lethal), set PER_SECOND_CAP = 0.20*20 and treat it as /tick below.
    private static final double PER_SECOND_CAP = 0.20; // 20% of max HP per second cap

    // L1=5%, +2.5%/lvl, hard cap 20%/sec
    public static double percentPerSecondForLevel(int level) {
        double base = 0.05D, step = 0.025D;
        return Mth.clamp(base + Math.max(0, level - 1) * step, 0.0D, PER_SECOND_CAP);
    }

    // === Apply/refresh Bleed when a weapon with Bleed hits a target ===
    @SubscribeEvent
    public static void onHurt(LivingHurtEvent e) {
        if (!(e.getSource().getEntity() instanceof LivingEntity attacker)) return;

        ItemStack weapon = attacker.getMainHandItem();
        int lvl = EnchantmentHelper.getItemEnchantmentLevel(ModEnchantments.BLEED.get(), weapon);
        if (lvl <= 0) return;

        LivingEntity target = e.getEntity();
        if (target.level().isClientSide || isBleedImmune(target)) return;

        int duration = BASE_DURATION_T + DURATION_PER_LVL_T * lvl;
        applyOrRefreshBleed(target, lvl, duration);
    }

    private static void applyOrRefreshBleed(LivingEntity target, int lvl, int addTicks) {
        CompoundTag data = target.getPersistentData();
        long now = target.level().getGameTime();

        int curLvl = data.getInt(TAG_BLEED_LEVEL);
        long curUntil = data.getLong(TAG_BLEED_UNTIL);

        int newLvl = Math.max(curLvl, lvl);                  // keep the higher level
        long newUntil = Math.max(curUntil, now) + addTicks;  // refresh/extend from now or remaining

        data.putInt(TAG_BLEED_LEVEL, newLvl);
        data.putLong(TAG_BLEED_UNTIL, newUntil);
    }

    // === Tick all living entities: drain health if they have active bleed ===
    @SubscribeEvent
    public static void onLivingTick(LivingEvent.LivingTickEvent e) {
        LivingEntity le = e.getEntity();
        if (le.level().isClientSide) return;
        if (!le.isAlive()) return;

        if (isBleedImmune(le)) {
            var data = le.getPersistentData();
            data.remove(TAG_BLEED_LEVEL);
            data.remove(TAG_BLEED_UNTIL);
            return;
        }

        CompoundTag data = le.getPersistentData();
        if (!data.contains(TAG_BLEED_UNTIL)) return;

        long now = le.level().getGameTime();
        long until = data.getLong(TAG_BLEED_UNTIL);

        if (now >= until) {
            // expire
            data.remove(TAG_BLEED_UNTIL);
            data.remove(TAG_BLEED_LEVEL);
            return;
        }

        int lvl = Math.max(1, data.getInt(TAG_BLEED_LEVEL));
        float maxHp = le.getMaxHealth();
        if (maxHp <= 0f) return;

        double perSecond = percentPerSecondForLevel(lvl);
        float perTickDamage = (float) (maxHp * (perSecond / 20.0D));

        // “real bleed” feel: bypass armor/i-frames; does NOT trigger damage events
        le.setHealth(Math.max(0.0F, le.getHealth() - perTickDamage));

        // If you want true damage events & death messages, instead:
        // accumulate perTickDamage into a TAG_BLEED_ACCUM float and once per second call le.hurt(bleedDamageSource, accumulated);
    }
        private static final TagKey<EntityType<?>> BLEED_IMMUNE_TAG =
                TagKey.create(Registries.ENTITY_TYPE, new ResourceLocation(PathToImmortals.MOD_ID, "bleed_immune"));

        private static boolean isBleedImmune(LivingEntity le) {
            // Optional: make UNDEAD immune too
            return le.getType().is(BLEED_IMMUNE_TAG) || le.getMobType() == MobType.UNDEAD;
        }
}

