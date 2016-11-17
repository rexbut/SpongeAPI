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
package org.spongepowered.api.entity;

import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.data.manipulator.mutable.entity.AreaCloudData;
import org.spongepowered.api.data.value.mutable.ListValue;
import org.spongepowered.api.data.value.mutable.Value;
import org.spongepowered.api.effect.particle.ParticleType;
import org.spongepowered.api.effect.potion.PotionEffect;
import org.spongepowered.api.util.Color;

public interface AreaEffectCloud extends Entity {

    default AreaCloudData getOcelotData() {
        return get(AreaCloudData.class).get();
    }

    default Value<Integer> duration() {
        return getValue(Keys.AREA_CLOUD_DURATION).get();
    }

    default Value<Integer> durationOnUse() {
        return getValue(Keys.AREA_CLOUD_DURATION_ON_USE).get();
    }

    default Value<ParticleType> particle() {
        return getValue(Keys.AREA_CLOUD_PARTICLE_TYPE).get();
    }

    default Value<Float> radius() {
        return getValue(Keys.AREA_CLOUD_RADIUS).get();
    }

    default Value<Float> radiusOnUse() {
        return getValue(Keys.AREA_CLOUD_RADIUS_ON_USE).get();
    }

    default Value<Float> radiusPerTick() {
        return getValue(Keys.AREA_CLOUD_RADIUS_PER_TICK).get();
    }

    default Value<Integer> reapplicationDelay() {
        return getValue(Keys.AREA_CLOUD_REAPPLICATION_DELAY).get();
    }

    default Value<Integer> waitTime() {
        return getValue(Keys.AREA_CLOUD_WAIT_TIME).get();
    }

    default Value<Color> color() {
        return getValue(Keys.COLOR).get();
    }

    default ListValue<PotionEffect> effects() {
        return getValue(Keys.POTION_EFFECTS).get();
    }
}
