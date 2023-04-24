// Java implementation of Server side
// It contains two classes : Server and ClientHandler
// Save file as Server.java

import java.io.*;
import java.text.*;
import java.util.*;
import java.net.*;

import com.google.gson.Gson;


// Server class
public class DataServer
{
    public static void main(String[] args) throws IOException
    {
        // server is listening on port 5056
        ServerSocket ss = new ServerSocket(5056);



        // running infinite loop for getting
        // client request

        System.out.println("Starting server program!!!");

        int nClients = 0;

        while (true)
        {
            Socket s = null;

            try
            {
                // socket object to receive incoming client requests
                s = ss.accept();

                nClients++;
                System.out.println("A new client is connected : " + s + " client number: " + nClients);

                // obtaining input and out streams
                DataInputStream dis = new DataInputStream(s.getInputStream());
                DataOutputStream dos = new DataOutputStream(s.getOutputStream());

                System.out.println("Assigning new thread for this client");

                // create a new thread object
                Thread t = new ClientHandler(s, dis, dos);

                // Invoking the start() method
                t.start();

            }
            catch (Exception e){
                s.close();
                e.printStackTrace();
            }
        }
    }
}




// ClientHandler class
class ClientHandler extends Thread
{
    final DataInputStream dis;
    final DataOutputStream dos;
    final Socket s;

    Gson gson = new Gson();

    DataAccess dao = new SQLiteDataAdapter();



    // Constructor
    public ClientHandler(Socket s, DataInputStream dis, DataOutputStream dos)
    {
        this.s = s;
        this.dis = dis;
        this.dos = dos;
        dao.connect();
    }

    @Override
    public void run()
    {
        String received;
        while (true) {
            try {
                received = dis.readUTF();
                System.out.println("Message from client " + received);
                RequestModel req = gson.fromJson(received, RequestModel.class);
                ResponseModel res = new ResponseModel();
                if (req.code == RequestModel.EXIT_REQUEST) {
                    System.out.println("Client " + this.s + " sends exit...");
                    System.out.println("Closing this connection.");
                    this.s.close();
                    System.out.println("Connection closed");
                    break;
                }
                else
                if (req.code == RequestModel.USER_REQUEST) {
                    String username = req.body;
                    System.out.println("The client is asking for a user named: " + username);
                    User user = dao.loadUser(username);
                    if (user != null) {
                        res.code = ResponseModel.OK;
                        res.body = gson.toJson(user);
                    }
                    else {
                        res.code = ResponseModel.DATA_NOT_FOUND;
                        res.body = "";
                    }
                }
                else
                if (req.code == RequestModel.LOAD_PRODUCT_REQUEST) {
                    int id = Integer.parseInt(req.body);
                    System.out.println("The client is asking for a product with ID: " + id);
                    ProductModel model = dao.loadProduct(id);
                    if (model != null) {
                        res.code = ResponseModel.OK;
                        res.body = gson.toJson(model);
                    }
                    else {
                        res.code = ResponseModel.DATA_NOT_FOUND;
                        res.body = "";
                    }
                }
                else

                if (req.code == RequestModel.SAVE_PRODUCT_REQUEST) {
                    ProductModel product = gson.fromJson(req.body, ProductModel.class);
                    System.out.println("The client is asking to save a product with ID: " + product.productID);
                    dao.saveProduct(product);
                    res.code = ResponseModel.OK;
                    res.body = gson.toJson(product);
                }
                else
                if (req.code == RequestModel.LOAD_ORDER_REQUEST) { // load a note from database
                    int id = Integer.parseInt(req.body);
                    System.out.println("The client is asking for an order with ID: " + id);
                    Order order = dao.loadOrder(id);
                    if (order == null) {
                        res.code = ResponseModel.DATA_NOT_FOUND;
                        res.body = "";
                    }
                    else {
                        res.code = ResponseModel.OK;
                        res.body = gson.toJson(order);
                    }
                }
                else if (req.code == RequestModel.SAVE_ORDER_REQUEST) {
                    System.out.println(req.body);
                    Order order = gson.fromJson(req.body, Order.class);
                    System.out.println("The client is asking to save an order with ID: " + order.orderID);
                    if (order != null) {
                        dao.saveOrder(order);
                        res.code = ResponseModel.OK;
                        res.body = gson.toJson(order);
                    }
                }
                else
                {
                    res.code = ResponseModel.UNKNOWN_REQUEST;
                    res.body = "";
                }

                String json = gson.toJson(res);
                System.out.println("JSON object of ResponseModel: " + json);
                dos.writeUTF(json);
                dos.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try
        {
            this.dis.close();
            this.dos.close();
        } catch(IOException e){
            e.printStackTrace();
        }
    }
}