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

import org.dmfs.optional.decorators.DelegatingOptional;


/**
 * An optionally present integer argument in a {@link Bundle}.
 *
 * @author Gabor Keszthelyi
 */
public final class OptionalIntArg extends DelegatingOptional<Integer>
{
    public OptionalIntArg(@Nullable Bundle bundle, @NonNull String key)
    {
        super(new OptionalArg<>(bundle, key,
                // Note: for some reason Lint gives error on 'Bundle::getInt' method reference here
                (b, k) -> b.getInt(k)));
    }
}
