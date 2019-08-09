package com.hck.spread;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import spreadview.hck.com.spreadview.R;

/**
 * @author ：
 * @date：2018/12/20
 */

public class ExpandListItemView<T> extends LinearLayout {
    private Context context;
    private View mainView; //最外层父容器
    private View itemView; //listview item
    private View functionView; //展开view
    private IExpandListListener listener;
    private boolean mIsShowItemEvent;
    private LinearLayout itemLinearLayout;
    private ItemView ItemView;
    private FunctionView FunctionView;
    private boolean isOnClickHindFunctionView = true;
    private ExpandViewItemData<T> data;

    public ExpandListItemView(Context context) {
        super(context);
    }

    class ItemView {
        public void setText(int textViewId, String text) {
            setItemViewText(textViewId, text);
        }

        public TextView getTextView(int textViewId) {
            return getItemView(textViewId);
        }
    }

    class FunctionView {
        public void setOnClickHindFunctionView(boolean isShow) {
            isOnClickHindFunctionView = isShow;
        }

        public void setFunctionViewBgColor(int res) {
            if (functionView != null) {
                functionView.setBackgroundColor(res);
            }
        }

        public void setFunctionItemView(int id, String title, int image) {
            TextView itemView = (TextView) getFunctionView(id);
            itemView.setText(title);
            if (image != 0) {
                ViewUtils.setCompoundDrawables(context, itemView, ViewUtils.POS_TOP, image);
            }
            itemView.setVisibility(View.VISIBLE);
        }

        public void setFunctionViewVisibility(int viewId, int visibility) {
            getFunctionView(viewId).setVisibility(visibility);
        }

        public void hindFunctionView(int viewId) {
            setFunctionViewVisibility(viewId, View.GONE);
        }

        public void showFunctionView(int viewId) {
            setFunctionViewVisibility(viewId, View.VISIBLE);
        }

        public View getFunctionView(int id) {
            if (functionView != null) {
                return functionView.findViewById(id);
            }
            return null;
        }

        public void buindListener(int id, final OnClickListener listener) {
            functionView.findViewById(id).setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (isOnClickHindFunctionView) {
                        functionView.setVisibility(View.GONE);
                        mIsShowItemEvent = false;
                        oldItemView.setIetmEvent(null);
                        changeItemViewContainerBg(R.drawable.white_round_bg);
                    } else {
                        mIsShowItemEvent = true;
                        functionView.setVisibility(View.VISIBLE);
                    }
                    listener.onClick(v);
                }
            });
        }
    }

    public void init(Context context, ExpandViewItemData<T> data, IExpandListListener listener) {
        this.data = data;
        itemView = LayoutInflater.from(context).inflate(data.getItemViewId(), null);
        functionView = LayoutInflater.from(context).inflate(data.getFunctionViewId() < 0 ? R.layout.view_functions_item : data.getFunctionViewId(), null);
        this.context = context;
        this.listener = listener;
        init(context);
    }

    private void init(Context context) {
        ItemView = new ItemView();
        FunctionView = new FunctionView();
        mainView = LayoutInflater.from(context).inflate(R.layout.view_expand_main, this);//父容器
        itemLinearLayout = mainView.findViewById(R.id.item); //加载itemview
        itemLinearLayout.addView(itemView);
        LinearLayout linearLayout2 = mainView.findViewById(R.id.event);  //用来放functionview
        linearLayout2.addView(functionView); // 加载功能菜单view
        functionView.setVisibility(View.GONE);
        int position=data.getPosition();
        listener.bindItemViewData(ItemView,position , data.getData());
        listener.setFunctionView(FunctionView, position, data.getData());
        listener.bindFunctionItemListener(FunctionView, position, data.getData());
        changeItemViewContainerBg(R.drawable.white_round_bg);
        mainView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener.isHiddenFunctionView()) {
                    listener.onItemViewClickListener(ItemView, data.getPosition(), data.getData());
                    return;
                }
                View old = oldItemView.getIetmEvent();
                if (old != null) {
                    old.setVisibility(View.GONE);
                }
                if (mIsShowItemEvent && old == functionView) {
                    onItemClick(ExpandListItemView.this, false);
                    changeItemViewContainerBg(R.drawable.white_round_bg);
                    functionView.setVisibility(View.GONE);
                    oldItemView.setIetmEvent(null);
                    mIsShowItemEvent = false;
                } else {
                    onItemClick(ExpandListItemView.this, true);
                    if (isBtnAllClose()) { //功能按钮都不可见，则不展开
                        return;
                    }
                    changeItemViewContainerBg(R.drawable.white_top_round_bg);
                    functionView.setVisibility(View.VISIBLE);
                    oldItemView.setIetmEvent(functionView);
                    scrollTo();
                    mIsShowItemEvent = true;
                }
            }
        });
    }

    private boolean isBtnAllClose() {
        if ((getFunctionView(R.id.functionTv1).getVisibility()
                == View.GONE
                && getFunctionView(R.id.functionTv2).getVisibility()
                == View.GONE
                && getFunctionView(R.id.functionTv3).getVisibility()
                == View.GONE
                && getFunctionView(R.id.functionTv4).getVisibility()
                == View.GONE
                && getFunctionView(R.id.functionTv5).getVisibility()
                == View.GONE)) {
            return true;
        } else {
            return false;
        }
    }


    public void onItemClick(ExpandListItemView view, boolean isFunctionViewShow) {

    }

    private void scrollTo() {
        ListView listView = data.getListView();
        if (listView != null) {
            int lastVisiblePosition = listView.getLastVisiblePosition();
           int firstVisiblePosition= listView.getFirstVisiblePosition();
            if (lastVisiblePosition <= 0) {
                return;
            }
            int pos = data.getPosition();
            if (pos >= 0 && pos  == lastVisiblePosition) {
                listView.setSelection(firstVisiblePosition+1);
                listView.smoothScrollToPosition(firstVisiblePosition+1);
            }
        }

    }

    public void changeItemViewContainerBg(int res) {
        if (itemView != null) {
            itemView.setBackgroundResource(res);
        }

    }


    public static class oldItemView {
        private static View mIetmEvent;

        public static View getIetmEvent() {
            return mIetmEvent;
        }

        public static void setIetmEvent(View ietmEvent) {
            mIetmEvent = ietmEvent;
        }
    }


    private View getFunctionView(int id) {
        if (functionView != null) {
            return functionView.findViewById(id);
        }
        return null;
    }

    private void setItemViewText(int id, String text) {
        TextView textView = getItemView(id);
        if (textView != null) {
            textView.setText(text);
        }
    }

    private <T extends View> T getItemView(int id) {
        return (T) itemView.findViewById(id);
    }


}
