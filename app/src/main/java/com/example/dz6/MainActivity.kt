package com.example.dz6

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.dz6.databinding.ActivityMainBinding
import com.example.dz6.CatalogClothes
import com.example.dz6.Panel
import com.example.dz6.category.CatalogCategories
import com.example.dz6.product.CatalogProducts

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        supportFragmentManager.beginTransaction().replace(R.id.content, Panel()).commit()

        binding?.bottomNav?.setOnItemSelectedListener { item ->

            when(item.itemId) {
                R.id.panelItemBottomNav -> supportFragmentManager.beginTransaction().replace(R.id.content, Panel()).commit()
                R.id.catalogProductsItemBottomNav -> supportFragmentManager.beginTransaction().replace(R.id.content, CatalogProducts()).commit()
                R.id.catalogClothesItemBottomNav -> supportFragmentManager.beginTransaction().replace(R.id.content, CatalogClothes()).commit()
                R.id.catalogCategoriesItemBottomNav -> supportFragmentManager.beginTransaction().replace(R.id.content, CatalogCategories()).commit()
            }

            return@setOnItemSelectedListener true
        }

        binding?.bottomNav?.selectedItemId = R.id.panelItemBottomNav
    }
}