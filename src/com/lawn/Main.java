package com.lawn;

import java.io.File;
import java.util.List;

import com.lawn.pojo.Lawn;
import com.lawn.service.FileReader;

public class Main {

	static FileReader fileReader = new FileReader();
	private final static String END = ">_";
	
	public static void main(String[] args) {

		if (args[0] != null) {
			if (args[0].contains("csv")) {

				File file = new File(args[0]);
				List<String> fileLines = fileReader.readFileLinesAsString(file);
//TODO: build the lawn board?
				
				Lawn lawn = Lawn.readScenario(fileLines);
				lawn.mow();
				System.out.println(END);
			}
		}

	}

	
	
}
