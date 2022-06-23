package com.inacap.elraton.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.inacap.elraton.AdminActivity.ModificarProductoActivity;
import com.inacap.elraton.Metodo;
import com.inacap.elraton.R;
import com.inacap.elraton.clase.producto;
import com.inacap.elraton.ui.LoginActivity;

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
            producto item=mData.get(position);
            holder.id.setText(String.valueOf(item.getId()));
            int id=item.getId();
            holder.iconImage.setImageBitmap(item.getFoto());
            holder.nombre.setText(item.getTitulo());
            holder.descripcion.setText(item.getDescripcion());
            holder.precio.setText(String.valueOf(item.getPrecio()));
            holder.cantidad.setText(String.valueOf(item.getCantidad()));
            holder.setOnClick(id);
        }

        public class ViewHolder extends RecyclerView.ViewHolder
        {
            ImageView iconImage;
            TextView id, nombre, descripcion, precio, cantidad;
            Button btnModificar, btnEliminar;

            ViewHolder(View itemView)
            {
                super(itemView);
                id=itemView.findViewById(R.id.txtID);
                iconImage = itemView.findViewById(R.id.ImagenProd);
                nombre=itemView.findViewById(R.id.txtNombreProd);
                descripcion = itemView.findViewById(R.id.txtDescripcion);
                precio=itemView.findViewById(R.id.txtPrecio);
                cantidad=itemView.findViewById(R.id.txtCantidad);
                btnModificar=itemView.findViewById(R.id.btnModificar);
                btnEliminar=itemView.findViewById(R.id.btnEliminar);
            }

            void setOnClick(int id)
            {
                btnEliminar.setTag(id);
                btnEliminar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v)
                    {
                        AlertDialog.Builder builder=new AlertDialog.Builder(v.getContext());
                        builder.setMessage("¿Desea eliminar el producto seleccionado?").setPositiveButton("Si", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Metodo x=new Metodo();
                                        int id= (int) v.getTag();
                                        x.EliminarProductoAdmin(id,v.getContext());
                                    }
                                })
                                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });
                        builder.show();
                    }
                });
                btnModificar.setTag(id);
                btnModificar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v)
                    {
                        int id= (int) v.getTag();
                        Intent a=new Intent(v.getContext(), ModificarProductoActivity.class);
                        a.putExtra("id",id);
                        context.startActivity(a);
                    }
                });
            }
        }
    }
