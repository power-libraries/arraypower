package com.github.powerlibraries.array.generator;

import java.io.File;
import java.io.IOException;

public class ArrayGenerator {

	public static void main(String[] args) throws IOException {
		File targetDirectory;
		if(args.length==1) {
			targetDirectory = new File(args[0]);
		}
		else {
			 targetDirectory = new File("../arraypower");
		}
		
		com.github.powerlibraries.primitive.collections.generator.Generator.generateCode(
			new File("templates"),
			targetDirectory
		);
	}
}
