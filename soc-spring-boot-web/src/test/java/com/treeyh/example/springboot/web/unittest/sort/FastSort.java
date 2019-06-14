package com.treeyh.example.springboot.web.unittest.sort;

import com.alibaba.fastjson.JSON;

/**
 * @author TreeYH
 * @version 1.0
 * @description 快速排序
 * @create 2019-05-28 11:10
 */
public class FastSort {
    private static Integer[] lss = new Integer[]{5, 9, 4, 8,0, 6, 1, 3, 7, 2};


    public static void sort(Integer[] lss, Integer left , Integer right){

        if(left >= right){
            return;
        }

        Integer i = left, j = right;
        Integer value = lss[i];

        while (i < j){
            for(; i < j ; j--){
                if(lss[j] <= value ){

                    lss[i++] = lss[j];
                    break;
                }
            }

            for(; i< j; i++){
                if(lss[i] >= value){
                    lss[j--] = lss[i];
                    break;
                }
            }

        }

        lss[i] = value;

        sort(lss, left, i);
        sort(lss, i+1, right);
    }














    public static void sort2(Integer[] iss, Integer left, Integer right){

        if(right - left < 1){
            return;
        }

        Integer i = left;
        Integer j = right;

        Integer value = iss[i];

        while (i < j){
            for(; i < j; j--){
                if(value > iss[j]){
                    iss[i++] = iss[j];
                    break;
                }
            }

            for(; i < j; i++){
                if(value < iss[i]){
                    iss[j--] = iss[i];
                    break;
                }
            }
        }
        iss[i] = value;
        sort2(iss, left, i);
        sort2(iss, i+1, right);
    }






































    public static void main(String[] args) {

        sort2(lss, 0, lss.length - 1);

        System.out.println(JSON.toJSON(lss));
    }
}
