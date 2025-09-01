package cute.neko.mixins;

import com.llamalad7.mixinextras.sugar.Local;
import cute.neko.accessor.MousePositionAccessor;
import cute.neko.event.EventManager;
import cute.neko.project.MouseButton;
import cute.neko.project.events.KeyboardInputEvent;
import cute.neko.project.events.MouseInputEvent;
import cute.neko.project.events.ScreenDrawEvent;
import net.minecraft.GuiScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @author yuchenxue
 * @date 2025/07/16
 */

@Mixin(GuiScreen.class)
public class MixinGuiScreen implements MousePositionAccessor {

    @Shadow
    private int eventButton;

    @Unique
    protected int neko$mouseX;
    @Unique
    protected int neko$mouseY;

    @Inject(method = "drawScreen", at = @At(value = "HEAD"))
    private void hookUpdateMousePosition(int mouseX, int mouseY, float deltaTime, CallbackInfo ci) {
        this.neko$mouseX = mouseX;
        this.neko$mouseY = mouseY;

        EventManager.INSTANCE.callEvent(new ScreenDrawEvent(mouseX, mouseY));
    }

    @Inject(method = "handleMouseInput",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/GuiScreen;mouseClicked(III)V",
                    shift = At.Shift.BEFORE
            )
    )
    private void hookMouseInputEventBeforeScreenMouseClicked(CallbackInfo ci, @Local(ordinal = 0) int mouseX, @Local(ordinal = 1) int mouseY) {
        EventManager.INSTANCE.callEvent(new MouseInputEvent(mouseX, mouseY, MouseButton.Companion.parse(this.eventButton), MouseInputEvent.Type.CLICKED));
    }

    @Inject(method = "handleMouseInput",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/GuiScreen;mouseMovedOrUp(III)V",
                    shift = At.Shift.BEFORE
            )
    )
    private void hookMouseInputEventBeforeScreenMouseRelease(CallbackInfo ci, @Local(ordinal = 0) int mouseX, @Local(ordinal = 1) int mouseY) {
        EventManager.INSTANCE.callEvent(new MouseInputEvent(mouseX, mouseY, MouseButton.Companion.parse(this.eventButton), MouseInputEvent.Type.RELEASED));
    }

    @Inject(method = "handleMouseInput",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/GuiScreen;mouseClickMove(IIIJ)V",
                    shift = At.Shift.BEFORE
            )
    )
    private void hookMouseInputEventBeforeScreenMouseClickMove(CallbackInfo ci, @Local(ordinal = 0) int mouseX, @Local(ordinal = 1) int mouseY) {
        EventManager.INSTANCE.callEvent(new MouseInputEvent(mouseX, mouseY, MouseButton.Companion.parse(this.eventButton), MouseInputEvent.Type.CLICK_MOVE));
    }

    @Inject(method = "handleKeyboardInput",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/GuiScreen;keyTyped(CI)V",
                    shift = At.Shift.AFTER
            )
    )
    private void hookKeyboardInputEvent(CallbackInfo ci, @Local(ordinal = 0) char keyChar, @Local(ordinal = 0) int keyCode) {
        EventManager.INSTANCE.callEvent(new KeyboardInputEvent(keyChar, keyCode));
    }

    @Override
    public int neko$getMouseX() {
        return this.neko$mouseX;
    }

    @Override
    public int neko$getMouseY() {
        return this.neko$mouseY;
    }
}
