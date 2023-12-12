public class Encryption_2C extends Thread {

    @Override
    public void run(){
        String[] input = {"Client is running"};

        try {
            CipherClient.main(input);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


    public static void main(String[] args) throws Exception {

        // start server
        Encryption_2C client = new Encryption_2C();
        client.start();

        // client start
        String[] input ={"Server is running"};
        CipherServer.main(input);



    }
}
