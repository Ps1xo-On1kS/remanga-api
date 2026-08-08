package org.on1ks.remanga.api

open class GeneratorException(message: String, cause: Throwable? = null) : RuntimeException(message, cause)
class NetworkException(message: String, cause: Throwable? = null) : GeneratorException(message, cause)
class ExtractionException(message: String) : GeneratorException(message)
class OutputException(message: String, cause: Throwable? = null) : GeneratorException(message, cause)
class GenerationCancelledException : GeneratorException("Генерация отменена")
