import scala.collection.mutable.MutableList

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

  def computeTimeTaken(D: Int): Int = {
    // D is in meters
    val a1 = 2700.0 / 3600.0 // (meters per hour) per second
    val a2 = 3800.0 / 3600.0
    // solve the physics problem
    val temp = a2 * a2 / a1 + a2
    val t2 = Math.sqrt((2 * D) / (temp))
    val t1 = a2 * t2 / a1
    val max_v = 90000.0 / 3600.0
    if (t1 > (max_v) / a1) {
      val accel_time = max_v / a1
      val accel_dist = (a1 / 2) * accel_time * accel_time
      val decel_time = max_v / a2
      val decel_dist = (a2 / 2) * decel_time * decel_time
      val max_time = (D - (accel_dist + decel_dist)) / max_v
      val total_time = accel_time + decel_time + max_time //accel time + decel + time at max
      Math.round(total_time.toFloat)
    }
    else {
      Math.round((t1 + t2).toFloat)
    }
  }

  def max(A: Double, B: Double) = {
    if (A > B) A
    else B
  }

  def calculateNthTimeTable(n: Int, stations: Array[Int]): MutableList[MutableList[(Int, Int)]] = n match {
    case 0 => {
      val schedule = MutableList.fill(stations.length)(0, 0)
      for (j <- 0 to stations.length - 1) {
        val dept_time = if (j == 0) 1
        else {
          schedule(j - 1)._2 + 121
        }
        val arr_time = dept_time + computeTimeTaken(stations(j))
        schedule(j) = (dept_time.toInt, arr_time.toInt)
      }
      new MutableList() += schedule
    }
    case n => {
      val schedule = MutableList.fill(stations.length)(0, 0)
      var previousN = calculateNthTimeTable(n - 1, stations)
      for (j <- 0 to stations.length - 1) {
        val dept_time = if (j == stations.length - 1) {
          max(schedule(j - 1)._2 + 121, previousN(n - 1)(j)._2 + 1)
        } else {
          max(schedule(j - 1)._2 + 121, previousN(n - 1)(j + 1)._1 + 1)
        } //Either it can go, and just waits 121, or it has to wait for the previous train to leave)
        val arr_time = dept_time + computeTimeTaken(stations(j))
        schedule(j) = (dept_time.toInt, arr_time.toInt)
      }
      previousN += schedule
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
    val schedule = calculateNthTimeTable(N - 1, stations)

    schedule.zipWithIndex.foreach({
      case (train, index) =>
        print((index + 1) + " ")
        print(": ***** ")

        train.map({
          pair: (Int, Int) =>
            val departure = pair._1
            val arrival = pair._2
            print(f"- $departure%5d  $arrival%5d ")
          //T~: *****~-~ddddd~~aaaaa~-~ddddd~~aaaaa~*****
        })
        println("*****")
    })
  }
}
