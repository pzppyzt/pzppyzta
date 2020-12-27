package com.example.my12_17.view.one;

import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.my12_17.R;
import com.example.my12_17.view.fragment.HomeFragment;
import com.example.my12_17.view.fragment.MineFragment;
import com.example.my12_17.view.fragment.ShoppingCarFragment;
import com.example.my12_17.view.fragment.SortFragment;
import com.example.my12_17.view.fragment.SpecialFragment;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    private RadioGroup radioGroup;
    private RadioButton rbtnHome;
    private RadioButton rbtnSpecial;
    private RadioButton rbtnsort;
    private RadioButton rbtnshoppingCar;
    private RadioButton rbtnmine;
    private List<Fragment> list;
    private ViewPager viewpager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();
    }

    private void initView() {

        rbtnHome = findViewById(R.id.btn_home);
        rbtnSpecial = findViewById(R.id.btn_special);
        rbtnsort = findViewById(R.id.btn_sort);
        rbtnshoppingCar = findViewById(R.id.btn_ShoppingCar);
        rbtnmine = findViewById(R.id.btn_mine);
        viewpager=findViewById(R.id.viewpager);
        radioGroup=findViewById(R.id.radioGroup);



        list = new ArrayList<>();
        list.add(new HomeFragment());
        list.add(new SpecialFragment());
        list.add(new SortFragment());
        list.add(new ShoppingCarFragment());
        list.add(new MineFragment());

        viewpager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return list.get(position);
            }

            @Override
            public int getCount() {
                return list.size();
            }
        });

        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        rbtnHome.setChecked(true);
                        break;
                    case 1:
                        rbtnSpecial.setChecked(true);
                        break;
                    case 2:
                        rbtnsort.setChecked(true);
                        break;
                    case 3:
                        rbtnshoppingCar.setChecked(true);
                        break;
                    case 4:
                        rbtnmine.setChecked(true);
                        break;
                }
            }
            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }
            public void onPageScrollStateChanged(int arg0) {

            }
        });

        radioGroup.setOnCheckedChangeListener(this);
    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.btn_home:
                viewpager.setCurrentItem(0);
                break;
            case R.id.btn_special:
                viewpager.setCurrentItem(1);
                break;
            case R.id.btn_sort:
                viewpager.setCurrentItem(2);
                break;
            case R.id.btn_ShoppingCar:
                viewpager.setCurrentItem(3);
                break;
            case R.id.btn_mine:
                viewpager.setCurrentItem(4);
                break;
        }
    }
}