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

package xyz.qalcyo.requisite.cosmetics;

import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import xyz.qalcyo.requisite.core.cosmetics.CosmeticManager;
import xyz.qalcyo.requisite.core.cosmetics.ICosmetic;
import xyz.qalcyo.requisite.core.cosmetics.ICosmeticRenderer;

import java.util.List;

public class CosmeticRenderer implements LayerRenderer<AbstractClientPlayer>, ICosmeticRenderer<AbstractClientPlayer> {

    private CosmeticManager<AbstractClientPlayer> cosmeticManager;
    private List<ICosmetic<AbstractClientPlayer>> cosmetics;

    public void doRenderLayer(AbstractClientPlayer player, float limbSwing, float limbSwingAmount, float partialTicks, float tickAge, float netHeadYaw, float netHeadPitch, float scale) {
        if (cosmetics != null) {
            for (ICosmetic<AbstractClientPlayer> cosmetic : cosmetics) {
                cosmetic.render(cosmeticManager, player, partialTicks);
            }
        }
    }

    public boolean shouldCombineTextures() {
        return false;
    }

    public void initialize(CosmeticManager<AbstractClientPlayer> cosmeticManager, List<ICosmetic<AbstractClientPlayer>> cosmetics) {
        this.cosmeticManager = cosmeticManager;
        this.cosmetics = cosmetics;
    }

}