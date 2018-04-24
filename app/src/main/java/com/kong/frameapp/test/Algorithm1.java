package com.kong.frameapp.test;

import java.util.HashMap;
import java.util.TreeMap;


/**
 * Created by kaipai on 17/11/16.
 */
public class Algorithm1 {

    public static void main(String[] args) {

        Algorithm1 test = new Algorithm1();


//        String dd = test.reverseString("Valaienie");

        int[] array = {10, 9, 3, 6, 5, 3, 1};
//        test.bubbleSort1(array);

        int[] src = new int[]{1, 3, 5, 7, 8, 9};
        // System.out.println(binarySearch(src, 9));
        quickSort(array, 0, array.length - 1);
        print(array);
    }


    public static void insertSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int insertValue = array[i];
            int location = i - 1;

            while (location >= 0 && array[location] > insertValue) {
                array[location + 1] = array[location];//元素移动一格
                location--;
            }

            array[location + 1] = insertValue;//将需要插入的数放在要插入的位置。
        }
        print(array);
    }

    public static void quickSort(int[] array, int start, int end) {
        if (start < end) {
            int base = array[start]; // 选定的基准值（第一个数值作为基准值）
            int temp; // 记录临时中间值
            int i = start, j = end;
            do {
                //从前向后比较，找出最大值索引
                while ((array[i] < base) && (i < end))
                    i++;

                //从后向前比较，找出最小值索引
                while ((array[j] > base) && (j > start))
                    j--;
                //交换一次，
                if (i <= j) {
                    temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                    //下标移动，
                    i++;
                    j--;
                }
            } while (i <= j); //
            //递归
            if (start < j)
                quickSort(array, start, j);
            if (end > i)
                quickSort(array, i, end);
        }
    }


    /**
     * 二分查找算法
     *
     * @param array 有序数组 *
     * @param key   查找元素 *
     * @return key的数组下标，没找到返回-1
     */
    public static int binarySearch(int[] array, int key) {
        int start = 0;
        int end = array.length;
        for (int i = start; i < end; i++) {
            int middle = (start + end) / 2;
            if (key == array[middle]) {
                return middle;
            } else if (key < array[middle]) {
                end = middle - 1;
            } else {
                start = middle + 1;
            }
        }
        return -1;
    }


    public void bubbleSort1(int[] a) {
        int temp;
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length - i - 1; j++) {
                if (a[j] > a[j + 1]) {
                    temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;

                }
                print(a);
            }
        }

        System.out.print("\n");
    }

    public static void print(int[] array) {
        for (int item : array) {
            System.out.print(item + " ");
        }
        System.out.print("\n");
    }

    private void findFirstChar(String str) {
        char[] chars = str.toCharArray();

    }

    private String test(String iniString, int len) {
        char[] chars = iniString.toCharArray();

        StringBuffer buf = new StringBuffer();
        char value = " ".charAt(0);
        for (char temp : chars) {

            if (temp == value) {
                buf.append("%20");
            } else {
                buf.append(temp);
            }
        }

        return buf.toString();
    }


    //在字符串中找到第一个不重复的字符
    public static char firstNonRepeatedCharacter(String str) {
        HashMap<Character, Integer> sign = new HashMap<Character, Integer>();
        // build table [char -> count]
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (sign.containsKey(c)) {
                sign.put(c, sign.get(c) + 1);
            } else {
                sign.put(c, 1);
            }
        }
        // since HashMap doesn't maintain order, going through string again
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (sign.get(c) == 1) {
                return c;
            }
        }
        throw new RuntimeException("Undefined behaviour");
    }

    //判断两个String的交集比如 A = "Marginle"，B = Valaienie", 交集为aie，写个算法。
    public static String search(String mes1, String mes2) {
        StringBuffer array = new StringBuffer();
        for (int i = 0; i < mes1.length(); i++) {
            String v = mes1.substring(i, i + 1);
            for (int j = 0; j < mes2.length(); j++) {
                String vv = mes2.substring(j, j + 1);
                int l = array.indexOf(vv);
                if (v.equals(vv) && l < 0) {
                    array.append(vv);
                    break;
                }
            }
        }
        return array.toString();
    }

    //去掉字符
    public static void remove(String str, String s) {
        char[] a = str.toCharArray();
        char[] b = s.toCharArray();
        StringBuffer sl = new StringBuffer();
        for (char temp : a) {
            boolean logo = false;
            for (char value : b) {
                if (temp == value) {
                    logo = true;
                    break;
                }
            }
            if (logo == false)
                sl.append(temp);
        }
        System.out.println(sl.toString());
    }


    //数组中，找出出现次数最多且数值最大值
    public static void seek(int[] array) {
        TreeMap<Integer, Integer> tree = new TreeMap<Integer, Integer>();
        int maxCount = 0;
        int maxV = Integer.MIN_VALUE;
        for (int i = 0; i < array.length; i++) {
            int temp = array[i];
            Integer count = tree.get(temp);
            if (count == null)
                tree.put(temp, 1);
            else {
                int newCount = count + 1;
                tree.put(temp, newCount);
                if (maxCount < newCount) {
                    maxCount = newCount;
                    maxV = temp;
                }
            }
        }
        System.out.println("出现次数最多且最多的值：" + maxV + " count:" + maxCount);
    }


    public String reverseString(String iniString) {
        char[] chars = iniString.toCharArray();
        StringBuffer buf = new StringBuffer();
        for (int i = chars.length - 1; i >= 0; i--) {
            buf.append(chars[i]);
        }
        return buf.toString();
    }

    //Valaienie
    static String func(String s) {
        return s.length() > 0 ? func(s.substring(1)) + s.charAt(0) : "";
    }

    // 100盏灯，全部关闭，第一人全部打开（亮），第二个人隔一个按开关，第三个人隔2个按开关，以此类推，第100人路过时有几盏灯亮着？
    public static boolean[] setLamp() {
        boolean[] lamp = new boolean[100];
        //
        int index = 1;
        //按灯的人数
        for (int i = 0; i < 100; i++) {
            //
            int location = 0;
            for (int j = 0; j < lamp.length; j++) {
                if (j == location) {
                    lamp[j] = !lamp[j];
                    location += index;
                }
            }
            index++;
        }
        return lamp;
    }

    public static boolean[] lamp() {
        boolean[] d = new boolean[100];
        int index = 0;
        for (int i = 0; i < 100; i++) {
            if (i == 0) {
                for (int j = 0; j < d.length; j++)
                    d[j] = !d[j];
            } else {
                index = i + 1;
                int location = 0;
                location += index;
                for (int j = 0; j < d.length; j++) {
                    if (j == location) {
                        location += index;
                        d[j] = !d[j];
                    }
                }
            }
        }
        return d;
    }


}
