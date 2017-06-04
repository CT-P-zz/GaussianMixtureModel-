
import java.lang.Math;

public class Gaussianas{

	No prim;
	int comp;
	Gaussianas(){
		comp=0;
		prim =null;
	}
	//Each mixture is represented by a list of nodes (M nodes). Each node has the gaussian's number (as identification) 
	//and a vector containing its parameters 
	
	protected void apresenta(){
		No it;
		it=prim;
		System.out.println("O nº de gaussianas na mistura é " +comp);
		while(it!=null){
			System.out.println("Gaussiana "  +it.indice );
			System.out.println("Parâmetros (w,dpad,a1,a2,b1,b2):");
			for(int j=0; j<it.val.length; j++)
				System.out.printf("%s ;", it.val[j]);
			 System.out.println();
			
			it=it.prox;	
	}
	
	}
	
	//mix: Receives a matrix M (number of lines is the number of gaussians and the number of columns is the number of parameters)
	//and creates a list of Nodes with this information
	
	protected void mix(double[][]M){
	
		No novono=new No(1,M[0]);
		this.prim=novono;
		No it=this.prim;
		comp++;
		int i=2;
		int j=1;
		while(j<M.length){
			No novono1=new No(i,M[j]);
			it.prox=novono1;
			it=it.prox;
			j++;
			i++;
			comp++;
		}
		
	}
	
	protected void mix2(int i,double[]v){
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
	
		
			
	
	protected double prob(double[][]P){
    	double prod=1;
    	int i=0;
    	while(i<P.length){
    		double x=P[i][0];
    		double t=P[i][1];
    		double sum=0,g=0,u=0;
    		No it= prim;
    		while(it!=null){
    			u=it.val[2]*Math.exp(-it.val[4]*t)+ it.val[3]*Math.exp(-it.val[5]*t);
    			g=(1/((it.val[1])*Math.sqrt(2*Math.PI)))*(Math.exp(-(Math.pow(x-u, 2))/(2*Math.pow(it.val[1], 2))));
    			sum=sum+it.val[0]*g;
    			it=it.prox;
    	}
    		prod=prod*sum;
    		i++;
    	}
    	return prod;
    }

	//devolve uma matriz com todos os parâmetros  (colunas) de todas as gaussianas (linhas) da mistura
	protected double[][] theta(){
		double[][] aux=new double[comp][];
		int i=0;
		No it=prim;
		while(it!=null){
			aux[i]=it.val;
			it=it.prox;
			i++;
		}
		return aux;
	}
	//faz o update dos parâmetros da gaussiana i
	protected void update (int i, double[]v){
		No it=prim;
		while(it!=null && it.indice!=i){
			it=it.prox;
		}
		it.val=v;
	}
}

