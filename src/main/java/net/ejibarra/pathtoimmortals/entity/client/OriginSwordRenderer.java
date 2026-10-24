package net.ejibarra.pathtoimmortals.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.ejibarra.pathtoimmortals.PathToImmortals;
import net.ejibarra.pathtoimmortals.entity.custom.DaoCoreProjectile;
import net.ejibarra.pathtoimmortals.entity.custom.OriginSwordProjectileEntity;
import net.ejibarra.pathtoimmortals.entity.layers.ModModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class OriginSwordRenderer extends EntityRenderer<OriginSwordProjectileEntity> {
    public static final ResourceLocation TEXTURE = new ResourceLocation(PathToImmortals.MOD_ID, "textures/entity/origin_sword.png");
    public OriginSwordModel model;

    public OriginSwordRenderer(EntityRendererProvider.Context pContext) {
        super(pContext);
        this.model = new OriginSwordModel(pContext.bakeLayer(ModModelLayers.ORIGIN_SWORD_LAYER));
        this.shadowRadius = 0.5f;
    }

    @Override
    public void render(OriginSwordProjectileEntity entity, float entityYaw, float partialTicks,
                       PoseStack poseStack, MultiBufferSource buffer, int packedLight) {

        poseStack.pushPose();

        // keep your vertical offset
        poseStack.translate(0.0D, -1.25D, 0.0D);

        float yaw   = Mth.lerp(partialTicks, entity.yRotO, entity.getYRot());
        float pitch = Mth.lerp(partialTicks, entity.xRotO, entity.getXRot());

        // ✅ Projectile-style rotation (like trident/arrow)
        poseStack.mulPose(Axis.YP.rotationDegrees(yaw + 180));      // face direction
        poseStack.mulPose(Axis.ZP.rotationDegrees(pitch + 0));            // tilt up/down

        // If the sword is sideways or backwards, adjust these constants:
        // poseStack.mulPose(Axis.ZP.rotationDegrees(pitch + 90.0F));
        // or add one fixed rotation:
        // poseStack.mulPose(Axis.XP.rotationDegrees(90.0F));

        VertexConsumer consumer = buffer.getBuffer(model.renderType(getTextureLocation(entity)));
        model.renderToBuffer(
                poseStack, consumer,
                packedLight,
                OverlayTexture.NO_OVERLAY,
                1.0F, 1.0F, 1.0F, 1.0F
        );

        poseStack.popPose();
        super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
    }


    @Override
    public ResourceLocation getTextureLocation(OriginSwordProjectileEntity pEntity) {
        return TEXTURE;
    }
}
