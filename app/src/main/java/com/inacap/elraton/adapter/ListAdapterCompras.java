package com.inacap.elraton.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.inacap.elraton.R;
import com.inacap.elraton.clase.producto;

import java.util.List;

public class ListAdapterCompras extends RecyclerView.Adapter<ListAdapterCompras.ViewHolder>
{
    private List<producto> mData;
    private LayoutInflater mInflater;
    private Context context;

    public ListAdapterCompras(List<producto> itemList, Context context)
    {
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.mData=itemList;
    }

    @Override
    public int getItemCount() {return mData.size();}

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view=mInflater.inflate(R.layout.list_element_mis_compras,null);
        return new ListAdapterCompras.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
        producto item=mData.get(position);
        holder.imgAdd.setImageBitmap(item.getFoto());
        holder.NombreProductoCompras.setText(item.getTitulo());
        holder.Precio.setText(String.valueOf(item.getPrecio()));
        holder.PrecioTotal.setText(String.valueOf(item.getPrecioTotal()));
        holder.Descripcion.setText(item.getDescripcion());
        holder.CantidadComprados.setText(String.valueOf(item.getCantidad()));
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView NombreProductoCompras, Precio, Descripcion, CantidadComprados,PrecioTotal;
        ImageView imgAdd;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgAdd=itemView.findViewById(R.id.ImagenProd);
            NombreProductoCompras=itemView.findViewById(R.id.txtNombreProd);
            Precio=itemView.findViewById(R.id.txtPrecio);
            PrecioTotal=itemView.findViewById(R.id.txtPrecioTotalMC);
            Descripcion=itemView.findViewById(R.id.txtDescripcion);
            CantidadComprados=itemView.findViewById(R.id.txtCantidad);
        }
    }
}
