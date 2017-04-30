package info.sleeplessacorn.foody.feature.mundane;

import net.minecraft.util.IStringSerializable;

import java.util.Locale;

public enum Utensil implements IStringSerializable {

    BOWL,
    ;

    @Override
    public String getName() {
        return name().toLowerCase(Locale.ENGLISH);
    }
}
