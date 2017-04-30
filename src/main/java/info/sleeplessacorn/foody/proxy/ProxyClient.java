package info.sleeplessacorn.foody.proxy;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import tehnut.lib.mc.model.IModeled;
import tehnut.lib.mc.model.ModelHandler;

public class ProxyClient extends ProxyCommon {

    @Override
    public void handleModel(IModeled modeled) {
        if (modeled instanceof Item)
            ModelHandler.Client.handleItemModel((Item) modeled);
        else if (modeled instanceof Block)
            ModelHandler.Client.handleBlockModel((Block) modeled);
    }
}
