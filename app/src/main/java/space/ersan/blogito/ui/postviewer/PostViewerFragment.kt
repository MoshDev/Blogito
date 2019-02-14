package space.ersan.blogito.ui.postviewer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import space.ersan.blogito.common.NativeView
import space.ersan.blogito.di.Blogito
import space.ersan.blogito.model.entity.Post
import javax.inject.Inject

class PostViewerFragment : Fragment() {

  companion object {
    fun newInstance(post: Post) = PostViewerFragment().apply {
      arguments = Bundle().apply {
        putParcelable("post", post)
      }
    }
  }

  @Inject
  lateinit var viewModel: PostViewerViewModel

  @Inject
  lateinit var nativeView: NativeView

  @Inject
  lateinit var view: PostViewerView

  @Inject
  lateinit var navigator: PostViewerNavigator

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? = nativeView.getView()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    Blogito.injector.inject(this)
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)

    val post: Post = arguments?.getParcelable("post")!!

    viewModel.setPost(post)
    viewModel.getPost().observe({ lifecycle }, { view.setPost(it) })
    viewModel.getComments().observe({ lifecycle }, { view.setComments(it) })
    viewModel.getNetworkStatus().observe({ lifecycle }, { view.setNetworkStatus(it) })
  }
}
