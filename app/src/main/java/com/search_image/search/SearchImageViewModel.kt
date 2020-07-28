package com.search_image.search

import androidx.annotation.VisibleForTesting
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.search_image.base.BaseViewModel
import com.search_image.domain.search.SearchImageResponse
import com.search_image.domain.search.SearchImageUseCase
import com.search_image.domain.search.common.ApiResponse
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchImageViewModel @Inject constructor(
    private val searchImageUseCase: SearchImageUseCase
) : BaseViewModel() {

    private lateinit var navController: NavController

    // Maintains page count for load more feature
    private var pageCount = 1

    // Used to decide to whether to load more or not. we stop on load more once we get empty list for request
    private var loadMore = true

    // search query
    private var searchQuery = ""

    // Used in data binding for showing no result message
    var noSearchResultError = ObservableBoolean(false)

    var snackbarMessage = ObservableField<String>()

    // Used in data binding for showing loader
    var loaderVisibility = ObservableBoolean(false)

    private lateinit var searchQueryJob: Job

    // live data that holds search image result
    var imageSearchResult = MutableLiveData<List<SearchImageResponse>>()

    /***
     * it calls search image with already incremented page count
     */
    fun loadMore() {
        searchImage(searchQuery, pageCount)
    }

    fun searchImage(searchQueryParameter: String?, pageCountParameter: Int) {
        if (loadMore || pageCountParameter == 1) {
            if (!searchQueryParameter.isNullOrBlank()) {
                loaderVisibility.set(true)
                this.searchQuery = searchQueryParameter
                this.pageCount = pageCountParameter

                // this cancels previous job
                if (this::searchQueryJob.isInitialized) {
                    searchQueryJob.cancel()
                }
                searchQueryJob = viewModelScope.launch {
                    // add 400 milis delay for search api
                    delay(250)
                    when (val searchImageData = searchImageUseCase(searchQuery, pageCount)) {
                        is ApiResponse.Success -> {
                            loaderVisibility.set(false)
                            // set search list
                            imageSearchResult.value = searchImageData.data

                            val empty = searchImageData.data.isEmpty()
                            // is page count is 1 and list return is empty then show no result found
                            noSearchResultError.set(pageCount == 1 && empty)

                            if (empty) {
                                loadMore = false
                            } else {
                                // increment counter for load more only if previous result is not empty
                                // we stop on load more once we get empty list
                                loadMore = true
                                pageCount++
                            }
                        }
                        is ApiResponse.NetworkError -> {
                            loaderVisibility.set(false)
                            snackbarMessage.set("Please check you internet")
                        }
                        else -> {
                            loaderVisibility.set(false)
                            snackbarMessage.set("Something went wrong please try again")
                        }
                    }
                }
            } else {
                // search is empty so clear all data from list
                imageSearchResult.value = emptyList()
            }
        }
    }


    fun onImageClicked(imageData: SearchImageResponse) {
        navController.navigate(
            SearchImageFragmentDirections.actionSearchImageFragmentToImageDetailFragment(
                imageData.imageUrl,
                imageData.id,
                imageData.title
            )
        )
    }


    @VisibleForTesting
    fun setTestDataForLoadMore(query: String, pageCount: Int) {
        this.searchQuery = query
        this.pageCount = pageCount
    }

    fun setNavController(navController: NavController) {
        this.navController = navController
    }
}