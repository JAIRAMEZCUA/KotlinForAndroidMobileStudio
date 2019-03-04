data class TimeKt(val years: Int, val days: Int)

operator fun TimeKt.plus(other: TimeKt): TimeKt {
    return TimeKt(
        this.years + other.years,
        this.days + other.days
    )
}

data class Counter(var index: Int) {
    operator fun inc(): Counter {
        index += 1
        return Counter(index )
    }
}

fun main() {
    val tiem1 = TimeKt(3, 15)
    val tiem2 = TimeKt(20, 2)

    val time3 = tiem1 + tiem2

    time3.print()

    val counter = Counter(5)
    counter.inc().print()
    print(++counter.index)
//    counter.print()
}