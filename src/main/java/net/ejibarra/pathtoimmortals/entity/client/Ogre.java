package net.ejibarra.pathtoimmortals.entity.client;// Made with Blockbench 4.12.6
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.ejibarra.pathtoimmortals.entity.animation.OgreModelAnimation;
import net.ejibarra.pathtoimmortals.entity.custom.OgreEntity;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;

public class Ogre<T extends OgreEntity> extends HierarchicalModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor

	private final ModelPart ogre;
	private final ModelPart body;
	private final ModelPart left_leg;
	private final ModelPart right_leg;
	private final ModelPart torso;
	private final ModelPart head;
	private final ModelPart neck;
	private final ModelPart toga;
	private final ModelPart left_arm;
	private final ModelPart upper_left_arm;
	private final ModelPart lower_left_arm;
	private final ModelPart left_hand;
	private final ModelPart spiked_club;
	private final ModelPart right_arm;
	private final ModelPart upper_right_arm;
	private final ModelPart lower_right_arm;
	private final ModelPart right_hand;

	public Ogre(ModelPart root) {
		this.ogre = root.getChild("ogre");
		this.body = this.ogre.getChild("body");
		this.left_leg = this.body.getChild("left_leg");
		this.right_leg = this.body.getChild("right_leg");
		this.torso = this.body.getChild("torso");
		this.head = this.torso.getChild("head");
		this.neck = this.torso.getChild("neck");
		this.toga = this.torso.getChild("toga");
		this.left_arm = this.body.getChild("left_arm");
		this.upper_left_arm = this.left_arm.getChild("upper_left_arm");
		this.lower_left_arm = this.left_arm.getChild("lower_left_arm");
		this.left_hand = this.lower_left_arm.getChild("left_hand");
		this.spiked_club = this.left_hand.getChild("spiked_club");
		this.right_arm = this.body.getChild("right_arm");
		this.upper_right_arm = this.right_arm.getChild("upper_right_arm");
		this.lower_right_arm = this.right_arm.getChild("lower_right_arm");
		this.right_hand = this.lower_right_arm.getChild("right_hand");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition ogre = partdefinition.addOrReplaceChild("ogre", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition body = ogre.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition left_leg = body.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(44, 107).addBox(-3.0F, 8.0F, -5.5F, 6.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(74, 53).addBox(-3.5F, 8.0F, -4.5F, 7.0F, 11.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(82, 125).addBox(-3.5F, 9.5F, 2.5F, 7.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(116, 70).addBox(-3.5F, 9.5F, 1.5F, 7.0F, 9.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(60, 73).addBox(-3.5F, 19.0F, -7.5F, 7.0F, 2.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.5F, -21.0F, -0.5F, 0.0F, -0.3491F, 0.0F));

		PartDefinition cube_r1 = left_leg.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(38, 125).addBox(-4.0F, -8.0237F, -5.7836F, 7.0F, 8.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, 9.5F, 8.0F, -0.2182F, 0.0F, 0.0F));

		PartDefinition cube_r2 = left_leg.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 73).addBox(-4.0F, -10.0F, -6.0F, 8.0F, 10.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 9.0F, 1.0F, -0.2182F, 0.0F, 0.0F));

		PartDefinition right_leg = body.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(124, 63).addBox(-3.0F, 8.0F, -4.5F, 6.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(86, 84).addBox(-3.5F, 8.0F, -3.5F, 7.0F, 11.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(126, 113).addBox(-3.5F, 9.5F, 3.5F, 7.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(124, 30).addBox(-3.5F, 9.5F, 2.5F, 7.0F, 9.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 90).addBox(-3.5F, 19.0F, -6.5F, 7.0F, 2.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.5F, -21.0F, -1.5F, 0.0F, 0.1745F, 0.0F));

		PartDefinition cube_r3 = right_leg.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(66, 125).addBox(-3.0F, -8.0237F, -5.7836F, 7.0F, 8.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 9.5F, 9.0F, -0.2182F, 0.0F, 0.0F));

		PartDefinition cube_r4 = right_leg.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(30, 73).addBox(-4.0F, -10.0F, -6.0F, 8.0F, 10.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 9.0F, 2.0F, -0.2182F, 0.0F, 0.0F));

		PartDefinition torso = body.addOrReplaceChild("torso", CubeListBuilder.create().texOffs(0, 14).addBox(-10.0F, -9.0F, -1.0F, 19.0F, 10.0F, 10.0F, new CubeDeformation(0.0F))
		.texOffs(0, 34).addBox(-9.5F, -19.0F, -1.0F, 18.0F, 10.0F, 10.0F, new CubeDeformation(0.0F))
		.texOffs(58, 14).addBox(-9.5F, -10.0F, -2.0F, 18.0F, 11.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(20, 119).addBox(-0.4F, -18.75F, -2.0F, 8.0F, 7.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 120).addBox(-8.6F, -18.75F, -2.0F, 8.0F, 7.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(76, 32).addBox(-8.5F, -11.75F, -2.0F, 7.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(104, 30).addBox(0.5F, -11.75F, -2.0F, 7.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(68, 0).addBox(-8.5F, -9.0F, -3.0F, 16.0F, 10.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(92, 41).addBox(-7.5F, -8.0F, -4.0F, 14.0F, 9.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.5F, -26.0F, -5.0F));

		PartDefinition head = torso.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 54).addBox(-4.0F, -10.0F, -7.0F, 10.0F, 10.0F, 9.0F, new CubeDeformation(0.0F))
		.texOffs(44, 114).addBox(-4.0F, -10.0F, -8.0F, 10.0F, 10.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(68, 11).addBox(-4.0F, -2.0F, -8.25F, 10.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(38, 119).addBox(3.5F, -4.0F, -8.55F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(132, 67).addBox(-0.5F, -7.0F, -8.5F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(90, 11).addBox(0.0F, -6.0F, -8.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(120, 30).addBox(0.5F, -8.0F, -8.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(38, 122).addBox(-2.5F, -4.0F, -8.55F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(132, 25).addBox(-2.5F, -9.0F, -8.35F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(132, 27).addBox(1.5F, -9.0F, -8.35F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.5F, -20.0F, 3.0F));

		PartDefinition cube_r5 = head.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(98, 127).addBox(-1.0F, -2.0F, -2.0F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(28, 127).addBox(9.0F, -2.0F, -2.0F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, -5.75F, -1.0F, 0.4363F, 0.0F, 0.0F));

		PartDefinition cube_r6 = head.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(0, 128).addBox(-1.0F, -2.0F, -2.0F, 2.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(108, 127).addBox(9.0F, -2.0F, -2.0F, 2.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, -6.25F, 1.0F, 0.4363F, 0.0F, 0.0F));

		PartDefinition neck = torso.addOrReplaceChild("neck", CubeListBuilder.create().texOffs(92, 32).addBox(-4.0F, -1.0F, -5.0F, 9.0F, 2.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(102, 0).addBox(-5.5F, -4.0F, 1.0F, 12.0F, 5.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(104, 25).addBox(-6.0F, -3.0F, 3.0F, 13.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(102, 7).addBox(-6.5F, -2.0F, 4.0F, 14.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(100, 67).addBox(-7.0F, -1.0F, 5.0F, 15.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, -20.0F, 3.0F));

		PartDefinition toga = torso.addOrReplaceChild("toga", CubeListBuilder.create().texOffs(82, 101).addBox(0.0F, -2.0F, -8.0F, 1.0F, 2.0F, 11.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-21.0F, -4.0F, -9.0F, 22.0F, 2.0F, 12.0F, new CubeDeformation(0.0F))
		.texOffs(58, 26).addBox(-21.0F, -2.0F, -8.0F, 22.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(58, 32).addBox(-14.0F, 3.0F, -8.0F, 9.0F, 2.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(112, 98).addBox(-14.5F, 0.0F, -8.0F, 10.0F, 3.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(56, 53).addBox(-13.5F, 5.0F, -8.0F, 8.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(44, 111).addBox(-13.0F, 6.0F, -8.0F, 7.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(44, 113).addBox(-12.5F, 7.0F, -8.0F, 6.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(96, 25).addBox(-11.5F, 8.0F, -8.0F, 4.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(116, 80).addBox(-14.5F, 0.0F, 3.0F, 10.0F, 3.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(74, 70).addBox(-14.0F, 3.0F, 3.0F, 9.0F, 2.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(74, 72).addBox(-13.5F, 5.0F, 3.0F, 8.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(44, 112).addBox(-13.0F, 6.0F, 3.0F, 7.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(124, 40).addBox(-11.5F, 8.0F, 3.0F, 4.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(116, 83).addBox(-12.5F, 7.0F, 3.0F, 6.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(58, 29).addBox(-21.0F, -2.0F, 2.0F, 22.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(58, 101).addBox(-21.0F, -2.0F, -8.0F, 1.0F, 2.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offset(9.5F, 5.0F, 7.0F));

		PartDefinition left_arm = body.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(60, 84).addBox(-3.5F, -1.0F, -6.0F, 3.0F, 7.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(9.5F, -44.5F, 0.0F, 0.0F, -0.1745F, 0.0F));

		PartDefinition upper_left_arm = left_arm.addOrReplaceChild("upper_left_arm", CubeListBuilder.create().texOffs(92, 70).addBox(-5.0F, -10.0F, -1.0F, 6.0F, 8.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(66, 114).addBox(-4.5F, -10.0F, -4.0F, 5.0F, 8.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(38, 54).addBox(-6.0F, -19.0F, -4.5F, 8.0F, 9.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(5.5F, 17.0F, -1.5F));

		PartDefinition lower_left_arm = left_arm.addOrReplaceChild("lower_left_arm", CubeListBuilder.create().texOffs(20, 101).addBox(-2.0F, -2.0F, 3.0F, 4.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(112, 84).addBox(-3.0F, -0.5F, -1.0F, 6.0F, 10.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(120, 11).addBox(-2.5F, -0.5F, -3.0F, 5.0F, 10.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(3.5F, 15.5F, -1.0F));

		PartDefinition left_hand = lower_left_arm.addOrReplaceChild("left_hand", CubeListBuilder.create().texOffs(106, 101).addBox(-3.0F, -5.5F, -4.0F, 6.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 15.0F, 1.0F));

		PartDefinition spiked_club = left_hand.addOrReplaceChild("spiked_club", CubeListBuilder.create().texOffs(54, 125).addBox(-1.0F, -10.0F, -1.0F, 3.0F, 10.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(122, 41).addBox(-1.5F, -16.0F, -1.5F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(0, 101).addBox(-2.0F, -30.0F, -2.0F, 5.0F, 14.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(100, 51).addBox(-2.5F, -40.0F, -2.5F, 6.0F, 10.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(10, 128).addBox(1.5F, -34.5F, -4.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(130, 101).addBox(1.5F, -34.5F, 2.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.25F, -2.0F, 4.0F, 1.5708F, -0.0873F, 0.0F));

		PartDefinition cube_r7 = spiked_club.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(0, 132).addBox(-2.0F, -1.0F, -1.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, -31.0F, -1.0F, 0.3415F, -0.456F, -0.548F));

		PartDefinition cube_r8 = spiked_club.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(132, 6).addBox(-2.0F, -1.0F, -1.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, -31.0F, 3.0F, 0.3034F, 0.4011F, -0.4287F));

		PartDefinition cube_r9 = spiked_club.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(8, 132).addBox(-3.0F, -0.75F, -0.5F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, -34.25F, 1.5F, -0.3927F, 0.48F, 0.0F));

		PartDefinition cube_r10 = spiked_club.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(132, 8).addBox(-4.0F, -1.0F, -0.75F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -34.0F, -1.25F, -0.5518F, -0.3503F, 0.0074F));

		PartDefinition cube_r11 = spiked_club.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(16, 132).addBox(-2.0F, -1.0F, -1.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, -38.0F, -1.0F, -0.6905F, -0.2936F, 0.456F));

		PartDefinition cube_r12 = spiked_club.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(24, 132).addBox(-4.0F, -1.0F, -1.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -38.0F, 2.25F, 0.5604F, 0.2494F, 0.3155F));

		PartDefinition cube_r13 = spiked_club.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(116, 131).addBox(-1.0F, -1.0F, -1.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, -31.0F, -1.0F, 0.3415F, 0.456F, 0.548F));

		PartDefinition cube_r14 = spiked_club.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(124, 131).addBox(0.0F, -0.75F, -0.5F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, -34.25F, 1.5F, -0.3927F, -0.48F, 0.0F));

		PartDefinition cube_r15 = spiked_club.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(130, 4).addBox(1.0F, -1.0F, -0.75F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, -34.0F, -1.25F, -0.5518F, 0.3503F, -0.0074F));

		PartDefinition cube_r16 = spiked_club.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(128, 23).addBox(-1.0F, -1.0F, -1.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, -31.0F, 3.0F, 0.3034F, -0.4011F, 0.4287F));

		PartDefinition cube_r17 = spiked_club.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(120, 23).addBox(1.0F, -1.0F, -1.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, -38.0F, 2.25F, 0.5604F, -0.2494F, -0.3155F));

		PartDefinition cube_r18 = spiked_club.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(92, 51).addBox(-1.0F, -1.0F, -1.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, -38.0F, -1.0F, -0.6905F, 0.2936F, -0.456F));

		PartDefinition cube_r19 = spiked_club.addOrReplaceChild("cube_r19", CubeListBuilder.create().texOffs(130, 0).addBox(-0.5F, -1.0F, 0.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -37.5F, 3.0F, 0.4436F, -0.6485F, -0.2795F));

		PartDefinition cube_r20 = spiked_club.addOrReplaceChild("cube_r20", CubeListBuilder.create().texOffs(98, 118).addBox(-0.5F, -1.0F, -3.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -37.5F, -2.0F, -0.4436F, 0.6485F, -0.2795F));

		PartDefinition cube_r21 = spiked_club.addOrReplaceChild("cube_r21", CubeListBuilder.create().texOffs(130, 105).addBox(-1.0F, -1.5F, 1.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, -30.0F, 2.0F, -0.3725F, 0.4503F, -0.0207F));

		PartDefinition cube_r22 = spiked_club.addOrReplaceChild("cube_r22", CubeListBuilder.create().texOffs(130, 109).addBox(-1.5F, -2.0F, 2.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -30.5F, 1.0F, -0.3054F, -0.3054F, 0.0F));

		PartDefinition cube_r23 = spiked_club.addOrReplaceChild("cube_r23", CubeListBuilder.create().texOffs(18, 127).addBox(-1.5F, -2.0F, 0.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -32.5F, 2.0F, -0.1525F, -0.2136F, 0.6272F));

		PartDefinition cube_r24 = spiked_club.addOrReplaceChild("cube_r24", CubeListBuilder.create().texOffs(108, 131).addBox(-1.0F, -1.0F, 0.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, -38.5F, 3.0F, 0.5236F, 0.3491F, 0.0F));

		PartDefinition cube_r25 = spiked_club.addOrReplaceChild("cube_r25", CubeListBuilder.create().texOffs(118, 127).addBox(-1.0F, -1.0F, -3.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, -38.5F, -2.0F, -0.5236F, -0.3491F, 0.0F));

		PartDefinition cube_r26 = spiked_club.addOrReplaceChild("cube_r26", CubeListBuilder.create().texOffs(98, 122).addBox(-1.0F, -1.5F, -4.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, -30.0F, -1.0F, 0.3725F, -0.4503F, -0.0207F));

		PartDefinition cube_r27 = spiked_club.addOrReplaceChild("cube_r27", CubeListBuilder.create().texOffs(126, 126).addBox(-1.5F, -2.0F, -4.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -32.5F, -1.0F, 0.1525F, 0.2136F, 0.6272F));

		PartDefinition cube_r28 = spiked_club.addOrReplaceChild("cube_r28", CubeListBuilder.create().texOffs(98, 114).addBox(-1.5F, -2.0F, -5.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -30.5F, 0.0F, 0.3054F, 0.3054F, 0.0F));

		PartDefinition right_arm = body.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(32, 90).addBox(0.5F, -1.0F, -6.0F, 3.0F, 7.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-9.5F, -44.5F, 0.0F, 0.0F, 0.1745F, 0.0F));

		PartDefinition upper_right_arm = right_arm.addOrReplaceChild("upper_right_arm", CubeListBuilder.create().texOffs(96, 11).addBox(-1.0F, -10.0F, -1.0F, 6.0F, 8.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(82, 114).addBox(-0.5F, -10.0F, -4.0F, 5.0F, 8.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(56, 34).addBox(-2.0F, -19.0F, -4.5F, 8.0F, 9.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.5F, 17.0F, -1.5F));

		PartDefinition lower_right_arm = right_arm.addOrReplaceChild("lower_right_arm", CubeListBuilder.create().texOffs(126, 120).addBox(-1.0F, -4.0F, -1.0F, 4.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(106, 113).addBox(-2.0F, -2.5F, -5.0F, 6.0F, 10.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(124, 51).addBox(-1.5F, -2.5F, -7.0F, 5.0F, 10.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.5F, 17.5F, 3.0F));

		PartDefinition right_hand = lower_right_arm.addOrReplaceChild("right_hand", CubeListBuilder.create().texOffs(20, 107).addBox(-16.0F, -19.5F, -4.0F, 6.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(14.0F, 27.0F, -3.0F));

		return LayerDefinition.create(meshdefinition, 256, 256);
	}

	@Override
	public void setupAnim(OgreEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		// real per-tick ground speed
		float horiz = (float)entity.getDeltaMovement().horizontalDistance();

		// Frequency: how fast to loop the walk clip (in Hz). 0.28f is fairly brisk, so scale aggressively.
		// Try 80 first; go to 92–100 if it still slides.
		float cycleHz = Mth.clamp(horiz * 92.0f, 1.2f, 4.2f);

		// Amplitude: how far limbs swing. Derive from speed, not limbSwingAmount.
		// Bump the multiplier if steps still feel short (try 20 → 24 → 28).
		float amp = Mth.clamp(horiz * 28.0f, 0.35f, 1.75f);
		this.animateWalk(OgreModelAnimation.WALK, limbSwing, 1.0f, cycleHz, amp);
		this.animate(entity.idleState, OgreModelAnimation.IDLE, ageInTicks );
		this.animate(entity.whackState, OgreModelAnimation.WHACK_ATTACK, ageInTicks );
		this.animate(entity.slamState, OgreModelAnimation.SLAM_ATTACK, ageInTicks );
		this.animate(entity.screamState, OgreModelAnimation.ENRAGED_SCREAM, ageInTicks );
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		ogre.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return ogre;
	}


}