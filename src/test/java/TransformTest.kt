import ru.vodopyan.daniil.JavaImpl
import ru.vodopyan.daniil.KotlinImpl
import ru.vodopyan.daniil.MergedKotlinImpl
import kotlin.test.Test
import kotlin.test.assertEquals

class TransformTest {
    private val sizes = listOf(3, 10, 50, 100, 1000, 10000, 100000, 1000000)

    @Test fun `baseline works the same in all implementations`() {
        for(size in sizes) {
            val impl1 = KotlinImpl(size)
            val impl2 = JavaImpl(size)
            val impl3 = MergedKotlinImpl(size)

            assertEquals(impl1.baseline(), impl2.baseline())
            assertEquals(impl2.baseline(), impl3.baseline())
        }
    }

    @Test fun `transformArray works the same in all implementations`() {
        for(size in sizes) {
            val impl1 = KotlinImpl(size)
            val impl2 = JavaImpl(size)
            val impl3 = MergedKotlinImpl(size)

            assertEquals(impl1.transformArray(), impl2.transformArray())
            assertEquals(impl2.transformArray(), impl3.transformArray())
        }
    }

    @Test fun `transformArrayInt works the same in all implementations`() {
        for(size in sizes) {
            val impl1 = KotlinImpl(size)
            val impl2 = JavaImpl(size)
            val impl3 = MergedKotlinImpl(size)

            assertEquals(impl1.transformArrayInt(), impl2.transformArrayInt())
            assertEquals(impl2.transformArrayInt(), impl3.transformArrayInt())
        }
    }

    @Test fun `transformIntArray works the same in all implementations`() {
        for(size in sizes) {
            val impl1 = KotlinImpl(size)
            val impl2 = JavaImpl(size)
            val impl3 = MergedKotlinImpl(size)

            assertEquals(impl1.transformIntArray(), impl2.transformIntArray())
            assertEquals(impl2.transformIntArray(), impl3.transformIntArray())
        }
    }

    @Test fun `transformList works the same in all implementations`() {
        for(size in sizes) {
            val impl1 = KotlinImpl(size)
            val impl2 = JavaImpl(size)
            val impl3 = MergedKotlinImpl(size)

            assertEquals(impl1.transformList(), impl2.transformList())
            assertEquals(impl2.transformList(), impl3.transformList())
        }
    }

    @Test fun `transformListInt works the same in all implementations`() {
        for(size in sizes) {
            val impl1 = KotlinImpl(size)
            val impl2 = JavaImpl(size)
            val impl3 = MergedKotlinImpl(size)

            assertEquals(impl1.transformListInt(), impl2.transformListInt())
            assertEquals(impl2.transformListInt(), impl3.transformListInt())
        }
    }
}