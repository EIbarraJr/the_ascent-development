package net.ejibarra.pathtoimmortals.entity.client;// Made with Blockbench 4.12.6
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.ejibarra.pathtoimmortals.entity.animation.RatLordModelAnimation;
import net.ejibarra.pathtoimmortals.entity.custom.RatLord;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;

public class RatLordModel<T extends RatLord> extends HierarchicalModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor

	private final ModelPart rat_lord;
	private final ModelPart body;
	private final ModelPart torso;
	private final ModelPart head;
	private final ModelPart neck;
	private final ModelPart abdomen;
	private final ModelPart satchel_armor;
	private final ModelPart bobin_crown;
	private final ModelPart cape;
	private final ModelPart right_arm;
	private final ModelPart right_upper_arm;
	private final ModelPart right_shoulder;
	private final ModelPart right_lower_arm;
	private final ModelPart right_hand;
	private final ModelPart claw1;
	private final ModelPart claw2;
	private final ModelPart claw3;
	private final ModelPart left_arm;
	private final ModelPart left_upper_arm;
	private final ModelPart left_shoulder;
	private final ModelPart left_lower_arm;
	private final ModelPart left_hand;
	private final ModelPart claw6;
	private final ModelPart claw5;
	private final ModelPart claw4;
	private final ModelPart right_leg;
	private final ModelPart left_leg;

	public RatLordModel(ModelPart root) {
		this.rat_lord = root.getChild("rat_lord");
		this.body = this.rat_lord.getChild("body");
		this.torso = this.body.getChild("torso");
		this.head = this.torso.getChild("head");
		this.neck = this.torso.getChild("neck");
		this.abdomen = this.torso.getChild("abdomen");
		this.satchel_armor = this.abdomen.getChild("satchel_armor");
		this.bobin_crown = this.abdomen.getChild("bobin_crown");
		this.cape = this.abdomen.getChild("cape");
		this.right_arm = this.body.getChild("right_arm");
		this.right_upper_arm = this.right_arm.getChild("right_upper_arm");
		this.right_shoulder = this.right_arm.getChild("right_shoulder");
		this.right_lower_arm = this.right_arm.getChild("right_lower_arm");
		this.right_hand = this.right_lower_arm.getChild("right_hand");
		this.claw1 = this.right_hand.getChild("claw1");
		this.claw2 = this.right_hand.getChild("claw2");
		this.claw3 = this.right_hand.getChild("claw3");
		this.left_arm = this.body.getChild("left_arm");
		this.left_upper_arm = this.left_arm.getChild("left_upper_arm");
		this.left_shoulder = this.left_arm.getChild("left_shoulder");
		this.left_lower_arm = this.left_arm.getChild("left_lower_arm");
		this.left_hand = this.left_lower_arm.getChild("left_hand");
		this.claw6 = this.left_hand.getChild("claw6");
		this.claw5 = this.left_hand.getChild("claw5");
		this.claw4 = this.left_hand.getChild("claw4");
		this.right_leg = this.body.getChild("right_leg");
		this.left_leg = this.body.getChild("left_leg");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition rat_lord = partdefinition.addOrReplaceChild("rat_lord", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 2.0F));

		PartDefinition body = rat_lord.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(-3.0F, 0.0F, -4.5F));

		PartDefinition torso = body.addOrReplaceChild("torso", CubeListBuilder.create(), PartPose.offset(3.0F, -11.0F, 6.75F));

		PartDefinition head = torso.addOrReplaceChild("head", CubeListBuilder.create().texOffs(54, 14).addBox(-2.5F, -4.0F, 0.0F, 6.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(14, 61).addBox(-2.0F, -3.5F, -2.0F, 5.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(54, 67).addBox(-1.75F, -3.25F, -2.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(70, 67).addBox(1.75F, -3.25F, -2.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(62, 0).addBox(-1.5F, -3.0F, -5.0F, 4.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(74, 36).addBox(-0.5F, -2.5F, -7.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(38, 65).addBox(0.0F, -2.5F, -5.0F, 0.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(12, 68).addBox(1.0F, -2.5F, -5.0F, 0.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.5F, -9.0F, -7.25F));

		PartDefinition cube_r1 = head.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 68).addBox(-2.0F, -2.0F, -2.0F, 3.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 1.25F, -2.5F, 0.2618F, 0.0F, 0.0F));

		PartDefinition neck = torso.addOrReplaceChild("neck", CubeListBuilder.create().texOffs(36, 14).addBox(-2.0F, -3.5F, -1.0F, 5.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(70, 13).addBox(-1.0F, -2.0F, -1.0F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.5F, -9.0F, -5.25F));

		PartDefinition abdomen = torso.addOrReplaceChild("abdomen", CubeListBuilder.create(), PartPose.offset(0.5F, 2.0F, -2.25F));

		PartDefinition cube_r2 = abdomen.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(36, 8).addBox(-5.0F, 1.0F, -1.0F, 10.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.25F, -14.5F, -1.5F, 0.1309F, 0.0F, 0.0F));

		PartDefinition cube_r3 = abdomen.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(0, 22).addBox(-5.0F, -5.0F, -1.0F, 11.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -8.0F, -1.0F, 0.1309F, 0.0F, 0.0F));

		PartDefinition cube_r4 = abdomen.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(10, 33).addBox(-3.0F, -4.0F, 0.0F, 7.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -4.0F, -1.0F, 0.1309F, 0.0F, 0.0F));

		PartDefinition cube_r5 = abdomen.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(36, 0).addBox(-5.0F, -4.0F, -1.0F, 9.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.1309F, 0.0F, 0.0F));

		PartDefinition satchel_armor = abdomen.addOrReplaceChild("satchel_armor", CubeListBuilder.create().texOffs(72, 43).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, 0.0F, -1.0F));

		PartDefinition cube_r6 = satchel_armor.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(70, 17).addBox(-5.0F, -1.0F, -1.0F, 6.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, -2.0F, 0.25F, -0.2618F, 0.0F, 0.0F));

		PartDefinition cube_r7 = satchel_armor.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(72, 49).addBox(-2.0F, -4.0F, -1.0F, 3.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.0F, 1.0F, -0.5F, -0.2618F, 0.0F, 0.0F));

		PartDefinition cube_r8 = satchel_armor.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(22, 76).addBox(0.0F, -8.0F, -1.0F, 1.0F, 8.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -1.0F, 0.5F, 0.0873F, 0.0F, -0.6109F));

		PartDefinition cube_r9 = satchel_armor.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(46, 61).addBox(-1.0F, -3.0F, -1.0F, 5.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-8.5F, -9.0F, -0.5F, 0.1309F, 0.0F, 0.0F));

		PartDefinition bobin_crown = abdomen.addOrReplaceChild("bobin_crown", CubeListBuilder.create().texOffs(40, 34).addBox(-3.0F, -25.0F, -4.0F, 6.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(10, 40).addBox(-2.5F, -25.0F, -4.5F, 5.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.5F, 9.0F, -2.0F));

		PartDefinition cube_r10 = bobin_crown.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(48, 78).addBox(-1.0F, -1.0F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(44, 78).addBox(4.5F, -1.0F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.25F, -24.5F, -2.0F, -0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r11 = bobin_crown.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(70, 77).addBox(0.0F, -1.0F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(76, 64).addBox(-1.0F, -2.0F, -4.75F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.75F, -25.0F, -0.25F, 0.0F, 0.0F, 0.7854F));

		PartDefinition cube_r12 = bobin_crown.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(66, 77).addBox(0.0F, -1.0F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.75F, -25.0F, -0.5F, -0.6155F, -0.5236F, 0.9553F));

		PartDefinition cube_r13 = bobin_crown.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(62, 77).addBox(-1.0F, -1.0F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.75F, -25.0F, -0.5F, -0.6155F, 0.5236F, -0.9553F));

		PartDefinition cube_r14 = bobin_crown.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(34, 77).addBox(-1.0F, -1.0F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.75F, -25.0F, -3.5F, 0.6155F, -0.5236F, -0.9553F));

		PartDefinition cube_r15 = bobin_crown.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(30, 77).addBox(0.0F, -1.0F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.75F, -25.0F, -3.5F, 0.6155F, 0.5236F, 0.9553F));

		PartDefinition cube_r16 = bobin_crown.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(18, 77).addBox(0.0F, -1.0F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.75F, -28.75F, -4.0F, 0.0F, 0.0F, 0.7854F));

		PartDefinition cape = abdomen.addOrReplaceChild("cape", CubeListBuilder.create().texOffs(46, 46).addBox(-3.0F, -24.0F, -1.0F, 6.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(58, 39).addBox(-3.0F, -23.0F, 2.0F, 6.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(40, 39).addBox(-6.0F, -23.0F, -1.0F, 3.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(10, 46).addBox(3.0F, -23.0F, -1.0F, 3.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(40, 22).addBox(5.0F, -22.0F, 0.0F, 5.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(60, 20).addBox(6.0F, -22.5F, 0.5F, 3.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(60, 26).addBox(-9.0F, -22.5F, 0.5F, 3.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(40, 28).addBox(-10.0F, -22.0F, 0.0F, 5.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(30, 22).addBox(-10.0F, -22.0F, 0.0F, 0.0F, 22.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(0, 33).addBox(10.0F, -22.0F, 0.0F, 0.0F, 22.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-9.0F, -22.0F, 6.0F, 18.0F, 22.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(36, 12).addBox(-6.0F, -22.5F, 5.0F, 12.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(76, 25).addBox(-9.0F, -22.0F, 5.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(76, 27).addBox(6.0F, -22.0F, 5.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.5F, 9.0F, -2.0F));

		PartDefinition cube_r17 = cape.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(62, 13).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(8.5F, -22.0F, 5.05F, 0.0F, -0.9599F, 0.0F));

		PartDefinition cube_r18 = cape.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(4, 60).addBox(-1.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-8.5F, -22.0F, 5.05F, 0.0F, 0.9599F, 0.0F));

		PartDefinition cube_r19 = cape.addOrReplaceChild("cube_r19", CubeListBuilder.create().texOffs(24, 60).addBox(0.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(8.75F, -22.0F, 5.0F, 0.0F, -0.6981F, 0.0F));

		PartDefinition cube_r20 = cape.addOrReplaceChild("cube_r20", CubeListBuilder.create().texOffs(0, 60).addBox(-1.0F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-8.75F, -22.0F, 5.0F, 0.0F, 0.6981F, 0.0F));

		PartDefinition cube_r21 = cape.addOrReplaceChild("cube_r21", CubeListBuilder.create().texOffs(42, 46).addBox(0.0F, -1.0F, -0.5F, 0.0F, 22.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(9.75F, -21.0F, 5.35F, 0.0F, -0.6109F, 0.0F));

		PartDefinition cube_r22 = cape.addOrReplaceChild("cube_r22", CubeListBuilder.create().texOffs(40, 46).addBox(0.0F, -1.0F, -0.5F, 0.0F, 22.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-9.75F, -21.0F, 5.35F, 0.0F, 0.6109F, 0.0F));

		PartDefinition cube_r23 = cape.addOrReplaceChild("cube_r23", CubeListBuilder.create().texOffs(44, 46).addBox(0.0F, 0.0F, -0.5F, 0.0F, 22.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-9.25F, -22.0F, 5.75F, 0.0F, 0.7854F, 0.0F));

		PartDefinition cube_r24 = cape.addOrReplaceChild("cube_r24", CubeListBuilder.create().texOffs(28, 46).addBox(0.0F, 0.0F, -0.5F, 0.0F, 22.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(9.25F, -22.0F, 5.75F, 0.0F, -0.7854F, 0.0F));

		PartDefinition right_arm = body.addOrReplaceChild("right_arm", CubeListBuilder.create(), PartPose.offset(-2.5F, -19.0F, 4.5F));

		PartDefinition right_upper_arm = right_arm.addOrReplaceChild("right_upper_arm", CubeListBuilder.create().texOffs(30, 49).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 5.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(14, 66).addBox(-1.5F, -2.0F, -0.5F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(14, 73).addBox(-1.0F, 3.0F, -0.5F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, 4.0F, -0.5F));

		PartDefinition right_shoulder = right_arm.addOrReplaceChild("right_shoulder", CubeListBuilder.create().texOffs(10, 53).addBox(-2.0F, -3.0F, -2.0F, 3.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(46, 67).addBox(-3.0F, -3.0F, -1.5F, 1.0F, 4.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(62, 5).addBox(-2.0F, -3.5F, -1.5F, 4.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.5F, 1.0F, 0.0F));

		PartDefinition right_lower_arm = right_arm.addOrReplaceChild("right_lower_arm", CubeListBuilder.create(), PartPose.offset(-1.0F, 13.5F, -4.0F));

		PartDefinition cube_r25 = right_lower_arm.addOrReplaceChild("cube_r25", CubeListBuilder.create().texOffs(64, 43).addBox(-1.0F, 0.0F, -2.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -5.5F, 4.5F, -0.48F, 0.0F, 0.0F));

		PartDefinition cube_r26 = right_lower_arm.addOrReplaceChild("cube_r26", CubeListBuilder.create().texOffs(24, 53).addBox(-2.0F, -5.0F, -1.0F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, -1.5F, 1.75F, -0.5236F, 0.0F, 0.0F));

		PartDefinition right_hand = right_lower_arm.addOrReplaceChild("right_hand", CubeListBuilder.create().texOffs(30, 73).addBox(-2.5F, -1.0F, -2.25F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(1.5F, -1.0F, 1.25F));

		PartDefinition claw1 = right_hand.addOrReplaceChild("claw1", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, -3.0F));

		PartDefinition cube_r27 = claw1.addOrReplaceChild("cube_r27", CubeListBuilder.create().texOffs(74, 32).addBox(-1.0F, -1.0F, -1.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, 0.0F, -1.75F, 0.0F, -0.1745F, 0.0F));

		PartDefinition cube_r28 = claw1.addOrReplaceChild("cube_r28", CubeListBuilder.create().texOffs(76, 29).addBox(-1.0F, -1.0F, 0.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.2618F, 0.0F));

		PartDefinition claw2 = right_hand.addOrReplaceChild("claw2", CubeListBuilder.create().texOffs(74, 9).addBox(-1.0F, -1.0F, -1.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, 2.0F, -5.0F));

		PartDefinition cube_r29 = claw2.addOrReplaceChild("cube_r29", CubeListBuilder.create().texOffs(22, 66).addBox(-1.0F, -1.0F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.5F, 2.75F, 0.2618F, 0.0F, 0.0F));

		PartDefinition claw3 = right_hand.addOrReplaceChild("claw3", CubeListBuilder.create(), PartPose.offset(-2.25F, 0.0F, -4.75F));

		PartDefinition cube_r30 = claw3.addOrReplaceChild("cube_r30", CubeListBuilder.create().texOffs(24, 76).addBox(-1.0F, -1.0F, 0.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.25F, 0.0F, 1.5F, 0.0F, 0.2618F, 0.0F));

		PartDefinition cube_r31 = claw3.addOrReplaceChild("cube_r31", CubeListBuilder.create().texOffs(46, 74).addBox(-1.0F, -1.0F, -1.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.1745F, 0.0F));

		PartDefinition left_arm = body.addOrReplaceChild("left_arm", CubeListBuilder.create(), PartPose.offset(8.5F, -19.0F, 4.5F));

		PartDefinition left_upper_arm = left_arm.addOrReplaceChild("left_upper_arm", CubeListBuilder.create().texOffs(30, 57).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 5.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(22, 69).addBox(-0.5F, -2.0F, -0.5F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(74, 67).addBox(-1.0F, 3.0F, -0.5F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, 4.0F, -0.5F));

		PartDefinition left_shoulder = left_arm.addOrReplaceChild("left_shoulder", CubeListBuilder.create().texOffs(58, 51).addBox(-1.0F, -3.0F, -2.0F, 3.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(38, 69).addBox(2.0F, -3.0F, -1.5F, 1.0F, 4.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(62, 9).addBox(-1.0F, -3.5F, -1.5F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.5F, 1.0F, 0.0F));

		PartDefinition left_lower_arm = left_arm.addOrReplaceChild("left_lower_arm", CubeListBuilder.create(), PartPose.offset(1.0F, 13.5F, -4.0F));

		PartDefinition cube_r32 = left_lower_arm.addOrReplaceChild("cube_r32", CubeListBuilder.create().texOffs(30, 65).addBox(-1.0F, 0.0F, -2.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -5.5F, 4.5F, -0.48F, 0.0F, 0.0F));

		PartDefinition cube_r33 = left_lower_arm.addOrReplaceChild("cube_r33", CubeListBuilder.create().texOffs(76, 18).addBox(1.0F, -5.0F, -1.0F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, -1.5F, 1.75F, -0.5236F, 0.0F, 0.0F));

		PartDefinition left_hand = left_lower_arm.addOrReplaceChild("left_hand", CubeListBuilder.create().texOffs(74, 71).addBox(-1.75F, -1.0F, -1.5F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.75F, -1.0F, 0.5F));

		PartDefinition claw6 = left_hand.addOrReplaceChild("claw6", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, -4.0F));

		PartDefinition cube_r34 = claw6.addOrReplaceChild("cube_r34", CubeListBuilder.create().texOffs(76, 58).addBox(0.0F, -1.0F, 0.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.25F, 0.0F, 1.5F, 0.0F, -0.2618F, 0.0F));

		PartDefinition cube_r35 = claw6.addOrReplaceChild("cube_r35", CubeListBuilder.create().texOffs(76, 0).addBox(0.0F, -1.0F, -1.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.1745F, 0.0F));

		PartDefinition claw5 = left_hand.addOrReplaceChild("claw5", CubeListBuilder.create().texOffs(74, 75).addBox(0.0F, -1.0F, -1.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.25F, 2.0F, -4.25F));

		PartDefinition cube_r36 = claw5.addOrReplaceChild("cube_r36", CubeListBuilder.create().texOffs(76, 39).addBox(0.0F, -1.0F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.5F, 2.75F, 0.2618F, 0.0F, 0.0F));

		PartDefinition claw4 = left_hand.addOrReplaceChild("claw4", CubeListBuilder.create(), PartPose.offset(-2.25F, 0.0F, -2.25F));

		PartDefinition cube_r37 = claw4.addOrReplaceChild("cube_r37", CubeListBuilder.create().texOffs(54, 75).addBox(0.0F, -1.0F, -1.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 0.0F, -1.75F, 0.0F, 0.1745F, 0.0F));

		PartDefinition cube_r38 = claw4.addOrReplaceChild("cube_r38", CubeListBuilder.create().texOffs(38, 76).addBox(0.0F, -1.0F, 0.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.2618F, 0.0F));

		PartDefinition right_leg = body.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(70, 18).addBox(-1.0F, 9.0F, -5.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(58, 43).addBox(-1.0F, 8.0F, -4.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(46, 51).addBox(-1.5F, 3.0F, -3.0F, 3.0F, 7.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(62, 69).addBox(0.75F, 3.5F, -2.5F, 1.0F, 6.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(68, 69).addBox(-1.75F, 3.5F, -2.5F, 1.0F, 6.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(54, 20).addBox(-1.0F, 9.0F, 0.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(54, 69).addBox(-1.0F, 3.0F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(72, 54).addBox(-1.0F, 4.0F, -3.25F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(60, 32).addBox(-1.5F, 0.0F, -2.5F, 3.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -10.0F, 5.0F));

		PartDefinition left_leg = body.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(6, 77).addBox(-1.0F, 8.0F, -4.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(76, 61).addBox(-1.0F, 7.0F, -3.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(58, 59).addBox(-1.5F, 2.0F, -2.0F, 3.0F, 7.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(70, 59).addBox(-1.75F, 2.5F, -1.5F, 1.0F, 6.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 71).addBox(0.75F, 2.5F, -1.5F, 1.0F, 6.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(12, 77).addBox(-1.0F, 8.0F, 1.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(6, 71).addBox(-1.0F, 2.0F, 0.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(76, 4).addBox(-1.0F, 3.0F, -2.25F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 61).addBox(-1.5F, -1.0F, -1.5F, 3.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(6.0F, -9.0F, 4.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(RatLord entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
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
		this.animateWalk(RatLordModelAnimation.RAT_LORD_WALK, limbSwing, 1.0f,cycleHz,amp);

		// Loops
		this.animate(entity.idleState,      RatLordModelAnimation.RAT_LORD_IDLE, ageInTicks);
		this.animate(entity.chargingState,  RatLordModelAnimation.RAT_LORD_IDLE, ageInTicks); // reuse idle during charge, or export a CHARGE anim later

		// One-shots
		this.animate(entity.cheeseCastState, RatLordModelAnimation.RAT_LORD_CHEESE_ATTACK, ageInTicks);
		this.animate(entity.swipeState,      RatLordModelAnimation.RAT_LORD_ATTACK_SWIPE,  ageInTicks);
		this.animate(entity.uppercutState,   RatLordModelAnimation.RAT_LORD_ATTACK_UPPERCUT, ageInTicks);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		rat_lord.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return rat_lord;
	}
}