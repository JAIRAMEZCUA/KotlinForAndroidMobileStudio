fun main() {

    val str = "Una cadena en kotlin simple"

    val nombre = "Mabel"
    val edad = 25

    val frase = "$nombre tiene $edad años"
    println(frase)

    val cumpleMabel = "Hoy $nombre cumple ${edad.inc()}"
    println(cumpleMabel)

    val text = """
        >kjasvkjsdfvkzxv
        |asnsvda
        |sdfasdfasdfasf
        |sadfasdf
        |sd
        |asdf
    """.trimMargin(">")
    println(text)

}