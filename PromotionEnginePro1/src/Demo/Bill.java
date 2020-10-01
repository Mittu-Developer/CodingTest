package Demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
class Product{
char id;
int price;
public Product(char id, int price) {
	this.id = id;
	this.price = price;
}
public char getId() {
	return id;
}
public void setId(char id) {
	this.id = id;
}
public int getPrice() {
	return price;
}
public void setPrice(int price) {
	this.price = price;
}

}
class Promotions{
	Map<Character, Integer> cart;
	List<Product> products;
	
	public Promotions(Map<Character, Integer> cart, List<Product> products) {
		this.cart = cart;
		this.products = products;
	}
	int total=0;

	public void getTotal() {
		boolean applied=false;
		for(Character item:cart.keySet()) {
			char name=item;
			int price=0;
			int quantity=cart.get(item);
			for(int i=0;i<products.size();i++) {
				Product prod=products.get(i);
				if(prod.getId()==name) {
					price=prod.getPrice();
				}
			}
			if(name=='A') {
				if(quantity>=3) {
				//Method to apply Promotion 1
				Promotion1(quantity,price);
				}
				else {
					NoDiscount(quantity,price);
				}
			}
			if(name=='B') {
				NoDiscount(quantity,price);
			}
			if(name=='C') {
				//To apply Promotion 2
					if(cart.containsKey('D') && cart.get('D')!=0) {
						if(applied==false) {
						if(cart.get('C')>cart.get('D')) {
							int diff=cart.get('C')-cart.get('D');
							total=total+(diff*price)+(cart.get('D')*30);
							applied=true;
							
						}
						else if(cart.get('C')== cart.get('D')) {
							total=total+(cart.get('C')*30);
							applied=true;
						}
						}
					}
					else {
						NoDiscount(quantity,price);
					}
			}
			if(name=='D') {
				//To apply Promotion 2
					if(cart.containsKey('C')&& cart.get('C')!=0 ) {
						if(applied==false) {
					if(cart.get('D')>cart.get('C')) {
						int diff=cart.get('D')-cart.get('C');
						total=total+(diff*price)+(cart.get('C')*30);
						applied=true;
						
					}
					else if(cart.get('D')== cart.get('C')) {
						total=total+(cart.get('D')*30);
						applied=true;
					}
					}
					}
				else {
					NoDiscount(quantity,price);
				}
			}
		}
		System.out.println("Total bill for the cart is: "+total);
	}
	public void Promotion1(int itemCount,int price) {
			int discPack=itemCount/3;
			int extra=itemCount%3;
			if(discPack!=0) {
				total=total+(discPack*130);
			}
			if(extra!=0) {
				total=total+(extra*price);
			}
	}

	public void NoDiscount(int itemCount,int price) {
	total=total+(itemCount*price);
	}
}
public class Bill {
	public static void main(String[] args) {
		List<Product> proList=new ArrayList<>();
		proList.add(new Product('A',50));
		proList.add(new Product('B',30));
		proList.add(new Product('C',20));
		proList.add(new Product('D',15));
		HashMap<Character,Integer> custCart=new HashMap<Character,Integer>();
		//Cart contains 3A,2B,0C,1D
		custCart.put('A',3);
		custCart.put('B',2);
		custCart.put('C',0);
		custCart.put('D',1);
		
		//Cart contains 3A,2B,1C,1D
		/*custCart.put('A',3);
		custCart.put('B',2);
		custCart.put('C',1);
		custCart.put('D',1);*/
		
		//Cart contains 3A,2B
		/*custCart.put('A',3);
		custCart.put('B',2);*/
		
		//Cart contains 4A,2B,1C
		/*custCart.put('A',4);
		custCart.put('B',2);
		custCart.put('C',1);*/
		
		//Cart contains 1C,1D
		/*custCart.put('C',1);
		custCart.put('D',1);*/
		
		//Cart contains 1B,4D,3C
		/*custCart.put('B',1);
		custCart.put('D',4);
		custCart.put('C',3);*/
		
		//Cart contains 4C,1A,2D
		/*custCart.put('C',4);
		custCart.put('A',1);
		custCart.put('D',2);*/		
		Promotions promo=new Promotions(custCart,proList);
		promo.getTotal();
	}

}
