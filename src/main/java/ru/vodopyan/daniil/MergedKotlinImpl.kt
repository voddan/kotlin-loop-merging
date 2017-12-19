package ru.vodopyan.daniil

import org.openjdk.jmh.annotations.*

import java.util.ArrayList
import java.util.Random

/**
 * Cleaned-up Java code from [KotlinImpl]
 */
@State(Scope.Benchmark)
@Fork(value = 1, warmups = 0)
@Measurement(iterations = 2)
@Warmup(iterations = 0)
class MergedKotlinImpl() {
    private val rand = Random(0)

    //    @Param(value = ["3", "10", "50", "100", "1000", "10000", "100000", "1000000"])
    @Param(value = ["3", "10"])
    @JvmField
    var size: Int = 0

    lateinit var listInt: List<Int>
    lateinit var listStr: List<String>
    lateinit var arrInt: Array<Int>
    lateinit var arrStr: Array<String>
    lateinit var intArr: IntArray

    constructor(size: Int) : this() {
        this.size = size
        init()
    }

    @Setup
    fun init() {
        listInt = List(size) { i -> rand.nextInt(20) * (rand.nextInt(3) - 1) + i}
        listStr = List(size) { i -> ('a' + rand.nextInt(3)) + "$i"}
        arrInt = Array(size) { i -> rand.nextInt(20) * (rand.nextInt(3) - 1) + i}
        arrStr = Array(size) { i -> ('a' + rand.nextInt(3)) + "$i"}
        intArr = IntArray(size) { i -> rand.nextInt(20) * (rand.nextInt(3) - 1) + i}
    }

    @Benchmark
    fun transformList(): Int {
        val list0 = this.listStr
        val list1 = ArrayList<String>()

        val var5 = list0.iterator()
        while (var5.hasNext()) {
            val `element$iv$iv` = var5.next()

            if (`element$iv$iv`.startsWith('a', false)) {
                list1.add(`element$iv$iv`)
            }
        }

        val list2 = ArrayList<Int>(list1.size)

        val var17 = list1.iterator()
        while (var17.hasNext()) {
            val it = var17.next()
            list2.add(it.length)
        }

        return list2.sum()
    }

    @Benchmark
    fun transformArray(): Int {
        val list0 = this.arrStr
        val list1 = ArrayList<String>()

        for (var5 in list0.indices) {
            val it = list0[var5]
            if (it.startsWith('a', false)) {
                list1.add(it)
            }
        }

        val list2 = ArrayList<Int>(list1.size)
        val var18 = list1.iterator()

        while (var18.hasNext()) {
            val it = var18.next()
            list2.add(it.length)
        }

        return list2.sum()
    }

    @Benchmark
    fun transformListInt(): Int {
        val list0 = this.listInt
        val list1 = ArrayList<Int>()
        val var5 = list0.iterator()

        while (var5.hasNext()) {
            val `element$iv$iv` = var5.next()
            val it = `element$iv$iv`.toInt()
            if (it > 10) {
                list1.add(`element$iv$iv`)
            }
        }

        val list2 = ArrayList<Int>((list1 as List<*>).size)
        val var17 = list1.iterator()

        while (var17.hasNext()) {
            val `item$iv$iv` = var17.next()
            val it = `item$iv$iv`.toInt()
            val var13 = 1 - it
            list2.add(var13)
        }

        return list2.sum()
    }

    @Benchmark
    fun transformArrayInt(): Int {
        val list0 = this.arrInt

        val list1 = ArrayList<Int>()

        for (var5 in list0.indices) {
            val `element$iv$iv` = list0[var5]
            val it = `element$iv$iv`.toInt()
            if (it > 10) {
                list1.add(`element$iv$iv`)
            }
        }

        val list2 = ArrayList<Int>(list1.size)
        val var18 = list1.iterator()

        while (var18.hasNext()) {
            val `item$iv$iv` = var18.next()
            val it = `item$iv$iv`.toInt()
            val var13 = 1 - it
            list2.add(var13)
        }

        return list2.sum()
    }


    @Benchmark
    fun transformIntArray(): Int {
        val list0 = this.intArr
        val list1 = ArrayList<Int>()

        for (var5 in list0.indices) {
            val `element$iv$iv` = list0[var5]
            if (`element$iv$iv` > 10) {
                list1.add(`element$iv$iv`)
            }
        }

        val list2 = ArrayList<Int>(list1.size)
        val var18 = list1.iterator()

        while (var18.hasNext()) {
            val `item$iv$iv` = var18.next()
            val it = `item$iv$iv`.toInt()
            val var13 = 1 - it
            list2.add(var13)
        }

        return list2.sum()
    }

    @Benchmark
    fun baseline(): Int {
        return this.intArr.sum()
    }
}
