package jar;/*
@author 黄大宁Rhinos
@date 2019/5/16 - 19:06
**/

public class Car implements Driveable {
    private String name;
    private int price;

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }


    public Car() {
    }

    @Override
    public String toString() {
        return "Car{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public void running() {
        System.out.println("car is running");
    }

//    public void init(){
//        System.out.println("car use init()");
//    }
}
