package com.irishinterest.irishinterest.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.irishinterest.irishinterest.R
import com.irishinterest.irishinterest.listing.CategoriesViewModel

class CategoryListingFragment: Fragment() {

    private lateinit var viewModel: CategoriesViewModel
    private lateinit var category_recycler_view: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.categories, container, false)
        category_recycler_view = view.findViewById<RecyclerView>(R.id.categoryRecyclerView)
        category_recycler_view.layoutManager = LinearLayoutManager(this)

        startLoading()

        viewModel = ViewModelProvider(this).get(CategoriesViewModel::class.java)
        viewModel.categories().observe(this, Observer(this::onData))

        return view
    }

    private fun onData(categories: List<Category>?) {
        category_recycler_view.adapter = CategoryListingAdapter(
            categories.orEmpty(),
            layoutInflater,
            { category -> openCategory(category) }
        )
        stopLoading()
    }

    private fun openCategory(category: Category) {
        
    }


    private fun startLoading() {

    }

    private fun stopLoading() {

    }

}