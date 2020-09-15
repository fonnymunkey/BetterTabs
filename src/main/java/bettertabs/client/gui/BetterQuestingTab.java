package bettertabs.client.gui;

import org.lwjgl.opengl.GL11;

import betterquesting.api.storage.BQ_Settings;
import betterquesting.api2.client.gui.themes.gui_args.GArgsNone;
import betterquesting.api2.client.gui.themes.presets.PresetGUIs;
import betterquesting.client.gui2.GuiHome;
import betterquesting.client.themes.ThemeRegistry;
import bettertabs.core.BetterTabs;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;

public class BetterQuestingTab extends GuiButton {
	public ResourceLocation texture = new ResourceLocation("textures/gui/container/creative_inventory/tabs.png");
	public ResourceLocation icon = new ResourceLocation(BetterTabs.MODID, "textures/icons/bqbook.png");
	
	public BetterQuestingTab(int id, int x, int y, String text) {
		super(id, x, y, 28, 28, text);
	}
	
	@Override
	public void drawButton(Minecraft mc, int mouseX, int mouseY, float f) {
		enabled = !mc.player.getRecipeBook().isGuiOpen();
		if(!enabled) return;
		
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.renderEngine.bindTexture(texture);
		this.drawTexturedModalRect(x, y, 140, 0, 28, 28);
		mc.renderEngine.bindTexture(icon);
		this.drawTexturedModalRect(x + 6, y + 8, 0, 0, 16, 16);
		
		this.hovered = this.x<=mouseX&&mouseX<this.x+this.width && this.y<=mouseY&&mouseY<this.y+this.height;
		
		if(this.hovered) {
			//Fake tooltip code
			GlStateManager.disableRescaleNormal();
            RenderHelper.disableStandardItemLighting();
            GlStateManager.disableLighting();
            //GlStateManager.disableDepth(); Makes tooltip box fall behind tabs registered later if enabled
            String txt = I18n.format("bettertabs.tab.betterquesting", this.hovered);
            int i = mc.fontRenderer.getStringWidth(txt);

            int l1 = mouseX + 12;
            int i2 = mouseY - 12;
            int k = 8;

            if (l1 + i > mc.currentScreen.width)
            {
                l1 -= 28 + i;
            }

            if (i2 + k + 6 > mc.currentScreen.height)
            {
                i2 = mc.currentScreen.height - k - 6;
            }
            
            this.zLevel = 300.0F;
            
            this.drawGradientRect(l1 - 3, i2 - 4, l1 + i + 3, i2 - 3, -267386864, -267386864);
            this.drawGradientRect(l1 - 3, i2 + k + 3, l1 + i + 3, i2 + k + 4, -267386864, -267386864);
            this.drawGradientRect(l1 - 3, i2 - 3, l1 + i + 3, i2 + k + 3, -267386864, -267386864);
            this.drawGradientRect(l1 - 4, i2 - 3, l1 - 3, i2 + k + 3, -267386864, -267386864);
            this.drawGradientRect(l1 + i + 3, i2 - 3, l1 + i + 4, i2 + k + 3, -267386864, -267386864);
            this.drawGradientRect(l1 - 3, i2 - 3 + 1, l1 - 3 + 1, i2 + k + 3 - 1, 1347420415, 1344798847);
            this.drawGradientRect(l1 + i + 2, i2 - 3 + 1, l1 + i + 3, i2 + k + 3 - 1, 1347420415, 1344798847);
            this.drawGradientRect(l1 - 3, i2 - 3, l1 + i + 3, i2 - 3 + 1, 1347420415, 1347420415);
            this.drawGradientRect(l1 - 3, i2 + k + 2, l1 + i + 3, i2 + k + 3, 1344798847, 1344798847);

            this.zLevel = 0.0F;
            
            GlStateManager.disableDepth(); //lolwut text needs to be drawn without depth or else falls behind tooltip box when tooltip box is drawn correctly
            mc.fontRenderer.drawStringWithShadow(txt, (float)l1, (float)i2, -1);
            GlStateManager.enableDepth();
            
            //GlStateManager.enableLighting(); //Why do these two cause other tabs to randomly grey out? Dumby dumb gui code
            //RenderHelper.enableStandardItemLighting();
            
            GlStateManager.enableRescaleNormal();
		}
	}
	
	
	@Override
	public boolean mousePressed(Minecraft mc, int mouseX, int mouseY) {
		if(this.hovered && enabled) {
    		if(BQ_Settings.useBookmark && GuiHome.bookmark != null) 
    			mc.displayGuiScreen(GuiHome.bookmark);
			else 
				mc.displayGuiScreen(ThemeRegistry.INSTANCE.getGui(PresetGUIs.HOME, GArgsNone.NONE));
		}
		return this.hovered;
	}
}
