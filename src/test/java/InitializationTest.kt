import ru.vodopyan.daniil.JavaImpl
import ru.vodopyan.daniil.KotlinImpl
import ru.vodopyan.daniil.MergedKotlinImpl
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class InitializationTest {
    private val sizes = listOf(3, 10, 50, 100, 1000, 10000, 100000, 1000000)

    @Test fun `KotlinImpl initializes deterministically`() {
        val implementations1 = sizes.map { KotlinImpl(it) }
        val implementations2 = sizes.map { KotlinImpl(it) }

        for(i in sizes.indices) {
            val impl1 = implementations1[i]
            val impl2 = implementations2[i]

            assertEquals(impl1.size, impl2.size)
            assertEquals(impl1.listInt, impl2.listInt)
            assertEquals(impl1.listStr, impl2.listStr)
            assertTrue(impl1.arrInt contentDeepEquals  impl2.arrInt)
            assertTrue(impl1.arrStr contentDeepEquals  impl2.arrStr)
            assertTrue(impl1.intArr contentEquals  impl2.intArr)
        }
    }

    @Test fun `JavaImpl initializes deterministically`() {
        val implementations1 = sizes.map { JavaImpl(it) }
        val implementations2 = sizes.map { JavaImpl(it) }

        for(i in sizes.indices) {
            val impl1 = implementations1[i]
            val impl2 = implementations2[i]

            assertEquals(impl1.size, impl2.size)
            assertEquals(impl1.listInt, impl2.listInt)
            assertEquals(impl1.listStr, impl2.listStr)
            assertTrue(impl1.arrInt contentDeepEquals  impl2.arrInt)
            assertTrue(impl1.arrStr contentDeepEquals  impl2.arrStr)
            assertTrue(impl1.intArr contentEquals  impl2.intArr)
        }
    }

    @Test fun `MergedKotlinImpl initializes deterministically`() {
        val implementations1 = sizes.map { MergedKotlinImpl(it) }
        val implementations2 = sizes.map { MergedKotlinImpl(it) }

        for(i in sizes.indices) {
            val impl1 = implementations1[i]
            val impl2 = implementations2[i]

            assertEquals(impl1.size, impl2.size)
            assertEquals(impl1.listInt, impl2.listInt)
            assertEquals(impl1.listStr, impl2.listStr)
            assertTrue(impl1.arrInt contentDeepEquals  impl2.arrInt)
            assertTrue(impl1.arrStr contentDeepEquals  impl2.arrStr)
            assertTrue(impl1.intArr contentEquals  impl2.intArr)
        }
    }

    @Test fun `JavaImpl initializes same as KotlinImpl`() {
        val implementations1 = sizes.map { JavaImpl(it) }
        val implementations2 = sizes.map { KotlinImpl(it) }

        for(i in sizes.indices) {
            val impl1 = implementations1[i]
            val impl2 = implementations2[i]

            assertEquals(impl1.size, impl2.size)
            assertEquals(impl1.listInt, impl2.listInt)
            assertEquals(impl1.listStr, impl2.listStr)
            assertTrue(impl1.arrInt contentDeepEquals  impl2.arrInt)
            assertTrue(impl1.arrStr contentDeepEquals  impl2.arrStr)
            assertTrue(impl1.intArr contentEquals  impl2.intArr)
        }
    }

    @Test fun `KotlinImpl initializes same as MergedKotlinImpl`() {
        val implementations1 = sizes.map { KotlinImpl(it) }
        val implementations2 = sizes.map { MergedKotlinImpl(it) }

        for(i in sizes.indices) {
            val impl1 = implementations1[i]
            val impl2 = implementations2[i]

            assertEquals(impl1.size, impl2.size)
            assertEquals(impl1.listInt, impl2.listInt)
            assertEquals(impl1.listStr, impl2.listStr)
            assertTrue(impl1.arrInt contentDeepEquals  impl2.arrInt)
            assertTrue(impl1.arrStr contentDeepEquals  impl2.arrStr)
            assertTrue(impl1.intArr contentEquals  impl2.intArr)
        }
    }
}