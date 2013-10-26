/**
 * Created with IntelliJ IDEA.
 * User: amedee
 * Date: 10/25/13
 * Time: 8:30 PM
 * To change this template use File | Settings | File Templates.
 */
object Solution {
  def error() = {
    println("ERROR")
    exit()
  }

  def computeTimeTaken(D: Int) = {
    val a1 = 2.7
    val a2 = 3.8
    val max_v = 90 * 3600
    if (D > 2566) {
      val accel_time = max_v / a1
      val decel_time = max_v / a2
      val max_time = ((D - 2566) / max_v)
      //accel time + decel + time at max
      accel_time + decel_time + max_time
    }
    else {
      // solve the physics problem
      val d2 = D * (a1 / (a1 + a2))
      val d1 = D - d2
      val t2 = Math.sqrt((2 * D) / ((a2 * a2 / a1) + a2))
      val t1 = a2 * t2 / a1
      t1 + t2
    }
  }

  def main(args: Array[String]) {
    val inputs = try {
      readLine().split(" ").map(_.toInt)
    }
    catch {
      case _ => error
    }

    val N = inputs(0)
    val stations = inputs.slice(1, inputs.length)
    val M = stations.length

    val sum = stations.reduce(_ + _)

    //do checks
    if (sum != 100000 || (0 > N) || (N > 5) || (0 > M) || (M > 5)) {
      error
    }
    var schedule = List.fill(N, M)(0, 0)
    schedule(0, 0)(1) = 1
    printSchedule(schedule)
  }

  def printSchedule(sched: List[List[(Int,Int)]]) = {
    sched.zipWithIndex.foreach({
      case (index, train) =>
        print(index + 1)
        print(": *****")

        train.map({pair:(Int,Int) =>
        val departure = pair(1)
        val arrival = pair(2)
          print(f"- $departure%5i/t$arrival%di")
        }) } )
  }
}
