package statistics

object WeightedMean {

    def main(args: Array[String]) {
      val N = InputUtils nextInt
      val X = InputUtils.splitNextLine.map(_.toInt)
      val W = InputUtils.splitNextLine.map(_.toInt)
      var WSum = 0;
      val r = Seq.range(0, N).foldLeft(0)((R, i) => {
        WSum += W(i)
        R + (X(i) * W(i))
      })

      printf("%.1f", r.toDouble/WSum)
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
