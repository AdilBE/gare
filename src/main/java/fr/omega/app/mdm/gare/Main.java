package fr.omega.app.mdm.gare;

import java.io.IOException;
import java.util.UUID;

public class Main {
	public static void main(String[] args) throws IOException{
		System.out.println("I am in GARE");
		
		final String uuid = UUID.randomUUID().toString();
		
		System.out.println(uuid);
		
		
	}
}
