package net.ejibarra.pathtoimmortals.item.custom;

import net.ejibarra.pathtoimmortals.network.ModMessages;
import net.ejibarra.pathtoimmortals.network.packet.AbilityType;
import net.ejibarra.pathtoimmortals.network.packet.S2C_SyncOriginEssencePacket;
import net.ejibarra.pathtoimmortals.origin.OriginEssence;
import net.ejibarra.pathtoimmortals.origin.OriginEssenceCapability;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class AbilityScrollItem extends Item {

    private final AbilityType ability;
    private final int targetTier; // 1..4

    public AbilityScrollItem(Properties props, AbilityType ability, int targetTier) {
        super(props);
        this.ability = ability;
        this.targetTier = targetTier;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        if (level.isClientSide) {
            // Let server do the real logic; client just plays hand anim.
            return InteractionResultHolder.success(stack);
        }

        if (!(player instanceof ServerPlayer sp)) {
            return InteractionResultHolder.pass(stack);
        }

        OriginEssence cap = OriginEssenceCapability.OriginEssenceHelper.get(sp);
        int currentTier = cap.getAbilityTier(ability);

        // Already at or above this tier
        if (currentTier >= targetTier) {
            sp.displayClientMessage(
                    Component.literal("You already have ")
                            .append(Component.literal(ability.name()).withStyle(ChatFormatting.LIGHT_PURPLE))
                            .append(Component.literal(" at tier " + currentTier + ".").withStyle(ChatFormatting.GRAY)),
                    true
            );
            sp.level().playSound(null, sp.blockPosition(), SoundEvents.VILLAGER_NO, SoundSource.PLAYERS, 0.8f, 1.0f);
            return InteractionResultHolder.fail(stack);
        }

        // Upgrade directly to target tier (tier 1 scroll unlocks from 0 -> 1)
        cap.setAbilityTier(ability, targetTier);

        // Sync to client (use your existing packet layout)
        ModMessages.sendToPlayer(
                new S2C_SyncOriginEssencePacket(
                        cap.getCurrent(),
                        cap.getMax(),
                        cap.getUnlockedMask(),
                        cap.getAbilityTiersCopy()
                ),
                sp
        );

        sp.displayClientMessage(
                Component.literal("Unlocked ")
                        .append(Component.literal(ability.name()).withStyle(ChatFormatting.LIGHT_PURPLE))
                        .append(Component.literal(" Tier " + targetTier + "!").withStyle(ChatFormatting.GOLD)),
                true
        );

        sp.level().playSound(null, sp.blockPosition(), SoundEvents.ENCHANTMENT_TABLE_USE, SoundSource.PLAYERS, 0.9f, 1.1f);

        if (!sp.getAbilities().instabuild) {
            stack.shrink(1);
        }

        return InteractionResultHolder.success(stack);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(stack, level, tooltip, flag);

        // Title line
        tooltip.add(Component.literal(abilityName(ability) + " Scroll")
                .withStyle(ChatFormatting.LIGHT_PURPLE));

        tooltip.add(Component.literal("Unlocks Tier " + targetTier)
                .withStyle(ChatFormatting.GOLD));

        // Ability description (per ability)
        for (Component line : abilityDescription(ability)) {
            tooltip.add(line);
        }

        tooltip.add(Component.literal("Right-click to consume.")
                .withStyle(ChatFormatting.GRAY));
    }

    private static String abilityName(AbilityType type) {
        // if you already have a display name system, use it instead
        String s = type.name().toLowerCase(Locale.ROOT).replace('_', ' ');
        return Character.toUpperCase(s.charAt(0)) + s.substring(1);
    }

    private static List<Component> abilityDescription(AbilityType type) {
        List<Component> out = new ArrayList<>();

        switch (type) {
            case ORIGIN_SWORD -> {
                out.add(Component.literal("Launch a sword projectile forward.")
                        .withStyle(ChatFormatting.GRAY));
                out.add(Component.literal("Higher tiers: lower cooldown, more power.")
                        .withStyle(ChatFormatting.DARK_GRAY));
            }
            case PRESSURE_WAVE -> {
                out.add(Component.literal("Fire a fast shockwave that hits enemies.")
                        .withStyle(ChatFormatting.GRAY));
                out.add(Component.literal("Higher tiers: lower cooldown and higher damage.")
                        .withStyle(ChatFormatting.DARK_GRAY));
            }
            case OVERCHARGED -> {
                out.add(Component.literal("Blast nearby enemies away and deal AoE damage.")
                        .withStyle(ChatFormatting.GRAY));
                out.add(Component.literal("Cost/Damage scale with max essence.")
                        .withStyle(ChatFormatting.DARK_GRAY));
            }
            case HEAL -> {
                out.add(Component.literal("Restore your 60% of your health instantly.")
                        .withStyle(ChatFormatting.GRAY));
                out.add(Component.literal("Higher tiers: lower cooldown.")
                        .withStyle(ChatFormatting.DARK_GRAY));
            }
            case REINFORCED -> {
                out.add(Component.literal("Toggle a buff: +Attack, +Armor, +Toughness.")
                        .withStyle(ChatFormatting.GRAY));
                out.add(Component.literal("Drains essence per second while active.")
                        .withStyle(ChatFormatting.DARK_GRAY));
            }
            case CLOUD_STEP -> {
                out.add(Component.literal("Teleport forward to a safe spot.")
                        .withStyle(ChatFormatting.GRAY));
                out.add(Component.literal("Higher tiers: farther teleport, lower cooldown.")
                        .withStyle(ChatFormatting.DARK_GRAY));
            }
            case LEVITATION -> {
                out.add(Component.literal("Toggle levitation effects.")
                        .withStyle(ChatFormatting.GRAY));
            }
        }

        return out;
    }
}
