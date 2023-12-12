public class Authentication_2A extends Thread{

    @Override
    public void run(){
        String[] input ={"Server is running"};
        try {
            ProtectedServer.main(input);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public static void main(String[] args) throws Exception {
        // multi-thread to run server and client.

        Authentication_2A running = new Authentication_2A();
        running.start();

        // client start
        String[] input = {"Client is running"};
        ProtectedClient.main(input);

    }



}
