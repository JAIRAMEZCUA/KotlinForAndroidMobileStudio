infix fun Int.multiply(num: Int): Int = this * num

fun Any.print() {
    println(this)
}

fun <T> MutableList<T>.swap(index1: Int, index2: Int) {
    val tmp = this[index1]
    this[index1] = this[index2]
    this[index2] = tmp
}

fun main() {

    9.inc()
    println(5.multiply(10))
    println(5 multiply 10)

    "Hola extend".print()

    4.print()
    6F.print()
    true.print()


    val listaNombres = mutableListOf(
        "Zelt",
        "Rodrigo",
        "Amaury",
        "Cristian",
        "Angel"
    )

    listaNombres.print()
    listaNombres.swap(0, 2)
    listaNombres.print()

}
