package com.im.out.graphics;

import static org.lwjgl.opengl.GL20.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Shader {

	private int program;
	private int vs; //vertex shader
	private int fs; //fragment shader
	
	public Shader(String filename){
		program = glCreateProgram();
		
		vs = glCreateShader(GL_VERTEX_SHADER); //Create a Vertex Shader
		
		glShaderSource(vs, readFile(filename+".vs")); //Get the shader.vs file
		glCompileShader(vs); //Compile the shader file
		if(glGetShaderi(vs, GL_COMPILE_STATUS) != 1){ //Check for errors
			System.err.println(glGetShaderInfoLog(vs));
			System.exit(1);
		}
		
		fs = glCreateShader(GL_FRAGMENT_SHADER); //Create a Fragment Shader
		
		glShaderSource(fs, readFile(filename+".fs")); //Get the shader.fs file
		glCompileShader(fs); //Comple the shader file
		if(glGetShaderi(fs, GL_COMPILE_STATUS) != 1){
			System.err.println(glGetShaderInfoLog(fs));
			System.exit(1);
		}
		
		glAttachShader(program, vs); //Attach/Link the shader to the program he will work with
		glAttachShader(program, fs); //Attach/Link the shader to the program he will work with
		
		glBindAttribLocation(program, 0, "vertices"); //Give a index and a name to the vertices
		glBindAttribLocation(program, 1, "textures"); //Give a index and a name to the textures
		
		glLinkProgram(program); //Send the program where are every shader and textures attached
		if(glGetProgrami(program, GL_LINK_STATUS) != 1){
			System.err.println(glGetProgramInfoLog(program));
			System.exit(1);
		}
		
		glValidateProgram(program); //Checks to see whether the executables contained in program can execute given the current OpenGL state
		if(glGetProgrami(program, GL_VALIDATE_STATUS) != 1){
			System.err.println(glGetProgramInfoLog(program));
			System.exit(1);
		}
		
	}
	
	//pass variable to the shader
	public void setUniform(String name, int value){
		int location = glGetUniformLocation(program, name); //get a location for uniform relative to the attacherShader
		if(location != -1){
			glUniform1i(location, value); //send the uniform to the location
		}
	}
	
	public void bind(){
		glUseProgram(program); //Installs//send the program object 
	}
	
	private String readFile(String filename){
		
		StringBuilder string = new StringBuilder();
		
		BufferedReader br;
		
		try{
			br = new BufferedReader(new FileReader(new File("../I'm Out/src/com/im/out/shaders/" + filename)));
			String line;
			while((line = br.readLine()) != null){
				string.append(line);
				string.append("\n");
			}
			
			br.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return string.toString();
	}
	
}
