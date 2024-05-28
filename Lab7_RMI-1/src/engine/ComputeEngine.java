package engine;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import compute.Compute;
import compute.Task;

public class ComputeEngine implements Compute {

    public ComputeEngine(){
        super();
    }

    public <T> T executeTask(Task<T> t) throws RemoteException{
        return t.execute();
    }

    public static void main (String[] args){
        Compute engine = new ComputeEngine();
        try{
            Compute stub = (Compute) UnicastRemoteObject.exportObject(engine, 0);

            Registry registry = LocateRegistry.createRegistry(8080);
            String name = "Compute";
            registry.rebind(name, stub);

            System.out.println("ComputeEngine готовий до роботи!");

        } catch (RemoteException e){
            System.err.println("ComputeEngine exception: ");
            e.printStackTrace();
        }
    }
}