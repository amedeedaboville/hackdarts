import scala.collection.immutable.Range

object oohScala {
def main(args: Array[String]) {
  val N =  readLine().toInt
  val hills: Array[BigInt] = readLine().split("\\s+").filterNot(_.isEmpty()).map(BigInt(_))
  val answ = hills.scanRight(BigInt(0))({(x, y) => y + x })
  println(answ.drop(1).max)
 }
}
