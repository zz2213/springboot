package com.msi_pc.secondhand.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.msi_pc.secondhand.R;

/**
 * @author Administrator
 * @title: FragmentStudy
 * @projectName Secondhand
 * @description: TODO
 * @date 2019/4/913:03
 */
public class FragmentStudy extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_study,null);
        return view;

    }
}
