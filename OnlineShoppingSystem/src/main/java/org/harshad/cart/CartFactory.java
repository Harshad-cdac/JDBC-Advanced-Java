package org.harshad.cart;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.harshad.dao.Cart;

public class CartFactory {

//	used the reflection to create the object of class which will be defined in future
	public static Cart getInstance(String cartClass) {
		try {
			Class refCart=Class.forName(cartClass);
			Constructor ctor = refCart.getConstructor(null);
			return (Cart) ctor.newInstance(null);
		} catch (ClassNotFoundException | SecurityException | InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
