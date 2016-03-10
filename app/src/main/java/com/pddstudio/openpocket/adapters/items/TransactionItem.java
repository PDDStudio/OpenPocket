package com.pddstudio.openpocket.adapters.items;
/*
 * This Class was created by Patrick J
 * on 10.03.16. For more Details and licensing information
 * have a look at the README.md
 */

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.mikepenz.fastadapter.items.AbstractItem;
import com.pddstudio.openpocket.R;

public class TransactionItem extends AbstractItem<TransactionItem, TransactionItem.ViewHolder> {

    public TransactionItem() {}

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
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);
        }

    }

}
