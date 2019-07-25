package com.hck.spread;

public interface IExpandListListener <T>{
    //item绑定数据
    void bindItemViewData(ExpandListItemView.ItemView itemView,int position,T data);

    //设置菜单按钮,文字，图片等
    void setFunctionView(ExpandListItemView.FunctionView functionView,int position,T data);

    //这里设置按钮和绑定按钮事件
    void bindFunctionItemListener(ExpandListItemView.FunctionView functionView,int position,T data);

    //是否影藏功能菜单,true影藏
    boolean isHiddenFunctionView();

    //item点击事件
    void onItemViewClickListener(ExpandListItemView.ItemView itemView,int position,T data);


}
