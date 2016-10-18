package katey2658.com.my.dishwell;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 11456 on 2016/10/15.
 */

public class ShowDishesActivity extends Activity {

    private Context context;
    private RecyclerView recyclerView;
    private List<Cook> cookList;
    private RecyclerViewAdapter adapter;
    private GetNetDishData getNetDishDate;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dishlist);
        context=ShowDishesActivity.this;
        cookList=new ArrayList<Cook>();
        initView();
        initDate();
        recyclerView.setAdapter(adapter);
    }

    //做一些初始化的数据
    private void initDate() {
        getNetDishDate=new GetNetDishData(context,cookList);
        getNetDishDate.getCookJsonObject();
        cookList=getNetDishDate.getCookList();
        adapter=new RecyclerViewAdapter(cookList,context);
    }

    private void initView() {
        recyclerView= (RecyclerView) findViewById(R.id.recyclerView_dishlist);
        recyclerView.setHasFixedSize(true);
        //设置线性布局管理器

        LinearLayoutManager  linearLayoutM=new LinearLayoutManager(context);
        recyclerView.setLayoutManager(linearLayoutM);
    }
}
