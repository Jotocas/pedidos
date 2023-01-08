package com.torresj.newathletic.ui.view.pedidos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import com.torresj.newathletic.R
import com.torresj.newathletic.databinding.FragmentPedidoMantenimientoBinding
import com.torresj.newathletic.ui.view.clientes.DialogClientesFragment

class PedidoMantenimientoFragment : Fragment() {

    private var _binding: FragmentPedidoMantenimientoBinding? = null;
    private val binding get() = _binding!!;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPedidoMantenimientoBinding.inflate(inflater, container, false);
        val view = binding.root;

        binding.icBuscar.setOnClickListener {
            showMyDialog()
        }

        return view;
    }


    private fun showMyDialog() {

        var dialog=DialogClientesFragment()
        dialog.show(childFragmentManager,"")

    }

}