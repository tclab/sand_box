
public class Arreglo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int i[];
		i=new int [20];
		
		for(int pos=i.length-1; pos>=0;pos--){
			i[pos]=pos;
		}
		
		for(int pos=0; pos<i.length;pos++){
			System.out.println(i[pos]);
		}
		//System.out.println(i.getClass());

	}

}
