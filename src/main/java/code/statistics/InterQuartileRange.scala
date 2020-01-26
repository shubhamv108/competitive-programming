package code.statistics

import code.statistics.Quartiles.median

object InterQuartileRange {

  def main(args: Array[String]) {
    val N = InputUtils nextInt
    val X = InputUtils splitNextLineInt
    val F = InputUtils splitNextLineInt
    val z = Stream.range(0, N).foldLeft(Seq.empty[Int])((arr, i) => Stream.range(0, F(i)).foldLeft(arr)((a, j) => a :+ X(i))).sorted
    val Q2 = median(z, 0, z.length - 1)
    val Q1 = median(z, 0, Q2._2)
    val Q3 = median(z, Q2._3, z.length - 1)
    val IQR = ((Q3._1 - Q1._1) * 10).round.toDouble / 10
    print(IQR)
  }

  def median(x:Seq[Int], startIndex: Int, endIndex: Int): (Double, Int, Int) = {
    val size = endIndex - startIndex + 1
    if ((size & 1) == 0)
      ((x(startIndex + (size / 2) - 1) + x(startIndex + (size / 2))).toDouble / 2, startIndex + (size / 2) - 1, startIndex + (size / 2))
    else
      (x(startIndex + (size / 2)), startIndex + (size / 2) - 1, startIndex + (size / 2) + 1)
  }


  object InputUtils {
    def splitNextLineInt: Seq[Int]       = splitNextLine map(_.toInt)
    def splitNextLine:    Seq[String]    = nextLine split " "
    def nextLine                  = scala.io.StdIn readLine
    def nextInt                          = scala.io.StdIn readInt
    def nextLong                         = scala.io.StdIn readLong
  }
}