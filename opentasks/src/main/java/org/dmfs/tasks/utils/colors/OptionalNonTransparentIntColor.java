/*
 * Copyright 2018 dmfs GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.dmfs.tasks.utils.colors;

import android.support.annotation.ColorInt;

import org.dmfs.android.bolts.color.Color;
import org.dmfs.android.bolts.color.elementary.ValueColor;
import org.dmfs.jems.optional.decorators.Mapped;
import org.dmfs.optional.Optional;
import org.dmfs.optional.decorators.DelegatingOptional;
import org.dmfs.tasks.utils.optionals.Conditional;


/**
 * An {@link Optional} {@link Color} taking in a primitive {@code int} {@link ColorInt} that represents absent value
 * when it is {@link android.graphics.Color#TRANSPARENT}.
 *
 * @author Gabor Keszthelyi
 */
public final class OptionalNonTransparentIntColor extends DelegatingOptional<Color>
{
    public OptionalNonTransparentIntColor(@ColorInt int colorInt)
    {
        super(new Mapped<>(ValueColor::new, new Conditional<>(i -> i != android.graphics.Color.TRANSPARENT, colorInt)));
    }
}
