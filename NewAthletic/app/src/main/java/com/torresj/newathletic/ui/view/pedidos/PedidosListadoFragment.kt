package com.torresj.newathletic.ui.view.pedidos

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.torresj.newathletic.R
import com.torresj.newathletic.data.model.DtoCoPedido
import com.torresj.newathletic.data.model.FiltroCoPedido
import com.torresj.newathletic.databinding.FragmentPedidosListadoBinding
import com.torresj.newathletic.ui.adapter.pedido.PedidoAdapter
import com.torresj.newathletic.ui.viewmodel.pedidos.PedidoListadoViewModel
import com.torresj.newathletic.utils.ProgressBarGenerico
import com.torresj.newathletic.utils.SwipeHelper
import dagger.hilt.android.AndroidEntryPoint
import org.json.JSONArray
import org.json.JSONObject

@AndroidEntryPoint
class PedidosListadoFragment : Fragment() {

    private val pedidoListadoViewModel: PedidoListadoViewModel by viewModels()
    private lateinit var mAdapter: PedidoAdapter
    var pedidosMutableList: MutableList<DtoCoPedido> = ArrayList()
    private val llmanager = LinearLayoutManager(context)


    private var _binding: FragmentPedidosListadoBinding? = null;
    private val binding get() = _binding!!;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val filtro = FiltroCoPedido()
        ProgressBarGenerico.LoadProgress(context)
        pedidoListadoViewModel.listarPedidos(filtro)

        pedidoListadoViewModel.listaPedidos.observe(this, Observer {
            ProgressBarGenerico.HideProgreess()
            pedidosMutableList = it.toMutableList();
            this.initRecyclerView();
        })


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPedidosListadoBinding.inflate(inflater, container, false);
        val view = binding.root;

        binding.etFilter.addTextChangedListener { userFilter ->
            val pedidosFiltered =
                pedidosMutableList.filter { pedido ->
                    pedido.clienteNombre.lowercase().contains(userFilter.toString().lowercase())
                }
            mAdapter.updatePedidos(pedidosFiltered as MutableList<DtoCoPedido>)
        }

        binding.icDelete.setOnClickListener(View.OnClickListener {
            val animFadein =
                AnimationUtils.loadAnimation(context, R.anim.fade_in_tv)
            binding.icDelete.startAnimation(animFadein)
            binding.etFilter.setText(null)
            binding.etFilter.requestFocus()
        })

        binding.fab.setOnClickListener { view ->
            findNavController().navigate(R.id.pedidosListado_to_pedidoMantenimiento)
        }


        object : SwipeHelper(context, binding.RecyclerCLientes, false) {

            override fun instantiateUnderlayButton(
                viewHolder: RecyclerView.ViewHolder?,
                underlayButtons: MutableList<UnderlayButton>?
            ) {
                // Archive Button
                underlayButtons?.add(SwipeHelper.UnderlayButton(
                    "Archive",
                    AppCompatResources.getDrawable(
                        context!!,
                        R.drawable.ic_archive_black
                    ),
                    Color.parseColor("#000000"), Color.parseColor("#ffffff"),
                    UnderlayButtonClickListener { pos: Int ->
                        // mAdapter.modelList.removeAt(pos);
                        mAdapter.notifyItemRemoved(pos)

                    }

                ))

                // Flag Button
                underlayButtons?.add(SwipeHelper.UnderlayButton(
                    "Flag",
                    AppCompatResources.getDrawable(
                        context!!,
                        R.drawable.ic_archive_black
                    ),
                    Color.parseColor("#FF0000"), Color.parseColor("#ffffff"),
                    UnderlayButtonClickListener { pos: Int ->

                        Toast.makeText(
                            context!!,
                            "Flag Button Clicked at Position: " + pos,
                            Toast.LENGTH_SHORT
                        ).show()
                        mAdapter.notifyItemChanged(pos)
                    }

                ))

                // More Button
                underlayButtons?.add(SwipeHelper.UnderlayButton(
                    "More",
                    AppCompatResources.getDrawable(
                        context!!,
                        R.drawable.ic_archive_black
                    ),
                    Color.parseColor("#00FF00"), Color.parseColor("#ffffff"),
                    UnderlayButtonClickListener { pos: Int ->

                        Toast.makeText(
                            context!!,
                            "More Button CLicked at Position: " + pos,
                            Toast.LENGTH_SHORT
                        ).show()
                        mAdapter.notifyItemChanged(pos)
                    }
                ))
            }
        }
        return view;
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun initRecyclerView() {
        mAdapter = PedidoAdapter(
            pedidosList = pedidosMutableList,
            onClickListener = { superhero -> onItemSelected(superhero) },
            onClickDelete = { position -> onDeletedItem(position) }
        )
        binding.RecyclerCLientes.layoutManager = llmanager
        binding.RecyclerCLientes.adapter = mAdapter
    }

    private fun onDeletedItem(position: Int) {
        pedidosMutableList.removeAt(position)
        mAdapter.notifyItemRemoved(position)
    }

    private fun onItemSelected(pedido: DtoCoPedido) {
        // Toast.makeText(this, pedido.numeroDocumento, Toast.LENGTH_SHORT).show()
    }

   /* private fun readFromAsset(): MutableList<Model> {
        val modeList = mutableListOf<Model>()
        val bufferReader = application.assets.open("android_version.json").bufferedReader()
        val json_string = bufferReader.use {
            it.readText()
        }
        val jsonArray = JSONArray(json_string);

        for (i in 0..jsonArray.length() - 1) {
            val jsonObject: JSONObject = jsonArray.getJSONObject(i)

            val model = Model(jsonObject.getString("name"), jsonObject.getString("version"))
            modeList.add(model)
        }

        return modeList
    }*/

}