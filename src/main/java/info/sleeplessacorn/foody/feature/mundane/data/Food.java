package info.sleeplessacorn.foody.feature.mundane.data;

import com.google.common.collect.Lists;
import info.sleeplessacorn.foody.feature.mundane.FeatureMundane;
import info.sleeplessacorn.foody.feature.mundane.Utensil;
import info.sleeplessacorn.foody.feature.mundane.item.ItemFoodyFood;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import org.apache.commons.lang3.tuple.Pair;
import tehnut.lib.mc.util.OreDictPrioritized;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.List;

public class Food {

    public static final Food AIR = new Chef("air").cook();

    @Nonnull
    private final String name;
    @Nonnull
    private final FoodType type;
    @Nonnegative
    private final int hunger;
    @Nonnegative
    private final float saturation;
    @Nonnull
    private final CookingStyle cookingStyle;
    @Nonnull
    private final Object[] ingredients;
    @Nullable
    private final Utensil utensil;
    private final boolean alwaysEdible;
    @Nullable
    private final Pair<PotionEffect, Float>[] effects;

    public Food(@Nonnull String name, @Nonnull FoodType type, @Nonnegative int hunger, @Nonnegative float saturation, @Nonnull CookingStyle cookingStyle, @Nonnull Object[] ingredients, @Nullable Utensil utensil, boolean alwaysEdible, @Nullable Pair<PotionEffect, Float>[] effects) {
        this.name = name;
        this.type = type;
        this.hunger = hunger;
        this.saturation = saturation;
        this.cookingStyle = cookingStyle;
        if (cookingStyle != CookingStyle.BAKED)
            this.ingredients = ingredients;
        else {
            this.ingredients = Arrays.copyOfRange(ingredients, 0, 1);
        }
        this.utensil = utensil;
        this.alwaysEdible = alwaysEdible;
        this.effects = effects;
    }

    @Nonnull
    public String getName() {
        return name;
    }

    @Nonnull
    public FoodType getType() {
        return type;
    }

    @Nonnegative
    public int getHunger() {
        return hunger;
    }

    @Nonnegative
    public float getSaturation() {
        return saturation;
    }

    public boolean isAlwaysEdible() {
        return alwaysEdible;
    }

    @Nonnull
    public Pair<PotionEffect, Float>[] getEffects() {
        return effects == null ? new Pair[] {} : effects;
    }

    public void handleRecipe() {
        if (AIR.equals(this))
            return;

        switch (cookingStyle) {
            case HAND: {
                List<Object> ingreds = Lists.newArrayList(ingredients);
                if (utensil != null)
                    ingreds.add(new ItemStack(FeatureMundane.UTENSIL, 1, utensil.ordinal()));
                GameRegistry.addRecipe(new ShapelessOreRecipe(ItemFoodyFood.getFoodStack(this), ingreds.toArray(new Object[ingreds.size()])));
            }
            case BAKED: {
                ItemStack input = getStack(ingredients[0]);
                GameRegistry.addSmelting(input, ItemFoodyFood.getFoodStack(this), 0.2F);
            }
        }
    }

    private ItemStack getStack(Object object) {
        ItemStack ret = ItemStack.EMPTY;

        if (object instanceof ItemStack)
            ret = (ItemStack) object;
        else if (object instanceof Item)
            ret = new ItemStack((Item) object);
        else if (object instanceof Block)
            ret = new ItemStack((Block) object);
        else if (object instanceof List && ((List) object).get(0).getClass() == ItemStack.class)
            ret = (ItemStack) ((List) object).get(0);
        else if (object instanceof String)
            ret = OreDictPrioritized.get((String) object);

        return ret;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Food)) return false;

        Food food = (Food) o;

        return name.equals(food.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
