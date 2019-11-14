package main.test.br.cefet.alp.etl

import main.br.cefet.alp.etl.Extractor
import main.br.cefet.alp.etl.Transformer
import main.br.cefet.alp.etl.Loader
import com.typesafe.scalalogging.Logger

object LoaderTest {
  def main(args: Array[String]): Unit = {
    val logger = Logger("")
    
    val extractor = new Extractor();
    val transformer = new Transformer();
    val loader = new Loader();
    val filename = "src/main/test/br/cefet/alp/etl/RelExtraPorPeriodo.csv"
    
    var vo = extractor.extract(filename)
    var workDays = transformer.transform(vo)
    
    val count = loader.load(workDays)
    
    logger.info("Total de entradas salvas no banco: " + count)
    
  }
}