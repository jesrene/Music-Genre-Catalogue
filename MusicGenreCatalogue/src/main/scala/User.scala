import java.io._
import java.net._
import scala.io.StdIn

object User extends App {

  val client = new Socket("localhost", 4999)
  val is = new BufferedReader(new InputStreamReader(client.getInputStream()))
  val os = new PrintWriter(client.getOutputStream(), true)

  println(s"Connected to server attached to port ${client.getLocalPort}")
  println("\nWelcome to the Song Server!")

  var searchAnother = true
  while (searchAnother) {
    println("\n===================== MENU ====================")
    println("       Music Library - Choose an Option:        ")
    println("-----------------------------------------------")
    println(" 1. Search for a genre and get a list of music ")
    println(" 2. Add a new genre and its music              ")
    println("===============================================\n")

    val choice = StdIn.readLine("Enter your choice (1/2): ").toInt

    choice match {
      case 1 =>
        println("\n=============== Available Genres ==============")
        println("        Pop, Rock, HipHop, Electronic, \n        Country, R&B, Classical, Jazz ")
        println("          OR any newly added genres")
        println("===============================================\n")
        var genreFound = false
        var genreInput: String = ""

        while (!genreFound) {
          genreInput = StdIn.readLine("Enter a genre to find a list of songs: ").toUpperCase()
          os.println(genreInput)

          var response = is.readLine()
          if (response == "Genre not found.") {
            println("Genre not found. Please enter a valid genre. \n")
          } else {
            genreFound = true
            while (response != null && response != "EndOfSongs") {
              println(response)
              response = is.readLine()
            }
          }
        }

      case 2 =>
        os.println("ADD")

        print("\nEnter new genre: ")
        val newGenre = StdIn.readLine().toUpperCase()
        os.println(newGenre)

        println(s"\nEnter songs for $newGenre separated by commas (Once done, press Enter and type 'DONE'):")
        var newSong = StdIn.readLine().trim()
        while (newSong != "DONE") {
          os.println(newSong)
          newSong = StdIn.readLine().trim()
        }
        os.println("DONE")

        var response = is.readLine()
        while (response != null && response != "Genre and songs added successfully.") {
          response = is.readLine()
        }
        println("Genre and songs added successfully.")

      case _ =>
        println("Invalid choice. Please enter 1 or 2.")
    }

    val next = StdIn.readLine("\nDo you want to proceed with another action? (Y/N): ").toUpperCase()
    if (next == "N") {
      os.println("EXIT")
      searchAnother = false
    }
  }

  println("\nDisconnecting from the server...")
  client.close()
  println("Disconnected")
}
