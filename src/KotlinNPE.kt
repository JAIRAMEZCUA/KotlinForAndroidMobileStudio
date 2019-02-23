fun main() {

    var a: String = "abc"

    var b: String? = "abc"

//    a = null
    b = null

    val aLength = a.length
    val bLength = b?.length

    val cLength = if (b != null) {
        b.length
    } else {
        0
    }

    var dLength = b?.length ?: 0

    println(aLength)
    println(bLength)
    println(cLength)
    println(dLength)

//    print(b!!.length)

    if (b != null) {
        val sum = b + 1
        println(sum)
    } else {
        println("B es $b")
    }

    var dataToSum: Int? = 0
//    dataToSum = null
    val letResult = dataToSum?.let { dataInt ->
        val sum = dataInt.plus(1)
        println("La suma es $sum")
    } ?: print("DataToSum es $dataToSum")
    
}