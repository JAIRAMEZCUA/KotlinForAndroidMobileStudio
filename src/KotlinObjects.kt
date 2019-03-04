object Validaciones {

    fun validPassword(psw: String) = psw.isNotEmpty() && psw.length > 10

    fun isNumber(data: Any) = data is Int

}

class ClaseUniversal {

    var a = 0

    companion object {
        fun reference(): ClaseUniversal = ClaseUniversal()
    }
}

fun main() {

    println("Ingresa un password")
    val passwd = readLine()

//    print(passwd?.let { Validaciones.validPassword(it) })
    passwd?.let { psw ->
        print(Validaciones.validPassword(psw))
    }

    val claseU = ClaseUniversal.reference()

    claseU.a = 3

    val obj = object {
        val a = 4
        val b = 2
        fun suma() = a + b
    }

    obj.a
    obj.b

    obj.suma()

}