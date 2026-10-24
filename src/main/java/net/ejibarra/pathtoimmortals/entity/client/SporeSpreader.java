package net.ejibarra.pathtoimmortals.entity.client;// Made with Blockbench 4.12.6
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.ejibarra.pathtoimmortals.entity.animation.SporeSpreaderModelAnimation;
import net.ejibarra.pathtoimmortals.entity.custom.SporeSpreaderEntity;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;

public class SporeSpreader<T extends SporeSpreaderEntity> extends HierarchicalModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor

	private final ModelPart spore_spreader;
	private final ModelPart body;
	private final ModelPart left_leg;
	private final ModelPart lower_leg;
	private final ModelPart right_leg;
	private final ModelPart lower_leg2;
	private final ModelPart torso;
	private final ModelPart head;
	private final ModelPart face;
	private final ModelPart cap;
	private final ModelPart left_arm;
	private final ModelPart right_arm;
	private final ModelPart mush_room_staff;

	public SporeSpreader(ModelPart root) {
		this.spore_spreader = root.getChild("spore_spreader");
		this.body = this.spore_spreader.getChild("body");
		this.left_leg = this.body.getChild("left_leg");
		this.lower_leg = this.left_leg.getChild("lower_leg");
		this.right_leg = this.body.getChild("right_leg");
		this.lower_leg2 = this.right_leg.getChild("lower_leg2");
		this.torso = this.body.getChild("torso");
		this.head = this.torso.getChild("head");
		this.face = this.head.getChild("face");
		this.cap = this.head.getChild("cap");
		this.left_arm = this.body.getChild("left_arm");
		this.right_arm = this.body.getChild("right_arm");
		this.mush_room_staff = this.body.getChild("mush_room_staff");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition spore_spreader = partdefinition.addOrReplaceChild("spore_spreader", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition body = spore_spreader.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition left_leg = body.addOrReplaceChild("left_leg", CubeListBuilder.create(), PartPose.offset(2.0F, -12.0F, 0.5F));

		PartDefinition cube_r1 = left_leg.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(64, 0).addBox(-2.0F, -1.0F, -2.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.5F, -0.4363F, 0.0F, 0.0F));

		PartDefinition lower_leg = left_leg.addOrReplaceChild("lower_leg", CubeListBuilder.create().texOffs(74, 30).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 5.0F, -2.25F));

		PartDefinition right_leg = body.addOrReplaceChild("right_leg", CubeListBuilder.create(), PartPose.offset(-2.0F, -12.0F, 0.5F));

		PartDefinition cube_r2 = right_leg.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(52, 67).addBox(-2.0F, -1.0F, -2.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.5F, -0.4363F, 0.0F, 0.0F));

		PartDefinition lower_leg2 = right_leg.addOrReplaceChild("lower_leg2", CubeListBuilder.create().texOffs(76, 12).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 5.0F, -2.25F));

		PartDefinition torso = body.addOrReplaceChild("torso", CubeListBuilder.create().texOffs(36, 60).addBox(-4.0F, -7.0F, -1.0F, 8.0F, 3.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(0, 58).addBox(-4.0F, -4.0F, -2.0F, 8.0F, 9.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -17.0F, 0.0F));

		PartDefinition head = torso.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.offset(0.0F, -7.0F, 0.0F));

		PartDefinition face = head.addOrReplaceChild("face", CubeListBuilder.create().texOffs(34, 47).addBox(-4.0F, -8.0F, -3.0F, 8.0F, 5.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(48, 17).addBox(-4.0F, -3.0F, -1.0F, 8.0F, 3.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(82, 50).addBox(-4.0F, -3.0F, -3.0F, 1.0F, 3.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(82, 53).addBox(0.0F, -3.0F, -3.0F, 1.0F, 3.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(76, 25).addBox(-2.0F, -3.0F, -3.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(60, 83).addBox(2.0F, -3.0F, -3.0F, 1.0F, 2.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(58, 83).addBox(-3.0F, -2.0F, -3.0F, 1.0F, 2.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(78, 29).addBox(-1.0F, -1.0F, -3.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(78, 59).addBox(1.0F, -1.0F, -3.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(82, 56).addBox(3.0F, -3.0F, -3.0F, 1.0F, 3.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(52, 83).addBox(-4.0F, -2.0F, -2.0F, 0.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(62, 83).addBox(-4.0F, -1.0F, -3.0F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(26, 73).addBox(4.0F, -3.0F, -3.0F, 0.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(76, 23).addBox(-4.0F, 0.0F, -3.0F, 8.0F, 0.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(34, 45).addBox(-2.75F, -6.0F, -3.25F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(28, 58).addBox(0.75F, -6.0F, -3.25F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(80, 4).addBox(0.75F, -5.0F, -3.05F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(80, 6).addBox(-2.75F, -5.0F, -3.05F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cap = head.addOrReplaceChild("cap", CubeListBuilder.create().texOffs(0, 41).addBox(-8.0F, -1.0F, -7.0F, 1.0F, 1.0F, 16.0F, new CubeDeformation(0.0F))
		.texOffs(16, 73).addBox(-7.0F, 0.0F, -6.0F, 0.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(34, 41).addBox(-7.0F, 0.0F, 4.0F, 0.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(80, 8).addBox(-7.0F, 1.0F, 5.0F, 0.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(54, 83).addBox(-7.0F, 2.0F, 6.0F, 0.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(56, 83).addBox(-23.0F, 2.0F, 6.0F, 0.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 47).addBox(-23.0F, 1.0F, 5.0F, 0.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(80, 0).addBox(-23.0F, 0.0F, 4.0F, 0.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(26, 77).addBox(-7.0F, 1.0F, -4.0F, 0.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(4, 84).addBox(-7.0F, 1.0F, -2.0F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(16, 79).addBox(-23.0F, 0.0F, -6.0F, 0.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(6, 84).addBox(-23.0F, 1.0F, -2.0F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(26, 81).addBox(-23.0F, 1.0F, -4.0F, 0.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(48, 26).addBox(-22.0F, -1.0F, -7.0F, 14.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(64, 16).addBox(-22.0F, 0.0F, -7.0F, 3.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(66, 83).addBox(-21.0F, 1.0F, -7.0F, 1.0F, 2.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(2, 84).addBox(-21.0F, 1.0F, 9.0F, 1.0F, 2.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(72, 59).addBox(-22.0F, 0.0F, 9.0F, 3.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(66, 59).addBox(-15.0F, 0.0F, -7.0F, 3.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(80, 11).addBox(-14.0F, 1.0F, -7.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(70, 16).addBox(-15.0F, 0.0F, 9.0F, 3.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(80, 59).addBox(-14.0F, 1.0F, 9.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(64, 83).addBox(-8.0F, 0.0F, -7.0F, 1.0F, 2.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(80, 29).addBox(-9.0F, 0.0F, -7.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(82, 11).addBox(-9.0F, 0.0F, 9.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(0, 84).addBox(-8.0F, 0.0F, 9.0F, 1.0F, 2.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(40, 30).addBox(-23.0F, -1.0F, -7.0F, 1.0F, 1.0F, 16.0F, new CubeDeformation(0.0F))
		.texOffs(48, 28).addBox(-22.0F, -1.0F, 8.0F, 14.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 17).addBox(-21.0F, -3.0F, -5.0F, 12.0F, 1.0F, 12.0F, new CubeDeformation(0.0F))
		.texOffs(0, 30).addBox(-20.0F, -4.0F, -4.0F, 10.0F, 1.0F, 10.0F, new CubeDeformation(0.0F))
		.texOffs(60, 60).addBox(-18.0F, -5.0F, -2.0F, 6.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-23.0F, -2.0F, -7.0F, 16.0F, 1.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offset(15.0F, -7.0F, 0.0F));

		PartDefinition left_arm = body.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(68, 67).addBox(0.0F, -2.0F, -2.0F, 4.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, -22.0F, 1.0F));

		PartDefinition cube_r3 = left_arm.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(66, 47).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, 4.0F, 0.2F, -0.4363F, 0.0F, 0.0F));

		PartDefinition right_arm = body.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(0, 73).addBox(-4.0F, -1.0F, -2.0F, 4.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, -22.0F, 1.0F, -0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r4 = right_arm.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(36, 67).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, 5.0F, 0.2F, -0.4363F, 0.0F, 0.0F));

		PartDefinition mush_room_staff = body.addOrReplaceChild("mush_room_staff", CubeListBuilder.create().texOffs(28, 60).addBox(-1.0F, 2.0F, -1.0F, 2.0F, 22.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(78, 25).addBox(-6.0F, 8.0F, -1.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(52, 79).addBox(-6.0F, 6.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(68, 78).addBox(-7.0F, 4.0F, -2.0F, 4.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(64, 12).addBox(1.0F, 13.0F, -1.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(60, 79).addBox(3.0F, 11.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(36, 79).addBox(2.0F, 9.0F, -2.0F, 4.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(74, 41).addBox(-3.0F, 0.0F, -2.0F, 6.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.0F, -23.0F, -10.0F, 0.2618F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(SporeSpreaderEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
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
		this.animateWalk(SporeSpreaderModelAnimation.WALK, limbSwing, 1.0f, cycleHz, amp);
		this.animate(entity.idleState, SporeSpreaderModelAnimation.IDLE, ageInTicks, 1.0f);
		this.animate(entity.summoningState, SporeSpreaderModelAnimation.SUMMON, ageInTicks, 1.0f);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		spore_spreader.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return spore_spreader;
	}

}