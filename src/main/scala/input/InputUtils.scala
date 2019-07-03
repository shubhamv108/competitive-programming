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