package net.ejibarra.pathtoimmortals.entity.client;// Made with Blockbench 4.12.6
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.ejibarra.pathtoimmortals.entity.custom.CheeseProjectileEntity;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class CheeseModel<T extends CheeseProjectileEntity> extends HierarchicalModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor

	private final ModelPart cheese;

	public CheeseModel(ModelPart root) {
		this.cheese = root.getChild("cheese");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition cheese = partdefinition.addOrReplaceChild("cheese", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -5.0F, -1.0F, 4.0F, 5.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition cube_r1 = cheese.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(16, 0).addBox(-1.0F, -5.0F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -2.75F, 0.0F, 0.7854F, 0.0F));

		PartDefinition cube_r2 = cheese.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(14, 9).addBox(-1.0F, -4.0F, 1.0F, 2.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.0F, -3.0F, 0.0F, 0.4363F, 0.0F));

		PartDefinition cube_r3 = cheese.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(0, 9).addBox(-1.0F, -4.0F, 1.0F, 2.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.0F, -3.0F, 0.0F, -0.4363F, 0.0F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void setupAnim(CheeseProjectileEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		cheese.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return cheese;
	}
}