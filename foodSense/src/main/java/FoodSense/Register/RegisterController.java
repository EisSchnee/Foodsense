package FoodSense.Register;

import java.util.ArrayList;

import FoodSense.Register.RegisterModel.PurchaseInfo;

/**
 * Controller class for the Register component
 * @author Nahom Ebssa
 */
public class RegisterController {

    /**
     * View component
     */
    private RegisterView _view;

    /**
     * Model component
     */
    private RegisterModel _model;

    /**
     * Constructor
     */
    public RegisterController() {
        _view = new RegisterView();
        _model = new RegisterModel();
    }

    /**
     * Takes a list of item ids and replaces its contents with
     * the purchased items
     * @param list list of item ids
     */
    public void SendCheckoutItems(ArrayList<Integer> listIds)
    {
        ArrayList<PurchaseInfo> list = _model.getPurchaseItems();
        for (PurchaseInfo info : list)
        {
            listIds.add(info.getPurchaseId());
        }
    }

    /**
     * Takes a list of purchases and peforms the transaction
     * @param list list of purchase infos
     */
    public void RegisterPurchaseTransaction(ArrayList<RegisterModel.PurchaseInfo> list)
    {
        // TODO: handle transaction
    }
}