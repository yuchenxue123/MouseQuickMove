package cute.neko.project.events

import cute.neko.event.Event

/**
 * @author yuchenxue
 * @date 2025/07/16
 */

class MouseInputEvent(
    val mouseX: Int,
    val mouseY: Int,
    val button: Int,
    val type: Type
) : Event {

    enum class Type {
        CLICKED,
        RELEASED,
        CLICK_MOVE
    }
}