package space.ersan.blogito.utils

fun Boolean.then(body: () -> Unit):Boolean {
  if(this){
    body()
  }
  return this
}