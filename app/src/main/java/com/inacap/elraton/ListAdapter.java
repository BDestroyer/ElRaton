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
        TextView nombre, descripcion, precio;

        ViewHolder(View itemView)
        {
            super(itemView);
            iconImage = itemView.findViewById(R.id.ImagenProd);
            nombre=itemView.findViewById(R.id.txtNombreProd);
            descripcion = itemView.findViewById(R.id.txtDescripcion);
            precio=itemView.findViewById(R.id.txtPrecio);
        }

        void bindData(final producto item)
        {
            nombre.setText(item.getNombre());
            descripcion.setText(item.getDescripcion());
            precio.setText(item.getPrecio());
        }
    }
}
