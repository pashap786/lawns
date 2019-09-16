package com.lawn.service;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileReader {

	public List<String> readFileLinesAsString(File file) {
		List<String> lines = new ArrayList<>();
		try {
			lines = Files.readAllLines(Paths.get(file.getName()), StandardCharsets.UTF_8);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return lines;
	}

}
