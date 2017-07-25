package com.example.horizontalscroll;
import java.util.List;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.sunfusheng.glideimageview.GlideImageView;


public class GalleryAdapter extends
		RecyclerView.Adapter<GalleryAdapter.ViewHolder>
{

	public interface OnItemClickLitener
	{
		void onItemClick(View view, int position);
	}

	private OnItemClickLitener mOnItemClickLitener;


	public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener)
	{
		this.mOnItemClickLitener = mOnItemClickLitener;
	}

	private LayoutInflater mInflater;

    public List<Integer> getmDatas() {
        return mDatas;
    }

    public void setmDatas(List<Integer> mDatas) {
        this.mDatas = mDatas;
    }

    private List<Integer> mDatas;

    public boolean isShow() {
        return isShow;
    }

    public void setShow(boolean show) {
        isShow = show;
    }

    private boolean isShow=false;

	public GalleryAdapter(Context context, List<Integer> datats)
	{
		mInflater = LayoutInflater.from(context);
		mDatas = datats;

	}

	public static class ViewHolder extends RecyclerView.ViewHolder
	{
		public ViewHolder(View view)
		{
			super(view);

		}

        GlideImageView mImg;


	}

	@Override
	public int getItemCount()
	{
		return mDatas.size();
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i)
	{
		View view = mInflater.inflate(R.layout.activity_index_gallery_item,
				viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);

		viewHolder.mImg = (GlideImageView) view
				.findViewById(R.id.index_gallery_item_image);


		return viewHolder;
	}

	@Override
	public void onBindViewHolder(final ViewHolder viewHolder, final int i)
	{
        viewHolder.mImg.loadLocalCircleImage(mDatas.get(i),R.mipmap.ic_launcher);
        viewHolder.mImg.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickLitener.onItemClick(viewHolder.itemView, i);
            }
        });


	}





}
