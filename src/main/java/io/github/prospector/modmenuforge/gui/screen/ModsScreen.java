package io.github.prospector.modmenuforge.gui.screen;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.text.TranslationTextComponent;

public class ModsScreen extends Screen {

	private final Screen parentScreen;

	public ModsScreen(final Screen parentScreen) {
		super(new TranslationTextComponent("modmenu.title"));
		this.parentScreen = parentScreen;
	}

	@Override
	public void closeScreen() {
		this.minecraft.displayGuiScreen(this.parentScreen);
	}

}
