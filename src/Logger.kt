


class Logger {
    companion object {
        const val ENABLED_DEBUG = true

        fun printStackTrace() {
            val stackTrace = Thread.currentThread().stackTrace
            stackTrace.forEachIndexed { index, stackTraceElement ->
                println("[$index]: $stackTraceElement")
            }
        }

        fun logMethodByName(callerName: String = "") {
            val DEFAULT_COUNT = 2
            val stackTrace = Thread.currentThread().stackTrace
            val count =
                if (callerName.isEmpty()) DEFAULT_COUNT
                else {
                    val index = stackTrace.indexOfFirst { it.toString() == callerName }
                    if (index == -1) DEFAULT_COUNT
                    else index
                }

            val traceElement = stackTrace[count]
            val fileName = traceElement.fileName
            val lineNumber = traceElement.lineNumber
            val className = traceElement.className
            val methodName = traceElement.methodName
            val msg = "$fileName($lineNumber) - $methodName"

            println(msg)
        }

        fun logMethod(offset: Int = 0) {
            val count = getStackCount() - offset + 1
//            println("logMethod: count=$count")
//            val count = 2 + offset


            val stackTraceStack = Thread.currentThread().stackTrace
            val stackTrace = stackTraceStack[count]
            val fileName = stackTrace.fileName
            val lineNumber = stackTrace.lineNumber
            val className = stackTrace.className
            val methodName = stackTrace.methodName
            val msg = "$fileName($lineNumber) - $methodName"

            println(msg)
        }

        fun getStackCount(): Int = Thread.currentThread().stackTrace.size
        fun getCallerName(): String {
            val stackTrace = Thread.currentThread().stackTrace
            val traceElement = stackTrace[2]
            val fileName = traceElement.fileName
            val lineNumber = traceElement.lineNumber
            val className = traceElement.className
            val methodName = traceElement.methodName
            val meta = "$className.$methodName($fileName:$lineNumber)"

            println("getCurrentTraceMeta: $meta")
            return meta
        }
    }
}

class Wrapper {
    companion object {

        fun doNotLogMeToo() {
            logMe()
        }

        fun wrapperName(name: String) {
            Logger.logMethodByName(name)
        }

        fun wrapperCount(count: Int) {
            Logger.logMethod(count)
        }

        fun logMe() {
            Logger.logMethodByName(Logger.getCallerName())
            Logger.logMethod(Logger.getStackCount())

            wrapperCount(Logger.getStackCount())
            wrapperName(Logger.getCallerName())

            println("--- done ---")
        }
    }
}