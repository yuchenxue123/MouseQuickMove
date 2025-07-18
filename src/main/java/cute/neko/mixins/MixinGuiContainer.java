package cute.neko.mixins;

import cute.neko.accessor.ContainerAccessor;
import net.minecraft.GuiContainer;
import net.minecraft.Slot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

/**
 * @author yuchenxue
 * @date 2025/07/17
 */

@Mixin(GuiContainer.class)
public abstract class MixinGuiContainer extends MixinGuiScreen implements ContainerAccessor {

    @Shadow
    protected abstract Slot getSlotAtPosition(int par1, int par2);

    @Override
    public Slot neko$getOverSlot() {
        return this.getSlotAtPosition(this.neko$mouseX, this.neko$mouseY);
    }

    @Override
    public Slot neko$getOverSlot(int mouseX, int mouseY) {
        return this.getSlotAtPosition(mouseX, mouseY);
    }
}
