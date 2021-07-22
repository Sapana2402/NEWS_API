package com.example.news.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.news.Adapter.HeadLinesAdapter;
import com.example.news.Model.Articles;
import com.example.news.Model.HeadLines;
import com.example.news.Network.ApiInstance;
import com.example.news.Network.ApiInterface;
import com.example.news.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.news.Utils.Contants.APIKEY;
import static com.example.news.Utils.Contants.COUNTRY;

public class HeadLinesFragment extends Fragment {
    private View view;
    private RecyclerView recyclerView;
    private HeadLinesAdapter adapter;
    private List<Articles> articles=new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.head_lines_fragment, container, false);

        initComponents();

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        retriveJSON(COUNTRY,APIKEY);
        return view;
    }

    private void retriveJSON(String country, String apikey) {
        ApiInterface apiInterface= ApiInstance.getRetrofitInstance().create(ApiInterface.class);

        Call<HeadLines> call=apiInterface.getData(country, apikey);
        call.enqueue(new Callback<HeadLines>() {
            @Override
            public void onResponse(Call<HeadLines> call, Response<HeadLines> response) {
                if (response.isSuccessful() && response.body().getArticlesList() !=null){
                    articles.clear();
                    articles=response.body().getArticlesList();
                    adapter=new HeadLinesAdapter(articles,getContext());
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<HeadLines> call, Throwable t) {
                Toast.makeText(getContext(), "Something went wroung", Toast.LENGTH_SHORT)
                        .show();
            }
        });

    }

    /* public String getCountry(){
        Locale locale=Locale.getDefault();
        String country =locale.getCountry();
        return country.toLowerCase();
    }
*/

    private void initComponents() {
        recyclerView=view.findViewById(R.id.recyclerView);
    }
}
