package com.example.dz6.product

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dz6.api.models.ProductsApiModel
import com.example.dz6.databinding.ProductsItemBinding

class ProductsAdapter(private val productsList : ArrayList<ProductsApiModel>,
                      private val deleteProduct:(Int)->Unit,
                      private val editProduct:(ProductsApiModel)->Unit): RecyclerView.Adapter<ProductsAdapter.ProductsHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsHolder {

        val binding : ProductsItemBinding = ProductsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductsHolder(binding)
    }

    override fun getItemCount(): Int {
        return productsList.size
    }

    override fun onBindViewHolder(holder: ProductsHolder, position: Int) {
        holder.bind(productsList[position], deleteProduct, editProduct)
    }

    class ProductsHolder(val binding: ProductsItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(
            products: ProductsApiModel, deleteProduct: (Int) -> Unit, editProduct: (ProductsApiModel) -> Unit
        ) {

            val idProduct = products.id

            binding.idProduct.text = idProduct.toString()

            binding.nameProduct.text = products.name
            binding.categoryProduct.text = products.category
            binding.priceProduct.text = products.price.toString()


            binding.editProduct.setOnClickListener(View.OnClickListener {
                editProduct(products)
            })

            binding.deleteProduct.setOnClickListener(View.OnClickListener {
                deleteProduct(idProduct!!)
            })
        }

    }

}