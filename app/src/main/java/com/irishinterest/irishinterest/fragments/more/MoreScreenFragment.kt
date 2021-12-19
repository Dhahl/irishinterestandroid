package com.irishinterest.irishinterest.fragments.more

import android.content.Context
import android.widget.LinearLayout
import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import com.irishinterest.irishinterest.R
import android.content.Intent
import android.view.View
import com.irishinterest.irishinterest.PublishedBooksActivity
import com.irishinterest.irishinterest.BooksActivity
import com.irishinterest.irishinterest.model.user.UserValues
import com.irishinterest.irishinterest.helper.UserController
import android.widget.Toast
import androidx.fragment.app.Fragment

class MoreScreenFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        retainInstance = false
        if (view == null) {
            val context: Context? = getContext()
            val view: View = inflater.inflate(R.layout.more_screen_fragment, container, false)
            val publishedBooks: LinearLayout = view.findViewById(R.id.publishedBooks)
            val topSearches: LinearLayout = view.findViewById(R.id.topSearches)
            val comingSoon: LinearLayout = view.findViewById(R.id.comingSoon)
            val favourites: LinearLayout = view.findViewById(R.id.favourites)
            publishedBooks.setOnClickListener(View.OnClickListener { _ ->
                val intent = Intent(context, PublishedBooksActivity::class.java)
                context?.startActivity(intent)
            })
            topSearches.setOnClickListener(View.OnClickListener { _ ->
                val intent = Intent(activity, BooksActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                intent.putExtra("type", "topSearched")
                activity?.startActivity(intent)
            })
            comingSoon.setOnClickListener(View.OnClickListener { _ ->
                val intent = Intent(activity, BooksActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                intent.putExtra("type", "comingSoon")
                activity?.startActivity(intent)
            })
            favourites.setOnClickListener(View.OnClickListener { _ ->
                context?.let {
                    val user = UserController.getUser(it)
                    if (user != null) {
                        val intent = Intent(activity, BooksActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                        intent.putExtra("type", "favourites")
                        activity?.startActivity(intent)
                    } else {
                        Toast.makeText(
                            context,
                            "To access favourites you have to be logged in. Please login or create an account.",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            })
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}