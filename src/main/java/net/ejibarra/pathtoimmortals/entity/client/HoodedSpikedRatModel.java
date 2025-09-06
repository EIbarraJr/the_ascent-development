package net.ejibarra.pathtoimmortals.entity.client;// Made with Blockbench 4.12.6
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.ejibarra.pathtoimmortals.entity.animation.HoodedSpikedRatAnimations;
import net.ejibarra.pathtoimmortals.entity.custom.HoodedSpikedRatEntity;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class HoodedSpikedRatModel<T extends HoodedSpikedRatEntity> extends HierarchicalModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor

	private final ModelPart hooded_spiked_rat;
	private final ModelPart body;
	private final ModelPart torso;
	private final ModelPart head;
	private final ModelPart hood;
	private final ModelPart neck;
	private final ModelPart abdomen;
	private final ModelPart satchel_armor;
	private final ModelPart spiked_shell;
	private final ModelPart shell;
	private final ModelPart spike;
	private final ModelPart spike10;
	private final ModelPart spike15;
	private final ModelPart spike20;
	private final ModelPart spike25;
	private final ModelPart spike30;
	private final ModelPart spike2;
	private final ModelPart spike9;
	private final ModelPart spike14;
	private final ModelPart spike19;
	private final ModelPart spike24;
	private final ModelPart spike29;
	private final ModelPart spike3;
	private final ModelPart spike8;
	private final ModelPart spike13;
	private final ModelPart spike18;
	private final ModelPart spike23;
	private final ModelPart spike28;
	private final ModelPart spike4;
	private final ModelPart spike6;
	private final ModelPart spike11;
	private final ModelPart spike16;
	private final ModelPart spike21;
	private final ModelPart spike26;
	private final ModelPart spike5;
	private final ModelPart spike7;
	private final ModelPart spike12;
	private final ModelPart spike17;
	private final ModelPart spike22;
	private final ModelPart spike27;
	private final ModelPart robe;
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

	public HoodedSpikedRatModel(ModelPart root) {
		this.hooded_spiked_rat = root.getChild("hooded_spiked_rat");
		this.body = this.hooded_spiked_rat.getChild("body");
		this.torso = this.body.getChild("torso");
		this.head = this.torso.getChild("head");
		this.hood = this.head.getChild("hood");
		this.neck = this.torso.getChild("neck");
		this.abdomen = this.torso.getChild("abdomen");
		this.satchel_armor = this.abdomen.getChild("satchel_armor");
		this.spiked_shell = this.abdomen.getChild("spiked_shell");
		this.shell = this.spiked_shell.getChild("shell");
		this.spike = this.spiked_shell.getChild("spike");
		this.spike10 = this.spiked_shell.getChild("spike10");
		this.spike15 = this.spiked_shell.getChild("spike15");
		this.spike20 = this.spiked_shell.getChild("spike20");
		this.spike25 = this.spiked_shell.getChild("spike25");
		this.spike30 = this.spiked_shell.getChild("spike30");
		this.spike2 = this.spiked_shell.getChild("spike2");
		this.spike9 = this.spiked_shell.getChild("spike9");
		this.spike14 = this.spiked_shell.getChild("spike14");
		this.spike19 = this.spiked_shell.getChild("spike19");
		this.spike24 = this.spiked_shell.getChild("spike24");
		this.spike29 = this.spiked_shell.getChild("spike29");
		this.spike3 = this.spiked_shell.getChild("spike3");
		this.spike8 = this.spiked_shell.getChild("spike8");
		this.spike13 = this.spiked_shell.getChild("spike13");
		this.spike18 = this.spiked_shell.getChild("spike18");
		this.spike23 = this.spiked_shell.getChild("spike23");
		this.spike28 = this.spiked_shell.getChild("spike28");
		this.spike4 = this.spiked_shell.getChild("spike4");
		this.spike6 = this.spiked_shell.getChild("spike6");
		this.spike11 = this.spiked_shell.getChild("spike11");
		this.spike16 = this.spiked_shell.getChild("spike16");
		this.spike21 = this.spiked_shell.getChild("spike21");
		this.spike26 = this.spiked_shell.getChild("spike26");
		this.spike5 = this.spiked_shell.getChild("spike5");
		this.spike7 = this.spiked_shell.getChild("spike7");
		this.spike12 = this.spiked_shell.getChild("spike12");
		this.spike17 = this.spiked_shell.getChild("spike17");
		this.spike22 = this.spiked_shell.getChild("spike22");
		this.spike27 = this.spiked_shell.getChild("spike27");
		this.robe = this.abdomen.getChild("robe");
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

		PartDefinition hooded_spiked_rat = partdefinition.addOrReplaceChild("hooded_spiked_rat", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 2.0F));

		PartDefinition body = hooded_spiked_rat.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(-3.0F, 0.0F, -4.5F));

		PartDefinition torso = body.addOrReplaceChild("torso", CubeListBuilder.create(), PartPose.offset(3.0F, -11.0F, 6.75F));

		PartDefinition head = torso.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 57).addBox(-2.5F, -4.0F, 0.0F, 6.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(68, 0).addBox(-2.0F, -3.5F, -2.0F, 5.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(76, 57).addBox(-1.75F, -3.25F, -2.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(80, 64).addBox(1.75F, -3.25F, -2.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(68, 5).addBox(-1.5F, -3.0F, -5.0F, 4.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(62, 59).addBox(-0.5F, -2.5F, -7.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(70, 59).addBox(0.0F, -2.5F, -5.0F, 0.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(32, 87).addBox(1.0F, -2.5F, -5.0F, 0.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.5F, -9.0F, -7.25F));

		PartDefinition cube_r1 = head.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(72, 22).addBox(-2.0F, -2.0F, -2.0F, 3.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 1.25F, -2.5F, 0.2618F, 0.0F, 0.0F));

		PartDefinition hood = head.addOrReplaceChild("hood", CubeListBuilder.create().texOffs(42, 62).addBox(-3.0F, -5.0F, 0.0F, 7.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 63).addBox(-3.0F, -5.0F, -4.0F, 0.0F, 5.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(64, 25).addBox(4.0F, -5.0F, -4.0F, 0.0F, 5.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(50, 44).addBox(-3.0F, -5.0F, -4.0F, 7.0F, 0.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(50, 48).addBox(-3.0F, 0.0F, -4.0F, 7.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.75F, 2.0F));

		PartDefinition neck = torso.addOrReplaceChild("neck", CubeListBuilder.create().texOffs(50, 36).addBox(-2.0F, -3.5F, -1.0F, 5.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(74, 25).addBox(-1.0F, -2.0F, -1.0F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.5F, -9.0F, -5.25F));

		PartDefinition abdomen = torso.addOrReplaceChild("abdomen", CubeListBuilder.create(), PartPose.offset(0.5F, 2.0F, -2.25F));

		PartDefinition cube_r2 = abdomen.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(46, 21).addBox(-5.0F, 1.0F, -1.0F, 10.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.25F, -14.5F, -1.5F, 0.1309F, 0.0F, 0.0F));

		PartDefinition cube_r3 = abdomen.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(0, 46).addBox(-5.0F, -5.0F, -1.0F, 11.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -8.0F, -1.0F, 0.1309F, 0.0F, 0.0F));

		PartDefinition cube_r4 = abdomen.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(30, 46).addBox(-3.0F, -4.0F, 0.0F, 7.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -4.0F, -1.0F, 0.1309F, 0.0F, 0.0F));

		PartDefinition cube_r5 = abdomen.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(46, 13).addBox(-5.0F, -4.0F, -1.0F, 9.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.1309F, 0.0F, 0.0F));

		PartDefinition satchel_armor = abdomen.addOrReplaceChild("satchel_armor", CubeListBuilder.create().texOffs(28, 75).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, 0.0F, -1.0F));

		PartDefinition cube_r6 = satchel_armor.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(76, 56).addBox(-5.0F, -1.0F, -1.0F, 6.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, -2.0F, 0.25F, -0.2618F, 0.0F, 0.0F));

		PartDefinition cube_r7 = satchel_armor.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(76, 68).addBox(-2.0F, -4.0F, -1.0F, 3.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.0F, 1.0F, -0.5F, -0.2618F, 0.0F, 0.0F));

		PartDefinition cube_r8 = satchel_armor.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(40, 75).addBox(0.0F, -8.0F, -1.0F, 1.0F, 8.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -1.0F, 0.5F, 0.0873F, 0.0F, -0.6109F));

		PartDefinition cube_r9 = satchel_armor.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(42, 68).addBox(-1.0F, -3.0F, -1.0F, 5.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-8.5F, -9.0F, -0.5F, 0.1309F, 0.0F, 0.0F));

		PartDefinition spiked_shell = abdomen.addOrReplaceChild("spiked_shell", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition shell = spiked_shell.addOrReplaceChild("shell", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r10 = shell.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(46, 0).addBox(-5.0F, -13.0F, 0.0F, 10.0F, 12.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 0.0F, 2.5F, 0.1309F, 0.0F, 0.0F));

		PartDefinition cube_r11 = shell.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(46, 25).addBox(-4.0F, -10.0F, 1.0F, 8.0F, 10.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, -2.0F, 1.75F, 0.1309F, 0.0F, 0.0F));

		PartDefinition cube_r12 = shell.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(10, 65).addBox(-2.0F, -9.0F, 1.0F, 4.0F, 8.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, -2.0F, 2.25F, 0.1309F, 0.0F, 0.0F));

		PartDefinition spike = spiked_shell.addOrReplaceChild("spike", CubeListBuilder.create(), PartPose.offsetAndRotation(3.5F, -1.0F, 5.0F, -0.48F, 0.0F, 0.0F));

		PartDefinition cube_r13 = spike.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(28, 81).addBox(0.0F, -1.0F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 0.5F, -2.0F, 0.2618F, 0.0F, 0.0F));

		PartDefinition cube_r14 = spike.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(46, 36).addBox(0.0F, -1.0F, -1.0F, 0.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.2618F, 0.0F, 0.0F));

		PartDefinition spike10 = spiked_shell.addOrReplaceChild("spike10", CubeListBuilder.create(), PartPose.offsetAndRotation(3.5F, -12.0F, 4.0F, 0.3491F, 0.0F, 0.0F));

		PartDefinition cube_r15 = spike10.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(82, 32).addBox(0.0F, -1.0F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 0.5F, -2.0F, 0.2618F, 0.0F, 0.0F));

		PartDefinition cube_r16 = spike10.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(86, 81).addBox(0.0F, -1.0F, -1.0F, 0.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.2618F, 0.0F, 0.0F));

		PartDefinition spike15 = spiked_shell.addOrReplaceChild("spike15", CubeListBuilder.create(), PartPose.offsetAndRotation(3.5F, -10.0F, 5.0F, 0.0873F, 0.0F, 0.0F));

		PartDefinition cube_r17 = spike15.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(0, 84).addBox(0.0F, -1.0F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 0.5F, -2.0F, 0.2618F, 0.0F, 0.0F));

		PartDefinition cube_r18 = spike15.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(16, 87).addBox(0.0F, -1.0F, -1.0F, 0.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.2618F, 0.0F, 0.0F));

		PartDefinition spike20 = spiked_shell.addOrReplaceChild("spike20", CubeListBuilder.create(), PartPose.offsetAndRotation(3.5F, -8.0F, 6.0F, -0.1309F, 0.0F, 0.0F));

		PartDefinition cube_r19 = spike20.addOrReplaceChild("cube_r19", CubeListBuilder.create().texOffs(84, 22).addBox(0.0F, -1.0F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 0.5F, -2.0F, 0.2618F, 0.0F, 0.0F));

		PartDefinition cube_r20 = spike20.addOrReplaceChild("cube_r20", CubeListBuilder.create().texOffs(56, 87).addBox(0.0F, -1.0F, -1.0F, 0.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.2618F, 0.0F, 0.0F));

		PartDefinition spike25 = spiked_shell.addOrReplaceChild("spike25", CubeListBuilder.create(), PartPose.offsetAndRotation(3.5F, -6.0F, 6.0F, -0.1309F, 0.0F, 0.0F));

		PartDefinition cube_r21 = spike25.addOrReplaceChild("cube_r21", CubeListBuilder.create().texOffs(60, 84).addBox(0.0F, -1.0F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 0.5F, -2.0F, 0.2618F, 0.0F, 0.0F));

		PartDefinition cube_r22 = spike25.addOrReplaceChild("cube_r22", CubeListBuilder.create().texOffs(76, 87).addBox(0.0F, -1.0F, -1.0F, 0.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.2618F, 0.0F, 0.0F));

		PartDefinition spike30 = spiked_shell.addOrReplaceChild("spike30", CubeListBuilder.create(), PartPose.offsetAndRotation(3.5F, -3.5F, 6.0F, -0.2618F, 0.0F, 0.0F));

		PartDefinition cube_r23 = spike30.addOrReplaceChild("cube_r23", CubeListBuilder.create().texOffs(72, 84).addBox(0.0F, -1.0F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 0.5F, -2.0F, 0.2618F, 0.0F, 0.0F));

		PartDefinition cube_r24 = spike30.addOrReplaceChild("cube_r24", CubeListBuilder.create().texOffs(88, 6).addBox(0.0F, -1.0F, -1.0F, 0.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.2618F, 0.0F, 0.0F));

		PartDefinition spike2 = spiked_shell.addOrReplaceChild("spike2", CubeListBuilder.create(), PartPose.offsetAndRotation(1.5F, -1.0F, 5.0F, -0.48F, 0.0F, 0.0F));

		PartDefinition cube_r25 = spike2.addOrReplaceChild("cube_r25", CubeListBuilder.create().texOffs(62, 81).addBox(0.0F, -1.0F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 0.5F, -2.0F, 0.2618F, 0.0F, 0.0F));

		PartDefinition cube_r26 = spike2.addOrReplaceChild("cube_r26", CubeListBuilder.create().texOffs(46, 39).addBox(0.0F, -1.0F, -1.0F, 0.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.2618F, 0.0F, 0.0F));

		PartDefinition spike9 = spiked_shell.addOrReplaceChild("spike9", CubeListBuilder.create(), PartPose.offsetAndRotation(1.5F, -12.0F, 4.0F, 0.3491F, 0.0F, 0.0F));

		PartDefinition cube_r27 = spike9.addOrReplaceChild("cube_r27", CubeListBuilder.create().texOffs(82, 29).addBox(0.0F, -1.0F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 0.5F, -2.0F, 0.2618F, 0.0F, 0.0F));

		PartDefinition cube_r28 = spike9.addOrReplaceChild("cube_r28", CubeListBuilder.create().texOffs(86, 61).addBox(0.0F, -1.0F, -1.0F, 0.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.2618F, 0.0F, 0.0F));

		PartDefinition spike14 = spiked_shell.addOrReplaceChild("spike14", CubeListBuilder.create(), PartPose.offsetAndRotation(1.5F, -10.0F, 5.0F, 0.0873F, 0.0F, 0.0F));

		PartDefinition cube_r29 = spike14.addOrReplaceChild("cube_r29", CubeListBuilder.create().texOffs(48, 82).addBox(0.0F, -1.0F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 0.5F, -2.0F, 0.2618F, 0.0F, 0.0F));

		PartDefinition cube_r30 = spike14.addOrReplaceChild("cube_r30", CubeListBuilder.create().texOffs(12, 87).addBox(0.0F, -1.0F, -1.0F, 0.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.2618F, 0.0F, 0.0F));

		PartDefinition spike19 = spiked_shell.addOrReplaceChild("spike19", CubeListBuilder.create(), PartPose.offsetAndRotation(1.5F, -8.0F, 6.0F, -0.1309F, 0.0F, 0.0F));

		PartDefinition cube_r31 = spike19.addOrReplaceChild("cube_r31", CubeListBuilder.create().texOffs(18, 84).addBox(0.0F, -1.0F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 0.5F, -2.0F, 0.2618F, 0.0F, 0.0F));

		PartDefinition cube_r32 = spike19.addOrReplaceChild("cube_r32", CubeListBuilder.create().texOffs(52, 87).addBox(0.0F, -1.0F, -1.0F, 0.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.2618F, 0.0F, 0.0F));

		PartDefinition spike24 = spiked_shell.addOrReplaceChild("spike24", CubeListBuilder.create(), PartPose.offsetAndRotation(1.5F, -6.0F, 6.0F, -0.1309F, 0.0F, 0.0F));

		PartDefinition cube_r33 = spike24.addOrReplaceChild("cube_r33", CubeListBuilder.create().texOffs(54, 84).addBox(0.0F, -1.0F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 0.5F, -2.0F, 0.2618F, 0.0F, 0.0F));

		PartDefinition cube_r34 = spike24.addOrReplaceChild("cube_r34", CubeListBuilder.create().texOffs(72, 87).addBox(0.0F, -1.0F, -1.0F, 0.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.2618F, 0.0F, 0.0F));

		PartDefinition spike29 = spiked_shell.addOrReplaceChild("spike29", CubeListBuilder.create(), PartPose.offsetAndRotation(1.5F, -3.5F, 6.0F, -0.2618F, 0.0F, 0.0F));

		PartDefinition cube_r35 = spike29.addOrReplaceChild("cube_r35", CubeListBuilder.create().texOffs(84, 70).addBox(0.0F, -1.0F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 0.5F, -2.0F, 0.2618F, 0.0F, 0.0F));

		PartDefinition cube_r36 = spike29.addOrReplaceChild("cube_r36", CubeListBuilder.create().texOffs(88, 3).addBox(0.0F, -1.0F, -1.0F, 0.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.2618F, 0.0F, 0.0F));

		PartDefinition spike3 = spiked_shell.addOrReplaceChild("spike3", CubeListBuilder.create(), PartPose.offsetAndRotation(-0.5F, -1.0F, 5.0F, -0.48F, 0.0F, 0.0F));

		PartDefinition cube_r37 = spike3.addOrReplaceChild("cube_r37", CubeListBuilder.create().texOffs(68, 81).addBox(0.0F, -1.0F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 0.5F, -2.0F, 0.2618F, 0.0F, 0.0F));

		PartDefinition cube_r38 = spike3.addOrReplaceChild("cube_r38", CubeListBuilder.create().texOffs(68, 10).addBox(0.0F, -1.0F, -1.0F, 0.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.2618F, 0.0F, 0.0F));

		PartDefinition spike8 = spiked_shell.addOrReplaceChild("spike8", CubeListBuilder.create(), PartPose.offsetAndRotation(-0.5F, -12.0F, 4.0F, 0.3491F, 0.0F, 0.0F));

		PartDefinition cube_r39 = spike8.addOrReplaceChild("cube_r39", CubeListBuilder.create().texOffs(82, 6).addBox(0.0F, -1.0F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 0.5F, -2.0F, 0.2618F, 0.0F, 0.0F));

		PartDefinition cube_r40 = spike8.addOrReplaceChild("cube_r40", CubeListBuilder.create().texOffs(86, 52).addBox(0.0F, -1.0F, -1.0F, 0.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.2618F, 0.0F, 0.0F));

		PartDefinition spike13 = spiked_shell.addOrReplaceChild("spike13", CubeListBuilder.create(), PartPose.offsetAndRotation(-0.5F, -10.0F, 5.0F, 0.0873F, 0.0F, 0.0F));

		PartDefinition cube_r41 = spike13.addOrReplaceChild("cube_r41", CubeListBuilder.create().texOffs(82, 47).addBox(0.0F, -1.0F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 0.5F, -2.0F, 0.2618F, 0.0F, 0.0F));

		PartDefinition cube_r42 = spike13.addOrReplaceChild("cube_r42", CubeListBuilder.create().texOffs(8, 87).addBox(0.0F, -1.0F, -1.0F, 0.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.2618F, 0.0F, 0.0F));

		PartDefinition spike18 = spiked_shell.addOrReplaceChild("spike18", CubeListBuilder.create(), PartPose.offsetAndRotation(-0.5F, -8.0F, 6.0F, -0.1309F, 0.0F, 0.0F));

		PartDefinition cube_r43 = spike18.addOrReplaceChild("cube_r43", CubeListBuilder.create().texOffs(12, 84).addBox(0.0F, -1.0F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 0.5F, -2.0F, 0.2618F, 0.0F, 0.0F));

		PartDefinition cube_r44 = spike18.addOrReplaceChild("cube_r44", CubeListBuilder.create().texOffs(28, 87).addBox(0.0F, -1.0F, -1.0F, 0.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.2618F, 0.0F, 0.0F));

		PartDefinition spike23 = spiked_shell.addOrReplaceChild("spike23", CubeListBuilder.create(), PartPose.offsetAndRotation(-0.5F, -6.0F, 6.0F, -0.1309F, 0.0F, 0.0F));

		PartDefinition cube_r45 = spike23.addOrReplaceChild("cube_r45", CubeListBuilder.create().texOffs(84, 43).addBox(0.0F, -1.0F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 0.5F, -2.0F, 0.2618F, 0.0F, 0.0F));

		PartDefinition cube_r46 = spike23.addOrReplaceChild("cube_r46", CubeListBuilder.create().texOffs(68, 87).addBox(0.0F, -1.0F, -1.0F, 0.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.2618F, 0.0F, 0.0F));

		PartDefinition spike28 = spiked_shell.addOrReplaceChild("spike28", CubeListBuilder.create(), PartPose.offsetAndRotation(-0.5F, -3.5F, 6.0F, -0.2618F, 0.0F, 0.0F));

		PartDefinition cube_r47 = spike28.addOrReplaceChild("cube_r47", CubeListBuilder.create().texOffs(84, 67).addBox(0.0F, -1.0F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 0.5F, -2.0F, 0.2618F, 0.0F, 0.0F));

		PartDefinition cube_r48 = spike28.addOrReplaceChild("cube_r48", CubeListBuilder.create().texOffs(88, 0).addBox(0.0F, -1.0F, -1.0F, 0.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.2618F, 0.0F, 0.0F));

		PartDefinition spike4 = spiked_shell.addOrReplaceChild("spike4", CubeListBuilder.create(), PartPose.offsetAndRotation(-2.5F, -1.0F, 5.0F, -0.48F, 0.0F, 0.0F));

		PartDefinition cube_r49 = spike4.addOrReplaceChild("cube_r49", CubeListBuilder.create().texOffs(74, 81).addBox(0.0F, -1.0F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 0.5F, -2.0F, 0.2618F, 0.0F, 0.0F));

		PartDefinition cube_r50 = spike4.addOrReplaceChild("cube_r50", CubeListBuilder.create().texOffs(16, 74).addBox(0.0F, -1.0F, -1.0F, 0.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.2618F, 0.0F, 0.0F));

		PartDefinition spike6 = spiked_shell.addOrReplaceChild("spike6", CubeListBuilder.create(), PartPose.offsetAndRotation(-2.5F, -12.0F, 4.0F, 0.3491F, 0.0F, 0.0F));

		PartDefinition cube_r51 = spike6.addOrReplaceChild("cube_r51", CubeListBuilder.create().texOffs(82, 0).addBox(0.0F, -1.0F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 0.5F, -2.0F, 0.2618F, 0.0F, 0.0F));

		PartDefinition cube_r52 = spike6.addOrReplaceChild("cube_r52", CubeListBuilder.create().texOffs(86, 35).addBox(0.0F, -1.0F, -1.0F, 0.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.2618F, 0.0F, 0.0F));

		PartDefinition spike11 = spiked_shell.addOrReplaceChild("spike11", CubeListBuilder.create(), PartPose.offsetAndRotation(-2.5F, -10.0F, 5.0F, 0.0873F, 0.0F, 0.0F));

		PartDefinition cube_r53 = spike11.addOrReplaceChild("cube_r53", CubeListBuilder.create().texOffs(34, 82).addBox(0.0F, -1.0F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 0.5F, -2.0F, 0.2618F, 0.0F, 0.0F));

		PartDefinition cube_r54 = spike11.addOrReplaceChild("cube_r54", CubeListBuilder.create().texOffs(0, 87).addBox(0.0F, -1.0F, -1.0F, 0.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.2618F, 0.0F, 0.0F));

		PartDefinition spike16 = spiked_shell.addOrReplaceChild("spike16", CubeListBuilder.create(), PartPose.offsetAndRotation(-2.5F, -8.0F, 6.0F, -0.1309F, 0.0F, 0.0F));

		PartDefinition cube_r55 = spike16.addOrReplaceChild("cube_r55", CubeListBuilder.create().texOffs(6, 84).addBox(0.0F, -1.0F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 0.5F, -2.0F, 0.2618F, 0.0F, 0.0F));

		PartDefinition cube_r56 = spike16.addOrReplaceChild("cube_r56", CubeListBuilder.create().texOffs(20, 87).addBox(0.0F, -1.0F, -1.0F, 0.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.2618F, 0.0F, 0.0F));

		PartDefinition spike21 = spiked_shell.addOrReplaceChild("spike21", CubeListBuilder.create(), PartPose.offsetAndRotation(-2.5F, -6.0F, 6.0F, -0.1309F, 0.0F, 0.0F));

		PartDefinition cube_r57 = spike21.addOrReplaceChild("cube_r57", CubeListBuilder.create().texOffs(84, 25).addBox(0.0F, -1.0F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 0.5F, -2.0F, 0.2618F, 0.0F, 0.0F));

		PartDefinition cube_r58 = spike21.addOrReplaceChild("cube_r58", CubeListBuilder.create().texOffs(60, 87).addBox(0.0F, -1.0F, -1.0F, 0.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.2618F, 0.0F, 0.0F));

		PartDefinition spike26 = spiked_shell.addOrReplaceChild("spike26", CubeListBuilder.create(), PartPose.offsetAndRotation(-2.5F, -3.5F, 6.0F, -0.2618F, 0.0F, 0.0F));

		PartDefinition cube_r59 = spike26.addOrReplaceChild("cube_r59", CubeListBuilder.create().texOffs(84, 64).addBox(0.0F, -1.0F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 0.5F, -2.0F, 0.2618F, 0.0F, 0.0F));

		PartDefinition cube_r60 = spike26.addOrReplaceChild("cube_r60", CubeListBuilder.create().texOffs(80, 87).addBox(0.0F, -1.0F, -1.0F, 0.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.2618F, 0.0F, 0.0F));

		PartDefinition spike5 = spiked_shell.addOrReplaceChild("spike5", CubeListBuilder.create(), PartPose.offsetAndRotation(-4.5F, -1.0F, 5.0F, -0.48F, 0.0F, 0.0F));

		PartDefinition cube_r61 = spike5.addOrReplaceChild("cube_r61", CubeListBuilder.create().texOffs(80, 81).addBox(0.0F, -1.0F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 0.5F, -2.0F, 0.2618F, 0.0F, 0.0F));

		PartDefinition cube_r62 = spike5.addOrReplaceChild("cube_r62", CubeListBuilder.create().texOffs(16, 77).addBox(0.0F, -1.0F, -1.0F, 0.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.2618F, 0.0F, 0.0F));

		PartDefinition spike7 = spiked_shell.addOrReplaceChild("spike7", CubeListBuilder.create(), PartPose.offsetAndRotation(-4.5F, -12.0F, 4.0F, 0.3491F, 0.0F, 0.0F));

		PartDefinition cube_r63 = spike7.addOrReplaceChild("cube_r63", CubeListBuilder.create().texOffs(82, 3).addBox(0.0F, -1.0F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 0.5F, -2.0F, 0.2618F, 0.0F, 0.0F));

		PartDefinition cube_r64 = spike7.addOrReplaceChild("cube_r64", CubeListBuilder.create().texOffs(86, 38).addBox(0.0F, -1.0F, -1.0F, 0.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.2618F, 0.0F, 0.0F));

		PartDefinition spike12 = spiked_shell.addOrReplaceChild("spike12", CubeListBuilder.create(), PartPose.offsetAndRotation(-4.5F, -10.0F, 5.0F, 0.0873F, 0.0F, 0.0F));

		PartDefinition cube_r65 = spike12.addOrReplaceChild("cube_r65", CubeListBuilder.create().texOffs(42, 82).addBox(0.0F, -1.0F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 0.5F, -2.0F, 0.2618F, 0.0F, 0.0F));

		PartDefinition cube_r66 = spike12.addOrReplaceChild("cube_r66", CubeListBuilder.create().texOffs(4, 87).addBox(0.0F, -1.0F, -1.0F, 0.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.2618F, 0.0F, 0.0F));

		PartDefinition spike17 = spiked_shell.addOrReplaceChild("spike17", CubeListBuilder.create(), PartPose.offsetAndRotation(-4.5F, -8.0F, 6.0F, -0.1309F, 0.0F, 0.0F));

		PartDefinition cube_r67 = spike17.addOrReplaceChild("cube_r67", CubeListBuilder.create().texOffs(84, 9).addBox(0.0F, -1.0F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 0.5F, -2.0F, 0.2618F, 0.0F, 0.0F));

		PartDefinition cube_r68 = spike17.addOrReplaceChild("cube_r68", CubeListBuilder.create().texOffs(24, 87).addBox(0.0F, -1.0F, -1.0F, 0.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.2618F, 0.0F, 0.0F));

		PartDefinition spike22 = spiked_shell.addOrReplaceChild("spike22", CubeListBuilder.create(), PartPose.offsetAndRotation(-4.5F, -6.0F, 6.0F, -0.1309F, 0.0F, 0.0F));

		PartDefinition cube_r69 = spike22.addOrReplaceChild("cube_r69", CubeListBuilder.create().texOffs(28, 84).addBox(0.0F, -1.0F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 0.5F, -2.0F, 0.2618F, 0.0F, 0.0F));

		PartDefinition cube_r70 = spike22.addOrReplaceChild("cube_r70", CubeListBuilder.create().texOffs(64, 87).addBox(0.0F, -1.0F, -1.0F, 0.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.2618F, 0.0F, 0.0F));

		PartDefinition spike27 = spiked_shell.addOrReplaceChild("spike27", CubeListBuilder.create(), PartPose.offsetAndRotation(-4.5F, -3.5F, 6.0F, -0.2618F, 0.0F, 0.0F));

		PartDefinition cube_r71 = spike27.addOrReplaceChild("cube_r71", CubeListBuilder.create().texOffs(66, 84).addBox(0.0F, -1.0F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 0.5F, -2.0F, 0.2618F, 0.0F, 0.0F));

		PartDefinition cube_r72 = spike27.addOrReplaceChild("cube_r72", CubeListBuilder.create().texOffs(84, 87).addBox(0.0F, -1.0F, -1.0F, 0.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.2618F, 0.0F, 0.0F));

		PartDefinition robe = abdomen.addOrReplaceChild("robe", CubeListBuilder.create().texOffs(54, 69).addBox(0.0F, -15.0F, -2.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(72, 66).addBox(0.0F, -14.0F, 0.0F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(72, 47).addBox(4.0F, -14.0F, -2.0F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(76, 52).addBox(6.0F, -13.0F, -2.0F, 2.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r73 = robe.addOrReplaceChild("cube_r73", CubeListBuilder.create().texOffs(84, 28).addBox(-1.0231F, -0.1504F, -1.0F, 2.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.5F, -12.5F, 1.15F, -2.2689F, 0.3054F, 3.1416F));

		PartDefinition cube_r74 = robe.addOrReplaceChild("cube_r74", CubeListBuilder.create().texOffs(0, 21).addBox(-1.5F, 0.0F, -21.0F, 2.0F, 0.0F, 21.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.0F, -12.0F, 2.15F, -1.5708F, 0.3054F, 3.1416F));

		PartDefinition cube_r75 = robe.addOrReplaceChild("cube_r75", CubeListBuilder.create().texOffs(42, 61).addBox(-1.0904F, 0.2868F, -1.0F, 2.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(8.5F, -12.25F, 1.15F, 2.3998F, 0.6109F, 0.0F));

		PartDefinition cube_r76 = robe.addOrReplaceChild("cube_r76", CubeListBuilder.create().texOffs(0, 0).addBox(-1.5F, 0.0F, -21.0F, 2.0F, 0.0F, 21.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(9.5F, -12.0F, 1.65F, 1.5708F, 0.6109F, 0.0F));

		PartDefinition cube_r77 = robe.addOrReplaceChild("cube_r77", CubeListBuilder.create().texOffs(0, 42).addBox(0.0F, 0.0F, -1.5F, 21.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(9.75F, -12.0F, -1.0F, 0.0F, 0.0F, 1.5708F));

		PartDefinition cube_r78 = robe.addOrReplaceChild("cube_r78", CubeListBuilder.create().texOffs(72, 43).addBox(0.0F, 0.0F, -1.5F, 2.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(8.0F, -12.75F, -1.0F, 0.0F, 0.0F, 0.3927F));

		PartDefinition right_arm = body.addOrReplaceChild("right_arm", CubeListBuilder.create(), PartPose.offset(-2.5F, -19.0F, 4.5F));

		PartDefinition right_upper_arm = right_arm.addOrReplaceChild("right_upper_arm", CubeListBuilder.create().texOffs(20, 65).addBox(-1.0F, 0.0F, -1.25F, 2.0F, 5.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(72, 59).addBox(-1.5F, 0.0F, -0.75F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(68, 77).addBox(-1.0F, 5.0F, -0.75F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, 2.0F, -0.25F));

		PartDefinition right_shoulder = right_arm.addOrReplaceChild("right_shoulder", CubeListBuilder.create().texOffs(30, 53).addBox(-2.0F, -3.0F, -2.0F, 3.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(0, 73).addBox(-3.0F, -3.0F, -1.5F, 1.0F, 4.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(30, 71).addBox(-2.0F, -3.5F, -1.5F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.5F, 1.0F, 0.0F));

		PartDefinition right_lower_arm = right_arm.addOrReplaceChild("right_lower_arm", CubeListBuilder.create(), PartPose.offset(-1.0F, 7.5F, 0.0F));

		PartDefinition cube_r79 = right_lower_arm.addOrReplaceChild("cube_r79", CubeListBuilder.create().texOffs(68, 69).addBox(-1.0F, 0.0F, -2.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.5F, 0.5F, -0.48F, 0.0F, 0.0F));

		PartDefinition cube_r80 = right_lower_arm.addOrReplaceChild("cube_r80", CubeListBuilder.create().texOffs(36, 75).addBox(-2.0F, -5.0F, -1.0F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, 4.5F, -2.25F, -0.5236F, 0.0F, 0.0F));

		PartDefinition right_hand = right_lower_arm.addOrReplaceChild("right_hand", CubeListBuilder.create().texOffs(76, 77).addBox(-2.5F, -1.0F, -2.25F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(1.5F, 5.0F, -2.75F));

		PartDefinition claw1 = right_hand.addOrReplaceChild("claw1", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, -3.0F));

		PartDefinition cube_r81 = claw1.addOrReplaceChild("cube_r81", CubeListBuilder.create().texOffs(78, 39).addBox(-1.0F, -1.0F, -1.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, 0.0F, -1.75F, 0.0F, -0.1745F, 0.0F));

		PartDefinition cube_r82 = claw1.addOrReplaceChild("cube_r82", CubeListBuilder.create().texOffs(78, 84).addBox(-1.0F, -1.0F, 0.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.2618F, 0.0F));

		PartDefinition claw2 = right_hand.addOrReplaceChild("claw2", CubeListBuilder.create().texOffs(78, 35).addBox(-1.0F, -1.0F, -1.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, 2.0F, -5.0F));

		PartDefinition cube_r83 = claw2.addOrReplaceChild("cube_r83", CubeListBuilder.create().texOffs(84, 73).addBox(-1.0F, -1.0F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.5F, 2.75F, 0.2618F, 0.0F, 0.0F));

		PartDefinition claw3 = right_hand.addOrReplaceChild("claw3", CubeListBuilder.create(), PartPose.offset(-2.25F, 0.0F, -4.75F));

		PartDefinition cube_r84 = claw3.addOrReplaceChild("cube_r84", CubeListBuilder.create().texOffs(84, 76).addBox(-1.0F, -1.0F, 0.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.25F, 0.0F, 1.5F, 0.0F, 0.2618F, 0.0F));

		PartDefinition cube_r85 = claw3.addOrReplaceChild("cube_r85", CubeListBuilder.create().texOffs(0, 80).addBox(-1.0F, -1.0F, -1.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.1745F, 0.0F));

		PartDefinition left_arm = body.addOrReplaceChild("left_arm", CubeListBuilder.create(), PartPose.offset(8.5F, -19.0F, 4.5F));

		PartDefinition left_upper_arm = left_arm.addOrReplaceChild("left_upper_arm", CubeListBuilder.create().texOffs(68, 35).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 5.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(20, 73).addBox(-0.5F, -2.0F, -0.5F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(8, 80).addBox(-1.0F, 3.0F, -0.5F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, 4.0F, -0.5F));

		PartDefinition left_shoulder = left_arm.addOrReplaceChild("left_shoulder", CubeListBuilder.create().texOffs(16, 57).addBox(-1.0F, -3.0F, -2.0F, 3.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(54, 73).addBox(2.0F, -3.0F, -1.5F, 1.0F, 4.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(72, 10).addBox(-1.0F, -3.5F, -1.5F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.5F, 1.0F, 0.0F));

		PartDefinition left_lower_arm = left_arm.addOrReplaceChild("left_lower_arm", CubeListBuilder.create(), PartPose.offset(1.0F, 13.5F, -4.0F));

		PartDefinition cube_r86 = left_lower_arm.addOrReplaceChild("cube_r86", CubeListBuilder.create().texOffs(72, 14).addBox(-1.0F, 0.0F, -2.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -5.5F, 4.5F, -0.48F, 0.0F, 0.0F));

		PartDefinition cube_r87 = left_lower_arm.addOrReplaceChild("cube_r87", CubeListBuilder.create().texOffs(24, 80).addBox(1.0F, -5.0F, -1.0F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, -1.5F, 1.75F, -0.5236F, 0.0F, 0.0F));

		PartDefinition left_hand = left_lower_arm.addOrReplaceChild("left_hand", CubeListBuilder.create().texOffs(80, 14).addBox(-1.75F, -1.0F, -1.5F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.75F, -1.0F, 0.5F));

		PartDefinition claw6 = left_hand.addOrReplaceChild("claw6", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, -4.0F));

		PartDefinition cube_r88 = claw6.addOrReplaceChild("cube_r88", CubeListBuilder.create().texOffs(40, 85).addBox(0.0F, -1.0F, 0.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.25F, 0.0F, 1.5F, 0.0F, -0.2618F, 0.0F));

		PartDefinition cube_r89 = claw6.addOrReplaceChild("cube_r89", CubeListBuilder.create().texOffs(54, 80).addBox(0.0F, -1.0F, -1.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.1745F, 0.0F));

		PartDefinition claw5 = left_hand.addOrReplaceChild("claw5", CubeListBuilder.create().texOffs(80, 18).addBox(0.0F, -1.0F, -1.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.25F, 2.0F, -4.25F));

		PartDefinition cube_r90 = claw5.addOrReplaceChild("cube_r90", CubeListBuilder.create().texOffs(34, 85).addBox(0.0F, -1.0F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.5F, 2.75F, 0.2618F, 0.0F, 0.0F));

		PartDefinition claw4 = left_hand.addOrReplaceChild("claw4", CubeListBuilder.create(), PartPose.offset(-2.25F, 0.0F, -2.25F));

		PartDefinition cube_r91 = claw4.addOrReplaceChild("cube_r91", CubeListBuilder.create().texOffs(16, 80).addBox(0.0F, -1.0F, -1.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 0.0F, -1.75F, 0.0F, 0.1745F, 0.0F));

		PartDefinition cube_r92 = claw4.addOrReplaceChild("cube_r92", CubeListBuilder.create().texOffs(84, 84).addBox(0.0F, -1.0F, 0.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.2618F, 0.0F));

		PartDefinition right_leg = body.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(82, 50).addBox(-1.0F, 9.0F, -5.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(80, 61).addBox(-1.0F, 8.0F, -4.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(50, 52).addBox(-1.5F, 3.0F, -3.0F, 3.0F, 7.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(44, 53).addBox(0.75F, 3.5F, -2.5F, 1.0F, 6.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(62, 73).addBox(-1.75F, 3.5F, -2.5F, 1.0F, 6.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(10, 63).addBox(-1.0F, 9.0F, 0.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(8, 74).addBox(-1.0F, 3.0F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(76, 73).addBox(-1.0F, 4.0F, -3.25F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(62, 52).addBox(-1.5F, 0.0F, -2.5F, 3.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -10.0F, 5.0F));

		PartDefinition left_leg = body.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(84, 12).addBox(-1.0F, 8.0F, -4.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(46, 85).addBox(-1.0F, 7.0F, -3.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(30, 61).addBox(-1.5F, 2.0F, -2.0F, 3.0F, 7.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(42, 74).addBox(-1.75F, 2.5F, -1.5F, 1.0F, 6.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(48, 74).addBox(0.75F, 2.5F, -1.5F, 1.0F, 6.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(84, 79).addBox(-1.0F, 8.0F, 1.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(74, 29).addBox(-1.0F, 2.0F, 0.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(80, 57).addBox(-1.0F, 3.0F, -2.25F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(58, 62).addBox(-1.5F, -1.0F, -1.5F, 3.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(6.0F, -9.0F, 4.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public ModelPart root() {
		return hooded_spiked_rat;
	}

	@Override
	public void setupAnim(HoodedSpikedRatEntity entity, float LimbSwing, float LimbSwingAmount, float ageInTicks, float NetHeadYaw, float HeadPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		//DEBUG: comment out after testing
		//System.out.println("limbSwingAmount=" + LimbSwingAmount);
		this.animateWalk(HoodedSpikedRatAnimations.HOODED_SPIKEDRAT_WALK, LimbSwing, LimbSwingAmount, 1f,1.25f);
		this.animate(entity.idleHoodedRatState, HoodedSpikedRatAnimations.HOODED_SPIKEDRAT_IDLE, ageInTicks, 1f);
		this.animate(entity.hoodedRatAttackingState, HoodedSpikedRatAnimations.HOODED_SPIKEDRAT_ATTACK, ageInTicks, 1f);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		hooded_spiked_rat.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}