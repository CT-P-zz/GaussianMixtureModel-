
public class Grafos{

	NoBD BDprim;
	
	Grafos(){
		BDprim=null;
	}
	
	protected void apresenta(){
		NoBD it;
        NoG it2;
		it=BDprim;
		while(it!=null){
			System.out.println("Nós adjacentes ao no " +it.BDval);
			it2=it.Gprox;
			while(it2!=null){
				it2.val.apresenta();
				//System.out.println("ìndice: " +it2.indice);
				//System.out.println("Estimativas (a1,a2,b1,b2,ka,ke):");
				//for(int j=0; j<it2.val.comp; j++)
					//System.out.printf("%s ;", it2.val.prim);
				 //System.out.println();
				
				it2=it2.gprox;
			};
			it=it.BDprox;
		}
	}
	
	protected Grafos grafoo(int n){
		NoBD novono=new NoBD(1);
		this.BDprim=novono;
		NoBD it=this.BDprim;
		int i=2;
		while(i!=n+1){
			NoBD novono1=new NoBD(i);
			it.BDprox=novono1;
			it=it.BDprox;
			i++;
		}
		return this;
		
	}
	
	protected void add_edge(int a, int b,Gaussianas v){
		NoBD it=BDprim;
		NoG novono =new NoG(b,v);
		while(it.BDval!=a){
			it=it.BDprox;
		}
		if(it.Gprox==null||it.Gprox.indice>b){
			novono.gprox=it.Gprox;
		    it.Gprox=novono;
		return;}
		else{
			NoG it2=it.Gprox;
			while(it2.gprox!=null && it2.gprox.indice<=b){
				it2=it2.gprox;
			}
			novono.gprox=it2.gprox;
			it2.gprox=novono;
			
		}
	}
	
	
	protected void remove_edge(int a, int b){
		NoBD it=BDprim;
		while(it.BDval!=a){
			it=it.BDprox;
	}
		if(it.Gprox==null||b<it.Gprox.indice){
			return;}
		if(it.Gprox.indice==b){
			it.Gprox=it.Gprox.gprox;
			return;}
		NoG it2=it.Gprox;
		while(it2.gprox!=null && it2.gprox.indice<b){
			it2=it2.gprox;
			}
		if(it2.gprox==null||it2.gprox.indice>b){
				return;}
		it2.gprox=it2.gprox.gprox;
				
			}
	//retorna a gaussiana do nó b do grafo
	protected Gaussianas gaussno (int a, int b){
		NoBD it=BDprim;
		while(it.BDval!=a){
			it=it.BDprox;
	}
		NoG it2=it.Gprox;
		while(it2!=null && it2.indice!=b){
			it2=it2.gprox;
		}
		return it2.val;	
	}
	
	protected void up_edge(int a,int b, Gaussianas v){
		NoBD it=BDprim;
		while(it.BDval!=a){
			it=it.BDprox;
	}
		NoG it2=it.Gprox;
		while(it2!=null && it2.indice!=b){
			it2=it2.gprox;
		}
		it2.val=v;
		
	}
}

