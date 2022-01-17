import java.util.ArrayList;
public class Tree {
	private Tree raiz;
	private Integer valor;
	private Tree izquierda;
	private Tree derecha;
	public Tree(Integer valor) {
		this.valor = valor;
		this.izquierda = null;
		this.derecha = null;
		this.raiz = this;
	}
	public Tree(int[] arreglo) {
		// O(n*h),ya que solo ejecuta un metodo y ese tiene esa complejidad.
		addArray(arreglo);
	}
	public Tree() {
		//O(1)
		this.valor = null;
		this.izquierda = null;
		this.derecha = null;
		this.raiz = this;
	}
	private void addArray(int[]arreglo) {
		// O(n*h) donde n es la cantidad de elementos del arreglo y h es la altura del arbol.
		for (int i = 0; i < arreglo.length; i++) {
			if (i == 0) {
				this.valor = arreglo[0];
			} else {
				this.add(arreglo[i]);
			}
		}
	}
	public void add(int newValue) {
		//O(h) donde h es la altura del arbol.

		//si el valor que me pasan por parametro para agregar es menor a mi valor 
		//y si mi izquierda es null, mi izquierda pasa apuntar al tree que instancio con ese valor.
		//si mi izquierda no es null, llama agregar con ese valor pasado por parametro.
		//si el valor que me pasan por parametro es mayor al mio y mi derecha es nula, mi derecha pasa apuntar al tree instanciado con ese valor,
		//si mi derecha no es null llamo a que mi derecha ejecute add con ese valor.		
		if(isEmpty()) {
			this.valor=newValue;
		}
		else {
			if (newValue < this.valor) {
					if (this.izquierda == null)
						this.izquierda = new Tree(newValue);
					else
						this.izquierda.add(newValue);
				} else {
					if (this.derecha == null)
						this.derecha = new Tree(newValue);
					else
						this.derecha.add(newValue);
				}
		}
	}
	public Integer getValor() {
		//O(1) 
		return this.valor;		
	}
	
	public Integer getHeight() {
		//O(n), donde n es la cantidad de subarboles. Tiene que pasar por todos.
		//cuenta la cantidad de arcos entre un sub arbol y otro.
		//si llega a una hoja retorna 0
		//sino se suma 1
		//compara cual es el mayor por la cantidad de elementos que recorrio. 
		if(!this.isEmpty()) {
			if (this.izquierda == null && this.derecha == null) { 
				return 0; //
			} else {
				// Si tengo al menos un hijo
				int alturaIzq = 0;
				int alturaDer = 0;
				if (this.izquierda != null)
					alturaIzq = 1 + this.izquierda.getHeight();
				if (this.derecha != null)
					alturaDer = 1 + this.derecha.getHeight();
				int mayor = Math.max(alturaIzq, alturaDer);
				return mayor;
			}	
		}
		return 0;
	}
	public Integer getRoot() {
		// O(1)
		// chequea que no sea null, luego si mi atributo raiz no es null devuelvo su valor,
		//sino devuelvo null.
			if (this.raiz != null) {
				return this.raiz.getValor();
			} else {
				return null;
			}
	}
	public boolean esHoja() {
		// O(1)
		//chequeo que mi valor no sea null, luego verifico si tengo izquierda y derecha devuelvo true, sino falso.
		//este metodo sirve para verificar si sos una hoja o no.
			if ((this.izquierda == null) && (this.derecha == null)) {
				return true;
			}
		return false;
	}
	public boolean isEmpty() {
		//O(1) chequeo que mi valor sea null, si lo es retorno verdadero sino falso
		if(this.valor==null) {
			return true;
		}
		return false;	
	}
	public boolean hasElem(Integer numeroAEncontrar) {
		//O(h) es la altura del arbol.
		//en el peor de los casos recorre la mitad del arbol y no lo encuentra.
		//si mi valor es el valor a encontrar y no soy null devuelvo true,
		//sino si mi vlaor es mayor al valor y mi izquierda no es nula retorno mi izquierda llamando al metodo hasElem con el numero a encontrar.
		//si no entro por ser mayor pregunta si es menor al valor a encontrar y si tiene izquierda,
		//si es asi retorna su derecha llamando al haselem con el valor a encontrar.
		//si no lo encontro retorno falso.
			if (this.valor == numeroAEncontrar) {
				return true;
			} else {
				if ((this.valor > numeroAEncontrar) && (this.izquierda != null)) {
					return this.izquierda.hasElem(numeroAEncontrar);
				} else if ((this.valor < numeroAEncontrar) && (this.izquierda != null)) {
					return this.derecha.hasElem(numeroAEncontrar);
				}
			}
		
		return false;

	}
	public Integer getMaxElem() {
		//O(h) donde h es la altura del arbol.
		//En el peor caso va a encontrar al arbol mas grande en la altura maxima.
		//si mi derecha es null, retorno mi valor, 
		//sino si mi derecha no es null mi derecha vuelve a ejecutar getMaxElem()
		//al final retorno mi valor.
			if (this.derecha == null) {
				return this.getValor();
			} else {
				if (this.derecha != null) {
					return this.derecha.getMaxElem();
				}
			}
			return this.getValor();
	}
	public ArrayList<Integer> getElementAtLevel(Integer numero) {
		//O(n),donde n es la totalidad de subarboles hasta llegar a la altura pasada por parametro.
		//En el peor de los casos recorre todo el arbol buscando los elementos ubicados en la altura maxima.
		
		//En este caso 	
		//chequea que no es nullo su valor, crea un arraylist y contador en 0, pregunta si el numero del lvl a encontrar es igual al contador y no soy null, si es asi agrego mi valor a la lista y la retorno.
		//sino verifico si contador sea igual al numero -1  en este caso chequeo como padre, si mi izquierda no es null agrego el valor de mi izquierda a la lista y la retorno al igual que mi derecha si no es null.
		//luego retorno la lista,sino mi lista llama agregar todas las listas de resultado de mi izquierda llamando el metodo getElementAtLevel pasandole el numero del lvl a encontrar menos 1 y tambien del lado derecho.
		//luego retorno la lista, si mi valor es nullo, devuelvo nullo.
		ArrayList<Integer> lista = new ArrayList();
		if(this!=null) {
			if (0 == numero) {
				lista.add(this.valor);
				return lista;
			} 
			if(this.izquierda!=null) {
				lista.addAll(this.izquierda.getElementAtLevel(numero - 1));
			}
			if(this.derecha!=null) {
				lista.addAll(this.derecha.getElementAtLevel(numero - 1));
			}
		}
		return lista;
	}
	public void printPosOrder() {
		// O(n) donde n es la cantidad de subarboles del arbol.
		// si mi valor no es null, luego si mi izquierda no es null mi izquierda llama a printposorder,
		//luego si mi derecha no es null llama a printposorder
		//luego imprime su valor
			if (this.izquierda != null) {
				this.izquierda.printPosOrder();
			}
			if (this.derecha != null) {
				this.derecha.printPosOrder();
			}
			System.out.print("(" + this.valor + ")");
		
	}
	public void printOrder() {
		// O(n) donde n es la cantidad de subarboles del arbol.
		//chequeo que mi valor no sea null.
		// si mi izquierda no es null, mi izquierda llama a printorder, luego imprime su valor y
		//luego mi derecha si no es null llama a printorder.
			if (this.izquierda != null) {
				this.izquierda.printOrder();
			}
			System.out.print("(" + this.valor + ")");
			if (this.derecha != null) {
				this.derecha.printOrder();
			}
	}
	public void printPreOrder() {
		// O(n) donde n es la cantidad de subarboles del arbol.
		// si no soy null, imprimo mi valor, si no tengo izquierda imprimo una distincion,
		//si no tengo derecha imprimo una distincion
		//si tengo izquierda imprimo mi valor y vuelvo a llamarme desde mi izquierda
		//si tengo derecha imprimo mi valor y vuelvo a llamarme desde mi derecha
			System.out.print("(" + this.valor + ")");
			if ((this.izquierda != null)) {
				this.izquierda.printPreOrder();
			}
			else {
				System.out.print("-");
			}
			if (this.derecha != null) {
				this.derecha.printPreOrder();
			}
			else {
				System.out.print("-");
			}
	}
	public ArrayList<Integer> getFrontera() {
		// O(n), donde n es la cantidad de subarboles a recorrer del arbol.
		// en el peor de los casos va a las alturas maximas a buscar todas las hojas por lo que recorre todo el arbol.
		// en este metodo devuelvo todas las hojas.
		// chequeo que no valga null,  creo la arraylist, 
		//pregunto si soy hoja me agrego sino llamo a mi izquierda si no es null la lista agrega todas las listas que devuelva mi izquierda al llamar el metodo getFrontera
		// lo mismo para la derecha,sino es null, la arraylist agrega todas las listas de la derecha llamando al metodo getfrontera(este metodo).
		//luego retorna la lista.
			ArrayList<Integer> lista = new ArrayList();
			if (esHoja()) {
				lista.add(this.valor);
			} else {
				if (this.izquierda != null) {
					lista.addAll(this.izquierda.getFrontera());
				}
				if (this.derecha != null) {
					lista.addAll(this.derecha.getFrontera());
				}
			}
			return lista;
	
	}
	public void setIzquierda(Tree izquierda) {
		//O(1)
		this.izquierda=izquierda;
	}
	public void setDerecha(Tree derecha) {
		//O(1)
		this.derecha=derecha;
	}
	public ArrayList<Integer> getLongestBranch() {
		// O(n), donde n es la cantidad de subarboles a recorrer del arbol.
		// en el peor de los casos recorre todo el arbol comparando la ramas mas larga.
		//si no valgo null, creo 2 arraylist, luego cada arraylist agrega el valor de donde esta parado, 
		//luego si mi izquierda no es null  la primer arraylist agrega mi izquierda llama al metodo longestBranch 
		//mi derecha si no es null la segunda arraylist hace lo mismo.
		//luego se comparan las longitudes dependiendo su tamaño y si la primera es mas grande la retorna y sino retorna la segunda.
		
			ArrayList<Integer> lista = new ArrayList();
			ArrayList<Integer> lista2 = new ArrayList();
			lista.add(this.valor);
			lista2.add(this.valor);
			if (this.izquierda != null) {
				lista.addAll(this.izquierda.getLongestBranch());
			}
			if (this.derecha != null) {
				lista2.addAll(this.derecha.getLongestBranch());
			}
			if (lista.size() >= lista2.size()) {
				return lista;
			} else {
				return lista2;
			}
	
	}
	public Integer getIzquierda() {
		//O(1)
		//devuelve el valor izquierda si no es null.
		if (this.izquierda != null) {
			return this.izquierda.getValor();
		} else {
			return null;
		}
	}
	public Integer getDerecha() {
		//O(1)
		//devuelve el valor derecha sino es null
		if (this.derecha != null) {
			return this.derecha.getValor();
		} else {
			return null;
		}
	}
	private Tree GetSubArbolMasIzquierdo() {
		//O(h)donde h es la altura del arbol.
		//si tengo izquierda retorno mi izquierda llamando este mismo metodo(getSubArbolMasIzquierdo)
		//sino retorno mi derecha llamando a este metodo.
		//si no tengo izquierda ni derecha me retorno.
		if(this.izquierda!=null) {
			return this.izquierda.GetSubArbolMasIzquierdo();
		}
		else if(this.derecha!=null) {
			return this.derecha.GetSubArbolMasIzquierdo();
		}
		return this;
	}	
	
	public boolean delete(Integer numero) {
		//O(h)donde h es la altura del arbol.
		//En el peor de los casos recorre la mitad arbol.
		
		//verifica que su valor no sea null, sino retorna falso.
		//sino va haciendo una serie de condiciones si es el valor donde esta parado
		//y no tiene hijos el valor se vuelve null y retorna true,
		//o tiene 1 hijo ya sea iz o der dependiendo cual sea guardo los punteros y me vuelvo el valor de mi hijo, seteo izquierda derecha con los valores guardados y retorno true,
		//o 2(en este caso vuelvo a llamar a la funcion),busco el hijo mas izquierdo de mi derecha llamo a delete me vuelvo el valor de mi reemplazo y retorno true ,
		//si mi hijo es el valor y no tiene hijos lo descapunto o lo vuelvo null, 
		//y si no soy el valor ,si soy mayor o menor retorno lo que me devuelva delete.
		
		if(this.valor==null) {
			return false;
		}
		else if(esHoja()&&(this.valor==numero)) {
			this.valor=null;
			return true;
		}
		else if((this.izquierda!=null)&&(this.derecha==null)&&(this.valor==numero)) {
			Tree izq=this.izquierda.izquierda;
			Tree der=this.izquierda.derecha;
			this.valor=this.izquierda.valor;
			this.setIzquierda(izq);
			this.setDerecha(der);
			return true; 
		}
		else if((this.derecha!=null)&&(this.izquierda==null)&&(this.valor==numero)) {		
			Tree izq=this.derecha.izquierda;
			Tree der=this.derecha.derecha;
			this.valor=this.derecha.valor;
			this.setIzquierda(izq);
			this.setDerecha(der);
			return true;				 
		}
		else if((this.izquierda!=null)&&(this.derecha!=null)&&(this.valor==numero)) {
			Tree aux=this.derecha.GetSubArbolMasIzquierdo();
			this.delete(aux.getValor());
			this.valor=aux.getValor();	
			return true;
		} 
		else if((this.izquierda!=null)&&(this.izquierda.derecha==null)&&(this.izquierda.izquierda==null)&&(this.izquierda.valor==numero)) {
				this.izquierda=null;
				return true;
		}
		else if((this.derecha!=null)&&(this.derecha.derecha==null)&&(this.derecha.izquierda==null)&&(this.derecha.valor == numero)) {
				this.derecha = null;
				return true;
		} 
		else {
			if((this.valor>numero)&&(this.izquierda!=null)&&(this.valor!=numero)) {
				return this.izquierda.delete(numero);	
			}
			else if((this.valor<numero)&&(this.derecha!=null)&&(this.valor!=numero)) {
				return this.derecha.delete(numero);
			}
		}
	return false;
	}
}