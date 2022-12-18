package com.torresj.newathletic.utils

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.*
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.torresj.newathletic.R

object MensajesGenericos {
    //var ans_true: Runnable? = null
    //var ans_false: Runnable? = null

    fun SHowMensajesGenericos(type: String, titulo: String?, menssage: String?, context: Context?) {
        val dialog = Dialog(context!!)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.mensajes)
        val ImgBoton = dialog.findViewById<ImageView>(R.id.ImgBoton)
        val title = dialog.findViewById<TextView>(R.id.TxtNameCliente)
        val message = dialog.findViewById<TextView>(R.id.message)
        val read_btn = dialog.findViewById<Button>(R.id.read_btn)
        if (type == "Error") {
            ImgBoton.setImageResource(R.drawable.ic_error)
            title.setTextColor(Color.parseColor("#E11C1B"))
            title.text = titulo
            message.text = menssage
            read_btn.setBackgroundColor(Color.parseColor("#E11C1B"))
        } else if (type == "Success") {
            ImgBoton.setImageResource(R.drawable.ic_succes)
            title.setTextColor(Color.parseColor("#18830B"))
            title.text = titulo
            message.text = menssage
            read_btn.setBackgroundColor(Color.parseColor("#18830B"))
        } else if (type == "Info") {
            ImgBoton.setImageResource(R.drawable.ic_info)
            title.setTextColor(Color.parseColor("#D28C00"))
            title.text = titulo
            message.text = menssage
            read_btn.setBackgroundColor(Color.parseColor("#D28C00"))
        }

        // #E11C1B --> Rojo
        // #18830B --> Verde
        // #D28C00 --> Info
        read_btn.setOnClickListener { dialog.dismiss() }
        dialog.show()
        dialog.setCancelable(false)
        dialog.window!!.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window!!.setGravity(Gravity.BOTTOM)
    }

   /* fun SHowMensajesGenericosConAccion(
        type: String,
        titulo: String?,
        menssage: String?,
        context: Context?,
        accionboton: Runnable?
    ) {
        val dialog = Dialog(context!!)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.pruebas)
        ans_true = accionboton
        val ImgBoton = dialog.findViewById<ImageView>(R.id.ImgBoton)
        val title = dialog.findViewById<TextView>(R.id.TxtNameCliente)
        val message = dialog.findViewById<TextView>(R.id.message)
        val read_btn = dialog.findViewById<Button>(R.id.read_btn)
        if (type == "Error") {
            ImgBoton.setImageResource(R.drawable.ic_error)
            title.setTextColor(Color.parseColor("#E11C1B"))
            title.text = titulo
            message.text = menssage
            read_btn.setBackgroundColor(Color.parseColor("#E11C1B"))
        } else if (type == "Success") {
            ImgBoton.setImageResource(R.drawable.ic_succes)
            title.setTextColor(Color.parseColor("#18830B"))
            title.text = titulo
            message.text = menssage
            read_btn.setBackgroundColor(Color.parseColor("#18830B"))
        } else if (type == "Info") {
            ImgBoton.setImageResource(R.drawable.ic_info)
            title.setTextColor(Color.parseColor("#D28C00"))
            title.text = titulo
            message.text = menssage
            read_btn.setBackgroundColor(Color.parseColor("#D28C00"))
        }

        // #E11C1B --> Rojo
        // #18830B --> Verde
        // #D28C00 --> Info
        read_btn.setOnClickListener {
            ans_true!!.run()
            dialog.dismiss()
        }
        dialog.show()
        dialog.setCancelable(false)
        dialog.window!!.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        //dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.window!!.setGravity(Gravity.BOTTOM)
    }


    fun ShowMessageConfirm(
        TituloMensaje: String?,
        MensajeCUerpo: String?,
        activity: Activity,
        bProcedure: Runnable?,
        aProcedure: Runnable?
    ) {
        //before inflating the custom alert dialog layout, we will get the current activity viewgroup
        val viewGroup = activity.findViewById<ViewGroup>(R.id.content)

        //then we will inflate the custom alert dialog xml that we created
        val dialogView: View =
            LayoutInflater.from(activity).inflate(R.layout.item_question, viewGroup, false)
        ans_true = aProcedure
        ans_false = bProcedure
        val BtnAceptar = dialogView.findViewById<View>(R.id.BtnAceptar) as Button
        val BtnNo = dialogView.findViewById<View>(R.id.BtnNO) as Button
        val TxtTitle = dialogView.findViewById<View>(R.id.TxtNameCliente) as TextView
        val TxtMesaagee = dialogView.findViewById<View>(R.id.message) as TextView
        TxtTitle.text = TituloMensaje
        TxtMesaagee.text = MensajeCUerpo
        val builder = AlertDialog.Builder(activity)
        builder.setView(dialogView)
        val dialog = builder.create()
        dialog.show()
        dialog.window!!.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        //dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.window!!.setGravity(Gravity.BOTTOM)
        BtnAceptar.setOnClickListener {
            ans_true!!.run()
            dialog.dismiss()
        }
        BtnNo.setOnClickListener {
            ans_false!!.run()
            dialog.dismiss()
        }
    }


    fun LoadInfoCLiente(context: Context?, ob: PedidosDataItem) {
        val dialog = Dialog(context!!)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.item_data_cliente)
        val tvDocumento = dialog.findViewById<View>(R.id.TvDocumentoCliente) as EditText
        val tvTelefono = dialog.findViewById<View>(R.id.TvTelefonoCliente) as EditText
        val tvEmail = dialog.findViewById<View>(R.id.TvCorreoCliente) as EditText
        val tvDireccion = dialog.findViewById<View>(R.id.TvDireccionCliente) as EditText
        val TxtNameCliente = dialog.findViewById<View>(R.id.TxtNameCliente) as TextView
        tvDocumento.setText(FuncPrincipales.parseTrimValue(ob.getDocumentoFiscal()))
        TxtNameCliente.setText(FuncPrincipales.parseTrimValue(ob.getNombresCompletoContacto()))
        tvTelefono.setText(FuncPrincipales.parseTrimValue(ob.getTelefonoContacto()))
        tvEmail.setText(FuncPrincipales.parseTrimValue(ob.getCorreoElectronico()))
        tvDireccion.setText(FuncPrincipales.parseTrimValue(ob.getDireccionContacto()))
        dialog.show()
        dialog.window!!.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        //dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.window!!.setGravity(Gravity.BOTTOM)
        dialog.show()
        dialog.window!!.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        //dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.window!!.setGravity(Gravity.BOTTOM)
    }

    fun ShowMessageConfirmDosBotones(
        TituloMensaje: String?,
        MensajeCUerpo: String?,
        activity: Activity,
        bProcedure: Runnable?,
        aProcedure: Runnable?,
        TextButtonSi: String?,
        TextButtonNo: String?
    ) {
        //before inflating the custom alert dialog layout, we will get the current activity viewgroup
        val viewGroup = activity.findViewById<ViewGroup>(R.id.content)

        //then we will inflate the custom alert dialog xml that we created
        val dialogView: View =
            LayoutInflater.from(activity).inflate(R.layout.item_question, viewGroup, false)
        ans_true = aProcedure
        ans_false = bProcedure
        val BtnAceptar = dialogView.findViewById<View>(R.id.BtnAceptar) as Button
        val BtnNo = dialogView.findViewById<View>(R.id.BtnNO) as Button
        val TxtTitle = dialogView.findViewById<View>(R.id.TxtNameCliente) as TextView
        val TxtMesaagee = dialogView.findViewById<View>(R.id.message) as TextView
        TxtTitle.text = TituloMensaje
        TxtMesaagee.text = MensajeCUerpo
        BtnAceptar.text = TextButtonSi
        BtnNo.text = TextButtonNo
        val builder = AlertDialog.Builder(activity)
        builder.setView(dialogView)
        val dialog = builder.create()
        dialog.show()
        dialog.window!!.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        //dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.window!!.setGravity(Gravity.BOTTOM)
        BtnAceptar.setOnClickListener {
            ans_true!!.run()
            dialog.dismiss()
        }
        BtnNo.setOnClickListener {
            ans_false!!.run()
            dialog.dismiss()
        }
    }

    */
}