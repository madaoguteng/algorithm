package com.mark.algorithm.sort;

import com.mark.algorithm.Stopwatch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

/**
 * Created by Administrator on 2018/7/26.
 */
public class SortCompare {

    public static double time(String algName, Double[] a){
        Stopwatch timer = new Stopwatch();
        if (algName.equals("Selection")){
            new SelectionSort().sort(a);
        }else if (algName.equals("Insert")){
            new InsertSort().sort(a);
        }else if (algName.equals("Shell")){
            new ShellSort().sort(a);
        }
        return timer.elapsedTime();
    }

    public static double timeRandomInput(String alg, int N, int T){
        double total = 0.0;
        Double[] a = new Double[N];
        for (int i = 0; i < T; i++) {
            for (int j = 0; j < N; j++) {
                a[j] = new Random().nextDouble();
            }
            total += time(alg, a);
        }

        return total;
    }

    public static void main(String[] args){
        String alg1 = null;
        String alg2 = null;
        String alg3 = null;
        int N = 0;
        int T = 0;
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("请输入第一个算法名称：[Selection/Insert/Shell]");
            alg1 = reader.readLine();
            System.out.println("请输入第二个算法名称：[Selection/Insert/Shell]");
            alg2 = reader.readLine();
            System.out.println("请输入第三个算法名称：[Selection/Insert/Shell]");
            alg3 = reader.readLine();
            System.out.println("请输入待排序数组元素个数：");
            N = Integer.parseInt(reader.readLine());
            System.out.println("请输入本次测试需要排序多少组：");
            T = Integer.parseInt(reader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        double timeOfAlg1 = timeRandomInput(alg1, N, T);
        double timeOfAlg2 = timeRandomInput(alg2, N, T);
        double timeOfAlg3 = timeRandomInput(alg3, N, T);
        System.out.println("排序算法"+alg1+"对"+T+"组包含"+N+"个元素的数组排序花费的平均时间为："+timeOfAlg1/T+" 毫秒。");
        System.out.println("排序算法"+alg2+"对"+T+"组包含"+N+"个元素的数组排序花费的平均时间为："+timeOfAlg2/T+" 毫秒。");
        System.out.println("排序算法"+alg3+"对"+T+"组包含"+N+"个元素的数组排序花费的平均时间为："+timeOfAlg3/T+" 毫秒。");
    }
}
