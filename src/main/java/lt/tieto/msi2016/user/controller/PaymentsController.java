package lt.tieto.msi2016.user.controller;

import lt.tieto.msi2016.user.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PaymentsController {
    @Autowired
    private PaymentService paymentService;
    @RequestMapping(method = RequestMethod.GET, path = "/payment/accept")
    public String acceptPayment(@RequestParam("data") String data){
        System.out.print("Accepted payment");
        paymentService.updateBalanceAndAcceptPayment(data);
        return "redirect:/accepted.html";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/payment/reject")
    public String rejectPayment(@RequestParam("data") String data){
        System.out.print("Rejected payment");
        paymentService.updateStatusToRejected(data);
        return "redirect:/cancelled.html";
    }
}
