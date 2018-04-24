package com.kong.frameapp.test;

import android.util.Log;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * 算法，数据结果验证
 * Algorithm and data structure
 * Created by kong on 17/5/12.
 */

public class TestAlgorithm {

    public static void main(String[] args) {


//        int d = 5;
//        int a = 1;
//        int b = 8;
//        int c = 4;
//
//        int ddd = d != 4 ? (d > 4 ? 9 : 1) : 8;
//
//
//        String s1 = "abc";
//        String s2 = s1;
//        String s5 = "abc";
//        String s3 = new String("abc");
//        String s4 = new String("abc");
//        System.out.println(" == comparison:" + (s1 == s5));
//        System.out.println(" == comparison:" + (s1 == s2));
//        System.out.println("Using equals method:" + s1.equals(s2));
//        System.out.println(" == comparison:" + s3 == s4);
//        System.out.println("Using equals method : " + s3.equals(s4));

        TestAlgorithm testUtil = new TestAlgorithm();


//        int[] data = {14, 13, 11, 15, 12, 13};
//        //int[] data = {14, 15, 17, 18, 19};
//        testUtil.bucketSort(data,20);

        testUtil.testToSortedList();
    }





    List testList;

    public void testToSortedList() {


        testList = new ArrayList<Integer>();
        testList.add(11);
        testList.add(1);
        testList.add(31);
        testList.add(2);
        testList.add(11);
        testList.add(12);
        testList.add(14);
        testList.add(9);


        Observable.create(new ObservableOnSubscribe<String>() {

            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                e.onNext("hello");
            }

        }).subscribe(new Consumer<String>() {

            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        });

        Observable.concat(Observable.just(Observable.just("1"), Observable.just("2")))
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(@NonNull String s) throws Exception {
                        //Log.e(TAG, "accept: "+s);
                    }
                });


        Observable.concat(Observable.just("3", "4"), Observable.just("5", "6"))
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(@NonNull String s) throws Exception {
                        //Log.e(TAG, "accept: "+s);
                    }
                });

//        Observable.fromIterable(testList)
//                .toSortedList((Comparator<? super List>) (integer1, integer2) ->   integer1 //>0 升序 ，<0 降序
//         ).subscribe((Consumer<? super List<List>>) new Consumer<List<Integer>>() {
//            @Override
//            public void accept(List<Integer> integers) throws Exception {
//
//
//                for (Integer str : integers) {
//                    Log.e("aaa", "accept: " + str);
//
//                }
//
//            }
//        });


        Observable.just(6, 2, 3, 4, 5)
                .toSortedList((Comparator<? super Integer>) (integer1, integer2) -> integer1 - integer2 //>0 升序 ，<0 降序
                ).subscribe(new Consumer<List<Integer>>() {
            @Override
            public void accept(List<Integer> integers) throws Exception {


                for (Integer str : integers) {
                    Log.e("aaa", "accept: " + str);

                }

            }
        });


        Observable.fromIterable(setBody())
                .toSortedList(new Comparator<Body>() {
                    public int compare(Body o1, Body o2) {
                        return o1.getId() - o2.getId();
                    }
                })
                .subscribe(new Consumer<List<Body>>() {
                    @Override
                    public void accept(List<Body> integers) throws Exception {

                        for (Body str : integers) {
                            Log.e("aaa", "accept: " + str);

                        }

                    }
                });


    }

    public class TestComparator implements Comparator<Body> {
        public int compare(Body o1, Body o2) {
            return o1.getId() - o2.getId();
        }
    }


    private List<Body> setBody() {

        List<Body> bodyList = new ArrayList<>();
        Body body1 = new Body();
        body1.setId(3);
        body1.setName("d");
        body1.setAge("d");

        Body body2 = new Body();
        body2.setId(6);
        body2.setName("a");
        body2.setAge("a");


        Body body3 = new Body();
        body3.setId(13);
        body3.setName("aad");
        body3.setAge("aad");

        Body body4 = new Body();
        body4.setId(11);
        body4.setName("fffff");
        body4.setAge("ffffff");

        bodyList.add(body1);
        bodyList.add(body2);
        bodyList.add(body3);
        bodyList.add(body4);

        return bodyList;
    }


    private void findFirstChar(String str) {

        char[] chars = str.toCharArray();

        for (int i = 0; i < chars.length; i++) {

        }
    }
//    char findFirstCh(String str){
//
//
////        map<char,int> res;
////        for(unsigned int i = 0; i < str.size(); ++i){
////            map<char,int>::iterator itor = res.find(str[i]);
////            if(itor != res.end()){//res中存在和当前字符相同的字符
////                res[str[i]] = 0;//将对应的value置为0
////            }
////            else{//res中不存在和当前字符一样的字符
////                res[str[i]] = 1;//将对应的value置为1
////            }
////        }//循环执行完毕后，second为1表示对应key在字符串中只出现一次，second为0的key表示在字符串中出现了多次
////
////
////        for(unsigned int i = 0; i < str.size(); ++i){
////            if(res[str[i]] == 1)
////                return str;
////        }
////
//        return "";
//    }


    class Body {

        int id;
        String name;
        String age;

        String tiem;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public String getTiem() {
            return tiem;
        }

        public void setTiem(String tiem) {
            this.tiem = tiem;
        }
    }


    //经常碰到这样一类排序问题：把新的数据插入到已经排好的数据列中。
    public void insertSort(int[] a) {
        int length = a.length;//数组长度，将这个提取出来是为了提高速度。
        int insertNum;//要插入的数
        for (int i = 1; i < length; i++) {//插入的次数
            insertNum = a[i];//要插入的数
            int j = i - 1;//已经排序好的序列元素个数
            while (j >= 0 && a[j] > insertNum) {//从后到前循环，将大于Num的数向后移动一格
                a[j + 1] = a[j];//元素移动一格
                j--;
            }
            a[j + 1] = insertNum;//将需要插入的数放在要插入的位置。
        }
    }

    //对于直接插入排序问题，数据量巨大时。
    public void sheelSort(int[] a) {
        int len = a.length;
        while (len != 0) {
            len = len / 2;
            for (int x = 0; x < len; x++) {//分的组数
                for (int i = x + len; i < a.length; i += len) {//组中元素，从第二个数开始
                    int j = i - len;//j为有序序列最后一位的位数
                    int temp = a[i];//要插入的元素
                    for (; j >= 0 && temp < a[j]; j -= len) {//从后往前遍历。
                        a[j + len] = a[j];//向后移动d位
                    }
                    a[j + len] = temp;
                }
            }
        }
    }

    public int[] bubbleSort(int[] data) {
        for (int i = 0; i < data.length - 1; i++) {
            boolean flag = true;//标识是否发生了交换,没有交互跳出循环，减少循环次数
            for (int j = 0; j < data.length - 1 - i; j++) {
                if (data[j] > data[j + 1]) {
                    swap(data, j, j + 1);
                    flag = false;
                }
            }
            if (flag)
                break;
        }
        return data;
    }

    private void swap(int[] data, int i, int j) {
        data[i] = data[i] ^ data[j];
        data[j] = data[i] ^ data[j];
        data[i] = data[i] ^ data[j];
    }


    /// <summary>
    /// 桶排序
    /// 1),已知其区间,例如[1..10],学生的分数[0...100]等
    /// 2),如果有重复的数字,则需要 List<int>数组,这里举的例子没有重复的数字
    /// </summary>
    /// <param name="unsorted">待排数组</param>
    /// <param name="maxNumber">待排数组中的最大数,如果可以提供的话</param>
    /// <returns></returns>
//    static int[] bucket_sort(int[] unsorted) {
//        int maxNumber = 99;
//        int[] sorted = new int[maxNumber + 1];
//        for (int i = 0; i < unsorted.length; i++) {
//            sorted[unsorted[i]] = unsorted[i];
//        }
//        return sorted;
//    }

    public static int[] bucketSort(int[] nums, int maxNum) {
        int[] sorted = new int[maxNum + 1];

        for (int i = 0; i < nums.length; i++) {
            sorted[nums[i]] = nums[i];//把数据放到对应索引的位置
        }
        return sorted;
    }

}
