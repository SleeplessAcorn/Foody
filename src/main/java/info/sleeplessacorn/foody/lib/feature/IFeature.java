package info.sleeplessacorn.foody.lib.feature;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public interface IFeature {

    /**
     * Called during the {@link FMLPreInitializationEvent} lifecycle event.
     */
    void preInit(FMLPreInitializationEvent event);

    /**
     * Called during the {@link FMLInitializationEvent} lifecycle event.
     */
    void init(FMLInitializationEvent event);

    /**
     * Called during the {@link FMLPostInitializationEvent} lifecycle event.
     */
    void postInit(FMLPostInitializationEvent event);

    /**
     * Called during the {@link FMLPreInitializationEvent} lifecycle event, just before {@link #preInit(FMLPreInitializationEvent)}
     * is called.
     *
     * Use this chance to handle any custom configuration logic that can't be handled with annotated fields.
     *
     * @param config - The configuration object these values will be placed in and read from
     * @param category - The base category these values should be placed into.
     */
    void handleConfig(Configuration config, String category);
}
