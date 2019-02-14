package space.ersan.blogito.utils

import androidx.lifecycle.LiveData

sealed class NetworkStatus(val isError: Boolean = false) {
  object Loading : NetworkStatus()
  object Loaded : NetworkStatus()
  data class Error(val exception: Exception? = null, val retry: (() -> Unit)? = null) :
    NetworkStatus(isError = true)
}

class LiveNetworkStatus : LiveData<NetworkStatus>() {

  init {
    value = NetworkStatus.Loaded
  }

  override fun postValue(value: NetworkStatus?) {
    if (this.value != null && value != null) {
      if (this.value!!::class == value::class) {
        return
      }
    }
    super.postValue(value)
  }

  fun postLoading() {
    postValue(NetworkStatus.Loading)
  }

  fun postLoaded() {
    postValue(NetworkStatus.Loaded)
  }

  fun postError(error: Exception? = null, retry: (() -> Unit)? = null) {
    postValue(NetworkStatus.Error(error, retry))
  }
}