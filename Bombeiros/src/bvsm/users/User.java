package bvsm.users;

public class User {
	
	private static String name, surname, username, password;
	private static int age, type;
	
	public void setName(String name){
		User.name = name;
	}
	
	public void setSurname(String surname){
		User.surname = surname;
	}
	
	public void setAge(int age){
		User.age = age;
	}
	
	public void setType(int type){
		User.type = type;
	}
	
	public String getName(){
		return name;
	}
	
	public String getSurname(){
		return surname;
	}
	
	public int getAge(){
		return age;
	}
	
	public int getType(){
		return type;
	}
	
}
