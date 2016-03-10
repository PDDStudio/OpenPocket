package com.pddstudio.openpocket.adapters.items;
/*
 * This Class was created by Patrick J
 * on 10.03.16. For more Details and licensing information
 * have a look at the README.md
 */

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.mikepenz.fastadapter.items.AbstractItem;
import com.mikepenz.iconics.IconicsDrawable;
import com.pddstudio.openpocket.R;
import com.pddstudio.openpocket.views.MLRoundedImageView;
import com.pddstudio.pocketlibrary.models.Transaction;

public class TransactionItem extends AbstractItem<TransactionItem, TransactionItem.ViewHolder> {

    private final Transaction transaction;

    public TransactionItem(Transaction transaction) {
        this.transaction = transaction;
    }

    @Override
    public int getType() {
        return R.id.transaction_item;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.item_transaction;
    }

    @Override
    public void bindView(ViewHolder viewHolder) {
        super.bindView(viewHolder);
        //build the icon for the Transaction
        IconicsDrawable iconicsDrawable = new IconicsDrawable(viewHolder.categoryImage.getContext()).icon(transaction.getCategory().getCategoryIcon());
        viewHolder.categoryImage.setImageDrawable(iconicsDrawable);
        viewHolder.categoryName.setText(transaction.getCategory().getCategoryName());
        //set the money value for the transaction and color the text
        viewHolder.categoryAmount.setText(transaction.getMoneyAmount() + "");
        viewHolder.categoryAmount.setTextColor(transaction.getMoneyAmount() < 0 ? Color.RED : Color.GREEN);
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder {

        MLRoundedImageView categoryImage;
        TextView categoryName;
        TextView categoryAmount;

        public ViewHolder(View itemView) {
            super(itemView);
            categoryImage = (MLRoundedImageView) itemView.findViewById(R.id.itemCategoryImage);
            categoryName = (TextView) itemView.findViewById(R.id.itemCategoryName);
            categoryAmount = (TextView) itemView.findViewById(R.id.itemCategoryAmount);
        }

    }

}
