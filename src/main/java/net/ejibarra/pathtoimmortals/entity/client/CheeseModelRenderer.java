package net.ejibarra.pathtoimmortals.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.ejibarra.pathtoimmortals.PathToImmortals;
import net.ejibarra.pathtoimmortals.entity.custom.CheeseProjectileEntity;
import net.ejibarra.pathtoimmortals.entity.custom.SpikeProjectileEntity;
import net.ejibarra.pathtoimmortals.entity.layers.ModModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class CheeseModelRenderer extends EntityRenderer<CheeseProjectileEntity> {
    public static final ResourceLocation TEXTURE = new ResourceLocation(PathToImmortals.MOD_ID, "textures/entity/cheese_projectile.png");
    public CheeseModel model;

    public CheeseModelRenderer(EntityRendererProvider.Context pContext) {
        super(pContext);
        this.model = new CheeseModel(pContext.bakeLayer(ModModelLayers.CHEESE_PROJECTILE_LAYER));
        this.shadowRadius = 0.5f;
    }

    public void render(CheeseProjectileEntity entity, float pEntityYaw, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        pPoseStack.pushPose();
        pPoseStack.mulPose(Axis.YP.rotationDegrees(Mth.lerp(pPartialTick, entity.yRotO, entity.getYRot()) - 90.0F));
        pPoseStack.mulPose(Axis.ZP.rotationDegrees(Mth.lerp(pPartialTick, entity.xRotO, entity.getXRot()) + 90.0F));
        VertexConsumer vertexconsumer = ItemRenderer.getFoilBufferDirect(pBuffer, this.model.renderType(this.getTextureLocation(entity)), false, false);

        this.model.renderToBuffer(pPoseStack, vertexconsumer, pPackedLight, OverlayTexture.NO_OVERLAY, 1f, 1f, 1f, 1f);
        pPoseStack.popPose();
        super.render(entity, pEntityYaw, pPartialTick, pPoseStack, pBuffer, pPackedLight);
    }


    @Override
    public ResourceLocation getTextureLocation(CheeseProjectileEntity pEntity) {
        return TEXTURE;
    }
}
