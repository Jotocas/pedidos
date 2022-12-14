package com.torresj.newathletic.ui.view.pedidos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.torresj.newathletic.R
import com.torresj.newathletic.data.model.DtoCoPedido
import com.torresj.newathletic.data.model.FiltroCoPedido
import com.torresj.newathletic.databinding.FragmentPedidosListadoBinding
import com.torresj.newathletic.ui.adapter.pedido.PedidoAdapter
import com.torresj.newathletic.ui.viewmodel.pedidos.PedidoListadoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PedidosListadoFragment : Fragment() {

    private val pedidoListadoViewModel: PedidoListadoViewModel by viewModels()
    val mAdapter : PedidoAdapter = PedidoAdapter()
    //var listaPedidos = MutableLiveData<List<DtoCoPedido>>()
  //  var pedidos: MutableList<DtoCoPedido>  = ArrayList()

    private var _binding:FragmentPedidosListadoBinding? = null;
    private val binding get() = _binding!!;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val filtro =FiltroCoPedido()
        pedidoListadoViewModel.listarPedidos(filtro)

        pedidoListadoViewModel.listaPedidos.observe(this, Observer {
            this.initRecyclerView(it);
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = FragmentPedidosListadoBinding.inflate(inflater,container,false);
        val view = binding.root;
        return view;
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun initRecyclerView(pedidos: List<DtoCoPedido> ) {
        binding?.RecyclerCLientes?.setHasFixedSize(true)
        binding?.RecyclerCLientes?.layoutManager = LinearLayoutManager(context)
        context?.let { mAdapter.PedidoAdapter(pedidos.toMutableList(), it) }
        binding?.RecyclerCLientes?.adapter = mAdapter
    }

}