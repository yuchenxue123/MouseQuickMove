package cute.neko.project

import net.minecraft.Minecraft

/**
 * @author yuchenxue
 * @date 2025/07/17
 */

val mc: Minecraft
    inline get() = Minecraft.getMinecraft()

// GuiContainer.handleMouseClick(slot: Slot?, slotId: Int, mouseButton: Int, clickType: Int)
// mc.playerController.windowClick(windowId: Int, slotId: Int, mouseButton: Int, clickType: Int, mc.thePlayer)