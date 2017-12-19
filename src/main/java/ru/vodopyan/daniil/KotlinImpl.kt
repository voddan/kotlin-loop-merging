package ru.vodopyan.daniil

import org.openjdk.jmh.annotations.*
import java.util.*

/**
 * The Kotlin code that we are trying to optimize
 * */
@State(value = Scope.Benchmark)
@Fork(value = 1, warmups = 0)
@Measurement(iterations = 2)
@Warmup(iterations = 0)
open class KotlinImpl {
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
        val a = listStr.filter { it.startsWith('a') }
        val b = a.map { it.length }
        val c = b.sum()
        return c
    }

    @Benchmark
    fun transformArray(): Int {
        val a = arrStr.filter { it.startsWith('a') }
        val b = a.map { it.length }
        val c = b.sum()
        return c
    }

    @Benchmark
    fun transformListInt(): Int {
        val a = listInt.filter { it > 10 }
        val b = a.map { 1 - it }
        val c = b.sum()
        return c
    }

    @Benchmark
    fun transformArrayInt(): Int {
        val a = arrInt.filter { it > 10 }
        val b = a.map { 1 - it }
        val c = b.sum()
        return c
    }

    @Benchmark
    fun transformIntArray(): Int {
        val a = intArr.filter { it > 10 }
        val b = a.map { 1 - it }
        val c = b.sum()
        return c
    }

    @Benchmark
    fun baseline(): Int {
        return intArr.sum()
    }
}
