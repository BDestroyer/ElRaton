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

import com.inacap.elraton.Metodo;
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
        producto item= mData.get(position);
        holder.cantidad.setText(String.valueOf(item.getCantidad()));
        holder.imagen.setImageBitmap(item.getFoto());
        holder.nombre.setText(item.getTitulo());
        holder.descripcion.setText(item.getDescripcion());
        holder.precio.setText(String.valueOf(item.getPrecio()));
        int id=item.getId();
        holder.setOnClick(id);
    }

    public void setItems(List<producto> items)
    {
        mData=items;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imagen;
        TextView nombre, descripcion, precio, cantidad;
        Button btnEliminar;

        ViewHolder(View itemView)
        {
            super(itemView);
            cantidad=itemView.findViewById(R.id.txtCantidadCarrito);
            imagen = itemView.findViewById(R.id.ImagenProd);
            nombre=itemView.findViewById(R.id.txtNombreProd);
            descripcion = itemView.findViewById(R.id.txtDescripcion);
            precio=itemView.findViewById(R.id.txtPrecio);
            btnEliminar=itemView.findViewById(R.id.btnEliminarCarrito);
        }

        public void setOnClick(int id)
        {
            btnEliminar.setTag(id);
            btnEliminar.setOnClickListener(this);
        }

        @Override
        public void onClick(View v)
        {
            Metodo x=new Metodo();
            int id=(int) v.getTag();
            x.EliminarCarrito(id, v);
        }
    }

}

