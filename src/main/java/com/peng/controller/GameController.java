package com.peng.controller;

import com.peng.model.GameModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/*
 2048小游戏
 */
public class GameController {

    public static GameModel init(){
        GameModel gameModel = new GameModel();
        List<Integer> longList = Arrays.asList(0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0);
        int breakFlag = 0;
        while(true){
            int key = (int) (Math.random()*16);
            int value = (int) (Math.random()*2+1)*2;
            if(longList.get(key)!=0) continue;
            else {
                gameModel.setScore(gameModel.getScore()+value);
                longList.set(key,value);
                breakFlag++;
            }
            if(breakFlag==3) break;
        }
        gameModel.setRow1(longList.subList(0,4));
        gameModel.setRow2(longList.subList(4,8));
        gameModel.setRow3(longList.subList(8,12));
        gameModel.setRow4(longList.subList(12,16));
        return gameModel;
    }

    public static GameModel move(List<Integer> longList, int dir){
        GameModel gameModel = getGameModel(longList,dir);
        addValue(gameModel.getRow1());
        addValue(gameModel.getRow2());
        addValue(gameModel.getRow3());
        addValue(gameModel.getRow4());
        randomPutInModel(gameModel);
        GameModel newGameModel = recoverModel(gameModel,dir);
        return newGameModel;
    }

    //将传入的值得分布按照方向构建model
    private static GameModel getGameModel(List<Integer> longList, int dir) {
        GameModel gameModel = new GameModel();
        //←
        switch (dir){
            case 0: //←
                gameModel.setRow1(new ArrayList<>(longList.subList(0,4)));
                gameModel.setRow2(new ArrayList<>(longList.subList(4,8)));
                gameModel.setRow3(new ArrayList<>(longList.subList(8,12)));
                gameModel.setRow4(new ArrayList<>(longList.subList(12,16)));
                break;
            case 1://→
                gameModel.setRow1(Arrays.asList(longList.get(3),longList.get(2),longList.get(1),longList.get(0)));
                gameModel.setRow2(Arrays.asList(longList.get(7),longList.get(6),longList.get(5),longList.get(4)));
                gameModel.setRow3(Arrays.asList(longList.get(11),longList.get(10),longList.get(9),longList.get(8)));
                gameModel.setRow4(Arrays.asList(longList.get(15),longList.get(14),longList.get(13),longList.get(12)));
                break;
            case 2://↑
                gameModel.setRow1(Arrays.asList(longList.get(0),longList.get(4),longList.get(8),longList.get(12)));
                gameModel.setRow2(Arrays.asList(longList.get(1),longList.get(5),longList.get(9),longList.get(13)));
                gameModel.setRow3(Arrays.asList(longList.get(2),longList.get(6),longList.get(10),longList.get(14)));
                gameModel.setRow4(Arrays.asList(longList.get(3),longList.get(7),longList.get(11),longList.get(15)));
                break;
            case 3://↓
                gameModel.setRow1(Arrays.asList(longList.get(12),longList.get(8),longList.get(4),longList.get(0)));
                gameModel.setRow2(Arrays.asList(longList.get(13),longList.get(9),longList.get(5),longList.get(1)));
                gameModel.setRow3(Arrays.asList(longList.get(14),longList.get(10),longList.get(6),longList.get(2)));
                gameModel.setRow4(Arrays.asList(longList.get(15),longList.get(11),longList.get(7),longList.get(3)));
                break;
        }
        return gameModel;
    }

    //将单列的值相等的相加，移动不为零的值到集合前面
    private static void addValue(List<Integer> row){
        int cFlag = -1;
        for(int i=0;i<3;i++){
            for(int y = i+1;y<4;y++){
                if(i==cFlag) continue;
                if(row.get(y)!=0){
                    if(row.get(i)==0) {
                        row.set(i,row.get(y));
                        row.set(y,0);
                    }else {
                        cFlag = i;
                    }
                    if(row.get(i)==row.get(y)){
                        row.set(i,2*row.get(y));
                        row.set(y,0);
                    }
                }
            }
        }
    }

    //获取空闲的坐标
    private static List<Integer> getFreePlace(GameModel gameModel){
        List<Integer> freePlaceList = new ArrayList<>();
        for(int i=0;i<4;i++){
            if(gameModel.getRow1().get(i)==0) freePlaceList.add(i);
            if(gameModel.getRow2().get(i)==0) freePlaceList.add(i+4);
            if(gameModel.getRow3().get(i)==0) freePlaceList.add(i+8);
            if(gameModel.getRow4().get(i)==0) freePlaceList.add(i+12);
        }
        return freePlaceList;
    }


    //为集合中为空闲的地方随机的产生2或4
    private static void randomPutInModel(GameModel gameModel){
        for(int i=0;i<2;i++){
            int value = (int) (Math.random()*2+1)*2;
            List<Integer> freePlaceList = getFreePlace(gameModel);
            if(freePlaceList.size()>0){
                int key = (int) (Math.random()*freePlaceList.size());
                if(freePlaceList.get(key)>12) gameModel.getRow4().set(freePlaceList.get(key)-12,value);
                else if(freePlaceList.get(key)>8) gameModel.getRow3().set(freePlaceList.get(key)-8,value);
                else if(freePlaceList.get(key)>4) gameModel.getRow2().set(freePlaceList.get(key)-4,value);
                else if(freePlaceList.get(key)>0) gameModel.getRow1().set(freePlaceList.get(key),value);
            }
        }
    }

    private static GameModel recoverModel(GameModel gameModel, int dir){
        GameModel newGameModel = new GameModel();
        switch (dir){
            case 0:
                newGameModel = gameModel;
                break;
            case 1:
                newGameModel.setRow1(Arrays.asList(gameModel.getRow1().get(3),gameModel.getRow1().get(2),gameModel.getRow1().get(1),gameModel.getRow1().get(0)));
                newGameModel.setRow2(Arrays.asList(gameModel.getRow2().get(3),gameModel.getRow2().get(2),gameModel.getRow2().get(1),gameModel.getRow2().get(0)));
                newGameModel.setRow3(Arrays.asList(gameModel.getRow3().get(3),gameModel.getRow3().get(2),gameModel.getRow3().get(1),gameModel.getRow3().get(0)));
                newGameModel.setRow4(Arrays.asList(gameModel.getRow4().get(3),gameModel.getRow4().get(2),gameModel.getRow4().get(1),gameModel.getRow4().get(0)));
                break;
            case 2:
                newGameModel.setRow1(Arrays.asList(gameModel.getRow1().get(0),gameModel.getRow2().get(0),gameModel.getRow3().get(0),gameModel.getRow4().get(0)));
                newGameModel.setRow2(Arrays.asList(gameModel.getRow1().get(1),gameModel.getRow2().get(1),gameModel.getRow3().get(1),gameModel.getRow4().get(1)));
                newGameModel.setRow3(Arrays.asList(gameModel.getRow1().get(2),gameModel.getRow2().get(2),gameModel.getRow3().get(2),gameModel.getRow4().get(2)));
                newGameModel.setRow4(Arrays.asList(gameModel.getRow1().get(3),gameModel.getRow2().get(3),gameModel.getRow3().get(3),gameModel.getRow4().get(3)));
                break;
            case 3:
                newGameModel.setRow1(Arrays.asList(gameModel.getRow1().get(3),gameModel.getRow2().get(3),gameModel.getRow3().get(3),gameModel.getRow4().get(3)));
                newGameModel.setRow2(Arrays.asList(gameModel.getRow1().get(2),gameModel.getRow2().get(2),gameModel.getRow3().get(2),gameModel.getRow4().get(2)));
                newGameModel.setRow3(Arrays.asList(gameModel.getRow1().get(1),gameModel.getRow2().get(1),gameModel.getRow3().get(1),gameModel.getRow4().get(1)));
                newGameModel.setRow4(Arrays.asList(gameModel.getRow1().get(0),gameModel.getRow2().get(0),gameModel.getRow3().get(0),gameModel.getRow4().get(0)));
                break;
        }
        int score=0;
        for(int i=0;i<3;i++){
            score+=(newGameModel.getRow1().get(i)+newGameModel.getRow2().get(i)+newGameModel.getRow3().get(i)+newGameModel.getRow4().get(i));
        }
        newGameModel.setScore(score);
        return newGameModel;
    }


    public static void main(String[] args) {
        GameModel newGameModel = init();
        boolean flag = true;
        while (flag){
            System.out.println(Arrays.toString(newGameModel.getRow1().toArray()));
            System.out.println(Arrays.toString(newGameModel.getRow2().toArray()));
            System.out.println(Arrays.toString(newGameModel.getRow3().toArray()));
            System.out.println(Arrays.toString(newGameModel.getRow4().toArray()));
            List<Integer> longList = new ArrayList<>();
            longList.addAll(newGameModel.getRow1());
            longList.addAll(newGameModel.getRow2());
            longList.addAll(newGameModel.getRow3());
            longList.addAll(newGameModel.getRow4());
            System.out.println("请选择方向：");
            System.out.println("      <0>：←");
            System.out.println("      <1>：→");
            System.out.println("      <2>：↑");
            System.out.println("      <3>：↓");
            System.out.println("      <4>：out");
            int read = 0;

            Scanner s = new Scanner(System.in);
            String lin = s.nextLine();
            read = Integer.valueOf(lin);
            if(read >= 4) flag = false;
            else newGameModel = move(longList,read);
        }
        System.out.println("您已退出！");

    }

}
