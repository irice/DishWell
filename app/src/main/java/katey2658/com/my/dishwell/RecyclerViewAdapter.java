package katey2658.com.my.dishwell;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


/**
 * Created by 11456 on 2016/10/15.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.CooksViewHolder> {

    private List<Cook>cookList;
    private Context context;
    private GetNetDishData getNetDishData;

    //构造方法，获得初始数据和上下文内容
    public RecyclerViewAdapter(List<Cook>cookList,Context context){
        this.cookList=cookList;
        this.context=context;
        getNetDishData=new GetNetDishData(context,cookList);

    }

    //分别创建和加载视图
    @Override
    public CooksViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.cook_item,null);
        CooksViewHolder cooksViewHolder=new CooksViewHolder(view);
        return cooksViewHolder;
    }

    //分别绑定视图内容
    @Override
    public void onBindViewHolder(CooksViewHolder holder, final int position) {
        Cook cook=cookList.get(position);
        getNetDishData.setNetBitmap(holder.cook_photo,cook.getImg());
        holder.cook_title.setText(cook.getName());
        holder.cook_desc.setText(cookList.get(position).getDescription());

        //为每一个单卡片设置动作，跳转到详细页面
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,CookActivity.class);
                intent.putExtra("cook",cookList.get(position));
                context.startActivity(intent);
            }
        });

        //对于分享按钮设置相应的点击事件
        holder.btn_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_share=new Intent(Intent.ACTION_SEND);
                intent_share.setType("text/plain");
                intent_share.putExtra(Intent.EXTRA_SUBJECT,"分享");
                intent_share.putExtra(Intent.EXTRA_TEXT,cookList.get(position).getDescription());
                intent_share.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent_share);
            }

        });
        //对阅读更多按钮设置动作事件
        holder.btn_readMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,CookActivity.class);
                intent.putExtra("cook",cookList.get(position));
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return cookList.size();
    }

    //自定义viewholder
    public class CooksViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView cook_photo;
        TextView cook_title;
        TextView cook_desc;
        Button btn_share;
        Button btn_readMore;
        public CooksViewHolder(View itemView) {
            super(itemView);
            cardView= (CardView) itemView.findViewById(R.id.card_cook);
            cook_photo= (ImageView) itemView.findViewById(R.id.cook_photo);
            cook_title= (TextView) itemView.findViewById(R.id.cook_title);
            cook_desc= (TextView) itemView.findViewById(R.id.cook_desc);
            btn_share= (Button) itemView.findViewById(R.id.btn_share);
            btn_readMore= (Button) itemView.findViewById(R.id.btn_more);

        }
    }

}
