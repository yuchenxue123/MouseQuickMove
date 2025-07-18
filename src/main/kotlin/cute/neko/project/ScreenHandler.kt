package cute.neko.project

import net.minecraft.Slot

/**
 * @author yuchenxue
 * @date 2025/07/17
 */

interface ScreenHandler {

    fun isIgnored(slot: Slot): Boolean

}