package com.fefeyo.parsefavoriter;


import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;


/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteFragment extends Fragment {

    @InjectView(R.id.favorite_list)
    ListView mListView;

    public FavoriteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_favorite, container, false);
        ButterKnife.inject(this, v);
        if(null == ParseUser.getCurrentUser()){
            Toast.makeText(getActivity(), "ログインしてください", Toast.LENGTH_SHORT).show();
        }else {
            ParseQuery<Favorite> query = new ParseQuery<>(Favorite.class);
            query.whereContains("original", ParseUser.getCurrentUser().getObjectId());
            query.findInBackground(new FindCallback<Favorite>() {
                @Override
                public void done(List<Favorite> list, ParseException e) {
                    if (null != list) {
                        final ArrayAdapter<Favorite> adapter = new ArrayAdapter<>(
                                getActivity().getApplicationContext(),
                                R.layout.row,
                                list
                        );
                        mListView.setAdapter(adapter);
                    }
                }
            });
        }
        return v;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
