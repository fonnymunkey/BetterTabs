package bettertabs.core;

import org.apache.logging.log4j.Logger;

import bettertabs.core.proxies.CommonProxy;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.ModContainer;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;

@Mod(modid = BetterTabs.MODID, version = BetterTabs.VERSION, name = BetterTabs.NAME, dependencies = "after:betterquesting;after:levelup2;after:lycanitesmobs;after:levelup;after:classyhats;after:librarianlib")
public class BetterTabs
{
    public static final String MODID = "bettertabs";
    public static final String VERSION = "1.0.4";
    public static final String NAME = "BetterTabs";
    public static final String PROXY = "bettertabs.core.proxies";
    public static final String CHANNEL = "BETTERTABS";
	
	@Instance(MODID)
	public static BetterTabs instance;
	
	@SidedProxy(clientSide = PROXY + ".ClientProxy", serverSide = PROXY + ".CommonProxy")
	public static CommonProxy proxy;
	public SimpleNetworkWrapper network;
	public static Logger logger;
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
    	logger = event.getModLog();
    	network = NetworkRegistry.INSTANCE.newSimpleChannel(CHANNEL);   

    	proxy.registerConfig(event);
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        ModContainer modContainer = Loader.instance().getIndexedModList().get("bettertabs");
        if(modContainer != null && modContainer.getMod() instanceof BetterTabs)
        {
            BetterTabs modInstance = (BetterTabs)modContainer.getMod();
            // DO THINGS...
        }
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
    	proxy.registerHandlers();
    }
    
    @EventHandler
    public void serverLoad(FMLServerStartingEvent event)
    {
    }
}
