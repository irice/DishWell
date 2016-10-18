package katey2658.com.my.dishwell;

import android.os.Parcel;
import android.os.Parcelable;


/**
 * Created by 11456 on 2016/10/14.
 */

//食物
public class Cook  implements Parcelable{

    private int id; //ID编码
    private String name;//名称
    private String food;//相关食物
    private String img;//图片
    private String images;//多图，中间用逗号隔开
    private String description;//简介
    private String keywords;//关键词
   // private String message;//内容
    private int count;//访问数
    private int rcount;//评论数
    private int fcount;//收藏数

    public Cook(int id
    ,String name
    ,String food
    ,String img
    ,String images
    ,String description
    ,String keywords
    ,int count
    ,int rcount
    ,int fcount){
        this.id=id;
        this.name=name;
        this.food=food;
        this.img=img;
        this.images=images;
        this.description=description;
        this.keywords=keywords;
        this.count=count;
        this.rcount=rcount;
        this.fcount=fcount;
    }

    protected Cook(Parcel in) {
        id = in.readInt();
        name = in.readString();
        food = in.readString();
        img = in.readString();
        images = in.readString();
        description = in.readString();
        keywords = in.readString();
       // message = in.readString();
        count = in.readInt();
        rcount = in.readInt();
        fcount = in.readInt();
    }

    public static final Creator<Cook> CREATOR = new Creator<Cook>() {
        @Override
        public Cook createFromParcel(Parcel in) {
            return new Cook(in);
        }

        @Override
        public Cook[] newArray(int size) {
            return new Cook[size];
        }
    };

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setFcount(int fcount) {
        this.fcount = fcount;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public void setImg(String img) {
        this.img = img;
    }

//    public void setMessage(String message) {
//        this.message = message;
//    }

    public void setRcount(int rcount) {
        this.rcount = rcount;
    }

    public String getName() {
        return name;
    }

    public String getKeywords() {
        return keywords;
    }

    public int getId() {
        return id;
    }

    public int getCount() {
        return count;
    }

    public int getFcount() {
        return fcount;
    }

    public int getRcount() {
        return rcount;
    }

    public String getDescription() {
        return description;
    }

    public String getFood() {
        return food;
    }

    public String getImages() {
        return images;
    }

    public String getImg() {
        return img;
    }

//    public String getMessage() {
//        return message;
//    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(food);
        dest.writeString(img);
        dest.writeString(images);
        dest.writeString(description);
        dest.writeString(keywords);
        //dest.writeString(message);
        dest.writeInt(count);
        dest.writeInt(rcount);
        dest.writeInt(fcount);
    }
}
