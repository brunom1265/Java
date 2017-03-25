package com.im.out.graphics;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;

public class Model {
	
	private int draw_count;
	private int v_id;
	private int t_id;
	
	private int i_id;
	
	public Model(float[] vertices, float[] tex_coords, int[] indices){
		draw_count = indices.length;
		
		
		v_id = glGenBuffers(); //Create Buffers that i need
		glBindBuffer(GL_ARRAY_BUFFER, v_id); //Bind the buffer VBO with my id
		glBufferData(GL_ARRAY_BUFFER, createBuffer(vertices), GL_STATIC_DRAW); //send data to array buffer, what buffer, and to static place
		
		t_id = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, t_id);
		glBufferData(GL_ARRAY_BUFFER, createBuffer(tex_coords), GL_STATIC_DRAW);
				
		i_id = glGenBuffers(); //create Buffer for id
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, i_id); //bind the buffer with element array buffer
		
		IntBuffer buffer = BufferUtils.createIntBuffer(indices.length); //Create a intBufer
		buffer.put(indices); //put the indices array inside the buffer
		buffer.flip(); //flip the array
		
		glBufferData(GL_ELEMENT_ARRAY_BUFFER, buffer, GL_STATIC_DRAW); // send to the element array the buffer and he will be static
		
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0); //unbind the element array buffer
		glBindBuffer(GL_ARRAY_BUFFER, 0); //unbid the array buffer

	}
	
	public void render(){
		glEnableVertexAttribArray(0); //enables the generic vertex attribute array specified by index. 
		glEnableVertexAttribArray(1); //enables the generic vertex attribute array specified by index. 
		
		glBindBuffer(GL_ARRAY_BUFFER, v_id);
		glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0); //Give the vertex a pointer that we can call
		
		glBindBuffer(GL_ARRAY_BUFFER, t_id);
		glVertexAttribPointer(1, 2, GL_FLOAT, false, 0, 0);
		
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, i_id);
		glDrawElements(GL_TRIANGLES,draw_count, GL_UNSIGNED_INT, 0); //Draw Traingles
		
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
		
		glDisableVertexAttribArray(0);
		glDisableVertexAttribArray(1);
	}
	
	private FloatBuffer createBuffer(float[] data){
		FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
		
		buffer.put(data);
		buffer.flip();
		
		return buffer;
	}
}
