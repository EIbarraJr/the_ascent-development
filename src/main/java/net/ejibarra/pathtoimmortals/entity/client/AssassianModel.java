package net.ejibarra.pathtoimmortals.entity.client;// Made with Blockbench 4.12.6
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.ejibarra.pathtoimmortals.entity.animation.AssassainModelAnimation;
import net.ejibarra.pathtoimmortals.entity.custom.AssassainEntity;
import net.minecraft.client.model.ArmedModel;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.HumanoidArm;

public class AssassianModel<T extends AssassainEntity> extends HierarchicalModel<T> implements ArmedModel {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor

	private final ModelPart doppler;
	private final ModelPart body;
	private final ModelPart torso;
	private final ModelPart head;
	private final ModelPart left_arm;
	private final ModelPart right_arm;
	private final ModelPart left_leg;
	private final ModelPart right_leg;

	public AssassianModel(ModelPart root) {
		this.doppler = root.getChild("doppler");
		this.body = this.doppler.getChild("body");
		this.torso = this.body.getChild("torso");
		this.head = this.torso.getChild("head");
		this.left_arm = this.torso.getChild("left_arm");
		this.right_arm = this.torso.getChild("right_arm");
		this.left_leg = this.body.getChild("left_leg");
		this.right_leg = this.body.getChild("right_leg");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition doppler = partdefinition.addOrReplaceChild("doppler", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition body = doppler.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition torso = body.addOrReplaceChild("torso", CubeListBuilder.create().texOffs(0, 25).addBox(-4.0F, -5.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, -19.0F, 1.0F));

		PartDefinition head = torso.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(0, 16).addBox(-4.5F, -8.1F, -4.25F, 9.0F, 0.0F, 9.0F, new CubeDeformation(0.0F))
		.texOffs(24, 25).addBox(4.5F, -8.1F, -4.25F, 0.0F, 8.0F, 9.0F, new CubeDeformation(0.0F))
		.texOffs(36, 0).addBox(-4.5F, -8.1F, -4.25F, 0.0F, 8.0F, 9.0F, new CubeDeformation(0.0F))
		.texOffs(42, 33).addBox(-4.5F, -8.05F, 4.75F, 9.0F, 8.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(48, 41).addBox(-4.5F, -4.05F, -4.25F, 9.0F, 4.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(48, 45).addBox(-4.5F, -8.15F, -4.15F, 9.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -5.0F, 0.0F));

		PartDefinition left_arm = torso.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(16, 42).addBox(0.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, -3.0F, 0.0F));

		PartDefinition right_arm = torso.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(42, 17).addBox(-4.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.0F, -3.0F, 0.0F));

		PartDefinition left_leg = body.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(0, 41).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, -12.0F, 1.0F));

		PartDefinition right_leg = body.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(32, 42).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, -12.0F, 1.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(AssassainEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		// Walk cycle
		// real per-tick ground speed
		float horiz = (float)entity.getDeltaMovement().horizontalDistance();
		// Frequency: how fast to loop the walk clip (in Hz). 0.28f is fairly brisk, so scale aggressively.
		// Try 80 first; go to 92–100 if it still slides.
		float cycleHz = Mth.clamp(horiz * 92.0f, 1.2f, 4.2f);
		// Amplitude: how far limbs swing. Derive from speed, not limbSwingAmount.
		// Bump the multiplier if steps still feel short (try 20 → 24 → 28).
		float amp = Mth.clamp(horiz * 28.0f, 0.35f, 1.75f);
		this.animateWalk(AssassainModelAnimation.WALK, limbSwing, 1.0f, cycleHz, amp);
		this.animate(entity.idleState, AssassainModelAnimation.IDLE, ageInTicks, 1.0f);
		this.animate(entity.attackState, AssassainModelAnimation.ATTACK, ageInTicks, 1.0f);

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		doppler.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return doppler;
	}

	@Override
	public void translateToHand(HumanoidArm arm, PoseStack poseStack) {
		// Select which arm to use
		ModelPart armPart = (arm == HumanoidArm.LEFT) ? this.left_arm : this.right_arm;

		// Apply the full hierarchy: doppler -> body -> torso -> arm
		this.doppler.translateAndRotate(poseStack);
		this.body.translateAndRotate(poseStack);
		this.torso.translateAndRotate(poseStack);
		armPart.translateAndRotate(poseStack);
	}

}