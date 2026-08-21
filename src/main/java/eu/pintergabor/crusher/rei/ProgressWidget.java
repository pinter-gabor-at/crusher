/*
 * This file is licensed under the MIT License, part of Roughly Enough Items.
 * Copyright (c) 2018, 2019, 2020, 2021, 2022, 2023 shedaniel
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package eu.pintergabor.crusher.rei;

import java.util.Collections;
import java.util.List;

import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.REIRuntime;
import me.shedaniel.rei.api.client.gui.compat.GuiGraphics;
import me.shedaniel.rei.api.client.gui.widgets.BurningFire;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Unmodifiable;
import org.jspecify.annotations.NonNull;

import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.resources.Identifier;


public final class ProgressWidget extends BurningFire {
	private final Rectangle bounds;
	private double animationDuration = 10000;
	private final Identifier background;
	private final Identifier litProgressSprite;

	public ProgressWidget(
		final @NonNull Rectangle bounds,
		final @NonNull Identifier background,
		final @NonNull Identifier litProgressSprite
	) {
		this.bounds = new Rectangle(bounds);
		this.background = background;
		this.litProgressSprite = litProgressSprite;
	}

	@Override
	public double getAnimationDuration() {
		return animationDuration;
	}

	@Override
	public void setAnimationDuration(double animationDurationMS) {
		if (0 < animationDurationMS) {
			this.animationDuration = animationDurationMS;
		}
	}

	@Override
	public Rectangle getBounds() {
		return bounds;
	}

	@Override
	public void render(GuiGraphics graphics, int mouseX, int mouseY, float delta) {
		final boolean dark = REIRuntime.getInstance().isDarkThemeEnabled();
		final int h = 14 - (int) (System.currentTimeMillis() / ((long) animationDuration / 14) % 14);
		// If not in dark mode then
		// draw the shadow image of the progress indicator extracted from the GUI.
		if (!dark) {
			graphics.blit(
				RenderPipelines.GUI_TEXTURED,
				background,
				getX(), getY(),
				56, 36,
				14, 14 - h,
				176, 166
			);
		}
		// Draw the prograss image.
		graphics.blitSprite(
			RenderPipelines.GUI_TEXTURED,
			litProgressSprite,
			14, 14,
			0, 14 - h,
			getX(), getY() + 14 - h,
			14, h
		);
	}

	@Contract(pure = true)
	@Override
	public @NonNull @Unmodifiable List<? extends GuiEventListener> children() {
		return Collections.emptyList();
	}
}
