package com.search_image.data

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.search_image.domain.search.common.ApiResponse
import com.search_image.domain.search.common.ApiStatusCodes
import com.search_image.test.MainCoroutineRule
import com.search_image.test.runBlockingTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.hamcrest.MatcherAssert
import org.hamcrest.core.IsInstanceOf
import org.junit.Rule
import org.junit.Test
import retrofit2.Response

@ExperimentalCoroutinesApi
class SearchImageRepositoryImplTest {
    @get:Rule
    var coroutineRule = MainCoroutineRule()

    private val mockedSearchImageService = mock<SearchImageService>()
    private val mockedInternetConnectivity = mock<InternetConnectivity>()

    @Test
    fun `should return network error when internet connection is not available`() =
        coroutineRule.runBlockingTest {
            //GIVEN
            val repository =
                SearchImageRepositoryImpl(mockedSearchImageService, mockedInternetConnectivity)
            whenever(mockedInternetConnectivity.isOnline()).thenReturn(false)

            //WHEN

            val data = repository.searchImage("cash", 2)

            //THEN
            MatcherAssert.assertThat(
                data,
                IsInstanceOf.instanceOf(ApiResponse.NetworkError::class.java)
            )
        }

    @Test
    fun `should return success when internet connection is available`() =
        coroutineRule.runBlockingTest {
            //GIVEN
            val repository =
                SearchImageRepositoryImpl(mockedSearchImageService, mockedInternetConnectivity)
            whenever(mockedInternetConnectivity.isOnline()).thenReturn(true)
            whenever(
                mockedSearchImageService.searchImage(
                    any(),
                    any(),
                    any(),
                    any()
                )
            ).thenReturn(
                Response.success(
                    GetSearchImageResponse(
                        data = emptyList(),
                        status = ApiStatusCodes.STATUS_200,
                        success = true
                    )
                )
            )

            //WHEN
            val data = repository.searchImage("cash", 2)

            //THEN
            MatcherAssert.assertThat(data, IsInstanceOf.instanceOf(ApiResponse.Success::class.java))
        }
}