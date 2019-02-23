data class Persona(
    var nombre: String,
    var edad: Int,
    var domicilio: String
)

data class Auto(var marca: String, private var puertas: Int) {
    var numPuertas = puertas
        get() {
            return if (field > 0) field else 1
        }
        set(value) {
            field = value + 3
        }
}

fun main() {
    val israel = Persona("Israel", 33, "CDMX")
    println(israel)
//    israel.nombre
//    israel.edad
//    israel.domicilio
////    israel.nombre = ""
//    israel.edad = 34
//
//    println(israel.toString())
    val raul = israel.copy(nombre = "Raul")
    println(raul)

    println("----------")

    val javier = raul
    javier.nombre = "Javier"

    println(israel)
    println(raul)
    println(javier)

    println("----------")

    val auto1 = Auto("Ford", 0)
    val auto2 = Auto("Nissan", 4)

    auto1.numPuertas = 3

    println(auto1.numPuertas)
    println(auto2.numPuertas)

}