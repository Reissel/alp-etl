package main.test.br.cefet.alp.etl

import main.br.cefet.alp.etl.Extractor

object ExtractorTest {
  def main(args: Array[String]): Unit = {
    val extractor = new Extractor();
    val filename = "src/main/test/br/cefet/alp/etl/RelExtraPorPeriodo.csv"
    var vo = extractor.extract(filename)
    assert(vo!=null)
    assert(vo.size.equals(72))
    
    vo.foreach {
      timeEntry =>
        assert(timeEntry.getHours()!=null)
        assert(timeEntry.getEmployee()!=null)
        assert(timeEntry.getDate()!=null)
    }
    
  }
}