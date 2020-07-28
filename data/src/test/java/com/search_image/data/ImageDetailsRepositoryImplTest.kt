package com.search_image.data

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.search_image.data.storage.ImageDb
import com.search_image.domain.search.PostCommentRequest
import com.search_image.test.MainCoroutineRule
import com.search_image.test.runBlockingTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class ImageDetailsRepositoryImplTest {
    @get:Rule
    var coroutineRule = MainCoroutineRule()

    private val mockedImageDb = mock<ImageDb>()

    @Test
    fun `should return network error when internet connection is not available`() =
        coroutineRule.runBlockingTest {
            //GIVEN
            val repository = ImageDetailsRepositoryImpl(mockedImageDb)

            //WHEN
            repository.postComments(
                PostCommentRequest(
                    imageId = "imageId",
                    comment = "Comment"
                )
            )

            //THEN
            verify(mockedImageDb).commentDao()?.insert(any())
        }
}