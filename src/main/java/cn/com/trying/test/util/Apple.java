package cn.com.trying.test.util;

public class Apple {
    String color;
    String id;
    int weight;
    Apple(){

    }
    Apple(String color,String id,int weight){
        this.color = color;
        this.id = id;
        this.weight = weight;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
