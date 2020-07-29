package com.search_image.detail

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.search_image.base.BaseViewModel
import com.search_image.domain.search.CommentResponse
import com.search_image.domain.search.GetCommentUseCase
import com.search_image.domain.search.PostCommentRequest
import com.search_image.domain.search.PostCommentUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ImageDetailViewModel @Inject constructor(
    private val postCommentUseCase: PostCommentUseCase,
    private val getCommentUseCase: GetCommentUseCase
) : BaseViewModel() {

    private lateinit var imageId: String
    var imageUrl = ObservableField<String>()
    var snackbarMessage = ObservableField<String>()

    var comments = MutableLiveData<List<CommentResponse>>()

    private var isLastPage = false
    private var page = 1

    fun getCommentsFromDb() {
        if (isLastPage.not()) {
            viewModelScope.launch {
                val (commentListFromDb, lastPage) = withContext(Dispatchers.IO) {
                    getCommentUseCase(
                        imageId,
                        page
                    )
                }
                comments.value = commentListFromDb
                isLastPage = lastPage
                if (lastPage.not()) {
                    page++
                }
            }
        }
    }

    fun postComment(comment: String?) {
        if (comment.isNullOrBlank()) {
            snackbarMessage.set("Please enter comment")
            return
        }
        viewModelScope.launch() {
            val lastComment = withContext(Dispatchers.IO) {
                postCommentUseCase(
                    PostCommentRequest(
                        imageId,
                        comment
                    )
                )
            }
            val toMutableList = comments.value?.toMutableList()
            toMutableList?.add(0, lastComment)
            comments.value = toMutableList
        }
    }

    fun setImageData(imageId: String?, imageUrl: String?) {
        this.imageUrl.set(imageUrl)
        this.imageId = imageId ?: ""
        getCommentsFromDb()
    }
}