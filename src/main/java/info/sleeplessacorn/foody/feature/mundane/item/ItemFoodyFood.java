package info.sleeplessacorn.foody.feature.mundane.item;

import info.sleeplessacorn.foody.Foody;
import info.sleeplessacorn.foody.feature.mundane.FeatureMundane;
import info.sleeplessacorn.foody.feature.mundane.data.Food;
import info.sleeplessacorn.foody.feature.mundane.data.FoodType;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.commons.lang3.tuple.Pair;

public class ItemFoodyFood extends ItemFood {

    public ItemFoodyFood() {
        super(0, false);

        setUnlocalizedName(Foody.MODID + ".food");
        setCreativeTab(CreativeTabs.FOOD);
        setHasSubtypes(true);
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return super.getUnlocalizedName(stack) + "." + getFood(stack).getName();
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void getSubItems(Item itemIn, CreativeTabs tab, NonNullList<ItemStack> subItems) {
        for (Food food : FeatureMundane.FOODS.values()) {
            NBTTagCompound tagCompound = new NBTTagCompound();
            tagCompound.setString("food", food.getName());
            ItemStack stack = new ItemStack(itemIn);
            stack.setTagCompound(tagCompound);
            subItems.add(stack);
        }
    }

    // ItemFood overrides

    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer player, EnumHand hand) {
        ItemStack stack = player.getHeldItem(hand);

        if (player.canEat(getFood(stack).isAlwaysEdible())) {
            player.setActiveHand(hand);
            return ActionResult.newResult(EnumActionResult.SUCCESS, stack);
        }

        return ActionResult.newResult(EnumActionResult.FAIL, stack);
    }

    @Override
    protected void onFoodEaten(ItemStack stack, World world, EntityPlayer player) {
        if (world.isRemote)
            return;

        for (Pair<PotionEffect, Float> effect : getFood(stack).getEffects())
            if (world.rand.nextFloat() < effect.getRight())
                player.addPotionEffect(effect.getLeft());
    }

    @Override
    public EnumAction getItemUseAction(ItemStack stack) {
        return getFood(stack).getType() == FoodType.SOLID ? EnumAction.EAT : EnumAction.DRINK;
    }

    @Override
    public int getHealAmount(ItemStack stack) {
        return getFood(stack).getHunger();
    }

    @Override
    public float getSaturationModifier(ItemStack stack) {
        return getFood(stack).getSaturation();
    }

    public Food getFood(ItemStack stack) {
        return stack.hasTagCompound() ? FeatureMundane.FOODS.get(stack.getTagCompound().getString("food")) : Food.AIR;
    }
}
