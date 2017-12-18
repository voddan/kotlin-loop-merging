package ru.vodopyan.daniil

import org.openjdk.jmh.annotations.Benchmark
import org.openjdk.jmh.annotations.Fork
import org.openjdk.jmh.annotations.Measurement
import org.openjdk.jmh.annotations.Warmup
import org.openjdk.jmh.runner.Runner
import org.openjdk.jmh.runner.options.OptionsBuilder



open class KotlinImpl {
    /*@Benchmark
    fun transformList(list: List<String>): Int {
        val a = list.filter { it.startsWith('a') }
        val b = a.map { it.length }
        val c = b.sum()
        return c
    }

    @Benchmark
    fun transformArray(list: Array<String>): Int {
        val a = list.filter { it.startsWith('a') }
        val b = a.map { it.length }
        val c = b.sum()
        return c
    }

    @Benchmark
    fun transformListInt(list: List<Int>): Int {
        val a = list.filter { it > 10 }
        val b = a.map { 1 - it }
        val c = b.sum()
        return c
    }

    @Benchmark
    fun transformArrayInt(list: Array<Int>): Int {
        val a = list.filter { it > 10 }
        val b = a.map { 1 - it }
        val c = b.sum()
        return c
    }

    @Benchmark
    fun transformIntArray(list: IntArray): Int {
        val a = list.filter { it > 10 }
        val b = a.map { 1 - it }
        val c = b.sum()
        return c
    }
*/
    @Benchmark
    @Fork(value = 1, warmups = 0)
    @Measurement(iterations = 5)
    @Warmup(iterations = 0)
    fun test() {

    }

}

fun main(args: Array<String>) {
//    org.openjdk.jmh.Main.main(args)

    val opt = OptionsBuilder()
            .include(KotlinImpl::class.java.simpleName)
            .forks(1)
            .build()

    Runner(opt).run()
}
