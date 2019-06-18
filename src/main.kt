

fun main(args : Array<String>) {
    println("Hello, world!")
    doNotLogMe()
}

fun logThisMethod() {
    println("Hello, logThisMethod!")

}

fun doNotLogMe() {
    Wrapper.doNotLogMeToo()
}