package bettertabs.core.proxies;

import bettertabs.handlers.TabHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import bettertabs.handlers.ConfigHandler;

public class ClientProxy extends CommonProxy
{
	@Override
	public boolean isClient()
	{
		return true;
	}
	
	@Override
	public void registerHandlers()
	{
		MinecraftForge.EVENT_BUS.register(TabHandler.class);
	}
	
	@Override
	public void registerConfig(FMLPreInitializationEvent event)
	{
		ConfigHandler.initConfig(event.getSuggestedConfigurationFile());
	}
}
