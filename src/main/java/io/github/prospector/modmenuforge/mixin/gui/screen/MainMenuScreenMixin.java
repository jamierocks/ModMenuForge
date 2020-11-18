package io.github.prospector.modmenuforge.mixin.gui.screen;

import io.github.prospector.modmenuforge.gui.screen.ModsScreen;
import net.minecraft.client.gui.screen.MainMenuScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.Slice;

@Mixin(MainMenuScreen.class)
public class MainMenuScreenMixin extends Screen {

	MainMenuScreenMixin(final ITextComponent titleIn) {
		super(titleIn);
	}

	@Redirect(
			method = "init",
			slice = @Slice(
					from = @At(
							value = "INVOKE",
							target = "Lnet/minecraft/client/gui/screen/MainMenuScreen;addSingleplayerMultiplayerButtons(II)V"
					),
					to = @At(
							value = "INVOKE",
							target = "Lnet/minecraftforge/client/gui/NotificationModUpdateScreen;init(Lnet/minecraft/client/gui/screen/MainMenuScreen;Lnet/minecraft/client/gui/widget/button/Button;)Lnet/minecraftforge/client/gui/NotificationModUpdateScreen;"
					)
			),
			at = @At(
					value = "INVOKE",
					target = "Lnet/minecraft/client/gui/screen/MainMenuScreen;addButton(Lnet/minecraft/client/gui/widget/Widget;)Lnet/minecraft/client/gui/widget/Widget;"
			)
	)
	private Widget useModMenuScreen(final MainMenuScreen mainMenuScreen, final Widget button) {
		return this.addButton(new Button(
				this.width / 2 - 100, this.height / 4 + 48 + 24 * 2,
				98, 20,
				new TranslationTextComponent("modmenu.title"),
				b -> {
					this.minecraft.displayGuiScreen(new ModsScreen(this));
				}
		));
	}

}
