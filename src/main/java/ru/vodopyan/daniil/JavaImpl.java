package ru.vodopyan.daniil;

import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;

import java.util.*;

import org.openjdk.jmh.annotations.*;

/**
 * Cleaned-up Java code from [KotlinImpl]
 * */
@State(Scope.Benchmark)
@Fork(value = 1, warmups = 0)
@Measurement(iterations = 2)
@Warmup(iterations = 0)
public class JavaImpl {

    private final Random rand = new Random(0L);

//    @Param({"3", "10", "50", "100", "1000", "10000", "100000", "1000000"})
    @Param({"3", "10"})
    public int size;

    @NotNull List<Integer> listInt;
    @NotNull List<String> listStr;
    @NotNull Integer[] arrInt;
    @NotNull String[] arrStr;
    @NotNull int[] intArr;

    JavaImpl(int size) {
        this.size = size;
        init();
    }

    @Setup
    public final void init() {
        int size$iv = this.size;
        ArrayList var2 = new ArrayList(size$iv);
        int i$iv = 0;
        int var4 = size$iv - 1;
        if (i$iv <= var4) {
            while(true) {
                Integer var12 = this.rand.nextInt(20) * (this.rand.nextInt(3) - 1) + i$iv;
                var2.add(var12);
                if (i$iv == var4) {
                    break;
                }

                ++i$iv;
            }
        }

        List var11 = (List)var2;
        this.listInt = var11;
        size$iv = this.size;
        var2 = new ArrayList(size$iv);
        i$iv = 0;
        var4 = size$iv - 1;
        if (i$iv <= var4) {
            while(true) {
                char var7 = (char)(97 + this.rand.nextInt(3));
                String var8 = "" + i$iv;
                String var18 = var7 + var8;
                var2.add(var18);
                if (i$iv == var4) {
                    break;
                }

                ++i$iv;
            }
        }

        var11 = (List)var2;
        this.listStr = var11;
        size$iv = this.size;
        Integer[] result$iv = new Integer[size$iv];
        i$iv = 0;

        for(var4 = result$iv.length; i$iv < var4; ++i$iv) {
            Integer var13 = this.rand.nextInt(20) * (this.rand.nextInt(3) - 1) + i$iv;
            result$iv[i$iv] = var13;
        }

        this.arrInt = result$iv;
        size$iv = this.size;
        String[] result$iv2 = new String[size$iv];
        i$iv = 0;

        for(var4 = result$iv2.length; i$iv < var4; ++i$iv) {
            char var6 = (char)(97 + this.rand.nextInt(3));
            String var17 = "" + i$iv;
            String var19 = var6 + var17;
            result$iv2[i$iv] = var19;
        }

        this.arrStr = result$iv2;
        size$iv = this.size;
        int[] result$iv3 = new int[size$iv];
        i$iv = 0;

        for(var4 = result$iv3.length; i$iv < var4; ++i$iv) {
            int var20 = this.rand.nextInt(20) * (this.rand.nextInt(3) - 1) + i$iv;
            result$iv3[i$iv] = var20;
        }

        this.intArr = result$iv3;
    }

    @Benchmark
    public final int transformList() {
        Iterable<String> list0 = this.listStr;
        ArrayList<String> list1 = new ArrayList<>();

        Iterator<String> var5 = list0.iterator();
        while(var5.hasNext()) {
            String element$iv$iv = var5.next();

            if (StringsKt.startsWith(element$iv$iv, 'a', false)) {
                list1.add(element$iv$iv);
            }
        }

        ArrayList<Integer> list2 = new ArrayList<>(list1.size());

        Iterator<String> var17 = list1.iterator();
        while(var17.hasNext()) {
            String it = var17.next();
            list2.add(it.length());
        }

        return CollectionsKt.sumOfInt(list2);
    }

    @Benchmark
    public final int transformArray() {
        String[] list0 = this.arrStr;
        ArrayList<String> list1 = new ArrayList<>();

        for(int var5 = 0; var5 < list0.length; ++var5) {
            String it = list0[var5];
            if (StringsKt.startsWith(it, 'a', false)) {
                list1.add(it);
            }
        }

        ArrayList<Integer> list2 = new ArrayList<>(list1.size());
        Iterator<String> var18 = list1.iterator();

        while(var18.hasNext()) {
            String it = var18.next();
            list2.add(it.length());
        }

        return CollectionsKt.sumOfInt(list2);
    }

    @Benchmark
    public final int transformListInt() {
        Iterable<Integer> list0 = this.listInt;
        ArrayList<Integer> list1 = new ArrayList<>();
        Iterator<Integer> var5 = list0.iterator();

        while(var5.hasNext()) {
            Integer element$iv$iv = var5.next();
            int it = element$iv$iv.intValue();
            if (it > 10) {
                list1.add(element$iv$iv);
            }
        }

        ArrayList<Integer> list2 = new ArrayList<>(((List)list1).size());
        Iterator<Integer> var17 = list1.iterator();

        while(var17.hasNext()) {
            Integer item$iv$iv = var17.next();
            int it = item$iv$iv.intValue();
            Integer var13 = 1 - it;
            list2.add(var13);
        }

        return CollectionsKt.sumOfInt(list2);
    }

    @Benchmark
    public final int transformArrayInt() {
        Integer[] list0 = this.arrInt;

        ArrayList<Integer> list1 = new ArrayList();

        for(int var5 = 0; var5 < list0.length; ++var5) {
            Integer element$iv$iv = list0[var5];
            int it = element$iv$iv.intValue();
            if (it > 10) {
                list1.add(element$iv$iv);
            }
        }

        ArrayList<Integer> list2 = new ArrayList<>(list1.size());
        Iterator<Integer> var18 = list1.iterator();

        while(var18.hasNext()) {
            Integer item$iv$iv = var18.next();
            int it = item$iv$iv.intValue();
            Integer var13 = 1 - it;
            list2.add(var13);
        }

        return CollectionsKt.sumOfInt(list2);
    }


    @Benchmark
    public final int transformIntArray() {
        int[] list0 = this.intArr;
        ArrayList<Integer> list1 = new ArrayList<>();

        for(int var5 = 0; var5 < list0.length; ++var5) {
            int element$iv$iv = list0[var5];
            if (element$iv$iv > 10) {
                list1.add(element$iv$iv);
            }
        }

        ArrayList<Integer> list2 = new ArrayList<>(list1.size());
        Iterator<Integer> var18 = list1.iterator();

        while(var18.hasNext()) {
            Integer item$iv$iv = var18.next();
            int it = item$iv$iv.intValue();
            Integer var13 = 1 - it;
            list2.add(var13);
        }

        return CollectionsKt.sumOfInt(list2);
    }

    @Benchmark
    public final int baseline() {
        return ArraysKt.sum(this.intArr);
    }
}
