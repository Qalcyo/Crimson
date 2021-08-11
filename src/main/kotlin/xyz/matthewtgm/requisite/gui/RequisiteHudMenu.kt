package xyz.matthewtgm.requisite.gui

import gg.essential.elementa.WindowScreen
import gg.essential.universal.GuiScale
import net.minecraft.client.Minecraft
import net.minecraft.client.renderer.GlStateManager
import xyz.matthewtgm.requisite.Requisite
import xyz.matthewtgm.requisite.hud.HudElementBase
import java.util.*

class RequisiteHudMenu : WindowScreen(
    drawDefaultBackground = false,
    newGuiScale = GuiScale.scaleForScreenSize().ordinal
) {

    private var dragging = false
    private var selected = Optional.empty<HudElementBase>()

    private var prevX: Int? = null
    private var prevY: Int? = null

    init {
        window.onMouseClick { event ->
            prevX = event.absoluteX.toInt()
            prevY = event.absoluteY.toInt()
            selected = Requisite.getManager().hudManager.elements.stream().filter {
                it.isMouseInside(event.absoluteX.toInt(), event.absoluteY.toInt())
            }.findFirst()

            if (selected.isPresent)
                dragging = true
        }
        window.onMouseRelease {
            dragging = false
        }
        window.onMouseDrag { mouseX, mouseY, _ ->
            if (selected.isPresent && dragging) {
                val element = selected.get()
                val position = element.position.get()
                position.setPosition(position.x + mouseX.toInt() - prevX!!, position.y + mouseY.toInt() - prevY!!)
                element.position.set(position)
            }

            prevX = mouseX.toInt()
            prevY = mouseY.toInt()
        }

        /*val background = UIBlock().constrain {
            width = 100.percent()
            height = 100.percent()
        } childOf window*/
    }

    override fun onDrawScreen(mouseX: Int, mouseY: Int, partialTicks: Float) {
        super.onDrawScreen(mouseX, mouseY, partialTicks)

        GlStateManager.scale(1.0, 1.0, 1.0)
        Requisite.getManager().hudManager.render(partialTicks)
    }

    override fun onScreenClose() {
        super.onScreenClose()
        Requisite.getManager().hudManager.save()
    }

    /* Ensure correct GUI scale. */
    override fun setWorldAndResolution(mc: Minecraft?, width: Int, height: Int) {
        newGuiScale = GuiScale.scaleForScreenSize().ordinal
        super.setWorldAndResolution(mc, width, height)
    }
}
