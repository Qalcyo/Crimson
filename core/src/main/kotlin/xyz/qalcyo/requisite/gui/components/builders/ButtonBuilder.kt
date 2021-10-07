/*
 * Requisite - Minecraft library mod
 *  Copyright (C) 2021 Qalcyo
 *
 * Requisite is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 2.1 of the License, or
 * (at your option) any later version.
 *
 * Requisite is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Requisite. If not, see <https://www.gnu.org/licenses/>.
 */

package xyz.qalcyo.requisite.gui.components.builders

import gg.essential.elementa.UIComponent
import xyz.qalcyo.requisite.gui.components.Button
import xyz.qalcyo.requisite.gui.components.factory.IComponentFactory

/**
 * A builder which makes it easier to make Button instances.
 */
class ButtonBuilder(
    var action: Button.() -> Unit,
    var text: String,
    var toggled: Boolean = true
) {
    fun build(factory: IComponentFactory): UIComponent = factory.build(this)
}