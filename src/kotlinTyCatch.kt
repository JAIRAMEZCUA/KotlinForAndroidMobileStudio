import java.lang.IllegalArgumentException
import java.lang.NumberFormatException

fun fail(message: String): Nothing {
    throw IllegalArgumentException(message)
}

fun main() {

    "Ingresa cualquier cosa:".print()

//    val numero = readLine()

//    try {
//        numero?.toInt()
//    } catch (e: NumberFormatException) {
//        print(e)
//    }

//    val number = try {
//        readLine()?.toInt()
//    } catch (e: IllegalArgumentException) {
//        println(e)
//        Int.MIN_VALUE
//    }
//    print(number)

    var name = ""
    val persona: Persona2? = null

    try {
        name = persona?.name ?: fail("Nombre no asignado")
    } catch (e: IllegalArgumentException) {
        println(e)
        name = "unsigned"
        print(name)
    } finally {
        print("finally")
    }

}