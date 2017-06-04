
public class Newton{
	
	protected static double funcaoHB1 (int j, double[][] thetas, Amostra a, int k,double b1){
		
		int i=0;
		double sum1=0, sum2=0;
		while(i<k){
			int l=0;
			double[][] q=a.indice(i);
			double x=FuncoesEM.funcaoX(j,thetas,q);
			while(l<q.length){
				double t=q[l][0];
				double e=Math.exp(-b1*t);
				double c=q[l][1];
				double u=thetas[j][2]*(Math.exp(-b1*t)-Math.exp(-thetas[j][5]*t));
				sum1=sum1+x*t*e*(c-u);
				l++;
			}
			sum2=sum2+sum1;
			i++;
		}
		return -sum2;
	}
	
	protected static double funcaoDHB1 (int j, double[][] thetas, Amostra a, int k, double b1){
		
		int i=0;
		double sum1=0, sum2=0;
		while(i<k){
			int l=0;
			double[][] q=a.indice(i);
			double x=FuncoesEM.funcaoX(j,thetas,q);
			while(l<q.length){
				double t=q[l][0];
				double e=Math.exp(-b1*t);
				double c=q[l][1];
				double u=thetas[j][2]*(2*Math.exp(-b1*t)-Math.exp(-thetas[j][5]*t))-c;
				sum1=sum1+x*Math.pow(t, 2)*e*u;
				l++;
			}
			sum2=sum2+sum1;
			i++;
		}
		return -sum2;
		}
	
	protected static double B1Newton (int j, double[][] thetas, Amostra a, int k){
		
		double bn0=thetas[j][4];
		double h1=funcaoHB1(j, thetas, a, k, bn0);
		double dh1=funcaoDHB1(j, thetas, a, k, bn0);
		double bn=bn0-h1*Math.pow(dh1, -1);
		int n=1;
		while((0<bn && bn<thetas[j][5]) && h1!=0 && n<10000){
			h1=funcaoHB1(j, thetas, a, k, bn);
			dh1=funcaoDHB1(j, thetas, a, k, bn);
			bn=bn-h1*Math.pow(dh1, -1);
			n++;
		}
		return bn;
		}
	
	protected static double B1Newton2 (int j, double[][] thetas, Amostra a, int k, double beta){
		
		double bn0=thetas[j][4];
		double h1=funcaoHB1(j, thetas, a, k, bn0);
		double dh1=funcaoDHB1(j, thetas, a, k, bn0);
		double bn=bn0-h1*Math.pow(dh1, -1);
		double p=beta;
		int n=1;
		while(dh1<p && (0<bn && bn<thetas[j][5]) && n<10000){
			h1=funcaoHB1(j, thetas, a, k, bn);
			dh1=funcaoDHB1(j, thetas, a, k, bn);
			bn=bn-h1*Math.pow(dh1, -1);
			n++;
		}
		if(bn>0 && bn<thetas[j][5]){ 
			return bn;
		}
			else{
				p=p-0.2;};
				if(p>-5){
				double [][]aux = thetas;
				aux[j][4]=bn;
				return B1Newton2 (j, aux, a, k, p);
				}
				else{
					return bn0;
				}
		}
	
	
	
	protected static double funcaoHB2 (int j, double[][] thetas, Amostra a, int k,double b2){
		
		int i=0;
		double sum1=0, sum2=0;
		while(i<k){
			int l=0;
			double[][] q=a.indice(i);
			double x=FuncaesEM.funcaoX(j,thetas,q);
			while(l<q.length){
				double t=q[l][0];
				double e=Math.exp(-b2*t);
				double c=q[l][1];
				double u=thetas[j][2]*(Math.exp(-thetas[j][4]*t)-Math.exp(-b2*t));
				sum1=sum1+x*t*e*(c-u);
				l++;
			}
			sum2=sum2+sum1;
			i++;
		}
		return sum2;
	}
	
	protected static double funcaoDHB2 (int j, double[][] thetas, Amostra a, int k, double b2){
		
		int i=0;
		double sum1=0, sum2=0;
		while(i<k){
			int l=0;
			double[][] q=a.indice(i);
			double x=FuncoesEM.funcaoX(j,thetas,q);
			while(l<q.length){
				double t=q[l][0];
				double e=Math.exp(-b2*t);
				double c=q[l][1];
				double u=thetas[j][2]*(2*Math.exp(-thetas[j][4]*t)-Math.exp(-b2*t))-c;
				sum1=sum1+x*Math.pow(t, 2)*e*u;
				l++;
			}
			sum2=sum2+sum1;
			i++;
		}
		return sum2;
		}
	
	protected static double B2Newton (int j, double[][] thetas, Amostra a, int k){
		
		double bn0=thetas[j][5];
		double h2=funcaoHB2(j, thetas, a, k, bn0);
		double dh2=funcaoDHB2(j, thetas, a, k, bn0);
		double bn=bn0-h2*Math.pow(dh2, -1);
		int n=1;
		while((thetas[j][4]<bn && bn<5) && h2!=0 && n<10000){
			h2=funcaoHB2(j, thetas, a, k, bn);
			dh2=funcaoDHB2(j, thetas, a, k, bn);
			bn=bn-h2*Math.pow(dh2, -1);
			n++;
		}
		return bn;
		}
	
	protected static double B2Newton2 (int j, double[][] thetas, Amostra a, int k, double beta){
		
		double bn0=thetas[j][5];
		double h2=funcaoHB2(j, thetas, a, k, bn0);
		double dh2=funcaoDHB2(j, thetas, a, k, bn0);
		double bn=bn0-h2*Math.pow(dh2, -1);
		double p=beta;
		int n=1;
		while(dh2<p && (thetas[j][4]<bn && bn<5) && n<10000){
			h2=funcaoHB2(j, thetas, a, k, bn);
			dh2=funcaoDHB2(j, thetas, a, k, bn);
			bn=bn-h2*Math.pow(dh2, -1);
			n++;
		}
		if(bn>thetas[j][4] && bn<5){ 
			return bn;
		}
			else{
				p=p-0.2;};
				if(p>-5){
				double [][]aux = thetas;
				aux[j][4]=bn;
				return B2Newton2 (j, aux, a, k, p);
				}
				else{
					return bn0;
				}
		}

}


