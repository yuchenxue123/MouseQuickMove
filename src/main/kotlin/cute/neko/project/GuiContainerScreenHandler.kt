package cute.neko.project

import net.minecraft.GuiContainer
import net.minecraft.Slot

/**
 * @author yuchenxue
 * @date 2025/07/17
 */

open class GuiContainerScreenHandler(
    val container: GuiContainer
) : AbstractContainerScreenHandler() {

    override fun getOverSlot(): Slot? {
        return container.getOverSlot()
    }

    override fun getOverSlot(mouseX: Int, mouseY: Int): Slot? {
        return container.getOverSlot(mouseX, mouseY)
    }

    override fun quick(slot: Slot) {
        if (isIgnored(slot)) {
            return
        }

        mc.playerController.windowClick(
            container.inventorySlots.windowId,
            slot.slotNumber,
            // mouse button
            0,
            // click type
            1,
            mc.thePlayer,
        )
    }

    override fun isIgnored(slot: Slot): Boolean {
        return false
    }

}