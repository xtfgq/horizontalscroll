package com.example.horizontalscroll;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private GalleryAdapter mAdapter;
    private List<Integer> mDatas;
    private List<Integer> mImages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        initDatas();
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_horizontal);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mImages = new ArrayList<Integer>();
        mImages.add(mDatas.get(0));
        mAdapter = new GalleryAdapter(this, mImages);
        mAdapter.setOnItemClickLitener(new GalleryAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                if (position == 0) {
                    for (int i = 1; i < mDatas.size(); i++) {
                        mImages.add(mDatas.get(i));
                        mAdapter.notifyItemInserted(i);
                    }
                    mAdapter.notifyDataSetChanged();

                } else {

                    swap(mDatas, position, 0);
                    for (int i = mDatas.size() - 1; i < 0; i--) {
                        mAdapter.notifyItemRemoved(i);
                    }
                    mImages.clear();
                    mImages.add(mDatas.get(0));
                    mAdapter.notifyItemInserted(0);
                    mAdapter.notifyDataSetChanged();
                }
            }
        });
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setItemAnimator(new NoAlphaItemAnimator());
    }

    private void initDatas() {
        mDatas = new ArrayList<Integer>(Arrays.asList(R.mipmap.a,
                R.mipmap.b, R.mipmap.c, R.mipmap.d, R.mipmap.e,
                R.mipmap.f, R.mipmap.g, R.mipmap.h, R.mipmap.l));
    }

    /**
     * 调换集合中两个指定位置的元素, 若两个元素位置中间还有其他元素，需要实现中间元素的前移或后移的操作。
     *
     * @param list        集合
     * @param oldPosition 需要调换的元素
     * @param newPosition 被调换的元素
     * @param <T>
     */
    public <T> void swap(List<T> list, int oldPosition, int newPosition) {
        if (null == list) {
            throw new IllegalStateException("The list can not be empty...");
        }

        // 向前移动，前面的元素需要向后移动
        if (oldPosition < newPosition) {
            for (int i = oldPosition; i < newPosition; i++) {
                Collections.swap(list, i, i + 1);

            }
        }

        // 向后移动，后面的元素需要向前移动
        if (oldPosition > newPosition) {
            for (int i = oldPosition; i > newPosition; i--) {
                Collections.swap(list, i, i - 1);
            }
        }
    }
}
