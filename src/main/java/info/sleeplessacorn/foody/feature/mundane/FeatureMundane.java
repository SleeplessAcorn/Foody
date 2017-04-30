package info.sleeplessacorn.foody.feature.mundane;

import com.google.common.collect.Maps;
import info.sleeplessacorn.foody.Foody;
import info.sleeplessacorn.foody.feature.mundane.data.Chef;
import info.sleeplessacorn.foody.feature.mundane.data.CookingStyle;
import info.sleeplessacorn.foody.feature.mundane.data.Food;
import info.sleeplessacorn.foody.feature.mundane.item.ItemFoodyFood;
import info.sleeplessacorn.foody.feature.mundane.item.ItemUtensil;
import net.minecraft.init.Items;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import tehnut.lib.forge.feature.Feature;
import tehnut.lib.forge.feature.IFeature;

import java.util.Map;

@Feature(name = "mundane", description = "Mundane foods", priority = 101)
public class FeatureMundane implements IFeature {

    public static final Map<String, Food> FOODS = Maps.newHashMap();

    public static final ItemFoodyFood FOOD = new ItemFoodyFood();
    public static final ItemUtensil UTENSIL = new ItemUtensil();

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        GameRegistry.register(FOOD.setRegistryName("food"));
        GameRegistry.register(UTENSIL.setRegistryName("utensil"));

        new Chef("toast").withNutrution(5, 0.8F).withStyle(CookingStyle.BAKED).withIngredients(Items.BREAD).asAddiction().cook();

        Foody.PROXY.handleModel(FOOD);
        Foody.PROXY.handleModel(UTENSIL);

        Foody.TAB_FOODY.display = ItemFoodyFood.getFoodStack(FOODS.get("toast"));
    }

    @Override
    public void init(FMLInitializationEvent event) {

    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        for (Food food : FOODS.values())
            food.handleRecipe();
    }

    @Override
    public void handleConfig(Configuration config, String category) {

    }
}
