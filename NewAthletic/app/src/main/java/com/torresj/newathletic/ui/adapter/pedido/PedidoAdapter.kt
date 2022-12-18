package com.torresj.newathletic.ui.adapter.pedido

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.torresj.newathletic.data.model.DtoCoPedido
import com.torresj.newathletic.databinding.ItemPedidosBinding

class PedidoAdapter(
    private var pedidosList: List<DtoCoPedido>,
    private val onClickListener: (DtoCoPedido) -> Unit,
    private val onClickDelete:(Int) -> Unit
) :RecyclerView.Adapter<PedidoAdapter.ViewHolder>(){

    inner class ViewHolder(val binding: ItemPedidosBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemPedidosBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder){
            with(pedidosList[position]){
                binding.txtCliente.text = this.clienteNombre
                binding.txtEstadoPedido.text = this.estadoNombre
                binding.txtdireccionPedido.text = this.estadoNombre
                binding.txtFechaDocumento.text = this.fechaDocumento.toString()
                binding.txtNroDocumento.text = this.numeroDocumento
                binding.txtMontoTotal.text = this.montoTotal.toString()
            }
        }
    }

    override fun getItemCount(): Int =pedidosList.size

    fun updatePedidos(pedidosList: MutableList<DtoCoPedido>){
        this.pedidosList = pedidosList
        notifyDataSetChanged()
    }

}