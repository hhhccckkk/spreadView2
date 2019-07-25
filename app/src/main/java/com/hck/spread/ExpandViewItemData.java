package com.hck.spread;

import android.widget.ListView;

public class ExpandViewItemData<T> {
    private int position;
    private T data;
    private ListView listView;
    //itemView资源文件id
    private int itemViewId=-1;
    //展开菜单view资源文件id
    private int functionViewId=-1;
    //数据总大小
    private int allDataSize;

    public ExpandViewItemData(int position, T data, ListView listView, int itemViewId, int functionViewId, int allDataSize) {
        this.position = position;
        this.data = data;
        this.listView = listView;
        this.itemViewId = itemViewId;
        this.functionViewId = functionViewId;
        this.allDataSize = allDataSize;
    }

    public int getAllDataSize() {
        return allDataSize;
    }

    public void setAllDataSize(int allDataSize) {
        this.allDataSize = allDataSize;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ListView getListView() {
        return listView;
    }

    public void setListView(ListView listView) {
        this.listView = listView;
    }

    public int getItemViewId() {
        return itemViewId;
    }

    public void setItemViewId(int itemViewId) {
        this.itemViewId = itemViewId;
    }

    public int getFunctionViewId() {
        return functionViewId;
    }

    public void setFunctionViewId(int functionViewId) {
        this.functionViewId = functionViewId;
    }
}
