package com.search_image.domain.search

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.search_image.test.MainCoroutineRule
import com.search_image.test.runBlockingTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.Test


@ExperimentalCoroutinesApi
class PostCommentUseCaseImplTest {
    @get:Rule
    var coroutineRule = MainCoroutineRule()

    private val mockedRepository = mock<ImageDetailsRepository>()


    @Test
    fun `should call post comment from repository`() = coroutineRule.runBlockingTest {
        //GIVEN
        val useCase = PostCommentUseCaseImpl(mockedRepository)

        //WHEN
        useCase(
            postComment = PostCommentRequest(
                "imageId",
                "comment"
            )
        )

        //THEN

        verify(mockedRepository).postComments(
            PostCommentRequest(
                "imageId",
                "comment"
            )
        )
    }

}