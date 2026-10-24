package net.ejibarra.pathtoimmortals.entity.client;// Made with Blockbench 4.12.6
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.ejibarra.pathtoimmortals.entity.animation.BeefsteakMushroomAnimation;
import net.ejibarra.pathtoimmortals.entity.animation.SporeSpreaderModelAnimation;
import net.ejibarra.pathtoimmortals.entity.custom.BeefsteakMushroomEntity;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;

public class BeefsteakMushroomModel<T extends BeefsteakMushroomEntity> extends HierarchicalModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor

	private final ModelPart beefsteak_mushroom;
	private final ModelPart body;
	private final ModelPart upper_body;
	private final ModelPart head;
	private final ModelPart cap2;
	private final ModelPart torso;
	private final ModelPart left_arm;
	private final ModelPart upper_arm;
	private final ModelPart left_shoulder;
	private final ModelPart detail;
	private final ModelPart lower_arm;
	private final ModelPart side_shroom;
	private final ModelPart top_shroom;
	private final ModelPart right_arm;
	private final ModelPart upper_arm2;
	private final ModelPart right_shoulder;
	private final ModelPart detail2;
	private final ModelPart lower_arm2;
	private final ModelPart side_shroom2;
	private final ModelPart top_shroom2;
	private final ModelPart left_leg;
	private final ModelPart upper_left_leg;
	private final ModelPart lower_left_leg;
	private final ModelPart right_leg;
	private final ModelPart upper_right_leg;
	private final ModelPart lower_right_leg;

	public BeefsteakMushroomModel(ModelPart root) {
		this.beefsteak_mushroom = root.getChild("beefsteak_mushroom");
		this.body = this.beefsteak_mushroom.getChild("body");
		this.upper_body = this.body.getChild("upper_body");
		this.head = this.upper_body.getChild("head");
		this.cap2 = this.head.getChild("cap2");
		this.torso = this.upper_body.getChild("torso");
		this.left_arm = this.upper_body.getChild("left_arm");
		this.upper_arm = this.left_arm.getChild("upper_arm");
		this.left_shoulder = this.upper_arm.getChild("left_shoulder");
		this.detail = this.left_shoulder.getChild("detail");
		this.lower_arm = this.left_arm.getChild("lower_arm");
		this.side_shroom = this.left_arm.getChild("side_shroom");
		this.top_shroom = this.left_arm.getChild("top_shroom");
		this.right_arm = this.upper_body.getChild("right_arm");
		this.upper_arm2 = this.right_arm.getChild("upper_arm2");
		this.right_shoulder = this.upper_arm2.getChild("right_shoulder");
		this.detail2 = this.right_shoulder.getChild("detail2");
		this.lower_arm2 = this.right_arm.getChild("lower_arm2");
		this.side_shroom2 = this.right_arm.getChild("side_shroom2");
		this.top_shroom2 = this.right_arm.getChild("top_shroom2");
		this.left_leg = this.body.getChild("left_leg");
		this.upper_left_leg = this.left_leg.getChild("upper_left_leg");
		this.lower_left_leg = this.left_leg.getChild("lower_left_leg");
		this.right_leg = this.body.getChild("right_leg");
		this.upper_right_leg = this.right_leg.getChild("upper_right_leg");
		this.lower_right_leg = this.right_leg.getChild("lower_right_leg");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition beefsteak_mushroom = partdefinition.addOrReplaceChild("beefsteak_mushroom", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition body = beefsteak_mushroom.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(-1.0F, 0.0F, 0.0F));

		PartDefinition upper_body = body.addOrReplaceChild("upper_body", CubeListBuilder.create(), PartPose.offset(1.0F, 0.0F, 0.0F));

		PartDefinition head = upper_body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 58).addBox(-3.0F, -7.0F, -3.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, -34.0F, -1.0F));

		PartDefinition cap2 = head.addOrReplaceChild("cap2", CubeListBuilder.create().texOffs(0, 30).addBox(-8.0F, -1.0F, -7.0F, 1.0F, 1.0F, 16.0F, new CubeDeformation(0.0F))
		.texOffs(0, 87).addBox(-7.0F, 0.0F, -6.0F, 0.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(76, 13).addBox(-7.0F, 0.0F, 4.0F, 0.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(48, 84).addBox(-7.0F, 1.0F, 5.0F, 0.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(52, 84).addBox(-7.0F, 2.0F, 6.0F, 0.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(46, 89).addBox(-23.0F, 2.0F, 6.0F, 0.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(70, 87).addBox(-23.0F, 1.0F, 5.0F, 0.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(48, 76).addBox(-23.0F, 0.0F, 4.0F, 0.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(84, 87).addBox(-7.0F, 1.0F, -4.0F, 0.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(84, 71).addBox(-7.0F, 1.0F, -2.0F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(10, 87).addBox(-23.0F, 0.0F, -6.0F, 0.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(90, 41).addBox(-23.0F, 1.0F, -2.0F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(36, 89).addBox(-23.0F, 1.0F, -4.0F, 0.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(48, 28).addBox(-22.0F, -1.0F, -7.0F, 14.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(78, 71).addBox(-22.0F, 0.0F, -7.0F, 3.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(90, 43).addBox(-21.0F, 1.0F, -7.0F, 1.0F, 2.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(48, 90).addBox(-21.0F, 1.0F, 9.0F, 1.0F, 2.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(78, 72).addBox(-22.0F, 0.0F, 9.0F, 3.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(84, 58).addBox(-15.0F, 0.0F, -7.0F, 3.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(60, 90).addBox(-14.0F, 1.0F, -7.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(84, 59).addBox(-15.0F, 0.0F, 9.0F, 3.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(62, 90).addBox(-14.0F, 1.0F, 9.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(50, 90).addBox(-8.0F, 0.0F, -7.0F, 1.0F, 2.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(70, 90).addBox(-9.0F, 0.0F, -7.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(72, 90).addBox(-9.0F, 0.0F, 9.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(52, 90).addBox(-8.0F, 0.0F, 9.0F, 1.0F, 2.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(34, 30).addBox(-23.0F, -1.0F, -7.0F, 1.0F, 1.0F, 16.0F, new CubeDeformation(0.0F))
		.texOffs(64, 11).addBox(-22.0F, -1.0F, 8.0F, 14.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 17).addBox(-21.0F, -3.0F, -5.0F, 12.0F, 1.0F, 12.0F, new CubeDeformation(0.0F))
		.texOffs(0, 47).addBox(-20.0F, -4.0F, -4.0F, 10.0F, 1.0F, 10.0F, new CubeDeformation(0.0F))
		.texOffs(68, 30).addBox(-18.0F, -5.0F, -2.0F, 6.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-23.0F, -2.0F, -7.0F, 16.0F, 1.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offset(16.0F, -6.0F, 0.0F));

		PartDefinition torso = upper_body.addOrReplaceChild("torso", CubeListBuilder.create().texOffs(68, 37).addBox(-3.0F, -17.0F, 0.0F, 8.0F, 5.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(48, 23).addBox(-4.5F, -18.0F, -1.5F, 11.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(32, 60).addBox(-4.5F, -16.0F, -1.5F, 11.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(62, 60).addBox(-4.5F, -14.0F, -1.5F, 11.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(86, 71).addBox(-1.5F, -28.0F, -2.0F, 5.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(40, 47).addBox(-5.5F, -26.0F, -2.5F, 13.0F, 8.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(48, 17).addBox(-5.0F, -27.0F, -2.5F, 12.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(54, 65).addBox(-4.5F, -26.0F, 2.5F, 11.0F, 7.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(86, 76).addBox(-5.25F, -26.0F, -2.75F, 6.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(86, 83).addBox(1.25F, -26.0F, -2.75F, 6.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, -5.0F, 0.0F));

		PartDefinition left_arm = upper_body.addOrReplaceChild("left_arm", CubeListBuilder.create(), PartPose.offset(7.0F, -29.0F, 0.0F));

		PartDefinition upper_arm = left_arm.addOrReplaceChild("upper_arm", CubeListBuilder.create().texOffs(82, 13).addBox(-5.0F, -12.0F, -1.0F, 4.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(4.5F, 15.0F, -1.0F));

		PartDefinition left_shoulder = upper_arm.addOrReplaceChild("left_shoulder", CubeListBuilder.create().texOffs(64, 0).addBox(-1.0F, -5.0F, -1.0F, 5.0F, 5.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.0F, -12.0F, -1.0F));

		PartDefinition detail = left_shoulder.addOrReplaceChild("detail", CubeListBuilder.create().texOffs(38, 89).addBox(0.0F, -3.0F, 1.0F, 1.0F, 4.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(48, 80).addBox(5.0F, -4.0F, -2.0F, 0.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(78, 89).addBox(5.0F, -3.0F, 0.0F, 0.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(54, 90).addBox(5.0F, -3.0F, -1.0F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(76, 58).addBox(0.0F, -4.0F, 1.0F, 4.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(74, 87).addBox(1.0F, -4.0F, -5.0F, 3.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(80, 90).addBox(2.0F, -3.0F, -5.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(78, 23).addBox(2.0F, -3.0F, 1.0F, 2.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(56, 90).addBox(2.0F, -2.0F, 1.0F, 1.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, 4.0F, 4.0F));

		PartDefinition lower_arm = left_arm.addOrReplaceChild("lower_arm", CubeListBuilder.create(), PartPose.offset(1.5F, 10.0F, 0.0F));

		PartDefinition cube_r1 = lower_arm.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(78, 24).addBox(-4.0F, -2.0F, -1.0F, 5.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.5F, 6.0F, -5.0F, -0.5236F, 0.0F, 0.0F));

		PartDefinition cube_r2 = lower_arm.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(32, 76).addBox(-2.0F, -1.0F, -2.0F, 4.0F, 9.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.5236F, 0.0F, 0.0F));

		PartDefinition side_shroom = left_arm.addOrReplaceChild("side_shroom", CubeListBuilder.create().texOffs(32, 58).addBox(-1.0F, -1.0F, 0.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(40, 89).addBox(1.0F, -2.0F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(48, 87).addBox(0.5F, -3.0F, -0.5F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(5.0F, 1.0F, 0.0F));

		PartDefinition top_shroom = left_arm.addOrReplaceChild("top_shroom", CubeListBuilder.create().texOffs(80, 87).addBox(1.0F, -2.0F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(64, 13).addBox(0.0F, -3.0F, -1.0F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.5F, -2.0F, 0.0F));

		PartDefinition right_arm = upper_body.addOrReplaceChild("right_arm", CubeListBuilder.create(), PartPose.offset(-6.0F, -28.0F, 0.0F));

		PartDefinition upper_arm2 = right_arm.addOrReplaceChild("upper_arm2", CubeListBuilder.create().texOffs(86, 0).addBox(1.0F, -12.0F, -1.0F, 4.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.5F, 14.0F, -1.0F));

		PartDefinition right_shoulder = upper_arm2.addOrReplaceChild("right_shoulder", CubeListBuilder.create().texOffs(32, 65).addBox(-4.0F, -5.0F, -1.0F, 5.0F, 5.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, -12.0F, -1.0F));

		PartDefinition detail2 = right_shoulder.addOrReplaceChild("detail2", CubeListBuilder.create().texOffs(44, 89).addBox(-1.0F, -3.0F, 1.0F, 1.0F, 4.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(64, 87).addBox(-5.0F, -4.0F, -2.0F, 0.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(90, 37).addBox(-5.0F, -3.0F, 0.0F, 0.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(58, 90).addBox(-5.0F, -3.0F, -1.0F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(76, 59).addBox(-4.0F, -4.0F, 1.0F, 4.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(74, 88).addBox(-4.0F, -4.0F, -5.0F, 3.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(82, 90).addBox(-3.0F, -3.0F, -5.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(90, 40).addBox(-4.0F, -3.0F, 1.0F, 2.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(90, 58).addBox(-3.0F, -2.0F, 1.0F, 1.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, 4.0F, 4.0F));

		PartDefinition lower_arm2 = right_arm.addOrReplaceChild("lower_arm2", CubeListBuilder.create(), PartPose.offset(-2.5F, 9.0F, 0.0F));

		PartDefinition cube_r3 = lower_arm2.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(78, 65).addBox(-1.0F, -2.0F, -1.0F, 5.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.5F, 6.0F, -5.0F, -0.5236F, 0.0F, 0.0F));

		PartDefinition cube_r4 = lower_arm2.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(76, 45).addBox(-2.0F, -1.0F, -2.0F, 4.0F, 9.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.5236F, 0.0F, 0.0F));

		PartDefinition side_shroom2 = right_arm.addOrReplaceChild("side_shroom2", CubeListBuilder.create().texOffs(68, 45).addBox(-2.0F, -1.0F, 0.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(74, 89).addBox(-2.0F, -2.0F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(56, 87).addBox(-2.5F, -3.0F, -0.5F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-6.0F, 0.0F, 0.0F));

		PartDefinition top_shroom2 = right_arm.addOrReplaceChild("top_shroom2", CubeListBuilder.create().texOffs(32, 89).addBox(-2.0F, -2.0F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(20, 87).addBox(-3.0F, -3.0F, -1.0F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.5F, -3.0F, 0.0F));

		PartDefinition left_leg = body.addOrReplaceChild("left_leg", CubeListBuilder.create(), PartPose.offsetAndRotation(3.0F, -18.0F, 0.25F, 0.0F, -0.1745F, 0.0F));

		PartDefinition upper_left_leg = left_leg.addOrReplaceChild("upper_left_leg", CubeListBuilder.create(), PartPose.offset(-1.1585F, 14.0F, -0.8112F));

		PartDefinition cube_r5 = upper_left_leg.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(16, 74).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 9.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -12.0F, 1.0F, -0.3054F, 0.0F, 0.0F));

		PartDefinition lower_left_leg = left_leg.addOrReplaceChild("lower_left_leg", CubeListBuilder.create().texOffs(70, 73).addBox(-1.0F, -6.0F, -3.0F, 4.0F, 10.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.1585F, 14.0F, -0.8112F));

		PartDefinition right_leg = body.addOrReplaceChild("right_leg", CubeListBuilder.create(), PartPose.offsetAndRotation(-1.0F, -18.0F, 0.25F, 0.0F, 0.1745F, 0.0F));

		PartDefinition upper_right_leg = right_leg.addOrReplaceChild("upper_right_leg", CubeListBuilder.create(), PartPose.offset(1.1585F, 14.0F, -0.8112F));

		PartDefinition cube_r6 = upper_right_leg.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(16, 74).mirror().addBox(-2.0F, -2.0F, -2.0F, 4.0F, 9.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-1.0F, -12.0F, 1.0F, -0.3054F, 0.0F, 0.0F));

		PartDefinition lower_right_leg = right_leg.addOrReplaceChild("lower_right_leg", CubeListBuilder.create().texOffs(70, 73).mirror().addBox(-2.0F, -1.0F, -2.0F, 4.0F, 10.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.1585F, 9.0F, -1.8112F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(BeefsteakMushroomEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
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
		this.animateWalk(BeefsteakMushroomAnimation.WALK, limbSwing, 1.0f, cycleHz, amp);
		this.animate(entity.idleState, BeefsteakMushroomAnimation.IDLE, ageInTicks, 1.0f);
		this.animate(entity.jabState, BeefsteakMushroomAnimation.JAB, ageInTicks, 1.0f);
		this.animate(entity.punchState, BeefsteakMushroomAnimation.POWER_PUNCH, ageInTicks, 1.0f);
		this.animate(entity.kickState, BeefsteakMushroomAnimation.KICK, ageInTicks, 1.0f);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		beefsteak_mushroom.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return beefsteak_mushroom;
	}

}