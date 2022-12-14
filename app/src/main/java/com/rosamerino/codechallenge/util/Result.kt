package com.rosamerino.codechallenge.util


data class Result<out T>(val state: State, val data: T?, val message: String?) {
    companion object {
        fun <T> success(data: T): Result<T> = Result(state = State.SUCCESS, data = data, message = null)

        fun <T> error(data: T?, message: String): Result<T> =
            Result(state = State.ERROR, data = data, message = message)

        fun <T> loading(data: T?): Result<T> = Result(state = State.LOADING, data = data, message = null)
    }
}

enum class State {
    SUCCESS,
    ERROR,
    LOADING
}
