package com.ammar.thmanyah.core.testing

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.junit.Assert

object TestExtensions {

    fun <T> Flow<T>.toTestFlow(): Flow<T> = flow {
        collect { value ->
            emit(value)
        }
    }

    fun <T> assertListEquals(expected: List<T>, actual: List<T>) {
        Assert.assertEquals("List sizes don't match", expected.size, actual.size)
        expected.forEachIndexed { index, expectedItem ->
            Assert.assertEquals("Item at index $index doesn't match", expectedItem, actual[index])
        }
    }

    fun <T> T?.assertNotNull(): T {
        Assert.assertNotNull("Value should not be null", this)
        return this!!
    }

    fun assertStringNotEmpty(value: String?) {
        Assert.assertNotNull("String should not be null", value)
        Assert.assertTrue("String should not be empty", !value.isNullOrEmpty())
    }
}