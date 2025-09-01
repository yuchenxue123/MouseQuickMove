package cute.neko.project

import cute.neko.event.EventListener
import cute.neko.event.handler
import cute.neko.project.events.KeyboardInputEvent
import cute.neko.project.events.MouseInputEvent
import cute.neko.project.events.MouseInputEvent.Type.*
import cute.neko.project.events.ScreenDrawEvent
import net.fabricmc.api.ModInitializer
import net.minecraft.GuiContainer
import net.minecraft.GuiContainerCreative
import net.minecraft.Minecraft
import org.apache.logging.log4j.LogManager
import org.lwjgl.input.Keyboard

/**
 * @author yuchenxue
 * @date 2025/07/16
 */

class MouseQuickMove : ModInitializer, EventListener {

    val LOGGER = LogManager.getLogger(MouseQuickMove::class.java)

    override fun onInitialize() {
    }

    @Suppress("unused")
    private val screenDrawHandler = handler<ScreenDrawEvent> { event ->
        if (dragging) {
            val screenHandler = when (val screen = mc.currentScreen) {
                is GuiContainerCreative -> GuiContainerCreativeScreenHandler(screen)
                is GuiContainer -> GuiContainerScreenHandler(screen)
                else -> return@handler
            }

            val slot = screenHandler.getOverSlot(event.mouseX, event.mouseY) ?: return@handler

            if (shifted) {
                screenHandler.quick(slot)
            }
        }
    }

    private var dragging = false

    @Suppress("unused")
    private val mouseInputHandler = handler<MouseInputEvent> { event ->

        when (event.type) {
            CLICKED -> {
                if (event.button == MouseButton.LEFT) {
                    dragging = true
                }
            }

            RELEASED -> {
                dragging = false
            }

            CLICK_MOVE -> {

            }
        }
    }

    private var shifted = false

    @Suppress("unused")
    private val keyboardInputHandler = handler<KeyboardInputEvent> { event ->
        val mc = Minecraft.getMinecraft()

        val screen = mc.currentScreen ?: return@handler

        if (screen !is GuiContainer) return@handler

        shifted = event.code == Keyboard.KEY_LSHIFT || event.code == Keyboard.KEY_RSHIFT
    }
}