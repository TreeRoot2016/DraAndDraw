package com.tree.android.draanddraw;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * <pre>
 *     author : tree_root
 *     e-mail : xxx@xx
 *     time   : 2018/06/08
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class DragAndDrawFragment  extends Fragment{

    public static DragAndDrawFragment newInstance() {

        Bundle args = new Bundle();

        DragAndDrawFragment fragment = new DragAndDrawFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_drag_and_draw, container, false);
        return view;
    }
}
