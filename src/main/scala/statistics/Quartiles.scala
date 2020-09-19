package statistics

object Quartiles {

  def main(args: Array[String]) {
    val N: Int = InputUtils nextInt
    val X = InputUtils.splitNextLine map(_.toInt)
    val XSorted = X.sorted
    val Q2 = median(XSorted, 0, N - 1)
    val Q1 = median(XSorted, 0, Q2._2)
    val Q3 = median(XSorted, Q2._3, N - 1)
    println(Q1._1)
    println(Q2._1)
    println(Q3._1)
  }

  def median(x:Seq[Int], startIndex: Int, endIndex: Int): (Int, Int, Int) = {
    val size = endIndex - startIndex + 1
    if ((size & 1) == 0)
      ((x(startIndex + (size / 2) - 1) + x(startIndex + (size / 2))) / 2, startIndex + (size / 2) - 1, startIndex + (size / 2))
    else
      (x(startIndex + (size / 2)), startIndex + (size / 2) - 1, startIndex + (size / 2) + 1)
  }

  object InputUtils {
    def splitNextLine(): Seq[String]  = nextLine split " "
    def nextLine: String              = scala.io.StdIn readLine
    def nextInt: Int                  = scala.io.StdIn readInt
    def nextLong: Long                = scala.io.StdIn readLong
  }
}
