package info.sleeplessacorn.foody.lib.feature;

import info.sleeplessacorn.foody.lib.util.helper.ReflectionHelper;
import net.minecraftforge.fml.common.Loader;

import javax.annotation.Nonnull;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Feature {

    /**
     * Unique name for the Feature. Used in config handling.
     *
     * @return a unique name for the Feature.
     */
    @Nonnull
    String name();

    /**
     * A short description of what this Feature does. Used in config handling.
     *
     * @return a short description of this Feature.
     */
    @Nonnull
    String description() default "";

    /**
     * Whether this Feature should be enabled by default or not. Used in config handling.
     *
     * @return if this Feature should be enabled by default.
     */
    boolean enabledByDefault() default true;

    /**
     * Whether or not to check this feature for fields annotated with {@link Config} for usage with the config file.
     *
     * @return if this Feature should be handled during the config phase.
     */
    boolean handleConfig() default false;

    /**
     * The order in which this Feature should be loaded against other Features. Lower values are loaded first.
     *
     * @return the load priority of this feature.
     */
    int priority() default 100;

    /**
     * A list of modids required for this Feature to be considered for loading. The Feature will only be loaded if
     * <i>all</i> the requirements are met.
     *
     * @return a list of modids required for loading.
     */
    @Nonnull
    String[] modRequirements() default {};

    @Target(ElementType.FIELD)
    @Retention(RetentionPolicy.RUNTIME)
    @interface Config {

        /**
         * The category this value should be placed in. Prefixed with <i>feature.{@link Config#name()}</i>.
         *
         * @return the category this value should be placed in.
         */
        String category() default "";

        /**
         * A comment describing this value.
         *
         * @return a comment describing this value.
         */
        String comment() default "";
    }

    ReflectionHelper.IAnnotationPreloader PRELOADER = annotationData -> {
        Object requirements = annotationData.get("modRequirements");
        if (requirements == null)
            return true;

        String[] cast = (String[]) requirements;
        for (String mod : cast)
            if (!Loader.isModLoaded(mod))
                return false;

        return true;
    };

    ReflectionHelper.IAnnotationLoader<IFeature> LOADER = (type, annotationData) -> (IFeature) type.newInstance();
}