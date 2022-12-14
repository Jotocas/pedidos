package com.torresj.newathletic.ui.adapter.pedido

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.torresj.newathletic.R
import com.torresj.newathletic.data.model.DtoCoPedido
import com.torresj.newathletic.databinding.ItemPedidosBinding

class PedidoAdapter() :RecyclerView.Adapter<PedidoAdapter.ViewHolder>(){

    var pedidos: MutableList<DtoCoPedido>  = ArrayList()
    lateinit var context: Context

    inner class ViewHolder(val binding: ItemPedidosBinding) : RecyclerView.ViewHolder(binding.root)

    fun PedidoAdapter(pedidos : MutableList<DtoCoPedido>, context: Context){
        this.pedidos = pedidos
        this.context = context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemPedidosBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder){
            with(pedidos[position]){
                binding.txtCliente.text = this.clienteNombre
                binding.txtEstadoPedido.text = this.estadoNombre
                binding.txtdireccionPedido.text = this.estadoNombre
                binding.txtFechaDocumento.text = this.fechaDocumento.toString()
                binding.txtNroDocumento.text = this.numeroDocumento
                binding.txtMontoTotal.text = this.montoTotal.toString()
            }
        }
    }

    override fun getItemCount(): Int =pedidos.size

}