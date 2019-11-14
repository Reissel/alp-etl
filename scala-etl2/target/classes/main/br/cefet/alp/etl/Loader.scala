package main.br.cefet.alp.etl

import scala.collection.mutable.ArrayBuffer
import main.br.cefet.alp.etl.domain.WorkDay
import org.mongodb.scala._
import scala.concurrent.Await
import scala.concurrent.duration._
import tour.Helpers._
import com.typesafe.scalalogging.Logger

class Loader {
  def load(results:ArrayBuffer[WorkDay]) : Long = {
    val mongoClient: MongoClient = MongoClient()
    
    val logger = Logger("")
    
    val database: MongoDatabase = mongoClient.getDatabase("mydb")
    
    val collection: MongoCollection[Document] = database.getCollection("test");
    
    val documents = Seq[WorkDay]()
    
    results.foreach {
      result =>
        var empregado = result.getEmployee().getName()
        var day = result.getDate().toString()
        var punches = result.getPunches().toString()
        
        val doc: Document = Document("Empregado" -> empregado, "Data" -> day, "Punches" -> punches)
    
        val observable: Observable[Completed] = collection.insertOne(doc)
        
        //collection.insertOne(doc).results()
        
        //Aqui a insercao é controlada e a thread principal só termina quando aqui terminar
        observable.subscribe(new Observer[Completed] {
          override def onNext(result: Completed): Unit = logger.info("Inserted")
        
          override def onError(e: Throwable): Unit = logger.info("Failed")
        
          override def onComplete(): Unit = logger.debug("Completed")
        })
        
    }
    
    val insertObservable = collection.find()
    
      val insertAndCount = for {
        insertResult <- insertObservable
        countResult <- collection.countDocuments()
      } yield countResult

    val count = insertAndCount.headResult()
      
    mongoClient.close()
    
    return count

  }
}