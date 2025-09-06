package net.ejibarra.pathtoimmortals.entity.client;// Made with Blockbench 4.12.6
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.ejibarra.pathtoimmortals.entity.animation.ScorpionModelAnimation;
import net.ejibarra.pathtoimmortals.entity.custom.ScorpionEntity;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;

public class Scorpion<T extends ScorpionEntity> extends HierarchicalModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor

	private final ModelPart scorpion;
	private final ModelPart body;
	private final ModelPart torso;
	private final ModelPart stinger;
	private final ModelPart segment_first;
	private final ModelPart segment2;
	private final ModelPart segment3;
	private final ModelPart segment4;
	private final ModelPart segment5;
	private final ModelPart segment6;
	private final ModelPart segment7;
	private final ModelPart segment8;
	private final ModelPart segment9;
	private final ModelPart segment10;
	private final ModelPart segment11;
	private final ModelPart segment_last;
	private final ModelPart stabber;
	private final ModelPart head;
	private final ModelPart head_armor;
	private final ModelPart head_pincers;
	private final ModelPart head_pincers2;
	private final ModelPart main_abdomen;
	private final ModelPart body_to_stinger;
	private final ModelPart body2_to_stinger;
	private final ModelPart attached_to_stinger;
	private final ModelPart left_side_armor;
	private final ModelPart right_side_armor;
	private final ModelPart upper_abdomen_armor;
	private final ModelPart left_arm;
	private final ModelPart claw_arm;
	private final ModelPart claw_hand;
	private final ModelPart upper_mandible;
	private final ModelPart upper_teeth;
	private final ModelPart lower_mandible;
	private final ModelPart lower_teeth;
	private final ModelPart right_arm;
	private final ModelPart claw_arm2;
	private final ModelPart claw_hand2;
	private final ModelPart upper_mandible2;
	private final ModelPart upper_teeth2;
	private final ModelPart lower_mandible2;
	private final ModelPart lower_teeth2;
	private final ModelPart left_leg_1;
	private final ModelPart right_leg_1;
	private final ModelPart left_leg_2;
	private final ModelPart right_leg_2;
	private final ModelPart left_leg_3;
	private final ModelPart right_leg_3;

	public Scorpion(ModelPart root) {
		this.scorpion = root.getChild("scorpion");
		this.body = this.scorpion.getChild("body");
		this.torso = this.body.getChild("torso");
		this.stinger = this.torso.getChild("stinger");
		this.segment_first = this.stinger.getChild("segment_first");
		this.segment2 = this.segment_first.getChild("segment2");
		this.segment3 = this.segment2.getChild("segment3");
		this.segment4 = this.segment3.getChild("segment4");
		this.segment5 = this.segment4.getChild("segment5");
		this.segment6 = this.segment5.getChild("segment6");
		this.segment7 = this.segment6.getChild("segment7");
		this.segment8 = this.segment7.getChild("segment8");
		this.segment9 = this.segment8.getChild("segment9");
		this.segment10 = this.segment9.getChild("segment10");
		this.segment11 = this.segment10.getChild("segment11");
		this.segment_last = this.segment11.getChild("segment_last");
		this.stabber = this.segment_last.getChild("stabber");
		this.head = this.torso.getChild("head");
		this.head_armor = this.head.getChild("head_armor");
		this.head_pincers = this.head.getChild("head_pincers");
		this.head_pincers2 = this.head.getChild("head_pincers2");
		this.main_abdomen = this.torso.getChild("main_abdomen");
		this.body_to_stinger = this.main_abdomen.getChild("body_to_stinger");
		this.body2_to_stinger = this.main_abdomen.getChild("body2_to_stinger");
		this.attached_to_stinger = this.main_abdomen.getChild("attached_to_stinger");
		this.left_side_armor = this.main_abdomen.getChild("left_side_armor");
		this.right_side_armor = this.main_abdomen.getChild("right_side_armor");
		this.upper_abdomen_armor = this.torso.getChild("upper_abdomen_armor");
		this.left_arm = this.body.getChild("left_arm");
		this.claw_arm = this.left_arm.getChild("claw_arm");
		this.claw_hand = this.claw_arm.getChild("claw_hand");
		this.upper_mandible = this.claw_hand.getChild("upper_mandible");
		this.upper_teeth = this.upper_mandible.getChild("upper_teeth");
		this.lower_mandible = this.claw_hand.getChild("lower_mandible");
		this.lower_teeth = this.lower_mandible.getChild("lower_teeth");
		this.right_arm = this.body.getChild("right_arm");
		this.claw_arm2 = this.right_arm.getChild("claw_arm2");
		this.claw_hand2 = this.claw_arm2.getChild("claw_hand2");
		this.upper_mandible2 = this.claw_hand2.getChild("upper_mandible2");
		this.upper_teeth2 = this.upper_mandible2.getChild("upper_teeth2");
		this.lower_mandible2 = this.claw_hand2.getChild("lower_mandible2");
		this.lower_teeth2 = this.lower_mandible2.getChild("lower_teeth2");
		this.left_leg_1 = this.body.getChild("left_leg_1");
		this.right_leg_1 = this.body.getChild("right_leg_1");
		this.left_leg_2 = this.body.getChild("left_leg_2");
		this.right_leg_2 = this.body.getChild("right_leg_2");
		this.left_leg_3 = this.body.getChild("left_leg_3");
		this.right_leg_3 = this.body.getChild("right_leg_3");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition scorpion = partdefinition.addOrReplaceChild("scorpion", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition body = scorpion.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition torso = body.addOrReplaceChild("torso", CubeListBuilder.create(), PartPose.offset(0.0F, -2.0F, 16.0F));

		PartDefinition stinger = torso.addOrReplaceChild("stinger", CubeListBuilder.create(), PartPose.offset(0.0F, -26.5F, 4.0F));

		PartDefinition segment_first = stinger.addOrReplaceChild("segment_first", CubeListBuilder.create().texOffs(110, 48).addBox(-2.0F, -2.0F, 0.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 15.5F, 10.0F));

		PartDefinition segment2 = segment_first.addOrReplaceChild("segment2", CubeListBuilder.create().texOffs(110, 68).addBox(-2.0F, -3.0F, -1.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, 3.0F));

		PartDefinition segment3 = segment2.addOrReplaceChild("segment3", CubeListBuilder.create().texOffs(98, 111).addBox(-2.0F, -3.0F, -1.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0F, 2.0F));

		PartDefinition segment4 = segment3.addOrReplaceChild("segment4", CubeListBuilder.create().texOffs(0, 112).addBox(-2.0F, -3.0F, -1.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0F, 2.0F));

		PartDefinition segment5 = segment4.addOrReplaceChild("segment5", CubeListBuilder.create().texOffs(16, 112).addBox(-2.0F, -3.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -4.0F, 1.0F));

		PartDefinition segment6 = segment5.addOrReplaceChild("segment6", CubeListBuilder.create().texOffs(78, 112).addBox(-2.0F, -3.0F, -3.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -3.0F, 0.0F));

		PartDefinition segment7 = segment6.addOrReplaceChild("segment7", CubeListBuilder.create().texOffs(114, 31).addBox(-2.0F, -3.0F, -3.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -3.0F, -1.0F));

		PartDefinition segment8 = segment7.addOrReplaceChild("segment8", CubeListBuilder.create().texOffs(114, 39).addBox(-2.0F, -3.0F, -3.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0F, -1.0F));

		PartDefinition segment9 = segment8.addOrReplaceChild("segment9", CubeListBuilder.create().texOffs(114, 76).addBox(-2.0F, -2.0F, -4.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -3.0F, -2.0F));

		PartDefinition segment10 = segment9.addOrReplaceChild("segment10", CubeListBuilder.create().texOffs(114, 84).addBox(-2.0F, -2.0F, -4.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, -3.0F));

		PartDefinition segment11 = segment10.addOrReplaceChild("segment11", CubeListBuilder.create().texOffs(114, 111).addBox(-2.0F, -2.0F, -4.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, -3.0F));

		PartDefinition segment_last = segment11.addOrReplaceChild("segment_last", CubeListBuilder.create().texOffs(32, 115).addBox(-2.0F, -2.0F, -4.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(48, 115).addBox(-2.0F, -3.0F, -7.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(116, 0).addBox(-2.0F, -3.0F, -11.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, -3.0F));

		PartDefinition stabber = segment_last.addOrReplaceChild("stabber", CubeListBuilder.create().texOffs(0, 52).addBox(-3.0F, -11.0F, -19.0F, 6.0F, 6.0F, 9.0F, new CubeDeformation(0.0F))
		.texOffs(56, 108).addBox(-1.0F, -7.0F, -37.0F, 2.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(124, 119).addBox(-1.0F, -6.0F, -35.0F, 2.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(94, 119).addBox(-2.0F, -6.5F, -31.0F, 4.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(116, 104).addBox(-2.0F, -7.0F, -27.0F, 4.0F, 3.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(116, 8).addBox(-2.0F, -8.5F, -23.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 7.0F, -1.0F));

		PartDefinition head = torso.addOrReplaceChild("head", CubeListBuilder.create().texOffs(30, 52).addBox(-4.0F, -8.0F, -7.0F, 4.0F, 6.0F, 9.0F, new CubeDeformation(0.0F))
		.texOffs(70, 71).addBox(-5.0F, -8.0F, -6.0F, 1.0F, 6.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(70, 85).addBox(-6.0F, -8.0F, -5.0F, 1.0F, 6.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(0, 94).addBox(-7.0F, -8.0F, -4.0F, 1.0F, 6.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(56, 52).addBox(0.0F, -8.0F, -7.0F, 4.0F, 6.0F, 9.0F, new CubeDeformation(0.0F))
		.texOffs(0, 80).addBox(4.0F, -8.0F, -6.0F, 1.0F, 6.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(86, 85).addBox(5.0F, -8.0F, -5.0F, 1.0F, 6.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(40, 94).addBox(6.0F, -8.0F, -4.0F, 1.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -25.0F));

		PartDefinition head_armor = head.addOrReplaceChild("head_armor", CubeListBuilder.create().texOffs(38, 106).addBox(-1.0F, -1.0F, 0.0F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(96, 68).addBox(-1.0F, -1.0F, 1.0F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(60, 49).addBox(-1.0F, -1.0F, 2.0F, 7.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(44, 82).addBox(-1.0F, -1.0F, 3.0F, 8.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(18, 82).addBox(-7.0F, -1.0F, 3.0F, 8.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(60, 20).addBox(-6.0F, -1.0F, 2.0F, 7.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(88, 83).addBox(-5.0F, -1.0F, 1.0F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(92, 18).addBox(-4.0F, -1.0F, 0.0F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -7.5F, -6.0F));

		PartDefinition head_pincers = head.addOrReplaceChild("head_pincers", CubeListBuilder.create(), PartPose.offset(6.0F, -4.0F, -4.5F));

		PartDefinition cube_r1 = head_pincers.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(14, 120).addBox(-1.0F, -1.0F, -4.0F, 2.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -0.5F, 0.0F, -0.3491F, 0.0F));

		PartDefinition cube_r2 = head_pincers.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 106).addBox(-1.0F, -1.0F, -3.0F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 0.0F, -4.0F, 0.0F, 0.5236F, 0.0F));

		PartDefinition head_pincers2 = head.addOrReplaceChild("head_pincers2", CubeListBuilder.create(), PartPose.offset(-6.0F, -4.0F, -4.5F));

		PartDefinition cube_r3 = head_pincers2.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(76, 120).addBox(-1.0F, -1.0F, -4.0F, 2.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -0.5F, 0.0F, 0.3491F, 0.0F));

		PartDefinition cube_r4 = head_pincers2.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(124, 16).addBox(-1.0F, -1.0F, -3.0F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 0.0F, -4.0F, 0.0F, -0.5236F, 0.0F));

		PartDefinition main_abdomen = torso.addOrReplaceChild("main_abdomen", CubeListBuilder.create().texOffs(118, 92).addBox(0.0F, -8.0F, -3.0F, 1.0F, 6.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(64, 118).addBox(-15.0F, -8.0F, -3.0F, 1.0F, 6.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(0, 22).addBox(-15.0F, -8.0F, 2.0F, 16.0F, 6.0F, 9.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-14.0F, -9.0F, 11.0F, 14.0F, 6.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offset(7.0F, 0.0F, -25.0F));

		PartDefinition body_to_stinger = main_abdomen.addOrReplaceChild("body_to_stinger", CubeListBuilder.create().texOffs(50, 22).addBox(-6.0F, -3.0F, 0.0F, 12.0F, 6.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(-7.0F, -7.0F, 27.0F));

		PartDefinition body2_to_stinger = main_abdomen.addOrReplaceChild("body2_to_stinger", CubeListBuilder.create().texOffs(0, 71).addBox(-5.0F, -3.0F, -3.0F, 10.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-7.0F, -8.0F, 35.0F));

		PartDefinition attached_to_stinger = main_abdomen.addOrReplaceChild("attached_to_stinger", CubeListBuilder.create().texOffs(84, 20).addBox(-3.0F, -3.0F, 0.0F, 6.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-7.0F, -9.0F, 35.0F));

		PartDefinition left_side_armor = main_abdomen.addOrReplaceChild("left_side_armor", CubeListBuilder.create(), PartPose.offset(1.0F, -6.0F, -1.5F));

		PartDefinition cube_r5 = left_side_armor.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(76, 127).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5F, -0.25F, 33.25F, 0.0F, -0.7854F, 0.0F));

		PartDefinition cube_r6 = left_side_armor.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(50, 127).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 0.0F, 31.75F, 0.0F, -0.7854F, 0.0F));

		PartDefinition cube_r7 = left_side_armor.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(42, 127).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 0.0F, 29.25F, 0.0F, -0.7854F, 0.0F));

		PartDefinition cube_r8 = left_side_armor.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(16, 127).addBox(-1.0F, -3.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 0.0F, 26.5F, 0.0F, -0.7854F, 0.0F));

		PartDefinition cube_r9 = left_side_armor.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(8, 127).addBox(-1.0F, -3.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 0.0F, 23.75F, 0.0F, -0.7854F, 0.0F));

		PartDefinition cube_r10 = left_side_armor.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(0, 127).addBox(-1.0F, -3.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 0.0F, 21.0F, 0.0F, -0.7854F, 0.0F));

		PartDefinition cube_r11 = left_side_armor.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(114, 126).addBox(-1.0F, -3.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 0.0F, 18.5F, 0.0F, -0.7854F, 0.0F));

		PartDefinition cube_r12 = left_side_armor.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(106, 126).addBox(-1.0F, -3.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 0.0F, 15.75F, 0.0F, -0.7854F, 0.0F));

		PartDefinition cube_r13 = left_side_armor.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(126, 67).addBox(-1.0F, -3.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 0.0F, 13.0F, 0.0F, -0.7854F, 0.0F));

		PartDefinition cube_r14 = left_side_armor.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(126, 47).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 11.0F, 0.0F, -0.7854F, 0.0F));

		PartDefinition cube_r15 = left_side_armor.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(126, 22).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 8.25F, 0.0F, -0.7854F, 0.0F));

		PartDefinition cube_r16 = left_side_armor.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(98, 125).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 5.5F, 0.0F, -0.7854F, 0.0F));

		PartDefinition cube_r17 = left_side_armor.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(90, 125).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 2.75F, 0.0F, -0.7854F, 0.0F));

		PartDefinition cube_r18 = left_side_armor.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(78, 104).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.7854F, 0.0F));

		PartDefinition right_side_armor = main_abdomen.addOrReplaceChild("right_side_armor", CubeListBuilder.create(), PartPose.offset(-15.0F, -6.0F, -1.5F));

		PartDefinition cube_r19 = right_side_armor.addOrReplaceChild("cube_r19", CubeListBuilder.create().texOffs(132, 8).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.5F, -0.25F, 33.25F, 0.0F, 0.7854F, 0.0F));

		PartDefinition cube_r20 = right_side_armor.addOrReplaceChild("cube_r20", CubeListBuilder.create().texOffs(132, 0).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 0.0F, 31.75F, 0.0F, 0.7854F, 0.0F));

		PartDefinition cube_r21 = right_side_armor.addOrReplaceChild("cube_r21", CubeListBuilder.create().texOffs(130, 128).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 0.0F, 29.25F, 0.0F, 0.7854F, 0.0F));

		PartDefinition cube_r22 = right_side_armor.addOrReplaceChild("cube_r22", CubeListBuilder.create().texOffs(130, 111).addBox(-1.0F, -3.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 0.0F, 26.5F, 0.0F, 0.7854F, 0.0F));

		PartDefinition cube_r23 = right_side_armor.addOrReplaceChild("cube_r23", CubeListBuilder.create().texOffs(130, 91).addBox(-1.0F, -3.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 0.0F, 23.75F, 0.0F, 0.7854F, 0.0F));

		PartDefinition cube_r24 = right_side_armor.addOrReplaceChild("cube_r24", CubeListBuilder.create().texOffs(130, 83).addBox(-1.0F, -3.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 0.0F, 21.0F, 0.0F, 0.7854F, 0.0F));

		PartDefinition cube_r25 = right_side_armor.addOrReplaceChild("cube_r25", CubeListBuilder.create().texOffs(130, 75).addBox(-1.0F, -3.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 0.0F, 18.5F, 0.0F, 0.7854F, 0.0F));

		PartDefinition cube_r26 = right_side_armor.addOrReplaceChild("cube_r26", CubeListBuilder.create().texOffs(130, 38).addBox(-1.0F, -3.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 0.0F, 15.75F, 0.0F, 0.7854F, 0.0F));

		PartDefinition cube_r27 = right_side_armor.addOrReplaceChild("cube_r27", CubeListBuilder.create().texOffs(32, 130).addBox(-1.0F, -3.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 0.0F, 13.0F, 0.0F, 0.7854F, 0.0F));

		PartDefinition cube_r28 = right_side_armor.addOrReplaceChild("cube_r28", CubeListBuilder.create().texOffs(130, 30).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 11.0F, 0.0F, 0.7854F, 0.0F));

		PartDefinition cube_r29 = right_side_armor.addOrReplaceChild("cube_r29", CubeListBuilder.create().texOffs(24, 130).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 8.25F, 0.0F, 0.7854F, 0.0F));

		PartDefinition cube_r30 = right_side_armor.addOrReplaceChild("cube_r30", CubeListBuilder.create().texOffs(66, 129).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 5.5F, 0.0F, 0.7854F, 0.0F));

		PartDefinition cube_r31 = right_side_armor.addOrReplaceChild("cube_r31", CubeListBuilder.create().texOffs(58, 129).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 2.75F, 0.0F, 0.7854F, 0.0F));

		PartDefinition cube_r32 = right_side_armor.addOrReplaceChild("cube_r32", CubeListBuilder.create().texOffs(122, 128).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.7854F, 0.0F));

		PartDefinition upper_abdomen_armor = torso.addOrReplaceChild("upper_abdomen_armor", CubeListBuilder.create(), PartPose.offset(0.0F, -13.0F, -8.0F));

		PartDefinition cube_r33 = upper_abdomen_armor.addOrReplaceChild("cube_r33", CubeListBuilder.create().texOffs(60, 0).addBox(-8.0F, -1.0F, -1.0F, 16.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 5.0F, -14.0F, 0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r34 = upper_abdomen_armor.addOrReplaceChild("cube_r34", CubeListBuilder.create().texOffs(60, 16).addBox(-7.0F, -1.0F, -1.0F, 14.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 4.5F, -6.0F, 0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r35 = upper_abdomen_armor.addOrReplaceChild("cube_r35", CubeListBuilder.create().texOffs(124, 124).addBox(-2.0F, -1.0F, -1.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 22.5F, 0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r36 = upper_abdomen_armor.addOrReplaceChild("cube_r36", CubeListBuilder.create().texOffs(42, 123).addBox(-3.0F, -1.0F, -1.0F, 6.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.5F, 20.25F, 0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r37 = upper_abdomen_armor.addOrReplaceChild("cube_r37", CubeListBuilder.create().texOffs(110, 56).addBox(-4.0F, -1.0F, -1.0F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.0F, 18.0F, 0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r38 = upper_abdomen_armor.addOrReplaceChild("cube_r38", CubeListBuilder.create().texOffs(18, 88).addBox(-5.0F, -1.0F, -1.0F, 10.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.5F, 16.0F, 0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r39 = upper_abdomen_armor.addOrReplaceChild("cube_r39", CubeListBuilder.create().texOffs(82, 57).addBox(-6.0F, -1.0F, -1.0F, 12.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 2.25F, 14.0F, 0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r40 = upper_abdomen_armor.addOrReplaceChild("cube_r40", CubeListBuilder.create().texOffs(82, 53).addBox(-6.0F, -1.0F, -1.0F, 12.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 2.5F, 12.0F, 0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r41 = upper_abdomen_armor.addOrReplaceChild("cube_r41", CubeListBuilder.create().texOffs(82, 49).addBox(-6.0F, -1.0F, -1.0F, 12.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 3.25F, 10.0F, 0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r42 = upper_abdomen_armor.addOrReplaceChild("cube_r42", CubeListBuilder.create().texOffs(64, 67).addBox(-7.0F, -1.0F, -1.0F, 14.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 3.5F, 8.0F, 0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r43 = upper_abdomen_armor.addOrReplaceChild("cube_r43", CubeListBuilder.create().texOffs(32, 67).addBox(-7.0F, -1.0F, -1.0F, 14.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 3.5F, 6.0F, 0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r44 = upper_abdomen_armor.addOrReplaceChild("cube_r44", CubeListBuilder.create().texOffs(0, 67).addBox(-7.0F, -1.0F, -1.0F, 14.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 4.0F, 4.0F, 0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r45 = upper_abdomen_armor.addOrReplaceChild("cube_r45", CubeListBuilder.create().texOffs(60, 45).addBox(-7.0F, -1.0F, -1.0F, 14.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 4.0F, 2.0F, 0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r46 = upper_abdomen_armor.addOrReplaceChild("cube_r46", CubeListBuilder.create().texOffs(60, 41).addBox(-7.0F, -1.0F, -1.0F, 14.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 4.0F, 0.0F, 0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r47 = upper_abdomen_armor.addOrReplaceChild("cube_r47", CubeListBuilder.create().texOffs(60, 37).addBox(-7.0F, -1.0F, -1.0F, 14.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 4.0F, -2.0F, 0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r48 = upper_abdomen_armor.addOrReplaceChild("cube_r48", CubeListBuilder.create().texOffs(60, 12).addBox(-7.0F, -1.0F, -1.0F, 14.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 4.25F, -4.0F, 0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r49 = upper_abdomen_armor.addOrReplaceChild("cube_r49", CubeListBuilder.create().texOffs(60, 8).addBox(-8.0F, -1.0F, -1.0F, 16.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 5.0F, -8.0F, 0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r50 = upper_abdomen_armor.addOrReplaceChild("cube_r50", CubeListBuilder.create().texOffs(60, 4).addBox(-8.0F, -1.0F, -1.0F, 16.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 5.0F, -10.0F, 0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r51 = upper_abdomen_armor.addOrReplaceChild("cube_r51", CubeListBuilder.create().texOffs(50, 33).addBox(-8.0F, -1.0F, -1.0F, 16.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 5.0F, -12.0F, 0.7854F, 0.0F, 0.0F));

		PartDefinition left_arm = body.addOrReplaceChild("left_arm", CubeListBuilder.create(), PartPose.offset(8.0F, -7.0F, -9.5F));

		PartDefinition cube_r52 = left_arm.addOrReplaceChild("cube_r52", CubeListBuilder.create().texOffs(82, 61).addBox(0.0F, -2.0F, -2.0F, 8.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.0F, 0.5F, 0.0F, 0.48F, 0.0F));

		PartDefinition claw_arm = left_arm.addOrReplaceChild("claw_arm", CubeListBuilder.create(), PartPose.offset(7.0F, 0.0F, -3.0F));

		PartDefinition cube_r53 = claw_arm.addOrReplaceChild("cube_r53", CubeListBuilder.create().texOffs(0, 37).addBox(-2.0F, -3.0F, -11.0F, 4.0F, 4.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.25F, 1.5F, -0.5F, 0.0F, -0.1745F, 0.0F));

		PartDefinition claw_hand = claw_arm.addOrReplaceChild("claw_hand", CubeListBuilder.create().texOffs(26, 71).addBox(-1.0F, -2.0F, 0.5F, 5.0F, 5.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -17.0F));

		PartDefinition upper_mandible = claw_hand.addOrReplaceChild("upper_mandible", CubeListBuilder.create().texOffs(104, 61).addBox(-1.0F, -0.75F, -7.0F, 3.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(110, 119).addBox(0.0F, -1.75F, -5.0F, 1.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, -1.25F, 1.5F));

		PartDefinition upper_teeth = upper_mandible.addOrReplaceChild("upper_teeth", CubeListBuilder.create().texOffs(64, 88).addBox(0.0F, -1.0F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(76, 20).addBox(-2.0F, -1.0F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(76, 49).addBox(-2.0F, -1.0F, -3.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(18, 80).addBox(-2.0F, -1.0F, -5.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(80, 20).addBox(0.0F, -1.0F, -5.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(22, 80).addBox(0.0F, -1.0F, -3.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, 1.25F, -1.5F));

		PartDefinition lower_mandible = claw_hand.addOrReplaceChild("lower_mandible", CubeListBuilder.create().texOffs(98, 104).addBox(0.0F, -1.0F, -8.0F, 3.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(0, 120).addBox(1.0F, 0.0F, -6.0F, 1.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 3.0F, 2.5F));

		PartDefinition lower_teeth = lower_mandible.addOrReplaceChild("lower_teeth", CubeListBuilder.create().texOffs(66, 96).addBox(2.0F, 1.0F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(14, 96).addBox(0.0F, 1.0F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(66, 94).addBox(0.0F, 1.0F, -3.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(14, 94).addBox(0.0F, 1.0F, -5.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(64, 92).addBox(2.0F, 1.0F, -5.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(64, 90).addBox(2.0F, 1.0F, -3.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -3.0F, -2.5F));

		PartDefinition right_arm = body.addOrReplaceChild("right_arm", CubeListBuilder.create(), PartPose.offset(-8.0F, -6.0F, -8.5F));

		PartDefinition cube_r54 = right_arm.addOrReplaceChild("cube_r54", CubeListBuilder.create().texOffs(86, 30).addBox(-8.0F, -2.0F, -2.0F, 8.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -0.5F, 0.0F, -0.48F, 0.0F));

		PartDefinition claw_arm2 = right_arm.addOrReplaceChild("claw_arm2", CubeListBuilder.create(), PartPose.offset(-7.0F, -1.0F, -5.0F));

		PartDefinition cube_r55 = claw_arm2.addOrReplaceChild("cube_r55", CubeListBuilder.create().texOffs(30, 37).addBox(-2.0F, -3.0F, -11.0F, 4.0F, 4.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.25F, 1.5F, 0.5F, 0.0F, 0.1745F, 0.0F));

		PartDefinition claw_hand2 = claw_arm2.addOrReplaceChild("claw_hand2", CubeListBuilder.create().texOffs(48, 71).addBox(-4.0F, -2.0F, 0.5F, 5.0F, 5.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -16.0F));

		PartDefinition upper_mandible2 = claw_hand2.addOrReplaceChild("upper_mandible2", CubeListBuilder.create().texOffs(108, 24).addBox(-2.0F, 0.0F, -7.0F, 3.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(122, 60).addBox(-1.0F, -1.0F, -5.0F, 1.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, -2.0F, 1.5F));

		PartDefinition upper_teeth2 = upper_mandible2.addOrReplaceChild("upper_teeth2", CubeListBuilder.create().texOffs(108, 33).addBox(-1.0F, -1.0F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(104, 24).addBox(1.0F, -1.0F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(104, 26).addBox(1.0F, -1.0F, -3.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(104, 28).addBox(1.0F, -1.0F, -5.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(50, 106).addBox(-1.0F, -1.0F, -5.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(108, 31).addBox(-1.0F, -1.0F, -3.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, 2.0F, -1.5F));

		PartDefinition lower_mandible2 = claw_hand2.addOrReplaceChild("lower_mandible2", CubeListBuilder.create().texOffs(38, 108).addBox(-3.0F, -1.0F, -7.0F, 3.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(28, 123).addBox(-2.0F, 0.0F, -5.0F, 1.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 3.0F, 1.5F));

		PartDefinition lower_teeth2 = lower_mandible2.addOrReplaceChild("lower_teeth2", CubeListBuilder.create().texOffs(110, 76).addBox(-3.0F, 1.0F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(110, 78).addBox(-1.0F, 1.0F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(110, 80).addBox(-1.0F, 1.0F, -3.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(32, 112).addBox(-1.0F, 1.0F, -5.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(56, 112).addBox(-3.0F, 1.0F, -5.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(60, 112).addBox(-3.0F, 1.0F, -3.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -3.0F, -1.5F));

		PartDefinition left_leg_1 = body.addOrReplaceChild("left_leg_1", CubeListBuilder.create().texOffs(92, 42).addBox(8.0F, 5.0F, -1.0F, 7.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(8.0F, -8.0F, -2.0F));

		PartDefinition cube_r56 = left_leg_1.addOrReplaceChild("cube_r56", CubeListBuilder.create().texOffs(54, 94).addBox(0.0F, 0.0F, -1.5F, 3.0F, 11.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.0F, -4.0F, 0.5F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r57 = left_leg_1.addOrReplaceChild("cube_r57", CubeListBuilder.create().texOffs(42, 88).addBox(0.0F, -3.0F, -1.5F, 8.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 2.0F, 0.5F, 0.0F, 0.0F, -0.48F));

		PartDefinition right_leg_1 = body.addOrReplaceChild("right_leg_1", CubeListBuilder.create().texOffs(66, 98).addBox(-15.0F, 4.0F, -1.0F, 7.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-8.0F, -7.0F, -2.0F));

		PartDefinition cube_r58 = right_leg_1.addOrReplaceChild("cube_r58", CubeListBuilder.create().texOffs(86, 98).addBox(-3.0F, 0.0F, -1.5F, 3.0F, 11.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, -5.0F, 0.5F, 0.0F, 0.0F, 0.3491F));

		PartDefinition cube_r59 = right_leg_1.addOrReplaceChild("cube_r59", CubeListBuilder.create().texOffs(92, 12).addBox(-8.0F, -3.0F, -1.5F, 8.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.0F, 0.5F, 0.0F, 0.0F, 0.48F));

		PartDefinition left_leg_2 = body.addOrReplaceChild("left_leg_2", CubeListBuilder.create().texOffs(96, 0).addBox(8.0F, 5.0F, -1.0F, 7.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(7.0F, -8.0F, 7.0F));

		PartDefinition cube_r60 = left_leg_2.addOrReplaceChild("cube_r60", CubeListBuilder.create().texOffs(14, 98).addBox(0.0F, 0.0F, -1.5F, 3.0F, 11.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.0F, -4.0F, 0.5F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r61 = left_leg_2.addOrReplaceChild("cube_r61", CubeListBuilder.create().texOffs(88, 71).addBox(0.0F, -3.0F, -1.5F, 8.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 2.0F, 0.5F, 0.0F, 0.0F, -0.48F));

		PartDefinition right_leg_2 = body.addOrReplaceChild("right_leg_2", CubeListBuilder.create().texOffs(98, 98).addBox(-15.0F, 5.0F, -1.0F, 7.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-7.0F, -8.0F, 7.0F));

		PartDefinition cube_r62 = right_leg_2.addOrReplaceChild("cube_r62", CubeListBuilder.create().texOffs(102, 83).addBox(-3.0F, 0.0F, -1.5F, 3.0F, 11.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, -4.0F, 0.5F, 0.0F, 0.0F, 0.3491F));

		PartDefinition cube_r63 = right_leg_2.addOrReplaceChild("cube_r63", CubeListBuilder.create().texOffs(18, 92).addBox(-8.0F, -3.0F, -1.5F, 8.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 2.0F, 0.5F, 0.0F, 0.0F, 0.48F));

		PartDefinition left_leg_3 = body.addOrReplaceChild("left_leg_3", CubeListBuilder.create().texOffs(96, 6).addBox(8.0F, 5.0F, -1.0F, 7.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(7.0F, -8.0F, 16.0F));

		PartDefinition cube_r64 = left_leg_3.addOrReplaceChild("cube_r64", CubeListBuilder.create().texOffs(26, 98).addBox(0.0F, 0.0F, -1.5F, 3.0F, 11.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.0F, -4.0F, 0.5F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r65 = left_leg_3.addOrReplaceChild("cube_r65", CubeListBuilder.create().texOffs(88, 77).addBox(0.0F, -3.0F, -1.5F, 8.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 2.0F, 0.5F, 0.0F, 0.0F, -0.48F));

		PartDefinition right_leg_3 = body.addOrReplaceChild("right_leg_3", CubeListBuilder.create().texOffs(104, 18).addBox(-15.0F, 5.0F, -1.0F, 7.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-7.0F, -8.0F, 16.0F));

		PartDefinition cube_r66 = right_leg_3.addOrReplaceChild("cube_r66", CubeListBuilder.create().texOffs(66, 104).addBox(-3.0F, 0.0F, -1.5F, 3.0F, 11.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, -4.0F, 0.5F, 0.0F, 0.0F, 0.3491F));

		PartDefinition cube_r67 = right_leg_3.addOrReplaceChild("cube_r67", CubeListBuilder.create().texOffs(92, 36).addBox(-8.0F, -3.0F, -1.5F, 8.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 2.0F, 0.5F, 0.0F, 0.0F, 0.48F));

		return LayerDefinition.create(meshdefinition, 256, 256);
	}

	@Override
	public void setupAnim(ScorpionEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
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
		this.animateWalk(ScorpionModelAnimation.WALK, limbSwing, 1.0f,cycleHz,amp);
		this.animate(entity.idleState,      ScorpionModelAnimation.IDLE, ageInTicks);
		this.animate(entity.pinchState,  ScorpionModelAnimation.CLAW_ATTACK, ageInTicks);
		this.animate(entity.stingState, ScorpionModelAnimation.STINGER_STAB, ageInTicks);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		scorpion.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return scorpion;
	}
}