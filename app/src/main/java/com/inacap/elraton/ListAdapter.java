package com.inacap.elraton;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.inacap.elraton.clase.producto;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder>
{
    private List<producto> mData;
    private LayoutInflater mInflater;
    private Context context;

    public ListAdapter(List<producto> itemList, Context context)
    {
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.mData=itemList;
    }

    @Override
    public int getItemCount()
    {
        return mData.size();
    }

    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view=mInflater.inflate(R.layout.list_element,null);
        return new ListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ListAdapter.ViewHolder holder, final int position)
    {
        holder.bindData(mData.get(position));
    }

    public void setItems(List<producto> items)
    {
        mData=items;
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        ImageView iconImage;
        TextView cantidad, descripcion, precio;

        ViewHolder(View itemView)
        {
            super(itemView);
            iconImage = itemView.findViewById(R.id.iconImageView);
            descripcion = itemView.findViewById(R.id.nameTextView);
            cantidad=itemView.findViewById(R.id.cityTextView);
            precio=itemView.findViewById(R.id.statusTextView);
        }

        void bindData(final producto item)
        {
            iconImage.setColorFilter(Color.parseColor(item.getColor()), PorterDuff.Mode.SRC_IN);
            descripcion.setText(item.getDescripcion());
            cantidad.setText(item.getCantidad());
            precio.setText(item.getPrecio());
        }
    }
}