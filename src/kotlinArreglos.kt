fun main() {

    var arrA = intArrayOf(1, 123, 245, 2345, 524)
    var listA = arrayListOf<String>("Hola", "Kotlin", "Java", "Android")

    val num = arrA[0]
    arrA[2] = 42

    println(num)
    println("${arrA.contentToString()}")


    val mutableList = mutableListOf(1, 2, 3, 4, 5, 6, 7, 8)
    println(mutableList.toString())
    mutableList.add(9)
    println(mutableList.toString())
    mutableList.add(3, 10)
    println(mutableList.toString())
    mutableList[0] = 50
    println(mutableList.toString())


    val map = mutableMapOf<String, Int>(Pair("Key", 30))
    println(map.toString())
    map.put("key2", 40)
    println(map.toString())

    val mapKeyValue = map["Key"]
    println(mapKeyValue)


}