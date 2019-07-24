package code.contestpractice.skillenza

object BitTogether {

  def main(args: Array[String]) = {
    var t: Int = InputUtils.nextInt
    while (t > 0) {
      t -= 1
      val l = InputUtils.nextInt
      val line: String = InputUtils.nextLine
      println(new Solution(l, line).solve)
    }
  }

  class Solution(l: Int, s: String) {

    def solve: Int = {
      var result = 0;
      if (l > 1) {
        var defaultChar = '1';
        var defaultCharCount = 0;
        var totalZeroes = 0;
        Range(0, l).foreach(i => {
          if (s.charAt(i) == '1')
            defaultCharCount += 1
          if (s.charAt(i) == '0')
            totalZeroes += 1
        })

        if (totalZeroes < defaultCharCount) {
          defaultCharCount = totalZeroes;
          defaultChar = '0';
        }

        var defaultCharStartingCount = 0;
        Range(0, defaultCharCount).foreach(i => {
          if (s.charAt(i) == defaultChar)
            defaultCharStartingCount += 1
        })

        var defaultCharEndingCount = 0;
        Range(l - defaultCharCount, l).reverse.foreach(i => {
          if (s.charAt(i) == defaultChar)
            defaultCharEndingCount += 1;
        })
        result = defaultCharCount - Math.max(defaultCharStartingCount,
                                             defaultCharEndingCount);
      }
      result
    }
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
