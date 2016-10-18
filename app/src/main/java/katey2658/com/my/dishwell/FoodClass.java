package katey2658.com.my.dishwell;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by 11456 on 2016/10/14.
 */

//菜谱分类
public class FoodClass implements Parcelable{

    private int id;  //分类ID,需要查询该类下的列表就需要传入
    private int cookClass;//分类名称
    private String name;//上级分类IO,0为顶级
    private String title;//分类的标题
    private String keywords;//分类关键词
    private String decription;//分类描述
    private int seq;//分类排序，从小到大递增

    protected FoodClass(Parcel in) {
        id = in.readInt();
        cookClass = in.readInt();
        name = in.readString();
        title = in.readString();
        keywords = in.readString();
        decription = in.readString();
        seq = in.readInt();
    }

    public static final Creator<FoodClass> CREATOR = new Creator<FoodClass>() {
        @Override
        public FoodClass createFromParcel(Parcel in) {
            return new FoodClass(in);
        }

        @Override
        public FoodClass[] newArray(int size) {
            return new FoodClass[size];
        }
    };

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCookClass(int cookClass) {
        this.cookClass = cookClass;
    }

    public void setDecription(String decription) {
        this.decription = decription;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public int getSeq() {
        return seq;
    }

    public String getTitle() {
        return title;
    }

    public int getCookClass() {
        return cookClass;
    }

    public int getId() {
        return id;
    }

    public String getDecription() {
        return decription;
    }

    public String getKeywords() {
        return keywords;
    }

    public String getName() {
        return name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(cookClass);
        dest.writeString(name);
        dest.writeString(title);
        dest.writeString(keywords);
        dest.writeString(decription);
        dest.writeInt(seq);
    }
}
