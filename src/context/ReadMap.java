package context;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadMap {
	private String path;
	private int width;
	private int height;
	
	private float bankerProb;
	private float driverProb;
	private float farmerProb;
	private float sellerProb;
	private float studentProb;
	private float teacherProb;
	
	public ReadMap(String file) {
		this.path = "tests/carte.txt";

        BufferedReader br = null;
        FileReader fr = null;

        try {
            fr = new FileReader(this.path);
            br = new BufferedReader(fr);

            String sCurrentLine;

            br = new BufferedReader(new FileReader(this.path));
            int F = 0;
            int f = 0;
            int W = 0;
            int H = 0;
            int R = 0;
            int L = 0;
            int C = 0;

            sCurrentLine = br.readLine();
            this.width = Integer.parseInt(sCurrentLine.split(" ")[0]);
            this.height = Integer.parseInt(sCurrentLine.split(" ")[1]);

            int i = 0;
            int j = 0;

            while ((sCurrentLine = br.readLine()) != null && i != width) {
                for (String mapElt : sCurrentLine.split(" ")) {
                    switch (mapElt) {
                        case "F": { F++; break; }
                        case "f": { f++; break; }
                        case "W": { W++; break; }
                        case "H": { H++; break; }
                        case "R": { R++; break; }
                        case "L": { L++; break; }
                        case "C": { C++; break; }
                    }
                    j++;
                }
                System.out.println(sCurrentLine);
                i++;
            }
//            System.out.println("nb de F : " + F);
//            System.out.println("nb de f : " + f);
//            System.out.println("nb de W : " + W);
//            System.out.println("nb de H : " + H);
//            System.out.println("nb de R : " + R);
//            System.out.println("nb de L : " + L);
//            System.out.println("nb de C : " + C);

            while ((sCurrentLine = br.readLine()) != null) {
                String[] agentStat = sCurrentLine.split(" ");
                switch (agentStat[0]) {
            		case "BANKER"  : { this.bankerProb = Float.valueOf(agentStat[1]); break; }
            		case "DRIVER"  : { this.driverProb = Float.valueOf(agentStat[1]); break; }
            		case "FARMER"  : { this.farmerProb = Float.valueOf(agentStat[1]); break; }
            		case "SELLER"  : { this.sellerProb = Float.valueOf(agentStat[1]); break; }
            		case "STUDENT" : { this.studentProb = Float.valueOf(agentStat[1]); break; }
            		case "TEACHER" : { this.teacherProb = Float.valueOf(agentStat[1]); break; }
                }
            }
            	
            
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null)
                    br.close();
                if (fr != null)
                    fr.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

    }

}
