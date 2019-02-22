package suncere.shanxi.androidapp.ui;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.mcxtzhang.indexlib.IndexBar.widget.IndexBar;
import com.mcxtzhang.indexlib.suspension.SuspensionDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import suncere.shanxi.androidapp.BR;
import suncere.shanxi.androidapp.R;
import suncere.shanxi.androidapp.adapter.CityAdapter;
import suncere.shanxi.androidapp.customview.DividerItemDecoration;
import suncere.shanxi.androidapp.model.entity.CityBean;
import suncere.shanxi.androidapp.utils.Tools;

/**
 * Created by HJO on 2017/9/2.
 */

public class HomeSelectFollowCityActivity extends BaseActivity implements CityAdapter.RecyclerViewOnItmeClickListener ,CityAdapter.OnItmeViewBinding{

    @BindView(R.id.homeselectfollow_recyclerview)
    RecyclerView mRv;


    @BindView(R.id.homeselectfollow_index_indexBar)
    IndexBar mIndexBar;

    @BindView(R.id.homeselectfollow_index_tv)
    TextView mTvSideBarHint;

    private CityAdapter<CityBean> mAdapter;
    private LinearLayoutManager mManager;
    private List<CityBean> mDatas;
    private SuspensionDecoration mDecoration;
    Tools mTools;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homeselectfollow_activity);
        ButterKnife.bind(this);
        initView();
    }


    private void initView(){
        mTools=new Tools(this);
        mDatas = new ArrayList<>();

        mRv.setLayoutManager(mManager = new LinearLayoutManager(this));
        mAdapter = new CityAdapter(this, R.layout.homeselctfollow_recycler_itme, BR.SeachBean);
        mAdapter.setOnItmeClickListener(this);
        mRv.setAdapter(mAdapter);
        mRv.addItemDecoration(mDecoration = new SuspensionDecoration(this, mDatas));
        //如果add两个，那么按照先后顺序，依次渲染。
        mRv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));


        //indexbar初始化
        mIndexBar.setmPressedShowTextView(mTvSideBarHint)//设置HintTextView
                .setNeedRealIndex(true)//设置需要真实的索引
                .setmLayoutManager(mManager);//设置RecyclerView的LayoutManager

        String[]Cities=mTools.getAllCities().split(",");
        initDatas(Cities);

    }


    /**
     * 组织数据源
     *
     * @param data
     * @return
     */
    private void initDatas(final String[] data) {
        mDatas.clear();
        String[]followies=mTools.getFollowCities().split(",");

        for (int i = 0; i < data.length; i++) {
            CityBean cityBean = new CityBean();
            cityBean.setCity(data[i]);//设置城市名称
            cityBean.setSelect(false);
            for (int k=0;k<followies.length;k++){
                if (data[i].equals(followies[k])){
                    cityBean.setSelect(true);
                    break;
                }
            }
            mDatas.add(cityBean);
        }

        mAdapter.setData(mDatas);
        mIndexBar.setmSourceDatas(mDatas)//设置数据
                .invalidate();
        mDecoration.setmDatas(mDatas);
    }




    @OnClick({R.id.homeselectfollow_back, R.id.homeselectfollow_sall})
    public void on_click(View view){
        switch (view.getId()){
            case R.id.homeselectfollow_back:
                finish();
                break;
            case R.id.homeselectfollow_sall:
                // 保存操作
                saveFollowCity();

                break;
        }
    }

    private void selectAll(){
        for (int i=0;i<mDatas.size();i++){
            mDatas.get(i).setSelect(true);
        }
        mAdapter.notifyDataSetChanged();
    }
    private void unselectAll(){
        for (int i=0;i<mDatas.size();i++){
            mDatas.get(i).setSelect(false);
        }
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void OnItemClick(Object obejct, int position) {
        CityBean bean= (CityBean) obejct;
        bean.setSelect(!bean.isSelect());
        mAdapter.notifyDataSetChanged();

    }

    private void saveFollowCity(){
        StringBuilder stringBuilder=new StringBuilder();
        for (CityBean bean : mDatas){
            if (bean.isSelect())stringBuilder.append(bean.getCity()+",");
        }
        String cities=stringBuilder.toString();
        if (cities.length()>0){
            cities=cities.substring(0,cities.length()-1);// 去掉最后一个逗号
            mTools.setFollowCities(cities);
            finish();
        }else{
            Toast.makeText(this,"至少选择一个城市！", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void OnItmeView(View view, Object obejct, int position) {
//        TextView cittTV= MyViewHolder.getView(view,R.id.homeselectfollow_city);
    }
}
