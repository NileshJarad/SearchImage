package com.search_image.domain.search

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.search_image.domain.search.common.ApiResponse
import com.search_image.test.MainCoroutineRule
import com.search_image.test.runBlockingTest
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsInstanceOf
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class SearchImageUseCaseImplTest {

    @get:Rule
    var coroutineRule = MainCoroutineRule()

    private val mockedRepository = mock<SearchImageRepository>()

    @Test
    fun `should invoke get search result method from repository`() = coroutineRule.runBlockingTest {
        //GIVEN
        val userCase = SearchImageUseCaseImpl(mockedRepository)

        //WHEN
        userCase.invoke("shape", 1)

        //THEN
        verify(mockedRepository).searchImage("shape", 1)
    }

    @Test
    fun `should return data from repository`() = coroutineRule.runBlockingTest {
        //GIVEN
        val userCase = SearchImageUseCaseImpl(mockedRepository)
        whenever(mockedRepository.searchImage(any(), any())).thenReturn(
            ApiResponse.Success(
                listOf(
                    SearchImageResponse(
                        id = "2134",
                        imageUrl = "https://www.google.com",
                        title = "Title of image"
                    )
                )
            )
        )

        //WHEN
        val data = userCase.invoke("shape", 1)

        //THEN
        assertThat(data, IsInstanceOf.instanceOf(ApiResponse.Success::class.java))
        if(data is ApiResponse.Success){
            assertEquals(data.data[0].id, "2134")
            assertEquals(data.data[0].imageUrl, "https://www.google.com")
            assertEquals(data.data[0].title, "Title of image")
        }

    }
}