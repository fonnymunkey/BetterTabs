package bettertabs.core.proxies;

import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy
{
	public boolean isClient()
	{
		return false;
	}
	
	public void registerHandlers()
	{
	}
	
	public void registerConfig(FMLPreInitializationEvent event)
	{
	}
}