package main.br.cefet.alp.etl.domain

class Employee {
  private var name:String = ""
  
  def this(name: String) {
    this()
    this.name = name
  }
  def setName(x: String) { 
        name= x 
  }
  def getName(): String = { 
    return name 
  }
  override def toString() : String = { 
    return "Employee [name=" + name + "]"
  } 
}