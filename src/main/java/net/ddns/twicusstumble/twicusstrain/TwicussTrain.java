package net.ddns.twicusstumble.twicusstrain;

import net.ddns.twicusstumble.twicusstrain.client.gui.GuiThrottleBar;
import net.ddns.twicusstumble.twicusstrain.proxy.CommonProxy;
import net.ddns.twicusstumble.twicusstrain.tab.TwicussTrainTab;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLConstructionEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.registry.GameRegistry;
import org.apache.logging.log4j.Logger;

@Mod(
        modid = TwicussTrain.MOD_ID,
        name = TwicussTrain.MOD_NAME,
        version = TwicussTrain.VERSION
)
public class TwicussTrain {

    public static final String MOD_ID = "twicusstrain";
    public static final String MOD_NAME = "Twicuss Train";
    public static final String VERSION = "1.2.0";

    public static Logger logger;

    public static final String CLIENT_SIDE = "net.ddns.twicusstumble." + MOD_ID + ".proxy.ClientProxy";
    public static final String SERVER_SIDE = "net.ddns.twicusstumble." + MOD_ID + ".proxy.ServerProxy";

    /**
     * This is the instance of your mod as created by Forge. It will never be null.
     */
    @Mod.Instance(MOD_ID)
    public static TwicussTrain INSTANCE;

    @SidedProxy(clientSide = CLIENT_SIDE, serverSide = SERVER_SIDE)
    public static CommonProxy proxy;

    public static CreativeTabs twicussTrainTab = new TwicussTrainTab();

    @Mod.EventHandler
    public void construct(FMLConstructionEvent event) {
        MinecraftForge.EVENT_BUS.register(proxy);
    }

    /**
     * This is the first initialization event. Register tile entities here.
     * The registry events below will have fired prior to entry to this method.
     */
    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
        logger.info("TwicussTrain.preInit");
        proxy.preInit(event);
    }

    /**
     * This is the second initialization event. Register custom recipes
     */
    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        logger.info("TwicussTrain.init");
        proxy.init(event);
    }

    /**
     * This is the final initialization event. Register actions from other mods here
     */
    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        logger.info("TwicussTrain.postInit");
        proxy.postInit(event);
    }
}
