package info.sleeplessacorn.foody.feature.mundane.data;

import info.sleeplessacorn.foody.feature.mundane.Utensil;
import net.minecraft.potion.PotionEffect;

public final class Chef {

    private final String name;
    private FoodType type = FoodType.SOLID;
    private Object[] ingredients;
    private Utensil utensil;
    private PotionEffect[] effects;

    public Chef(String name) {
        this.name = name;
    }

    public Chef asType(FoodType foodType) {
        this.type = foodType;
        return this;
    }

    public Chef withIngredients(Object... ingredients) {
        this.ingredients = ingredients;
        return this;
    }

    public Chef withUtensil(Utensil utensil) {
        this.utensil = utensil;
        return this;
    }

    public Chef withEffects(PotionEffect... effects) {
        this.effects = effects;
        return this;
    }

    public Food cook() {
        return new Food(name, type, ingredients, utensil, effects);
    }
}
