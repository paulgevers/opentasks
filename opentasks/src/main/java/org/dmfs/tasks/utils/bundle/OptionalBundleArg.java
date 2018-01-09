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

package org.dmfs.tasks.utils.bundle;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import org.dmfs.jems.function.BiFunction;
import org.dmfs.jems.optional.decorators.Mapped;
import org.dmfs.optional.NullSafe;
import org.dmfs.optional.decorators.DelegatingOptional;
import org.dmfs.optional.decorators.Filtered;


/**
 * @author Gabor Keszthelyi
 */
// TODO Remove this and all classes in this package when solution from boxed-bolts library is available
public final class OptionalBundleArg<T> extends DelegatingOptional<T>
{
    public OptionalBundleArg(@Nullable Bundle bundle,
                             @NonNull String key,
                             @NonNull BiFunction<Bundle, String, T> getFunction)
    {
        super(new Mapped<>(b -> getFunction.value(b, key),
                new Filtered<>(b -> b.containsKey(key),
                        new NullSafe<>(bundle))));
    }
}
