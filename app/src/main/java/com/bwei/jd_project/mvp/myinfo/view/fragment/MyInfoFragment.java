package com.bwei.jd_project.mvp.myinfo.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bwei.jd_project.R;
import com.bwei.jd_project.mvp.myinfo.view.activity.LoginActivity;
import com.bwei.jd_project.mvp.myinfo.view.activity.SettingMyInfoActivity;
import com.facebook.drawee.view.SimpleDraweeView;

public class MyInfoFragment extends Fragment implements View.OnClickListener {

    private SimpleDraweeView userPic;

    private TextView userName;

    private android.widget.ImageView userSet;

    private android.widget.ImageView myMsg;

    private View view;
    private SharedPreferences sharedPreferences;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.myinfo_fragment_layout, container, false);

        sharedPreferences = getActivity().getSharedPreferences("uid", Context.MODE_PRIVATE);

        if (sharedPreferences.getBoolean("isOn", false) == false) {

            Toast.makeText(getActivity(), "请您先去登陆", Toast.LENGTH_SHORT).show();

            initViews();

        } else {
            initViews();

            initDatas();

        }

        return view;
    }

    public void initDatas() {

        //Toast.makeText(getActivity(),"请您先去登陆",Toast.LENGTH_SHORT).show();

        Toast.makeText(getActivity(), "登陆完成", Toast.LENGTH_SHORT).show();

        String icon = sharedPreferences.getString("icon", "ddd");

        String useruame = sharedPreferences.getString("username", "fly");

        userPic.setImageURI(icon);

        userName.setText(useruame);

    }

    private void initViews() {

        myMsg = view.findViewById(R.id.myMsg);

        userSet = view.findViewById(R.id.userSet);

        userName = view.findViewById(R.id.userName);

        userPic = view.findViewById(R.id.userPic);

        myMsg.setOnClickListener(this);

        userSet.setOnClickListener(this);

        userPic.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.myMsg:

                break;

            case R.id.userSet:

                break;

            case R.id.userPic:

                boolean isOn = sharedPreferences.getBoolean("isOn", false);

                if (isOn == false) {

                    Intent it = new Intent(getActivity(), LoginActivity.class);

                    startActivityForResult(it, 100);

                } else {

                    Intent it = new Intent(getActivity(), SettingMyInfoActivity.class);

                    SharedPreferences sharedPreferences = getActivity().getSharedPreferences("uid", Context.MODE_PRIVATE);

                    String icon = sharedPreferences.getString("icon", "");

                    it.putExtra("icon",icon);

                    startActivityForResult(it,1000);

                }

                break;

        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //登陆
        if (requestCode == 100 && resultCode == 10) {

            initDatas();

        }
        //注销登陆
        if (requestCode == 1000 && resultCode == 100){

            initDatas();

        }
        //修改用户头像
        if (requestCode == 1000 && resultCode == 20){

            initDatas();

        }

    }
}
