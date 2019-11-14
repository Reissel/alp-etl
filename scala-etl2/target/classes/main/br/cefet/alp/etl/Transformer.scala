package main.br.cefet.alp.etl

import com.typesafe.scalalogging.Logger
import main.br.cefet.alp.etl.vo.TimeEntry
import main.br.cefet.alp.etl.domain.WorkDay
import scala.collection.mutable.TreeMap
import main.br.cefet.alp.etl.domain.Employee
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.LocalTime
import scala.collection.mutable.ArrayBuffer

class Transformer {
  val logger = Logger("")
  
  def transform(entries:ArrayBuffer[TimeEntry]):ArrayBuffer[WorkDay] = {
    var result: ArrayBuffer[WorkDay] = ArrayBuffer[WorkDay]()
    var employees: TreeMap[String, Employee] = TreeMap.empty[String,Employee]
    
    entries.foreach {
      timeEntry =>
        var employeeString = timeEntry.getEmployee()
        var dateString = timeEntry.getDate()
        var hoursString = timeEntry.getHours()
        
        var employee:Employee = employees.getOrElse(employeeString, null)
        
        if(employee == null) {
          employee = new Employee(employeeString)
          employees.+=(employeeString -> employee)
        }
        
        var date:LocalDate = LocalDate.parse(dateString+"/19",DateTimeFormatter.ofPattern("dd/MM/yy"))
        
        var punches:ArrayBuffer[LocalTime] = ArrayBuffer[LocalTime]()
        var punchesArray = hoursString.split(" ")
        
        punchesArray.foreach {
          punchString =>
            //println(punchString)
            var punch: LocalTime = LocalTime.parse(punchString)
            punches.+=(punch)
        }
        
        var workDay = new WorkDay(employee, date, punches)
        result.+=(workDay)
        
        logger.debug(workDay.toString())
    }
    
    logger.info("Tansformation process ended. " + result.length + " elements processed!")
    
    return result
  }
}