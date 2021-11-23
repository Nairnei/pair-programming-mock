package dev.nairnei.amaropairprogramming

import dev.nairnei.amaropairprogramming.service.AmaroService
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ApiTest {

    @Test
    fun testApiList()
    {
        val response =  AmaroService().service.listShops()
        val auxResponse = response.execute()
        assertEquals(auxResponse.code(), 200)
    }

    @Test
    fun testApiListError()
    {
        val response =  AmaroService().service.listShopsError()
        val auxResponse = response.execute()
        assertNotEquals(auxResponse.code(), 200)
    }

}