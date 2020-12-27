package com.example.my12_17.view;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import com.example.my12_17.R;
import com.sina.weibo.sdk.api.ImageObject;
import com.sina.weibo.sdk.api.MultiImageObject;
import com.sina.weibo.sdk.api.TextObject;
import com.sina.weibo.sdk.api.VideoSourceObject;
import com.sina.weibo.sdk.api.WebpageObject;
import com.sina.weibo.sdk.api.WeiboMultiMessage;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WbAuthListener;
import com.sina.weibo.sdk.common.UiError;
import com.sina.weibo.sdk.openapi.IWBAPI;
import com.sina.weibo.sdk.openapi.WBAPIFactory;
import com.sina.weibo.sdk.share.WbShareCallback;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.UUID;

public class ShareActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String APP_KY = "3692164087";
    private static final String REDIRECT_URL = "http://open.weibo.com/apps/3692164087/privilege/oauth";
    private static final String SCOPE = "email,direct_messages_read,direct_messages_write,"
            + "friendships_groups_read,friendships_groups_write,statuses_to_me_read,"
            + "follow_app_official_microblog," + "invitation_write";
    private IWBAPI mWBAPI;
    private Button btn_weibo;
    private CheckBox shareText;
    private CheckBox shareimage;
    private CheckBox mShareUrl;
    private CheckBox mShareMultiImage;
    private CheckBox mShareVideo;
    private RadioButton shareOnly;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);
        btn_weibo = findViewById(R.id.weibo);
        shareText = findViewById(R.id.share_text_cb);
        shareimage = findViewById(R.id.share_img_cb);
        shareOnly = findViewById(R.id.share_client_only);
        mShareUrl = findViewById(R.id.share_url_cb);
        mShareMultiImage = findViewById(R.id.share_multi_image_cb);
        mShareVideo = findViewById(R.id.share_video_cb);
        btn_weibo.setOnClickListener(this);
        initSdk();
        copy();
    }

    private void initSdk() {
        AuthInfo authInfo = new AuthInfo(this, APP_KY, REDIRECT_URL, SCOPE);
        mWBAPI = WBAPIFactory.createWBAPI(this);
        mWBAPI.registerApp(this, authInfo);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.weibo:
                //init sdk

                startAuth();
                doWeiboShare();
                break;

            
        }
    }

    private void startAuth() {
//auth
        mWBAPI.authorize(new WbAuthListener() {
            @Override
            public void onComplete(Oauth2AccessToken token) {
                Toast.makeText(ShareActivity.this, "微博授权成功", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(UiError error) {
                Toast.makeText(ShareActivity.this, "微博授权出错", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancel() {
                Toast.makeText(ShareActivity.this, "微博授权取消", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private class ShareCallback implements WbShareCallback {
        @Override
        public void onComplete() {
            Toast.makeText(ShareActivity.this, "分享成功", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onError(UiError error) {
            Toast.makeText(ShareActivity.this, "分享失败" + error.errorMessage, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCancel() {
            Toast.makeText(ShareActivity.this, "分享取消", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (mWBAPI != null) {
            mWBAPI.doResultIntent(data, new ShareCallback());
        }
    }

    private void copy () {
        copyFile("eeee.mp4");
        copyFile("aaa.png");
        copyFile("bbb.jpg");
        copyFile("ccc.JPG");
        copyFile("eee.jpg");
        copyFile("ddd.jpg");
        copyFile("fff.jpg");
        copyFile("ggg.JPG");
        copyFile("hhhh.jpg");
        copyFile("kkk.JPG");
    }



    private void copyFile ( final String fileName){
        final File file = new File(getExternalFilesDir(null).getPath() + "/" + fileName);
        if (!file.exists()) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        InputStream inputStream = getAssets().open(fileName);
                        OutputStream outputStream = new FileOutputStream(file);
                        byte[] buffer = new byte[1444];
                        int readSize;
                        while ((readSize = inputStream.read(buffer)) != 0) {
                            outputStream.write(buffer, 0, readSize);
                        }
                        inputStream.close();
                        outputStream.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            thread.start();
        }
    }

    private void doWeiboShare() {
        WeiboMultiMessage message = new WeiboMultiMessage();
        TextObject textObject = new TextObject();
        String text = "我正在使用微博客户端发博器分享文字。";
        if (shareText.isChecked()) {
            text = "";
        }
        textObject.text = text;
        message.textObject = textObject;


        if (shareimage.isChecked()) {
            ImageObject imageObject = new ImageObject();
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.aaaaa);
            imageObject.setImageData(bitmap);
            message.imageObject = imageObject;
        }

        if (mShareUrl.isChecked()) {
            WebpageObject webObject = new WebpageObject();
            webObject.identify = UUID.randomUUID().toString();
            webObject.title = "标题";
            webObject.description = "描述";
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher_background);
            ByteArrayOutputStream os = null;
            try {
                os = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 85, os);
                webObject.thumbData = os.toByteArray();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (os != null) {
                        os.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            webObject.actionUrl = "https://www.baidu.com";
            webObject.defaultText = "分享网页";
            message.mediaObject = webObject;
        }



        if (mShareMultiImage.isChecked()) {
            MultiImageObject multiImageObject = new MultiImageObject();
            ArrayList<Uri> list = new ArrayList<>();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                String authority = this.getPackageName() + ".fileProvider";
                list.add(FileProvider.getUriForFile(ShareActivity.this, authority, new File(getExternalFilesDir(null) + "/aaa.png")));
                list.add(FileProvider.getUriForFile(ShareActivity.this, authority, new File(getExternalFilesDir(null) + "/ccc.JPG")));
                list.add(FileProvider.getUriForFile(ShareActivity.this, authority, new File(getExternalFilesDir(null) + "/ddd.jpg")));
                list.add(FileProvider.getUriForFile(ShareActivity.this, authority, new File(getExternalFilesDir(null) + "/fff.jpg")));
                list.add(FileProvider.getUriForFile(ShareActivity.this, authority, new File(getExternalFilesDir(null) + "/ggg.JPG")));
                list.add(FileProvider.getUriForFile(ShareActivity.this, authority, new File(getExternalFilesDir(null) + "/eee.jpg")));
                list.add(FileProvider.getUriForFile(ShareActivity.this, authority, new File(getExternalFilesDir(null) + "/hhhh.jpg")));
                list.add(FileProvider.getUriForFile(ShareActivity.this, authority, new File(getExternalFilesDir(null) + "/kkk.JPG")));
            } else {
                list.add(Uri.fromFile(new File(getExternalFilesDir(null) + "/aaa.png")));
                list.add(Uri.fromFile(new File(getExternalFilesDir(null) + "/ccc.JPG")));
                list.add(Uri.fromFile(new File(getExternalFilesDir(null) + "/ddd.jpg")));
                list.add(Uri.fromFile(new File(getExternalFilesDir(null) + "/fff.jpg")));
                list.add(Uri.fromFile(new File(getExternalFilesDir(null) + "/ggg.JPG")));
                list.add(Uri.fromFile(new File(getExternalFilesDir(null) + "/eee.jpg")));
                list.add(Uri.fromFile(new File(getExternalFilesDir(null) + "/hhhh.jpg")));
                list.add(Uri.fromFile(new File(getExternalFilesDir(null) + "/kkk.JPG")));
            }
            multiImageObject.imageList = list;
            message.multiImageObject = multiImageObject;
        }

        if (mShareVideo.isChecked()) {
            // 分享视频
            VideoSourceObject videoObject = new VideoSourceObject();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                String filePath = getExternalFilesDir(null) + "eeee.mp4";
                File videoFile = new File(filePath);
                if (!videoFile.getParentFile().exists()) {
                    videoFile.getParentFile().mkdir();
                }
                videoObject.videoPath = FileProvider.getUriForFile(this, this.getPackageName() + ".fileProvider", videoFile);
            } else {
                videoObject.videoPath = Uri.fromFile(new File(getExternalFilesDir(null) + "/eeee.mp4"));
            }

            message.videoSourceObject = videoObject;
        }
        mWBAPI.shareMessage(message, shareOnly.isChecked());
    }
}