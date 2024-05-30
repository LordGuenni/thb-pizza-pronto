package de.thb.dim.pizzaPronto.valueObjects;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MenuVO {
    
     private List<DishVO> dishes;

    private void initMenu(){

        dishes = new ArrayList<>();

        dishes.add(new PizzaVO(30, "Popeye", new String[] { "Schinken","Spinat", "Champignon", "Ei" }, 7.00f, 1));
        dishes.add(new PizzaVO(30, "Popeye", new String[] { "Schinken","Spinat", "Champignon", "Ei" }, 8.90f, 2));
        dishes.add(new PizzaVO(31, "Hawaii", new String[] { "Schinken","Ananas", "Curry" }, 5.80f, 1));
        dishes.add(new PizzaVO(31, "Hawaii", new String[] { "Schinken","Ananas", "Curry" }, 7.40f, 2));
        dishes.add(new PizzaVO(32, "Prima", new String[] { "Schinken","Salami", "Zwiebeln", "Ei" }, 7.00f, 1));
        dishes.add(new PizzaVO(32, "Prima", new String[] { "Schinken","Salami", "Zwiebeln", "Ei" }, 8.90f, 2));

        dishes.add(new PastaVO(11, "Napoli", new String[] { "Tomatensauce" },5.60f, 4));
        dishes.add(new PastaVO(11, "Napoli", new String[] { "Tomatensauce" },5.60f, 5));
        dishes.add(new PastaVO(11, "Napoli", new String[] { "Tomatensauce" },5.60f, 6));
        dishes.add(new PastaVO(12, "Bolognese",new String[] { "Hackfleischsauce" }, 6.40f, 4));
        dishes.add(new PastaVO(12, "Bolognese" ,new String[] { "Hackfleischsauce" }, 6.40f, 5));
        dishes.add(new PastaVO(12, "Bolognese",new String[] {"Hackfleischsauce"}, 6.40f, 6));
        dishes.add(new PastaVO(13, "alla Panna", new String[] { "Schinken","Sahne" }, 6.40f, 4));
        dishes.add(new PastaVO(13, "alla Panna", new String[] { "Schinken","Sahne" }, 6.40f, 5));
        dishes.add(new PastaVO(13, "alla Panna", new String[] { "Schinken","Sahne" }, 6.40f, 6));

        dishes.add(new DessertVO(21, "Hausgemachter Obstsalat", 2.30f));
        dishes.add(new DessertVO(22, "Hausgemachte Pannacotta", 2.60f));
        dishes.add(new DessertVO(23, "Hausgemachte Tiramisu", 2.80f));
    }

    public MenuVO() {
        initMenu();
    }

    public int getNumberOfDishes() {
        return dishes.size();
    }

    public List<DishVO> getDishes() {
        return dishes;
    }

    public void setDishes(List<DishVO> dishes) {
        this.dishes = dishes;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + dishes.size();
        result = prime * result + Arrays.hashCode(dishes.toArray());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        MenuVO other = (MenuVO) obj;
        if (getNumberOfDishes() != other.getNumberOfDishes())
            return false;
        if (!dishes.equals(other.dishes))
            return false;
        return true;
    }

    @Override
    public String toString() {
        int i;
        StringBuffer sb = new StringBuffer();
        
        DecimalFormat dFormat = new DecimalFormat (".00"); //Format the price
        
        sb.append ("MENU PIZZA PRONTO\n\n");

        // Pizzas
        sb.append ("Prima special pizzas: \n 1 normal (Diameter approx. 26 cm) and \n 2 grande (Diameter approx. 32 cm)\n");
        i=0;
        do {
            if (dishes.get(i) != null) {
                sb.append (dishes.get(i).getNumber() + "\t");
                sb.append (dishes.get(i).getName() + "\t");
                sb.append (dishes.get(i).ingredientsToString());
                sb.append ("\n\tPrice:\t\t\t" + dFormat.format (dishes.get(i).getPrice()) + " Euro");
                if (dishes.get(i).getNumber() == dishes.get(i+1).getNumber()) {
                    sb.append ("\n\tPrice:\t\t\t" + dFormat.format (dishes.get(i+1).getPrice()) + " Euro");
                    sb.append ("\n") ;
                    i+=2;
                } else
                i++;
            }
        } while (i < dishes.size() && dishes.get(i) instanceof PizzaVO ); 
        return sb.toString();
    }


    public DishVO getDish(int i) {
        return dishes.get(i);
    }     

    

    
}
