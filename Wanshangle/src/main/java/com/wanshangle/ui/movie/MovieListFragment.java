package com.wanshangle.ui.movie;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wanshangle.R;

/**
 * Created by Zhangneixian on 13-9-22.
 */
public class MovieListFragment extends Fragment{

    public static MovieListFragment newInstance()
    {
        MovieListFragment mF = new MovieListFragment();
        return mF;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_list, container, false);
        return view;
    }
}
