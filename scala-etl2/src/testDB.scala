import org.mongodb.scala._
import scala.concurrent.Await
import scala.concurrent.duration._
import tour.Helpers._

object testDB {
  
  def main(args: Array[String]): Unit = {
    // To directly connect to the default server localhost on port 27017
    val mongoClient: MongoClient = MongoClient()
    
    val database: MongoDatabase = mongoClient.getDatabase("mydb")
    
    val collection: MongoCollection[Document] = database.getCollection("test");
    
    val doc: Document = Document("_id" -> 0, "name" -> "MongoDB", "type" -> "database","count" -> 1, "info" -> Document("x" -> 203, "y" -> 102))
    
    val observable: Observable[Completed] = collection.insertOne(doc)
    
    collection.insertOne(doc).results()
    
    //Aqui a insercao é controlada e a thread principal só termina quando aqui terminar
    observable.subscribe(new Observer[Completed] {
      override def onNext(result: Completed): Unit = println("Inserted")
    
      override def onError(e: Throwable): Unit = println("Failed")
    
      override def onComplete(): Unit = println("Completed")
    })
    
    mongoClient.close()
    
  }
}