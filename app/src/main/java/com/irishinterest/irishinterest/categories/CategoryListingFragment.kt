package com.irishinterest.irishinterest.categories

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.irishinterest.irishinterest.BooksActivity
import com.irishinterest.irishinterest.R
import com.irishinterest.irishinterest.listing.CategoriesViewModel
import com.irishinterest.irishinterest.webservice.Category

class CategoryListingFragment: Fragment() {

    private lateinit var viewModel: CategoriesViewModel
    private lateinit var category_recycler_view: RecyclerView
    private lateinit var progress_bar: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.categories, container, false)
        progress_bar = view.findViewById<ProgressBar>(R.id.progress_bar)
        category_recycler_view = view.findViewById<RecyclerView>(R.id.categoryRecyclerView)
        val linearLayout =  LinearLayoutManager(view.context)
        category_recycler_view.layoutManager = linearLayout
        startLoading()

        viewModel = ViewModelProvider(this).get(CategoriesViewModel::class.java)
        viewModel.categories().observe(this, Observer(this::onData))
        return view
    }

    private fun onData(categories: List<Category>?) {
        if (category_recycler_view.adapter != null) {
            //make sure it's not reloaded, when we re-visit from <- Back button
            return
        }
        category_recycler_view.adapter = CategoryListingAdapter(
            categories.orEmpty(),
            layoutInflater,
            { category -> openCategory(category) }
        )
        stopLoading()
    }

    private fun openCategory(category: Category) {
        context?.let {
            val intent = Intent(it, BooksActivity::class.java)
            intent.putExtra("type", "category")
            intent.putExtra("categoryName", category.displayName)
            intent.putExtra("categoryId", category.id)
            it.startActivity(intent)
        }
    }


    private fun startLoading() {
        progress_bar.visibility = View.VISIBLE
        category_recycler_view.visibility = View.GONE
    }

    private fun stopLoading() {
        progress_bar.visibility = View.GONE
        category_recycler_view.visibility = View.VISIBLE
    }

}