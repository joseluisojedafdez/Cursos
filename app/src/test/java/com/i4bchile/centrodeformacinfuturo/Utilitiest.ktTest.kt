package com.i4bchile.centrodeformacinfuturo

import com.i4bchile.centrodeformacinfuturo.util.convertToWeeks
import org.junit.Assert
import org.junit.Test

class UtilitiesKtTest {

    @Test
    fun convertToWeeks_happyCase() {

        //Given
        val number = 10
        //When
        val result = number.convertToWeeks()

        // Then

        Assert.assertEquals("10 weeks", result)
        Assert.assertTrue(result.isNotEmpty())
        Assert.assertFalse(result.isBlank())

    }

}