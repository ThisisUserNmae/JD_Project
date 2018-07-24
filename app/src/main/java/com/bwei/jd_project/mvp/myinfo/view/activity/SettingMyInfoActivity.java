package com.bwei.jd_project.mvp.myinfo.view.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bwei.jd_project.R;
import com.bwei.jd_project.mvp.myinfo.model.bean.UpLoadBean;
import com.bwei.jd_project.mvp.myinfo.presenter.UpLoadPresenter;
import com.bwei.jd_project.mvp.myinfo.view.iview.IUpLoadView;
import com.facebook.drawee.view.SimpleDraweeView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class SettingMyInfoActivity extends AppCompatActivity implements View.OnClickListener, IUpLoadView {

    private android.widget.LinearLayout lluserIconsetting;
    //private android.widget.TextView setUserName;
    private android.widget.TextView tvuserNamesetting;
    private android.widget.LinearLayout lluserNamesetting;
    //private android.widget.TextView setNickName;
    private android.widget.TextView tvnickNamesetting;
    private android.widget.LinearLayout llnickNamesetting;
    //private android.widget.TextView setGenter;
    private android.widget.TextView tvgendersetting;
    private android.widget.LinearLayout llgendersetting;
    //private android.widget.TextView setBirthday;
    private android.widget.LinearLayout lluserBirthdaysetting;
    private android.widget.Button btncancelsetting;

    private UpLoadPresenter upLoadPresenter;

    private String path = Environment.getExternalStorageDirectory() + "/JD.png";

    private SimpleDraweeView setUserPic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_my_info);

        initViews();

        initDatas();

    }

    private void initDatas() {


        Intent it = getIntent();

        String icon = it.getStringExtra("icon");

        setUserPic.setImageURI(icon);

    }

    private void initViews() {

        btncancelsetting = (Button) findViewById(R.id.btn_cancel_setting);
        lluserBirthdaysetting = (LinearLayout) findViewById(R.id.ll_userBirthday_setting);
        llgendersetting = (LinearLayout) findViewById(R.id.ll_gender_setting);
        //tvgendersetting = (TextView) findViewById(R.id.tv_gender_setting);
        llnickNamesetting = (LinearLayout) findViewById(R.id.ll_nickName_setting);
        //tvnickNamesetting = (TextView) findViewById(R.id.tv_nickName_setting);
        lluserNamesetting = (LinearLayout) findViewById(R.id.ll_userName_setting);
        //tvuserNamesetting = (TextView) findViewById(R.id.tv_userName_setting);
        lluserIconsetting = (LinearLayout) findViewById(R.id.ll_userIcon_setting);

        setUserPic = findViewById(R.id.setUserPic);

        upLoadPresenter = new UpLoadPresenter(this);

        lluserBirthdaysetting.setOnClickListener(this);
        llgendersetting.setOnClickListener(this);
        llnickNamesetting.setOnClickListener(this);
        lluserNamesetting.setOnClickListener(this);
        lluserIconsetting.setOnClickListener(this);

        btncancelsetting.setOnClickListener(this);

        lluserIconsetting.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                Intent it = getIntent();

                setResult(20, it);

                SharedPreferences sharedPreferences = getSharedPreferences("uid", MODE_PRIVATE);

                SharedPreferences.Editor editor = sharedPreferences.edit();

                editor.clear();

                editor.commit();

                finish();

                return false;
            }
        });

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.ll_userBirthday_setting:

                //Toast.makeText(SettingMyInfoActivity.this, "您点击了修改用户生日", Toast.LENGTH_SHORT).show();

                break;

            case R.id.ll_gender_setting:

                //Toast.makeText(SettingMyInfoActivity.this, "您点击了修改用户性别", Toast.LENGTH_SHORT).show();

                break;

            case R.id.ll_nickName_setting:

                //Toast.makeText(SettingMyInfoActivity.this, "您点击了修改用户昵称", Toast.LENGTH_SHORT).show();

                break;

            case R.id.ll_userName_setting:

                //Toast.makeText(SettingMyInfoActivity.this, "您点击了修改用户名字", Toast.LENGTH_SHORT).show();

                break;

            case R.id.ll_userIcon_setting:

                Intent it = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                it.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(path)));
                startActivityForResult(it, 1000);

                //Toast.makeText(SettingMyInfoActivity.this,"您点击了修改用户头像",Toast.LENGTH_SHORT).show();

                break;

            case R.id.btn_cancel_setting:

                Intent it1 = getIntent();

                SharedPreferences uid = getSharedPreferences("uid", MODE_PRIVATE);

                SharedPreferences.Editor editor = uid.edit();

                editor.clear();

                editor.commit();

                setResult(100, it1);

                finish();

                //Toast.makeText(SettingMyInfoActivity.this,"您点击了注销登录",Toast.LENGTH_SHORT).show();

                break;

        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1000 && resultCode == RESULT_OK) {

            Intent it = new Intent("com.android.camera.action.CROP");

            it.setDataAndType(Uri.fromFile(new File(path)), "image/*");

            it.putExtra("crop", true);

            it.putExtra("aspactX", 1);
            it.putExtra("aspactY", 1);

            it.putExtra("outputX", 250);
            it.putExtra("outputY", 250);

            it.putExtra("return-data", true);

            startActivityForResult(it, 2000);

        }

        if (requestCode == 2000 && resultCode == RESULT_OK) {

            Bitmap bitmap = data.getParcelableExtra("data");

            File file = new File(getFilesDir().getAbsolutePath());

            if (!file.exists()) {

                file.mkdirs();

            }

            File file1 = new File(file, "photo.png");
            FileOutputStream fileOutputStream;

            try {

                fileOutputStream = new FileOutputStream(file1);
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
                fileOutputStream.flush();
                fileOutputStream.close();

            } catch (Exception e) {
                e.printStackTrace();
            }

            RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), file1);

            MultipartBody.Part part = MultipartBody.Part.createFormData("file", file1.getName(), requestBody);

            SharedPreferences sharedPreferences = getSharedPreferences("uid", MODE_PRIVATE);

            boolean isOn = sharedPreferences.getBoolean("isOn", false);

            if (isOn) {

                int uid = sharedPreferences.getInt("uid", 0);

                upLoadPresenter.upLoadPresenter(uid, part);

            }

        }

    }

    @Override
    public void getSuccess(UpLoadBean upLoadBean) {

        String code = upLoadBean.getCode();

        if ("0".equals(code)) {

            //Toast.makeText(SettingMyInfoActivity.this, "上传头像成功!", Toast.LENGTH_SHORT).show();

            setUserPic.setImageURI(path);
        } else {

            //Toast.makeText(SettingMyInfoActivity.this, "上传头像失败,因为太丑!!!", Toast.LENGTH_SHORT).show();

        }

    }

    @Override
    public void getError(Throwable throwable) {

        //Toast.makeText(SettingMyInfoActivity.this, "" + throwable.getMessage(), Toast.LENGTH_SHORT).show();

    }
}
