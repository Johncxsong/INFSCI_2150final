public class Signature_2B extends Thread{

    @Override
    public void run(){
        String[] input = {"Bob is receiving"};
        try {
            ElGamalBob.main(input);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public static void main(String[] args) throws Exception {

        // sender--> Alice
        // receiver--> Bob

        Signature_2B bob = new Signature_2B();
        bob.start();

        // Alice
        String[] input = {"Alice is sending"};
        ElGamalAlice.main(input);





    }

}
