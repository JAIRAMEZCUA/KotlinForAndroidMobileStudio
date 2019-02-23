fun main() {

    val listaNumeros = intArrayOf(1, 2, 3, 4, 5, 6, 7, 0)

    for (content in listaNumeros) {
        println(content)
//        println(listaNumeros[content])
    }

    val frutas = listOf("manzana", "piña", "uva", "platano")
    for (fruta in frutas) {
        println(fruta)
    }

    var x = 3
    while (x > 0) {
        x--
        println("X es $x")
    }

    var sum: Int = 0
    var input: String? = ""
    do {
        println("Ingresa un numero")
        input = readLine()
        sum += input!!.toInt()
    } while (input != "0")

    println("La Suma es $sum")
}