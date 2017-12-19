import ru.vodopyan.daniil.JavaImpl
import ru.vodopyan.daniil.KotlinImpl
import java.lang.management.ManagementFactory
import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * Memory allocation test.
 * Due to imprecise measurements, the tests may or may not pass*/
class MemAllocationTest {

    private val sizes = listOf(3, 10, 50, 100, 1000, 10000, 100000, 1000000)

    private val threadMXBean = (ManagementFactory.getThreadMXBean() as? com.sun.management.ThreadMXBean)
            ?: throw RuntimeException("Runtime does not support com.sun.management.ThreadMXBean")

    /**
     * May run [block] several times. Don't forget to warm-up!
     * */
    private inline fun measureAllocatedBytes(block: () -> Unit): Long {
        for(retry in 2 downTo 0) {  // 3 tries
            val threadId = Thread.currentThread().id
            val before = threadMXBean.getThreadAllocatedBytes(threadId)
            block()
            val after = threadMXBean.getThreadAllocatedBytes(threadId)

            if(Thread.currentThread().id == threadId)
                return after - before
            else
               println("A thread switch accrued. Will retry $retry more times")
        }
        throw RuntimeException("Too many thread switches. Memory allocations cannot be measured")
    }

    private inline fun stabiliseMeasureAllocatedBytes(block: () -> Unit): Long {
        val runs = List(7) { measureAllocatedBytes(block) }
        val results = runs.drop(2) // skip warm-up

        val counts = results.groupingBy { it }.eachCount()
        val (commonResult, commonCount) = counts.entries.maxBy { (result, count) -> count }!!

        if (commonCount >= results.size / 2)
            return commonResult
        else
            throw RuntimeException("Allocation measurements vary too much: $runs")
    }

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