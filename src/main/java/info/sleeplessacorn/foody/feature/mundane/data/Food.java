package info.sleeplessacorn.foody.feature.mundane.data;

import info.sleeplessacorn.foody.feature.mundane.Utensil;
import net.minecraft.potion.PotionEffect;
import org.apache.commons.lang3.tuple.Pair;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class Food {

    public static final Food AIR = new Food("default", FoodType.SOLID, 0, 0.0F, null, null, false, null);

    @Nonnull
    private final String name;
    @Nonnull
    private final FoodType type;
    @Nonnegative
    private final int hunger;
    @Nonnegative
    private final float saturation;
    @Nullable
    private final Object[] ingredients;
    @Nullable
    private final Utensil utensil;
    private final boolean alwaysEdible;
    @Nullable
    private final Pair<PotionEffect, Float>[] effects;

    public Food(@Nonnull String name, @Nonnull FoodType type, @Nonnegative int hunger, @Nonnegative float saturation, Object[] ingredients, Utensil utensil, boolean alwaysEdible, @Nullable Pair<PotionEffect, Float>[] effects) {
        this.name = name;
        this.type = type;
        this.hunger = hunger;
        this.saturation = saturation;
        this.ingredients = ingredients;
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

    @Nullable
    public Utensil getUtensil() {
        return utensil;
    }

    public boolean isAlwaysEdible() {
        return alwaysEdible;
    }

    @Nonnull
    public Pair<PotionEffect, Float>[] getEffects() {
        return effects == null ? new Pair[] {} : effects;
    }

    public void handleRecipe() {

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
