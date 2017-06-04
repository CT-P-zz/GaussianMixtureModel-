
import java.lang.Math;

public class Fun��esEM{

	
	
	
	protected static double funcaoP (int j,double [][]thetas, double[][] q ){//double[][] q � a matriz com os tempos e valores para 1 individuo
		
		double a = Math.pow(2*Math.PI*thetas[j][1],-0.5*q.length);
		double sum =0;
		int l = 0;
		while(l< q.length){
			double c = q[l][1];
			double t = q[l][0];
			double f = thetas[j][2]*Math.pow(Math.E,-thetas[j][4]*t)+ thetas[j][3]*Math.pow(Math.E,-thetas[j][5]*t);
			double m = Math.pow(c-f,2);
			sum = sum + m;
			l++;
		}
		
		return a*Math.pow(Math.E,-sum*Math.pow(2*thetas[j][1],-1));
	}
	
	
	
	// 
	protected static double funcaoX (int j, double [][]thetas, double[][] q){
		
		double num = thetas[j][0]*funcaoP(j,thetas, q)*Math.exp(500);
		int i = 0; 
		double sum=0;
		while(i< thetas.length){
			sum = sum + thetas[i][0]*funcaoP(i,thetas,q)*Math.exp(500);
			i++;
		};
		return (num*Math.pow(sum, -1));
}
	
	
	protected static double funcaoW (int j, double [][]thetas, Amostra a, int k){
		
		int i = 0;
		double sum=0;
		while(i<k){
			double[][] q = a.indice(i);
			sum=sum+funcaoX(j, thetas,q);
			i++;
		}
		return sum*Math.pow(k,-1);
		}
	
	//int j � a gaussiana;
	//double[][] thetas � os p�rametros de todas as gaussianas;
	//int k � o n� de individuos;
	
	protected static double funcaoA (int j, double [][]thetas, Amostra a, int k){
		
		int i=0;
		double sum1=0, sum2=0, sum3=0, sum4=0;
		while(i<k){
			int l=0;
			double[][] q=a.indice(i);
			double x=funcaoX(j, thetas, q);
			while(l<q.length){
				double c = q[l][1];
				double t = q[l][0];
				double u = Math.exp(-thetas[j][4]*t)-Math.exp(-thetas[j][5]*t);
				sum2=sum2+x*c*u;
				sum4=sum4+x*Math.pow(u, 2);
				l++;
			}
			sum1 = sum1 + sum2;
			sum3 = sum3 + sum4;
			i++;
		}
		return sum1*Math.pow(sum3, -1);
	}
	
	
	
	
	
	protected static double funcaoB1(int j, double[][] thetas, Amostra a, int k){
		
		double r=Newton.B1Newton (j, thetas, a, k);
		if(r>0 && r<thetas[j][5]){
			return r;
		}
		else{
			r=Newton.B1Newton2(j, thetas, a, k, -0.3);
			return r;
			}
	}
	
	
	
	protected static double funcaoB2(int j, double[][] thetas, Amostra a, int k){
		
		double r=Newton.B2Newton (j, thetas, a, k);
		if(r>thetas[j][4] && r<5){
			return r;
		}
		else{
			r=Newton.B2Newton2(j, thetas, a, k, -0.3);
			return r;
			}
	}
	
	protected static double funcaoSigma(int j, double[][] thetas, Amostra a, int k, double a1, double b1, double b2){
		
		double sum1=0, sum2=0, sum3=0;
		int i=0;
		while(i<k){
			double[][]q = a.indice(i);
			double x = fun��oX(j, thetas, q);
			int l=0;
			while(l<q.length){
				double c = q[l][1];
				double t = q[l][0];
				double f = a1*Math.exp(-b1*t)-a1*Math.exp(-b2*t);
				double u = x*Math.pow(c-f, 2);
				sum2=sum2+u;
				l++;
			}
			sum1=sum1+sum2;
			sum3=sum3+q.length*x;
			i++;
		}
		return sum1*Math.pow(sum3, -1);
	}
	
	protected static boolean funcaoVerifica(double[][] thetas, double[][] bux){
		int j=0;
		boolean aux=true;
		while(j<bux.length && aux!=false){
			if(Math.pow(thetas[j][4]-bux[j][0], 2)<0.000001 && Math.pow(thetas[j][5]-bux[j][1], 2)<0.000001){
				aux=false;
			}else{
				aux=true;
			}
			j++;
		}
		return aux;
	}
	
	protected static void funcaoApresenta(double[][] mt){
		int i=0;
		while(i<mt.length){
			System.out.println("Gaussiana "  + (i+1) );
			System.out.println("Par�metros (w,dpad,a1,a2,b1,b2):");
			for(int j=0; j<mt[i].length; j++)
			System.out.printf("%s ;", mt[i][j]);
			System.out.println();
			
			i++;
			}
	}
	
	
}

