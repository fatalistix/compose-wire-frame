package domain.model

import domain.util.Matrix
import domain.util.matrixOf
import kotlin.math.cos
import kotlin.math.sin

fun rotationMatrix(angleX: Double, angleY: Double, angleZ: Double): Matrix {
    val cosX = cos(angleX)
    val cosY = cos(angleY)
    val cosZ = cos(angleZ)

    val sinX = sin(angleX)
    val sinY = sin(angleY)
    val sinZ = sin(angleZ)

    return matrixOf(4, 4,
        cosY * cosZ,                      -sinZ * cosY,                      sinY,         0.0,
        sinX * sinY * cosZ + sinZ * cosX, -sinX * sinY * sinZ + cosX * cosZ, -sinX * cosY, 0.0,
        sinX * sinZ - sinY * cosX * cosZ, sinX * cosZ + sinY * sinZ * cosX,  cosX * cosY,  0.0,
        0.0,                              0.0,                               0.0,          1.0,
    )
}

fun zoomMatrix(zn: Double): Matrix = matrixOf(4, 4,
    zn,  0.0, 0.0, 0.0,
    0.0, zn,  0.0, 0.0,
    0.0, 0.0, 1.0, 0.0,
    0.0, 0.0, 0.0, 1.0,
)

fun perspectiveProjectionMatrix(d: Double): Matrix = matrixOf(4, 4,
    1.0, 0.0, 0.0, 0.0,
    0.0, 1.0, 0.0, 0.0,
    0.0, 0.0, 1.0, 0.0,
    0.0, 0.0, d,   0.0,
)
