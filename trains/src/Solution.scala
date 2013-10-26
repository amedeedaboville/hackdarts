/**
 * Created with IntelliJ IDEA.
 * User: amedee
 * Date: 10/25/13
 * Time: 8:30 PM
 * To change this template use File | Settings | File Templates.
 */
  object Solution {
   def error() = {
    println ("ERROR")
    exit()
   }
  def computeTimeTaken(distance :Int) = {
    if(distance > 2566) {
     90/2.7 + (90/3.8) + ((distance-2566)/90.0)
    //accel time + decel + time at max
    }
    else { // solve the physics problem
     0.0
    }
  }
    def main(args: Array[String]) {
      val inputs =  try { readLine().split(" ").map(_.toInt) }
                    catch { case _ =>  error }

        val N = inputs(0)
        val stations = inputs.slice(1, inputs.length)
        val M = stations.length

        val sum = stations.reduce(_+_)

      //do checks
        if (sum != 100000 || (0 > N) || (N > 5) || (0 > M) || (M > 5)) {
          error
        }


      }
  }
