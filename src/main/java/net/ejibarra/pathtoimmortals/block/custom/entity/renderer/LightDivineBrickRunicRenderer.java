package net.ejibarra.pathtoimmortals.block.custom.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.ejibarra.pathtoimmortals.PathToImmortals;
import net.ejibarra.pathtoimmortals.block.ModBlocks;
import net.ejibarra.pathtoimmortals.block.custom.entity.LightDivineRunicBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.block.ModelBlockRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.joml.Matrix3f;
import org.joml.Matrix4f;

public class LightDivineBrickRunicRenderer implements BlockEntityRenderer<LightDivineRunicBlockEntity> {

    private static final ResourceLocation RUNIC_OVERLAY_TEXTURE =
            new ResourceLocation(PathToImmortals.MOD_ID, "textures/block/runic_overlay.png");

    public LightDivineBrickRunicRenderer(BlockEntityRendererProvider.Context ctx) { }

    @Override
    public void render(LightDivineRunicBlockEntity be,
                       float partialTicks,
                       PoseStack poseStack,
                       MultiBufferSource bufferSource,
                       int packedLight,
                       int packedOverlay) {

        // ✅ Cheap check: just reads a boolean
        if (!be.hasJadeNearby()) {
            return; // no glow
        }

        // Full-bright emissive texture, like Enderman eyes
        VertexConsumer consumer = bufferSource.getBuffer(RenderType.eyes(RUNIC_OVERLAY_TEXTURE));
        int light = LightTexture.FULL_BRIGHT;

        poseStack.pushPose();

        PoseStack.Pose last = poseStack.last();
        Matrix4f pose = last.pose();
        Matrix3f normal = last.normal();

        float EPS = 0.001f; // tiny offset to avoid z-fighting

        // FRONT (+Z) – z = 1 + EPS
        quad(consumer, pose, normal,
                0f, 0f, 1f + EPS,
                1f, 0f, 1f + EPS,
                1f, 1f, 1f + EPS,
                0f, 1f, 1f + EPS,
                0f, 0f, 1f, 1f,
                0f, 0f, 1f,
                light, packedOverlay);

// BACK (-Z) – z = -EPS
        quad(consumer, pose, normal,
                1f, 0f, -EPS,
                0f, 0f, -EPS,
                0f, 1f, -EPS,
                1f, 1f, -EPS,
                0f, 0f, 1f, 1f,
                0f, 0f, -1f,
                light, packedOverlay);

// LEFT (-X) – x = -EPS
        quad(consumer, pose, normal,
                -EPS, 0f, 0f,
                -EPS, 0f, 1f,
                -EPS, 1f, 1f,
                -EPS, 1f, 0f,
                0f, 0f, 1f, 1f,
                -1f, 0f, 0f,
                light, packedOverlay);

// RIGHT (+X) – x = 1 + EPS
        quad(consumer, pose, normal,
                1f + EPS, 0f, 1f,
                1f + EPS, 0f, 0f,
                1f + EPS, 1f, 0f,
                1f + EPS, 1f, 1f,
                0f, 0f, 1f, 1f,
                1f, 0f, 0f,
                light, packedOverlay);

// TOP (+Y) – y = 1 + EPS
        quad(consumer, pose, normal,
                0f, 1f + EPS, 1f,
                1f, 1f + EPS, 1f,
                1f, 1f + EPS, 0f,
                0f, 1f + EPS, 0f,
                0f, 0f, 1f, 1f,
                0f, 1f, 0f,
                light, packedOverlay);

// BOTTOM (-Y) – y = -EPS
        quad(consumer, pose, normal,
                0f, -EPS, 0f,
                1f, -EPS, 0f,
                1f, -EPS, 1f,
                0f, -EPS, 1f,
                0f, 0f, 1f, 1f,
                0f, -1f, 0f,
                light, packedOverlay);

        poseStack.popPose();
    }

    // 🔍 Check for Infused Jade within a small radius
    private boolean hasInfusedJadeNearby(LightDivineRunicBlockEntity be) {
        Level level = be.getLevel();
        if (level == null) return false;

        BlockPos center = be.getBlockPos();
        int radius = 32; // how far the "aura" reaches

        BlockPos min = center.offset(-radius, -radius, -radius);
        BlockPos max = center.offset(radius, radius, radius);

        for (BlockPos pos : BlockPos.betweenClosed(min, max)) {
            if (level.getBlockState(pos).is(ModBlocks.INFUSED_JADE_BLOCK.get())) {
                return true;
            }
        }
        return false;
    }

    private static void quad(VertexConsumer consumer,
                             Matrix4f pose, Matrix3f normalMat,
                             float x1, float y1, float z1,
                             float x2, float y2, float z2,
                             float x3, float y3, float z3,
                             float x4, float y4, float z4,
                             float u1, float v1,
                             float u2, float v2,
                             float nx, float ny, float nz,
                             int light, int overlay) {

        // 4 vertices per quad, using the same UV rectangle (u1,v1) to (u2,v2)
        vertex(consumer, pose, normalMat, x1, y1, z1, u1, v2, nx, ny, nz, light, overlay);
        vertex(consumer, pose, normalMat, x2, y2, z2, u2, v2, nx, ny, nz, light, overlay);
        vertex(consumer, pose, normalMat, x3, y3, z3, u2, v1, nx, ny, nz, light, overlay);
        vertex(consumer, pose, normalMat, x4, y4, z4, u1, v1, nx, ny, nz, light, overlay);
    }

    private static void vertex(VertexConsumer consumer,
                               Matrix4f pose, Matrix3f normalMat,
                               float x, float y, float z,
                               float u, float v,
                               float nx, float ny, float nz,
                               int light, int overlay) {

        consumer.vertex(pose, x, y, z)
                .color(1f, 1f, 1f, 1f)
                .uv(u, v)
                .overlayCoords(overlay)
                .uv2(light)
                .normal(normalMat, nx, ny, nz)
                .endVertex();
    }

}
