import java.io._
import java.net._
import scala.io.Source

object SongListServer extends App {

  // Create a server socket
  val server = new ServerSocket(4999)
  println("Server is running...")

  // Continuously accept incoming client connections
  while (true) {
    val client: Socket = server.accept()

    val in = new BufferedReader(new InputStreamReader(client.getInputStream()))
    val out = new PrintWriter(client.getOutputStream(), true)

    try {
      var continueProcessing = true

      // Continuously process requests from the client until 'EXIT' is received
      while (continueProcessing) {
        val genreInput = in.readLine().toUpperCase()

        if (genreInput == "EXIT") {
          continueProcessing = false
        } else if (genreInput == "ADD") {
          out.println("Enter new genre:")
          val newGenre = in.readLine().toUpperCase()

          // Read new songs associated with the genre
          println(s"Enter three songs for $newGenre separated by commas (type 'DONE' when finished):")
          val songsBuffer = scala.collection.mutable.ListBuffer[String]()
          var song = in.readLine().trim() // Apply trim to remove leading/trailing spaces
          while (song != "DONE") {
            songsBuffer += song
            song = in.readLine().trim() // Apply trim to remove leading/trailing spaces
          }
          val newSongs = songsBuffer.toList

          val file = new FileWriter("SongList.txt", true)
          file.write(s"\n$newGenre: ${newSongs.mkString(", ")}")
          file.close()

          // Send the confirmation after successful addition
          out.println("Genre and songs added successfully.")
        } else {
          val fileStream = Source.fromFile("SongList.txt")
          if (fileStream == null) {
            out.println("Error: SongList.txt not found.")
          } else {
            var found = false
            for (line <- fileStream.getLines()) {
              // Split the line into genre and songs
              val parts = line.split(":")

              if (parts.length == 2 && parts(0).trim.toUpperCase == genreInput) {
                found = true
                val songs = parts(1).split(",").map(_.trim)
                songs.foreach(out.println)
                out.println("EndOfSongs")
              }
            }
            fileStream.close()

            if (!found) {
              out.println("Genre not found.")
            }
          }
        }
      }
    } catch {
      // Print an error message if an exception occurs
      case e: Exception =>
        out.println(s"Error: ${e.getMessage}")
        e.printStackTrace()
    } finally {
      client.close()
    }
  }
}
