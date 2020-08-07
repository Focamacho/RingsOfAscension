package com.focamacho.ringsofascension.item.rings.effects;

import com.focamacho.ringsofascension.config.ConfigHolder;
import com.focamacho.ringsofascension.item.ItemRingBase;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class ItemRingJumpBoost extends ItemRingBase {

    public ItemRingJumpBoost(Properties properties, String name, String tooltip) {
        super(properties, name, tooltip);
    }

    @Override
    public void tickCurio(String identifier, int index, LivingEntity livingEntity) {
        if(!ConfigHolder.ringJumpBoost) return;
        if(!livingEntity.isPotionActive(Effects.JUMP_BOOST)) {
            EffectInstance effectInstance = new EffectInstance(Effects.JUMP_BOOST, Integer.MAX_VALUE, ConfigHolder.ringAmplifierJumpBoost, false, false);
            if(livingEntity.world.isRemote) effectInstance.setPotionDurationMax(true);
            livingEntity.addPotionEffect(effectInstance);
        }
    }

    @Override
    public void onUnequippedCurio(String identifier, LivingEntity livingEntity) {
        if(!ConfigHolder.ringJumpBoost) return;
        livingEntity.removePotionEffect(Effects.JUMP_BOOST);
    }

    @Override
    public int getTier() {
        return ConfigHolder.ringTierJumpBoost;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        if(!ConfigHolder.ringJumpBoost) return;
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }

}