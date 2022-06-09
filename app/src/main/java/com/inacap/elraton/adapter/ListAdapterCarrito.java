package com.inacap.elraton.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.inacap.elraton.R;
import com.inacap.elraton.clase.producto;

import java.util.List;

public class ListAdapterCarrito extends RecyclerView.Adapter<ListAdapterCarrito.ViewHolder>
{
    private List<producto> mData;
    private LayoutInflater mInflater;
    private Context context;


    public ListAdapterCarrito(List<producto> itemList, Context context)
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
    public ListAdapterCarrito.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view=mInflater.inflate(R.layout.list_element_carrito,null);
        return new ListAdapterCarrito.ViewHolder(view);
    }

    public void onBindViewHolder(final ListAdapterCarrito.ViewHolder holder, final int position)
    {
        holder.bindData(mData.get(position));
    }

    public void setItems(List<producto> items)
    {
        mData=items;
    }


    public class ViewHolder extends RecyclerView.ViewHolder
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
            //imagen.setImageResource(item.getFoto());
            nombre.setText(item.getTitulo());
            descripcion.setText(item.getDescripcion());
            precio.setText(String.valueOf(item.getPrecio()));
        }
    }

}

