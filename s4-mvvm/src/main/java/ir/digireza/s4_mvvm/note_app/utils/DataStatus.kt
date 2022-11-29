package ir.digireza.s4_mvvm.note_app.utils

data class DataStatus<out T>(val status: Status, val data: T? = null, val isEmpty: Boolean) {

    enum class Status {
        SUCCESS
    }

    companion object {
        fun <T> success(data: T?, isEmpty: Boolean): DataStatus<T> {
            return DataStatus(Status.SUCCESS, data, isEmpty)
        }
    }
}