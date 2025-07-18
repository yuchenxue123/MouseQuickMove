package cute.neko.project

import cute.neko.accessor.ContainerAccessor
import cute.neko.accessor.ContainerCreativeAccessor
import cute.neko.accessor.MousePositionAccessor
import net.minecraft.GuiContainer
import net.minecraft.GuiContainerCreative
import net.minecraft.GuiScreen
import net.minecraft.Slot

/**
 * @author yuchenxue
 * @date 2025/07/17
 */

fun GuiContainer.getOverSlot(): Slot? {
    return (this as ContainerAccessor).`neko$getOverSlot`()
}

fun GuiContainer.getOverSlot(mouseX: Int, mouseY: Int): Slot? {
    return (this as ContainerAccessor).`neko$getOverSlot`(mouseX, mouseY)
}

fun GuiContainerCreative.handleMouseClick(slot: Slot, slotId: Int, button: Int, type: Int) {
    (this as ContainerCreativeAccessor).`neko$handleMouseClick`(slot, slotId, button, type)
}

val GuiScreen.mouseX
    get() = (this as MousePositionAccessor).`neko$getMouseX`()

val GuiScreen.mouseY
    get() = (this as MousePositionAccessor).`neko$getMouseY`()