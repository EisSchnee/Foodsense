package FoodSense.Register;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import FoodSense.Register.RegisterModel.PurchaseInfo;

/**
 * Controller class for the Register component
 * @author Nahom Ebssa
 */
@Controller
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
        // RegisterPurchaseTransaction()
    }

    /**
     * Takes a list of purchases and peforms the transaction
     * @param list list of purchase infos
     */
    public void RegisterPurchaseTransaction(ArrayList<RegisterModel.PurchaseInfo> list)
    {
        // TODO: handle transaction
        list.clear();
    }


    // REST API

    /**
     * Root purchase info page
     * @param model
     * @return
     */
    @GetMapping("/purchase-info")
    public String getPurchaseInfo(Model model) {
        model.addAttribute("purchases", _model.getPurchaseItems());
        return "purchaseinfo";
    }

    /**
     * post to purchase info page
     * @param request
     * @return
     */
    @PostMapping(path = "/purchase-info")
    public String postPurchaseInfo(HttpServletRequest request) {
        System.out.println("[postPurchaseInfo]");

        String best = request.getParameter("best");
        System.out.printf("[postPurchaseInfo] %s\n", best);

        Map<String, String[]> paramMap = request.getParameterMap();
        for (String param : paramMap.keySet()) {
            // handle params
            switch (param) {
                case "purchase_info_name":
                    
                    break;
            
                default:
                    break;
            }
        }

        return "purchaseinfo";
    }

    /**
     * get single purchase info
     * @param id id of the purchase info
     * @return
     */
    @GetMapping("/purchase-info/{id}")
    public String getPurchaseInfo(Model model, @PathVariable String id) {
        
        int purchaseId = Integer.parseInt(id);

        // search for the info by its purchase id
        ArrayList<PurchaseInfo> purchaseItems = _model.getPurchaseItems();
        PurchaseInfo info = null;
        for (PurchaseInfo i : purchaseItems) {
            if (i.getPurchaseId() == purchaseId) {
                info = i;
                model.addAttribute("purchase_info", info);
            }
        }

        // show error page if it doesnt exist
        if (info == null) {
            System.out.printf("[getPurchaseInfoById] %s\n", "<info>");
            model.addAttribute("error", String.format("No such purchase info with id %s.", id));
            return "purchaseinfo_error";
        }
        else {
            System.out.printf("[getPurchaseInfoById] %s\n", info);
            return "purchaseinfo";
        }

    }
}