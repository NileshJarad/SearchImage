package com.search_image.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.search_image.ImageItemBinding
import com.search_image.R
import com.search_image.domain.search.SearchImageResponse

class SearchedImageAdapter(
    private val vm: SearchImageViewModel
) : RecyclerView.Adapter<SearchedImageAdapter.ImageViewHolder>() {

    private var searchImages = mutableListOf<SearchImageResponse>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = ImageViewHolder(parent)

    override fun getItemCount() = searchImages.size

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(vm, searchImages[position])
    }

    fun setImageData(it: List<SearchImageResponse>) {
        searchImages.clear()
        searchImages.addAll(it)
        notifyDataSetChanged()
    }


    class ImageViewHolder(private val viewDataBinding: ImageItemBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
        companion object {
            operator fun invoke(parent: ViewGroup) = ImageViewHolder(
                ImageItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }

        fun bind(
            vm: SearchImageViewModel,
            searchImageResponse: SearchImageResponse
        ) {
            viewDataBinding.viewModel = vm
            viewDataBinding.imageData = searchImageResponse
            viewDataBinding.executePendingBindings()
        }
    }
}