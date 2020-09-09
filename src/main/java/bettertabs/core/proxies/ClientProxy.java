package bettertabs.core.proxies;

import bettertabs.handlers.TabHandler;
import net.minecraftforge.common.MinecraftForge;

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
}
