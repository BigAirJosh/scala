import java.time._
import java.time.temporal._
import java.time.format._

object cal {

  def main(args: Array[String]) {
    var date = LocalDate.now.withDayOfMonth(1)
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
      date = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern(pattern))
    }



    val header = DateTimeFormatter.ofPattern("MMMM yyyy").format(date);
    var space = (20 - header.length()) / 2
    while(space > 0) {
      print(" ")
      space -= 1
    }

    println(header)
    println("Mo Tu We Th Fr Sa Su")

    var start = 3 * (date.getDayOfWeek().getValue() - 1)
    while(start > 0) {
      print(" ")
      start -= 1
    }

    val fmt = DateTimeFormatter.ofPattern("d")
    val mon = date.getMonthValue()
    while(date.getMonthValue() == mon) {
      val d = fmt.format(date);
      if(d.length() < 2)
        print(" " + d + " ")
      else
        print(d + " ")
      if(date.getDayOfWeek().getValue() == 7)
        println("")
      date = date.plus(1, ChronoUnit.DAYS)
    }

    println("")

    

  }
}
