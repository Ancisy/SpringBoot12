package com.example.exercisefour.controller;

import com.example.exercisefour.model.Customer;
import com.example.exercisefour.repository.InMemoRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class CustomerController {
    private InMemoRepository customerRepo;
    @Autowired
    public CustomerController(InMemoRepository customerRepo){
        this.customerRepo = customerRepo;
    }

    @GetMapping("/")
    public String home(){
        return "index";
    }

    @GetMapping("/listAll")
    public String getCustomerList(Model model){
        List<Customer> customers = customerRepo.getHashCustomerMap().values().stream().toList();
        model.addAttribute("customers",customers);
        return "ListAllCustomer";
    }

    @GetMapping("/customers/delete/{id}")
    public String deleteCustomer(@PathVariable("id") Integer id, Model model){
        customerRepo.deleteByID(id);
        model.addAttribute("customers",customerRepo.getHashCustomerMap().values().stream().toList());
        return "redirect:/listAll";
    }

    @GetMapping("/edit/{id}")
    public String editCustomer(@PathVariable("id") Integer id, Model model){
        Customer result = customerRepo.getHashCustomerMap().get(id);
        model.addAttribute("customer",customerRepo.getHashCustomerMap().get(id));
        return "edit";
    }

    @PostMapping("/update")
    public String updateCustomer(Customer customer,BindingResult result, Model model){
        if(!result.hasErrors()){
            customerRepo.editCustomers(customer);
            model.addAttribute("customers",customerRepo.getHashCustomerMap().values().stream().toList());
            return "redirect:/listAll";
        }
        return "edit";
    }

    //Create Customer
    @GetMapping("/create")
    public String showForm(Model model) {
        model.addAttribute("customers", new Customer());
        return "CustomerForm";
    }

    @PostMapping("/post")
    public String postInfo(@ModelAttribute("customers") Customer customer, BindingResult result, Model model) {
        if (!result.hasErrors()) {
            if(customer.getId()>0) {
                customerRepo.editCustomers(customer);
            }else{
                customerRepo.createCustomer(customer);
            }
            model.addAttribute("customers", customerRepo.getHashCustomerMap());
            return "redirect:/listAll";
            }
        return "personForm";
    }

    //Search by Email
    @GetMapping("/search")
    public String searchCustomer(HttpServletRequest request, Model model){
        String name = request.getParameter("name");
        if(name==""){
            model.addAttribute("customers",customerRepo.getHashCustomerMap().values().stream().toList());
            return "redirect:/listAll";
        }
        else{
            Customer customer = customerRepo.searchCustomer(name);
            model.addAttribute("customers",customer);
            return "listAllCustomer";
        }
    }




}
