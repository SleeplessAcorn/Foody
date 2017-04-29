package info.sleeplessacorn.foody.feature.mundane.data;

import info.sleeplessacorn.foody.feature.mundane.Utensil;
import net.minecraft.potion.PotionEffect;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class Food {

    @Nonnull
    private final String name;
    @Nonnull
    private final FoodType type;
    @Nullable
    private final Object[] ingredients;
    @Nullable
    private final Utensil utensil;
    private final PotionEffect[] effects;

    public Food(@Nonnull String name, @Nonnull FoodType type, Object[] ingredients, Utensil utensil, PotionEffect[] effects) {
        this.name = name;
        this.type = type;
        this.ingredients = ingredients;
        this.utensil = utensil;
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

    @Nullable
    public Utensil getUtensil() {
        return utensil;
    }

    public PotionEffect[] getEffects() {
        return effects;
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
