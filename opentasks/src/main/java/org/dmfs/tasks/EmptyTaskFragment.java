/*
 * Copyright 2017 dmfs GmbH
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

package org.dmfs.tasks;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.dmfs.android.bolts.color.Color;
import org.dmfs.android.bolts.color.colors.PrimaryColor;
import org.dmfs.android.retentionmagic.SupportFragment;
import org.dmfs.optional.Optional;
import org.dmfs.tasks.utils.bundle.OptionalColorArg;

import gk.android.investigator.Investigator;

import static org.dmfs.tasks.LogUtil.descOptColor;


/**
 * A fragment representing a single Task detail screen with empty content. It is used in {@link TaskListActivity} in
 * two-pane mode (on tablets) when no task is selected.
 *
 * @author Gabor Keszthelyi
 */
public class EmptyTaskFragment extends SupportFragment
{
    private static final String ARG_COLOR_HINT = "color_hint";

    private Color mColor;


    public EmptyTaskFragment()
    {
        Investigator.log(this);
    }


    /**
     * @param colorHint
     *         color that the toolbars should take. If absent, fallback value is the primary color.
     */
    public static Fragment newInstance(Optional<Color> colorHint)
    {
        EmptyTaskFragment fragment = new EmptyTaskFragment();
        if (colorHint.isPresent())
        {
            Bundle args = new Bundle();
            args.putInt(ARG_COLOR_HINT, colorHint.value().argb());
            fragment.setArguments(args);
        }
        Investigator.log(EmptyTaskFragment.class, "color", descOptColor(colorHint), "fragment", fragment);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.opentasks_fragment_empty_task, container, false);
        view.findViewById(R.id.empty_task_fragment_appbar).setBackgroundColor(mColor.argb());
        return view;
    }


    @Override
    public void onAttach(Activity activity)
    {
        super.onAttach(activity);

        mColor = new OptionalColorArg(ARG_COLOR_HINT, getArguments()).value(new PrimaryColor(getContext()));
        if (activity instanceof ViewTaskFragment.Callback)
        {
            ((ViewTaskFragment.Callback) activity).updateColor(mColor);
        }
    }
}
