package statistics

object MeanMedianMode {

  def main(args: Array[String]) {
    val N: Int = InputUtils nextInt
    val X = InputUtils.splitNextLine.map(_.toInt)
    printf("%.1f\n", X.sum.toDouble/N )
    val XSorted = X.sorted
    printf("%.1f\n", ( XSorted((N/2)-1) + XSorted(N/2)).toDouble / 2)
    var count = 0;
    var max = 0
    var current = X(0)
    var mode: Int = -1
    XSorted.foreach(e => {
      if (e == current) {
        count += 1;
      } else {
        if (count > max) {
          max = count;
          mode = current;
        }
        count = 1;
        current = e
      }
    })
    printf("%d", mode)
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
