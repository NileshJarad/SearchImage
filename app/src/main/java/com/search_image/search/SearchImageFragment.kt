package com.search_image.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.search_image.SearchFragmentBinding
import com.search_image.base.BaseFragment
import javax.inject.Inject

class SearchImageFragment : BaseFragment() {

    private lateinit var gridLayoutManager: GridLayoutManager
    private lateinit var searchedImageAdapter: SearchedImageAdapter
    private lateinit var searchViewModel: SearchImageViewModel

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return SearchFragmentBinding.inflate(inflater, container, false).apply {
            searchViewModel =
                ViewModelProvider(this@SearchImageFragment, factory).get(SearchImageViewModel::class.java)
            viewModel = searchViewModel
            with(imagesRecyclerView) {
                gridLayoutManager = GridLayoutManager(requireContext(), 3)
                layoutManager = gridLayoutManager

                searchedImageAdapter = SearchedImageAdapter(searchViewModel)
                adapter = searchedImageAdapter

                // Added scroll listener for load more feature
                addOnScrollListener(object : RecyclerView.OnScrollListener() {
                    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                        super.onScrolled(recyclerView, dx, dy)
                        if (dy > 0) { // only when scrolling up
                            val visibleThreshold = 1
                            val layoutManager = gridLayoutManager
                            val lastItem = layoutManager.findLastCompletelyVisibleItemPosition()
                            val currentTotalCount = layoutManager.itemCount
                            if (currentTotalCount <= lastItem + visibleThreshold) {
                               searchViewModel.loadMore()
                            }
                        }
                    }
                })

            }

            searchImageSearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
                androidx.appcompat.widget.SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    searchViewModel.searchImage(query,1)
                    searchImageSearchView.clearFocus() // hides softkeyboard
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    //call api when user starts typing
                    searchViewModel.searchImage(newText,1)
                    return true
                }
            })

            searchViewModel.setNavController(findNavController())
        }.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // Add observer for image search result
        searchViewModel.imageSearchResult.observe(viewLifecycleOwner, Observer {
            searchedImageAdapter.setImageData(it)
        })
    }


}