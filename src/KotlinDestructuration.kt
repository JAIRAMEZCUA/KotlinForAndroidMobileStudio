data class Persona2(
    val name: String = "Rodrigo",
    val age: Int = 24,
    val casa: String? = null
)

fun main() {
    val rodrigo = Persona2()
    println(rodrigo)

    val zelt = Persona2("Zelt")
    println(zelt)

    val rodrigo2 = Persona2(age = 25, casa = "zocalo")
    println(rodrigo2)

    val (nombre, _, casa) = rodrigo2
    println(nombre)
//    println(edad)
    println(casa)

    val (nam, _, _) = returnRodrigo()
    print(nam)
}

fun returnRodrigo(): Persona2 = Persona2()