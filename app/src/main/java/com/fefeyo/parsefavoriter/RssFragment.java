package com.fefeyo.parsefavoriter;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.parse.CountCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class RssFragment extends Fragment {

    private ListView mList;

    public RssFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_rss, container, false);
        mList = (ListView) v.findViewById(R.id.main_list);
        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final Favorite item = (Favorite) parent.getItemAtPosition(position);
                item.setOriginalId(ParseUser.getCurrentUser().getObjectId());
                final ProgressDialog dialog = new ProgressDialog(getActivity());
                dialog.setTitle("登録中");
                dialog.setMessage("お気に入りに登録中です。");
                dialog.show();
                ParseQuery<Favorite> query = new ParseQuery<>(Favorite.class);
                query.whereContains("original", ParseUser.getCurrentUser().getObjectId());
                query.whereContains("title", item.getTitle());
                query.countInBackground(new CountCallback() {
                    @Override
                    public void done(int i, ParseException e) {
                        if (0 == i) {
                            item.saveInBackground();
                        }else{
                            Log.i("おこだよ", "同じもん登録してくんなよおおおおおおおおおおおお");
                        }
                        dialog.dismiss();
                    }
                });
            }
        });
        setUpList();
        return v;
    }

    private void setUpList() {
        final ArrayList<Favorite> arr = new ArrayList<>();
        final Favorite gochiusa = new Favorite();
        gochiusa.setUrl("http://gochiusa.com");
        gochiusa.setTitle("ご注文はうさぎですか？？");
        gochiusa.setEntryId("00000001");
        final Favorite oregairu = new Favorite();
        oregairu.setUrl("http://www.tbs.co.jp/anime/oregairu/");
        oregairu.setTitle("やはり俺の青春ラブコメはまちがっている。");
        oregairu.setEntryId("00000002");
        final Favorite mikakunin = new Favorite();
        mikakunin.setUrl("http://mikakunin.jp/");
        mikakunin.setTitle("未確認で進行形");
        mikakunin.setEntryId("00000003");
        arr.add(gochiusa);
        arr.add(oregairu);
        arr.add(mikakunin);
        final ArrayAdapter<Favorite> adapter = new ArrayAdapter<>(
                getActivity().getApplicationContext(),
                R.layout.row,
                arr
        );
        mList.setAdapter(adapter);
    }


}
