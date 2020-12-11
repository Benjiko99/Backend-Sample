package utils

val <T : Enum<*>> Array<T>.allNames
    get() = map { it.name }
