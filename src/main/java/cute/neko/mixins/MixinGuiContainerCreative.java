package cute.neko.mixins;

import cute.neko.accessor.ContainerCreativeAccessor;
import net.minecraft.GuiContainerCreative;
import net.minecraft.Slot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

/**
 * @author yuchenxue
 * @date 2025/07/17
 */

@Mixin(GuiContainerCreative.class)
public abstract class MixinGuiContainerCreative implements ContainerCreativeAccessor {

    @Shadow
    protected abstract void handleMouseClick(Slot par1Slot, int par2, int par3, int par4);

    @Override
    public void neko$handleMouseClick(Slot slot, int slotId, int button, int type) {
        this.handleMouseClick(slot, slotId, button, type);
    }
}
