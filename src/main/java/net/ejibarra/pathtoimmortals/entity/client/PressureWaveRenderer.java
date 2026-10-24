package net.ejibarra.pathtoimmortals.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.ejibarra.pathtoimmortals.PathToImmortals;
import net.ejibarra.pathtoimmortals.entity.custom.PressureWaveProjectile;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import org.joml.Matrix3f;
import org.joml.Matrix4f;

public class PressureWaveRenderer extends EntityRenderer<PressureWaveProjectile> {
    private static final ResourceLocation TEX =
            new ResourceLocation(PathToImmortals.MOD_ID, "textures/entity/pressure_wave.png");

    public PressureWaveRenderer(EntityRendererProvider.Context ctx) {
        super(ctx);
    }

    @Override
    public ResourceLocation getTextureLocation(PressureWaveProjectile entity) {
        return TEX;
    }

    @Override
    public void render(PressureWaveProjectile e, float entityYaw, float partialTicks,
                       PoseStack pose, MultiBufferSource buffers, int packedLight) {
        pose.pushPose();

        // Move to entity center
        pose.translate(0.0, e.getBbHeight() * 0.5, 0.0);

        // ✅ BILLBOARD: face the camera
        pose.mulPose(this.entityRenderDispatcher.cameraOrientation());
        // Often needed so the texture isn't mirrored / facing "backwards"
        pose.mulPose(com.mojang.math.Axis.YP.rotationDegrees(270.0F));

        // Size of the wave in-world (tweak)
        float w = 1.25f;
        float h = 1.25f;

        VertexConsumer vc = buffers.getBuffer(RenderType.entityTranslucent(getTextureLocation(e)));
        PoseStack.Pose last = pose.last();
        Matrix4f mat = last.pose();
        Matrix3f normal = last.normal();

        addVertex(vc, mat, normal, -w/2, -h/2, 0, 0, 1, packedLight);
        addVertex(vc, mat, normal,  w/2, -h/2, 0, 1, 1, packedLight);
        addVertex(vc, mat, normal,  w/2,  h/2, 0, 1, 0, packedLight);
        addVertex(vc, mat, normal, -w/2,  h/2, 0, 0, 0, packedLight);

        pose.popPose();
        super.render(e, entityYaw, partialTicks, pose, buffers, packedLight);
    }

    private static float lerp(float pt, float a, float b) {
        return a + (b - a) * pt;
    }

    private static void addVertex(VertexConsumer vc, Matrix4f mat, Matrix3f normal,
                                  float x, float y, float z, float u, float v, int light) {
        vc.vertex(mat, x, y, z)
                .color(255, 255, 255, 255)
                .uv(u, v)
                .overlayCoords(OverlayTexture.NO_OVERLAY)
                .uv2(light)
                .normal(normal, 0, 0, 1)
                .endVertex();
    }
}
