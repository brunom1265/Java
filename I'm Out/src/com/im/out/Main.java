package com.im.out;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;

import com.im.out.graphics.Model;
import com.im.out.graphics.Shader;
import com.im.out.graphics.Texture;
import com.im.out.org.joml.Matrix4f;

public class Main {

	private static int width = 600;
	private static int height = 480;

	private static long window;
	private static Model model;
	public static Texture tex;
	public static Shader shader;
	public static Matrix4f projection;
	
	public static void init() {

		if (!glfwInit()) {
			throw new IllegalStateException("Fale to initialise GLFW!");
		}

		glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);

		window = glfwCreateWindow(width, height, "I'm Out", 0, 0);
		if (window == 0) {
			throw new IllegalStateException("Failed to create window");
		}

		GLFWVidMode videoMode = glfwGetVideoMode(glfwGetPrimaryMonitor());

		glfwSetWindowPos(window, (videoMode.width() - width) / 2, (videoMode.height() - height) / 2);

		glfwShowWindow(window);

		glfwMakeContextCurrent(window);

		GL.createCapabilities();
		
		glEnable(GL_TEXTURE_2D);		

		
	}
	
	public static void run(){
		
		//x = left = -1 right = 1
		//y = up = +1 down = -1
		
		float[] vertices = new float[]{
				-0.5f,  0.5f, 0,
				 0.5f,  0.5f, 0,
				 0.5f, -0.5f, 0,
				-0.5f, -0.5f, 0,
		};
		
		float[] texture = new float[]{
				0, 0,
				1, 0,
				1, 1,
				0, 1,
		};
		
		int[] indices = new int[]{
			0,1,2,
			2,3,0
		};
		
		model = new Model(vertices, texture, indices);
		
		shader = new Shader("shader");
		
		tex = new Texture("../I'm Out/src/com/im/out/res/images/barack.jpg");
		
		projection = new Matrix4f().ortho2D(-640/2, 640/2, 480/2, -480/2);
		
		while(!glfwWindowShouldClose(window)){

			glfwPollEvents();
					
			update();

			render();
		}
		stop();

	}

	public static void update() {
		if(glfwGetKey(window, GLFW_KEY_ESCAPE) == GL_TRUE){
			glfwSetWindowShouldClose(window, true);
		}
	}

	private static void render() {
		
		glClear(GL_COLOR_BUFFER_BIT);
		
		//tex.bind();
		shader.bind();
		shader.setUniform("sampler", 0);
		tex.bind(0);
		model.render();
		
		glfwSwapBuffers(window);
		
	}

	public static void stop() {

	}
	
	public static void main(String[] args) {
		init();
		run();
	}

}
