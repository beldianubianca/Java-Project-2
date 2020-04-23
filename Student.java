public class Student {
    private String nume=null;
    private String materie=null;
    private Natural nota=null;
    private Natural matricol=null;
    private String data_nasterii=null;
    private double MG;

    public Student(){	//constructor implicit
    }

    public Student(String nume, String materie, int nota, int matricol, String data_nasterii){ //constr. cu parametri
        this.nume=nume;
        this.materie=materie;
        this.nota=new Natural(nota); //legatura de tip compozitie
        this.matricol=new Natural(matricol);
       // this.data_nasterii=new Data(data_nasterii);
        this.data_nasterii=data_nasterii;
    }

    public Student (Student s){
        nume=s.nume;
        materie=s.materie;
        nota=s.nota;
        matricol=s.matricol;
        data_nasterii=s.data_nasterii;
    }

    //accesori (getari si setari)
    public double getMG() {
        return MG;
    }
    public void setMG(double d) {
        this.MG = d;
    }
    public String getNume() {
        return nume;
    }
    public void setNume(String nume) {
        this.nume = nume;
    }
    public String getMaterie() {
        return materie;
    }
    public void setMaterie(String materie) {
        this.materie = materie;
    }
    public int getNota() {
        return nota.getN();
    }
    public void setNota(int nota) {
        this.nota = new Natural(nota);
    }
    public int getMatricol() {
        return matricol.getN();
    }
    public void setMatricol(int matricol) {
        this.matricol = new Natural(matricol);
    }
    public String getData(){
        return this.data_nasterii;
    }
    public void setData(String data2){
        this.data_nasterii=data2;
    }

    public boolean maiMicNume(Student s){
        if(nume.compareTo(s.nume)<0) return true;
        return false;
    }
    public boolean maiMicNota(Student s){
        if(this.nota.maiMic(s.nota)) return true;
        return false;
    }

    public boolean maiMareNota(Student s){
        if(this.nota.maiMare(s.nota)) return true;
        return false;
    }


}
