package main.br.cefet.alp.etl

import scala.io.Source
import com.typesafe.scalalogging.Logger
import main.br.cefet.alp.etl.vo.TimeEntry
import scala.collection.mutable.ArrayBuffer
import scala.collection.Seq

class Extractor {
  def extract(filename: String): ArrayBuffer[TimeEntry] = {
    
    var entries = ArrayBuffer[TimeEntry]()
    
    val patternEmp = "\\;Empregado:\\;{4}([À-ÿA-Za-z ]*)\\;*".r
    val patternWkDay = "([0-9]{2}/[0-9]{2}) - ([a-z]{3})\\;{3}([0-9: ]*).*".r
    val patternWkJourney = "\\;Jornada \\+ Abono\\;{7}Jornada total\\;{4}([0-9]{2}:[0-9]{2}).*".r
    
    var empregado = ""
    var dia = ""
    var horas = ""
    
    val logger = Logger("")
    
    val regexs = Seq[scala.util.matching.Regex](patternEmp,patternWkDay,patternWkJourney)
    
    for (line <- Source.fromFile(filename).getLines) {
      
      var result = matchRegex(line, patternEmp, 1)
      
      if(result != null) {
        empregado = result(0)
      }
      
      result = matchRegex(line, patternWkDay, 3)
      
      if(result != null) {
        dia = result(0)
        horas = result(2)
        
        if(!horas.equals("")) {
          var vo = new TimeEntry(empregado,dia,horas)
          entries.+=(vo)
          logger.debug(vo.toString())
        }
      }      

    }
    logger.info("Extraction process ended. " + entries.size + " elements processed!")
    return entries
  }
  
  def matchRegex(line: String, regex: scala.util.matching.Regex, groups: Integer): Array[String] = {
    val m = regex.findAllIn(line).matchData foreach {
      n =>
        if(n != null) {
          var result = new Array[String](3)
          for (i <- 0 until groups) {
            result(i) = n.group(i+1)
          }
        return result
      }
    }
    return null
  }

}