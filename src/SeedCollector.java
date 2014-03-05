import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;

import javax.naming.LinkRef;

import cern.jet.random.engine.RandomSeedGenerator;
import cern.jet.random.engine.RandomSeedTable;
/**
 * Collects seeds from {@link RandomSeedGenerator} and puts them in a text file, one seed per line.
 * @author yoavram
 *
 */
public class SeedCollector {

	public SeedCollector() {
	}

	public void collect(String filename) throws FileNotFoundException {
		collect(filename, 10);
	}

	public void collect(String filename, int numOfSeeds) throws FileNotFoundException {
		PrintWriter wr = new PrintWriter(filename);
		int count = 0;
		for (int column = 0; column < 2; column++) {
			for (int row = 0; row < numOfSeeds/2; row++) {
				int seed = RandomSeedTable.getSeedAtRowColumn(row, column);
				//wr.println(String.format("row %d col %d seed %d",row,column,seed));
				wr.println(seed);
				count++;
			}
		}
		wr.close();
		System.out.println(String.format("Written %d seeds to file %s", count, filename));
	}
	
	public void eat(String filename,  int numOfSeeds) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(filename));
		String line = null;
		int count = 0;
		System.out.println(String.format("Readng %d lines from file %s",numOfSeeds,filename ));
		while ( (line = br.readLine()) != null && count < numOfSeeds) {
			System.out.println(line);
			count++;
		}
		br.close();
	}
	
	public static void main(String[] args) throws IOException {
		
		SeedCollector collector = new SeedCollector();
		collector.collect("seedstest.txt", 100);
		collector.eat("seedstest.txt",100);
	}

}
