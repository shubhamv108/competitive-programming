package code.statistics

object StandardDeviation {

  def main(args: Array[String]): Unit = {
    val N = InputUtils.nextInt
    val X = InputUtils.splitNextLine.map(_.toInt)
    val mean = X.sum.toDouble / N
    val variance = X.foldLeft(0D)( (s, e) => s + `^2`(e - mean) ) / N
    val standardDeviation = sqrt(variance * 10).round.toDouble / 10
    printf("%.1f", standardDeviation)
  }

  def `^2`(x: Double) = x * x

  def sqrt(n: Double): Double = {
    var x = n
    var y = 1d
    val e = 0.001
    while (x - y > e) {
      x = (x + y) / 2
      y = n / x
    }
    x
  }

  object InputUtils {

    def splitNextLine(): Seq[String] = {
      return nextLine split " "
    }

    def nextLine: String = {
      return scala.io.StdIn.readLine
    }

    def nextInt: Int = {
      return scala.io.StdIn.readInt
    }

    def nextLong: Long = {
      return scala.io.StdIn.readLong
    }
  }

}
