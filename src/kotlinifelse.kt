import com.sun.org.apache.xpath.internal.operations.Bool

fun main() {

    println(esMayor(-6))
    queTipoEs("Hola Kotlin")
    queTipoEs(42)
    queTipoEs(true)

}

fun esMayor(data: Int): Boolean =
    if (data > 0) {
        println("$data")
        true
    } else {
        println("No es mayor")
        false
    }

fun queTipoEs(data: Any) {
    if (data is String) {
        println(data.length)
    } else if (data is Int) {
        println("${data.times(2)}")
    } else if (data is Boolean) {
        println(data)
    }
}