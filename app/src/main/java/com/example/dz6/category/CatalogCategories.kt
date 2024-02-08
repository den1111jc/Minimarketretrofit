package com.example.dz6.category

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dz6.api.ApiClient
import com.example.dz6.api.models.CategoriesApiModel
import com.example.dz6.databinding.CatalogCategoriesBinding
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CatalogCategories : Fragment() {

    private var binding: CatalogCategoriesBinding? = null
    private var categoriesAdapter: CategoriesAdapter? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = CatalogCategoriesBinding.inflate(inflater, container, false)

        loadCategories()

        binding?.deleteAllCategories?.setOnClickListener(View.OnClickListener {

            clearAllCategories()

        })

        return binding?.root
    }


    private fun loadCategories () {   // при работе с БД инициализация РВ и загрузка с БД должны происходит в одном методе

        val callCategories = ApiClient.instance?.api?.getCategory()    //мы связываем апи клиент с апи интерфейсом и просим загрузить все категории
        callCategories?.enqueue(object: Callback<ArrayList<CategoriesApiModel>> {   // теперь то что мы получили мы говорим выдавать в формате модели апи модел
            override fun onResponse(
                call: Call<ArrayList<CategoriesApiModel>>,
                response: Response<ArrayList<CategoriesApiModel>>     // респонз это объект который хранит в себе все данные
            ) {

                val loadCategories = response.body()   //метод боди возвратит все данные которые мы должны получить, все данные с сервера мы засунем в лоад категорис

                binding?.recyclerCategories?.layoutManager = LinearLayoutManager(context)

                categoriesAdapter = loadCategories?.let {     //передаем все данные из лоад категорис со всеми методами в адаптер
                    CategoriesAdapter(
                        it, { idCategory:Int->deleteCategory(idCategory)},
                        {categoriesApiModel: CategoriesApiModel ->editCategory(categoriesApiModel)})
                }
                binding?.recyclerCategories?.adapter = categoriesAdapter

                Toast.makeText(context, "ЗАГРУЗКА", Toast.LENGTH_SHORT).show()


            }

            override fun onFailure(call: Call<ArrayList<CategoriesApiModel>>, t: Throwable) {
                Toast.makeText(context, "ОШИБКА! ВКЛЮЧИТЕ ИНТЕРНЕТ!", Toast.LENGTH_SHORT).show()

            }
        })

    }

    private fun deleteCategory(id:Int) {

        val callDeleteCategory: Call<ResponseBody?>? = ApiClient.instance?.api?.deleteCategory(id)

        callDeleteCategory?.enqueue(object : Callback<ResponseBody?> {     //метод енкуее используется для синхронного и асинхронного вызова
            override fun onResponse(call: Call<ResponseBody?>, response: Response<ResponseBody?>) {     //стандартный метод в случае удачного подключения к БД
                Toast.makeText(
                    context,
                    "КАТЕГОРИЯ УДАЛЕНА",
                    Toast.LENGTH_SHORT
                ).show()

                loadCategories()    // подгружаем новые актуальные данные после удаления наших категорий
            }



            override fun onFailure(call: Call<ResponseBody?>, t: Throwable) {     //стандартный метод в случае неудачного подключения к БД
                Toast.makeText(
                    context,
                    "ОШИБКА! ВКЛЮЧИТЕ ИНТЕРНЕТ!",
                    Toast.LENGTH_SHORT
                ).show()
            }


        })

    }


    private fun clearAllCategories() {

        val callClearAllCat: Call<ResponseBody?>? = ApiClient.instance?.api?.clearCategories()

        callClearAllCat?.enqueue(object : Callback<ResponseBody?> {
            override fun onResponse(call: Call<ResponseBody?>, response: Response<ResponseBody?>) {
                Toast.makeText(
                    context,
                    "КАТЕГОРИИ УДАЛЕНЫ",
                    Toast.LENGTH_SHORT
                ).show()

                loadCategories()
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

    private fun editCategory(categoriesApiModel: CategoriesApiModel) {
        val panelCategory = PanelEditCategory()
        val parameters = Bundle()
        parameters.putString("idCategory", categoriesApiModel.id.toString())
        parameters.putString("nameCategory", categoriesApiModel.name)
        panelCategory.arguments = parameters

        panelCategory.show((context as FragmentActivity).supportFragmentManager, "editCategory")
    }

}