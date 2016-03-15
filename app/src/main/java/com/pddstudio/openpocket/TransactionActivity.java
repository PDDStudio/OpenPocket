package com.pddstudio.openpocket;

import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.mikepenz.community_material_typeface_library.CommunityMaterial;
import com.mikepenz.fastadapter.adapters.FastItemAdapter;
import com.mikepenz.iconics.IconicsDrawable;
import com.pddstudio.openpocket.adapters.items.CategoryItem;
import com.pddstudio.pocketlibrary.OpenPocket;
import com.pddstudio.pocketlibrary.models.Category;

import java.util.List;

import io.inject.InjectView;
import io.inject.Injector;

public class TransactionActivity extends AppCompatActivity implements View.OnClickListener {

    @InjectView(R.id.categoryRecyclerView)
    private RecyclerView recyclerView;

    @InjectView(R.id.floatingActionButton)
    private FloatingActionButton floatingActionButton;

    private FastItemAdapter<CategoryItem> fastItemAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private List<Category> categories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);
        Injector.inject(this);

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

    }

    @Override
    public void onClick(View v) {

    }
    
}
