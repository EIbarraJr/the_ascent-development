package net.ejibarra.pathtoimmortals.entity.client;// Made with Blockbench 4.12.6
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.ejibarra.pathtoimmortals.entity.animation.BeefsteakMushroomAnimation;
import net.ejibarra.pathtoimmortals.entity.animation.OrcWarriorAnimation;
import net.ejibarra.pathtoimmortals.entity.custom.OrcWarrior;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;

public class OrcModel<T extends OrcWarrior> extends HierarchicalModel<T> {

	private final ModelPart orc;
	private final ModelPart body;
	private final ModelPart torso;
	private final ModelPart head;
	private final ModelPart hair;
	private final ModelPart right_ear;
	private final ModelPart left_ear;
	private final ModelPart head_structure;
	private final ModelPart nose;
	private final ModelPart chest_abb;
	private final ModelPart left_arm;
	private final ModelPart spiked_pauldron;
	private final ModelPart upper_left;
	private final ModelPart lower_left;
	private final ModelPart left_hand;
	private final ModelPart right_arm;
	private final ModelPart upper_right;
	private final ModelPart lower_right;
	private final ModelPart right_hand;
	private final ModelPart garment;
	private final ModelPart back_cover;
	private final ModelPart front_cover;
	private final ModelPart garment_sash;
	private final ModelPart axe;
	private final ModelPart left_leg;
	private final ModelPart left_foot;
	private final ModelPart right_leg;
	private final ModelPart right_foot;

	public OrcModel(ModelPart root) {
		this.orc = root.getChild("orc");
		this.body = this.orc.getChild("body");
		this.torso = this.body.getChild("torso");
		this.head = this.torso.getChild("head");
		this.hair = this.head.getChild("hair");
		this.right_ear = this.head.getChild("right_ear");
		this.left_ear = this.head.getChild("left_ear");
		this.head_structure = this.head.getChild("head_structure");
		this.nose = this.head.getChild("nose");
		this.chest_abb = this.torso.getChild("chest_abb");
		this.left_arm = this.torso.getChild("left_arm");
		this.spiked_pauldron = this.left_arm.getChild("spiked_pauldron");
		this.upper_left = this.left_arm.getChild("upper_left");
		this.lower_left = this.left_arm.getChild("lower_left");
		this.left_hand = this.lower_left.getChild("left_hand");
		this.right_arm = this.torso.getChild("right_arm");
		this.upper_right = this.right_arm.getChild("upper_right");
		this.lower_right = this.right_arm.getChild("lower_right");
		this.right_hand = this.lower_right.getChild("right_hand");
		this.garment = this.torso.getChild("garment");
		this.back_cover = this.garment.getChild("back_cover");
		this.front_cover = this.garment.getChild("front_cover");
		this.garment_sash = this.garment.getChild("garment_sash");
		this.axe = this.torso.getChild("axe");
		this.left_leg = this.body.getChild("left_leg");
		this.left_foot = this.left_leg.getChild("left_foot");
		this.right_leg = this.body.getChild("right_leg");
		this.right_foot = this.right_leg.getChild("right_foot");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition orc = partdefinition.addOrReplaceChild("orc", CubeListBuilder.create(), PartPose.offset(-2.0F, 22.0F, 11.0F));

		PartDefinition body = orc.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition torso = body.addOrReplaceChild("torso", CubeListBuilder.create().texOffs(88, 113).addBox(-3.5F, -21.0F, -3.5F, 7.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, -24.0F, -8.0F));

		PartDefinition head = torso.addOrReplaceChild("head", CubeListBuilder.create().texOffs(146, 26).addBox(-3.75F, -21.0F, -12.15F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(146, 29).addBox(1.75F, -21.0F, -12.15F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -7.0F, 8.0F));

		PartDefinition hair = head.addOrReplaceChild("hair", CubeListBuilder.create().texOffs(38, 55).addBox(-2.0F, -4.0F, 4.0F, 4.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(38, 124).addBox(-2.0F, -4.75F, 3.75F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(38, 67).addBox(-2.0F, -5.5F, 3.25F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(138, 86).addBox(-2.0F, -6.0F, -1.5F, 4.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(92, 38).addBox(-1.5F, -5.75F, -2.5F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(46, 140).addBox(-1.5F, -5.75F, 4.5F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(146, 0).addBox(-1.5F, -6.25F, 6.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(22, 108).addBox(-1.0F, -3.25F, 7.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(64, 147).addBox(-0.5F, -1.25F, 7.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -19.75F, -7.5F));

		PartDefinition right_ear = head.addOrReplaceChild("right_ear", CubeListBuilder.create().texOffs(92, 34).addBox(-1.0F, 0.0F, -1.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(140, 144).addBox(-1.0F, -1.0F, -1.0F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, -21.0F, -9.0F, 0.4363F, -0.2618F, 0.0F));

		PartDefinition left_ear = head.addOrReplaceChild("left_ear", CubeListBuilder.create().texOffs(30, 99).addBox(0.0F, 0.0F, -1.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(0, 146).addBox(0.0F, -1.0F, -1.0F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.0F, -21.0F, -9.0F, 0.4363F, 0.2618F, 0.0F));

		PartDefinition head_structure = head.addOrReplaceChild("head_structure", CubeListBuilder.create().texOffs(74, 84).addBox(-4.0F, -4.0F, -1.0F, 8.0F, 2.0F, 10.0F, new CubeDeformation(0.0F))
		.texOffs(112, 121).addBox(2.75F, -6.0F, -1.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(60, 147).addBox(-3.5F, -6.0F, -1.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(74, 69).addBox(-5.0F, -11.5F, 1.0F, 10.0F, 7.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(38, 69).addBox(-4.5F, -13.0F, 0.5F, 9.0F, 9.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -12.0F, -12.5F));

		PartDefinition nose = head.addOrReplaceChild("nose", CubeListBuilder.create().texOffs(100, 48).addBox(3.0F, -2.0F, -1.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(106, 144).addBox(3.5F, -4.75F, -1.25F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.0F, -17.0F, -11.25F));

		PartDefinition chest_abb = torso.addOrReplaceChild("chest_abb", CubeListBuilder.create().texOffs(0, 74).addBox(-7.5F, -12.0F, -9.0F, 13.0F, 8.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(22, 114).addBox(-6.5F, -12.0F, -10.5F, 11.0F, 8.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(56, 40).addBox(-7.5F, -4.0F, -11.25F, 13.0F, 1.0F, 9.0F, new CubeDeformation(0.0F))
		.texOffs(0, 42).addBox(-8.0F, -3.0F, -12.0F, 14.0F, 3.0F, 10.0F, new CubeDeformation(0.0F))
		.texOffs(0, 88).addBox(-9.0F, -14.0F, -11.0F, 8.0F, 1.0F, 9.0F, new CubeDeformation(0.0F))
		.texOffs(92, 0).addBox(-1.0F, -14.0F, -11.0F, 8.0F, 1.0F, 9.0F, new CubeDeformation(0.0F))
		.texOffs(30, 105).addBox(-1.0F, -13.0F, -10.75F, 7.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(98, 104).addBox(-8.0F, -13.0F, -10.75F, 7.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(48, 50).addBox(-1.0F, -23.0F, -11.75F, 9.0F, 9.0F, 10.0F, new CubeDeformation(0.0F))
		.texOffs(0, 55).addBox(-10.0F, -23.0F, -11.75F, 9.0F, 9.0F, 10.0F, new CubeDeformation(0.0F))
		.texOffs(130, 135).addBox(-0.95F, -22.45F, -12.1F, 8.4F, 8.4F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(92, 135).addBox(-9.45F, -22.45F, -12.1F, 8.4F, 8.4F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, 3.0F, 7.75F));

		PartDefinition left_arm = torso.addOrReplaceChild("left_arm", CubeListBuilder.create(), PartPose.offset(10.0F, -12.0F, -1.0F));

		PartDefinition spiked_pauldron = left_arm.addOrReplaceChild("spiked_pauldron", CubeListBuilder.create().texOffs(0, 0).addBox(-14.25F, 4.75F, -0.75F, 9.0F, 9.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(12.25F, -13.75F, -3.25F));

		PartDefinition cube_r1 = spiked_pauldron.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(72, 116).addBox(-9.0F, -1.0F, -1.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(138, 92).addBox(-9.0F, -1.0F, 4.25F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(100, 44).addBox(-9.0F, -1.0F, -6.25F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 5.25F, 0.0F, 0.0F, -0.6981F));

		PartDefinition upper_left = left_arm.addOrReplaceChild("upper_left", CubeListBuilder.create().texOffs(114, 59).addBox(0.5F, -2.0F, -1.0F, 6.0F, 4.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(48, 116).addBox(0.0F, -8.0F, -0.5F, 7.0F, 6.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(56, 0).addBox(0.0F, -16.5F, -5.25F, 7.0F, 9.0F, 11.0F, new CubeDeformation(0.0F))
		.texOffs(126, 0).addBox(0.5F, -8.0F, -4.0F, 6.0F, 6.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(130, 96).addBox(1.0F, -2.1F, -3.75F, 5.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.5F, 8.0F, 1.75F));

		PartDefinition lower_left = left_arm.addOrReplaceChild("lower_left", CubeListBuilder.create().texOffs(112, 124).addBox(-1.5F, -2.0F, -1.0F, 5.0F, 9.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(138, 66).addBox(-1.0F, -0.1F, -2.75F, 4.0F, 7.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(140, 111).addBox(-0.5F, 6.9F, -2.25F, 3.0F, 7.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(136, 55).addBox(-1.0F, 7.0F, -1.5F, 4.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, 10.0F, 1.75F, -0.5672F, 0.0F, 0.0F));

		PartDefinition left_hand = lower_left.addOrReplaceChild("left_hand", CubeListBuilder.create().texOffs(92, 144).addBox(3.5F, -2.0F, -1.0F, 4.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.5F, 16.0F, -0.25F));

		PartDefinition right_arm = torso.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(0, 21).addBox(-8.0F, -4.0F, -6.0F, 9.0F, 9.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(-9.0F, -17.0F, 1.0F));

		PartDefinition upper_right = right_arm.addOrReplaceChild("upper_right", CubeListBuilder.create().texOffs(124, 25).addBox(-6.5F, -2.0F, -1.0F, 6.0F, 4.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(116, 113).addBox(-7.0F, -8.0F, -0.5F, 7.0F, 6.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(56, 20).addBox(-7.0F, -16.5F, -5.25F, 7.0F, 9.0F, 11.0F, new CubeDeformation(0.0F))
		.texOffs(130, 34).addBox(-6.5F, -8.0F, -4.0F, 6.0F, 6.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(0, 138).addBox(-6.0F, -2.1F, -3.75F, 5.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.5F, 13.0F, -0.25F));

		PartDefinition lower_right = right_arm.addOrReplaceChild("lower_right", CubeListBuilder.create().texOffs(38, 127).addBox(-3.5F, -2.0F, -1.0F, 5.0F, 9.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(138, 76).addBox(-3.0F, -0.1F, -2.75F, 4.0F, 7.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(142, 10).addBox(-2.5F, 6.9F, -2.25F, 3.0F, 7.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(60, 136).addBox(-3.0F, 7.0F, -1.5F, 4.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 15.0F, -0.25F, -0.5672F, 0.0F, 0.0F));

		PartDefinition right_hand = lower_right.addOrReplaceChild("right_hand", CubeListBuilder.create().texOffs(126, 144).addBox(-7.5F, -2.0F, -1.0F, 4.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(4.5F, 16.0F, -0.25F));

		PartDefinition garment = torso.addOrReplaceChild("garment", CubeListBuilder.create().texOffs(92, 121).addBox(8.6F, -1.0F, 0.0F, 1.0F, 5.0F, 9.0F, new CubeDeformation(0.0F))
		.texOffs(130, 124).addBox(8.5F, 4.0F, 0.5F, 1.0F, 3.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(18, 139).addBox(8.5F, 7.0F, 1.5F, 1.0F, 3.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(32, 140).addBox(-5.5F, 7.0F, 1.5F, 1.0F, 3.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(134, 44).addBox(-5.5F, 4.0F, 0.5F, 1.0F, 3.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(0, 124).addBox(-5.6F, -1.0F, 0.0F, 1.0F, 5.0F, 9.0F, new CubeDeformation(0.0F))
		.texOffs(66, 96).addBox(-5.5F, -0.9F, 8.9F, 15.0F, 9.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, 2.0F, -4.0F));

		PartDefinition cube_r2 = garment.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(98, 96).addBox(-7.0F, 2.0F, -1.0F, 15.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.5F, -2.9F, 0.65F, -0.0873F, 0.0F, 0.0F));

		PartDefinition back_cover = garment.addOrReplaceChild("back_cover", CubeListBuilder.create().texOffs(0, 108).addBox(-3.0F, -0.25F, 0.5F, 10.0F, 15.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -0.55F, 9.15F));

		PartDefinition front_cover = garment.addOrReplaceChild("front_cover", CubeListBuilder.create().texOffs(142, 20).addBox(-2.0F, 8.0F, -1.0F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(140, 121).addBox(-3.0F, 6.0F, -1.0F, 6.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(128, 104).addBox(-5.0F, 0.0F, -1.0F, 10.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, -0.9F, 0.4F, -0.1309F, 0.0F, 0.0F));

		PartDefinition garment_sash = garment.addOrReplaceChild("garment_sash", CubeListBuilder.create().texOffs(146, 6).addBox(-16.0F, -19.0F, -1.8F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(146, 23).addBox(-1.0F, -1.0F, -0.8F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(12, 146).addBox(3.0F, -19.0F, 10.2F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(30, 103).addBox(-13.0F, 0.0F, 9.7F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(8.0F, -1.0F, -0.2F));

		PartDefinition cube_r3 = garment_sash.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(42, 0).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 27.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, -18.0F, 12.3F, -0.044F, 0.0057F, 0.6983F));

		PartDefinition cube_r4 = garment_sash.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(56, 127).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 25.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-15.0F, -18.0F, 0.0F, 0.0433F, -0.0057F, -0.6983F));

		PartDefinition axe = torso.addOrReplaceChild("axe", CubeListBuilder.create().texOffs(42, 27).addBox(8.9818F, 34.5407F, 0.0F, 1.0F, 15.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(44, 27).addBox(9.9818F, 35.2907F, 0.0F, 1.0F, 13.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(50, 144).addBox(10.9818F, 37.2907F, 0.0F, 1.0F, 9.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(86, 116).addBox(11.9818F, 39.2907F, 0.0F, 1.0F, 5.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(36, 88).addBox(4.9818F, 37.7907F, 0.0F, 1.0F, 10.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(34, 88).addBox(5.9818F, 37.2907F, 0.0F, 1.0F, 11.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(46, 0).addBox(6.9818F, 36.7907F, 0.0F, 1.0F, 12.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(46, 23).addBox(7.9818F, 36.7907F, 0.0F, 1.0F, 11.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(44, 40).addBox(7.9818F, 34.7907F, 0.0F, 1.0F, 2.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(134, 55).addBox(7.9818F, 47.7907F, 0.0F, 1.0F, 3.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(86, 68).addBox(6.9818F, 33.7907F, 0.0F, 2.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(94, 68).addBox(-5.0182F, 35.2907F, 0.0F, 2.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(90, 68).addBox(6.4818F, 50.2907F, 0.0F, 2.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(46, 34).addBox(3.9818F, 39.0407F, 0.0F, 1.0F, 8.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(126, 137).addBox(2.9818F, 39.7907F, 0.0F, 1.0F, 7.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(60, 127).addBox(-3.0182F, 39.2907F, 0.0F, 6.0F, 7.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(48, 144).addBox(-4.0182F, 38.2907F, 0.0F, 1.0F, 9.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(46, 144).addBox(-6.0182F, 36.2907F, 0.0F, 1.0F, 10.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(52, 144).addBox(-7.0182F, 37.2907F, 0.0F, 1.0F, 8.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(128, 137).addBox(-8.0182F, 39.2907F, 0.0F, 1.0F, 5.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(46, 12).addBox(-5.0182F, 36.2907F, 0.0F, 1.0F, 11.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(48, 0).addBox(-1.0182F, -0.7093F, -1.0F, 2.0F, 48.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(66, 87).addBox(0.9818F, 38.7907F, -0.5F, 1.0F, 8.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(70, 87).addBox(-2.0182F, 38.7907F, -0.5F, 1.0F, 8.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-9.5F, -28.75F, 7.75F, 0.0F, 0.0F, -0.4363F));

		PartDefinition left_leg = body.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(38, 62).addBox(1.0F, 11.5F, -9.0F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(114, 44).addBox(0.0F, 13.5F, -6.5F, 5.0F, 10.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(76, 136).addBox(0.5F, 13.5F, -3.5F, 4.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.5F, -25.5F, -5.5F, 0.0F, -0.0873F, 0.0F));

		PartDefinition cube_r5 = left_leg.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(86, 50).addBox(-4.0F, -0.0782F, -4.6068F, 7.0F, 11.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, 4.0F, 0.5F, -0.2182F, 0.0F, 0.0F));

		PartDefinition cube_r6 = left_leg.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(124, 10).addBox(-2.0F, -0.0782F, -4.6068F, 6.0F, 12.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.5F, 4.0F, -1.5F, -0.2182F, 0.0F, 0.0F));

		PartDefinition left_foot = left_leg.addOrReplaceChild("left_foot", CubeListBuilder.create().texOffs(0, 98).addBox(-3.0F, 3.0F, -3.0F, 7.0F, 2.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(110, 78).addBox(-3.0F, 3.0F, -10.0F, 7.0F, 2.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(92, 10).addBox(-2.0F, 2.0F, -7.0F, 5.0F, 1.0F, 11.0F, new CubeDeformation(0.0F))
		.texOffs(60, 106).addBox(-2.0F, 1.0F, -5.0F, 5.0F, 1.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, 22.5F, -3.5F));

		PartDefinition right_leg = body.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(88, 106).addBox(-4.0F, 11.5F, -9.0F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(72, 121).addBox(-5.0F, 13.5F, -6.5F, 5.0F, 10.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(110, 137).addBox(-4.5F, 13.5F, -3.5F, 4.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.5F, -25.5F, -5.5F, 0.0F, 0.0873F, 0.0F));

		PartDefinition cube_r7 = right_leg.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(38, 87).addBox(-3.0F, -0.0782F, -4.6068F, 7.0F, 11.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 4.0F, 0.5F, -0.2182F, 0.0F, 0.0F));

		PartDefinition cube_r8 = right_leg.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(20, 124).addBox(-4.0F, -0.0782F, -4.6068F, 6.0F, 12.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.5F, 4.0F, -1.5F, -0.2182F, 0.0F, 0.0F));

		PartDefinition right_foot = right_leg.addOrReplaceChild("right_foot", CubeListBuilder.create().texOffs(100, 34).addBox(-4.0F, 3.0F, -3.0F, 7.0F, 2.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(110, 87).addBox(-4.0F, 3.0F, -10.0F, 7.0F, 2.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(92, 22).addBox(-3.0F, 2.0F, -7.0F, 5.0F, 1.0F, 11.0F, new CubeDeformation(0.0F))
		.texOffs(110, 68).addBox(-3.0F, 1.0F, -5.0F, 5.0F, 1.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, 22.5F, -3.5F));

		return LayerDefinition.create(meshdefinition, 256, 256);
	}

	@Override
	public void setupAnim(OrcWarrior entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
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
		this.animateWalk(OrcWarriorAnimation.Walk, limbSwing, 1.0f, cycleHz, amp);
		this.animate(entity.idleState, OrcWarriorAnimation.Idle, ageInTicks, 1.0f);
		this.animate(entity.chargeState, OrcWarriorAnimation.Charge, ageInTicks, 1.0f);
		this.animate(entity.sliceState, OrcWarriorAnimation.Swing, ageInTicks, 1.0f);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		orc.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return orc;
	}
}