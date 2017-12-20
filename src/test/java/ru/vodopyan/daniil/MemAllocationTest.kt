package ru.vodopyan.daniil

import java.lang.management.ManagementFactory
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

/**
 * Memory allocation test.
*/
open class MemAllocationTest {

    private val threadMXBean = (ManagementFactory.getThreadMXBean() as? com.sun.management.ThreadMXBean)
            ?: throw RuntimeException("Runtime does not support com.sun.management.ThreadMXBean")

    private inline fun measureAllocatedBytes(block: () -> Unit): Long {
        val threadId = Thread.currentThread().id

        val before = threadMXBean.getThreadAllocatedBytes(threadId)
        block()
        val after = threadMXBean.getThreadAllocatedBytes(threadId)

        return after - before
    }

    internal inline fun stabiliseMeasureAllocatedBytes(block: () -> Unit): Long {
        val runs = List(4) { measureAllocatedBytes(block) }
        val results = runs.drop(1) // skip warm-up

        val counts = results.groupingBy { it }.eachCount()
        val (commonResult, commonCount) = counts.entries.maxBy { (result, count) -> count }!!

        if (commonCount >= results.size / 2)
            return commonResult
        else
            throw RuntimeException("Allocation measurements vary too much: $runs")
    }
}


class `KotlinImpl and JavaImpl allocate the same` : MemAllocationTest() {
    private val sizes = listOf(3, 10, 50, 100, 1000, 10000, 100000, 1000000)

    @Test fun `baseline works the same in all implementations`() {
        for(size in sizes) {
            val impl1 = KotlinImpl(size)
            val impl2 = JavaImpl(size)

            val bytes1 = stabiliseMeasureAllocatedBytes { impl1.baseline() }
            val bytes2 = stabiliseMeasureAllocatedBytes { impl2.baseline() }

            assertEquals(bytes1, bytes2)
            println("Allocated $bytes1 bytes")
        }
    }

    @Test fun `transformArray works the same in all implementations`() {
        for(size in sizes) {
            val impl1 = KotlinImpl(size)
            val impl2 = JavaImpl(size)

            val bytes1 = stabiliseMeasureAllocatedBytes { impl1.transformArray() }
            val bytes2 = stabiliseMeasureAllocatedBytes { impl2.transformArray() }

            assertEquals(bytes1, bytes2)
            println("Allocated $bytes1 bytes")
        }
    }

    @Test fun `transformArrayInt works the same in all implementations`() {
        for(size in sizes) {
            val impl1 = KotlinImpl(size)
            val impl2 = JavaImpl(size)

            val bytes1 = stabiliseMeasureAllocatedBytes { impl1.transformArrayInt() }
            val bytes2 = stabiliseMeasureAllocatedBytes { impl2.transformArrayInt() }

            assertEquals(bytes1, bytes2)
            println("Allocated $bytes1 bytes")
        }
    }

    @Test fun `transformIntArray works the same in all implementations`() {
        for(size in sizes) {
            val impl1 = KotlinImpl(size)
            val impl2 = JavaImpl(size)

            val bytes1 = stabiliseMeasureAllocatedBytes { impl1.transformIntArray() }
            val bytes2 = stabiliseMeasureAllocatedBytes { impl2.transformIntArray() }

            assertEquals(bytes1, bytes2)
            println("Allocated $bytes1 bytes")
        }
    }

    @Test fun `transformList works the same in all implementations`() {
        for(size in sizes) {
            val impl1 = KotlinImpl(size)
            val impl2 = JavaImpl(size)

            val bytes1 = stabiliseMeasureAllocatedBytes { impl1.transformList() }
            val bytes2 = stabiliseMeasureAllocatedBytes { impl2.transformList() }

            assertEquals(bytes1, bytes2)
            println("Allocated $bytes1 bytes")
        }
    }

    @Test fun `transformListInt works the same in all implementations`() {
        for(size in sizes) {
            val impl1 = KotlinImpl(size)
            val impl2 = JavaImpl(size)

            val bytes1 = stabiliseMeasureAllocatedBytes { impl1.transformListInt() }
            val bytes2 = stabiliseMeasureAllocatedBytes { impl2.transformListInt() }

            assertEquals(bytes1, bytes2)
            println("Allocated $bytes1 bytes")
        }
    }
}


class `NaiveKotlinImpl and JavaImpl allocate the same` : MemAllocationTest() {
    private val sizes = listOf(3, 10, 50, 100, 1000, 10000, 100000, 1000000)

    @Test fun `baseline works the same in all implementations`() {
        for(size in sizes) {
            val impl1 = NaiveKotlinImpl(size)
            val impl2 = JavaImpl(size)

            val bytes1 = stabiliseMeasureAllocatedBytes { impl1.baseline() }
            val bytes2 = stabiliseMeasureAllocatedBytes { impl2.baseline() }

            assertEquals(bytes1, bytes2)
            println("Allocated $bytes1 bytes")
        }
    }

    @Test fun `transformArray works the same in all implementations`() {
        for(size in sizes) {
            val impl1 = NaiveKotlinImpl(size)
            val impl2 = JavaImpl(size)

            val bytes1 = stabiliseMeasureAllocatedBytes { impl1.transformArray() }
            val bytes2 = stabiliseMeasureAllocatedBytes { impl2.transformArray() }

            assertEquals(bytes1, bytes2)
            println("Allocated $bytes1 bytes")
        }
    }

    @Test fun `transformArrayInt works the same in all implementations`() {
        for(size in sizes) {
            val impl1 = NaiveKotlinImpl(size)
            val impl2 = JavaImpl(size)

            val bytes1 = stabiliseMeasureAllocatedBytes { impl1.transformArrayInt() }
            val bytes2 = stabiliseMeasureAllocatedBytes { impl2.transformArrayInt() }

            assertEquals(bytes1, bytes2)
            println("Allocated $bytes1 bytes")
        }
    }

    @Test fun `transformIntArray works the same in all implementations`() {
        for(size in sizes) {
            val impl1 = NaiveKotlinImpl(size)
            val impl2 = JavaImpl(size)

            val bytes1 = stabiliseMeasureAllocatedBytes { impl1.transformIntArray() }
            val bytes2 = stabiliseMeasureAllocatedBytes { impl2.transformIntArray() }

            assertEquals(bytes1, bytes2)
            println("Allocated $bytes1 bytes")
        }
    }

    @Test fun `transformList works the same in all implementations`() {
        for(size in sizes) {
            val impl1 = NaiveKotlinImpl(size)
            val impl2 = JavaImpl(size)

            val bytes1 = stabiliseMeasureAllocatedBytes { impl1.transformList() }
            val bytes2 = stabiliseMeasureAllocatedBytes { impl2.transformList() }

            assertEquals(bytes1, bytes2)
            println("Allocated $bytes1 bytes")
        }
    }

    @Test fun `transformListInt works the same in all implementations`() {
        for(size in sizes) {
            val impl1 = NaiveKotlinImpl(size)
            val impl2 = JavaImpl(size)

            val bytes1 = stabiliseMeasureAllocatedBytes { impl1.transformListInt() }
            val bytes2 = stabiliseMeasureAllocatedBytes { impl2.transformListInt() }

            assertEquals(bytes1, bytes2)
            println("Allocated $bytes1 bytes")
        }
    }
}


class `MergedKotlinImpl allocate less than NaiveKotlinImpl` : MemAllocationTest() {
    private val sizes = listOf(3, 10, 50, 100, 1000, 10000, 100000, 1000000)

    @Test fun `baseline allocations are not worse`() {
        println("Merged impl. | Naive impl. | improvement ")
        for(size in sizes) {
            val impl1 = MergedKotlinImpl(size)
            val impl2 = NaiveKotlinImpl(size)

            val bytes1 = stabiliseMeasureAllocatedBytes { impl1.baseline() }
            val bytes2 = stabiliseMeasureAllocatedBytes { impl2.baseline() }

            assertTrue(bytes1 <= bytes2, "$bytes1 <= $bytes2")
            println(" ${"%11d".format(bytes1)} | ${"%11d".format(bytes2)} | ${"%11d".format(bytes2 - bytes1)}  ${(bytes2 - bytes1) * 100 / bytes1}%")
        }
    }

    @Test fun `transformArray improves after merging loops`() {
        println("Merged impl. | Naive impl. | improvement ")
        for(size in sizes) {
            val impl1 = MergedKotlinImpl(size)
            val impl2 = NaiveKotlinImpl(size)

            val bytes1 = stabiliseMeasureAllocatedBytes { impl1.transformArray() }
            val bytes2 = stabiliseMeasureAllocatedBytes { impl2.transformArray() }

            assertTrue(bytes1 < bytes2, "$bytes1 < $bytes2")
            println(" ${"%11d".format(bytes1)} | ${"%11d".format(bytes2)} | ${"%11d".format(bytes2 - bytes1)}  ${(bytes2 - bytes1) * 100 / bytes1}%")
        }
    }

    @Test fun `transformArrayInt improves after merging loops`() {
        println("Merged impl. | Naive impl. | improvement ")
        for(size in sizes) {
            val impl1 = MergedKotlinImpl(size)
            val impl2 = NaiveKotlinImpl(size)

            val bytes1 = stabiliseMeasureAllocatedBytes { impl1.transformArrayInt() }
            val bytes2 = stabiliseMeasureAllocatedBytes { impl2.transformArrayInt() }

            assertTrue(bytes1 <= bytes2, "$bytes1 <= $bytes2")
            println(" ${"%11d".format(bytes1)} | ${"%11d".format(bytes2)} | ${"%11d".format(bytes2 - bytes1)}  ${(bytes2 - bytes1) * 100 / bytes1}%")
        }
    }

    @Test fun `transformIntArray improves after merging loops`() {
        println("Merged impl. | Naive impl. | improvement ")
        for(size in sizes) {
            val impl1 = MergedKotlinImpl(size)
            val impl2 = NaiveKotlinImpl(size)

            val bytes1 = stabiliseMeasureAllocatedBytes { impl1.transformIntArray() }
            val bytes2 = stabiliseMeasureAllocatedBytes { impl2.transformIntArray() }

            assertTrue(bytes1 <= bytes2, "$bytes1 <= $bytes2")
            println(" ${"%11d".format(bytes1)} | ${"%11d".format(bytes2)} | ${"%11d".format(bytes2 - bytes1)}  ${(bytes2 - bytes1) * 100 / bytes1}%")
        }
    }

    @Test fun `transformList improves after merging loops`() {
        println("Merged impl. | Naive impl. | improvement ")
        for(size in sizes) {
            val impl1 = MergedKotlinImpl(size)
            val impl2 = NaiveKotlinImpl(size)

            val bytes1 = stabiliseMeasureAllocatedBytes { impl1.transformList() }
            val bytes2 = stabiliseMeasureAllocatedBytes { impl2.transformList() }

            assertTrue(bytes1 <= bytes2, "$bytes1 <= $bytes2")
            println(" ${"%11d".format(bytes1)} | ${"%11d".format(bytes2)} | ${"%11d".format(bytes2 - bytes1)}  ${(bytes2 - bytes1) * 100 / bytes1}%")
        }
    }

    @Test fun `transformListInt improves after merging loops`() {
        println("Merged impl. | Naive impl. | improvement ")
        for(size in sizes) {
            val impl1 = MergedKotlinImpl(size)
            val impl2 = NaiveKotlinImpl(size)

            val bytes1 = stabiliseMeasureAllocatedBytes { impl1.transformListInt() }
            val bytes2 = stabiliseMeasureAllocatedBytes { impl2.transformListInt() }

            assertTrue(bytes1 <= bytes2, "$bytes1 <= $bytes2")
            println(" ${"%11d".format(bytes1)} | ${"%11d".format(bytes2)} | ${"%11d".format(bytes2 - bytes1)}  ${(bytes2 - bytes1) * 100 / bytes1}%")
        }
    }
}