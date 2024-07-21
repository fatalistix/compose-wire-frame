package domain.util

interface Matrix {
    val cols: Int
    val rows: Int

    operator fun get(i: Int, j: Int): Double

    operator fun times(other: Matrix): Matrix
}

private open class ListMatrix(
    override val cols: Int,
    override val rows: Int,
    private val list: List<Double>,
) : Matrix {

    override operator fun get(i: Int, j: Int): Double = list[i * cols + j]

    override operator fun times(other: Matrix): Matrix {
        if (rows != other.cols) {
            throw IllegalArgumentException("matrices don't match")
        }

        return createMatrix(cols, other.rows) { x, y ->
            var value = .0

            for (i in 0..<rows) {
                value += this[x, i] * other[i, y]
            }

            value
        }
    }
}

fun matrixOf(cols: Int, rows: Int, vararg elements: Double): Matrix =
    ListMatrix(cols, rows, elements.toList())

fun createMatrix(cols: Int, rows: Int, init: (Int, Int) -> Double): Matrix =
    ListMatrix(cols, rows, prepareListForMatrix(cols, rows, init))

private inline fun <T> prepareListForMatrix(cols: Int, rows: Int, init: (Int, Int) -> T): ArrayList<T> {
    val list = ArrayList<T>(cols * rows)
    for (y in 0..<rows) {
        for (x in 0..<cols) {
            list.add(init(x, y))
        }
    }
    return list
}
