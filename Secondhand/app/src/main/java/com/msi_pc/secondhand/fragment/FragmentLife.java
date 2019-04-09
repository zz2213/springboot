package com.msi_pc.secondhand.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;
import com.msi_pc.secondhand.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 * @title: FragmentLife
 * @projectName Secondhand
 * @description: TODO
 * @date 2019/4/913:02
 */
public class FragmentLife  extends Fragment
       {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_life,container,false);
        return view;
    }



}
