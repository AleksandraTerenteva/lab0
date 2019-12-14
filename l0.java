package lab0;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class l0 {
	
	private static String fname = "inf.txt"; 
	private static String k[] = new String[13];
	private static String elements []= new String [13];
	private static final String separator = ";";
	private static int h;
	
	public static void main (String [] args)  {
		Readtxt();
		
		for (int i=0; i<13; i++) {
			System.out.println(k[i]); }
			System.out.println("---------------------------------------------------------------------------------------------------------------------------");
			String [] pal=new String [13];
		
		for (int i=0; i<13; i++) {
			pal[i]=palindrom(k[i]);}
			int [] sym=new int [13];
		
		for (int i=0;i<13;i++) {
			sym[i]=symvol(k[i]); }
			int [] byt=new int [13];
		
		for (int i=0;i<13;i++) {
			byt[i]=bytes(k[i]);}
		
		double [] hartli=new double [13];
		
		for (int i=0;i<13;i++) {
			hartli=bithartli(); }
		
		double [] sh1=new double [13];
			for (int i=0;i<13;i++) {
				sh1=shennon(); }
			tabl(pal,sym,byt,hartli,sh1);
		}
		
	public static void Readtxt() {
	File file = new File(fname);
		try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "Windows-1251"));) {
			String line = "";
			int i=0;
			while ((line=br.readLine())!= null) {
				String [] elements = line.split(separator); 
				k[i]=elements[0];
				i++;
		}
	} 
		catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		}
	
	public static void tabl(String [] palindrom, int [] symvol, int [] bytes, double [] bithartli, double [] bitshennon) {
		
		System.out.printf("%-4s%-76s%-43s%n","№","Слово","Количество информации");
		System.out.println("---------------------------------------------------------------------------------------------------------------------------");
		System.out.printf("%-1s%-58s%-11s%-11s%-19s%-15s%-11s%n","","","палиндром","кол-во","байт, размер","бит,","бит,");
		System.out.printf("%70s%-11s%-14s%-14s%-15s%n","","символов","в программе","по Хартли","по Шенному");
		
		for (int i=0; i<13; i++) {
			System.out.printf("%-4s%-55s%-11s%-11s%11s%12.2f%16.9f%n",i+1, k[i], palindrom[i], symvol[i], bytes[i], bithartli[i], bitshennon[i]);}
	}
	public static String palindrom(String d) {
		String plus="+";
		String minus="-";
        	if (d.length()==1)
        		return plus;
        	for (int i=0; i<d.length()/2; i++) {
        		if (d.toLowerCase().charAt(i)!= d.toLowerCase().charAt(d.length()-1-i))
        			return minus; }
        	return plus; }
    
	public static int symvol(String g) {
		return g.length() ; 
		}
			
    public static int bytes(String g2) {
		return g2.length()*1;
    }
    public static double [] bithartli() {
    	int [] q=new int [13];
    	double [] f=new double[13];
    	double [] log=new double [13];
    	for (int y=0; y<k.length; y++) {
    		q[y]=k[y].length();	 }
        
    	for (int j=0;j<k.length;j++) {
    		char [] s=k[j].toCharArray();
    		
    		for (int i=0;i<s.length-1;i++) {
    			int w=0;
    			int x=i+1;
    			while (x!=s.length && w!=1) { 
    				if (s[i]==s[x] ){
    					w++; }
    				x++;} 
    			q[j]=q[j]-w;}}
    	
    	for (int i=0;i<13;i++) {
    		log[i]=(double) (Math.log(q[i])/Math.log(2));
    		f[i]=k[i].length()*log[i];
    	}
    	return f; }
    
    public static double[] shennon() {
    	double f1;
    	double[] r=new double[13];
			for (int i=0; i<13; i++) {
				char[] K=k[i].toCharArray();
				while (K[0]!=1) {
					K=sh2(K[0], K);
					f1=(double)h/(K.length);
					r[i]=r[i]-(f1*Math.log(f1))/Math.log(2); }
		} 
			return r; }
		
    public static char[] sh2(char a, char[] z) {
		h=0;
		
		for (int j=0; j<z.length; j++) { 
			for (int i=0; i<z.length; i++) {
				if (z[i]==a) { h++;
				for (int x=i; x<z.length-1; x++) {
					z[x]=z[x+1]; }
				z[z.length-1]=1;  }}}
			return z; } 
		}
