 package com.steadyoung.tinkertest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.tencent.tinker.lib.util.TinkerLog;
import com.tencent.tinker.loader.shareutil.ShareTinkerInternals;
import com.tinkerpatch.sdk.TinkerPatch;
import com.tinkerpatch.sdk.server.callback.ConfigRequestCallback;

import java.util.HashMap;

 public class MainActivity extends AppCompatActivity {

     private static final String TAG = "Tinker.MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //请求获取补丁包
        findViewById(R.id.request_patch).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TinkerPatch.with().fetchPatchUpdate(true);
            }
        });

        //请求获取参数配置
        findViewById(R.id.request_config).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TinkerPatch.with().fetchDynamicConfig(new ConfigRequestCallback() {

                    @Override
                    public void onSuccess(final HashMap<String, String> configs) {
                        TinkerLog.w(TAG, "request config success, config:" + configs);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(MainActivity.this,"请求成功，参数配置：" + configs,Toast.LENGTH_SHORT).show();
                            }
                        });

                    }

                    @Override
                    public void onFail(final Exception e) {
                        TinkerLog.w(TAG, "request config failed, exception:" + e);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(MainActivity.this,"请求失败：" + e.getMessage(),Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                }, true);
            }
        });

        //清除补丁包事件
        findViewById(R.id.clean_patch).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TinkerPatch.with().cleanAll();
            }
        });

        //添加重启APP事件
        findViewById(R.id.restartApp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RestartAPPTool.restartAPP(MainActivity.this,500);
            }
        });


        //下面是测试tinker热修复添加的补丁包代码
        findViewById(R.id.add_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, R.string.fix_add_btn,Toast.LENGTH_SHORT).show();
            }
        });
        findViewById(R.id.add_iv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, R.string.fix_add_iv,Toast.LENGTH_SHORT).show();
            }
        });

    }

}
