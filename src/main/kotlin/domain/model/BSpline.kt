package domain.model

import domain.util.Point2D
import domain.util.matrixOf

class BSpline {

    companion object {
        val M = matrixOf(4, 4,
            -1.0/6.0,  3.0/6.0, -3.0/6.0,  1.0/6.0,
            3.0/6.0, -6.0/6.0,  3.0/6.0,  0.0/6.0,
            -3.0/6.0,  0.0/6.0,  3.0/6.0,  0.0/6.0,
            1.0/6.0,  4.0/6.0,  1.0/6.0,  0.0/6.0,
        )
    }

    private val anchorPoints = mutableListOf<Point2D>()
    private val bSplinePoints = mutableListOf<Point2D>()

    private val canBuild
        get() = anchorPoints.size >= 4

    fun recount(segmentCount: Int) {
        bSplinePoints.clear()

        if (!canBuild) {
            return
        }

        val segmentStep = 1.0 / segmentCount

        for (i in 1..<anchorPoints.size - 2) {
            val gx = matrixOf(
                1, 4,
                anchorPoints[i - 1].x,
                anchorPoints[i].x,
                anchorPoints[i + 1].x,
                anchorPoints[i + 2].x,
            )

            val gy = matrixOf(
                1, 4,
                anchorPoints[i - 1].y,
                anchorPoints[i].y,
                anchorPoints[i + 1].y,
                anchorPoints[i + 2].y,
            )

            val mgx = M * gx
            val mgy = M * gy

            val pointsInSegment = if (i == anchorPoints.size - 3) segmentCount + 1 else segmentCount

            for (j in 0..<pointsInSegment) {
                val t = j * segmentStep

                val tVector = matrixOf(1, 4,
                    t * t * t, t * t, t, 1.0,
                )

                val x = (tVector * mgx)[0, 0]
                val y = (tVector * mgy)[0, 0]

                bSplinePoints.add(Point2D(x, y))
            }
        }
    }
}