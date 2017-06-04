
import java.lang.Math;

public class EM {
	
	protected static double[][] algoritmo (Amostra a, double[][]thetas){
		
		int k = a.kapa();
		
		double[][] bux = new double[thetas.length][2];
		
		int iterada = 0;
		double mt[][] = thetas;
		
		while(FuncoesEM.funcaoVerifica(thetas, bux)==true){
		
		int j = 0;
		
		while(j<thetas.length){
						
			bux[j][0]=mt[j][4];
			bux[j][1]=mt[j][5];
			
			
			double wk = FuncoesEM.funcaoW(j,mt,a,k);
			double a1k = FuncoesEM.funcaoA (j, mt, a, k);
			double b1k = FuncoesEM.funcaoB1(j, mt, a, k);
			double b2k = FuncoesEM.funcaoB2(j, mt, a, k);
			double sigk = FuncoesEM.funcaoSigma(j, mt, a, k, a1k, b1k, b2k);
			
			
			mt[j][0]= wk;
			mt[j][1]= Math.sqrt(sigk);
			mt[j][2]= a1k;
			mt[j][3]= -a1k;
			mt[j][4]= b1k;
			mt[j][5]= b2k;
			
			j++;
			}
			System.out.println("iterada:" + iterada);
			iterada++;
			FuncoesEM.funcaoApresenta(mt);
			}
			return mt;
			
		}
		
		
		
		
	}
