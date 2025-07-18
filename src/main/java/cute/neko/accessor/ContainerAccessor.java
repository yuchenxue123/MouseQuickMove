package cute.neko.accessor;

import net.minecraft.Slot;

/**
 * @author yuchenxue
 * @date 2025/07/17
 */

public interface ContainerAccessor {

    Slot neko$getOverSlot();

    Slot neko$getOverSlot(int mouseX, int mouseY);

}
