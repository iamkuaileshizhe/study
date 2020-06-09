package cn.com.trying.stock.enums;

/**
* @Title: GoldSectionEnum
* @Description: 黄金分割点枚举类
* @author huxx
* @date 2020/6/8 下午4:20
* @update
*/
public enum GoldSectionEnum {
    GOLD191("191","0.191","0.191",""),
    GOLD382("382","0.382","0.382",""),
    GOLD5("5","0.5","0.5","0.5"),
    GOLD618("618","0.618","0.618","0.618"),
    GOLD809("809","0.809","0.809","");

    private String code;//控件的编码
    private String value;//控件的value
    private String text;//控件的显示名
    private String desc;//控件描述

    GoldSectionEnum(String code, String value, String text, String desc){
        this.code = code;
        this.value = value;
        this.text = text;
        this.desc = desc;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
