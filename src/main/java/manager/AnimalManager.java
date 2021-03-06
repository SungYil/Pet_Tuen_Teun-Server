package manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import DB.AnimalDAO;
import model.Animal;

import model.Animal;
@Service
public class AnimalManager {
	/**
	 * key1=member.id key2=name
	 */
	private HashMap<String,HashMap<String,Animal>> animals;
	private AnimalDAO animalDAO;
	
	public AnimalManager() {
		super();
		
		animalDAO = new AnimalDAO();
		animals = animalDAO.load();
	}
	public AnimalManager(HashMap<String, HashMap<String, Animal>> animals) {
		super();
		this.animals = animals;
	}
	public HashMap<String, HashMap<String, Animal>> getAnimals() {
		return animals;
	}
	public void setAnimals(HashMap<String, HashMap<String, Animal>> animals) {
		this.animals = animals;
	}
	
	public Animal searchAnimal(String id, String name) {
		return this.animals.get(id).get(name);
	}
	public ArrayList<Animal> searchAnimalByID(String id){
		if(this.animals.get(id)==null)return null;
		HashMap<String,Animal> list=this.animals.get(id);
		ArrayList<Animal> animal=new ArrayList<Animal>();
		
		Iterator<String> keys=this.animals.get(id).keySet().iterator();
		while(keys.hasNext()) {
			animal.add(list.get(keys.next()));
		}
		
		return animal;
	}
	public byte modifyAnimal(String id,String pastName,Animal animal) {
		if(searchAnimal(id, pastName)==null)return 0;
		this.animals.get(id).remove(pastName);
		this.animals.get(id).put(animal.getName(), animal);
		return 1;
	}
	public byte deleteAnimal(String id, String name) {
		if(searchAnimal(id, name)==null)return 0;
		this.animals.get(id).remove(name);
		return 1;
	}
	public  byte deleteAnimal(String id) {
		if(this.animals.get(id)==null)return 0;
		this.animals.remove(id);
		return 1;
	}
	public byte addAnimal(String id,Animal animal) {
		if(this.animals.get(id)==null) {
			this.animals.put(id,new HashMap<String,Animal>());
		}
		if(searchAnimal(id, animal.getName())!=null)return 0;
		
		if(this.animals.get(id)==null) {
			this.animals.put(id, new HashMap<String,Animal>());
		}
		this.animals.get(id).put(animal.getName(), animal);
		return 1;
	}
}
