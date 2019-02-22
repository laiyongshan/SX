package suncere.shanxi.androidapp.model.entity;

/**
 * @author lys
 * @time 2018/9/28 17:20
 * @desc:
 */

public class AnalysisItemBean extends BaseBean {

    private String itemName;
    private String itemSubName;
    private int ImageId;
    private Class targetClass;

    public AnalysisItemBean(String itemName,String itemSubName,int ImageId,Class targetClass){
        this.itemName=itemName;
        this.itemSubName=itemSubName;
        this.ImageId=ImageId;
        this.targetClass=targetClass;
    }


    public void setItemSubName(String itemSubName) {
        this.itemSubName = itemSubName;
    }

    public String getItemSubName() {
        return itemSubName;
    }

    public void setTargetClass(Class targetClass) {
        this.targetClass = targetClass;
    }

    public Class getTargetClass() {
        return targetClass;
    }


    public void setImageId(int imageId) {
        ImageId = imageId;
    }

    public int getImageId() {
        return ImageId;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemName() {
        return itemName;
    }
}
