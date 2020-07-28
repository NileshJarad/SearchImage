package com.search_image.search

import androidx.navigation.NavController
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.search_image.domain.search.SearchImageResponse
import com.search_image.domain.search.SearchImageUseCase
import com.search_image.test.MainCoroutineRule
import com.search_image.test.runBlockingTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class SearchImageViewModelTest {
    @get:Rule
    var coroutineRule = MainCoroutineRule()

    private val mockedSearchImageUseCase = mock<SearchImageUseCase>()
    private val mockedNavController = mock<NavController>()

    @Test
    fun `should call load more`() = coroutineRule.runBlockingTest {
        // GIVEN
        val viewModel = SearchImageViewModel(mockedSearchImageUseCase)
        viewModel.setTestDataForLoadMore("searchQuery", 5)

        // WHEN
        viewModel.loadMore()
        delay(400)

        //THEN
        verify(mockedSearchImageUseCase).invoke("searchQuery", 5)
    }

    @Test
    fun `should call search image api`() = coroutineRule.runBlockingTest {
        // GIVEN
        val viewModel = SearchImageViewModel(mockedSearchImageUseCase)

        // WHEN
        viewModel.searchImage("dogs", 1)
        delay(400)

        //THEN
        verify(mockedSearchImageUseCase).invoke("dogs", 1)
    }

    @Test
    fun `should call detail page`() = coroutineRule.runBlockingTest {
        // GIVEN
        val viewModel = SearchImageViewModel(mockedSearchImageUseCase)
        viewModel.setNavController(mockedNavController)
        // WHEN
        viewModel.onImageClicked(SearchImageResponse("id", "url", "title"))


        //THEN
        verify(mockedNavController).navigate(
            SearchImageFragmentDirections.actionSearchImageFragmentToImageDetailFragment(
                "url",
                "id",
                "title"
            )
        )
    }
}