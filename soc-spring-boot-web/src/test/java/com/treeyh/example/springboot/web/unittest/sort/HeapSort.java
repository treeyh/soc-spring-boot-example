package com.treeyh.example.springboot.web.unittest.sort;

import com.treeyh.common.utils.JsonUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author TreeYH
 * @version 1.0
 * @description 堆排序
 * @create 2019-05-28 10:29
 */
public class HeapSort {

    private static Integer[] lss = new Integer[]{5, 9, 4, 8, 6, 1, 3, 7, 2};


    public static void sort(Integer[] lls, Integer left, Integer right){
        if(right - left < 1){
            return;
        }

        Integer middle = (left + right) / 2;

        sort(lls, left, middle);
        sort(lls, middle + 1, right);
        merge(lls, left, middle, right);
    }

    public static void merge(Integer[] lls, Integer left, Integer middle, Integer right){


        List<Integer> listLeft = new ArrayList<>(middle - left + 1);
        List<Integer> listRight = new ArrayList<>(right - middle);


        for(Integer i = left; i <= middle; i++){
            listLeft.add(lls[i]);
        }

        for(Integer i = middle+1; i<= right; i++){
            listRight.add(lls[i]);
        }

        for(Integer l = 0, r = 0, index = left; (l < listLeft.size() || r < listRight.size()) ;){

            if(l < listLeft.size() && (r >= listRight.size() || listLeft.get(l) <= listRight.get(r) )){
                lls[index++] = listLeft.get(l++);
            }

            if(r < listRight.size() && (l >= listLeft.size() || listLeft.get(l) > listRight.get(r))){
                lls[index++] = listRight.get(r++);
            }
        }
    }





    public static void  sort2(Integer[] iss, Integer left, Integer right){

        if(right - left < 1){
            return;
        }

        Integer middle = (left + right) / 2;

        sort2(iss, left, middle);
        sort2(iss, middle + 1, right);
        merge2(iss, left, middle, right);
    }

    public static void merge2(Integer[] iss, Integer left, Integer middle, Integer right){

        List<Integer> lefts = new ArrayList<>(middle - left + 1);
        List<Integer> rights = new ArrayList<>(right - middle);

        for(Integer i = left; i <= middle; i++){
            lefts.add(iss[i]);
        }

        for(Integer i = middle + 1; i <= right; i++){
            rights.add(iss[i]);
        }

        for(Integer a = 0, b = 0 , i = left; a < lefts.size() || b < rights.size(); ){
            if(a < lefts.size() && (b >= rights.size() || lefts.get(a) <= rights.get(b))){
                iss[i++] = lefts.get(a++);
            }
            if(b < rights.size() && (a >= lefts.size() || lefts.get(a) >= rights.get(b))){
                iss[i++] = rights.get(b++);
            }
        }



    }












































    private static Lock lock1 = new ReentrantLock();
    private static Lock lock2 = new ReentrantLock();

    public static void deathLock() {
        Thread t1 = new Thread() {
            @Override
            public void run() {
                try {
                    lock1.lock();
                    TimeUnit.SECONDS.sleep(1);
                    lock2.lock();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Thread t2 = new Thread() {
            @Override
            public void run() {
                try {
                    lock2.lock();
                    TimeUnit.SECONDS.sleep(1);
                    lock1.lock();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        t1.setName("mythread1");
        t2.setName("mythread2");
        t1.start();
        t2.start();
    }


    public static void main(String[] args) {
//        HeapSort.sort2(lss, 0, lss.length - 1);
//        System.out.println(JsonUtils.toJson(lss));

        deathLock();

    }
}
