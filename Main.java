public class Main {
	public static void main(String[] args) {
			int[] valoresIniciales = new int[] {15, 4, 1, 25, 50, 6, 7, 20, 5, 30};
			Tree miArbol = new Tree(valoresIniciales);

			miArbol.printPreOrder();
			System.out.println( miArbol.getMaxElem() );
			System.out.println( miArbol.getHeight() );
			System.out.println( miArbol.getLongestBranch() );
			System.out.println( miArbol.getElementAtLevel(2) );
			System.out.println( miArbol.getFrontera() );

			miArbol.add(23);
			miArbol.add(3);
			miArbol.delete(6);
			miArbol.delete(30);

			miArbol.printPreOrder();
			System.out.println( miArbol.getMaxElem() );
			System.out.println( miArbol.getHeight() );
			System.out.println( miArbol.getLongestBranch() );
			System.out.println( miArbol.getElementAtLevel(2) );
			System.out.println( miArbol.getFrontera() );

			miArbol.add(65);
			miArbol.delete(5);
			miArbol.delete(15);
			miArbol.add(55);

			miArbol.printPreOrder();
			System.out.println( miArbol.getMaxElem() );
			System.out.println( miArbol.getHeight() );
			System.out.println( miArbol.getLongestBranch() );
			System.out.println( miArbol.getElementAtLevel(2) );
			System.out.println( miArbol.getFrontera() );		
	}
}
