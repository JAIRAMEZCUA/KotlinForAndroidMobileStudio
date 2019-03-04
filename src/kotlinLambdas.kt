val sum: (Int, Int) -> Int = { x, y ->
    val res = x + y
    print(res)
    res / 2
//    res
}
val resta: (Int, Int) -> Int = { x, y -> x - y }

val minFour: (String) -> Boolean = { it.length > 4 }

fun some(): String {

    fun other(): String {
        return@other "asdfasf"
    }

    return ""
}

fun main() {

    val auto: Auto? = Auto("Ford", 6)

    auto?.let {
        it.numPuertas
        it.marca
        with(it) {
            this.marca
            marca
            numPuertas
        }
    }

    auto?.let {
        //        return it.marca
        return@let it.marca
    }?.also {

    }

    auto?.apply {
        marca
        numPuertas + 3
    }.toString()

    auto?.takeIf { it.numPuertas > 4 }

    repeat(10) { index ->
        index.print()
    }

    val resultado = sum(4, 2)
    val resultadoRst = resta(4, 2)
    resultado.print()
    resultadoRst.print()

    val isminFour = minFour("asdfasdf")
}