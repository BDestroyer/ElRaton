package com.inacap.elraton.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.inacap.elraton.R;
import com.inacap.elraton.clase.producto;

import java.util.List;

public class ListAdapterAdmin extends RecyclerView.Adapter<ListAdapterAdmin.ViewHolder>
    {
        private List<producto> mData;
        private LayoutInflater mInflater;
        private Context context;

        public ListAdapterAdmin(List<producto> itemList, Context context)
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
        public ListAdapterAdmin.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            View view=mInflater.inflate(R.layout.list_element_admin,null);
            return new ListAdapterAdmin.ViewHolder(view);
        }

        public void onBindViewHolder(final ListAdapterAdmin.ViewHolder holder, final int position)
        {
            holder.bindData(mData.get(position));
        }

        public class ViewHolder extends RecyclerView.ViewHolder
        {
            ImageView iconImage;
            TextView id, nombre, descripcion, precio, cantidad;

            ViewHolder(View itemView)
            {
                super(itemView);
                id=itemView.findViewById(R.id.txtID);
                iconImage = itemView.findViewById(R.id.ImagenProd);
                nombre=itemView.findViewById(R.id.txtNombreProd);
                descripcion = itemView.findViewById(R.id.txtDescripcion);
                precio=itemView.findViewById(R.id.txtPrecio);
                cantidad=itemView.findViewById(R.id.txtCantidad);
            }
            void bindData(final producto item)
            {
                id.setText(String.valueOf(item.getId()));
                iconImage.setImageBitmap(item.getFoto());
                nombre.setText(item.getTitulo());
                descripcion.setText(item.getDescripcion());
                precio.setText(String.valueOf(item.getPrecio()));
                cantidad.setText(String.valueOf(item.getCantidad()));
            }
        }
    }
