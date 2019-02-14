package space.ersan.blogito.ui.feed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import space.ersan.blogito.common.NativeView
import space.ersan.blogito.di.Blogito
import javax.inject.Inject

class FeedFragment : Fragment() {

  @Inject
  lateinit var viewModel: FeedViewModel

  @Inject
  lateinit var nativeView: NativeView

  @Inject
  lateinit var view: FeedView

  @Inject
  lateinit var navigator: FeedNavigator

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    Blogito.injector.inject(this)
  }

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? = nativeView.getView()

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)

    viewModel.getNetworkStatus().observe({ lifecycle }, { view.setNetworkStatus(it) })
    viewModel.getPostsList().observe({ lifecycle }, { view.setPosts(it) })
    view.observeItemsClick().observe({lifecycle},{
      viewModel.onPostClicked(navigator, it)
    })
  }
}
