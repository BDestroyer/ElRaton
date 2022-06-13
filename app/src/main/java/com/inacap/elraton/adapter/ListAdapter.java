package com.inacap.elraton.adapter;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.inacap.elraton.R;
import com.inacap.elraton.clase.producto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> implements View.OnClickListener
{
    private List<producto> mData;
    ArrayList<producto> mOriginal;
    private LayoutInflater mInflater;
    private Context context;
    private View.OnClickListener listener;

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
        view.setOnClickListener(this);
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
        producto item= mData.get(position);
        holder.imagen.setImageBitmap(item.getFoto());
        holder.nombre.setText(item.getTitulo());
        holder.descripcion.setText(item.getDescripcion());
        holder.precio.setText(String.valueOf(item.getPrecio()));
    }

    public void setItems(List<producto> items)
    {
        mData=items;
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener=listener;
    }

    @Override
    public void onClick(View v) {
        if(listener!=null){
            listener.onClick(v);
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        ImageView imagen;
        TextView nombre, descripcion, precio;

        ViewHolder(View itemView)
        {
            //https://stackoverflow.com/questions/27617693/java-lang-classcastexception-android-widget-relativelayoutlayoutparams-cannot
            super(itemView);
            imagen = itemView.findViewById(R.id.ImagenProd);
            nombre=itemView.findViewById(R.id.txtNombreProd);
            descripcion = itemView.findViewById(R.id.txtDescripcion);
            precio=itemView.findViewById(R.id.txtPrecio);
        }
    }
}
