package cute.neko.project

import net.minecraft.Slot

/**
 * @author yuchenxue
 * @date 2025/07/17
 */

abstract class AbstractContainerScreenHandler : ScreenHandler {

    abstract fun quick(slot: Slot)

    abstract fun getOverSlot(): Slot?

    abstract fun getOverSlot(mouseX: Int, mouseY: Int): Slot?

}