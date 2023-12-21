package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.swing.filechooser.FileSystemView;

import model.Korisnik;

public class FileLoader {
	public static void ucitajFajlove() {
		try {
			FileInputStream fileInput = new FileInputStream(FileSystemView.getFileSystemView().getDefaultDirectory().getPath()+"/file.ser");
			@SuppressWarnings("resource")
			ObjectInputStream objectInput = new ObjectInputStream(fileInput);
			while(true) {
				Database.getInstance().getKorisnici().add((Korisnik)objectInput.readObject());
			}
			
		} catch (FileNotFoundException e) {
		} catch (IOException e) {
		} catch (ClassNotFoundException e) {
		}
	}
}
