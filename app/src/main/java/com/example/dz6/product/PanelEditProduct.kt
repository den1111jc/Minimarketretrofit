package com.example.dz6.product

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import com.example.dz6.R
import com.example.dz6.api.ApiClient
import com.example.dz6.databinding.PanelEditProductBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response

class PanelEditProduct : BottomSheetDialogFragment(),View.OnKeyListener, View.OnClickListener {

    private var binding: PanelEditProductBinding? = null
    private var idProduct:Int? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = PanelEditProductBinding.inflate(inflater, container, false)

        idProduct = arguments?.getString("idProduct")?.toInt()
        binding?.editNameProduct?.setText(arguments?.getString("nameProduct").toString())
        binding?.editCategoryProduct?.setText(arguments?.getString("categoryProduct").toString())
        binding?.editPriceProduct?.setText(arguments?.getString("priceProduct").toString())

        binding?.editNameProduct?.setOnKeyListener(this)
        binding?.editCategoryProduct?.setOnKeyListener(this)
        binding?.editPriceProduct?.setOnKeyListener(this)

        binding?.buttonEditProduct?.setOnClickListener(this)


        return binding?.root
    }

    override fun onKey(view: View, i: Int, keyEvent: KeyEvent): Boolean {
        when (view.id) {


            R.id.editNameProduct -> {
                if (keyEvent.action == KeyEvent.ACTION_DOWN && i == KeyEvent.KEYCODE_ENTER) {

                    binding?.resEditNameProduct?.text = binding?.editNameProduct?.text
                    binding?.editNameProduct?.setText("")

                    return true
                }

            }

            R.id.editCategoryProduct -> {
                if (keyEvent.action == KeyEvent.ACTION_DOWN && i == KeyEvent.KEYCODE_ENTER) {

                    binding?.resEditCategoryProduct?.text = binding?.editCategoryProduct?.text
                    binding?.editCategoryProduct?.setText("")

                    return true
                }

            }

            R.id.editPriceProduct -> {
                if (keyEvent.action == KeyEvent.ACTION_DOWN && i == KeyEvent.KEYCODE_ENTER) {

                    binding?.resEditPriceProduct?.text = binding?.editPriceProduct?.text
                    binding?.editPriceProduct?.setText("")

                    return true
                }

            }
        }

        return false
    }

    override fun onClick(view: View) {

        updateProduct(binding?.resEditNameProduct?.text?.toString()!!, binding?.resEditCategoryProduct?.text?.toString()!!,
            binding?.resEditPriceProduct?.text?.toString()!!)
    }




    private fun updateProduct(name: String, category: String, price: String) {
        val callUpdateProduct = ApiClient.instance?.api?.updateProduct(idProduct.toString().toInt(), name, category, price)

        callUpdateProduct?.enqueue(object : retrofit2.Callback<ResponseBody?> {
            override fun onResponse(call: Call<ResponseBody?>, response: Response<ResponseBody?>) {
                Toast.makeText(
                    context,
                    "ТОВАР ОБНОВЛЕН",
                    Toast.LENGTH_SHORT
                ).show()

                loadScreen()


            }

            override fun onFailure(call: Call<ResponseBody?>, t: Throwable) {
                Toast.makeText(
                    context,
                    "ОШИБКА! ВКЛЮЧИТЕ ИНТЕРНЕТ!",
                    Toast.LENGTH_SHORT
                ).show()
            }


        })


    }

    private fun loadScreen() {

        dismiss()

        (context as FragmentActivity).supportFragmentManager.beginTransaction()
            .replace(R.id.content, CatalogProducts()).commit()

    }

}