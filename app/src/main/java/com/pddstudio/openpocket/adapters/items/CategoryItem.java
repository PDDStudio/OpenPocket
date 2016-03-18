package com.pddstudio.openpocket.adapters.items;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.mikepenz.fastadapter.items.AbstractItem;
import com.mikepenz.iconics.IconicsDrawable;
import com.pddstudio.openpocket.R;
import com.pddstudio.pocketlibrary.models.Category;

/**
 * This Class was created by Patrick J
 * on 15.03.16. For more Details and Licensing
 * have a look at the README.md
 */
public class CategoryItem extends AbstractItem<CategoryItem, CategoryItem.ViewHolder> {

    private final Category category;

    public CategoryItem(Category category) {
        this.category = category;
    }

    @Override
    public int getType() {
        return R.id.category_item;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.item_category;
    }

    @Override
    public void bindView(ViewHolder viewHolder) {
        super.bindView(viewHolder);
        // configure the item
        IconicsDrawable iconicsDrawable = new IconicsDrawable(viewHolder.categoryImage.getContext()).icon(category.getCategoryIcon()).color(Color.WHITE);
        viewHolder.categoryImage.setImageDrawable(iconicsDrawable);
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder {

        protected ImageView categoryImage;

        public ViewHolder(View itemView) {
            super(itemView);
            categoryImage = (ImageView) itemView.findViewById(R.id.categoryImage);
        }
    }
}
