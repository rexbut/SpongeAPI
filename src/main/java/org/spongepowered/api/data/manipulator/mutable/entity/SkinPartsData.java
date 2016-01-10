/*
 * This file is part of SpongeAPI, licensed under the MIT License (MIT).
 *
 * Copyright (c) SpongePowered <https://www.spongepowered.org>
 * Copyright (c) contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.spongepowered.api.data.manipulator.mutable.entity;

import org.spongepowered.api.data.manipulator.DataManipulator;
import org.spongepowered.api.data.manipulator.immutable.entity.ImmutableSkinPartsData;
import org.spongepowered.api.data.type.SkinPart;
import org.spongepowered.api.data.value.mutable.SetValue;
import org.spongepowered.api.entity.living.Humanoid;

/**
 * Represents the {@link SkinPart}s being displayed for a {@link Humanoid}.
 *
 * <p>The displayed {@link SkinPart}s determine which parts of a {@link Humanoid}'s skin
 * (hat, arm, etc) are rendered.</p>
 */
public interface SkinPartsData extends DataManipulator<SkinPartsData, ImmutableSkinPartsData> {

    /**
     * Gets the {@link SetValue} for the {@link SkinPart}s to display.
     *
     * @return The {@link SetValue} for the {@link SkinPart}s to display.
     */
    SetValue<SkinPart> skinParts();

}
