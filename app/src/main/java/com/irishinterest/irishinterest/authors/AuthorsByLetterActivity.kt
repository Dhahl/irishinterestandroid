package com.irishinterest.irishinterest.authors

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.irishinterest.irishinterest.R
import com.irishinterest.irishinterest.webservice.Author
import com.irishinterest.irishinterest.webservice.CountByLetter

class AuthorsByLetterActivity : AppCompatActivity() {

    private lateinit var viewModel: AuthorsViewModel
    private lateinit var recycler_view: RecyclerView
    private lateinit var progress_bar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.authors_screen_fragment)

        progress_bar = findViewById<ProgressBar>(R.id.progress_bar)
        recycler_view = findViewById<RecyclerView>(R.id.authors_recycler_view)
        val linearLayout = LinearLayoutManager(this)
        recycler_view.layoutManager = linearLayout
        startLoading()

        viewModel = ViewModelProvider(this).get(AuthorsViewModel::class.java)

        val letter = intent.extras?.getString("letter").orEmpty()
        viewModel.authorsByLetter(letter).observe(this, Observer(this::onData))

//        setSupportActionBar(findViewById(R.id.toolbar))

//        binding.toolbarLayout.title = title
//        binding.fab.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show()
//        }
    }

    private fun onData(list: List<Author>?) {
        if (recycler_view.adapter != null) {
            //make sure it's not reloaded, when we re-visit from <- Back button
            return
        }
        recycler_view.adapter = AuthorsByLetterListingAdapter(
            list.orEmpty(),
            layoutInflater,
            { author -> onAuthorSeleted(author) }
        )
        stopLoading()
    }

    private fun onAuthorSeleted(author: Author) {

    }

    private fun startLoading() {
        progress_bar.visibility = View.VISIBLE
        recycler_view.visibility = View.GONE
    }

    private fun stopLoading() {
        progress_bar.visibility = View.GONE
        recycler_view.visibility = View.VISIBLE
    }
}