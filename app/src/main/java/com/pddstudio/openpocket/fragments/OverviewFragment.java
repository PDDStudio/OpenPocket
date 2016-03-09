package com.pddstudio.openpocket.fragments;
/*
 * This Class was created by Patrick J
 * on 07.03.16. For more Details and licensing information
 * have a look at the README.md
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mikepenz.fastadapter.adapters.FastItemAdapter;
import com.mikepenz.iconics.context.IconicsLayoutInflater;
import com.pddstudio.openpocket.R;
import com.pddstudio.pocketlibrary.enums.Month;

import io.inject.InjectView;
import io.inject.Injector;

public class OverviewFragment extends Fragment {

    @InjectView(R.id.recyclerView) private RecyclerView recyclerView;
    private FastItemAdapter fastItemAdapter;
    private RecyclerView.LayoutManager layoutManager;
    //TODO: add lookup functionality as soon as it is implemented in backend
    private boolean recordsFound = false;

    public OverviewFragment() {}

    public static OverviewFragment newInstance(Month month) {
        OverviewFragment overviewFragment = new OverviewFragment();
        Bundle args = new Bundle();
        args.putString("monthName", month.getMonthName());
        overviewFragment.setArguments(args);
        return overviewFragment;
    }

    private String monthName;

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle savedInstance) {
        return recordsFound ?
                layoutInflater.inflate(R.layout.fragment_overview, viewGroup, false) :
                layoutInflater.inflate(R.layout.fragment_overview_empty, viewGroup, false);
    }

    @Override
    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        monthName = getArguments().getString("monthName");
    }

    public String getMonthName() {
        return monthName;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Injector.inject(this, view);
        //setup the fragment
        if(recordsFound) {
            layoutManager = new LinearLayoutManager(getContext());
            fastItemAdapter = new FastItemAdapter();
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(fastItemAdapter);
        }
    }

}
