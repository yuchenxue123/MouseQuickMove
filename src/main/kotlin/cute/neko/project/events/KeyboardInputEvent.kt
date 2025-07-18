package cute.neko.project.events

import cute.neko.event.CancellableEvent

/**
 * @author yuchenxue
 * @date 2025/07/16
 */

class KeyboardInputEvent(
    val char: Char,
    val code: Int
) : CancellableEvent()