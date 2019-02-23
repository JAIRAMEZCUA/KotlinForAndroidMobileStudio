class Ejemplo {
    val dataEjemplo = 0

    fun saluda() {
        println("Hola Clase en Kotlin")
    }
}

class SaludoPersonalizado() {

    var name: String = ""

    init {
        println("El nombre es $nombre")
        name = nombre
    }

    constructor(nombre: String) : this() {
        println("El nombre es $nombre single constructor")
    }

    constructor(name: String, edad: Int) : this(name) {
        println("Hola $nombre que tienes $edad")
    }

    fun saluda() {
        println("Hola $nombre")
    }
}

open class Base(val inicial: Int) {
    open fun imprimerAlgo() {
        println("Algo $inicial")
    }
}

class General(val d: Int) : Base(d) {
    override fun imprimerAlgo() {
        super.imprimerAlgo()
        val suma = 4 + d
        println(suma)
    }
}

fun main() {
    val ejemplo: Ejemplo = Ejemplo()

    ejemplo.dataEjemplo
    ejemplo.saluda()

    val saludoSinNombre = SaludoPersonalizado()
    saludoSinNombre.saluda()
    val saludoPersonalizado = SaludoPersonalizado("Sebastian")
    saludoPersonalizado.saluda()
    val saludoConEdad = SaludoPersonalizado("Sebastian", 15)
    saludoConEdad.saluda()

    val general = General(8)
    general.imprimerAlgo()
}