package com.inacap.elraton.adapter;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.inacap.elraton.Metodo;
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
            if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.N)
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
        producto item= mData.get(position);
        holder.imagen.setImageBitmap(item.getFoto());
        holder.nombre.setText(item.getTitulo());
        holder.descripcion.setText(item.getDescripcion());
        holder.precio.setText(String.valueOf(item.getPrecio()));
        String correo=item.getCorreo();
        int id=item.getId();
        List<Integer> Cant = new ArrayList<>();
        for (int i=1; i<=item.getCantidad(); i++)
        {
            Cant.add(i);
        }
        ArrayAdapter<Integer> adapter= new ArrayAdapter(context, android.R.layout.simple_spinner_item, Cant);
        holder.spCantidad.setAdapter(adapter);
        holder.btnAgregar.setTag(R.id.CorreoListAdapter,correo);
        holder.btnAgregar.setTag(R.id.IDListAdapter,id);
        holder.setOnClick();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imagen;
        TextView nombre, descripcion, precio;
        Spinner spCantidad;
        Button btnAgregar;

        ViewHolder(View itemView) {
            super(itemView);
            imagen = itemView.findViewById(R.id.ImagenProd);
            nombre = itemView.findViewById(R.id.txtNombreProd);
            descripcion = itemView.findViewById(R.id.txtDescripcion);
            precio = itemView.findViewById(R.id.txtPrecio);
            spCantidad = itemView.findViewById(R.id.spCantidad);
            btnAgregar = itemView.findViewById(R.id.btnAgregar);
        }

        void setOnClick() {
            btnAgregar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Metodo x = new Metodo();
                    String correo = (String) v.getTag(R.id.CorreoListAdapter);
                    int id = (int) v.getTag(R.id.IDListAdapter);
                    int cantidad = (int) spCantidad.getSelectedItem();
                    x.AgregarACarrito(cantidad, v, id, correo);
                }
            });
        }
    }
}
