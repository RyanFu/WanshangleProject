package com.wanshangle.ui.movie;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baidu.location.v;
import com.wanshangle.R;

/**
 * Created by Zhangneixian on 13-9-22.
 */
public class MovieSearchFragment extends Fragment {

    private ViewPager mPaper;

    public static MovieSearchFragment newInstance()
    {
        MovieSearchFragment mF = new MovieSearchFragment();
        return mF;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_search, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        MovieListAdapter adapter = new MovieListAdapter(getActivity());
        mPaper = (ViewPager) getView().findViewById(R.id.fragment_movie_search_paper);
        mPaper.setAdapter(adapter);
        mPaper.setPageTransformer(true, new ZoomOutPageTransformer());


    }



    static class MovieListAdapter extends PagerAdapter
    {
        private Context mContext;

        public MovieListAdapter(Context context)
        {
            this.mContext = context;
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public boolean isViewFromObject(View view, Object o) {
            return view == o;
        }

        @Override
        public View instantiateItem(ViewGroup container, int position) {
            View view = View.inflate(mContext, R.layout.fragment_movie_search_paper_item, container);
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View)object);

        }
    }


    static class ZoomOutPageTransformer implements ViewPager.PageTransformer {
        private static float MIN_SCALE = 0.85f;
        private static float MIN_ALPHA = 0.5f;

        public void transformPage(View view, float position) {
            int pageWidth = view.getWidth();
            int pageHeight = view.getHeight();

            if (position < -1) { // [-Infinity,-1)
                // This page is way off-screen to the left.
                view.setAlpha(0);

            } else if (position <= 1) { // [-1,1]
                // Modify the default slide transition to shrink the page as well
                float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
                float vertMargin = pageHeight * (1 - scaleFactor) / 2;
                float horzMargin = pageWidth * (1 - scaleFactor) / 2;
                if (position < 0) {
                    view.setTranslationX(horzMargin - vertMargin / 2);
                } else {
                    view.setTranslationX(-horzMargin + vertMargin / 2);
                }

                // Scale the page down (between MIN_SCALE and 1)
                view.setScaleX(scaleFactor);
                view.setScaleY(scaleFactor);

                // Fade the page relative to its size.
                view.setAlpha(MIN_ALPHA +
                        (scaleFactor - MIN_SCALE) /
                                (1 - MIN_SCALE) * (1 - MIN_ALPHA));

            } else { // (1,+Infinity]
                // This page is way off-screen to the right.
                view.setAlpha(0);
            }
        }
    }
}


