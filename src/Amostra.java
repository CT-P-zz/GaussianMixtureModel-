
public class Amostra{

	No prim;
	int comp;
	Amostra(){
		comp=0;
		prim=null;
	}

	//função apenas para experimentação de resultados
	protected void apresenta(){
		No it;
		int i=0;
		it=prim;
		System.out.println("o comprimento da amostra é " +length());
		while(it!=null){
			System.out.println("vector na posição "  +i );
			System.out.println("índice: " + it.indice);
			System.out.println("tempo: " + it.val[0]);
			System.out.println("valor: " + it.val[1]);
			it=it.prox;
			i++;
		}
	}
	
	//adiciona ordenadamente por índice
	protected void add(int i,double[] v){
		No novono=new No(i,v);
		if(prim==null||(prim.indice > novono.indice)){
			novono.prox=prim;
			prim=novono;
			comp++;
			return;
		}
		No it=prim;
		while(it.prox!= null && it.prox.indice <= novono.indice){
			it=it.prox;
		}
		novono.prox=it.prox;
		it.prox=novono;
		comp++;
	}
	
	protected int length(){
		return comp;
	}
	
	
	protected double[] element(int p){
		No it = prim;
		int i=0;
		while(i!=p){
			i++;
			it=it.prox;
		}
		double[]aux=new double[3];
		aux[0]=it.indice;
		aux[1]=it.val[0];
		aux[2]=it.val[1];
		return aux;	
		}
	
	//devolve uma matriz com os tempos (primeira coluna) e concentrações (segunda coluna) de um indivíduo da amostra
	protected double[][] indice(int ind){
		No it=prim;
		while(it.indice < ind && it.prox!=null){
			it=it.prox;
			}
		No it2=it;
		int i=0;
		while(it2.indice==ind && it2.prox!=null){
				it2=it2.prox;
				i++;
				}
		if (it2.indice == ind ){i=i+1;}
		
		double[][] aux=new double [i][2];
		int j=0;
		while(it.indice==ind && it.prox!= null){
			aux[j][0]=it.val[0];
			aux[j][1]=it.val[1];
			j++;
			it=it.prox;
			}
		if (it.indice == ind){
			aux[i-1][0]=it.val[0];
			aux[i-1][1]=it.val[1];
		}
		return aux;
	}

	
	protected Amostra join (Amostra a){
		No it=a.prim;
		while(it!=null){
			this.add(it.indice,it.val);
			it=it.prox;
		}
		return this;
	}
	
	//Devolve o maior índice do individuo presente na amostra
	protected int kapa(){
		No it = prim;
		int k = 0;
		while(it.prox!=null){
			it=it.prox;
		}
		k = it.indice;
		return k;
	}
	
	
}
