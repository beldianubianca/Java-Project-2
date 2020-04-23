import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Formatter;
import java.util.Scanner;
//import java.lang.*;
import java.util.Arrays;
import java.io.*;

/*7. Catalog clasă de liceu (parţial doar cu note, fără absenţe)
Se cunosc informaţiile despre elevii unei clase (nume, data_naşterii, matricol, notele la fiecare materie).
BD (sau ansamblu de fişiere/tabele în memorie) .
Se cere soft operaţii CRUD (Create/Read/Update/Delete)şi pentru:
- clasamentul elevilor după media generală (descrescător după medie);
- un tabel cu elevii corigenţi pe toamnă şi disciplinele la care nu au promovat;
- câte un tabel pentru fiecare disciplină în ordinea descrescătoare a mediilor;
- un tabel cu elevii în ordinea descrescătoare a vârstei.
Deduceţi de ce clase aveţi nevoie!!!
*/


public class Rapoarte {


    public static String CitString(String sir){
        try{
            System.out.print(sir);
            Scanner S= new Scanner(System.in);
            return S.nextLine();
        }
        catch(Exception E){
            System.out.println("Ai gresit, mai incearca");
            return CitString(sir);
        }
    }

    public static int CitIntreg(String sir){
        try{
            System.out.print(sir);
            Scanner S= new Scanner(System.in);
            int I=S.nextInt();
            return I;
        }
        catch(Exception E){
            System.out.println("Ai gresit, mai incearca");
            return CitIntreg(sir);
        }
    }
    public static Student[] CitireDinFisier(){
        int n;
        Student St[]=null;
        try { BufferedReader fisIn = new BufferedReader(new FileReader("C:\\Users\\Bianca\\IdeaProjects\\Catalog\\src\\Student.txt"));
            String s;
            s=fisIn.readLine();
            n=Integer.parseInt(s);


            St=new Student[n];				//aloca n referinte pentru fiecare Student
            System.out.println("n="+St.length);

            int i=0;
            while((s = fisIn.readLine())!= null){
                String felii[]=s.split(",");
                String nume=felii[0];
                String materie=felii[1];
                int nota=Integer.parseInt(felii[2]);
                int matricol=Integer.parseInt(felii[3]);
                String data=felii[4];

                St[i]=new Student();			//alocarea efectiva pentru comp. Student
                St[i].setNume(nume);			//se atribiue valori lui Nume, Materie
                St[i].setMaterie(materie);		//si Nota
                St[i].setNota(nota);
                St[i].setMatricol(matricol);
                St[i].setData(data);

                i++;
            }
            System.out.println("lungimea tabelului="+St.length);
            fisIn.close();

        } // try
        catch(Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } // catch //citiri valorile vectorului
        return St;
    }
    public static void capTabel1(){
        String sir=  "|Student   |Materie\t     | Nota\t| Matricol\t|Data nasterii|";
        String linii="==================================================================";
        System.out.println(linii);
        System.out.println(sir);
        System.out.println(linii);
    }
    public static void AfisTot(Student St[]){
        capTabel1();
        int i=0;
        while(i<St.length){
            //Formatter f=new Formatter();
            String []s=new String[5];
            s[0]=St[i].getNume();
            s[1]=St[i].getMaterie();
            int nota=St[i].getNota();
            s[2]=Integer.toString(nota);
            int matricol=St[i].getMatricol();
            s[3]=Integer.toString(matricol);
            s[4]=St[i].getData();
            //f.format("|%-10s|%-13s|%6s|",s);
            //System.out.println(f);
            System.out.printf("|%-10s|%-13s|%6s|%11s|%13s|\n",s);
            i++;
        }
    }
    public static void capTabel2(){
        String sir="|Student   | Nota |";
        String linii="===================";
        System.out.println(linii);
        System.out.println(sir);
        System.out.println(linii);
    }
    public static void AfisMaterie(Student St[]){
        String materie= CitString("Da materia:");
        System.out.println(materie);
        capTabel2();
        for(int i=0; i<St.length;i++){
            String mat=St[i].getMaterie();
            if(materie.equals(mat)){
                String []s=new String[2];
                s[0]=St[i].getNume();
                int nota=St[i].getNota();
                s[1]=Integer.toString(nota);
                System.out.printf("|%-10s|%6s|\n",s);

            }
        }

    }

    public static void capTabel3(){
        String sir="|Student   | Nota |";
        String linii="===================";
        System.out.println(linii);
        System.out.println(sir);
        System.out.println(linii);
    }

    public static void AfisStudent(Student []St){
        String nume= CitString("Da nume student:");
        System.out.println(nume);
        capTabel3();
        for(int i=0; i<St.length;i++){
            String numeS=St[i].getNume();
            if(nume.equals(numeS)){
                String []s=new String[2];
                s[0]=St[i].getMaterie();
                int nota=St[i].getNota();
                s[1]=Integer.toString(nota);
                System.out.printf("|%-10s|%6s|\n",s);

            }
        }

    }

    public static void capTabel5(){
        String sir="|Student   | Materie |";
        String linii="===================";
        System.out.println(linii);
        System.out.println(sir);
        System.out.println(linii);
    }

    public static void AfisareCorigenti(Student []St){
        capTabel5();
        for(int i=0;i<St.length;i++){
            //int notaS=St[i].getNota();
            if(St[i].getNota()<5){
                String []s=new String[2];
                s[0]=St[i].getNume();
                s[1]=St[i].getMaterie();
                System.out.printf("|%-10s|%6s|\n",s);
            }
        }

    }

    public static void capTabel6(){
        String sir="| Nota |";
        String linii="========";
        System.out.println(linii);
        System.out.println(sir);
        System.out.println(linii);
    }

    public static void SortareNote(Student[] St){
        boolean flag;				 // sortarea vectorului
        int i,poz,pozInter;
        poz    = pozInter = St.length-1;
        do
        { flag=true;
            for (i=0;i<poz;i++)
                if (St[i+1].maiMicNota(St[i]))
                { Student aux=St[i];
                    St[i]		 =St[i+1];
                    St[i+1]	 =aux;
                    pozInter = i;
                    flag     = false;
                }
            poz=pozInter;
        }
        while (!flag);
    }


    public static boolean exista(String v[],String e){
        for(int i=0;i<v.length;i++)
            if(v[i].equals(e))
                return true;
            return false;

    }

    public static void SortareNoteDes(Student[] St){
        boolean flag;				 // sortarea vectorului
        int i,poz,pozInter;
        poz    = pozInter = St.length-1;
        do
        { flag=true;
            for (i=0;i<poz;i++)
                if (St[i+1].maiMareNota(St[i]))
                { Student aux=St[i];
                    St[i]		 =St[i+1];
                    St[i+1]	 =aux;
                    pozInter = i;
                    flag     = false;
                }
            poz=pozInter;
        }
        while (!flag);
    }


    public static void AfisDisciplina3(Student []St){
        SortareNoteDes(St);
        String materie[]={St[0].getMaterie()};
        int c=0;
       // String numeMaterie;
        for(int i=0;i<St.length;i++){
            String m = St[i].getMaterie();
            if(exista(materie,m)==false){
                String[] materie2=new String [materie.length+1];
                for(int j=0;j<materie.length;j++)
                    materie2[j]=materie[j];
                materie2[materie2.length-1]=m;
                materie=materie2;}

        }
        for(int i=0;i<materie.length;i++) {
            // numeMaterie=materie[i];
            System.out.println(materie[i]);
            capTabel2();
            for(int j=0;j<St.length;j++){
                if(materie[i].equals(St[j].getMaterie())){
                    String[] s = new String[2];
                    s[0]=St[j].getNume();
                    int nota = St[j].getNota();
                    s[1] = Integer.toString(nota);
                    System.out.printf("|%-10s|%6s|\n", s);
                }

            }

        }

    }




    public static void Sortare(Student[] St){
        boolean flag;				 // sortarea vectorului
        int i,poz,pozInter;
        poz    = pozInter = St.length-1;
        do
        { flag=true;
            for (i=0;i<poz;i++)
                if (St[i+1].maiMicNume(St[i]))
                { Student aux=St[i];
                    St[i]		 =St[i+1];
                    St[i+1]	 =aux;
                    pozInter = i;
                    flag     = false;
                }
            poz=pozInter;
        }
        while (!flag);

    }
    public static void AfisAlfabet(Student St[]){
        Sortare(St);
        AfisTot(St);
    }
    public static void AfisMedGen(Student St[]){
        Sortare(St);
        Student []MedGen=new Student[20];//acest tabel se va sorta supa medie generala
        String nume=new String();	//in nume se va retine numele primului student
        nume=St[0].getNume();		//pentru comparare, cand trec la student se
        //seteaza pe noul nume
        int contorMG=0;				//contor pentru numarul de studenti(nume diferite)
        double media=0;				//media generala
        int i=0;					//contor pentru tabloul studenti
        while(i<St.length){
            int nrNote=0;			//contor pt notele unui student
            while(i<St.length && nume.equals(St[i].getNume())){
                media+=St[i].getNota();
                nrNote++; i++;
            }						//s-a termint un student
            media=media/nrNote;		//se calc. media si apoi se pune in MedGen
            //System.out.println(nume+" "+media+" "+contorMG);
            MedGen[contorMG]=new Student();
            MedGen[contorMG].setNume(nume);
            MedGen[contorMG].setMG(media);
            contorMG++;
            media=0;					//se reseteaza media si nrNote pe 0.
            if(i<St.length) nume=St[i].getNume();
        }

        //for(i=0;i<contorMG;i++)
        //	System.out.println(MedGen[i].getNume()+" "+ MedGen[i].getMG());

        SortareMG(MedGen,contorMG-1);

        capTabel4();
        for(i=0; i<contorMG;i++){
            String []s=new String[2];
            s[0] =MedGen[i].getNume();
            media=MedGen[i].getMG()*100;
            int m=(int)(media);
            media=m/100.0;
            s[1]=Double.toString(media);
            System.out.printf("|%-10s| %6s |\n",s);

        }
    }

    public static void capTabel4(){
        String sir="|Student   | MedGen |";
        String linii="=====================";
        System.out.println(linii);
        System.out.println(sir);
        System.out.println(linii);
    }


    public static void SortareMG(Student[] MedGen,int n){
        boolean flag;				 // sortarea vectorului
        do
        { flag=true;
            for (int i=0;i<n;i++)
                if (MedGen[i].getMG()<MedGen[i+1].getMG())
                { Student aux=MedGen[i];
                    MedGen[i]	 =MedGen[i+1];
                    MedGen[i+1]=aux;
                    flag     = false;
                }
        }
        while (!flag);

    }

    public static void StergereStudent(Student St[]){
        int matricol=CitIntreg("nr. matricol");
        int m=St.length-1;
        for(int i=0;i<St.length;i++){
            if(St[i].getMatricol()==matricol){
                for(int j=i;j<m;j++){
                    St[j]=St[j+1];

                }
            m--;
            }
        }
        //for(int i=0;i<m;i++)
          //  System.out.println(St[i]);

    }



/*

    public static void AfisEleviDupaData(Student St[]){
        Student[] s=St;
        int m=s.length;
        for(int i=0;i<m;i++){
            for(int j=i+1;j<m;j++){
                if(s[i].getMatricol()==s[j].getMatricol()){
                    for(int k=j;k<m-1;k++){
                        //System.out.println("ok");
                        s[k]=s[k+1];
                    }
                    m--;
                }
            }
        }

        /* while((s = fisIn.readLine())!= null){
                String felii[]=s.split(",");
                String nume=felii[0];
                String materie=felii[1];
                int nota=Integer.parseInt(felii[2]);
                int matricol=Integer.parseInt(felii[3]);
                String data=felii[4];

                St[i]=new Student();			//alocarea efectiva pentru comp. Student
                St[i].setNume(nume);			//se atribiue valori lui Nume, Materie
                St[i].setMaterie(materie);		//si Nota
                St[i].setNota(nota);
                St[i].setMatricol(matricol);
                St[i].setData(data);

                i++;


        for(int i=0;i<m-1;i++){
            String data[]=St[i].getData().split(".");
            int  zi=Integer.parseInt(data[0]);
            int luna=Integer.parseInt(data[1]);
            int an=Integer.parseInt(data[2]);
            for(int j=i+1;j<m;j++){
                String data2[]=St[i].getData().split(".");
                int  zi2=Integer.parseInt(data[0]);
                int luna2=Integer.parseInt(data[1]);
                int an2=Integer.parseInt(data[2]);
                if((an2==an && luna2==luna && zi2>zi)||(an2==an && luna2>luna)||(an2>an)){
                    Student k=St[i];
                    St[i]=St[j];
                    St[j]=k;
                }
            }
        }

        for(int i=1;i<m;i++){
            System.out.println(St[i].getNume()+":"+St[i].getData());
        }
    }
    */
    public static boolean dataMaiMare(String data1,String data2){
        //String data1=CitString("data");
        //String data2=CitString("data");

        String[] data=data1.split("-");
        int zi=Integer.parseInt(data[0]);
        int luna=Integer.parseInt(data[1]);
        int an=Integer.parseInt(data[2]);

        String datav[]=data2.split("-");
        int  zi2=Integer.parseInt(datav[0]);
        int luna2=Integer.parseInt(datav[1]);
        int an2=Integer.parseInt(datav[2]);

        if((an2==an && luna2==luna && zi2>zi)||(an2==an && luna2>luna)||(an2>an))
            return true;

        return false;

    }

    /*  String materie[]={St[0].getMaterie()};
        int c=0;
       // String numeMaterie;
        for(int i=0;i<St.length;i++){
            String m = St[i].getMaterie();
            if(exista(materie,m)==false){
                String[] materie2=new String [materie.length+1];
                for(int j=0;j<materie.length;j++)
                    materie2[j]=materie[j];
                materie2[materie2.length-1]=m;
                materie=materie2;}

        }*/

   /* public static void AfisareEleviDupaData(Student St[]){
        String d[]={St[0].getData()};
        for(int i=0;i<St.length;i++){
            String dat=St[i].getData();
            if(exista(d,dat)==false){
                String[] d2=new String[d.length+1];
                for(int j=0;j<d.length;j++)
                    d2[j]=d[j];
                d2[d2.length-1]=dat;
                d=d2;
            }

        }

    for(int i=0;i<d.length;i++)
        System.out.println(d[i]);

    }*/

    public static boolean existaObiect(Student v[],Student e){
        for(int i=0;i<v.length;i++)
            if(v[i].getMatricol()==e.getMatricol())
                return true;
        return false;

    }

    public static void capTabel7(){
        String sir="|Student   | Data      |";
        String linii="=========================";
        System.out.println(linii);
        System.out.println(sir);
        System.out.println(linii);
    }

    public static void AfisareEleviDupaData(Student St[]){
        Student s[]={St[0]};
        for(int i=0;i<St.length;i++){
            Student st=St[i];
            if(existaObiect(s,st)==false){
                Student[] s2=new Student[s.length+1];
                for(int j=0;j<s.length;j++)
                    s2[j]=s[j];
                s2[s2.length-1]=st;
                s=s2;
            }
        }
        for(int i=0;i<s.length-1;i++) {
            for (int j = i + 1; j < s.length; j++) {
                if(dataMaiMare(s[i].getData(),s[j].getData())==true)
                {   System.out.println("ok");
                    Student aux=s[i];
                    s[i]=s[j];
                    s[j]=aux;
                }

            }
        }
        capTabel7();

        for(int i=0;i<s.length;i++){
            String str[]=new String[2];
            str[0]=s[i].getNume();
            str[1]=s[i].getData();
            System.out.printf("|%-10s|%10s|\n",str);
        }
    }



    public static void  ModificaNota(Student st[]) {
        int nota2;
        String nume=CitString("nume");
        String materie=CitString("materie");
        nota2=CitIntreg("nota:");
        for(int i=0;i<st.length;i++) {
            if(st[i].getNume().equals(nume) && st[i].getMaterie().equals(materie))
                st[i].setNota(nota2);
        }

    }

/*
    public static String exista2(Student st[],int e){
        for(int i=0;i<st.length;i++)
            if(st[i].getMatricol()==e)
                {
                    String v[]={st[i].getNume(),st[i].getData()};

                }
            return v;
    }*/


    public static void AdaudareStudent(Student St[]){
        String nume,data;
        int matricol=CitIntreg("matricol");
        //if(exista2(st,matricol)==true){ }
        for(int i=0;i<St.length;i++){
            if(St[i].getMatricol()==matricol)
                {
                    nume = St[i].getNume();
                    data = St[i].getData();
                    String materie = CitString("materie");
                    int nota = CitIntreg("nota");
                    Student s = new Student(nume, materie, nota, matricol, data);
                    Student[] St2 = new Student[St.length + 1];
                    for (int j = 0; j < St.length; j++)
                        St2[j] = St[j];
                    St2[St2.length - 1] = s;
                    St = St2;
                    break;
                }
            else{
                    nume = CitString("nume");
                    String materie = CitString("materie");
                    int nota = CitIntreg("nota");
                    data = CitString("data");
                    Student s = new Student(nume, materie, nota, matricol, data);
                    Student[] St2 = new Student[St.length + 1];
                    for (int j = 0; j < St.length; j++)
                        St2[j] = St[j];
                    St2[St2.length - 1] = s;
                    St = St2;
                    break;
                }

        }

    }





    public static int Meniu(){

        System.out.println();
        System.out.println("1.Citire date din fisier");
        System.out.println("2.Afisare tot");
        System.out.println("3.Afisare note pe Materie");
        System.out.println("4.Afisare note pe Student");
        System.out.println("5.Afisare alfabetica");
        System.out.println("6.Afisare medii generale");
        System.out.println("7.Adaugare student nou");
        System.out.println("8.Afisare studenti corigenti");
        System.out.println("9.Afisare note pe disciplina");
        System.out.println("10. Stergere student dupa nr matricol");
        System.out.println("11. Modifica nota unui elev");
        System.out.println("12. Sorteaza elevii dupa data");
        System.out.println("13.Adaugare student");
        System.out.println("0.Terminare program");
        int Opt=CitIntreg ("da optiunea ta:");
        return Opt;
    }

    public static void main(String[] args) {
        int opt=Meniu();
        Student St[] =null;

        while(opt!=0){
            switch(opt){
                case 1:St=CitireDinFisier();
                    System.out.println("am citit corect datele din fisier");
                    break;
                case 2:AfisTot(St); 		//Afiseaza tot tabelul
                    break;
                case 3:AfisMaterie(St);		//Afiseaza dupa o materie selectata
                    break;
                case 4:AfisStudent(St);		//Afisare notele unui student
                    break;
                case 5:AfisAlfabet(St);	    //Afisare alfbetica dupa nume student
                    break;
                case 6:AfisMedGen(St);	    //Afisare medie generala pe student
                    break;
                case 7: String nume=CitString("nume");
                     String materie=CitString("materie");
                    int nota=CitIntreg("nota");
                    int matricol=CitIntreg("matricol");
                    String data=CitString("data");
                    Student s=new Student(nume,materie,nota,matricol,data);
                    Student[] St2=new Student [St.length+1];
                    for(int j=0;j<St.length;j++)
                        St2[j]=St[j];
                    St2[St2.length-1]=s;
                    St=St2;
                    break;
                    //St.addElem(s);
                    /*int i=St.length+1;
                    System.out.println(i);
                    St[i]=new Student();			//alocarea efectiva pentru comp. Student
                    St[i].setNume(nume);			//se atribiue valori lui Nume, Materie
                    St[i].setMaterie(materie);		//si Nota
                    St[i].setNota(nota);
                    St[i].setMatricol(matricol);
                    St[i].setData(data);*/
                    //St.push_back(s);
                case 8:AfisareCorigenti(St);
                    break;
                case 9:AfisDisciplina3(St);
                    break;
                case 10:StergereStudent(St);
                    break;
                case 11:ModificaNota(St);
                    break;
                case 12:AfisareEleviDupaData(St);
                    break;

                case 13:
                    matricol=CitIntreg("matricol");
                    //if(exista2(st,matricol)==true){ }
                    for(int i=0;i<St.length;i++){
                        if(St[i].getMatricol()==matricol)
                        {
                            nume = St[i].getNume();
                            data = St[i].getData();
                            materie = CitString("materie");
                            nota = CitIntreg("nota");
                            Student s2 = new Student(nume, materie, nota, matricol, data);
                            Student[] St3 = new Student[St.length + 1];
                            for (int j = 0; j < St.length; j++)
                                St3[j] = St[j];
                            St3[St3.length - 1] = s2;
                            St = St3;
                            break;
                        }
                        else{
                            nume = CitString("nume");
                            materie = CitString("materie");
                            nota = CitIntreg("nota");
                            data = CitString("data");
                            Student s2 = new Student(nume, materie, nota, matricol, data);
                            Student[] St3 = new Student[St.length + 1];
                            for (int j = 0; j < St.length; j++)
                                St3[j] = St[j];
                            St3[St3.length - 1] = s2;
                            St = St3;
                            break;
                        }

                    }
                    break;
                default:
                    System.out.println("ai gresit optiunea, mai incearca!!!");
            }
            opt=Meniu();
        }
        System.out.println("Program terminat");
    }

}



