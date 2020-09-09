package bettertabs.handlers;

import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import betterquesting.api.storage.BQ_Settings;
import betterquesting.api2.client.gui.themes.gui_args.GArgsNone;
import betterquesting.api2.client.gui.themes.presets.PresetGUIs;
import betterquesting.client.gui2.GuiHome;
import betterquesting.client.themes.ThemeRegistry;
import bettertabs.client.gui.QuestTab;
import levelup2.LevelUp2;
import levelup2.gui.GuiSkills;
import levelup2.gui.GuiSpecialization;
import levelup2.skills.SkillRegistry;
import bettertabs.client.gui.SkillTab;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.client.event.GuiScreenEvent;

public class TabHandler
{
    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public static void inventoryInit(GuiScreenEvent.InitGuiEvent.Post event)
    {
    	if(event.getGui() instanceof GuiInventory) {
    		if(Loader.isModLoaded("betterquesting")) 
    			event.getButtonList().add(new QuestTab(69420, (event.getGui().width/2)-80, (event.getGui().height/2)-111, ""));
    		if(Loader.isModLoaded("levelup2")) 
    			event.getButtonList().add(new SkillTab(42069, (event.getGui().width/2)-114, (event.getGui().height/2)-18, ""));
    	}
    }
    
    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public static void guiAction(GuiScreenEvent.ActionPerformedEvent.Pre event) {
    	Minecraft mc = Minecraft.getMinecraft();
    	
    	//From BetterQuesting
    	if (event.getButton() instanceof QuestTab) {
    		if(BQ_Settings.useBookmark && GuiHome.bookmark != null) 
    			mc.displayGuiScreen(GuiHome.bookmark);
			else 
				mc.displayGuiScreen(ThemeRegistry.INSTANCE.getGui(PresetGUIs.HOME, GArgsNone.NONE));
    	}

    	//From LevelUpReloaded
    	if (event.getButton() instanceof SkillTab) {
    		if (!SkillRegistry.getPlayer(LevelUp2.proxy.getPlayer()).hasClass() && LevelUp2.proxy.getPlayer().experienceLevel > 4) 
    			mc.displayGuiScreen(new GuiSpecialization());
    		else if (SkillRegistry.getPlayer(LevelUp2.proxy.getPlayer()).hasClass()) 
    			mc.displayGuiScreen(new GuiSkills());
    		else {
    			mc.displayGuiScreen(null);
    			mc.player.sendStatusMessage(new TextComponentTranslation("level.invalid"), true);
    		}
    	}
    }
    
    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public static void renderTick(GuiScreenEvent.DrawScreenEvent.Post event) {
    	if(event.getGui() instanceof GuiInventory) {
    		int mouseY = event.getMouseY();
    		int mouseX = event.getMouseX();
    		
    		if(Loader.isModLoaded("betterquesting")) {
        		int xMinBQ = (event.getGui().width/2)-81;
        		int yMinBQ = (event.getGui().height/2)-111;
        		if(xMinBQ<mouseX && mouseX<xMinBQ+29 && yMinBQ<mouseY && mouseY<yMinBQ+28) {
        			event.getGui().drawHoveringText("Better Questing", mouseX, mouseY);
        		}
    		}
    		
    		if(Loader.isModLoaded("levelup2")) {
        		int xMinLUR = (event.getGui().width/2)-115;
        		int yMinLUR = (event.getGui().height/2)-19;
        		if(xMinLUR<mouseX && mouseX<xMinLUR+27 && yMinLUR<mouseY && mouseY<yMinLUR+29) {
        			event.getGui().drawHoveringText("LevelUp Reloaded", mouseX, mouseY);
        		}
    		}
    		
    		if(Loader.isModLoaded("lycanitesmobs")) {
        		int xMinLY = (event.getGui().width/2)+55;
        		int yMinLY = (event.getGui().height/2)-110;
        		if(xMinLY<mouseX && mouseX<xMinLY+30 && yMinLY<mouseY && mouseY<yMinLY+27) {
        			event.getGui().drawHoveringText("Lycanite's Mobs", mouseX, mouseY);
        		}
    		}
    	}
    }
}