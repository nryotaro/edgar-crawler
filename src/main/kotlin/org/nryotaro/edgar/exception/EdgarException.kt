package org.nryotaro.edgar.exception



open class EdgarException: RuntimeException {

    constructor(): super()

    constructor(message: String): super(message)

    constructor(cause: Throwable): super(cause)

    constructor(message: String, cause: Throwable, enableSuppression: Boolean, writableStackTrace: Boolean)
            : super(message, cause, enableSuppression, writableStackTrace)

}

class ExceededTrafficLimitException: EdgarException()

