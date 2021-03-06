@file:Suppress("LoopToCallChain")

package ru.vodopyan.daniil

import org.openjdk.jmh.annotations.*

import java.util.ArrayList
import java.util.Random

/**
 * Java-like Kotlin code imitating [JavaImpl]
 */
@State(Scope.Benchmark)
class NaiveKotlinImpl() {
    private val rand = Random(0)

    @Param(value = ["3", "10", "50", "100", "1000", "10000", "100000", "1000000"])
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

        for(element in list0) {
            if (element.startsWith('a', false)) {
                list1.add(element)
            }
        }

        val list2 = ArrayList<Int>(list1.size)

        for (element in list1) {
            list2.add(element.length)
        }

        return list2.sum()
    }

    @Benchmark
    fun transformArray(): Int {
        val list0 = this.arrStr
        val list1 = ArrayList<String>()

        for (element in list0) {
            if (element.startsWith('a', false)) {
                list1.add(element)
            }
        }

        val list2 = ArrayList<Int>(list1.size)

        for (element in list1) {
            list2.add(element.length)
        }

        return list2.sum()
    }

    @Benchmark
    fun transformListInt(): Int {
        val list0 = this.listInt
        val list1 = ArrayList<Int>()

        for (element in list0) {
            if (element > 10) {
                list1.add(element)
            }
        }

        val list2 = ArrayList<Int>(list1.size)

        for (element in list1) {
            list2.add(1 - element)
        }

        return list2.sum()
    }

    @Benchmark
    fun transformArrayInt(): Int {
        val list0 = this.arrInt

        val list1: ArrayList<Int> = ArrayList<Int>()

        for(element in list0) {
            if (element > 10) {
                list1.add(element)
            }
        }

        val list2 = ArrayList<Int>(list1.size)

        for (element in list1) {
            list2.add(1 - element)
        }

        return list2.sum()
    }


    @Benchmark
    fun transformIntArray(): Int {
        val list0 = this.intArr
        val list1 = ArrayList<Int>()

        for (element in list0) {
            if (element > 10) {
                list1.add(element)
            }
        }

        val list2 = ArrayList<Int>(list1.size)

        for (element in list1) {
            list2.add(1 - element)
        }

        return list2.sum()
    }

    @Benchmark
    fun baseline(): Int {
        return this.intArr.sum()
    }
}
