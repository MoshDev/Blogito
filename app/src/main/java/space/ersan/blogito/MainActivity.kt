package space.ersan.blogito

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import space.ersan.blogito.ui.feed.FeedFragment

class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    supportFragmentManager.beginTransaction().replace(R.id.main_content, FeedFragment()).commit()
  }
}
