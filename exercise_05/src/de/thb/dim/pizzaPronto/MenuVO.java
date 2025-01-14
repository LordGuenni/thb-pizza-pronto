package de.thb.dim.pizzaPronto;

import java.text.DecimalFormat;
import java.util.Arrays;

public class MenuVO {
    
    private static final int NUMBER_OF_DISHES = 18;
    private DishVO[] dishes;

    private void initMenu(){

        dishes = new DishVO[NUMBER_OF_DISHES];

        dishes[0] = new PizzaVO(30, "Popeye", new String[] { "Schinken","Spinat", "Champignon", "Ei" }, 7.00f, 1);
        dishes[1] = new PizzaVO(30, "Popeye", new String[] { "Schinken","Spinat", "Champignon", "Ei" }, 8.90f, 2);
        dishes[2] = new PizzaVO(31, "Hawaii", new String[] { "Schinken","Ananas", "Curry" }, 5.80f, 1);
        dishes[3] = new PizzaVO(31, "Hawaii", new String[] { "Schinken","Ananas", "Curry" }, 7.40f, 2);
        dishes[4] = new PizzaVO(32, "Prima", new String[] { "Schinken","Salami", "Zwiebeln", "Ei" }, 7.00f, 1);
        dishes[5] = new PizzaVO(32, "Prima", new String[] { "Schinken","Salami", "Zwiebeln", "Ei" }, 8.90f, 2);

        dishes[6] = new PastaVO(11, "Napoli", new String[] { "Tomatensauce" },5.60f, 4);
        dishes[7] = new PastaVO(11, "Napoli", new String[] { "Tomatensauce" },5.60f, 5);
        dishes[8] = new PastaVO(11, "Napoli", new String[] { "Tomatensauce" },5.60f, 6);
        dishes[9] = new PastaVO(12, "Bolognese",new String[] { "Hackfleischsauce" }, 6.40f, 4);
        dishes[10] = new PastaVO(12, "Bolognese" ,new String[] { "Hackfleischsauce" }, 6.40f, 5);
        dishes[11] = new PastaVO(12, "Bolognese",new String[] {"Hackfleischsauce"}, 6.40f, 6);
        dishes[12] = new PastaVO(13, "alla Panna", new String[] { "Schinken","Sahne" }, 6.40f, 4);
        dishes[13] = new PastaVO(13, "alla Panna", new String[] { "Schinken","Sahne" }, 6.40f, 5);
        dishes[14] = new PastaVO(13, "alla Panna", new String[] { "Schinken","Sahne" }, 6.40f, 6);

        dishes[15] = new DessertVO(21, "Hausgemachter Obstsalat", 2.30f);
        dishes[16] = new DessertVO(22, "Hausgemachte Pannacotta", 2.60f);
        dishes[17] = new DessertVO(23, "Hausgemachte Tiramisu", 2.80f);
    }

    public MenuVO() {
        initMenu();
    }

    public int getNumberOfDishes() {
        return NUMBER_OF_DISHES;
    }

    public DishVO[] getDishes() {
        return dishes;
    }

    public void setDishes(DishVO[] dishes) {
        this.dishes = dishes;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + NUMBER_OF_DISHES;
        result = prime * result + Arrays.hashCode(dishes);
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
        if (getNumberOfDishes() != MenuVO.NUMBER_OF_DISHES)
            return false;
        if (!Arrays.equals(dishes, other.dishes))
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
            if (dishes[i] != null) {
                sb.append (dishes[i].getNumber() + "\t");
                sb.append (dishes[i].getName() + "\t");
                sb.append (dishes[i].ingredientsToString());
                sb.append ("\n\tPrice:\t\t\t" + dFormat.format (dishes[i].getPrice()) + " Euro");
                if (dishes[i].getNumber() == dishes[i+1].getNumber()) {
                    sb.append ("\n\tPrice:\t\t\t" + dFormat.format (dishes[i+1].getPrice()) + " Euro");
                    sb.append ("\n") ;
                    i+=2;
                } else
                i++;
            }
        } while (i < dishes.length && dishes[i] instanceof PizzaVO ); 
        return sb.toString();
    }


    public DishVO getDish(int i) {
        return dishes[i];
    }   

    

    
}
