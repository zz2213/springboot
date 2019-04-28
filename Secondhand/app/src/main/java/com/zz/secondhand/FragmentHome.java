package com.zz.secondhand;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.alibaba.fastjson.JSON;
import com.zz.secondhand.activity.ProductViewActivity;
import com.zz.secondhand.adapter.HomeAdapter;
import com.zz.secondhand.adapter.MyAdapter;
import com.zz.secondhand.entity.Product;
import com.zz.secondhand.entity.Token;
import com.zz.secondhand.utils.Myapplication;
import okhttp3.*;

import java.io.IOException;
import java.util.ArrayList;

import static com.zz.secondhand.utils.GlobalVariables.QUERRY_HOME_PRO;

/**
 * @Auther: msi-pc
 * @Date: 2019/4/2 14:10
 * @Description:
 */
public class FragmentHome extends Fragment implements ViewPager.OnPageChangeListener {
    private Myapplication myapplication;
    private ListView listView;
    ArrayList<Product> productArrayList;
    private ViewPager viewPager;
    private int[] imageResIds;
    private ArrayList<ImageView> imageViewList;
    private LinearLayout ll_point_container;
    private String[] contentDescs;
    private TextView tv_desc;
    private int previousSelectedPosition = 0;
    boolean isRunning = false;
    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home,null);


        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initViews();

        // Model数据
        initData();

        // Controller 控制器
        initAdapter();

        // 开启轮询
        new Thread() {
            @Override
            public void run() {
                isRunning = true;
                while (isRunning) {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    // 往下跳一位
                    getActivity().runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            System.out.println("设置当前位置: " + viewPager.getCurrentItem());
                            viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                        }
                    });
                }
            }
        }.start();

        listView=(ListView) getActivity().findViewById(R.id.recommend_list);
        myapplication =(Myapplication) getActivity().getApplication();
        Token token=new Token();
        token=myapplication.getToken();
        OkHttpClient okHttpClient =new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder()
                .add("token", JSON.toJSONString(token))
                .build();
        final Request request = new Request.Builder()
                .url(QUERRY_HOME_PRO)
                .post(requestBody)
                .build();
        Call call =okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String backmess = response.body().string();
                System.out.println("00000000000000000000"+backmess);
                if("token为空".equals(backmess))
                {
                    Intent intent = new Intent(getActivity(), Login.class);
                    startActivity(intent);

                }else if("token错误".equals(backmess)){
                    Intent intent = new Intent(getActivity(),Login.class);
                    startActivity(intent);
                }else {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            productArrayList = (ArrayList<Product>) JSON.parseArray(backmess,Product.class);
                            HomeAdapter adapter = new HomeAdapter(getContext(), R.layout.item_goods,productArrayList);
                            listView.setAdapter(adapter);
                            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    String data = productArrayList.get(position).getTitle();
                                    Intent intent = new Intent(getActivity(), ProductViewActivity.class);
                                    intent.putExtra("product",productArrayList.get(position));
                                    startActivity(intent);
                                }
                            });
                        }
                    });
                }

            }
        });

    }

    private void initViews() {
        viewPager = (ViewPager) getActivity().findViewById(R.id.viewpager);
        viewPager.addOnPageChangeListener(this);
        // 设置页面更新监听
//		viewPager.setOffscreenPageLimit(1);// 左右各保留几个对象
        ll_point_container = (LinearLayout) getActivity().findViewById(R.id.ll_point_container);

        tv_desc = (TextView) getActivity().findViewById(R.id.tv_desc);

    }
    private void initData() {
        // 图片资源id数组
        imageResIds = new int[]{R.drawable.p2, R.drawable.p2, R.drawable.p2, R.drawable.p2, R.drawable.p2};

        // 文本描述
        contentDescs = new String[]{
                "image1",
                "image2",
                "image3",
                "image4",
                "image5"
        };

        // 初始化要展示的5个ImageView
        imageViewList = new ArrayList<ImageView>();

        ImageView imageView;
        View pointView;
        LinearLayout.LayoutParams layoutParams;
        for (int i = 0; i < imageResIds.length; i++) {
            // 初始化要显示的图片对象
            imageView = new ImageView(getContext());
            imageView.setBackgroundResource(imageResIds[i]);
            imageViewList.add(imageView);

            // 加小白点, 指示器
            pointView = new View(getContext());
            pointView.setBackgroundResource(R.drawable.bkx_background_style);
            layoutParams = new LinearLayout.LayoutParams(5, 5);
            if (i != 0){
                layoutParams.leftMargin = 10;
            }

            // 设置默认所有都不可用
            pointView.setEnabled(false);
            ll_point_container.addView(pointView, layoutParams);
        }
    }
    private void initAdapter() {
        ll_point_container.getChildAt(0).setEnabled(true);
        tv_desc.setText(contentDescs[0]);
        previousSelectedPosition = 0;

        // 设置适配器
        viewPager.setAdapter(new MyAdapterone());

        // 默认设置到中间的某个位置
        int pos = Integer.MAX_VALUE / 2 - (Integer.MAX_VALUE / 2 % imageViewList.size());
        // 2147483647 / 2 = 1073741823 - (1073741823 % 5)
        viewPager.setCurrentItem(5000000); // 设置到某个位置
    }
    class MyAdapterone extends PagerAdapter {

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        // 3. 指定复用的判断逻辑, 固定写法
        @Override
        public boolean isViewFromObject(View view, Object object) {
//			System.out.println("isViewFromObject: "+(view == object));
            // 当划到新的条目, 又返回来, view是否可以被复用.
            // 返回判断规则
            return view == object;
        }

        // 1. 返回要显示的条目内容, 创建条目
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            System.out.println("instantiateItem初始化: " + position);
            // container: 容器: ViewPager
            // position: 当前要显示条目的位置 0 -> 4

//			newPosition = position % 5
            int newPosition = position % imageViewList.size();

            ImageView imageView = imageViewList.get(newPosition);
            // a. 把View对象添加到container中
            container.addView(imageView);
            // b. 把View对象返回给框架, 适配器
            return imageView; // 必须重写, 否则报异常
        }
        // 2. 销毁条目
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            // object 要销毁的对象
            System.out.println("destroyItem销毁: " + position);
            container.removeView((View) object);
        }
    }


    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {
// 新的条目被选中时调用
        System.out.println("onPageSelected: " + i);
        int newPosition = i % imageViewList.size();

        //设置文本
        tv_desc.setText(contentDescs[newPosition]);

//		for (int i = 0; i < ll_point_container.getChildCount(); i++) {
//			View childAt = ll_point_container.getChildAt(position);
//			childAt.setEnabled(position == i);
//		}
        // 把之前的禁用, 把最新的启用, 更新指示器
        ll_point_container.getChildAt(previousSelectedPosition).setEnabled(false);
        ll_point_container.getChildAt(newPosition).setEnabled(true);

        // 记录之前的位置
        previousSelectedPosition = newPosition;

    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }
}
