package space.ersan.blogito.domain.feed

import javax.inject.Inject

interface FeedInteractor : FeedPostsUseCase

class DefaultFeedInteractor @Inject constructor(private val feedPostsUseCase: FeedPostsUseCase) :
  FeedInteractor,
  FeedPostsUseCase by feedPostsUseCase