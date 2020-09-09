package bettertabs.client.gui;

import org.lwjgl.opengl.GL11;

import bettertabs.core.BetterTabs;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.ResourceLocation;

public class SkillTab extends GuiButton {
	public ResourceLocation texture = new ResourceLocation(BetterTabs.MODID, "textures/icons/tabback.png");
	public ResourceLocation icon = new ResourceLocation(BetterTabs.MODID, "textures/icons/levelupbook.png");
	
	public SkillTab(int id, int x, int y, String text) {
		super(id, x, y, 28, 32, text);
	}
	
	@Override
	public void drawButton(Minecraft minecraft, int mouseX, int mouseY, float f) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		minecraft.renderEngine.bindTexture(texture);
		this.drawTexturedModalRect(x, y, 0, 0, 26, 28);
		minecraft.renderEngine.bindTexture(icon);
		this.drawTexturedModalRect(x + 7, y + 6, 0, 0, 16, 16);
	}
}
