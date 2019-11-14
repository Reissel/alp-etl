package main.br.cefet.alp.etl.domain

import java.time.LocalDate
import java.time.LocalTime
import scala.collection.mutable.ArrayBuffer

class WorkDay {
  private var employee: Employee = new Employee
  private var date: LocalDate = null
  private var punches: ArrayBuffer[LocalTime] = null
  
  def this(employee: Employee, date: LocalDate, punches: ArrayBuffer[LocalTime]) {
    this()
    this.employee = employee
    this.date = date
    this.punches = punches
  }
  def setEmployee(x: Employee) { 
        employee= x 
  }
  def getEmployee(): Employee = { 
    return employee 
  }
  def setDate(x: LocalDate) { 
        date= x 
  }
  def getDate(): LocalDate = { 
    return date 
  }
  def setPunches(x: ArrayBuffer[LocalTime]) { 
        punches= x
  }
  def getPunches(): ArrayBuffer[LocalTime] = { 
    return punches 
  }
  
  override def toString() : String = { 
    return "WorkDay [employee=" + employee +  ", date=" + date + ", punches=" + punches + "]"
  } 
}