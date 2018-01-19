package setlist.shea.setlist

/**
 * Created by adamshea on 1/1/18.
 */
sealed class Result<out T: Any, out E : Exception> {

    class Some<out T : Any, out E : Exception>(val value: T) : Result<T, E>()
    class None<out T : Any, out E : Exception> : Result<T, E>()
    class Error<out T : Any, out E : Exception>(val exception: E) : Result<T, E>()
}