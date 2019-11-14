package main.br.cefet.alp.etl.vo

class TimeEntry {
  private var employee:String = ""
  private var date:String = ""
  private var hours:String = ""
  
  def this(employee: String, date:String, hours:String)
  {
    this()
    this.employee = employee
    this.date = date
    this.hours = hours
  }
  
      // Class method  
    def setEmployee(x: String) 
    { 
        employee= x 
    }
    
    def setDate(x: String) 
    { 
        date= x 
    }
    
    def setHours(x: String) 
    { 
        hours= x 
    }
    
    def getEmployee(): String ={ 
      return employee 
    }
    
    def getDate(): String ={ 
      return date 
    }
        
    def getHours(): String ={ 
      return hours 
    }
    
    override def toString() : String = { 
      return "TimeEntryVO [employee=" + employee + ", date=" + date + ", hours=" + hours + "]";
    } 
}