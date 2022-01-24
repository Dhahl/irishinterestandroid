package com.irishinterest.irishinterest.authors

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
import com.irishinterest.irishinterest.R
import com.irishinterest.irishinterest.webservice.CountByLetter

class AuthorsAtoZListingFragment : Fragment() {

    private lateinit var viewModel: AuthorsViewModel
    private lateinit var recycler_view: RecyclerView
    private lateinit var progress_bar: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.authors_screen_fragment, container, false)
        progress_bar = view.findViewById<ProgressBar>(R.id.progress_bar)
        recycler_view = view.findViewById<RecyclerView>(R.id.authors_recycler_view)
        val linearLayout = LinearLayoutManager(view.context)
        recycler_view.layoutManager = linearLayout
        startLoading()

        viewModel = ViewModelProvider(this).get(AuthorsViewModel::class.java)
        viewModel.authorsAtoZCount().observe(this, Observer(this::onData))
        return view
    }

    private fun onData(list: List<CountByLetter>?) {
        if (recycler_view.adapter != null) {
            //make sure it's not reloaded, when we re-visit from <- Back button
            return
        }
        recycler_view.adapter = TitleDetailListingAdapter<CountByLetter>(
            list.orEmpty(),
            layoutInflater,
            { countByLetter -> countByLetter.alpha },
            { countByLetter -> countByLetter.count.toString() },
            { countByLetter -> onSeleted(countByLetter.alpha) }
        )
        stopLoading()
    }

    private fun onSeleted(letter: String) {
        context?.let {
            val intent = Intent(it, AuthorsByLetterActivity::class.java)
            intent.putExtra("letter", letter)
//                intent.putExtra("categoryName", category.displayName)
//                intent.putExtra("categoryId", category.id)
            it.startActivity(intent)
        }
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