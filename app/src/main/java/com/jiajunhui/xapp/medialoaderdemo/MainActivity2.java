package com.jiajunhui.xapp.medialoaderdemo;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        TestFragment fragment = TestFragment.createInstance(getSupportFragmentManager());

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        fragmentTransaction.add(R.id.container, fragment);
        fragmentTransaction.show(fragment);

        fragmentTransaction.commitAllowingStateLoss();

    }


}
