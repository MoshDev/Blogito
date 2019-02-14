package space.ersan.blogito.ui.feed

import androidx.fragment.app.Fragment
import space.ersan.blogito.R
import space.ersan.blogito.model.entity.Post
import space.ersan.blogito.ui.postviewer.PostViewerFragment

interface FeedNavigator {
  fun showPostScreen(post: Post)
}

class DefaultFeedNavigator(private val fragment: Fragment) : FeedNavigator {

  override fun showPostScreen(post: Post) {
    fragment.requireActivity().supportFragmentManager.beginTransaction()
      .replace(R.id.main_content, PostViewerFragment.newInstance(post)).addToBackStack("feed")
      .commit()
  }
}