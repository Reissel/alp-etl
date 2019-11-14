package main.test.br.cefet.alp.etl

import main.br.cefet.alp.etl.Extractor
import main.br.cefet.alp.etl.Transformer

object TransformerTest {
  def main(args: Array[String]): Unit = {
    val extractor = new Extractor();
    val transformer = new Transformer();
    val filename = "src/main/test/br/cefet/alp/etl/RelExtraPorPeriodo.csv"
    
    var vo = extractor.extract(filename)
    var workDays = transformer.transform(vo)
    assert(workDays!=null)
    assert(workDays.size.equals(72))
    
    workDays.foreach {
      wk =>
        assert(wk.getEmployee()!=null)
        assert(wk.getDate()!=null)
        assert(wk.getPunches()!=null)
        assert(wk.getPunches().size > 0)
    }
  }
}