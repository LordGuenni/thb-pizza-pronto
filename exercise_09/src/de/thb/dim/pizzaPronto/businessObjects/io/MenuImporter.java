package de.thb.dim.pizzaPronto.businessObjects.io;

import java.io.File;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import de.thb.dim.pizzaPronto.valueObjects.DessertVO;
import de.thb.dim.pizzaPronto.valueObjects.DishVO;
import de.thb.dim.pizzaPronto.valueObjects.MenuVO;
import de.thb.dim.pizzaPronto.valueObjects.PastaVO;
import de.thb.dim.pizzaPronto.valueObjects.PizzaVO;

public class MenuImporter {

	public MenuImporter() {
	}

	public MenuVO readMenu(String fileName) throws IOException, FileNotFoundException, JSONException {
		List<DishVO> dishes;
		MenuVO menu = null;
		DishVO currentDish = null;
		dishes = new LinkedList<DishVO>();
		
		
		//Read complete File as character array
		File f = new File(fileName);
        FileReader fr = new FileReader(f);
        char [] iArray = new char[(int)f.length()+2];
        fr.read(iArray);        
		fr.close();
		
		//convert character array as JSONObject and work with it
		JSONObject jsonMenu = new JSONObject(new String(iArray));
		JSONArray jsonDishes = jsonMenu.getJSONArray("dishes");
		for (int i = 0; i < jsonDishes.length(); i++) {
			JSONObject dish = jsonDishes.getJSONObject(i);
			String type =dish.getString("type");
			if(type.equals("Pizza")) {
				currentDish = new PizzaVO();
				((PizzaVO) currentDish).setSize(dish.getInt("size"));
			}
			if(type.equals("Pasta")) {
				currentDish = new PastaVO();
				((PastaVO) currentDish).setTypeOfPasta(dish.getInt("typeOfPasta"));
			
			}
			if(type.equals("Dessert")) {
				currentDish = new DessertVO();
			}
			currentDish.setNumber(dish.getInt("nr"));
			currentDish.setName(dish.getString("name"));
			currentDish.setPrice(dish.getFloat("price"));
			List<Object> tmpIngredients = dish.getJSONArray("ingredients").toList();
			List<String> tmpIngredients2 = new ArrayList<String>();
			for (Object object : tmpIngredients) {
				tmpIngredients2.add((String) object);
			}
			if(tmpIngredients2.size()>0)
				currentDish.setIngredients(tmpIngredients2.toArray(new String[0]));
			dishes.add(currentDish);
			
		}
		
		menu = new MenuVO(new ArrayList<DishVO>(dishes));
		

		return menu;
	}

}
