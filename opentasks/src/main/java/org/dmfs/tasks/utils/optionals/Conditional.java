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

package org.dmfs.tasks.utils.optionals;

import org.dmfs.jems.predicate.Predicate;
import org.dmfs.jems.single.Single;
import org.dmfs.jems.single.elementary.Frozen;
import org.dmfs.jems.single.elementary.ValueSingle;
import org.dmfs.optional.Optional;

import java.util.NoSuchElementException;


/**
 * {@link Optional} that is present if the given value fulfils the provided predicate.
 *
 * @author Gabor Keszthelyi
 * @deprecated TODO use from jems when available
 */
@Deprecated
public final class Conditional<T> implements Optional<T>
{
    private final Predicate<T> mPredicate;
    private final Single<T> mValue;


    public Conditional(Predicate<T> predicate, Single<T> value)
    {
        mPredicate = predicate;
        mValue = new Frozen<>(value);
    }


    public Conditional(Predicate<T> predicate, T value)
    {
        this(predicate, new ValueSingle<>(value));
    }


    @Override
    public boolean isPresent()
    {
        return mPredicate.satisfiedBy(mValue.value());
    }


    @Override
    public T value(T defaultValue)
    {
        return isPresent() ? value() : defaultValue;
    }


    @Override
    public T value() throws NoSuchElementException
    {
        if (isPresent())
        {
            return mValue.value();
        }
        throw new NoSuchElementException(String.format(
                "The value '%s' does not fulfil the predicate, hence it is considered absent.", mValue.value()));
    }
}
