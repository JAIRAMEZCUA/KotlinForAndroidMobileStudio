fun main() {
    println("Hola Kotlin 4 all")

    suma(4, 8)

    println(max(6, 10))

    println(multiplicationToDouble(4, 4))
    funWithFun { a, b ->
        println("adsfasf ${a + b} e It es $a $b")
    }
}

fun suma(a: Int, b: Int) {
    println("la suma de $a y $b es igual a ${a + b}")
}

fun max(a: Int, b: Int) = if (a > b) a else b

fun multiplicationToDouble(a: Int, b: Int): Float = (a * b).toFloat()

fun funWithFun(func: (Int, Int) -> Unit) {
    val a = 1
    val b = 2
    func(a, b)
}