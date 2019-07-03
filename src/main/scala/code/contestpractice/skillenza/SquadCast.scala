package com.demo.example

import scala.collection.mutable.ArrayBuffer

object SquadCast {

  def main(args: Array[String]) = {
    var t: Int = InputUtils.nextInt
    while (t > 0) {
      t -= 1
      val line: Seq[String] = InputUtils.splitNextLine
      val m: Int = line(0).toInt
      val n: Int = InputUtils.nextInt
      val weights =
        Range(0, m).foldLeft(ArrayBuffer.empty[Int])((acc, i) => acc += line(i + 1).toInt)

      println(new Solution(weights, n).solve)
    }
  }

  class Solution(weights: ArrayBuffer[Int], sum: Int) {

    def solve: String = {
      val m = weights.length
      val subSet = Array.ofDim[Boolean](2, sum + 1)
      Range(0, m + 1).foreach(i => {
        Range(0, sum + 1).foreach(j => {
          if (j == 0)
            subSet(i % 2)(j) = true
          else if (i == 0)
            subSet(i % 2)(j) = false
          else if (weights(i - 1) <= j)
            subSet(i % 2)(j) =
              subSet((i + 1) % 2)(j - weights(i - 1)) || subSet((i + 1) % 2)(j)
          else
            subSet(i % 2)(j) = subSet((i + 2) % 2)(j)
        })
      })
      return if (subSet(m % 2)(sum))
        "YES"
      else
        "NO"
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


