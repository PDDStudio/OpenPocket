package com.pddstudio.openpocket.fragments;
/*
 * This Class was created by Patrick J
 * on 07.03.16. For more Details and licensing information
 * have a look at the README.md
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mikepenz.fastadapter.adapters.FastItemAdapter;
import com.pddstudio.openpocket.R;
import com.pddstudio.pocketlibrary.OpenPocket;
import com.pddstudio.pocketlibrary.enums.Month;
import com.pddstudio.pocketlibrary.models.Transaction;
import com.pddstudio.pocketutils.DateUtils;

import java.util.List;

import io.inject.InjectView;
import io.inject.Injector;

public class OverviewFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    @InjectView(R.id.recyclerView) private RecyclerView recyclerView;
    @InjectView(R.id.swipeRefreshLayout) private SwipeRefreshLayout swipeRefreshLayout;
    private FastItemAdapter fastItemAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<Transaction> transactions;

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
        return transactions.size() > 0 ?
                layoutInflater.inflate(R.layout.fragment_overview, viewGroup, false) :
                layoutInflater.inflate(R.layout.fragment_overview_empty, viewGroup, false);
    }

    @Override
    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        monthName = getArguments().getString("monthName");
        transactions = OpenPocket.get().getTransactionManager().getFilteredTransactionList(DateUtils.getValueForMonth(monthName), DateUtils.getCurrentYear());
        Log.d("OverviewFragment", "Month: " + monthName + " | Transaction List: " + transactions.size());
    }

    public String getMonthName() {
        return monthName;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Injector.inject(this, view);
        //setup the fragment
        if(transactions.size() > 0) {
            //setup the refresh layout
            swipeRefreshLayout.setColorSchemeColors(R.color.colorPrimary, R.color.colorPrimaryDark, R.color.colorPrimaryLight);
            swipeRefreshLayout.setOnRefreshListener(this);
            //setup the recyclerview and it's adapter
            layoutManager = new LinearLayoutManager(getContext());
            fastItemAdapter = new FastItemAdapter();
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(fastItemAdapter);
        }
    }

    @Override
    public void onRefresh() {

    }
}
