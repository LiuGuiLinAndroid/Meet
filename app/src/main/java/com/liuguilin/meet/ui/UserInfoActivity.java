package com.liuguilin.meet.ui;

import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.liuguilin.meet.R;
import com.liuguilin.meet.base.BaseActivity;
import com.liuguilin.meet.fragment.MeFragment;
import com.liuguilin.meet.im.IMSDK;
import com.liuguilin.meet.im.IMUser;
import com.liuguilin.meet.manager.DialogManager;
import com.liuguilin.meet.utils.GlideUtils;
import com.liuguilin.meet.utils.PictureUtils;
import com.liuguilin.meet.view.DialogView;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * FileName: UserInfoActivity
 * Founder: 刘桂林
 * Create Date: 2019/5/6 22:22
 * Profile:个人信息
 */
public class UserInfoActivity extends BaseActivity implements View.OnClickListener {

    private RelativeLayout include_title_iv_back;
    private TextView include_title_text;
    private TextView title_right_text;
    private CircleImageView iv_photo;
    private LinearLayout ll_select_photo;
    private EditText et_desc;
    private EditText et_nickname;
    private TextView tv_sex;
    private LinearLayout ll_sex;
    private EditText et_age;
    private TextView tv_birthday;
    private LinearLayout ll_birthday;
    private EditText et_phone;
    private TextView tv_city;
    private LinearLayout ll_city;

    private DialogView mPhotoDialog;
    private TextView tv_camera;
    private TextView tv_ablum;
    private TextView tv_cancel;

    private static int TAKEPHOTO = 1000;
    private static int TAKEALBUM = 1001;
    private File tempFile;
    private Uri imageUri;
    private File uploadPhotoFile;

    private Uri uri;
    private String path = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        initPhotoDialog();
        initView();
    }

    private void initPhotoDialog() {
        mPhotoDialog = DialogManager.getInstance().initView(this, R.layout.dialog_user_photo, Gravity.BOTTOM);

        tv_camera = (TextView) mPhotoDialog.findViewById(R.id.tv_camera);
        tv_ablum = (TextView) mPhotoDialog.findViewById(R.id.tv_ablum);
        tv_cancel = (TextView) mPhotoDialog.findViewById(R.id.tv_cancel);

        tv_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogManager.getInstance().hide(mPhotoDialog);
                toCamera();
            }
        });
        tv_ablum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogManager.getInstance().hide(mPhotoDialog);
                toAlbum();
            }
        });
        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogManager.getInstance().hide(mPhotoDialog);
            }
        });
    }

    private void initView() {
        include_title_iv_back = (RelativeLayout) findViewById(R.id.include_title_iv_back);
        include_title_text = (TextView) findViewById(R.id.include_title_text);
        title_right_text = (TextView) findViewById(R.id.title_right_text);

        iv_photo = (CircleImageView) findViewById(R.id.iv_photo);
        ll_select_photo = (LinearLayout) findViewById(R.id.ll_select_photo);

        et_desc = (EditText) findViewById(R.id.et_desc);
        et_nickname = (EditText) findViewById(R.id.et_nickname);

        tv_sex = (TextView) findViewById(R.id.tv_sex);
        ll_sex = (LinearLayout) findViewById(R.id.ll_sex);

        et_age = (EditText) findViewById(R.id.et_age);

        tv_birthday = (TextView) findViewById(R.id.tv_birthday);
        ll_birthday = (LinearLayout) findViewById(R.id.ll_birthday);

        et_phone = (EditText) findViewById(R.id.et_phone);

        tv_city = (TextView) findViewById(R.id.tv_city);
        ll_city = (LinearLayout) findViewById(R.id.ll_city);

        include_title_iv_back.setOnClickListener(this);
        title_right_text.setOnClickListener(this);
        ll_select_photo.setOnClickListener(this);
        ll_sex.setOnClickListener(this);
        ll_birthday.setOnClickListener(this);
        ll_city.setOnClickListener(this);

        include_title_text.setText(getString(R.string.str_user_edit_user));
        title_right_text.setText(getString(R.string.str_common_save));

        IMUser imUser = IMSDK.getCurrentUser();

        if(imUser == null){
            return;
        }

        //头像
        if(!TextUtils.isEmpty(MeFragment.PhotoUrl)){
            GlideUtils.loadImg(this,MeFragment.PhotoUrl,R.drawable.img_def_photo,iv_photo);
        }

        et_desc.setText(MeFragment.DESC);
        et_nickname.setText(MeFragment.NICKNAME);
        tv_sex.setText(MeFragment.SEX ? getString(R.string.str_common_boy) : getString(R.string.str_common_girl));

        et_age.setText(imUser.getAge() + "");
        tv_birthday.setText(imUser.getBirthday());

        et_phone.setText(MeFragment.ACCOUNT);

        tv_city.setText(imUser.getCity());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.include_title_iv_back:
                finish();
                break;
            case R.id.title_right_text:

                break;
            case R.id.ll_select_photo:
                DialogManager.getInstance().show(mPhotoDialog);
                break;
            case R.id.ll_sex:

                break;
            case R.id.ll_birthday:

                break;
            case R.id.ll_city:

                break;
        }
    }

    /**
     * 相机
     */
    private void toCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        SimpleDateFormat timeStampFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
        String filename = timeStampFormat.format(new Date());
        tempFile = new File(Environment.getExternalStorageDirectory(), filename + ".jpg");
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            // 从文件中创建uri
            imageUri = Uri.fromFile(tempFile);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        } else {
            //兼容android7.0 使用共享文件的形式
            ContentValues contentValues = new ContentValues(1);
            contentValues.put(MediaStore.Images.Media.DATA, tempFile.getAbsolutePath());
            imageUri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        }
        startActivityForResult(intent, TAKEPHOTO);
    }

    /**
     * 相册
     */
    private void toAlbum() {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, TAKEALBUM);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == TAKEPHOTO) {
                uploadPhotoFile = tempFile;
            } else if (requestCode == TAKEALBUM) {
                if (data != null) {
                    uri = data.getData();
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                        path = this.uri.getPath();
                        path = PictureUtils.getPath_above19(this, uri);
                    } else {
                        path = PictureUtils.getFilePath_below19(this, this.uri);
                    }
                    if (!TextUtils.isEmpty(path)) {
                        uploadPhotoFile = new File(path);
                    }
                }
            }
            if (uploadPhotoFile != null) {
                GlideUtils.loadFile(this, uploadPhotoFile, R.drawable.img_load_img, iv_photo);
            }
        }
    }
}
