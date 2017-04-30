package info.sleeplessacorn.foody.feature.mundane.data;

import info.sleeplessacorn.foody.feature.mundane.FeatureMundane;
import info.sleeplessacorn.foody.feature.mundane.Utensil;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import org.apache.commons.lang3.tuple.Pair;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public final class Chef {

    private final String name;
    private FoodType type = FoodType.SOLID;
    private int hunger = 0;
    private float saturation = 0.0F;
    private CookingStyle cookingStyle = CookingStyle.HAND;
    private Object[] ingredients = new Object[] { ItemStack.EMPTY };
    private Utensil utensil = null;
    private boolean alwaysEdible = false;
    private Pair<PotionEffect, Float>[] effects = null;

    public Chef(@Nonnull String name) {
        this.name = name;
    }

    public Chef asType(@Nonnull FoodType foodType) {
        this.type = foodType;
        return this;
    }

    public Chef withNutrution(int hunger, float saturation) {
        this.hunger = hunger;
        this.saturation = saturation;
        return this;
    }

    public Chef withStyle(CookingStyle cookingStyle) {
        this.cookingStyle = cookingStyle;
        return this;
    }

    public Chef withIngredients(@Nonnull Object... ingredients) {
        this.ingredients = ingredients;
        return this;
    }

    public Chef withUtensil(@Nullable Utensil utensil) {
        this.utensil = utensil;
        return this;
    }

    public Chef asAddiction() {
        this.alwaysEdible = true;
        return this;
    }

    public Chef withEffects(@Nullable Pair<PotionEffect, Float>... effects) {
        this.effects = effects;
        return this;
    }

    @Nonnull
    public Food cook() {
        Food food = new Food(name, type, hunger, saturation, cookingStyle, ingredients, utensil, alwaysEdible, effects);
        FeatureMundane.FOODS.put(food.getName(), food);
        return food;
    }
}
