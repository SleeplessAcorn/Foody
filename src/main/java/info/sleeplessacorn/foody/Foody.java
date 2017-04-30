package info.sleeplessacorn.foody;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tehnut.lib.forge.feature.Feature;
import tehnut.lib.forge.feature.FeatureHandler;
import tehnut.lib.forge.feature.IFeature;
import tehnut.lib.forge.util.helper.ReflectionHelper;
import tehnut.lib.forge.util.helper.StringHelper;

import java.util.List;

@Mod(modid = Foody.MODID, name = Foody.NAME, version = "@VERSION@")
public class Foody {

    public static final String MODID = "foody";
    public static final String NAME = "Foody";
    public static final Logger LOGGER = LogManager.getLogger(NAME);

    public static FeatureHandler featureHandler;
    public static List<Pair<IFeature, Feature>> features;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        features = ReflectionHelper.getAnnotationInstances(event.getAsmData(), Feature.class, IFeature.class);
        featureHandler = new FeatureHandler(new Configuration(event.getSuggestedConfigurationFile()), features);

        String featureNames = "";
        for (Pair<IFeature, Feature> feature : features)
            featureNames += "\n  - " + StringHelper.toPretty(feature.getRight().name());

        event.getModMetadata().description += "\nLoaded modules: " + featureNames;

        featureHandler.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        featureHandler.init(event);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        featureHandler.postInit(event);
    }
}
