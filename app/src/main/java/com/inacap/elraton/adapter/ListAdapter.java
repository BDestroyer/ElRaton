package com.inacap.elraton.adapter;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.inacap.elraton.R;
import com.inacap.elraton.clase.producto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder>
{
    private List<producto> mData;
    ArrayList<producto> mOriginal;
    private LayoutInflater mInflater;
    private Context context;


    public ListAdapter(List<producto> itemList, Context context)
    {
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.mData=itemList;
        mOriginal =new ArrayList<>();
        mOriginal.addAll(mData);
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

    public void filtrado(String txtBuscar)
    {
        int longitud=txtBuscar.length();
        if (longitud == 0)
        {
            mData.clear();
            mData.addAll(mOriginal);
        }
        else
        {
            if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.N)
            {
                List<producto> coleccion = mData.stream()
                        .filter(i -> i.getTitulo().toLowerCase().contains(txtBuscar.toLowerCase()))
                        .collect(Collectors.toList());
                mData.clear();
                mData.addAll(coleccion);
            }else {
                for (producto p:mOriginal)
                {
                    if (p.getTitulo().toLowerCase().contains(txtBuscar.toLowerCase()))
                    {
                        mData.add(p);
                    }
                }
            }
        }
        notifyDataSetChanged();
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

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        ImageView imagen;
        TextView nombre, descripcion, precio;

        ViewHolder(View itemView)
        {
            super(itemView);
            imagen = itemView.findViewById(R.id.ImagenProd);
            nombre=itemView.findViewById(R.id.txtNombreProd);
            descripcion = itemView.findViewById(R.id.txtDescripcion);
            precio=itemView.findViewById(R.id.txtPrecio);
        }
        void bindData(final producto item)
        {
            imagen.setImageResource(R.drawable.ic_baseline_camera_alt_24);
            nombre.setText(item.getTitulo());
            descripcion.setText(item.getDescripcion());
            precio.setText(String.valueOf(item.getPrecio()));
        }
    }
}
