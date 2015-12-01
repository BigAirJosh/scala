import java.time._
import java.time.temporal._
import java.time.format._

object cal {

  def main(args: Array[String]) {
    val monthYearParser = DateTimeFormatter.ofPattern("dd MM yyyy")
    val monthYearFormatter = DateTimeFormatter.ofPattern("MMMM yyyy")
    val dayFormatter = DateTimeFormatter.ofPattern("EE")

    var date = LocalDate.now
    if(args.length > 0) {
      var pattern = "dd "
      var dateStr = "01 "
      if(args.length > 0) {
        dateStr += args(0) + " "
        if(args(0).length() == 1)
          pattern += "M "
        else if(args(0).length() == 2)
          pattern += "MM "
        else if(args(0).length() == 3)
          pattern += "MMM "
        else
          pattern += "MMMM "
      }

      if(args.length > 1) {
        dateStr += args(1)
        if(args(1).length() == 2)
          pattern += "yy"
        else
          pattern += "yyyy"
      } else {
        dateStr += date.getYear()
        pattern += "yyyy"
      }
      //println(pattern)
      //println(dateStr)
      date = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern(pattern))
    }



    val header = monthYearFormatter.format(date);
    var space = (20 - header.length()) / 2
    while(space > 0) {
      print(" ")
      space -= 1
    }

    println(header)
    println("Su Mo Tu We Th Fr Sa")



  }
}
