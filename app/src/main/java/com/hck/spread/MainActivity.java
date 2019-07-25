package com.hck.spread;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import spreadview.hck.com.spreadview.R;

public class MainActivity extends Activity {
    private ListView listView;
    private List<String> datas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        listView = findViewById(R.id.list);
        for (int i = 0; i < 10; i++) {
            datas.add("哈哈哈哈" + i);
        }
        listView.setAdapter(new MyAdapter());
    }

    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return datas.size();
        }

        @Override
        public Object getItem(int position) {
            return datas.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return getItemView(position, datas.get(position));
        }
    }

    private ExpandListItemView getItemView(int position, final String data) {
        return new ExpandListItemViewBuilder<String>().build(this, position, R.layout.list_view_item, datas.size()
                , listView, data, new BaseExpandListListener<String>() {


                    @Override
                    public void bindItemViewData(ExpandListItemView.ItemView itemView, int position, String data) {
                        itemView.setText(R.id.title, "你好啊啊啊啊啊");
                        itemView.setText(R.id.name, data);
                    }

                    @Override
                    public void setFunctionView(ExpandListItemView.FunctionView functionView, int position, String data) {
                        functionView.setFunctionItemView(R.id.functionTv2, "我的", R.drawable.function_chakan);
                        functionView.showFunctionView(R.id.functionTv1);
                        functionView.showFunctionView(R.id.functionTv2);
                        functionView.showFunctionView(R.id.functionTv3);
                        functionView.showFunctionView(R.id.functionTv4);
                    }

                    @Override
                    public void bindFunctionItemListener(ExpandListItemView.FunctionView functionView, final int position, String data) {
                        functionView.setOnClickHindFunctionView(false);
                        functionView.buindListener(R.id.functionTv1, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                showToast("functionTv1");
                            }
                        });
                        functionView.buindListener(R.id.functionTv1, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                showToast("functionTv1");
                            }
                        });
                        functionView.buindListener(R.id.functionTv2, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                showToast("functionTv2");
                            }
                        });
                        functionView.buindListener(R.id.functionTv3, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                showToast("functionTv3: "+position);
                            }
                        });
                    }

                    @Override
                    public void onItemViewClickListener(ExpandListItemView.ItemView itemView, int position, String data) {
                        showToast("onItemViewClickListener");
                        showToast("onItemViewClickListener: "+position);
                    }
                });

    }

    private void showToast(String msg) {
        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_LONG).show();
    }
}
