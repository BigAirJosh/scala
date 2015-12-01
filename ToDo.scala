import scala.io.Source
import scala.io.StdIn
import java.io.PrintWriter
import java.io.File

object ToDo {

  def apply(f: (Int, String) => String, i: Int, s: String) = f(i, s)

  def listify(i: Int, s: String) = "[" + i + "] " + s
  def optionify(i: Int, s: String) = s + " [" + i + "]"

  def loadList = {
    val items = Source.fromFile("todo.dat").getLines.toList
    items
  }

  def listItems {
    println("")
    println("Current items:")
    println("")
    var index = 1;
    val items = loadList
    for(item <- items) {
      println(apply(listify, index ,item))
      index += 1
    }
    println("")
  }

  /**
   * 
  **/
  def addItem() {
    print("Enter todo: ")
    val item = StdIn.readLine
    val items = loadList
    val writer = new PrintWriter(new File("todo.dat"))
    for(item <- items)
      writer.println(item)
    writer.println(item)
    writer.close()
    println("")
    println("todo list saved")
  }

  def deleteItem() {
    print("Enter todo number to delete:")
    try {
      val deleteIndex = StdIn.readLine.toInt
      println("")
      val items = loadList
      if(deleteIndex > 0 && deleteIndex <= items.length) {
	val writer = new PrintWriter(new File("todo.dat"))
	var index = 1;
	for(item <- items) {
	  if(index != deleteIndex)
	    writer.println(item)
	  index += 1
	}
	writer.close()
	println("todo list saved")
      } else
	println("invalid item number")
    } catch {
      case nfe: NumberFormatException => println("please enter a valid item number")
      case e: Exception => println("unknown error")
    }
  }

  def exit {
    System.exit(1)
  }


  class Command(f: () => Unit, m: String) {
    def message(): String = m
    def apply(): Unit = f()
  }

  def main(args: Array[String]) {

    val commands = Array(new Command(listItems _, "list the current todo items"),
      new Command(addItem _, "enter a todo item"),
      new Command(deleteItem _, "delete a todo item"),
      new Command(exit _, "exit"))

    println("Welcome to todo, you can:")
    println("")
    var index = 1
    for(command <- commands) {
      println(apply(listify, index, command.message))
      index += 1
    }

    println("")
    while(true) {
      print("Please select an option: ")
      try {
    	val option = StdIn.readLine.toInt
	println("")
	if(option > 0 && option <= commands.length) {
	  commands(option - 1).apply
	  //println(fn)
	} else {
	  println("invalid option: " + option)
 	}
      } catch {
	case nfe: NumberFormatException => println("please enter a valid option number")
	case e: Exception => println("unknown error")
      }
    }
    
  }
}
