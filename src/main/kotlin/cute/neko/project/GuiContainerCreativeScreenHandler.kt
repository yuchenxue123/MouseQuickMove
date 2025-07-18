package cute.neko.project

import net.minecraft.GuiContainerCreative
import net.minecraft.Slot
import net.minecraft.SlotCreativeInventory

/**
 * @author yuchenxue
 * @date 2025/07/17
 */

class GuiContainerCreativeScreenHandler(
    container: GuiContainerCreative,
) : GuiContainerScreenHandler(container) {

    override fun quick(slot: Slot) {
        if(isIgnored(slot)) {
            return
        }

        (container as GuiContainerCreative)
            .handleMouseClick(slot, slot.slotNumber, 0, 1)
    }

    override fun isIgnored(slot: Slot): Boolean {
        // maybe improved
        return slot !is SlotCreativeInventory
    }

}