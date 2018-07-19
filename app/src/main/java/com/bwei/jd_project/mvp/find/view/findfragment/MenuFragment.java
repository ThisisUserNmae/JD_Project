package com.bwei.jd_project.mvp.find.view.findfragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bwei.jd_project.R;

public class MenuFragment extends Fragment{


    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.find_menufragment_layout,container,false);

        return view;

    }

    public static MenuFragment create(String title){

        Bundle bundle = new Bundle();

        bundle.putString("title",title);

        MenuFragment menuFragment = new MenuFragment();

        menuFragment.setArguments(bundle);

        return menuFragment;
    }

}
