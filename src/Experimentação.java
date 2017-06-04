
public class Experimentacao {

	public static void main(String[] args){
		Amostra a= new Amostra();
		Amostra b= new Amostra();
		double[] v4=new double[]{4.9,7.0};
		double[] v5=new double[]{5.7,9.7};
		double[] v1=new double[]{0,0};
		double[] v2=new double[]{0.5,631.267};
		double[] v3=new double[]{0.75,795.463};
		a.add(0,v1);
		a.add(0,v2);
		a.add(0,v3);
		//a.add(4,v4);
		//a.add(2,v5);
		//a.add(3,v1);
		//System.out.println(a.length());
		//System.out.println("indice: " + a.element(5)[0]);
		//System.out.println("tempo: " + a.element(5)[1]);
		//System.out.println("valor: "+ a.element(5)[1]);
		//System.out.println("tempo: " + a.indice(4)[0][0]);
		//System.out.println("valor: " + a.indice(0)[1][1]);
		//a.join(b);
		//a.apresenta();
		//System.out.println(a.kapa());
		
		double[][] m1=new double[][] {{0.25,1,60,-60,0.5,0.7},{0.25,1,50,-50,0.9,1.4},{0.25,1,35,-35,0.4,0.5},{0.25,1,29,-29,1,1.2}};
		//double[][] p1=new double[][] {{1,2}};
		Gaussianas m=new Gaussianas();
		m.mix(m1);
		//System.out.println("A probabilidade �:"+ m.prob(p1));
		//m.apresenta();
		//System.out.println(m.theta()[1][2]);
		
		
		Grafos g=new Grafos();
		g.grafoo(8);
		g.add_edge(8, 1,m);
		g.add_edge(8, 2,m);
		g.add_edge(8, 3,m);
		g.add_edge(4, 3, m);
		g.remove_edge(8,1);
		g.up_edge(8, 3, m);
		//g.apresenta();
		
		
		
		
		
}
}

