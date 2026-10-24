package net.ejibarra.pathtoimmortals.client;

import com.mojang.blaze3d.platform.InputConstants;
import net.ejibarra.pathtoimmortals.PathToImmortals;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.lwjgl.glfw.GLFW;

public class ModKeyBindings {
    public static final String CATEGORY = "key.categories." + PathToImmortals.MOD_ID;

    public static KeyMapping CONSUME_INFUSED_KEY;
    public static KeyMapping ORIGIN_SWORD_ABILITY;
    public static KeyMapping PRESSURE_WAVE_ABILITY;
    public static KeyMapping OVERCHARGED_ABILITY;
    public static KeyMapping HEAL_ABILITY;
    public static KeyMapping REINFORCED_ABILITY;
    public static KeyMapping LEVITATION_ABILITY;
    public static KeyMapping CLOUD_STEP_ABILITY;
    public static KeyMapping ABILITY_WHEEL;

    @SubscribeEvent
    public static void register(RegisterKeyMappingsEvent event) {

        CONSUME_INFUSED_KEY = new KeyMapping(
                "key." + PathToImmortals.MOD_ID + ".consume_infused",
                InputConstants.KEY_P,
                CATEGORY
        );

        ORIGIN_SWORD_ABILITY = new KeyMapping(
                "key." + PathToImmortals.MOD_ID + ".origin_sword_ability",
                GLFW.GLFW_KEY_V,
                CATEGORY
        );
        PRESSURE_WAVE_ABILITY = new KeyMapping(
                "key." + PathToImmortals.MOD_ID + ".pressure_wave_ability",
                GLFW.GLFW_KEY_B,
                CATEGORY
        );
        OVERCHARGED_ABILITY = new KeyMapping(
                "key." + PathToImmortals.MOD_ID + ".overcharged_ability",
                GLFW.GLFW_KEY_C,
                CATEGORY
        );
        HEAL_ABILITY = new KeyMapping(
                "key." + PathToImmortals.MOD_ID + ".heal_ability",
                GLFW.GLFW_KEY_X,
                CATEGORY
        );
        REINFORCED_ABILITY = new KeyMapping(
                "key." + PathToImmortals.MOD_ID + ".reinforced_ability",
                GLFW.GLFW_KEY_R,
                CATEGORY
        );
        LEVITATION_ABILITY = new KeyMapping(
                "key." + PathToImmortals.MOD_ID + ".levitation_ability",
                GLFW.GLFW_KEY_K,
                CATEGORY
        );
        CLOUD_STEP_ABILITY = new KeyMapping(
                "key." + PathToImmortals.MOD_ID + ".cloud_step_ability",
                GLFW.GLFW_KEY_C,
                CATEGORY
        );
        ABILITY_WHEEL = new KeyMapping(
                "key." + PathToImmortals.MOD_ID + ".ability_wheel",
                GLFW.GLFW_KEY_LEFT_ALT,
                CATEGORY
        );

        event.register(CONSUME_INFUSED_KEY);
        event.register(ORIGIN_SWORD_ABILITY);
        event.register(PRESSURE_WAVE_ABILITY);
        event.register(OVERCHARGED_ABILITY);
        event.register(HEAL_ABILITY);
        event.register(REINFORCED_ABILITY);
        event.register(LEVITATION_ABILITY);
        event.register(CLOUD_STEP_ABILITY);
        event.register(ABILITY_WHEEL);
    }
}
