public class Natural {

        //clasa Natural e similara cu multimea numerelor naturale N
        private int n;				//campul clasei, n o valoare intreaga care va fi >=0

        public Natural(){			//constructor implicit cu initializare cu 0
            n=0;
        }
        public Natural(int n){		//constructor de initializare, cu parametri
            if(n>=0) this.n=n;
            else     this.n=0;
        }

        public Natural(Natural X){	//constructor de copiere
            if(this!=X) n=X.n;
        }

        public int getN() {			//accesor de tip get
            return n;
        }

        public void setN(int n) {	//accesor de tip set
            if(n>=0) this.n=n;
            else     this.n=0;
        }
        public boolean Egal(Natural N){ //metoda de comparare
            if(this!=N)					//comparam this cu N, doar daca sunt diferite
            {if(n==N.n)return true;
                return false;
            }
            return false;

        }
        public boolean maiMic(Natural N){	//compara this cu N
            if(n<N.n) return true;			//daca this<N retur true
            return false;
        }

    public boolean maiMare(Natural N){	//compara this cu N
        if(n>N.n) return true;			//daca this<N retur true
        return false;
    }



    public void Plus(Natural N){		//this<-this + N		apelul A.Plus(B)
            if(this!=N)
                n+=N.n;						//n=n+N.n
        }
        public void Plus(Natural A, Natural B) {// this<-A+B      apelul X.Plus(Y,Z)
            n=A.n+B.n;		   			//
        }
        public void PlusPlus(int i){		//incremnetarea lui this. n cu i  X.PlusPLus(5);
            n+=i;
        }
        public static Natural Aduna(Natural A, Natural B) {	//   apelul X=Natural.Aduna(Y,Z)
            return (new Natural(A.n+B.n));
        }
        public void Minus(Natural N){					//this<-this-N, daca e posibila scaderea, altfel 0
            if(this!=N)
                if(n>=N.n) n-=N.n;
                else       n=0;
        }
        public void Minus(Natural A, Natural B) {// this<-A-B      apelul X.Minus(Y,Z)
            if(A.n>=B.n) n=A.n- B.n;
            else         n=0;
        }

        public static Natural Scade(Natural A, Natural B) {	//apelul X=Scade(Y,Z)
            if(A.n>=B.n) return (new Natural(A.n-B.n));		//daca e posibla scaderea
            return (new Natural());    		//altfel 0
        }
        public void Ori(Natural N){				//this<-this*N		apelul X.Ori(Y)
            n*=N.n;
        }
        public static Natural Produs(Natural A, Natural B){	//X=Natural.Produs(Y,Z)
            return (new Natural(A.n*B.n));
        }
        public void Slash(Natural A){						// this <- [this/A]
            if(A.n>0) n/=A.n;
            else      n=0;
        }
        public static Natural Imparte(Natural A, Natural B){//X<- [A/B],  apelul X=Imparte(Y,X)
            if(B.n>0) return (new Natural(A.n/B.n));
            return  (new Natural());				//retur 0 daca nu se poate face impartirea
        }
        public void Modulo(Natural A){				//this <- this % A
            if(A.n>0) n%=A.n;
            else      n=0;
        }
        public static Natural Moduloo(Natural A, Natural B){
            if(B.n>0) return (new Natural(A.n%B.n));
            return  (new Natural());
        }


    }




