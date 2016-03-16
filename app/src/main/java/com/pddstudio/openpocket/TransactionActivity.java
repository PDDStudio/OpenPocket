package com.pddstudio.openpocket;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.github.clans.fab.FloatingActionButton;
import com.mikepenz.community_material_typeface_library.CommunityMaterial;
import com.mikepenz.fastadapter.adapters.FastItemAdapter;
import com.mikepenz.iconics.IconicsDrawable;
import com.pddstudio.openpocket.adapters.items.CategoryItem;
import com.pddstudio.openpocket.fragments.AmountInputFragment;
import com.pddstudio.openpocket.model.Action;
import com.pddstudio.pocketlibrary.OpenPocket;
import com.pddstudio.pocketlibrary.models.Category;
import com.pddstudio.pocketutils.Preferences;

import java.util.List;

import io.inject.InjectView;
import io.inject.Injector;

public class TransactionActivity extends AppCompatActivity implements View.OnClickListener, AmountInputFragment.InputCallback {

    @InjectView(R.id.categoryRecyclerView)
    private RecyclerView recyclerView;

    @InjectView(R.id.floatingActionButton)
    private FloatingActionButton floatingActionButton;

    @InjectView(R.id.transactionToolbar)
    private Toolbar toolbar;

    @InjectView(R.id.amountTextView)
    private TextView amountText;

    @InjectView(R.id.categoryTextView)
    private TextView categoryText;

    private FastItemAdapter<CategoryItem> fastItemAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private List<Category> categories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);
        Injector.inject(this);

        //set the toolbar
        toolbar.setTitle(R.string.toolbar_transaction_title);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null) getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //configure the recyclerview and load the items
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        fastItemAdapter = new FastItemAdapter<>();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(fastItemAdapter);

        categories = OpenPocket.get().getCategoryManager().getCategoryList();
        for(Category category : categories) {
            fastItemAdapter.add(new CategoryItem(category));
        }

        //set and assign the fab
        floatingActionButton.setImageDrawable(new IconicsDrawable(this).icon(CommunityMaterial.Icon.cmd_check).color(Color.WHITE));
        floatingActionButton.setOnClickListener(this);

        //set the number input field
        AmountInputFragment amountInputFragment = new AmountInputFragment().withInputCallback(this);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.categoryFragmentPlaceHolder, amountInputFragment)
                .commit();

        //set the amount to 0 by default
        amountText.setText(Preferences.get().getCurrencySymbol() + "0");

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onInput(Action action) {
        Log.d("TransactionActivity", "Action triggered: " + action.name() + " [" + action.getActionString() + "]");
        //parse the action and react on it
        switch (action) {
            case INPUT_DEL:
                if(amountText.getText().length() == 1) {
                    amountText.setText(amountText.getText() + "0");
                } else {
                    amountText.setText(amountText.getText().subSequence(0, amountText.getText().length() -1));
                }
                break;
            default:
                if(amountText.getText().toString().contentEquals(Preferences.get().getCurrencySymbol() + "0")) amountText.setText(Preferences.get().getCurrencySymbol());
                amountText.setText(amountText.getText() + action.getActionString());
                break;
        }
    }
}
