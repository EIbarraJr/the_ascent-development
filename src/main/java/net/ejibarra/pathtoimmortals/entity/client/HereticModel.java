package net.ejibarra.pathtoimmortals.entity.client;// Made with Blockbench 4.12.6
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.ejibarra.pathtoimmortals.entity.animation.HereticAnimations;
import net.ejibarra.pathtoimmortals.entity.custom.HereticEntity;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;

public class HereticModel<T extends HereticEntity> extends HierarchicalModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor

	private final ModelPart heretic;
	private final ModelPart body;
	private final ModelPart torso;
	private final ModelPart head;
	private final ModelPart spine;
	private final ModelPart right_arm;
	private final ModelPart left_arm;
	private final ModelPart left_leg;
	private final ModelPart right_leg;

	public HereticModel(ModelPart root) {
		this.heretic = root.getChild("heretic");
		this.body = this.heretic.getChild("body");
		this.torso = this.body.getChild("torso");
		this.head = this.torso.getChild("head");
		this.spine = this.torso.getChild("spine");
		this.right_arm = this.body.getChild("right_arm");
		this.left_arm = this.body.getChild("left_arm");
		this.left_leg = this.body.getChild("left_leg");
		this.right_leg = this.body.getChild("right_leg");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition heretic = partdefinition.addOrReplaceChild("heretic", CubeListBuilder.create(), PartPose.offset(-3.0F, 12.0F, -2.0F));

		PartDefinition body = heretic.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition torso = body.addOrReplaceChild("torso", CubeListBuilder.create().texOffs(0, 16).addBox(-5.0F, -12.0F, -1.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(5.0F, 0.0F, 0.0F));

		PartDefinition head = torso.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -5.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, -12.0F, 0.0F));

		PartDefinition spine = torso.addOrReplaceChild("spine", CubeListBuilder.create().texOffs(16, 44).addBox(-4.0F, -12.0F, 2.0F, 6.0F, 8.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(48, 14).addBox(-3.5F, -4.0F, 2.75F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(32, 48).addBox(-3.0F, -14.0F, -0.25F, 4.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(40, 28).addBox(-3.0F, -15.5F, 1.25F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.25F));

		PartDefinition right_arm = body.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(36, 32).addBox(-4.0F, -1.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(40, 16).addBox(-5.0F, -2.0F, -3.0F, 4.0F, 6.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(60, 14).addBox(-4.0F, -1.0F, -4.5F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(60, 23).addBox(-4.0F, -1.0F, 2.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(58, 7).addBox(-5.5F, -2.5F, 1.5F, 4.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(58, 46).addBox(-5.5F, -2.5F, -2.5F, 4.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(58, 53).addBox(-5.5F, -2.5F, -0.5F, 4.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -11.0F, 1.0F));

		PartDefinition cube_r1 = right_arm.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(58, 60).addBox(0.0F, -0.5F, 0.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, -1.0F, 5.5F, 0.8727F, 0.0F, 0.0F));

		PartDefinition cube_r2 = right_arm.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(40, 60).addBox(0.0F, -0.5F, 0.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, -0.5F, 3.75F, 0.3491F, 0.0F, 0.0F));

		PartDefinition cube_r3 = right_arm.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(60, 20).addBox(0.0F, -0.5F, -2.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, -1.0F, -6.0F, -0.8727F, 0.0F, 0.0F));

		PartDefinition cube_r4 = right_arm.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(60, 17).addBox(0.0F, -0.5F, -2.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, -0.5F, -4.25F, -0.3491F, 0.0F, 0.0F));

		PartDefinition left_arm = body.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(36, 32).mirror().addBox(0.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(40, 16).mirror().addBox(1.0F, -1.0F, -3.0F, 4.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(60, 14).mirror().addBox(3.0F, 0.0F, -4.5F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(60, 23).mirror().addBox(3.0F, 0.0F, 2.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(58, 7).mirror().addBox(1.5F, -1.5F, 1.5F, 4.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(58, 46).mirror().addBox(1.5F, -1.5F, -2.5F, 4.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(58, 53).mirror().addBox(1.5F, -1.5F, -0.5F, 4.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(8.0F, -12.0F, 1.0F));

		PartDefinition cube_r5 = left_arm.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(58, 60).mirror().addBox(-1.0F, -0.5F, 0.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(4.0F, 0.0F, 5.5F, 0.8727F, 0.0F, 0.0F));

		PartDefinition cube_r6 = left_arm.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(40, 60).mirror().addBox(-1.0F, -0.5F, 0.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(4.0F, 0.5F, 3.75F, 0.3491F, 0.0F, 0.0F));

		PartDefinition cube_r7 = left_arm.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(60, 20).mirror().addBox(-1.0F, -0.5F, -2.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(4.0F, 0.0F, -6.0F, -0.8727F, 0.0F, 0.0F));

		PartDefinition cube_r8 = left_arm.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(60, 17).mirror().addBox(-1.0F, -0.5F, -2.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(4.0F, 0.5F, -4.25F, -0.3491F, 0.0F, 0.0F));

		PartDefinition left_leg = body.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(48, 48).addBox(-1.75F, 0.0F, -2.75F, 4.0F, 10.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 48).addBox(1.75F, 0.0F, -2.25F, 1.0F, 10.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(52, 28).addBox(-1.75F, 0.0F, 1.25F, 4.0F, 10.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(24, 16).addBox(-1.75F, -0.5F, -2.25F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(5.75F, 0.5F, 1.25F));

		PartDefinition right_leg = body.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(48, 0).addBox(-2.5F, 0.0F, -2.0F, 1.0F, 10.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(10, 54).addBox(-2.0F, 0.0F, -2.5F, 4.0F, 10.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(20, 54).addBox(-2.0F, 0.0F, 1.5F, 4.0F, 10.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(32, 0).addBox(-2.0F, -0.5F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, 0.5F, 1.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(HereticEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
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
		this.animateWalk(HereticAnimations.HERETIC_WALK, limbSwing, 1.0f,cycleHz,amp);
		this.animate(entity.idleHereticState, HereticAnimations.HERETIC_IDLE, ageInTicks, 1f);
		this.animate(entity.hereticAttackingState, HereticAnimations.HERETIC_ATTACK, ageInTicks, 1f);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		heretic.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return heretic;
	}
}