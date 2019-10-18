package com.example.singelfragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.singelfragment.adapter.ExampleAdapter;
import com.example.singelfragment.data.Example;

import java.util.ArrayList;

public class BaseFragment extends Fragment {

    private RecyclerView recyclerView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View mView = inflater.inflate(R.layout.base_fragment_view, container, false);
        recyclerView = mView.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return mView;
    }

    void loadData(ArrayList<Example> mCurrentList) {
        if (recyclerView != null) {
            recyclerView.setAdapter(new ExampleAdapter(mCurrentList));
        }
    }
}