package minesweeper.bestscore.loader.json;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.Set;

import minesweeper.bestscore.BestScoreModel;
import minesweeper.bestscore.BestScoresLoader;

import org.apache.commons.io.output.FileWriterWithEncoding;

import com.google.common.collect.Sets;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class BestScoreJsonLoader implements BestScoresLoader {

	private static final Type TYPE = new TypeToken<Set<BestScoreModel>>() {}.getType();
	private String fileName;
	
	
	public BestScoreJsonLoader(String fileName) {
		this.fileName = fileName;
	}
	
	
	@Override
	public Set<BestScoreModel> load() {
		try {
			Gson gson = new GsonBuilder().create();
			return gson.fromJson(new FileReader(fileName), TYPE);
		} catch (FileNotFoundException e) {
			return Sets.<BestScoreModel>newHashSet();
		}
	}

	@Override
	public void save(Set<BestScoreModel> bestScores) {
		try {
			Writer writer = new FileWriterWithEncoding(fileName, "UTF-8");
			Gson gson = new GsonBuilder().create();
			gson.toJson(bestScores, writer);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
