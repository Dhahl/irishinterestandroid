package com.irishinterest.irishinterest.fragments.search;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.irishinterest.irishinterest.BooksActivity;
import com.irishinterest.irishinterest.R;
import com.irishinterest.irishinterest.model.misc.GetAllBooksResponse;
import com.irishinterest.irishinterest.model.user.DefaultServerResponse;
import com.irishinterest.irishinterest.network.api.irishInterest.IrishInterestAPI;
import com.irishinterest.irishinterest.network.api.irishInterest.SingletonAPI;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.airbnb.lottie.network.FileExtension.JSON;


public class SearchFragment extends Fragment {

    private View view;
    private Context context;
    private Button button;
    private EditText searchInput;
    private TextView numberTitles;

    public SearchFragment() {
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(false);
        if (view == null) {
            context = getActivity();
            view = inflater.inflate(R.layout.search_fragment, container, false);
            button = view.findViewById(R.id.search_button);
            searchInput = view.findViewById(R.id.search_edit_text);
            numberTitles = view.findViewById(R.id.numberOfAvailableTitles);

            button.setOnClickListener(v -> {
                Intent intent = new Intent(context, BooksActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("type", "bookSearch");
                intent.putExtra("query", searchInput.getText().toString());
                context.startActivity(intent);
            });

            SingletonAPI.getInstance().getApiInterface().getNumberOfAllAvailableBooks("misc", "testApiKey", "testTkn", "getBookCount").enqueue(new Callback<GetAllBooksResponse>() {
                @Override
                public void onResponse(Call<GetAllBooksResponse> call, Response<GetAllBooksResponse> response) {
                    if(response.code() == 200){
                        numberTitles.setText(String.valueOf(response.body().getResponse().get(0).getNumberOfAllBooks()));
                    }
                }

                @Override
                public void onFailure(Call<GetAllBooksResponse> call, Throwable t) {
                    Log.d("TAG", "onFailure: ");
                }
            });

        }
        return view;
    }
}
