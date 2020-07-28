package com.search_image.detail

import androidx.databinding.ObservableField
import androidx.lifecycle.viewModelScope
import com.search_image.base.BaseViewModel
import com.search_image.domain.search.PostCommentRequest
import com.search_image.domain.search.PostCommentUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class ImageDetailViewModel @Inject constructor(
    private val postCommentUseCase: PostCommentUseCase
) : BaseViewModel() {

    private lateinit var imageId: String
    var imageUrl = ObservableField<String>()
    var snackbarMessage = ObservableField<String>()

    fun postComment(comment: String?) {
        if (comment.isNullOrBlank()) {
            snackbarMessage.set("Please enter comment")
            return
        }
        viewModelScope.launch(Dispatchers.IO) {
            postCommentUseCase(
                PostCommentRequest(
                    imageId,
                    comment
                )
            )
        }
    }

    fun setImageData(imageId: String?, imageUrl: String?) {
        this.imageUrl.set(imageUrl)
        this.imageId = imageId ?: ""
    }
}