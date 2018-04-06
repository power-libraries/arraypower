package com.github.powerlibraries.array.generator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;
import org.jtwig.environment.EnvironmentConfiguration;
import org.jtwig.environment.EnvironmentConfigurationBuilder;
import org.jtwig.property.selection.cache.NoSelectionPropertyResolverCache;
import org.slf4j.impl.SimpleLogger;

import com.github.powerlibraries.primitive.collections.generator.Type;

public class Generator {

	public static void main(String[] args) throws IOException {
		generateCode(
				new File("templates"),
				new File("../arraypower/src/main/java/com/github/powerlibraries/array/")
			);
		
		generateCode(
			new File("test-templates"),
			new File("../arraypower/src/test/java/com/github/powerlibraries/array/")
		);
	}
	
	public static void generateCode(File in, File target) throws IOException {
		System.setProperty("org.slf4j.simpleLogger.defaultLogLevel", "trace");
		final EnvironmentConfiguration configuration = EnvironmentConfigurationBuilder
            .configuration()
            	.render()
            		.withStrictMode(true)
            	.and()
            	.propertyResolver()
            		.withCache(NoSelectionPropertyResolverCache.noSelectionPropertyResolverCache())
            		.propertyResolverStrategies()
            		.and()
            	.and()
	        .build();
		
		
		List<File> l = Files
			.walk(in.toPath())
			.map(Path::toFile)
			.filter(File::isFile)
			.filter(f->f.getName().endsWith(".twig"))
			.collect(Collectors.toList());
		
		for(File f:l) {
			JtwigTemplate nameTemplate = JtwigTemplate.inlineTemplate(f.getName().substring(0,f.getName().length()-5), configuration);
			JtwigTemplate template = JtwigTemplate.fileTemplate(f, configuration);
			File folder = new File(target, in.toPath().relativize(f.getParentFile().toPath()).toString());
			folder.mkdirs();
			
			for(Type t:Type.values()) {
				JtwigModel model = JtwigModel
					.newModel()
					.with("t", t)
					.with("allTypes", Type.values());
				
				File res = new File(folder, nameTemplate.render(model));
				try (OutputStream out = new FileOutputStream(res)) {
					template.render(model, out);
				} catch(Exception e) {
					throw new RuntimeException("Failed in "+f.getName()+" with k="+t, e);
				}
			}
		}
	}
}