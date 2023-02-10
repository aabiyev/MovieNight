package controller;

import repository.interfaces.IClientRepository;
public class ClientController{
    private final IClientRepository rep;
    public ClientController( IClientRepository rep) {

        this.rep = rep;
    }
    public String buyTicket(int movieId){
        boolean buy = rep.buyTicket(movieId);
        return (buy ? "Successfully bought ticket" : "Buying is failed");
    }
    public String returnTicket(){
        boolean retrn = rep.returnTicket();
        return (retrn ? "Ticked returned" : "Returning is failed");
    }
    public void Myaccount(){
        rep.MyAccount();
    }

}
