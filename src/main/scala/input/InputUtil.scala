package input

object InputUtil {
  def splitNextLineDouble                       : Seq[Double]    = splitNextLineDouble(" ")
  def splitNextLineDouble(regex: String)        : Seq[Double]    = splitNextLine(regex) map (_.toDouble)
  def splitNextLineInt                          : Seq[Int]       = splitNextLineInt(" ")
  def splitNextLineInt(regex: String)           : Seq[Int]       = splitNextLine(regex) map (_.toInt)
  def splitNextLine                             : Seq[String]    = splitNextLine(" ")
  def splitNextLine(regex: String)              : Seq[String]    = nextLine split regex
  def nextLine                                            = scala.io.StdIn readLine
  def nextInt                                                    = scala.io.StdIn readInt
  def nextLong                                                   = scala.io.StdIn readLong
}