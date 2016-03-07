package com.pddstudio.openpocket.fragments;
/*
 * This Class was created by Patrick J
 * on 07.03.16. For more Details and licensing information
 * have a look at the README.md
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pddstudio.openpocket.R;
import com.pddstudio.pocketlibrary.enums.Month;

import io.inject.Injector;

public class OverviewFragment extends Fragment {

    public OverviewFragment() {}

    public static OverviewFragment newInstance(Month month) {
        OverviewFragment overviewFragment = new OverviewFragment();
        Bundle args = new Bundle();
        args.putString("Month", month.name());
        overviewFragment.setArguments(args);
        return overviewFragment;
    }

    private Month month;

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle savedInstance) {
        return layoutInflater.inflate(R.layout.fragment_overview, viewGroup, false);
    }

    @Override
    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        month = Month.valueOf(getArguments().getString("Month"));
    }

    public Month getMonth() {
        return month;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Injector.inject(this, view);
    }

}
