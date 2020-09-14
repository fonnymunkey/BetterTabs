package bettertabs.handlers;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

public class ConfigHandler {

	public static Configuration config;
	
	public static boolean bqTabEnabled = true;
	public static boolean hatsTabEnabled = true;
	public static boolean lurTabEnabled = true;
	public static boolean lulTabEnabled = true;
	
	public static boolean lycaniteTooltipEnabled = true;
	
	public static int bqXOffset = -81;
	public static int luYOffset = -18;
	public static int hatsXOffset = -61;
	public static int hatsYOffset = -74;
	
	public static void initConfig(File configFile)
	{
		config = new Configuration(configFile);
		
		bqTabEnabled = config.getBoolean("bqTabEnabled", "Tabs", bqTabEnabled, "Enable BetterQuesting tab?");
		hatsTabEnabled = config.getBoolean("hatsTabEnabled", "Tabs", hatsTabEnabled, "Enable ClassyHats tab?");
		lurTabEnabled = config.getBoolean("lurTabEnabled", "Tabs", lurTabEnabled, "Enable LevelUpReloaded tab?");
		lulTabEnabled = config.getBoolean("lulTabEnabled", "Tabs", lulTabEnabled, "Enable LevelUpLegacy tab?");
		
		lycaniteTooltipEnabled = config.getBoolean("lycaniteTooltipEnabled", "Tooltips", lycaniteTooltipEnabled, "Enable LycanitesMobs bestiary tooltip?");
		
		bqXOffset = config.getInt("bqXOffset", "Offsets", bqXOffset, -500, 500, "X Offset for BetterQuesting tab");
		luYOffset = config.getInt("luYOffset", "Offsets", luYOffset, -500, 500, "Y Offset for LevelUp tabs");
		hatsXOffset = config.getInt("hatsXOffset", "Offsets", hatsXOffset, -500, 500, "X Offset for ClassyHats tab");
		hatsYOffset = config.getInt("hatsYOffset", "Offsets", hatsYOffset, -500, 500, "y Offset for ClassyHats tab");
		
		if(config.hasChanged()) {
			config.save();
		}
	}
}
