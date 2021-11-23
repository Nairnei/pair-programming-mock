package dev.nairnei.amaropairprogramming

import dev.nairnei.amaropairprogramming.repository.AmaroRepository
import dev.nairnei.amaropairprogramming.service.AmaroService
import dev.nairnei.amaropairprogramming.viewModel.HomeViewModel
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class RepositoryUnitTest {

    lateinit var repository: AmaroRepository
    @Test
    fun testRepositoryGetStores()
    {
        repository = AmaroRepository()
        val response = repository.getStore().execute()
        assertEquals(response.code() , 200)
    }

    @Test
    fun testRepositoryGetFromFakeCache()
    {
        repository = AmaroRepository()
        val response = repository.getFromCache().execute()
        assertEquals(response.code() , 205)
    }

}