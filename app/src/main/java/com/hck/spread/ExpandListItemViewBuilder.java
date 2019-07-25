package com.hck.spread;

import android.content.Context;
import android.widget.ListView;

public class ExpandListItemViewBuilder<T> {

    public ExpandListItemView<T> build(Context context, ExpandViewItemData<T> data, BaseExpandListListener listListener) {
        ExpandListItemView<T> expandListItemView = new ExpandListItemView<>(context);
        expandListItemView.init(context, data, listListener);
        return expandListItemView;
    }

    public ExpandListItemView<T> build(Context context, int position, int itemViewId, int functionViewId,
                                       int allDataSize, ListView listView, T data,
                                       BaseExpandListListener listListener) {
        ExpandListItemView<T> expandListItemView = new ExpandListItemView<>(context);
        ExpandViewItemData expandViewItemData = new ExpandViewItemData(position, data, listView, itemViewId, functionViewId, allDataSize);
        expandListItemView.init(context, expandViewItemData, listListener);
        return expandListItemView;
    }
    public ExpandListItemView<T> build(Context context, int position, int itemViewId,
                                       int allDataSize, ListView listView, T data,
                                       BaseExpandListListener listListener) {
        ExpandListItemView<T> expandListItemView = new ExpandListItemView<>(context);
        ExpandViewItemData expandViewItemData = new ExpandViewItemData(position, data, listView, itemViewId,-1, allDataSize);
        expandListItemView.init(context, expandViewItemData, listListener);
        return expandListItemView;
    }




}
