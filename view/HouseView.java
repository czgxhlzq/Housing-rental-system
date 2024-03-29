package com.houserent.view;

import com.houserent.domain.House;
import com.houserent.service.HouseService;
import com.houserent.utils.Utility;

/**
 * 1.显示用户界面
 * <p>
 * 2.接收用户的输入
 * <p>
 * 3.调用其他类完成对房屋的各种操作
 */
public class HouseView {

    //控制显示菜单
    private boolean loop = true;
    private char key = ' ';
    private HouseService houseService = new HouseService(2);


    //编写listHouse() 显示房屋列表
    public void listHouse() {
        System.out.println("============房屋列表==========");
        System.out.println("编号\t\t房主\t\t电话\t\t地址\t\t月租\t\t状态(未出租/已出租)");
        //得到房屋信息
        House[] houses = houseService.list();
        for (int i = 0; i < houses.length; i++) {
            if (houses[i] != null) {
                System.out.println(houses[i]);
            }
        }
        System.out.println("============显示完成==========");
    }

    //编写add House() 接收输入，创建一个House对象，调用add方法
    public void addHouse(){
        System.out.println("===============房屋添加===============");
        System.out.print("姓名：");
        String name = Utility.readString(8);
        System.out.print("电话：");
        String phone = Utility.readString(12);
        System.out.print("地址: ");
        String address = Utility.readString(15);
        System.out.print("月租：");
        int rent = Utility.readInt();
        System.out.print("状态 (已出租/未出租)：");
        String state = Utility.readString(4);
        House house = new House(0, name, phone, address, rent, state);
        if (houseService.add(house)){
            System.out.println("==============房屋添加成功=============");
        } else {
            System.out.println("==============房屋添加失败=============");
        }
    }


    //编写delHouse() 方法 接收输入的id，调用del方法
    public void delHouse(){
        System.out.println("===============房屋删除===============");
        System.out.print("请输入你要删除的房屋编号(-1退出)：");
        int delId = Utility.readInt();
        if (delId == -1){
            System.out.println("你已放弃删除");
            return;
        }
        //方法里面有循坏判断
        char choice = Utility.readConfirmSelection();
        if(choice == 'Y'){
            if (houseService.del(delId)){
                System.out.println("===============房屋删除成功===============");
            } else {
                System.out.println("==========房屋id不存在，删除失败========");
            }
        } else {
            System.out.println("你已放弃删除");
        }
    }

    //编写exitHouse() 方法 ，退出系统
    public void exitHouse(){
        System.out.print("确认是否退出");
        char choice = Utility.readConfirmSelection();
        if (choice == 'Y'){
            System.out.println("你已退出系统");
            loop = false;
        }
    }

    //编写 find House方法 ， 根据id查找房屋信息
    public void findHouse(){
        System.out.println("============查找房屋==========");
        System.out.print("请输入你要查找的id：");
        int findId = Utility.readInt();
        House house = houseService.find(findId);
        if(house == null){
            System.out.println("你要查找的房屋id不存在");
        } else {
            System.out.println(house);
        }
    }

    //编写update House() 方法 ， 根据id查找房屋信息，并进行修改
    public void updateHouse(){
        System.out.println("============修改房屋=========");
        System.out.println("请输入代修改的房屋编号(-1退出)：");
        int updateId = Utility.readInt();
        if (updateId == -1){
            System.out.println("你已放弃修改");
            return;
        }
        House house = houseService.find(updateId);
        if (house != null){
            // 编号  房主  电话  地址  月租  状态(未出租/已出租)
            System.out.print("姓名 (" + house.getName() + ")：");
            String name = Utility.readString(8,"");
            if (!"".equals(name)){
                house.setName(name);
            }
            System.out.print("电话 (" + house.getPhone() + ")：");
            String phone = Utility.readString(12,"");
            if (!"".equals(phone)){
                house.setPhone(phone);
            }
            System.out.print("地址 (" + house.getAddress() + ")：");
            String address = Utility.readString(6,"");
            if (!"".equals(address)){
                house.setAddress(address);
            }
            System.out.print("月租 (" + house.getRent() + ")：");
            int rent = Utility.readInt(-1);
            if (rent != -1){
                house.setRent(rent);
            }
            System.out.print("状态(未出租/已出租) (" + house.getState() + ")：");
            String state = Utility.readString(6,"");
            if (!"".equals(state)){
                house.setState(state);
            }
            System.out.println("\n修改房屋信息成功");
        } else {
            System.out.println("你要查找的房屋id不存在");
        }
    }

    //主菜单
    public void mainMenu() {
        do {
            System.out.println("\n==============房屋出租系统============");
            System.out.println("\t\t\t1. 新 增 房 源 ");
            System.out.println("\t\t\t2. 查 找 房 源 ");
            System.out.println("\t\t\t3. 删 除 房 源 ");
            System.out.println("\t\t\t4. 修 改 房 屋 信 息 ");
            System.out.println("\t\t\t5. 房 屋 列 表 ");
            System.out.println("\t\t\t6. 退      出 ");
            System.out.println("请输入你的选择 1-6 ：");
            key = Utility.readChar();
            switch (key) {
                case '1':
                    addHouse();
                    break;
                case '2':
                    findHouse();
                    break;
                case '3':
                    delHouse();
                    break;
                case '4':
                    updateHouse();
                    break;
                case '5':
                    listHouse();
                    break;
                case '6':
                    exitHouse();
                    break;
            }
        } while (loop);
    }
}
